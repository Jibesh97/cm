package com.horizon.monitoring.pile.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StationBrief {
	private String freeNum;
	private String chargeNum;
	private String offNum;	
	private String maintainNum;
	public void init(){
		java.util.Random r=new java.util.Random();
		freeNum = String.valueOf(r.nextInt(25));
		chargeNum = String.valueOf(r.nextInt(25));
		offNum = String.valueOf(r.nextInt(25));
		maintainNum = String.valueOf(r.nextInt(25));
	}
	public String getFreeNum() {
		return freeNum;
	}
	public void setFreeNum(String freeNum) {
		this.freeNum = freeNum;
	}
	public String getChargeNum() {
		return chargeNum;
	}
	public void setChargeNum(String chargeNum) {
		this.chargeNum = chargeNum;
	}
	public String getOffNum() {
		return offNum;
	}
	public void setOffNum(String offNum) {
		this.offNum = offNum;
	}
	public String getMaintainNum() {
		return maintainNum;
	}
	public void setMaintainNum(String maintainNum) {
		this.maintainNum = maintainNum;
	}

}
