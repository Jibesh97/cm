package com.horizon.monitoring.alarm.websocket;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

import com.horizon.common.websocket.StringHandler;
import com.horizon.common.websocket.WebSocketUtil;

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。
@ServerEndpoint(value = "/alarm")
public class AlarmWebsocket {

	private static Logger logger = Logger.getLogger(AlarmWebsocket.class);

	private String[] orgIds;

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session) {
		// 保存连接的机构ID
		Map<String, List<String>> paraMap = session.getRequestParameterMap();
		String orgIds = paraMap.get("orgId").get(0);
		if (orgIds != null) {
			logger.info(orgIds);
			this.orgIds = orgIds.split(",");
			WebSocketUtil.alarmBox.add(this.orgIds, session);
		}
		session.addMessageHandler(String.class, new StringHandler());
		logger.debug("user join alarm:" + session.getId());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(Session session) {
		logger.info("user leave alarm:" + session.getId());
		if (this.orgIds != null)
			WebSocketUtil.alarmBox.remove(this.orgIds, session);// 从容器中移除
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 * @throws IOException
	 */
	@OnMessage
	public void onMessage(String jobKey, Session session) throws IOException {

	}

	/**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		logger.error("websocket link error:"+error.getMessage());
		// error.printStackTrace();
	}

}
