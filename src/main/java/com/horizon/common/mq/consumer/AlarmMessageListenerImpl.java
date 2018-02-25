package com.horizon.common.mq.consumer;

import java.io.IOException;
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
import com.horizon.common.mq.model.IndexValueVO;
import com.horizon.common.util.JsonUtil;
import com.horizon.common.util.enums.ACFaultEnum;
import com.horizon.common.util.enums.DCFaultEnum;
import com.horizon.monitoring.alarm.service.IAlarmService;
import com.horizon.monitoring.alarm.vo.AlarmAllInfo;
import com.horizon.monitoring.alarm.vo.AlarmMQInfo;

/**
 * 接收充电桩告警信息，刷新缓存
 */
public class AlarmMessageListenerImpl implements MessageListener {

	@Autowired
	private RedisClientDao redisClientDao;
	@Autowired
	private IAlarmService alarmService;

	private static Logger log = Logger
			.getLogger(AlarmMessageListenerImpl.class);

	@Override
	public Action consume(Message message, ConsumeContext consumeContext) {

		log.debug(new Date() + " Receive message, Topic is:"
				+ message.getTopic() + ", MsgId is:" + message.getMsgID());

		byte[] body = message.getBody();// fes_dccharge_tag

		try {
			if (log.isDebugEnabled()) {
				log.debug(new String(body));
			}
			// ① 解析告警信息json
			AlarmMQInfo info = (AlarmMQInfo) JsonUtil.genObjectFromJsonString(
					new String(body), AlarmMQInfo.class);
			// ② 插入关系型DB

			AlarmAllInfo alarmAllInfo = alarmService.getAlarmMQVO(info);

			String stationNum = alarmAllInfo.getStationId();
			String pileNum = alarmAllInfo.getPileId();
			
			for (IndexValueVO a : info.getIndexValue()) {
				if (a.getValue() != 0) {
					String faultDetails = null;
					String pileIndexNum = alarmService.getPs(stationNum, pileNum);
					if (pileIndexNum.equals(ConstantsInfo.PILE_POWER_SUPPLY_DC)) {
						faultDetails = DCFaultEnum.getName(a.getIndexNum());
					} else {
						faultDetails = ACFaultEnum.getName(a.getIndexNum());
					}
					alarmAllInfo.setFaultState(a.getValue().toString());// 1:故障 0:故障修复
					alarmAllInfo.setFaultStartTime(info.getSampTime());
					alarmAllInfo.setFaultMarking(Integer.toString(a.getIndexNum()));
					alarmAllInfo.setFaultDetails(faultDetails);
					int ID = alarmService.addPileFault(alarmAllInfo);
					alarmAllInfo.setID(ID);
					// ④websocket 推送到前端
					redisClientDao
					.publish(ConstantsInfo.CHANNEL_WS_PIPLE, JsonUtil.genJsonString(alarmAllInfo));
					// 更新告警数
					alarmService.updateRedisAlarm(alarmAllInfo);
				} else {
					alarmAllInfo.setFaultMarking(Integer.toString(a.getIndexNum()));
					alarmAllInfo.setFaultState(a.getValue().toString());
					// 故障开始与结束时间更新
					alarmAllInfo.setFaultEndTime(info.getSampTime());
					List<?> one = alarmService.getOne(alarmAllInfo);
					if(!one.isEmpty()){
						AlarmAllInfo history = (AlarmAllInfo)one.get(0);
						int rest = alarmService.addAlarmHistory(history);
						if(rest == 1){
							int res = alarmService.deleteAlarmFault(alarmAllInfo);
							if(res == 1){
								log.info("delete sucess");
							} else {
								log.info("delete false");
							}
						} else {
							log.info("addAlarmHistory false");
						}
						// ④websocket 推送到前端
						redisClientDao
						.publish(ConstantsInfo.CHANNEL_WS_PIPLE, JsonUtil.genJsonString(alarmAllInfo));
					}
				}
			}

		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return Action.CommitMessage;
	}

	public void setAlarmService(IAlarmService alarmService) {
		this.alarmService = alarmService;
	}

	public void setRedisClientDao(RedisClientDao redisClientDao) {
		this.redisClientDao = redisClientDao;
	}
}
