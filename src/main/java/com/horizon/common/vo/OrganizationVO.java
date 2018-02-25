package com.horizon.common.vo;

//机构VO
public class OrganizationVO {
	
	//主键
	private  String uuid ;
	//机构名称
	private  String name ;
	//父机构UUID，如果为顶级则为空
	private  String parentOrg ;
	//商户 UUID，冗余字段
	private  String trade ;
	//省编码
	private  String provinceCode ;
	//城市编码
	private  String cityCode ;
	//区编码
	private  String districtCode ;
	
	
	
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentOrg() {
		return parentOrg;
	}
	public void setParentOrg(String parentOrg) {
		this.parentOrg = parentOrg;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	
	
	
	
	
}
