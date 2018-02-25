package com.horizon.monitoring.alarm.service.impl;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.horizon.common.base.RedisClientDao;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.common.redis.Observer.util.RedisUtli;
import com.horizon.common.redis.model.PileStationMechanism;
import com.horizon.common.util.JsonUtil;
import com.horizon.common.util.SysContextUtil;
import com.horizon.common.util.enums.ACFaultEnum;
import com.horizon.common.util.enums.DCFaultEnum;
import com.horizon.monitoring.alarm.dao.IAlarmDAO;
import com.horizon.monitoring.alarm.service.IAlarmService;
import com.horizon.monitoring.alarm.sql.IAlarmSqlTemplate;
import com.horizon.monitoring.alarm.vo.AlarmAllInfo;
import com.horizon.monitoring.alarm.vo.AlarmInfo;
import com.horizon.monitoring.alarm.vo.AlarmMQInfo;
import com.horizon.monitoring.alarm.vo.AlarmVO;
import com.horizon.monitoring.alarm.vo.OrderObject;
import com.horizon.monitoring.alarm.vo.SendResVO;
import com.send.ss.sendSingle.service.ISendSingleService;
import com.send.ss.sendSingle.service.ISendSingleService;
 

/**
 * 
 * Title:<br>
 * Description: 告警模块服务层接口的实现类<br>
 * Date: 2017-05-05 <br>
 * 
 * @author liwei
 */
@Service("alarmService")
public class AlarmServiceImpl implements IAlarmService {

	private static Logger log = Logger.getLogger(AlarmServiceImpl.class);

	@Autowired
	private IAlarmDAO alarmDAO;
	
	@Autowired
	private RedisClientDao redisClientDAO;

	@Override
	public AlarmVO getAlarmVO(AlarmVO vo) {

		log.info("Start AlarmManagerServiceImpl --> getAlarmVO()");

		try {
			Integer.parseInt(vo.getType());
		} catch (Exception e) {
			vo.setType("");
		}

		vo.setName(vo.getName() + "%");
		// 实时刷新缓存200条数据
		if (vo.getIsRealTime().equals("true")) {
			vo.setStartLimit(0);
			vo.setEndLimit(200);

		} else {
			vo.setStartLimit((vo.getPage() - 1) * 50);
			vo.setEndLimit(vo.getPage() * 50);
		}

		AlarmVO alarmVO = (AlarmVO) alarmDAO.findByPK(vo,
				IAlarmSqlTemplate.ALARM_COUNT, AlarmVO.class);

		List<AlarmInfo> alarmInfo = (List<AlarmInfo>) alarmDAO.findByVO(vo,
				IAlarmSqlTemplate.ALARM_LIST, AlarmInfo.class);

		AlarmVO alarmVO2 = (AlarmVO) alarmDAO.findByPK(vo,
				IAlarmSqlTemplate.ALARM_LIST_COUNT, AlarmVO.class);

		alarmVO.setPageAllNumber(alarmVO2.getPageAllNumber());

		for (int i = 0; i < alarmInfo.size(); i++) {

			if ("1".equals(alarmInfo.get(i).getFaultType())) {
				alarmInfo.get(i).setFaultType("充电桩故障");
			} else if ("2".equals(alarmInfo.get(i).getFaultType())) {
				alarmInfo.get(i).setFaultType("网络故障");
			} else if ("3".equals(alarmInfo.get(i).getFaultType())) {
				alarmInfo.get(i).setFaultType("闪烁告警");
			}

 

			if ("1".equals(alarmInfo.get(i).getIsAffrim())) {
				alarmInfo.get(i).setIsAffrim("是");
			} else {
				alarmInfo.get(i).setIsAffrim("否");
			}

		}

		alarmVO.setVal(alarmInfo);

		return alarmVO;
	}

	@Override
	public AlarmVO getPageAllNumber(AlarmVO vo) {

		vo.setName(vo.getName() + "%");

		try {
			Integer.parseInt(vo.getType());
		} catch (Exception e) {
			vo.setType("");
		}

		AlarmVO alarmVO = (AlarmVO) alarmDAO.findByPK(vo,
				IAlarmSqlTemplate.ALARM_LIST_COUNT, AlarmVO.class);
		return alarmVO;
	}

	@Override
	public AlarmVO updateAllAffirm(AlarmVO vo) {
		AlarmVO alarmVO = new AlarmVO();
		alarmDAO.update(vo, IAlarmSqlTemplate.ALL_AFFIRM);
		alarmVO.setSuccess(true);
		return alarmVO;
	}
	@Override
	public String getPs(String stationNum,String pileNum) { 
		log.info("AlarmServiceImpl-->getPs");
		String pileStat = redisClientDAO.hget(stationNum, pileNum);
		Map<String, Object> pileMap = JSON.parseObject(pileStat);
		String pileIndexNum = pileMap.get("ps").toString();
		return pileIndexNum;
	}
	
