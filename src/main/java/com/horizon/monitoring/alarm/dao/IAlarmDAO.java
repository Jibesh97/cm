package com.horizon.monitoring.alarm.dao;

import java.util.List;

import com.horizon.common.dao.ISDAO;
import com.horizon.monitoring.alarm.vo.AlarmAllInfo;
import com.horizon.monitoring.alarm.vo.AlarmInfo;

public interface IAlarmDAO extends ISDAO<Object>{

//	void addPileFault(Map<String,Object> map);
	
//	void updatePileFault(Map<String,Object> map);
	
//	List<String> selectPileFault(AlarmInfo alarmVO);

	String getStationNum(String pile);

//	void flushRedis(String station, AlarmInfo alarmInfo);

	String getPs(String station, String pile);

	String getMechanism(String pile, String station);

	String getMechanismName(String mechanism);

//	String getStationName(String mechanism, String station);

	int addPileFault(AlarmAllInfo alarmAllInfo);

	void updatePileFault(AlarmAllInfo alarmAllInfo);

	AlarmInfo getAlarmVOBasePile(String pileId);
	
	AlarmInfo getAlarmVOBaseOrder(String orderId);

	int updateAlarmHistory(AlarmAllInfo alarmAllInfo);

	int deleteAlarmFault(AlarmAllInfo alarmAllInfo);

	List<?> getAll(AlarmAllInfo alarmAllInfo);

	void addAlarmHistory(AlarmAllInfo alarmAllInfo);

	List<?> getOne(AlarmAllInfo alarmAllInfo);

}
