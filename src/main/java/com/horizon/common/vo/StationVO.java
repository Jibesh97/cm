package com.horizon.common.vo;

//站VO
public class StationVO {
	
	//主键
	private  String uuid ;
	//机构名称
	private  String name ;
	//机构UUID
	private  String org ;
	//商户 UUID，冗余字段
	private  String trade ;
	//省编码
	private  String provinceCode ;
	//城市编码
	private  String cityCode ;
	//区编码
	private  String districtCode ;
	//具体的地址
	private  String address ;
	//开始时间
	private  String openAt ;
	//关闭时间
	private  String closeAt ;
	private  String lat;  //坐标位置（纬度）
	private  String lng;  //坐标位置（经度）
	
	
	
	
	
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
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOpenAt() {
		return openAt;
	}
	public void setOpenAt(String openAt) {
		this.openAt = openAt;
	}
	public String getCloseAt() {
		return closeAt;
	}
	public void setCloseAt(String closeAt) {
		this.closeAt = closeAt;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	
	
	
	
	
	
}
