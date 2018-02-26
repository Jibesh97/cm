package com.horizon.common.quartz;


public class SchedulerModifyVO {
	
	//具体操作类型
	private String operation;
	private String jobDetailName;
	private String jobDetailGroup;
	private String startTime;
	//表达式
	private String cron;
	private String triggerName;
	private String triggerGroup;
	//接口执行是否成功
	private String isOK = "false";
	//失败原因
	private String failDesc;
	
	public String getIsOK() {
		return isOK;
	}
	public void setIsOK(String isOK) {
		this.isOK = isOK;
	}
	public String getFailDesc() {
		return failDesc;
	}
	public void setFailDesc(String failDesc) {
		this.failDesc = failDesc;
	}
	public void setOperation(String operation){
		this.operation=operation;
	}
	public String getOperation(){
		return this.operation;
	}
	public void setJobDetailName(String jobDetailName){
		this.jobDetailName=jobDetailName;
	}
	public String getJobDetailName(){
		return this.jobDetailName;
	}
	public void setJobDetailGroup(String jobDetailGroup){
		this.jobDetailGroup=jobDetailGroup;
	}
	public String getJobDetailGroup(){
		return this.jobDetailGroup;
	}
	public void setstartTime(String startTime){
		this.startTime=startTime;
	}
	public String getStartTime(){
		return this.startTime;
	}
	public void setCron(String cron){
		this.cron=cron;
	}
	public String getCron(){
		return this.cron;
	}
	public void setTriggerName(String triggerName){
		this.triggerName=triggerName;
	}
	public String getTriggerName(){
		return this.triggerName;
	}
	public void setTriggerGroup(String triggerGroup){
		this.triggerGroup=triggerGroup;
	}
	public String getTriggerGroup(){
		return this.triggerGroup;
	}
}
