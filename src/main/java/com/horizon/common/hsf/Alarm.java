package com.horizon.common.hsf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.horizon.common.base.RedisClientDao;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.common.mq.model.IndexValueVO;
import com.horizon.common.util.JsonUtil;
import com.horizon.common.util.enums.ACFaultEnum;
import com.horizon.common.util.enums.DCFaultEnum;
import com.horizon.monitoring.alarm.service.IAlarmService;
import com.horizon.monitoring.alarm.vo.AlarmAllInfo;
import com.horizon.monitoring.alarm.vo.AlarmMQInfo;
import com.send.pile.service.PileInfoGetter;

public class Alarm {
	private static Logger log = Logger.getLogger(OrgTreeInitData.class);

	public static class InitMap {
		/**
		 * 初始化redis信息
		 */
		public static void init(final ApplicationContext ctx) {
			// 获取redis dao
			RedisClientDao rDao = (RedisClientDao) ctx.getBean("redisClientDao");
			// 桩告警信息接口
			PileInfoGetter pileInfoGetter = (PileInfoGetter) ctx.getBean("pileInfoGetter");

			IAlarmService alarmService = (IAlarmService) ctx.getBean("alarmService");
			String mem = "210000_C";
			try {
				List<String> cityList = rDao.lrange(mem, 0, -1);
				for (String city : cityList) {
					log.info("get city");
					System.out.println(city);
					List<String> countyList = rDao.lrange(city + "_C", 0, -1);
					for (String county : countyList) {
						log.info("get county");
						Set<String> station = rDao.hkeys(county + "_Z");
						List<String> stationList = new ArrayList<String>();
						stationList.addAll(station);
						for (String stationId : stationList) {
							Set<String> pile = rDao.hkeys(stationId);
							List<String> pileList = new ArrayList<String>();
							pileList.addAll(pile);
							log.info("get List<Map<String, String>> allPid");
							List<Map<String, String>> allPid = pileInfoGetter.searchPileStatus(pileList, "ALARM");
							for (Map<String, String> map : allPid) {
//							for (int i = 3; i < allPid.size(); i++) {
								 String pileStatus = map.get("ALARM");
//								String pileStatus = allPid.get(i).get("ALARM");
								AlarmMQInfo info = (AlarmMQInfo) JsonUtil.genObjectFromJsonString(pileStatus,
										AlarmMQInfo.class);

								AlarmAllInfo alarmAllInfo = alarmService.getAlarmMQVO(info);

								String stationNum = alarmAllInfo.getStationId();
								String pileNum = alarmAllInfo.getPileId();
								// 循环获取告警标识是否告警
								for (IndexValueVO a : info.getIndexValue()) {
									if (a.getValue() != 0) {
										// 查询数据库中是否有这条信息
										List<?> onePile = alarmService.getOnePile(alarmAllInfo);
										if (onePile.isEmpty()) {
											String faultDetails = null;
											String pileIndexNum = alarmService.getPs(stationNum, pileNum);
											if (pileIndexNum.equals(ConstantsInfo.PILE_POWER_SUPPLY_DC)) {
												faultDetails = DCFaultEnum.getName(a.getIndexNum());
											} else {
												faultDetails = ACFaultEnum.getName(a.getIndexNum());
											}
											alarmAllInfo.setFaultState(a.getValue().toString());// 1:故障
																								// 0:故障修复
											alarmAllInfo.setFaultStartTime(info.getSampTime());
											alarmAllInfo.setFaultMarking(Integer.toString(a.getIndexNum()));
											alarmAllInfo.setFaultDetails(faultDetails);
											int ID = alarmService.addPileFault(alarmAllInfo);
											alarmAllInfo.setID(ID);
											// 更新告警数
											alarmService.updateRedisAlarm(alarmAllInfo);
											// ④websocket 推送到前端
											rDao.publish(ConstantsInfo.CHANNEL_WS_PIPLE,
													JsonUtil.genJsonString(alarmAllInfo));
										}
									} else {
										// 查询数据库中是否有这条信息
										List<?> onePile = alarmService.getOnePile(alarmAllInfo);
										if (!onePile.isEmpty()) {
											alarmAllInfo.setFaultMarking(Integer.toString(a.getIndexNum()));
											alarmAllInfo.setFaultState(a.getValue().toString());
											// 故障开始与结束时间更新
											alarmAllInfo.setFaultEndTime(info.getSampTime());
											List<?> one = alarmService.getOne(alarmAllInfo);
											AlarmAllInfo history = (AlarmAllInfo) one.get(0);
											int rest = alarmService.addAlarmHistory(history);
											if (rest == 1) {
												int res = alarmService.deleteAlarmFault(alarmAllInfo);
												if (res == 1) {
													log.info("delete sucess");
												} else {
													log.info("delete false");
												}
											} else {
												log.info("addAlarmHistory false");
											}
											// ④websocket 推送到前端
											rDao.publish(ConstantsInfo.CHANNEL_WS_PIPLE,
													JsonUtil.genJsonString(alarmAllInfo));
										}
									}
								}
							}
						}
					}
				}
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
