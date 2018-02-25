package com.horizon.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

public class IoUtility {

	/**
	 * 将指定长度的数据从输入数据流写到输出数据流中   
	 * 
	 * @param in
	 *            输入
	 * @param out
	 *            输出
	 * @param len
	 *            数据长度
	 * @return 实际数据长度
	 * @throws IOException
	 */
	public static int copy(InputStream in, OutputStream out, int len)
			throws IOException {
		int rlt = 0;
		// 缓存
		byte[] buf = new byte[Math.min(4096, len)];
		int bufsize = buf.length;
		// 读写数据
		for (int l = in.read(buf, 0, Math.min(bufsize, len - rlt)); l != -1; l = in
				.read(buf, 0, Math.min(bufsize, len - rlt))) {
			out.write(buf, 0, l);
			// 已读数据长度
			rlt += l;
			// 判断是否已读满指定长度
			if (rlt >= len)
				break;
		}
		// 返回
		return rlt;
	}

	/**
	 * 从输入数据流中读取文本
	 * 
	 * @param in
	 *            输入数据流
	 * @param charset
	 *            文本编码
	 * @return 文本内容
	 * @throws IOException
	 */
	public static String readStringFromInputStream(InputStream in,
			String charset) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
		IOUtils.copy(in, out);
		return new String(out.toByteArray(), charset);
	}

}
