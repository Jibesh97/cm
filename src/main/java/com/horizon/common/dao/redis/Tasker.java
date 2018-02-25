package com.horizon.common.dao.redis;

import com.horizon.common.base.RedisClientDao;

import redis.clients.jedis.JedisPubSub;

public class Tasker implements Runnable {
	private String[] channel = null;// 监听的消息通道
	private JedisPubSub jedisPubSub = null;// 消息处理任务
	private RedisClientDao redisClientDao;

	public Tasker(RedisClientDao redisClientDao, JedisPubSub jedisPubSub,
			String... channel) {
		this.jedisPubSub = jedisPubSub;
		this.channel = channel;
		this.redisClientDao = redisClientDao;
	}

	@Override
	public void run() {
		// 监听channel通道的消息
//		redisClientDao.subscribe(jedisPubSub, channel);
		redisClientDao.psubscribe(jedisPubSub, channel);
	}

}
