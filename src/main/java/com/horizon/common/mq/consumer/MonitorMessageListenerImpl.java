package com.horizon.common.mq.consumer;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.horizon.common.base.RedisClientDao;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.common.mq.model.ChargingPileMonitor;
import com.horizon.common.redis.Observer.ObMessage;
import com.horizon.common.redis.Observer.util.RedisUtli;
import com.horizon.common.redis.model.PileStatus;
import com.horizon.common.util.JsonUtil;
import com.horizon.common.util.SysContextUtil;

import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 接收充电桩状态信息，刷新缓存
 */
public class MonitorMessageListenerImpl implements MessageListener {

	private static Logger log = Logger
			.getLogger(MonitorMessageListenerImpl.class);

	@Override
	public Action consume(Message message, ConsumeContext consumeContext) {
		log.info(new Date() + " Receive message, Topic is:"
				+ message.getTopic() + ", MsgId is:" + message.getMsgID());
		byte[] body = message.getBody();// fes_dccharge_tag
		// redisDao
		RedisClientDao rdao = (RedisClientDao) SysContextUtil
				.getSpringApplicationContext().getBean("redisClientDAO");
		try {
			// 直流充电桩充电监测信息
			ChargingPileMonitor on = (ChargingPileMonitor) JsonUtil
					.genObjectFromJsonString(new String(body),
							ChargingPileMonitor.class);
                 //初始化观察者的参数
			     ObMessage obMessage = RedisUtli.initObMessage(on.getChargingPileNum(),rdao);
			     if(obMessage != null) {
			    	 if (!on.getWorkStatus().equals(obMessage.getOldPineStatus().getRunSta()) || (ConstantsInfo.WORKSTATUS_WORK).equals(on.getWorkStatus())) {
							//重新封装新值
							PileStatus pilePsNew = new PileStatus();
							pilePsNew.setTime(on.getSampTime());
							pilePsNew.setRunSta(on.getWorkStatus());
							pilePsNew.setSoc(on.getSoc());
							// 放入新的桩状态
							obMessage.setPineStatus(pilePsNew);
							//初始化观察者对象们
							RedisUtli.initObServer(obMessage);
					  }
			     }
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		// 如果想测试消息重投的功能,可以将Action.CommitMessage 替换成Action.ReconsumeLater
		return Action.CommitMessage;
	}

}
