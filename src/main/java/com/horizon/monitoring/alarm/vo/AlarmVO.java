package com.horizon.monitoring.alarm.vo;

import java.util.List;

public class AlarmVO { 
	private List<AlarmInfo> val;

	private int pageAllNumber;// 根据查询条件查询有多少数据一共多少数据

	private int allNumber;// 一共多少数据(暂时没用)
	private int faultNumber;// 故障
	private int payoutNumber;// 派发
	private int disposeNumber;// 处理
	private int concludeNumber;// 办结
	 
	// 查询条件
	private String type;// 工单类型
	private String name;// 站点名称或电桩编号
	private int page;//页数
	private String isRealTime;//是否暂停刷新
	
	private List<String> ids;//
	private String idsJSON;//

	private boolean isSuccess;// 确认是否成功

	private int startLimit;//开始的查询条数
	private int endLimit;//结束的查询条数

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public int getStartLimit() {
		return startLimit;
	}

	public void setStartLimit(int startLimit) {
		this.startLimit = startLimit;
	}

	public int getEndLimit() {
		return endLimit;
	}

	public void setEndLimit(int endLimit) {
		this.endLimit = endLimit;
	}

 

	public List<AlarmInfo> getVal() {
		return val;
	}

	public void setVal(List<AlarmInfo> val) {
		this.val = val;
	}

	public int getAllNumber() {
		return allNumber;
	}

	public void setAllNumber(int allNumber) {
		this.allNumber = allNumber;
	}

	public int getFaultNumber() {
		return faultNumber;
	}

	public void setFaultNumber(int faultNumber) {
		this.faultNumber = faultNumber;
	}

	public int getPayoutNumber() {
		return payoutNumber;
	}

	public void setPayoutNumber(int payoutNumber) {
		this.payoutNumber = payoutNumber;
	}

	public int getDisposeNumber() {
		return disposeNumber;
	}

	public void setDisposeNumber(int disposeNumber) {
		this.disposeNumber = disposeNumber;
	}

	public int getConcludeNumber() {
		return concludeNumber;
	}

	public void setConcludeNumber(int concludeNumber) {
		this.concludeNumber = concludeNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageAllNumber() {
		return pageAllNumber;
	}

	public void setPageAllNumber(int pageAllNumber) {
		this.pageAllNumber = pageAllNumber;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getIdsJSON() {
		return idsJSON;
	}

	public void setIdsJSON(String idsJSON) {
		this.idsJSON = idsJSON;
	}

	public String getIsRealTime() {
		return isRealTime;
	}

	public void setIsRealTime(String isRealTime) {
		this.isRealTime = isRealTime;
	}

}
