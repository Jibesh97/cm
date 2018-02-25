package com.horizon.monitoring.alarm.vo;


public class SendResVO {
	private String message;//描述信息
	private boolean successful;//状态
	private Object data;//数据信息
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccessful() {
		return successful;
	}
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	 
 
}
