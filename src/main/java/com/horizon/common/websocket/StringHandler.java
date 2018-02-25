package com.horizon.common.websocket;

import javax.websocket.MessageHandler;

import org.apache.log4j.Logger;


public class StringHandler implements MessageHandler.Whole<String>{

	 private static Logger logger = Logger.getLogger(StringHandler.class);
	 
	@Override
	public void onMessage(String message) {
		logger.debug(message);
	}

}
