package com.horizon.common.vo;

public class RespVO {
	private int code;//数据查询是否成功 0成功，其他失败
	private String error;//如果失败，值为失败信息
	private String message;//返回的其他信息
	private String sign;//文档中没有注释
	private OrgTreeVO data;//查询的数据信息
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public OrgTreeVO getData() {
		return data;
	}
	public void setData(OrgTreeVO data) {
		this.data = data;
	}
	
}
