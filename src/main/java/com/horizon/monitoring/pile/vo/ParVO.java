package com.horizon.monitoring.pile.vo;

public class ParVO {
	//直流桩名字 	A组直流桩
	private String name;
	//直流桩下的指定站 A1
	private String content;
	//当前桩ID
	private String pileid;
	//当前桩所属的站ID
	private String stationCode;
	//当前桩所属机构权限
	private String mechanismCode;
	//运行状态
	private String operatingStatus;
	//车辆SOC
	private String vehicleSOC;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOperatingStatus() {
		return operatingStatus;
	}
	public void setOperatingStatus(String operatingStatus) {
		this.operatingStatus = operatingStatus;
	}
	public String getVehicleSOC() {
		return vehicleSOC;
	}
	public void setVehicleSOC(String vehicleSOC) {
		this.vehicleSOC = vehicleSOC;
	}
	public String getPileid() {
		return pileid;
	}
	public void setPileid(String pileid) {
		this.pileid = pileid;
	}
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	public String getMechanismCode() {
		return mechanismCode;
	}
	public void setMechanismCode(String mechanismCode) {
		this.mechanismCode = mechanismCode;
	}

}
