package com.horizon.monitoring.station.vo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StationResponse {
	private int isNew;
	private String freeNum;
	private String offNum;
	private String chargeNum;
	private String maintainNum;
	private List<PileBrief> pileInfos;
	public List<PileBrief> getPileInfos() {
		return pileInfos;
	}
	public void setPileInfos(List<PileBrief> pileInfos) {
		this.pileInfos = pileInfos;
	}
	public int getIsNew() {
		return isNew;
	}
	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}
	public String getFreeNum() {
		return freeNum;
	}
	public void setFreeNum(String freeNum) {
		this.freeNum = freeNum;
	}
	public String getOffNum() {
		return offNum;
	}
	public void setOffNum(String offNum) {
		this.offNum = offNum;
	}
	public String getChargeNum() {
		return chargeNum;
	}
	public void setChargeNum(String chargeNum) {
		this.chargeNum = chargeNum;
	}
	public String getMaintainNum() {
		return maintainNum;
	}
	public void setMaintainNum(String maintainNum) {
		this.maintainNum = maintainNum;
	}
}
