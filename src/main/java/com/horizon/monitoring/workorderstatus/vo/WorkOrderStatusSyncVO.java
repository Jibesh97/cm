package com.horizon.monitoring.workorderstatus.vo;

import java.util.List;

public class WorkOrderStatusSyncVO {
	
	private String message;//信息描述（更新失败...）
	
	private Boolean successful;//充电站ID  也可以是充电桩ID
	
	private List<WorkOrderStatusVO> result;// 工单状态list

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccessful() {
		return successful;
	}

	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}

	public List<WorkOrderStatusVO> getResult() {
		return result;
	}

	public void setResult(List<WorkOrderStatusVO> result) {
		this.result = result;
	}

}
