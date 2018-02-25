package com.horizon.monitoring.alarm.vo;

import java.util.Date;
import java.util.List;

import com.horizon.common.mq.model.IndexValueVO;

public class AlarmMQInfo {

	private String chargingPileNum;//桩编号
	
	private Short chargingPileInterfaceNum;//充电接口标识
	
	private Short faultStatus;//故障总  0正常，1故障
	
	private Short warningStatus;//告警总  0正常，1故障 
	
	private List<IndexValueVO> indexValue; // Vector  Int 告警类型   Short 故障、告警值
	
	private Short status; // 质量状态 ： STATUS_DEBUG  1   调试状态    STATUS_OFFLINE  2  离线状态    STATUS _EXCEPTION   4 数据异常

	private Date sampTime;//更新时间
	
	private Short faultWarningNum;//故障、告警数
	
	private String dataType;//数据类型

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

	public Short getFaultStatus() {
		return faultStatus;
	}

	public void setFaultStatus(Short faultStatus) {
		this.faultStatus = faultStatus;
	}

	public Short getWarningStatus() {
		return warningStatus;
	}

	public void setWarningStatus(Short warningStatus) {
		this.warningStatus = warningStatus;
	}

	public List<IndexValueVO> getIndexValue() {
		return indexValue;
	}

	public void setIndexValue(List<IndexValueVO> indexValue) {
		this.indexValue = indexValue;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}


	public Date getSampTime() {
		return sampTime;
	}

	public void setSampTime(Date sampTime) {
		this.sampTime = sampTime;
	}

	public Short getFaultWarningNum() {
		return faultWarningNum;
	}

	public void setFaultWarningNum(Short faultWarningNum) {
		this.faultWarningNum = faultWarningNum;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	
}
