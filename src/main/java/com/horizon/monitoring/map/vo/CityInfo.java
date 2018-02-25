package com.horizon.monitoring.map.vo;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.horizon.common.redis.model.MechanismStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityInfo extends MechanismStatus {
	private String code;// code
	private String local;// 城市

	List<StationInfo> stationList = new ArrayList<StationInfo>();

	List<CityInfo> citys = new ArrayList<CityInfo>();// 子级别list

	public String getCode() {
		return code;
	}

	public void setDistrictCode(String code) {

		super.setDistrictCode(code);
		if (code != "") {
			this.code = code;
		}
	}

	public void setProvinceCode(String code) {

		super.setProvinceCode(code);
		if (code != "") {
			this.code = code;
		}
	}

	public void setCityCode(String code) {

		super.setCityCode(code);
		if (code != "") {
			this.code = code;
		}
	}

	public void setMechanismName(String name) {

		super.setMechanismName(name);

		this.local = name;

	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public List<StationInfo> getStationList() {
		return stationList;
	}

	public void setStationList(List<StationInfo> stationList) {
		this.stationList = stationList;
	}

	public List<CityInfo> getCitys() {
		return citys;
	}

	public void setCitys(List<CityInfo> citys) {
		this.citys = citys;
	}

}
