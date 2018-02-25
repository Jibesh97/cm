package com.horizon.monitoring.alarm.websocket;

import java.util.Random;
import java.util.TimerTask;

import javax.websocket.Session;

import org.apache.log4j.Logger;

import com.horizon.common.util.JsonUtil;
import com.horizon.common.util.SysContextUtil;
import com.horizon.common.websocket.WebSocketUtil;
import com.horizon.monitoring.alarm.vo.AlarmInfo;
import com.horizon.monitoring.alarm.vo.AlarmVO;
import com.horizon.monitoring.map.vo.MapResponse;
import com.horizon.monitoring.pile.vo.PileResponse;

public class TestTask extends TimerTask {
	private static Logger logger = Logger.getLogger(TestTask.class);

	@Override
	public void run() {
		try {
//			redisTest();
//			serviceTest();			
//			broadAlarm();
//			broadStation();
//			broadMap();
//			broadPile();
		} catch (Exception e) {
			logger.error("timer task error", e);
		}

	}
	private void serviceTest(){
//		OrgInfoGetter orgInfoGetter = (OrgInfoGetter)SysContextUtil.getSpringBeanObject("orgInfoGetter");
//		logger.info(orgInfoGetter.getOrgs("0", "110000"));
//		logger.info(orgInfoGetter.getOrgs("1", "110000"));
		
//		PileInfoGetter pileInfoGetter = (PileInfoGetter)SysContextUtil.getSpringBeanObject("pileInfoGetter");
//		List<String> chargingPileNum = new ArrayList<String>();
//		chargingPileNum.add("001");
//		chargingPileNum.add("002");
//		logger.info(JsonUtil.genJsonString(pileInfoGetter.searchPileStatus(chargingPileNum, "MONITOR")));
//		logger.info(JsonUtil.genJsonString(pileInfoGetter.searchPileStatus(chargingPileNum, "ALARM")));
	}
	private void redisTest(){
//		logger.info(JsonUtil.genJsonString(RspObjGetter.getStationDetail("O00001","Z00001")));
		logger.info(JsonUtil.genJsonString(RspObjGetter.getPileDetail("O00001", "Z00001","ZZ00001")));
//		String[] orgs = {"O00001"};
//		logger.info(JsonUtil.genJsonString(RspObjGetter.getMapInfo(orgs)));
//		RspObjGetter.test();	
	}
	private void senderTest(){
		WebSocketUtil.sendPileMessage("ZZ00001");
	}
	private void broadPile() {
//		PileResponse statResp = new PileResponse();
//		statResp.init(1);
//		for (Session link : SysContextUtil.pileLinks) {
//			WebSocketUtil.sendMessage(link, statResp);
//		}
	}

	private void broadAlarm() {
		AlarmVO alarmVO = new AlarmVO();
		java.util.List<AlarmInfo> val = new java.util.ArrayList<AlarmInfo>();
		for (int i = 0; i < 2; i++) {
			AlarmInfo infoVO = new AlarmInfo();
			infoVO.init(i+3);
			val.add(infoVO);
		} 
		alarmVO.setVal(val);

		alarmVO.setFaultNumber(getsuiji(10, 30));
		alarmVO.setPayoutNumber(getsuiji(10, 30));// 派发
		alarmVO.setDisposeNumber(getsuiji(10, 30));
		alarmVO.setConcludeNumber(getsuiji(10, 30));
		alarmVO.setAllNumber(getsuiji(60, 150));

		for (String org : WebSocketUtil.alarmBox.getOrgSet())
			WebSocketUtil.alarmBox.sendMessage(org, alarmVO);

	}

	private int getsuiji(int min, int max) {

		Random random = new Random();

		int s = random.nextInt(max - min) + min;
		// System.out.println(s);

		return s;
	}

	private void broadMap() {
//		MapResponse br = new MapResponse();
////		br.init();
//		br.setIsNew(1);
//		// Object br = TestUtil.getOrgInfo();
//		for (Session link : SysContextUtil.mapLinks) {
//			WebSocketUtil.sendMessage(link, br);
//		}
	}

	private void broadStation() {

//		for (Session link : SysContextUtil.stationLinks) {
//			// WebSocketUtil.sendMessage(link, station);
//		}
	}

}
