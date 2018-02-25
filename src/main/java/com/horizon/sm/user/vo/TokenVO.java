package com.horizon.sm.user.vo;

import com.send.sm.user.vo.SysUserVO;



public class TokenVO extends SysUserVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String passWord;
	private String token;
	private String sysOrg;
	private String  province;
	private String  provinceid;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSysOrg() {
		return sysOrg;
	}
	public void setSysOrg(String sysOrg) {
		this.sysOrg = sysOrg;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvinceid() {
		return provinceid;
	}
	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}
	
	
}
