package com.horizon.common.mq.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author taos
 * 用来接收直流 非直流  充电  非充电的 MQ信息
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargingPileMonitor {
	
	//充电机编号
	private String chargingPileNum;
	private long sampTime;
	private int soc;
	private String workStatus;

	public String getChargingPileNum() {
		return chargingPileNum;
	}

	public void setChargingPileNum(String chargingPileNum) {
		this.chargingPileNum = chargingPileNum;
	}

	public long getSampTime() {
		return sampTime;
	}

	public void setSampTime(long sampTime) {
		this.sampTime = sampTime;
	}

	public int getSoc() {
		return soc;
	}

	public void setSoc(int soc) {
		this.soc = soc;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	

}
