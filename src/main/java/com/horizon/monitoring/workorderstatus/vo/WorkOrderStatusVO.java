package com.horizon.monitoring.workorderstatus.vo;

import java.util.Date;

public class WorkOrderStatusVO {
	
	private String orderId;//工单编号
	
	private String chargeStationId;//充电站ID  也可以是充电桩ID
	
	private String chargeStation;// 充电站
	
	private Date patrolId;// 巡视记录id	
	
	private int patrolPersonId;// 巡视人id
	
	private Date startDate;//开始日期
	
	private int distributePersonId ;// 派发人id
	
	private String distributePerson;// 派发人
	
	private int patrolTeamId;//巡视班组id
	
	private String patrolTeam ;//巡视班组
	
	private String patrolReason; //巡视内容
	
	private int faultType; //故障类型
	
	private String alarmType; // 告警类型

	private String duration;//持续时间
	
	private int taskChannel;//任务渠道
	
	private String status;//状态
	
	private String workOrderUserId;//受理人
	
	private Date completeTime;//	完成时间	
	
	private int getSinglePersonId;//领单人id
	
	private String getSinglePerson;//领单人
	
	private Date getSingleTime;// 领单人时间	
	
	private String operate;//操作	
	
	private int isAlert;// 是否提醒
	
	private String alertContent;//提醒内容
	
	private int isWarn ;// 是否告警
	
	private String warnContent;//告警内容
	
	private String tid;//工单id
	
	private int businessType ;//业务类型 default 0
	
	private int currStatus; //当前状态 default 0
	
	private int currClientStatus; //当前终端状态 default 0
	
	private int source; //来源  default 0

	private int orderType;//default 0	
	
	private int pileId;//充电桩id
	
	private String equipRunNum;//设备运行编号
	
	private String waitReceivePerson;//待接单人
	
	private int waitReceivePersonId;//	待接单人id
	
	

	public int getGetSinglePersonId() {
		return getSinglePersonId;
	}

	public void setGetSinglePersonId(int getSinglePersonId) {
		this.getSinglePersonId = getSinglePersonId;
	}

	public String getGetSinglePerson() {
		return getSinglePerson;
	}

	public void setGetSinglePerson(String getSinglePerson) {
		this.getSinglePerson = getSinglePerson;
	}

	public Date getGetSingleTime() {
		return getSingleTime;
	}

	public void setGetSingleTime(Date getSingleTime) {
		this.getSingleTime = getSingleTime;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public int getIsAlert() {
		return isAlert;
	}

	public void setIsAlert(int isAlert) {
		this.isAlert = isAlert;
	}

	public String getAlertContent() {
		return alertContent;
	}

	public void setAlertContent(String alertContent) {
		this.alertContent = alertContent;
	}

	public int getIsWarn() {
		return isWarn;
	}

	public void setIsWarn(int isWarn) {
		this.isWarn = isWarn;
	}

	public String getWarnContent() {
		return warnContent;
	}

	public void setWarnContent(String warnContent) {
		this.warnContent = warnContent;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public int getBusinessType() {
		return businessType;
	}

	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}

	public int getCurrStatus() {
		return currStatus;
	}

	public void setCurrStatus(int currStatus) {
		this.currStatus = currStatus;
	}

	public int getCurrClientStatus() {
		return currClientStatus;
	}

	public void setCurrClientStatus(int currClientStatus) {
		this.currClientStatus = currClientStatus;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public int getPileId() {
		return pileId;
	}

	public void setPileId(int pileId) {
		this.pileId = pileId;
	}

	public String getEquipRunNum() {
		return equipRunNum;
	}

	public void setEquipRunNum(String equipRunNum) {
		this.equipRunNum = equipRunNum;
	}

	public String getWaitReceivePerson() {
		return waitReceivePerson;
	}

	public void setWaitReceivePerson(String waitReceivePerson) {
		this.waitReceivePerson = waitReceivePerson;
	}

	public int getWaitReceivePersonId() {
		return waitReceivePersonId;
	}

	public void setWaitReceivePersonId(int waitReceivePersonId) {
		this.waitReceivePersonId = waitReceivePersonId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getChargeStationId() {
		return chargeStationId;
	}

	public void setChargeStationId(String chargeStationId) {
		this.chargeStationId = chargeStationId;
	}

	public String getChargeStation() {
		return chargeStation;
	}

	public void setChargeStation(String chargeStation) {
		this.chargeStation = chargeStation;
	}

	public Date getPatrolId() {
		return patrolId;
	}

	public void setPatrolId(Date patrolId) {
		this.patrolId = patrolId;
	}

	public int getPatrolPersonId() {
		return patrolPersonId;
	}

	public void setPatrolPersonId(int patrolPersonId) {
		this.patrolPersonId = patrolPersonId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getDistributePersonId() {
		return distributePersonId;
	}

	public void setDistributePersonId(int distributePersonId) {
		this.distributePersonId = distributePersonId;
	}

	public String getDistributePerson() {
		return distributePerson;
	}

	public void setDistributePerson(String distributePerson) {
		this.distributePerson = distributePerson;
	}

	public int getPatrolTeamId() {
		return patrolTeamId;
	}

	public void setPatrolTeamId(int patrolTeamId) {
		this.patrolTeamId = patrolTeamId;
	}

	public String getPatrolTeam() {
		return patrolTeam;
	}

	public void setPatrolTeam(String patrolTeam) {
		this.patrolTeam = patrolTeam;
	}

	public String getPatrolReason() {
		return patrolReason;
	}

	public void setPatrolReason(String patrolReason) {
		this.patrolReason = patrolReason;
	}

	public int getFaultType() {
		return faultType;
	}

	public void setFaultType(int faultType) {
		this.faultType = faultType;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getTaskChannel() {
		return taskChannel;
	}

	public void setTaskChannel(int taskChannel) {
		this.taskChannel = taskChannel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWorkOrderUserId() {
		return workOrderUserId;
	}

	public void setWorkOrderUserId(String workOrderUserId) {
		this.workOrderUserId = workOrderUserId;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	
}
