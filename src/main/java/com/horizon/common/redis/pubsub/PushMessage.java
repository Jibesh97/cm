package com.horizon.common.redis.pubsub;


public interface PushMessage {

	// 将消息推送
	void push(String channel, String message);

}
