package com.horizon.common.hsf.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author taos
 * 直流充电桩充电监测信息
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PileMonitorInitDataVO {
	//充电机编号
	private String chargingPileNum;
	//充电接口标识
	private Short chargingPileInterfaceNum;
	//是否连接电池
	private int conncetFlag;
	
	private int relayStatus;
	
	private int ccStatus;
	
	private int cpStatus;
	
	private long sampTime;
	
	private int chargingGunFlag;
	
	private int electronicLockStatus;
	
	private int dcOutputContactorStatus;
	
	private String workStatus;
	
	private Double outputVoltage;
	
	private Double outputCurrent;
	
	private Double demandVoltage;
	
	private Double demandCurrent;
	
	private Short chargeMode;
	
	private int soc;
	
	private Short batteryType;
	
	private Double minimumBatteryTemperature;
	
	private Double maximumBatteryTemperature;
	
	private Integer cumulativeChargeTime;
	
	private Integer estimatedFullChargeTime;
	
	private Double maximumBatteryVoltage;
	
	private Double minimumBatteryVoltage;
	
	private Double totalActivePower;
	
	private Double electricityConsumptionAmount;
	
	private Double serviceFee;
	
	private Short chargingType;
	
	private String userIdentification;
	
	private String tariffModelNumber;
	
	private String serviceChargeModelNumber;
	
	private String batch;
	
	
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

	public int getConncetFlag() {
		return conncetFlag;
	}

	public void setConncetFlag(int conncetFlag) {
		this.conncetFlag = conncetFlag;
	}

	public int getChargingGunFlag() {
		return chargingGunFlag;
	}

	public void setChargingGunFlag(int chargingGunFlag) {
		this.chargingGunFlag = chargingGunFlag;
	}

	public int getElectronicLockStatus() {
		return electronicLockStatus;
	}

	public void setElectronicLockStatus(int electronicLockStatus) {
		this.electronicLockStatus = electronicLockStatus;
	}

	public int getDcOutputContactorStatus() {
		return dcOutputContactorStatus;
	}

	public void setDcOutputContactorStatus(int dcOutputContactorStatus) {
		this.dcOutputContactorStatus = dcOutputContactorStatus;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public Double getOutputVoltage() {
		return outputVoltage;
	}

	public void setOutputVoltage(Double outputVoltage) {
		this.outputVoltage = outputVoltage;
	}

	public Double getOutputCurrent() {
		return outputCurrent;
	}

	public void setOutputCurrent(Double outputCurrent) {
		this.outputCurrent = outputCurrent;
	}

	public Double getDemandVoltage() {
		return demandVoltage;
	}

	public void setDemandVoltage(Double demandVoltage) {
		this.demandVoltage = demandVoltage;
	}

	public Double getDemandCurrent() {
		return demandCurrent;
	}

	public void setDemandCurrent(Double demandCurrent) {
		this.demandCurrent = demandCurrent;
	}

	public Short getChargeMode() {
		return chargeMode;
	}

	public void setChargeMode(Short chargeMode) {
		this.chargeMode = chargeMode;
	}

	public int getSoc() {
		return soc;
	}

	public void setSoc(int soc) {
		this.soc = soc;
	}

	public Short getBatteryType() {
		return batteryType;
	}

	public void setBatteryType(Short batteryType) {
		this.batteryType = batteryType;
	}

	public Double getMinimumBatteryTemperature() {
		return minimumBatteryTemperature;
	}

	public void setMinimumBatteryTemperature(Double minimumBatteryTemperature) {
		this.minimumBatteryTemperature = minimumBatteryTemperature;
	}

	public Double getMaximumBatteryTemperature() {
		return maximumBatteryTemperature;
	}

	public void setMaximumBatteryTemperature(Double maximumBatteryTemperature) {
		this.maximumBatteryTemperature = maximumBatteryTemperature;
	}

	public Integer getCumulativeChargeTime() {
		return cumulativeChargeTime;
	}

	public void setCumulativeChargeTime(Integer cumulativeChargeTime) {
		this.cumulativeChargeTime = cumulativeChargeTime;
	}

	public Integer getEstimatedFullChargeTime() {
		return estimatedFullChargeTime;
	}

	public void setEstimatedFullChargeTime(Integer estimatedFullChargeTime) {
		this.estimatedFullChargeTime = estimatedFullChargeTime;
	}

	public Double getMaximumBatteryVoltage() {
		return maximumBatteryVoltage;
	}

	public void setMaximumBatteryVoltage(Double maximumBatteryVoltage) {
		this.maximumBatteryVoltage = maximumBatteryVoltage;
	}

	public Double getMinimumBatteryVoltage() {
		return minimumBatteryVoltage;
	}

	public void setMinimumBatteryVoltage(Double minimumBatteryVoltage) {
		this.minimumBatteryVoltage = minimumBatteryVoltage;
	}

	public Double getTotalActivePower() {
		return totalActivePower;
	}

	public void setTotalActivePower(Double totalActivePower) {
		this.totalActivePower = totalActivePower;
	}

	public Double getElectricityConsumptionAmount() {
		return electricityConsumptionAmount;
	}

	public void setElectricityConsumptionAmount(Double electricityConsumptionAmount) {
		this.electricityConsumptionAmount = electricityConsumptionAmount;
	}

	public Double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public Short getChargingType() {
		return chargingType;
	}

	public void setChargingType(Short chargingType) {
		this.chargingType = chargingType;
	}

	public String getUserIdentification() {
		return userIdentification;
	}

	public void setUserIdentification(String userIdentification) {
		this.userIdentification = userIdentification;
	}

	public String getTariffModelNumber() {
		return tariffModelNumber;
	}

	public void setTariffModelNumber(String tariffModelNumber) {
		this.tariffModelNumber = tariffModelNumber;
	}

	public String getServiceChargeModelNumber() {
		return serviceChargeModelNumber;
	}

	public void setServiceChargeModelNumber(String serviceChargeModelNumber) {
		this.serviceChargeModelNumber = serviceChargeModelNumber;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public int getRelayStatus() {
		return relayStatus;
	}

	public void setRelayStatus(int relayStatus) {
		this.relayStatus = relayStatus;
	}

	public int getCcStatus() {
		return ccStatus;
	}

	public void setCcStatus(int ccStatus) {
		this.ccStatus = ccStatus;
	}

	public int getCpStatus() {
		return cpStatus;
	}

	public void setCpStatus(int cpStatus) {
		this.cpStatus = cpStatus;
	}

	public long getSampTime() {
		return sampTime;
	}

	public void setSampTime(long sampTime) {
		this.sampTime = sampTime;
	}

}
