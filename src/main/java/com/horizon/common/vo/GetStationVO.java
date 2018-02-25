package com.horizon.common.vo;


public class GetStationVO {

	private int errorCode;//数据查询是否成功 0成功，其他失败
	private String errorMsg;//如果失败，值为失败信息
	private String data;//返回的其他信息
	
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}