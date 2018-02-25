package com.horizon.common.util;

import com.horizon.common.util.bean.DwzAjaxBean;


/**
 * <P>
 * FileName: DwzAjaxUtil.java
 * 
 * @author peiyy
 *         <P>
 *         CreateTime: 2012-09-20
 *         <P>
 *         Description: 放置与Ajax有关的工具方法
 *         <P>
 *         Version:v1.0
 *         <P>
 *         History:
 */
public class DwzAjaxUtil {

	public static final String DWZ_STATUS_CODE_OK = "200";

	public static final String DWZ_STATUS_CODE_WARN = "260";

	public static final String DWZ_STATUS_CODE_ERROR = "300";

	public static final String DWZ_STATUS_CODE_TIMEOUT = "301";

	public static final String DWZ_CALLBACK_TYPE_CLOSE_CURRENT = "closeCurrent";

	public static final String DWZ_CALLBACK_TYPE_CLOSE_ALL = "closeAll";

	public static final String DWZ_DEFAULT_NAV_TAB_ID = "main";

	public static final String DWZ_DEFAULT_REL_CONTAINER = "mainContainer";

	public static final String DWZ_PRO_OUT_REL_CONTAINER = "productOutList";

	public static final String DWZ_PRO_PRICE_REL_CONTAINER = "productPriceList";
	
	public static final String DWZ_EMPLOYEE_LIST_REL_CONTAINER = "employeeList";

	/**
	 * 默认的构造方法
	 */
	private DwzAjaxUtil() {
	}

	/**
	 * <P>
	 * Function: ajaxRetnDefault
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @return Object
	 * @throws Exception
	 */
	public static Object ajaxRetnDefault(String message) throws Exception {
		DwzAjaxBean ajaxFormVO = new DwzAjaxBean();
		ajaxFormVO.setCallbackType(DWZ_CALLBACK_TYPE_CLOSE_CURRENT);
		ajaxFormVO.setForwardUrl("");
		ajaxFormVO.setMessage(message);
		ajaxFormVO.setNavTabId(DWZ_DEFAULT_NAV_TAB_ID);
		ajaxFormVO.setRel(DWZ_DEFAULT_REL_CONTAINER);
		ajaxFormVO.setStatusCode(DWZ_STATUS_CODE_OK);
		return ajaxFormVO;
	}

	/**
	 * <P>
	 * Function: ajaxRetnDefault
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @return Object
	 * @throws Exception
	 */
	public static Object ajaxRetnDefaultRel(String message) throws Exception {
		DwzAjaxBean ajaxFormVO = new DwzAjaxBean();
		ajaxFormVO.setCallbackType(DWZ_CALLBACK_TYPE_CLOSE_CURRENT);
		ajaxFormVO.setForwardUrl("");
		ajaxFormVO.setMessage(message);
		ajaxFormVO.setNavTabId(null);
		ajaxFormVO.setRel(DWZ_DEFAULT_REL_CONTAINER);
		ajaxFormVO.setStatusCode(DWZ_STATUS_CODE_OK);
		return ajaxFormVO;
	}

	/**
	 * <P>
	 * Function: ajaxRetnDefault
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @return Object
	 * @throws Exception
	 */
	public static Object ajaxRetnError(String errorMsg) throws Exception {
		DwzAjaxBean ajaxFormVO = new DwzAjaxBean();
		ajaxFormVO.setCallbackType("");
		ajaxFormVO.setForwardUrl("");
		ajaxFormVO.setMessage(errorMsg);
		ajaxFormVO.setNavTabId("");
		ajaxFormVO.setRel("");
		ajaxFormVO.setStatusCode(DWZ_STATUS_CODE_ERROR);
		return ajaxFormVO;
	}

	/**
	 * <P>
	 * Function: ajaxFormExtendRel
	 * <P>
	 * Description:增加了跳转目标rel
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @param message
	 * @param rel
	 * @return Object
	 * @throws Exception
	 */
	public static Object ajaxFormExtendRel(String message, String rel)
			throws Exception {
		DwzAjaxBean ajaxFormVO = new DwzAjaxBean();
		ajaxFormVO.setCallbackType("closeCurrent");
		ajaxFormVO.setForwardUrl("");
		ajaxFormVO.setMessage(message);
		ajaxFormVO.setNavTabId("");
		ajaxFormVO.setRel(rel);
		ajaxFormVO.setStatusCode(DWZ_STATUS_CODE_OK);
		return ajaxFormVO;
	}

