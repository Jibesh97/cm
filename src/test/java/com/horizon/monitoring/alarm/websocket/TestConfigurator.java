package com.horizon.monitoring.alarm.websocket;

import java.util.List;
import java.util.Map;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import org.apache.log4j.Logger;


public class TestConfigurator extends Configurator{
	private static Logger log = Logger.getLogger(TestConfigurator.class);

	@Override
	public boolean checkOrigin(String originHeaderValue){
		log.info(originHeaderValue);
		return true;
	}
	@Override
	public void modifyHandshake(ServerEndpointConfig sec,HandshakeRequest request,HandshakeResponse response){
		log.info("modifyHandshake");
		Map<String,List<String>> map = response.getHeaders();
		for(String key:map.keySet()){
			log.info(key+":"+map.get(key));
		}
	}
}
