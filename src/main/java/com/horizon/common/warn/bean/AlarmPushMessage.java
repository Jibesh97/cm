package com.horizon.common.warn.bean;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.horizon.common.mq.model.AlarmInfo;
import com.horizon.common.mq.model.IndexValueVO;
import com.horizon.common.redis.pubsub.PushMessage;
import com.horizon.common.util.JsonUtil;
import com.horizon.common.warn.delayqu.Task;
import com.horizon.common.warn.delayqu.TaskQueueDaemonThread;
import com.horizon.common.warn.runner.AlarmDelayRunner;

/**
 * 
 * 消费redis的信道消息实现
 * @author Administrator
 *
 */
public class AlarmPushMessage implements PushMessage {

	private static final Logger LOG = Logger.getLogger(AlarmPushMessage.class);
	
	private TaskQueueDaemonThread taskThreadDelay;
	private AlarmWindowedRunner awr;
	//延时时长
	private long delayTime;
	
	
	
    public AlarmPushMessage(TaskQueueDaemonThread taskThreadDelay,AlarmWindowedRunner awr,long delayTime) {
		super();
		this.taskThreadDelay = taskThreadDelay;
		this.delayTime = delayTime;
		this.awr= awr;
	}



	//将消息推送到延时消息队列
	public void push(String channel, String message) {
		try {
			AlarmInfo info = (AlarmInfo) JsonUtil.genObjectFromJsonString(
					message, AlarmInfo.class);
			List<IndexValueVO> indexValue = info.getIndexValue();
			for (IndexValueVO indexValueVO : indexValue) {
				if (indexValueVO.getValue() == 1) {
					taskThreadDelay.put(delayTime,
							new AlarmDelayRunner(info.getChargingPileNum(),
									indexValueVO));
					LOG.info(String.format("桩【%s】故障【%s】添加任务【%s】",
							info.getChargingPileNum(),
							indexValueVO.getIndexNum(),
							taskThreadDelay.size()));
					
				} else if (indexValueVO.getValue() == 0) {
					Task<Runnable> task = new Task<Runnable>(0,
							new AlarmDelayRunner(info.getChargingPileNum(),
									indexValueVO));

					// 任务清除成功则计入时间窗口
					if(taskThreadDelay.endTask(task)){
						if(awr!=null)
							awr.emitNormal(info.getChargingPileNum()+"|"+indexValueVO.getIndexNum());
					}
					LOG.info(String.format("桩【%s】故障【%s】清除任务【%s】",
							info.getChargingPileNum(),
							indexValueVO.getIndexNum(),
							taskThreadDelay.size()));

				} else {
					LOG.info(String.format("桩【%s】故障【%s】未知",
							info.getChargingPileNum(),
							indexValueVO.getIndexNum()));
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