	/**
	 * <P>
	 * Function: ajaxFormExtendNav
	 * <P>
	 * Description:服务器转回navTabId可以把那个navTab标记为reloadFlag=1,
	 * 下次切换到那个navTab时会重新载入内容. callbackType如果是closeCurrent就会关闭当前tab
	 * 只有callbackType="forward"时需要forwardUrl值 statusCode:用来判断是否操作成功如果是200
	 * 在页面上js自动解析为操作成功 300 为失败
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @param message
	 * @param rel
	 * @param callbackType
	 * @param forwardUrl
	 * @param navTabId
	 * @param statusCode
	 * @return Object
	 * @throws Exception
	 */
	public static Object ajaxFormExtendNav(String message, String navTabId,
			String statusCode, String callBackType) throws Exception {
		DwzAjaxBean ajaxFormVO = new DwzAjaxBean();
		ajaxFormVO.setCallbackType(callBackType);
		ajaxFormVO.setForwardUrl("");
		ajaxFormVO.setMessage(message);
		ajaxFormVO.setNavTabId(navTabId);
		ajaxFormVO.setRel("");
		ajaxFormVO.setStatusCode(statusCode);
		return ajaxFormVO;
	}

	/**
	 * <P>
	 * Function: ajaxFormExtendNav
	 * <P>
	 * Description:服务器转回navTabId可以把那个navTab标记为reloadFlag=1,
	 * 下次切换到那个navTab时会重新载入内容. callbackType如果是closeCurrent就会关闭当前tab
	 * 只有callbackType="forward"时需要forwardUrl值 statusCode:用来判断是否操作成功如果是200
	 * 在页面上js自动解析为操作成功 300 为失败
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @param message
	 * @param rel
	 * @param callbackType
	 * @param forwardUrl
	 * @param navTabId
	 * @param statusCode
	 * @return Object
	 * @throws Exception
	 */
	public static Object ajaxFormExtendRel(String message, String rel,
			String statusCode, String callBackType) throws Exception {
		DwzAjaxBean ajaxFormVO = new DwzAjaxBean();
		ajaxFormVO.setCallbackType(callBackType);
		ajaxFormVO.setForwardUrl("");
		ajaxFormVO.setMessage(message);
		ajaxFormVO.setNavTabId("");
		ajaxFormVO.setRel(rel);
		ajaxFormVO.setStatusCode(statusCode);
		return ajaxFormVO;
	}

	/**
	 * <P>
	 * Function: ajaxFormExtendForward
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @param message
	 * @param navTabId
	 * @param statusCode
	 * @param callBackType
	 * @return Object
	 * @throws Exception
	 */
	public static Object ajaxFormExtendForward(String message, String navTabId,
			String forwardUrl, String statusCode, String callBackType)
			throws Exception {
		DwzAjaxBean ajaxFormVO = new DwzAjaxBean();
		ajaxFormVO.setCallbackType(callBackType);
		ajaxFormVO.setForwardUrl(forwardUrl);
		ajaxFormVO.setMessage(message);
		ajaxFormVO.setNavTabId(navTabId);
		ajaxFormVO.setRel("");
		ajaxFormVO.setStatusCode(statusCode);
		return ajaxFormVO;
	}

	/**
	 * <P>
	 * Function: ajaxFormExtendForward
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @param message
	 * @param navTabId
	 * @param statusCode
	 * @param callBackType
	 * @return Object
	 * @throws Exception
	 */
	public static Object ajaxFormExtendForward(String forwardUrl,
			String statusCode) throws Exception {
		DwzAjaxBean ajaxFormVO = new DwzAjaxBean();
		ajaxFormVO.setCallbackType("");
		ajaxFormVO.setForwardUrl(forwardUrl);
		ajaxFormVO.setMessage(null);
		ajaxFormVO.setNavTabId(null);
		ajaxFormVO.setRel("");
		ajaxFormVO.setStatusCode(statusCode);
		return ajaxFormVO;
	}

	/**
	 * <P>
	 * Function: ajaxFormExtendForward
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author zhangsx
	 * @CreateTime: 2012-10-27
	 * @param obj
	 * @param statusCode
	 * @return Object
	 * @throws Exception
	 */
	public static Object ajaxFormExtendForward(Object obj, String statusCode,
			String message) throws Exception {
		DwzAjaxBean ajaxFormVO = new DwzAjaxBean();
		ajaxFormVO.setObj(obj);
		ajaxFormVO.setCallbackType("");
		ajaxFormVO.setMessage(message);
		ajaxFormVO.setNavTabId(DWZ_DEFAULT_NAV_TAB_ID);
		ajaxFormVO.setRel(DWZ_DEFAULT_REL_CONTAINER);
		ajaxFormVO.setStatusCode(statusCode);
		return ajaxFormVO;
	}
	
	/**
	 * <P>
	 * Function: ajaxFormExtendRel
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author zhangsx
	 * @CreateTime: 2012-10-27
	 * @param obj
	 * @param statusCode
	 * @return Object
	 * @throws Exception
	 */
	public static Object ajaxFormExtendRel(String message) throws Exception {
		DwzAjaxBean ajaxFormVO = new DwzAjaxBean();
		ajaxFormVO.setCallbackType("");
		ajaxFormVO.setMessage(message);
		ajaxFormVO.setNavTabId("");
		ajaxFormVO.setRel(DWZ_DEFAULT_REL_CONTAINER);
		ajaxFormVO.setStatusCode(DWZ_STATUS_CODE_OK);
		return ajaxFormVO;
	}
}