	@Override
	public List<?> getOne(AlarmAllInfo alarmAllInfo) { 
		log.info("AlarmServiceImpl-->getOne");
		return alarmDAO.getAll(alarmAllInfo);
	}
	
	@Override
	public List<?> getOnePile(AlarmAllInfo alarmAllInfo) { 
		log.info("AlarmServiceImpl-->getOnePile");
		return alarmDAO.getOne(alarmAllInfo);
	}
	/**
	 * 获取MQ告警信息的VO
	 */
	@Override
	public AlarmAllInfo getAlarmMQVO(AlarmMQInfo alarmMQInfo) {
		log.info("AlarmServiceImpl-->getAlarmMQVO");
		AlarmAllInfo alarmAllInfo = new AlarmAllInfo();
		alarmAllInfo.setFaultType("1");//1:充电桩故障  
		alarmAllInfo.setFaultState(alarmMQInfo.getFaultStatus().toString());
		alarmAllInfo.setPileId(alarmMQInfo.getChargingPileNum());
		String pileNum = alarmMQInfo.getChargingPileNum();
		List<String> list = new ArrayList<String>();
		list.addAll(redisClientDAO.hkeys(pileNum));
		String stationNum = list.get(0);
		
		alarmAllInfo.setStationId(stationNum);

		PileStationMechanism pileStationMechanism = RedisUtli.getMechanismIds(pileNum,stationNum,redisClientDAO);
		String provinceCode = pileStationMechanism.getProvinceCode();
		String cityCode = pileStationMechanism.getCityCode();
		String districtCode = pileStationMechanism.getDistrictCode();
		
		alarmAllInfo.setProvinceId(provinceCode);
		alarmAllInfo.setCityId(cityCode);
		alarmAllInfo.setCountyId(districtCode);
		String provinceName = getMechanismName(provinceCode);
		String cityName = getMechanismName(cityCode);
		String districtName = getMechanismName(districtCode);

		alarmAllInfo.setProvinceName(provinceName);
		alarmAllInfo.setCityName(cityName);
		alarmAllInfo.setCountyName(districtName);

		String stationName = getStationName(provinceCode,stationNum);
		alarmAllInfo.setStationName(stationName);
		
		return alarmAllInfo;
		
	}
	/**
	 * 更新redis中的告警数信息
	 */
	@Override
	public void updateRedisAlarm(AlarmAllInfo alarmAllInfo) {
		log.info("run workOrderStatusService updateRedisComplete");
		List<String> lis = new ArrayList<String>();
		lis.add(alarmAllInfo.getProvinceId());
		lis.add(alarmAllInfo.getCityId());
		lis.add(alarmAllInfo.getCountyId());
		String pileNum = alarmAllInfo.getPileId();
		String stationNum = alarmAllInfo.getStationId();
		for (String mechanismID : lis) {
			String mechanismState = redisClientDAO.get(mechanismID);
			Map<String, Object> mechanismMap = JSON.parseObject(mechanismState);
			mechanismMap.put("alarmNum",Integer.valueOf(mechanismMap.get("alarmNum").toString()).intValue() + 1);
			mechanismState = JSON.toJSONString(mechanismMap);
			redisClientDAO.set(mechanismID, mechanismState);

			// 站状态
			String stationState = redisClientDAO.hget(mechanismID+ "_Z", stationNum);
			Map<String, Object> stationMap = JSON.parseObject(stationState);
			stationMap.put("alarmNum",Integer.valueOf(stationMap.get("alarmNum").toString()).intValue() + 1);
			stationState = JSON.toJSONString(stationMap);
			redisClientDAO.hset(mechanismID + "_Z", stationNum,stationState);
		}

		// 桩状态
		String pileState = redisClientDAO.hget(stationNum, pileNum);

		Map<String, Object> pileMap = JSON.parseObject(pileState);
		pileMap.put("alarmNum",Integer.valueOf(pileMap.get("alarmNum").toString()).intValue() + 1);
		pileState = JSON.toJSONString(pileMap);
		redisClientDAO.hset(stationNum, pileNum, pileState);
	}
	/**
	 * 获取充电站名称
	 */
	@Override
	public String getStationName(String mechanism, String station) {
		log.info("getmechanism");
		String stationState = redisClientDAO.hget(mechanism + "_Z", station);
		Map<String, Object> stationMap = JSON.parseObject(stationState);
		String stationName = stationMap.get("stationName").toString();
		return stationName;
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
	 
	@Override
	public int addPileFault(AlarmAllInfo alarmAllInfo) {
		log.info("AlarmServiceImpl-->addPileFault");
		int ret = alarmDAO.addPileFault(alarmAllInfo);
		log.info(ret);
		return ret;
	}
	
	@Override
	public void updatePileFault(AlarmAllInfo alarmAllInfo) {
		log.info("AlarmServiceImpl-->updatePileFault");
		alarmDAO.updatePileFault(alarmAllInfo);
		
	}
	/**
	 * 插入告警历史信息
	 * @return 
	 */
	@Override
	public int addAlarmHistory(AlarmAllInfo alarmAllInfo) {
		log.info("AlarmServiceImpl-->addAlarmHistory");
		
		alarmDAO.addAlarmHistory(alarmAllInfo);
		return alarmDAO.updateAlarmHistory(alarmAllInfo);
	}
	/**
	 * 删除已修复的告警信息
	 * @return 
	 */
	@Override
	public int deleteAlarmFault(AlarmAllInfo alarmAllInfo) {
		log.info("AlarmServiceImpl-->deleteAlarmFault");
		return alarmDAO.deleteAlarmFault(alarmAllInfo);
		
	}
	/**
	 * 插入闪告
	 */
	@Override
	public void addFlashAlarm(String pileId,String faultMarking,String faultType) {
		log.info("AlarmServiceImpl-->addFlashAlarm");
		RedisClientDao redisClientDao = (RedisClientDao) SysContextUtil
				.getSpringApplicationContext().getBean("redisClientDao");
		AlarmAllInfo alarmAllInfo = new AlarmAllInfo();
		alarmAllInfo.setFaultType(faultType);
//		alarmAllInfo.setFaultState(alarmMQInfo.getFaultStatus().toString());
		alarmAllInfo.setPileId(pileId);
		String pileNum = pileId;
		List<String> list = new ArrayList<String>();
		list.addAll(redisClientDAO.hkeys(pileNum));
		String stationNum = list.get(0);
		
		alarmAllInfo.setStationId(stationNum);

		PileStationMechanism pileStationMechanism = RedisUtli.getMechanismIds(pileNum,stationNum,redisClientDAO);
		String provinceCode = pileStationMechanism.getProvinceCode();
		String cityCode = pileStationMechanism.getCityCode();
		String districtCode = pileStationMechanism.getDistrictCode();
		
		alarmAllInfo.setProvinceId(provinceCode);
		alarmAllInfo.setCityId(cityCode);
		alarmAllInfo.setCountyId(districtCode);
		String provinceName = getMechanismName(provinceCode);
		String cityName = getMechanismName(cityCode);
		String districtName = getMechanismName(districtCode);

		alarmAllInfo.setProvinceName(provinceName);
		alarmAllInfo.setCityName(cityName);
		alarmAllInfo.setCountyName(districtName);

		String stationName = getStationName(provinceCode,stationNum);
		alarmAllInfo.setStationName(stationName);
		
		Date d = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String dateNowStr = sdf.format(d);
		
		String faultDetails = null;
		String pileIndexNum = getPower(stationNum, pileNum);
		if (pileIndexNum.equals(ConstantsInfo.PILE_POWER_SUPPLY_DC)) {
			faultDetails = DCFaultEnum.getName(Integer.parseInt(faultMarking));
		} else {
			faultDetails = ACFaultEnum.getName(Integer.parseInt(faultMarking));
		}
//		alarmAllInfo.setFaultState(a.getValue().toString());// 1:故障
															// 0:故障修复
		alarmAllInfo.setFaultStartTime(d);
		alarmAllInfo.setFaultMarking(faultMarking);
		alarmAllInfo.setFaultDetails(faultDetails);
		int ID = alarmDAO.addPileFault(alarmAllInfo);
		alarmAllInfo.setID(ID);
		
		// websocket 推送到前端
		redisClientDao.publish(ConstantsInfo.CHANNEL_WS_PIPLE, JsonUtil.genJsonString(alarmAllInfo));

	}
	/**
	 * 获取电源信息
	 */
	@Override
	public String getPower(String station, String pile) {
		log.info("getPs");
		String pileStat = redisClientDAO.hget(station, pile);
		Map<String, Object> pileMap = JSON.parseObject(pileStat);
		String ps = pileMap.get("ps").toString();

 
		return ps;
	}
 
	@Override
	public AlarmVO updateWorkOrderPayout(List<OrderObject> list) {
		AlarmVO returnVO=new  AlarmVO();
		List<AlarmInfo> listAlarmInfo=new ArrayList<AlarmInfo>();//最后返回的vo
		

		List<String> idsList=new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			idsList.add(list.get(i).getCharge_station_id()+""); 
			
		}
		 
//验证是否有此故障 
		List<Map<String,Object>>	searchPileStatusList = searchPileStatus(idsList,ConstantsInfo.HSF_ALARM);
  		 
		 
		for (int i = 0; i < searchPileStatusList.size(); i++) {
			 
			String id=	(String) searchPileStatusList.get(i).get("chargingPileNum");
			List<Map<String,String>> indexValue=  (List<Map<String,String>>) searchPileStatusList.get(i).get("indexValue");
			for (int j = 0; j < list.size(); j++) {
			 
				if(id.equals(list.get(j).getCharge_station_id()+"")){
					
					String value= "";
					
					for (int k = 0; k < indexValue.size(); k++) {
						if((list.get(j).getFault_type()+"").equals(  indexValue.get(k).get("indexNum"))){
							value=indexValue.get(k).get("value");
							break;
						} 
					}
					
					
					
					if(!"1".equals(value)){
						list.get(j).setComplete_time((Date) searchPileStatusList.get(i).get("sampTime") );
					 
						
						alarmDAO.update(list.get(j), IAlarmSqlTemplate.UPDATE_FAULT_STATE);
						list.remove(j);
					}  
				}  
			}
			 
		}
		
		
//派单		
//a为接口返回的数据		
		
		
		
		List<OrderObject> a=new ArrayList<OrderObject>();
		try {
			a = putAlarmsToTasks(list);
		} catch (JsonGenerationException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		 
		//以下4个确定1条数据
		//工单状态（WORK_ORDER_STATE）为0 
		//故障状态（FAULT_STATE）为1
		//桩id 
		//故障标识(fault_type)
	  
		
		for (int i = 0; i < a.size(); i++) {
			alarmDAO.update(a.get(i), IAlarmSqlTemplate.UPDATE_WORK_ORDER);
		}
		
		
		
		returnVO.setVal(listAlarmInfo);
		return returnVO;
	}
	
	
	
	//接口
	
	
		private List<OrderObject> putAlarmsToTasks(List<OrderObject> list) throws JsonGenerationException, JsonMappingException, IOException {
			
		 
			ISendSingleService ss= (ISendSingleService) SysContextUtil.getSpringApplicationContext().getBean("sendSingleService");
			 
			
			String json= JSON.toJSONString(list);
			
			
//			  JSONArray array = JSON.parseArray(json);  
//			  List<OrderObject> list2 = JSON.parseArray(array+"", OrderObject.class);  
			 
			
//			String jsonObj=	 ss.addPutAlarmsToTasks(json);
			
//			JSONObject object = JSON.parseObject(jsonObj);  
			
//			SendResVO vo = (SendResVO) JSON.parseObject(jsonObj, SendResVO.class);;//Weibo类在下边定义  
			
//			object.get("listRes")
//			
//			  JSONArray arrayJson = JSON.parseArray(listJson);  
//			  List<JSONObject> ruleList = (List<JSONObject>) vo.getData();  
			  List<OrderObject> ruleList2 = new ArrayList<OrderObject>();  
			  
			  
			  //时间需要特殊处理
//			  for (int i = 0; i < ruleList.size(); i++) { 
//				  OrderObject obj=new OrderObject();
//				  
//				  obj.setReceive_time(new Date(ruleList.get(i).getLongValue("receive_time")));
//				  obj.setOrder_id(ruleList.get(i).getString("order_id"));;
//				  obj.setDistribute_person_id(ruleList.get(i).getIntValue("distribute_person_id")); 
//				  obj.setFault_type(ruleList.get(i).getIntValue("fault_type")); 
//				  obj.setCharge_station_id(ruleList.get(i).getString("charge_station_id"));
//		  
//				  ruleList2.add(obj); 
				
//			}
			  
 
		  
			return ruleList2;
		}
		
		@SuppressWarnings("unchecked")
		public static List<Map<String,Object>> searchPileStatus(List<String> ids, String satusType) {
				 
			List<Map<String,Object>> returnList=new ArrayList<Map<String,Object>>();
			
			List<Map<String,String>> workStatus=new ArrayList<Map<String,String>>();
			
			for (int i = 0; i < 21; i++) {
				
				Map<String, String> map =new HashMap<String, String>();
				
				map.put("indexNum", i+"");
				map.put("value", i%2+"");
				workStatus.add(map);
				
			}

			
			for (int i = 0; i < ids.size(); i++) {
				Map<String,Object>  ret=new HashMap<String, Object>();
				
				ret.put("indexValue", workStatus);
				ret.put("chargingPileNum", ids.get(i));
				ret.put("sampTime", new Date());
				 
				returnList.add(ret);
			}
		 
			 
				 
			return returnList;
		}
			 

}
