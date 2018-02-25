package com.horizon.common.redis.model;

public class StationStatus {
  /**
   * 站状态
   */
	private int freeNum;  //空闲数（桩）
	private int chargeNum;  //充电数（桩）
	private int faultNum;  //告警数（桩）
	private int maintainNum;  //维护数（桩）
	private int offNum;  //离线数（桩）
	private int normalNum;  //正常运行数（桩）
	private String stationName;  //站名（中文）
	private int allNum;  //总数（桩）
	private int alarmState;  //告警状态（站） 0：正常  1：一般告警 2：严重告警
	private String lat;  //坐标位置（纬度）
	private String lng;  //坐标位置（经度）
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
	public int getFreeNum() {
		return freeNum;
	}
	public void setFreeNum(int freeNum) {
		this.freeNum = freeNum;
	}
	public int getChargeNum() {
		return chargeNum;
	}
	public void setChargeNum(int chargeNum) {
		this.chargeNum = chargeNum;
	}
	public int getFaultNum() {
		return faultNum;
	}
	public void setFaultNum(int faultNum) {
		this.faultNum = faultNum;
	}
	public int getMaintainNum() {
		return maintainNum;
	}
	public void setMaintainNum(int maintainNum) {
		this.maintainNum = maintainNum;
	}
	public int getOffNum() {
		return offNum;
	}
	public void setOffNum(int offNum) {
		this.offNum = offNum;
	}
	public int getNormalNum() {
		return normalNum;
	}
	public void setNormalNum(int normalNum) {
		this.normalNum = normalNum;
	}
	
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public int getAllNum() {
		return allNum;
	}
	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}
	public int getAlarmState() {
		return alarmState;
	}
	public void setAlarmState(int alarmState) {
		this.alarmState = alarmState;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	
}
