package com.horizon.common.exception;

import org.apache.log4j.Logger;

/**
 * 
 * <P>
 * FileName: GenericException.java
 * 
 * @author 
 *         <P>
 *         CreateTime: 2012-09-17
 *         <P>
 *         Description:
 *         <P>
 *         Version:v1.0
 *         <P>
 *         History:
 * @param
 */
public class GenericException extends java.lang.Exception {

	/**
     * 
     */
	private static final long serialVersionUID = -4284209240082570071L;

	/**
	 * 错误信息，当前错误的信息�?
	 */
	private String errMsg;

	/**
     * 
     */
	private static Logger log = Logger.getLogger(GenericException.class);

	/**
     * 
     */
	public GenericException() {
	}

	/**
     * 
     */
	public GenericException(String errMsg) {
		this.errMsg = errMsg;
	}

	/**
	 * 
	 * @param e
	 */
	public GenericException(Exception e) {
		super(e);
	}

	/**
	 * 
	 * 错误信息记录日志�?
	 * 
	 */
	public void writeLog() {
		log.error(this.getMessage());
	}

	/**
	 * 
	 * <P>
	 * Function: getErrMsg
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author: 
	 * @CreateTime: 2012-09-17
	 * @return String
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * 
	 * <P>
	 * Function: getMessage
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @author: 
	 * @CreateTime: 2012-09-17
	 * @return String
	 */
	public String getMessage() {
		return errMsg;
	}
}
