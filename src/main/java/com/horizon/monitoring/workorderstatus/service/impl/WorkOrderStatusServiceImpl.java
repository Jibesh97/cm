package com.horizon.monitoring.workorderstatus.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.horizon.common.base.RedisClientDao;
import com.horizon.common.redis.Observer.util.RedisUtli;
import com.horizon.common.redis.model.PileStationMechanism;
import com.horizon.monitoring.alarm.service.impl.AlarmServiceImpl;
import com.horizon.monitoring.workorderstatus.dao.IWorkOrderStatusDAO;
import com.horizon.monitoring.workorderstatus.service.IWorkOrderStatusService;
import com.horizon.monitoring.workorderstatus.vo.WorkOrderStatusVO;

@Service("workOrderStatusService")
public class WorkOrderStatusServiceImpl implements IWorkOrderStatusService {

	private static Logger log = Logger.getLogger(AlarmServiceImpl.class);

	@Autowired
	private IWorkOrderStatusDAO workOrderStatusDAO;

	@Autowired
	private RedisClientDao redisClientDAO;

	
	/**
	 * 获取所有信息
	 */
	@Override
	public List<?> getAll(WorkOrderStatusVO workOrderStatusVO) {
		log.info("run workOrderStatusService getAll");
		List<?> lis = workOrderStatusDAO.getAll(workOrderStatusVO);
		return lis;
	}
	/**
	 * 获取所有信息
	 */
	@Override
	public List<?> getAllHistory(WorkOrderStatusVO workOrderStatusVO) {
		log.info("run workOrderStatusService getAllHistory");
		List<?> lis = workOrderStatusDAO.getAllHistory(workOrderStatusVO);
		return lis;
	}
	/**
	 * 更新数据库信息
	 * @return 
	 */
	@Override
	public int updateWorkOrderStatusOver(WorkOrderStatusVO workOrderStatusVO) {
		log.info("run workOrderStatusService updateWorkOrderStatus");
		int res = workOrderStatusDAO.updateWorkOrderStatusOver(workOrderStatusVO);
		return res;
	}
	/**
	 * 更新数据库历史告警工单信息
	 * @return 
	 */
	@Override
	public int updateWorkOrderStatusOverHistory(WorkOrderStatusVO workOrderStatusVO) {
		log.info("run workOrderStatusService updateWorkOrderStatusOverHistory");
		int res = workOrderStatusDAO.updateWorkOrderStatusOverHistory(workOrderStatusVO);
		return res;
	}
	/**
	 * 更新数据库信息
	 * @return 
	 */
	@Override
	public int updateWorkOrderStatus(WorkOrderStatusVO workOrderStatusVO) {
		log.info("run workOrderStatusService updateWorkOrderStatus");
		
		int res = workOrderStatusDAO.updateWorkOrderStatus(workOrderStatusVO);
		return res;
	}
	/**
	 * 更新数据库历史告警工单信息
	 * @return 
	 */
	@Override
	public int updateWorkOrderStatusHistory(WorkOrderStatusVO workOrderStatusVO) {
		log.info("run workOrderStatusService updateWorkOrderStatusHistory");
		int res = workOrderStatusDAO.updateWorkOrderStatusHistory(workOrderStatusVO);
		return res;
	}
	/**
	 * 更新redis信息
	 * 
	 * @return
	 */
	@Override
	public void updateRedisComplete(WorkOrderStatusVO workOrderStatusVO) {
		log.info("run workOrderStatusService updateRedisComplete");
		String pileID = workOrderStatusVO.getChargeStationId();// 桩ID

		String stationID = RedisUtli.getStationId(pileID, redisClientDAO);// 站ID
		// 获取桩所属机构
		List<String> lis = new ArrayList<String>();
		PileStationMechanism pileStationMechanism = RedisUtli.getMechanismIds(pileID, stationID, redisClientDAO);
		lis.add(pileStationMechanism.getProvinceCode());
		lis.add(pileStationMechanism.getCityCode());
		lis.add(pileStationMechanism.getDistrictCode());
		// 更新redis中的工单办结数和故障数
		log.info("completedNum+1  handleNum-1");
		for (String mechanism : lis) {
			String mechanismState = redisClientDAO.get(mechanism);
			Map<String, Object> mechanismMap = JSON.parseObject(mechanismState);
			mechanismMap.put("completedNum",
					Integer.valueOf(mechanismMap.get("completedNum").toString()).intValue() + 1);
			mechanismMap.put("handleNum", Integer.valueOf(mechanismMap.get("handleNum").toString()).intValue() - 1);
			mechanismState = JSON.toJSONString(mechanismMap);
			redisClientDAO.set(mechanism, mechanismState);
			// 站状态
			String stationState = redisClientDAO.hget(mechanism + "_Z", stationID);
			Map<String, Object> stationMap = JSON.parseObject(stationState);
			stationMap.put("completedNum", Integer.valueOf(stationMap.get("completedNum").toString()).intValue() + 1);
			stationMap.put("handleNum", Integer.valueOf(stationMap.get("handleNum").toString()).intValue() - 1);
			stationState = JSON.toJSONString(stationMap);
			redisClientDAO.hset(mechanism + "_Z", stationID, stationState);
		}
		// 桩状态
		String pileState = redisClientDAO.hget(stationID, pileID);

		Map<String, Object> pileMap = JSON.parseObject(pileState);
		pileMap.put("completedNum", Integer.valueOf(pileMap.get("completedNum").toString()).intValue() + 1);
		pileMap.put("handleNum", Integer.valueOf(pileMap.get("handleNum").toString()).intValue() - 1);
		pileState = JSON.toJSONString(pileMap);
		redisClientDAO.hset(stationID, pileID, pileState);
	}

