package com.horizon.monitoring.workorderstatus.vo;

import java.util.Date;

public class WorkOrderStatusDBInfo {
		private static final long serialVersionUID = 1L;
		private int ID;// 唯一标识
		private String provinceId;// 省编号
		private String provinceName;// 省名称
		private String cityId;// 市编号
		private String cityName;// 市名称
		private String countyId;// 区编号
		private String countyName;// 区名称
		private String stationId;// 充电站编号
		private String stationName;// 充电站名称
		private String isAffrim;// 是否确认 0：否 1：是
		private String pileId;// 充电桩编号
		private String faultType;// 故障类型
		private String faultState;// 故障状态
		private Date faultStartTime;// 故障开始时间
		private Date faultEndTime;// 故障结束时间
		private String faultMarking;// 故障标识
		private String faultDetails;// 故障详情
		private String workOrderId;// 工单编号
		private String workOrderState;// 工单状态
		private Date workOrderStartTime;// 工单开始时间
		private Date workOrderEndTime;// 工单办结时间
		private String workOrderUserId;// 受理人编号

		public int getID() {
			return ID;
		}

		public void setID(int iD) {
			ID = iD;
		}

		public String getProvinceId() {
			return provinceId;
		}

		public void setProvinceId(String provinceId) {
			this.provinceId = provinceId;
		}

		public String getProvinceName() {
			return provinceName;
		}

		public void setProvinceName(String provinceName) {
			this.provinceName = provinceName;
		}

		public String getCityId() {
			return cityId;
		}

		public void setCityId(String cityId) {
			this.cityId = cityId;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public String getCountyId() {
			return countyId;
		}

		public void setCountyId(String countyId) {
			this.countyId = countyId;
		}

		public String getCountyName() {
			return countyName;
		}

		public void setCountyName(String countyName) {
			this.countyName = countyName;
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

		public String getIsAffrim() {
			return isAffrim;
		}

		public void setIsAffrim(String isAffrim) {
			this.isAffrim = isAffrim;
		}

		public String getPileId() {
			return pileId;
		}

		public void setPileId(String pileId) {
			this.pileId = pileId;
		}

		public String getFaultType() {
			return faultType;
		}

		public void setFaultType(String faultType) {
			this.faultType = faultType;
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

		public String getWorkOrderId() {
			return workOrderId;
		}

		public void setWorkOrderId(String workOrderId) {
			this.workOrderId = workOrderId;
		}

		public String getWorkOrderState() {
			return workOrderState;
		}

		public void setWorkOrderState(String workOrderState) {
			this.workOrderState = workOrderState;
		}

		public Date getFaultStartTime() {
			return faultStartTime;
		}

		public void setFaultStartTime(Date faultStartTime) {
			this.faultStartTime = faultStartTime;
		}

		public Date getFaultEndTime() {
			return faultEndTime;
		}

		public void setFaultEndTime(Date faultEndTime) {
			this.faultEndTime = faultEndTime;
		}

		public Date getWorkOrderStartTime() {
			return workOrderStartTime;
		}

		public void setWorkOrderStartTime(Date workOrderStartTime) {
			this.workOrderStartTime = workOrderStartTime;
		}

		public Date getWorkOrderEndTime() {
			return workOrderEndTime;
		}

		public void setWorkOrderEndTime(Date workOrderEndTime) {
			this.workOrderEndTime = workOrderEndTime;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public String getWorkOrderUserId() {
			return workOrderUserId;
		}

		public void setWorkOrderUserId(String workOrderUserId) {
			this.workOrderUserId = workOrderUserId;
		}


}
