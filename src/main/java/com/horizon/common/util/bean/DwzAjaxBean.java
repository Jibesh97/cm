package com.horizon.common.util.bean;

/**
 * <P>
 * FileName: DwzAjaxBean.java
 * 
 * @author peiyy
 *         <P>
 *         CreateTime: 2012-09-20
 *         <P>
 *         Description:
 *         <P>
 *         Version:v1.0
 *         <P>
 *         History:
 */
public class DwzAjaxBean {
	/**
	 * 状态代码
	 */
	private String statusCode;
	/**
	 * 返回信息
	 */
	private String message;
	/**
    * 
    */
	private String navTabId;
	/**
    * 
    */
	private String rel;
	/**
    * 
    */
	private String callbackType;
	/**
    * 
    */
	private String forwardUrl;
	
	private Object obj;

	/**
	 * <P>
	 * Function: getStatusCode
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @return String
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * <P>
	 * Function: setStatusCode
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @param statusCode
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * <P>
	 * Function: getMessage
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @return String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * <P>
	 * Function: setMessage
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @param message
	 *            void
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * <P>
	 * Function: getNavTabId
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @return String
	 */
	public String getNavTabId() {
		return navTabId;
	}

	/**
	 * <P>
	 * Function: setNavTabId
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @param navTabId
	 *            void
	 */
	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	/**
	 * <P>
	 * Function: getRel
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @return String
	 */
	public String getRel() {
		return rel;
	}

	/**
	 * <P>
	 * Function: setRel
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @param rel
	 */
	public void setRel(String rel) {
		this.rel = rel;
	}

	/**
	 * <P>
	 * Function: getCallbackType
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @return String
	 */
	public String getCallbackType() {
		return callbackType;
	}

	/**
	 * <P>
	 * Function: setCallbackType
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @param callbackType
	 */
	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	/**
	 * <P>
	 * Function: getForwardUrl
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @return String
	 */
	public String getForwardUrl() {
		return forwardUrl;
	}

	/**
	 * <P>
	 * Function: setForwardUrl
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @param forwardUrl
	 *            void
	 */
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	/**
	 * <P>
	 * Function: getObj
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @return Object
	 */
	public Object getObj() {
		return obj;
	}
	/**
	 * <P>
	 * Function: setObj
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author peiyy
	 * @CreateTime: 2012-09-20
	 * @param obj
	 *            void
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}

}
