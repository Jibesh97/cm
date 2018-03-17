package com.horizon.common.vo;

public class GeocodingResultJson {
	private AddressComponentJson addressComponent;
	private String formatted_address;
	private String business;
	private String sematic_description;
	private String cityCode;

	/** 经纬度 */
	private class Location {
		private String lng;
		private String lat;
		
		public String getLng() {
			return lng;
		}
		public void setLng(String lng) {
			this.lng = lng;
		}
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
	}

	public AddressComponentJson getAddressComponent() {
		return addressComponent;
	}

	public void setAddressComponent(AddressComponentJson addressComponent) {
		this.addressComponent = addressComponent;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getSematic_description() {
		return sematic_description;
	}

	public void setSematic_description(String sematic_description) {
		this.sematic_description = sematic_description;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	
}
