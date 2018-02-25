package com.horizon.common.redis.model;

public class PileStationMechanism {
   /**
    * 桩站机构的反向关系
    */
	private String districtCode;  //区编码
	private String provinceCode;   //省编码
	private String cityCode;     //市编码
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
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
	
}