	/**
	 * 更新redis信息
	 * 
	 * @return
	 */
	@Override
	public void updateRedisPay(WorkOrderStatusVO workOrderStatusVO) {
		log.info("run workOrderStatusService updateRedisPay");
		String pileID = workOrderStatusVO.getChargeStationId();// 桩ID

		String stationID = RedisUtli.getStationId(workOrderStatusVO.getChargeStationId(), redisClientDAO);// 站ID
		// 获取桩所属机构
		List<String> lis = new ArrayList<String>();
		PileStationMechanism pileStationMechanism = RedisUtli.getMechanismIds(pileID, stationID, redisClientDAO);
		lis.add(pileStationMechanism.getProvinceCode());
		lis.add(pileStationMechanism.getCityCode());
		lis.add(pileStationMechanism.getDistrictCode());
		// 更新派发数和告警数
		log.info("payNum+1  alarmNum-1");
		for (String mechanism : lis) {
			String mechanismState = redisClientDAO.get(mechanism);
			Map<String, Object> mechanismMap = JSON.parseObject(mechanismState);
			mechanismMap.put("payNum", Integer.valueOf(mechanismMap.get("payNum").toString()).intValue() + 1);
			mechanismMap.put("alarmNum", Integer.valueOf(mechanismMap.get("alarmNum").toString()).intValue() - 1);
			mechanismState = JSON.toJSONString(mechanismMap);
			redisClientDAO.set(mechanism, mechanismState);
			// 站状态
			String stationState = redisClientDAO.hget(mechanism + "_Z", stationID);
			Map<String, Object> stationMap = JSON.parseObject(stationState);
			stationMap.put("payNum", Integer.valueOf(stationMap.get("payNum").toString()).intValue() + 1);
			stationMap.put("alarmNum", Integer.valueOf(stationMap.get("alarmNum").toString()).intValue() - 1);
			stationState = JSON.toJSONString(stationMap);
			redisClientDAO.hset(mechanism + "_Z", stationID, stationState);
		}
		// 桩状态
		String pileState = redisClientDAO.hget(stationID, pileID);
		Map<String, Object> pileMap = JSON.parseObject(pileState);
		pileMap.put("payNum", Integer.valueOf(pileMap.get("payNum").toString()).intValue() + 1);
		pileMap.put("alarmNum", Integer.valueOf(pileMap.get("alarmNum").toString()).intValue() - 1);
		pileState = JSON.toJSONString(pileMap);
		redisClientDAO.hset(stationID, pileID, pileState);
	}
	
	/**
	 * 更新redis信息
	 * 
	 * @return
	 */
	@Override
	public void updateRedisWork(WorkOrderStatusVO workOrderStatusVO) {
		log.info("run workOrderStatusService updateRedisWork");
		String pileID = workOrderStatusVO.getChargeStationId();// 桩ID

		String stationID = RedisUtli.getStationId(workOrderStatusVO.getChargeStationId(), redisClientDAO);// 站ID
		// 获取桩所属机构
		List<String> lis = new ArrayList<String>();
		PileStationMechanism pileStationMechanism = RedisUtli.getMechanismIds(pileID, stationID, redisClientDAO);
		lis.add(pileStationMechanism.getProvinceCode());
		lis.add(pileStationMechanism.getCityCode());
		lis.add(pileStationMechanism.getDistrictCode());
		//更新处理和派发数
		log.info("handleNum+1  payNum-1");
		for(String mechanism : lis){
			String mechanismState = redisClientDAO.get(mechanism);
			Map<String,Object> mechanismMap = JSON.parseObject(mechanismState);
			mechanismMap.put("payNum",Integer.valueOf(mechanismMap.get("payNum").toString()).intValue()-1);
			mechanismMap.put("handleNum",Integer.valueOf(mechanismMap.get("handleNum").toString()).intValue()+1);
			mechanismState = JSON.toJSONString(mechanismMap);
			redisClientDAO.set(mechanism,mechanismState);
			//站状态
			String stationState = redisClientDAO.hget(mechanism + "_Z", stationID);
			Map<String,Object> stationMap = JSON.parseObject(stationState);
			stationMap.put("payNum",Integer.valueOf(stationMap.get("payNum").toString()).intValue()-1);
			stationMap.put("handleNum",Integer.valueOf(stationMap.get("handleNum").toString()).intValue()+1);
			stationState = JSON.toJSONString(stationMap);
			redisClientDAO.hset(mechanism + "_Z",stationID,stationState);
		}
		//桩状态
		String pileState = redisClientDAO.hget(stationID, pileID);
		
		Map<String,Object> pileMap = JSON.parseObject(pileState);
		pileMap.put("payNum",Integer.valueOf(pileMap.get("payNum").toString()).intValue()-1);
		pileMap.put("handleNum",Integer.valueOf(pileMap.get("handleNum").toString()).intValue()+1);
		pileState = JSON.toJSONString(pileMap);
		redisClientDAO.hset(stationID,pileID,pileState);
	}

}
