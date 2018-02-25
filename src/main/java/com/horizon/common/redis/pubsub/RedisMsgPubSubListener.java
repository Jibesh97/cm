package com.horizon.common.redis.pubsub;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.horizon.common.util.JsonUtil;
import com.horizon.common.websocket.WebSocketUtil;

import redis.clients.jedis.JedisPubSub;

public class RedisMsgPubSubListener extends JedisPubSub {  
	
	private static Logger log = 
			Logger.getLogger(RedisMsgPubSubListener.class);
	
	private PushMessage pushMsg;
	
	public RedisMsgPubSubListener(){
	}
	public RedisMsgPubSubListener(PushMessage pushMsg){
		this.pushMsg= pushMsg;
	}
	
    @Override  
    public void unsubscribe() {  
        super.unsubscribe();  
    }  
  
    @Override  
    public void unsubscribe(String... channels) {  
        super.unsubscribe(channels);  
    }  
  
    @Override  
    public void subscribe(String... channels) {  
        super.subscribe(channels);  
    }  
  
    @Override  
    public void psubscribe(String... patterns) {  
        super.psubscribe(patterns);  
    }  
  
    @Override  
    public void punsubscribe() {  
        super.punsubscribe();  
    }  
  
    @Override  
    public void punsubscribe(String... patterns) {  
        super.punsubscribe(patterns);  
    }  
  
    @SuppressWarnings("unchecked")
	@Override  
    public void onMessage(String channel, String message) {  

    	log.info("channel:" + channel + "receives message :" + message);
    	Map<String, Object> m = null;
		try {
			m = (Map<String, Object>) JsonUtil
					.genObjectFromJsonString(message, HashMap.class);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		log.info("chargingPileNum:" + m.get("chargingPileNum").toString());
    	WebSocketUtil.sendPileMessage(m.get("chargingPileNum").toString(), message);
       //this.unsubscribe();  
    }  
  
    @Override  
    public void onPMessage(String pattern, String channel, String message) {  
//    	log.info("pattern：【"+pattern+"】channel:【" + channel + "】 receives message :" + message); 
    	pushMsg.push(channel,message);
    }  
  
    @Override  
    public void onSubscribe(String channel, int subscribedChannels) {  
    	log.info("channel:【" + channel + "】 is been subscribed:" + subscribedChannels+"");  
    }  
  
    @Override  
    public void onPUnsubscribe(String pattern, int subscribedChannels) {  
  
    }  
  
    @Override  
    public void onPSubscribe(String pattern, int subscribedChannels) {  
  
    }  
  
    @Override  
    public void onUnsubscribe(String channel, int subscribedChannels) {  
    	log.info("channel:【" + channel + "】 is been unsubscribed:" + subscribedChannels);  
    }  
}  