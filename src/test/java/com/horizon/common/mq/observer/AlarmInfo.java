package com.horizon.common.mq.observer;

import java.util.List;
import java.util.Map;

public class AlarmInfo {
	
	private String chargingPileNum;
	
	private Short chargingPileInterfaceNum;
	
	private String faultStatus;
	
	private Short warningStatus;
	//类型不明确
	private List<Map<Integer, Short>> indexValue;
	
	private Short status;

	private int sampTime;
	private Long faultWarningNum;
	
	private int indexNum;
	public int getIndexNum() {
		return indexNum;
	}
	public void setIndexNum(int indexNum) {
		this.indexNum = indexNum;
	}
	public Short getValue() {
		return value;
	}
	public void setValue(Short value) {
		this.value = value;
	}
	private Short value;
	
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public int getSampTime() {
		return sampTime;
	}
	public void setSampTime(int sampTime) {
		this.sampTime = sampTime;
	}
	public Long getFaultWarningNum() {
		return faultWarningNum;
	}
	public void setFaultWarningNum(Long faultWarningNum) {
		this.faultWarningNum = faultWarningNum;
	}
	
	public String getChargingPileNum() {
		return chargingPileNum;
	}
	public void setChargingPileNum(String chargingPileNum) {
		this.chargingPileNum = chargingPileNum;
	}
	public Short getChargingPileInterfaceNum() {
		return chargingPileInterfaceNum;
	}
	public void setChargingPileInterfaceNum(Short chargingPileInterfaceNum) {
		this.chargingPileInterfaceNum = chargingPileInterfaceNum;
	}

	public String getFaultStatus() {
		return faultStatus;
	}
	public void setFaultStatus(String faultStatus) {
		this.faultStatus = faultStatus;
	}
	public Short getWarningStatus() {
		return warningStatus;
	}
	public void setWarningStatus(Short warningStatus) {
		this.warningStatus = warningStatus;
	}
	public List<Map<Integer, Short>> getIndexValue() {
		return indexValue;
	}
	public void setIndexValue(List<Map<Integer, Short>> indexValue) {
		this.indexValue = indexValue;
	}
	

	

}
