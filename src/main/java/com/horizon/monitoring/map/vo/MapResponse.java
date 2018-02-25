package com.horizon.monitoring.map.vo;

import java.util.List;

public class MapResponse {
	private int isNew;
	private List<CityInfo> citys;
	public int getIsNew() {
		return isNew;
	}
	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}
	public List<CityInfo> getCitys() {
		return citys;
	}
	public void setCitys(List<CityInfo> citys) {
		this.citys = citys;
	}
}
