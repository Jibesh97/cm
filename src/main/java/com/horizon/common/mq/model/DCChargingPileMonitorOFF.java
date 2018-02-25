package com.horizon.common.mq.model;
/**
 * 
 * @author taos
 * 直流充电桩非充电监测信息
 *
 */
public class DCChargingPileMonitorOFF {
	
	//充电机编号
	private String chargingPileNum;
	//充电接口标识
	private Short chargingPileInterfaceNum;
	//是否连接电池
	private boolean conncetFlag;
	
	private boolean chargingGunFlag;
	
	private boolean electronicLockStatus;
	
	private boolean dcOutputContactorStatus;
	
	private String workStatus;
	
	private Double outputVoltage;
	
	private Double outputCurrent;
	
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

	public boolean isConncetFlag() {
		return conncetFlag;
	}

	public void setConncetFlag(boolean conncetFlag) {
		this.conncetFlag = conncetFlag;
	}

	public boolean isChargingGunFlag() {
		return chargingGunFlag;
	}

	public void setChargingGunFlag(boolean chargingGunFlag) {
		this.chargingGunFlag = chargingGunFlag;
	}

	public boolean isElectronicLockStatus() {
		return electronicLockStatus;
	}

	public void setElectronicLockStatus(boolean electronicLockStatus) {
		this.electronicLockStatus = electronicLockStatus;
	}

	public boolean isDcOutputContactorStatus() {
		return dcOutputContactorStatus;
	}

	public void setDcOutputContactorStatus(boolean dcOutputContactorStatus) {
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

}
