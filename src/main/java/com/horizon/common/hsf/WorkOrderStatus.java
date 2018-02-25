package com.horizon.common.hsf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.horizon.common.base.RedisClientDao;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.common.util.JsonUtil;
import com.horizon.monitoring.alarm.vo.AlarmInfo;
import com.horizon.monitoring.alarm.vo.AlarmVO;
import com.horizon.monitoring.workorderstatus.service.IWorkOrderStatusService;
import com.horizon.monitoring.workorderstatus.vo.WorkOrderStatusDBInfo;
import com.horizon.monitoring.workorderstatus.vo.WorkOrderStatusSyncVO;
import com.horizon.monitoring.workorderstatus.vo.WorkOrderStatusVO;
import com.send.ss.sendSingle.service.ISendSingleService;

public class WorkOrderStatus {
	private static Logger log = Logger.getLogger(OrgTreeInitData.class);

	public static class InitMap {
		/**
		 * 初始化redis信息
		 */
		public static void init(final ApplicationContext ctx) {
			// 获取redis dao
			RedisClientDao rDao = (RedisClientDao) ctx.getBean("redisClientDao");
			// 桩告警信息接口
			ISendSingleService sendSingleService = (ISendSingleService) ctx.getBean("SendSingleService");

			IWorkOrderStatusService workOrderStatusService = (IWorkOrderStatusService) ctx
					.getBean("workOrderStatusService");
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
							Set<String> pileLis = rDao.hkeys(stationId);
							List<String> pileList = new ArrayList<String>();
							pileList.addAll(pileLis);
							for (String pile : pileList) {
								log.info("get pile");
								String pileStatus = sendSingleService.getTasksByPile(pile);
								WorkOrderStatusSyncVO info = (WorkOrderStatusSyncVO) JsonUtil
										.genObjectFromJsonString(pileStatus, WorkOrderStatusSyncVO.class);
								log.info("get workOrderStatusVO List");
								List<WorkOrderStatusVO> workOrderStatusVOList = new ArrayList<WorkOrderStatusVO>();
								workOrderStatusVOList = info.getResult();
								for (WorkOrderStatusVO workOrderStatusVO : workOrderStatusVOList) {
									log.info("get workOrderStatusVO");
									// 获取这个桩编号的mysql中的一整条信息
									List<?> worklis = workOrderStatusService.getAll(workOrderStatusVO);
									if (worklis.isEmpty()) {
										worklis = workOrderStatusService.getAllHistory(workOrderStatusVO);
									}
									int res = 0;
									WorkOrderStatusDBInfo workOrderStatusDBInfo = (WorkOrderStatusDBInfo) worklis.get(0);
									if (!workOrderStatusDBInfo.getWorkOrderState().equals(workOrderStatusVO.getStatus())) {
										// 更新数据库
										if (workOrderStatusVO.getStatus().equals("9")) {
											// info.setStatus(info.getStatus());
											res = workOrderStatusService.updateWorkOrderStatusOver(workOrderStatusVO);
											if (res == 0) {
												res = workOrderStatusService
														.updateWorkOrderStatusOverHistory(workOrderStatusVO);
											}
										} else {
											// info.setStatus(info.getStatus());
											res = workOrderStatusService.updateWorkOrderStatus(workOrderStatusVO);
											if (res == 0) {
												res = workOrderStatusService
														.updateWorkOrderStatusHistory(workOrderStatusVO);
											}
										}
										log.info("query update workOrder result : " + res);
										// 更新redis
										if (workOrderStatusVO.getStatus().equals("9")) {
											log.info("completedNum+1  handleNum-1");
											workOrderStatusService.updateRedisComplete(workOrderStatusVO);
										}
										// 更新派发数和告警数
										if (workOrderStatusVO.getStatus().equals("2")) {
											log.info("payNum+1  alarmNum-1");
											workOrderStatusService.updateRedisPay(workOrderStatusVO);
										}
										// 更新处理和派发数
										if (workOrderStatusVO.getStatus().equals("3")) {
											log.info("handleNum+1  payNum-1");
											workOrderStatusService.updateRedisWork(workOrderStatusVO);
										}

										log.info("set workOrder VO");
										AlarmVO alarmVO = new AlarmVO();
										AlarmInfo alarmInfo = new AlarmInfo();
										// 获取这个桩编号的mysql中的一整条信息
										List<?> orderStatus = workOrderStatusService.getAll(workOrderStatusVO);
										if (orderStatus.isEmpty()) {
											orderStatus = workOrderStatusService.getAllHistory(workOrderStatusVO);
										}
										WorkOrderStatusDBInfo workOrderStatusDB = (WorkOrderStatusDBInfo) orderStatus.get(0);

										alarmInfo.setOrderState(workOrderStatusVO.getStatus());// 工单状态
										alarmInfo.setOrderId(workOrderStatusVO.getOrderId());// 工单编号
										alarmInfo.setFaultEndTime(workOrderStatusDB.getFaultEndTime());// 故障结束时间
										alarmInfo.setOrderEndTime(workOrderStatusDB.getWorkOrderEndTime());// 工单结束时间

										List<AlarmInfo> lisAlarmInfo = new ArrayList<AlarmInfo>();
										lisAlarmInfo.add(alarmInfo);
										alarmVO.setVal(lisAlarmInfo);

										// websocket 推送更新
										log.info("workOrder websocket");
										rDao.publish(ConstantsInfo.CHANNEL_WS_PIPLE, JsonUtil.genJsonString(alarmVO));
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
