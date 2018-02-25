package com.horizon.common.redis.model;

public class MechanismStatus {
  /**
   * 机构状态
   */
	private String districtCode;  //区编码
	private String provinceCode;   //省编码
	private String cityCode;     //市编码
	private String mechanismName;//地图界面右上角的中文位置（例如北京）
	private int offNum;//离线数（桩）
	private int faultNum;//故障数（桩）
	private int allNum;//总数数（桩）
	private int ga;//一般告警数（站）
	private int ca;//严重告警数（站）
	private int tsn;//总数（站）
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
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	public String getMechanismName() {
		return mechanismName;
	}
	public void setMechanismName(String mechanismName) {
		this.mechanismName = mechanismName;
	}
	public int getOffNum() {
		return offNum;
	}
	public void setOffNum(int offNum) {
		this.offNum = offNum;
	}
	public int getFaultNum() {
		return faultNum;
	}
	public void setFaultNum(int faultNum) {
		this.faultNum = faultNum;
	}
	public int getAllNum() {
		return allNum;
	}
	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}
	public int getGa() {
		return ga;
	}
	public void setGa(int ga) {
		this.ga = ga;
	}
	public int getCa() {
		return ca;
	}
	public void setCa(int ca) {
		this.ca = ca;
	}
	public int getTsn() {
		return tsn;
	}
	public void setTsn(int tsn) {
		this.tsn = tsn;
	}
     
	
}
