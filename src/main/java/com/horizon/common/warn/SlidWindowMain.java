package com.horizon.common.warn;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.horizon.common.base.Config;
import com.horizon.common.base.RedisClientDao;
import com.horizon.common.dao.redis.Tasker;
import com.horizon.common.redis.pubsub.RedisMsgPubSubListener;
import com.horizon.common.util.DateUtil;
import com.horizon.common.warn.bean.AlarmPushMessage;
import com.horizon.common.warn.bean.AlarmWindowedRunner;
import com.horizon.common.warn.delayqu.TaskQueueDaemonThread;
import com.horizon.common.warn.runner.AlarmDelayRunner;
import com.horizon.common.warn.runner.WorkOrderDelayRunner;

/**
 * 超时，时间窗口任务初始化
 * 
 * @author Administrator
 * 
 */
public class SlidWindowMain {

	private static ExecutorService cachedThreadPool = Executors
			.newCachedThreadPool();
	private static Set<String> channelset = new HashSet<String>();
	private static ScheduledExecutorService ses = Executors
			.newSingleThreadScheduledExecutor();
	private static TaskQueueDaemonThread taskThreadDelay;
	
	//告警窗口任务类
	private static AlarmWindowedRunner rum;
	//单位  秒
	private int ALARM_WINDOWLENGTH = 0;
	private final String ALARM_WINDOWLENGTH_KEY = "alarm_windowlength_key";
	private final String ALARM_WINDOWLENGTH_DEFAULT = "3600";
	private int ALARM_SLIDEINTERVAL = 0;
	private final String ALARM_SLIDEINTERVAL_KEY = "alarm_slideinterval_key";
	private final String ALARM_SLIDEINTERVAL_DEFAULT = "300";
	//告警延时派单
	private static long ALARM_DELAYQU = 0;
	private final String ALARM_DELAYQU_KEY = "alarm_delayqu_key";
	private final String ALARM_DELAYQU_DEFAULT = "3600";
	//工单延时接单
	private long WORKO_DELAYQU = 0;
	private final String WORKO_DELAYQU_KEY = "worko_delayqu_key";
	private final String WORKO_DELAYQU_DEFAULT = "900";

	public void init() {
		//参数
		ALARM_WINDOWLENGTH = Integer.valueOf(Config.props.getProperty(ALARM_WINDOWLENGTH_KEY,ALARM_WINDOWLENGTH_DEFAULT));
		ALARM_SLIDEINTERVAL = Integer.valueOf(Config.props.getProperty(ALARM_SLIDEINTERVAL_KEY,ALARM_SLIDEINTERVAL_DEFAULT));
		ALARM_DELAYQU = Long.valueOf(Config.props.getProperty(ALARM_DELAYQU_KEY,ALARM_DELAYQU_DEFAULT));
		
		WORKO_DELAYQU = Long.valueOf(Config.props.getProperty(WORKO_DELAYQU_KEY,WORKO_DELAYQU_DEFAULT));
		
		
		
		
		taskThreadDelay = TaskQueueDaemonThread.getInstance();
		
		taskThreadDelay.init();
		
		
		rum = new AlarmWindowedRunner(ALARM_WINDOWLENGTH, ALARM_SLIDEINTERVAL);

		// 开启窗口轮询任务
		ses.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				rum.emitCurrentWindowCounts();
				System.err.println(String.format("时间【%s】线程名【%s】任务数【%s】",
						DateUtil.getCurrentDateTime("HH:mm:ss"), Thread
								.currentThread().getName(), taskThreadDelay
								.size()));
			}
		}, 2, ALARM_SLIDEINTERVAL, TimeUnit.SECONDS);

	}

	/**
	 * 订阅redis消息-建立故障延时任务 派单
	 * 
	 * 现有逻辑 每个桩一个信道 每个信道一个线程
	 * 
	 */
	public static void subscribeAlarm(RedisClientDao redisClientDao,
			String channel) {
		channel = channel.substring(0, 4);
		if (channelset.add(channel)) {
			cachedThreadPool.execute(new Tasker(redisClientDao,
					new RedisMsgPubSubListener(new AlarmPushMessage(
							taskThreadDelay, rum, ALARM_DELAYQU)), channel + "*"));
		}
	}

	/**
	 * 读取工单信息-建立工单延时任务
	 */
	public static void subscribeWorkOrder() {
		taskThreadDelay.put(1000L, new WorkOrderDelayRunner());
	}

	/**
	 * 读取工单信息-建立监控延时任务
	 */
	public static void subscribeMonitor(RedisClientDao redisClientDao,
			String channel) {

	}

	public static void main(String[] args) {
		System.err.println("ZZ0140".substring(0, 4));
		long nanoTime = TimeUnit.NANOSECONDS.convert(3600000,
				TimeUnit.MILLISECONDS);
		System.err.println(nanoTime);
	}

}
