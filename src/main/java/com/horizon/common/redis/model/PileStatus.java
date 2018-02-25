package com.horizon.common.redis.model;

public class PileStatus {
  /**
   * 桩状态
   */
	private String runSta;  //运行状态（桩） 1：空闲中  2：充电中  3：离线中   4：维护中
	private String pileCode;  //桩编号
	private int soc;  //车辆SOC
	private int isAlarm;  // 告警状态（桩） 0：正常  1：告警、
	private String pileName;//桩名
	private String groupName;//桩所属组
	private String ps;  //电源
	private int offCount;  //离线次数
	private int faultCount; //故障次数
	private int chargeCount;  //充电次数
	private long time;  //时间
	private double successRate;  //成功率
	private double offRate;  //离线率
	private double faultRate;  //故障率
	private double repairCount;  //故障修复率
	private int completedNum;//办结数
	private int handleNum;//处理数
	private int payNum;//派发数
	private int alarmNum;//告警数（告警类型）
	
	public int getCompletedNum() {
		return completedNum;
	}
	public void setCompletedNum(int completedNum) {
		this.completedNum = completedNum;
	}
	public int getHandleNum() {
		return handleNum;
	}
	public void setHandleNum(int handleNum) {
		this.handleNum = handleNum;
	}
	public int getPayNum() {
		return payNum;
	}
	public void setPayNum(int payNum) {
		this.payNum = payNum;
	}
	public int getAlarmNum() {
		return alarmNum;
	}
	public void setAlarmNum(int alarmNum) {
		this.alarmNum = alarmNum;
	}
	public String getRunSta() {
		return runSta;
	}
	public void setRunSta(String runSta) {
		this.runSta = runSta;
	}
	public String getPileCode() {
		return pileCode;
	}
	public void setPileCode(String pileCode) {
		this.pileCode = pileCode;
	}
	public String getPileName() {
		return pileName;
	}
	public void setPileName(String pileName) {
		this.pileName = pileName;
	}
	public int getSoc() {
		return soc;
	}
	public void setSoc(int soc) {
		this.soc = soc;
	}
	public int getIsAlarm() {
		return isAlarm;
	}
	public void setIsAlarm(int isAlarm) {
		this.isAlarm = isAlarm;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	public int getOffCount() {
		return offCount;
	}
	public void setOffCount(int offCount) {
		this.offCount = offCount;
	}
	public int getChargeCount() {
		return chargeCount;
	}
	public void setChargeCount(int chargeCount) {
		this.chargeCount = chargeCount;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public double getSuccessRate() {
		return successRate;
	}
	public void setSuccessRate(double successRate) {
		this.successRate = successRate;
	}
	public double getOffRate() {
		return offRate;
	}
	public void setOffRate(double offRate) {
		this.offRate = offRate;
	}
	public int getFaultCount() {
		return faultCount;
	}
	public void setFaultCount(int faultCount) {
		this.faultCount = faultCount;
	}
	public double getFaultRate() {
		return faultRate;
	}
	public void setFaultRate(double faultRate) {
		this.faultRate = faultRate;
	}
	public double getRepairCount() {
		return repairCount;
	}
	public void setRepairCount(double repairCount) {
		this.repairCount = repairCount;
	}

}
