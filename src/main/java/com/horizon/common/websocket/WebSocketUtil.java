package com.horizon.common.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.Session;

import org.apache.log4j.Logger;

import com.horizon.common.base.RedisClientDao;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.common.util.JsonUtil;
import com.horizon.common.util.SysContextUtil;

public class WebSocketUtil {
	private static Logger log = Logger.getLogger(SysContextUtil.class);
	private static RedisClientDao redisClient = (RedisClientDao) SysContextUtil
			.getSpringBeanObject("redisClientDao");
	public static OrgLinksBox alarmBox = new OrgLinksBox();
	public static OrgLinksBox mapBox = new OrgLinksBox();
	public static OrgLinksBox stationBox = new OrgLinksBox();
	public static OrgLinksBox pileBox = new OrgLinksBox();

	public static void sendPileMessage(String pileId) {
		//此处使用DAO查出alarmInfo插入
		sendPileMessage(pileId, "redis has update");
	}
	public static void sendPileMessage(String pileId, Object objMessage) {
		String[] orgs = getPileOrgs(pileId);
		if (orgs != null) {
			for (String orgId : orgs) {				
				alarmBox.sendMessage(orgId, objMessage);
			}
		}
	}

	public static void sendPileMessage(String pileId, String strMessage) {
		String[] orgs = getPileOrgs(pileId);
		if (orgs != null) {
			for (String orgId : orgs) {
				alarmBox.sendMessage(orgId, strMessage);
			}
		}
	}

	/**
	 * 根据桩的Id取出相应的要推送的机构
	 * 
	 * @param pileId
	 * @return
	 */
	private static String[] getPileOrgs(String pileId) {
		List<String> orgValue = redisClient.hvals(pileId);
		if (orgValue.size() == 1) {
			String orgStr = orgValue.get(0);
			String[] orgs = orgStr.split("_");
			return orgs;
		} else {
			log.warn("can't find orgs base pileId:" + pileId);
			return null;
		}
	}

	public static Set<String> getReceives(String sonId) {
		Set<String> receivers = new HashSet<String>();
		receivers.add(sonId);
		String fatherId = redisClient.get(sonId + ConstantsInfo.CHILD_SUF);
		while (fatherId != null) {
			receivers.add(fatherId);
			fatherId = redisClient.get(fatherId + ConstantsInfo.CHILD_SUF);
		}
		return receivers;
	}

	/**
	 * 给页面发送string类型的message
	 * 
	 * @param session
	 * @param strMessage
	 * @return
	 */
	public static boolean sendMessage(Session session, String strMessage) {
		try {
			session.getBasicRemote().sendText(strMessage);
			return true;
		} catch (IOException e) {
			log.error(e.getMessage());
			return false;
		}
	}

	/**
	 * 给页面发送
	 * 
	 * @param session
	 * @param objMessage
	 * @return
	 */
	public static boolean sendMessage(Session session, Object objMessage) {
		try {
			session.getBasicRemote().sendText(
					JsonUtil.genJsonString(objMessage));
			return true;
		} catch (IOException e) {
			log.error(e.getMessage());
			return false;
		}
	}
}
