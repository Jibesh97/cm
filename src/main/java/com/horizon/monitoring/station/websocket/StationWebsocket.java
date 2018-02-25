package com.horizon.monitoring.station.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

import com.horizon.common.websocket.StringHandler;
import com.horizon.common.websocket.WebSocketUtil;
import com.horizon.monitoring.station.vo.StationResponse;

@ServerEndpoint("/station")
public class StationWebsocket {       
    
    private static Logger logger = Logger.getLogger(StationWebsocket.class);
   
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
    	StationResponse stat = new StationResponse();
        stat.setIsNew(0);
        //stat.init();
		WebSocketUtil.sendMessage(session, stat);	
		session.addMessageHandler(String.class, new StringHandler());
//        logger.info("AlarmWebsocket size:"+SysContextUtil.stationLinks.size());
        logger.info("user join alarm:"+session.getId());
    }
     
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session){
//    	SysContextUtil.stationLinks.remove(session);  //从set中删除
       logger.info("user leave alarm:"+session.getId());
    }
     
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     * @throws IOException 
     */
    @OnMessage
    public void onMessage(String jobKey, Session session) throws IOException {
    		
	 }
    
    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
       logger.info("发生错误");
        //error.printStackTrace();
    }
     

}

