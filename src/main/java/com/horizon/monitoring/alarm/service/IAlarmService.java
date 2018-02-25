package com.horizon.monitoring.alarm.service;

import java.util.List;

import com.horizon.monitoring.alarm.vo.AlarmAllInfo;
import com.horizon.monitoring.alarm.vo.AlarmMQInfo;
import com.horizon.monitoring.alarm.vo.AlarmVO;
import com.horizon.monitoring.alarm.vo.OrderObject;

 
/**
 * Description:  告警类接口
 * @author liw
 *	Date: 2017-05-05 <br>
 */
public interface IAlarmService {
 
	public AlarmVO getAlarmVO(AlarmVO vo);

	int addPileFault(AlarmAllInfo alarmAllInfo);

	void updatePileFault(AlarmAllInfo alarmAllInfo);

	void addFlashAlarm(String pileId, String faultMarking, String faultType);

	public AlarmVO getPageAllNumber(AlarmVO vo);

	public AlarmVO updateAllAffirm(AlarmVO vo);
 
	String getPs(String stationNum, String pileNum);
 
	public AlarmVO updateWorkOrderPayout(List<OrderObject> list);
 
	String getMechanismName(String mechanism);

	String getStationName(String mechanism, String station);

	String getPower(String station, String pile);

	int addAlarmHistory(AlarmAllInfo alarmAllInfo);

	int deleteAlarmFault(AlarmAllInfo alarmAllInfo);

	List<?> getOne(AlarmAllInfo alarmAllInfo);

	List<?> getOnePile(AlarmAllInfo alarmAllInfo);

	AlarmAllInfo getAlarmMQVO(AlarmMQInfo alarmMQInfo);

	void updateRedisAlarm(AlarmAllInfo alarmAllInfo);
	
 
}
