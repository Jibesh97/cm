package com.horizon.monitoring.alarm.vo;

import java.util.Date;
import java.util.UUID;

public class AlarmInfo {

	private String id;// 唯一标识

	private String provinces;// 省份
	private String city;// 地市
	private String stationId;// 充电站ID
	private String stationName;// 充电站名称
	private String pile;// 充电桩编号----------

	private String faultType;// 故障类型(0:充电桩故障，1：网络故障，2：闪烁告警)
	private Date faultStateTime;// 故障开始时间
	private Date faultEndTime;// 故障结束时间(没有为空)

	private String faultState;// 故障状态----（0:已恢复1:故障中 ）---------
	private String faultMarking;// 故障标识------------
	private String faultDetails;// 故障原因(故障明细)

	private String orderId;// 工单编号---
	private String orderState;// 工单状态----
	private Date orderStartTime;// 工单派发时间
	private Date orderEndTime;// 工单结束时间(没有为空)

	private String isAffrim;// 是否确认
	
	private String isTop;// 0:普通数据,1:2小时未处理,2:15分钟未接单

	public void init(int id) {
		// "{\"id\": \"1\",\"xuhao\": \"07\",\"gongdanbianhao\":
		// \"20170422124731\",\"gongdanzhaungtai\": \"处理中\",
		// \"chongname\": \"北京市门头沟区影剧院专用充电站(内部)\",\"chongid\":
		// \"11402900000000296\",\"guzhangleixing\": \"充电桩故障\",
		// \"guzhangshijian\": \"2017-04-2212: 48: 37\",\"chulijishho\": \"00: 14: 31\",\"guzhangmingxi\": \"急停按钮动作故障；电度表异常故障\"},"
		// +
	 
		
		
		if (id < 500) {
//			id=99+id; 
//			stationName = "北京市门头沟区影剧院专用充电站(内部)";
//			stationId=UUID.randomUUID().toString().substring(8);
//			pile = UUID.randomUUID().toString().substring(22);
//			faultType = "充电桩故障";
//			faultStateTime=new Date();
//			faultState="1";
//			faultMarking="10";
//			isTop="0";
//			
//			provinces = "北京";
//			city = "北京";
//			faultDetails = "充电电流过大";
//----------------------------------------------------------------------------
//			orderId="456";
//			orderState="3";
//			isTop="0";
  
//--------------------------------------------------------------
//			orderId="999";
//			orderState="4";
//			orderEndTime=new Date();
//			isTop="0";
  
//--------------------------------------------------------------
			  
//			pile = "liweicesi"; 
//			faultState="0";
//			faultMarking="1";
//			faultEndTime=new Date();
//			isTop="0";
//--------------------------------------------------------------
			
			id=99+id; 
			stationName = "形式";
			stationId=UUID.randomUUID().toString().substring(8);
			pile = UUID.randomUUID().toString().substring(22);
			faultType = "充电桩故障";
			faultStateTime=new Date();
			faultState="1";
			faultMarking="10"; 
			
			provinces = "北京";
			city = "北京";
			faultDetails = "充电电流过大";
			isTop="1";
//--------------------------------------------------------------
			
		} 

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvinces() {
		return provinces;
	}

	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getPile() {
		return pile;
	}

	public void setPile(String pile) {
		this.pile = pile;
	}

	public String getFaultType() {
		return faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

	public Date getFaultStateTime() {
		return faultStateTime;
	}

	public void setFaultStateTime(Date faultStateTime) {
		this.faultStateTime = faultStateTime;
	}

	public Date getFaultEndTime() {
		return faultEndTime;
	}

	public void setFaultEndTime(Date faultEndTime) {
		this.faultEndTime = faultEndTime;
	}

	public String getFaultState() {
		return faultState;
	}

	public void setFaultState(String faultState) {
		this.faultState = faultState;
	}

	public String getFaultMarking() {
		return faultMarking;
	}

	public void setFaultMarking(String faultMarking) {
		this.faultMarking = faultMarking;
	}

	public String getFaultDetails() {
		return faultDetails;
	}

	public void setFaultDetails(String faultDetails) {
		this.faultDetails = faultDetails;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public Date getOrderStartTime() {
		return orderStartTime;
	}

	public void setOrderStartTime(Date orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public Date getOrderEndTime() {
		return orderEndTime;
	}

	public void setOrderEndTime(Date orderEndTime) {
		this.orderEndTime = orderEndTime;
	}

	public String getIsAffrim() {
		return isAffrim;
	}

	public void setIsAffrim(String isAffrim) {
		this.isAffrim = isAffrim;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

 

}
