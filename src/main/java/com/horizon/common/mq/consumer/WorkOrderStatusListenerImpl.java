package com.horizon.common.mq.consumer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.horizon.common.base.RedisClientDao;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.common.util.JsonUtil;
import com.horizon.common.util.SysContextUtil;
import com.horizon.monitoring.alarm.vo.AlarmInfo;
import com.horizon.monitoring.alarm.vo.AlarmVO;
import com.horizon.monitoring.workorderstatus.service.IWorkOrderStatusService;
import com.horizon.monitoring.workorderstatus.vo.WorkOrderStatusDBInfo;
import com.horizon.monitoring.workorderstatus.vo.WorkOrderStatusVO;

/**
 * 工单状态同步
 */
public class WorkOrderStatusListenerImpl implements MessageListener {

	@Autowired
	private RedisClientDao redisClientDAO;

	private static Logger log = Logger
			.getLogger(WorkOrderStatusListenerImpl.class);

	@Override
	public Action consume(Message message, ConsumeContext consumeContext) {
		
		log.info("WorkOrderStatus更新");
		log.debug(new Date() + " Receive message, Topic is:"
				+ message.getTopic() + ", MsgId is:" + message.getMsgID());
		
		IWorkOrderStatusService workOrderStatusService = (IWorkOrderStatusService) SysContextUtil
				.getSpringApplicationContext().getBean("workOrderStatusService");
		byte[] body = message.getBody();// fes_dccharge_tag
		
		//获取消息 依据接口文档，解析json 内容
		try {
			WorkOrderStatusVO info = (WorkOrderStatusVO) JsonUtil.genObjectFromJsonString(
					new String(body), WorkOrderStatusVO.class);
			int res = 0;
			if(info.getStatus().equals("9")){
				res = workOrderStatusService.updateWorkOrderStatusOver(info);
				if(res == 0){
					res = workOrderStatusService.updateWorkOrderStatusOverHistory(info);
				}
			} else {
				res = workOrderStatusService.updateWorkOrderStatus(info);
				if(res == 0){
					res = workOrderStatusService.updateWorkOrderStatusHistory(info);
				}
			}
			if(info.getStatus().equals("9")){
				workOrderStatusService.updateRedisComplete(info);
			}
			if(info.getStatus().equals("2")){
				workOrderStatusService.updateRedisPay(info);
			}
			if(info.getStatus().equals("3")){
				workOrderStatusService.updateRedisWork(info);
			}
			AlarmVO alarmVO = new AlarmVO();
			AlarmInfo alarmInfo = new AlarmInfo();
			//获取这个桩编号的mysql中的一整条信息
			List<?> worklis = workOrderStatusService.getAll(info);
			if(worklis.isEmpty()){
				worklis = workOrderStatusService.getAllHistory(info);
			}
			if(!worklis.isEmpty()){
				WorkOrderStatusDBInfo workOrderStatusDB = (WorkOrderStatusDBInfo) worklis.get(0);
				alarmInfo.setOrderState(workOrderStatusDB.getWorkOrderState());// 工单状态
				alarmInfo.setOrderId(workOrderStatusDB.getWorkOrderId());// 工单编号
				alarmInfo.setFaultEndTime(workOrderStatusDB.getFaultEndTime());// 故障结束时间
				alarmInfo.setOrderEndTime(workOrderStatusDB.getWorkOrderEndTime());// 工单结束时间
				List<AlarmInfo> lisAlarmInfo = new ArrayList<AlarmInfo>();
				lisAlarmInfo.add(alarmInfo);
				alarmVO.setVal(lisAlarmInfo);
				//websocket 推送更新
				log.info("workOrder websocket");
				redisClientDAO
				.publish(ConstantsInfo.CHANNEL_WS_PIPLE, JsonUtil.genJsonString(alarmVO));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return Action.CommitMessage;
	}
}
