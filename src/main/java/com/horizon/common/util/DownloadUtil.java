package com.horizon.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * <P>
 * FileName: DownloadUtil.java
 * 
 * @author peiyy
 *         <P>
 *         CreateTime: 2012-09-20
 *         <P>
 *         Description: 放置与下载有关的工具方法
 *         <P>
 *         Version:v1.0
 *         <P>
 *         History:
 */
public class DownloadUtil {

	private DownloadUtil() {
	}

	public static void downLoad(String filePath, HttpServletResponse response,
			boolean isOnLine) throws Exception {
		OutputStream out = null;
		BufferedInputStream br = null;
		try {
			File f = new File(filePath);
			if (!f.exists()) {
				response.sendError(404, "File not found!");
				return;
			}
			br = new BufferedInputStream(new FileInputStream(f));
			byte[] buf = new byte[1024];
			int len = 0;

			response.reset(); // 非常重要
			if (isOnLine) { // 在线打开方式
				URL u = new URL("file:///" + filePath);
				response.setContentType(u.openConnection().getContentType());
				response.setHeader("Content-Disposition", "inline; filename="
						+ f.getName());
				// 文件名应该编码成UTF-8
			} else { // 纯下载方式
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-Disposition",
						"attachment; filename=" + f.getName());
			}
			out = response.getOutputStream();
			while ((len = br.read(buf)) > 0)
				out.write(buf, 0, len);
		} finally {
			IOUtils.closeQuietly(br);
			IOUtils.closeQuietly(out);
		}
	}
}
