package com.horizon.common.websocket;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.Session;
import org.apache.log4j.Logger;

/**
 * @author liuzw
 * 
 */
public class OrgLinksBox {
	private static Logger logger = Logger.getLogger(OrgLinksBox.class);

	private Map<String, Set<Session>> linkMap = new ConcurrentHashMap<String, Set<Session>>();

	public Set<String> getOrgSet() {
		return linkMap.keySet();
	}

	/**
	 * 判断某个机构是否存在连接
	 * 
	 * @param orgId
	 * @return
	 */
	public boolean isOn(String orgId) {
		Set<Session> links = linkMap.get(orgId);
		if (links != null && links.size() > 0)
			return true;
		else
			return false;

	}

	/**
	 * 给box里面的机构发送消息
	 * 
	 * @param orgId
	 * @param message
	 */
	public void sendMessage(String orgId, String strMessage) {
		Set<Session> links = linkMap.get(orgId);
		if (links != null) {
			for (Session link : links) {
				logger.debug("send message to " + orgId);
				WebSocketUtil.sendMessage(link, strMessage);
			}
		}
	}

	/**
	 * 给box里面的机构发送消息
	 * 
	 * @param orgId
	 * @param message
	 */
	public void sendMessage(String orgId, Object objMessage) {
		Set<Session> links = linkMap.get(orgId);
		if (links != null) {
			for (Session link : links) {
				WebSocketUtil.sendMessage(link, objMessage);
			}
		}
	}

	/**
	 * 为数组中的每一个机构添加连接
	 * 
	 * @param orgIds
	 * @param session
	 */
	public void add(String[] orgIds, Session session) {
		for (String orgId : orgIds) {
			logger.debug("orgId:" + orgId + " add session " + session.getId());
			WebSocketUtil.alarmBox.add(orgId, session);// 添加到工具类中方便使用
		}
	}

	/**
	 * 为数组中的每一个机构删除连接
	 * 
	 * @param orgIds
	 * @param session
	 */
	public void remove(String[] orgIds, Session session) {
		for (String orgId : orgIds) {
			WebSocketUtil.alarmBox.remove(orgId, session);// 添加到工具类中方便使用
		}
	}

	/**
	 * 在机构对应的session列表中添加此session
	 * 
	 * @param orgId
	 * @param session
	 */
	private void add(String orgId, Session session) {
		if (linkMap.containsKey(orgId)) {// 存在此机构
			Set<Session> orgSessions = linkMap.get(orgId);
			orgSessions.add(session);
		} else {
			CopyOnWriteArraySet<Session> orgSessions = new CopyOnWriteArraySet<Session>();
			orgSessions.add(session);
			linkMap.put(orgId, orgSessions);
		}

	}

	/**
	 * 在机构对应的session列表中删除此session
	 * 
	 * @param orgId
	 * @param session
	 */
	private void remove(String orgId, Session session) {
		logger.debug("user " + orgId + " leave:loose session " + session.getId());
		if (linkMap.containsKey(orgId)) {
			Set<Session> orgSessions = linkMap.get(orgId);
			boolean hasSession = orgSessions.remove(session);
			if (!hasSession) {
				logger.warn("can't find session " + session.getId()
						+ " while org " + orgId + " leave");
			} else {
				logger.debug("remove session " + session.getId() + " while org "
						+ orgId + " leave");
			}
			if (orgSessions.size() == 0)// 此操作占CPU，但是减少内存占用；删除后不影响运行
				linkMap.remove(orgId);
		} else {
			logger.warn("can't find orgIdf while org " + orgId + " leave");
		}
	}

}
