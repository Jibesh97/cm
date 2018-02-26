package com.horizon.common.quartz;

import java.util.Date;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.quartz.*; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.horizon.common.util.DateUtil;
import com.horizon.common.util.JsonIoConvertor;

import java.util.UUID;
public class SchedulerModifier {
	
	private Scheduler scheduler;
	
	protected static Logger logger = LoggerFactory.getLogger(SchedulerModifier.class);
	
	//对外提供的修改接口
	public SchedulerModifyVO modifyScheduler(String json){
		SchedulerModifyVO result = new SchedulerModifyVO();
		//参数为空
		if(StringUtils.isBlank(json)) return result;
		
		try {
			result = (SchedulerModifyVO) 
					new JsonIoConvertor().read(json, SchedulerModifyVO.class);
			String operation = result.getOperation();
			logger.info("operation :{}",operation);
			
			if("modifyCron".equals(operation)) {
				//修改定时器的cron表达式
				result = modifyCron(result);
			} else if("exeJob".equals(operation)) {
				//立即执行
			    result = exeJob(result);
			} else if("setTimer".equals(operation)) {
				//定时执行
			    result = setTimer(result);
			}
			return result;
		} catch (Throwable e) {//VO转换失败		
			logger.error("convert to json ", e);
			result.setFailDesc(e.getMessage());
			return result;
		}
		
	}
	//修改cron表达式
	private SchedulerModifyVO modifyCron(SchedulerModifyVO argsVO) {	
		logger.info("replace cron:{}",argsVO.getCron());
		synchronized(Scheduler.class) {
				CronTrigger trigger=null;
				try {
					trigger = (CronTrigger)scheduler
							.getTrigger(argsVO.getTriggerName(), argsVO.getTriggerGroup());	
					logger.info(trigger.getCronExpression());
					trigger.setCronExpression(argsVO.getCron());
	//				trigger.setGroup("TriggerGroup"+UUID.randomUUID());
					scheduler.rescheduleJob(argsVO.getTriggerName(), argsVO.getTriggerGroup(),trigger);	
					argsVO.setIsOK("true");
					logger.info("***********************CRON succ");
					return argsVO;
				} catch (Exception e) {
					logger.error("modifyCron error:",e);
					argsVO.setFailDesc(e.getMessage());
				    return argsVO;
				}	
			}
	}
    //立即执行
	private SchedulerModifyVO exeJob(SchedulerModifyVO argsVO) {
//		String jobDetailName,String jobDetailGroup
		//创建Trigger对象 
		UUID uuid = UUID.randomUUID();
		String triggerName = "Trigger" + uuid;
		logger.debug("trigger name:{}", triggerName);
		String triggerGroup = Scheduler.DEFAULT_GROUP;
		SimpleTrigger strigger = new SimpleTrigger(triggerName, triggerGroup);  
		strigger.setJobGroup(argsVO.getJobDetailGroup());
		strigger.setJobName(argsVO.getJobDetailName());
		logger.info("socket exec time:{}", DateUtil
				.toString(new Date(), DateUtil.DATE_TIME_FORMAT_ALL));
		//部署trigger
		try {
			scheduler.scheduleJob(strigger);
			argsVO.setIsOK("true");
			return argsVO;
		} catch (SchedulerException e) {
			logger.error("exeJob" , e);
			argsVO.setFailDesc(e.getMessage());
			return argsVO;
		}
	}
	//定时执行
	private SchedulerModifyVO setTimer(SchedulerModifyVO argsVO){
			SimpleTrigger strigger = new SimpleTrigger(); 
			
			java.util.Calendar c=java.util.Calendar.getInstance();
			StringTokenizer stk=new StringTokenizer(argsVO.getStartTime());
			int year = Integer.parseInt(stk.nextToken("/"));
			int month = Integer.parseInt(stk.nextToken("/"))-1;//在calendar中月份的表示是从0开始
			int day = Integer.parseInt(stk.nextToken("-").substring(1));
			int hour = Integer.parseInt(stk.nextToken(":").substring(1));
			int minute = Integer.parseInt(stk.nextToken(":"));
			int second = Integer.parseInt(stk.nextToken());
			c.set(year, month, day, hour, minute, second);
//			c.setTimeInMillis(Long.parseLong(argsVO.getStartTime()));
			strigger.setStartTime(c.getTime()); 
			
			strigger.setRepeatCount(0); 
			strigger.setName("Trigger"+UUID.randomUUID()); 
			strigger.setGroup(Scheduler.DEFAULT_GROUP);	
			strigger.setJobGroup(argsVO.getJobDetailGroup());
			strigger.setJobName(argsVO.getJobDetailName());				
			//部署trigger
			try {
				scheduler.scheduleJob(strigger);
				argsVO.setIsOK("true");
				return argsVO;
			} catch (SchedulerException e) {
				logger.error("setTimer", e);
				argsVO.setFailDesc(e.getMessage());
				return argsVO;
			}	
	}
	
	public void setScheduler(Scheduler scheduler){
		this.scheduler=scheduler;
	}
	public Scheduler getScheduler(){
		return this.scheduler;
	}
           
}
