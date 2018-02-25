package com.horizon.monitoring.alarm.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.horizon.common.base.BaseDao;
import com.horizon.common.base.RedisClientDao; 
import com.horizon.monitoring.alarm.dao.IAlarmDAO;
import com.horizon.monitoring.alarm.sql.IAlarmSqlTemplate;
import com.horizon.monitoring.alarm.vo.AlarmAllInfo;
import com.horizon.monitoring.alarm.vo.AlarmInfo;

@Component("alarmDAO")
public class AlarmDAOImpl extends BaseDao implements IAlarmDAO {
	private static Logger log = Logger
			.getLogger(AlarmDAOImpl.class);

	@Autowired
	private RedisClientDao redisClientDAO;

	public void setRedisClientDAO(RedisClientDao redisClientDAO) {
		this.redisClientDAO = redisClientDAO;
	}

	@Override
	public AlarmInfo getAlarmVOBasePile(String pileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlarmInfo getAlarmVOBaseOrder(String orderId) {
		AlarmInfo alarmInfo = new AlarmInfo();
		alarmInfo.setOrderId(orderId);
		alarmInfo =  (AlarmInfo) super.findByPK(alarmInfo, IAlarmSqlTemplate.RESP_ALARM_INFO,AlarmInfo.class);
		return alarmInfo;
//		List<AlarmInfo> alarmInfo=  (List<AlarmInfo>) alarmDAO.findByVO(vo,
//				IAlarmSqlTemplate.ALARM_LIST, 
//				AlarmInfo.class);
//		return null;
	}

	/**
	 * 根据桩编号和故障类型看是否在数据库中
	 */
	/*
	 * @Override public List<String> selectPileFault(AlarmInfo alarmVO) {
	 * log.info("selectPileFault"); List<String> id = new ArrayList<String>();
	 * try { id = (List<String>)super.findByVO(alarmVO,
	 * IAlarmSqlTemplate.SELECT_PILE_ID); } catch (Exception e) { log.info(e); }
	 * 
	 * return id; }
	 */
	/**
	 * 更新数据库信息
	 */
	@Override
	public void updatePileFault(AlarmAllInfo alarmAllInfo) {
		log.info("updatePileFault");
		super.update(alarmAllInfo,IAlarmSqlTemplate.UPDATA_PILE_ID);
	}

	/**
	 * 往数据库中插入信息
	 */
	@Override
	public int addPileFault(AlarmAllInfo alarmAllInfo) {

		log.info("addPileFault");
		return save(alarmAllInfo, "CM_FAULT_WORK_ORDER", "ID").intValue();
	}
	/**
	 * 插入告警历史信息
	 */
	@Override
	public void addAlarmHistory(AlarmAllInfo alarmAllInfo) {
		log.info("addAlarmHistory");
		super.save(alarmAllInfo, "CM_FAULT_WORK_ORDER_HISTORY", "ID").intValue();
	}
	/**
	 * 获取一条信息
	 */
	@Override
	public List<?> getAll(AlarmAllInfo alarmAllInfo) {
		log.info("getAll");
		List<?> lis = super.findByVO(alarmAllInfo,IAlarmSqlTemplate.SELECT_STATION_PILE,AlarmAllInfo.class);
		return lis;
	}
	/**
	 * 获取一条信息
	 */
	@Override
	public List<?> getOne(AlarmAllInfo alarmAllInfo) {
		log.info("getOne");
		List<?> lis = super.findByVO(alarmAllInfo,IAlarmSqlTemplate.SELECT_PILE_ONE,AlarmAllInfo.class);
		return lis;
	}
	/**
	 * 更新告警历史信息
	 * @return 
	 */
	@Override
	public int updateAlarmHistory(AlarmAllInfo alarmAllInfo) {
		log.info("updateAlarmHistory");
		return super.update(alarmAllInfo,IAlarmSqlTemplate.UPDATE_FAULT_STATE_HISTORY);
	}
	/**
	 * 更新告警历史信息
	 * @return 
	 */
	@Override
	public int deleteAlarmFault(AlarmAllInfo alarmAllInfo) {
		log.info("deleteAlarmFault");
		return super.update(alarmAllInfo,IAlarmSqlTemplate.DELETE_FAULT_STATE_HISTORY);
	}
	/**
	 * 获取站编号
	 */
	@Override
	public String getStationNum(String pile) {
		log.info("getStationNum");
		log.info(redisClientDAO.hkeys(pile));
		List<String> list = new ArrayList<String>();
		list.addAll(redisClientDAO.hkeys(pile));
		return list.get(0);
	}

	/**
	 * 获取机构信息
	 */
	@Override
	public String getMechanism(String pile, String station) {
		log.info("getmechanism");
		String mechanism = redisClientDAO.hget(pile, station);
		return mechanism;
	}

	/**
	 * 获取机构名称
	 */
	@Override
	public String getMechanismName(String mechanism) {
		log.info("getmechanism");
		String mechanismState = redisClientDAO.get(mechanism);
		Map<String, Object> mechanismMap = JSON.parseObject(mechanismState);
		String mechanismName = mechanismMap.get("mechanismName").toString();
		return mechanismName;
	}

	/**
	 * 获取充电站名称
	 */
//	@Override
//	public String getStationName(String mechanism, String station) {
//		log.info("getmechanism");
//		String stationState = redisClientDAO.hget(mechanism + "_Z", station);
//		Map<String, Object> stationMap = JSON.parseObject(stationState);
//		String stationName = stationMap.get("stationName").toString();
//		return stationName;
//	}

	/**
	 * 获取电源信息
	 */
	@Override
	public String getPs(String station, String pile) {
		log.info("getPs");
		String pileStat = redisClientDAO.hget(station, pile);
		Map<String, Object> pileMap = JSON.parseObject(pileStat);
		String ps = pileMap.get("ps").toString();

		return ps;
	}

}
