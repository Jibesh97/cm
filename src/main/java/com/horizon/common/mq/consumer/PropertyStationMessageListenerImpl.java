package com.horizon.common.mq.consumer;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.horizon.common.base.RedisClientDao;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.common.mq.model.PropStation;
import com.horizon.common.redis.model.MechanismStatus;
import com.horizon.common.redis.model.StationStatus;
import com.horizon.common.util.JsonUtil;
import com.horizon.common.util.SysContextUtil;
import com.horizon.common.vo.StationVO;
import com.send.edas.property.service.ISendStationService;

public class PropertyStationMessageListenerImpl implements MessageListener {
	
	private static Logger log = Logger.getLogger(PropertyStationMessageListenerImpl.class);
	
	public Action consume(Message message, ConsumeContext consumeContext) {
		
		log.debug(new Date() + " 资产--站信息变更~~~~~~~~~~~~ ");
		log.debug(new Date() + " Receive PropertyStationMessage, Topic is:" + message.getTopic() + ", MsgId is:" + message.getMsgID());
		
		byte[] body = message.getBody();
		String json = new String(body) ;
		
		log.debug(new Date() + " 监听信息如下： " + json);
		
		
			PropStation station;
			try {
				station = (PropStation) JsonUtil.genObjectFromJsonString(json, PropStation.class);
			
			//获取变更类型
			String type  = station.getType();//CREATE/UPDATE/DELETE 创建 / 更新 / 删除
			
			if("CREATE".equals(type)){
				log.debug(new Date() + " 增加--站信息 " );
				addStationStatusRed(station.getUuid(), SysContextUtil
						.getSpringApplicationContext());
			}
			if ("UPDATE".equals(type)) {
				log.debug(new Date() + " 更新--站信息 " );
				updateStationStatusRed(station.getUuid(), SysContextUtil
						.getSpringApplicationContext());
				
			}
			if ("DELETE".equals(type)) {
				log.debug(new Date() + " 删除--站信息 ");
				deleteStationStatusRed(station.getUuid(), SysContextUtil
						.getSpringApplicationContext());
			}
			
			
			//更新redis信息
			
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		
		
		
		//如果想测试消息重投的功能,可以将Action.CommitMessage 替换成Action.ReconsumeLater
        return Action.CommitMessage;
	}
	
	
	
	/**
	 * 新增redis里站的状态
	 * @param 站的uuid
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public  void addStationStatusRed (String stationId ,final ApplicationContext ctx) {
		
		log.info(new Date() + " 新增一个站 ！"+ " 站id：" + stationId);
		
		log.info(new Date() + " 引入获取一个站接口 ");
		//???;
		//ISendStationService stationservice = ( ISendStationService ) ctx.getBean( "StationGet" );
		//RedisClientDao redisClientDao = (RedisClientDao) SysContextUtil.getSpringApplicationContext().getBean("redisClientDao");
		
		//根据给出的站id，获取到新增站的信息，
		//String json = stationservice.getStation(stationId, "", "");
		//String json = "{\"errorCode\":\"0\",\"errorMsg\":\"xxxx\",\"data\": \"{\"station\": \"{\"uuid\":\"st_test_0001\",\"name\": \"测试站\",\"org\": \"机构uuid\",\"trade\":\"商户uuid\",\"provinceCode\":\"110000\",\"cityCode\":\"110100\",\"districtCode\":\"110101\",\"address\": \"北京市东城区市辖区测试区\",\"openAt\":\"开始时间\",\"closeAt\": \"结束时间\"}\"}\"}";
		String json2 = "{\"uuid\":\"st_test_0001\",\"name\": \"测试站\",\"org\": \"机构uuid\",\"trade\":\"商户uuid\",\"provinceCode\":\"110000\",\"cityCode\":\"110100\",\"districtCode\":\"110101\",\"address\": \"北京市东城区市辖区测试区\",\"openAt\":\"开始时间\",\"closeAt\": \"结束时间\"}";
		log.info(new Date() + " 获取到站的信息： " + json2);
		
		//转化成vo
		//GetStationVO station = (GetStationVO) JsonUtil.genObjectFromJsonString(json, GetStationVO.class);
		//调用接口查到的站的信息
		StationVO result = new StationVO();	//(StationVO) JsonUtil.genObjectFromJsonString(json2,StationVO.class);
		
		//传回redis存储信息
		StationStatus stationSta = new StationStatus();
		
		// 充电站名称
		stationSta.setStationName(result.getName());
		// 站状态 0 正常
		//stationSta.setAlarmState(PileConstantsInfo.STATION_FAULT_STATUS_0);
		// 站坐标
		stationSta.setLat(result.getLat());
		stationSta.setLng(result.getLng());
		
		//新增省-站-状态信息
		setOrgStationStatus(result.getProvinceCode(), stationId, stationSta);
		log.debug(new Date() + " 新增省-站-状态 "  + " 省id: " +result.getProvinceCode() + " 站id：" + stationId + " 状态信息: " + JsonUtil.genJsonString(stationSta) );
		//新增市-站-状态信息
		setOrgStationStatus(result.getCityCode(), stationId, stationSta);
		log.debug(new Date() + " 新增市-站-状态 "  + " 市id: " +result.getCityCode() + " 站id：" + stationId + " 状态信息: " + JsonUtil.genJsonString(stationSta) );
		//新增区县-站-状态信息
		setOrgStationStatus(result.getDistrictCode(), stationId, stationSta);
		log.debug(new Date() + " 新增区县-站-状态 "  + " 区县id: " +result.getDistrictCode() + " 站id：" + stationId + " 状态信息: " + JsonUtil.genJsonString(stationSta) );
		
		
		//省-状态	+1
		//获取redis中省-状态信息
		MechanismStatus lastProMS = getOrgStatus(result.getProvinceCode());
		MechanismStatus newProMS = lastProMS;
		newProMS.setTsn(lastProMS.getTsn()+1);
		//更新到redis
		setOrgStatus(result.getProvinceCode(), newProMS);
		log.info(new Date() + " 省-状态---站总数+1：" + " 省id："+ result.getProvinceCode() );
		
		//市-状态	+1
		//获取redis中市-状态信息
		MechanismStatus lastCitMS = getOrgStatus(result.getCityCode());
		MechanismStatus newCitMS = lastCitMS;
		newCitMS.setTsn(lastCitMS.getTsn()+1);
		//更新到redis
		setOrgStatus(result.getCityCode(), newCitMS);
		log.info(new Date() + " 市-状态---站总数+1：" + " 市id："+ result.getCityCode() );
		
		//区县 -状态 +1
		//获取redis中区县-状态信息
		MechanismStatus lastAreMS = getOrgStatus(result.getDistrictCode());
		MechanismStatus newAreMS = lastAreMS;
		newAreMS.setTsn(lastAreMS.getTsn()+1);
		//更新到redis
		setOrgStatus(result.getDistrictCode(), newAreMS);
		log.info(new Date() + " 区县-站---站总数+1：" + " 区县id："+ result.getDistrictCode() );
	}
	
	/**
	 * 更新redis里站的状态
	 * @param 站的uuid
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public  void updateStationStatusRed(String stationId ,final ApplicationContext ctx) {
		log.debug(new Date() + " 更新站： " + stationId );
		
		//RedisClientDao redisClientDao = (RedisClientDao) SysContextUtil.getSpringApplicationContext().getBean("redisClientDao");
		log.info(new Date() + " 引入获取一个站接口 ");
		//？？？;
		//ISendStationService stationservice = ( ISendStationService ) ctx.getBean( "StationGet" );
		
		//根据给出的站id，获取到要更新的站的信息，
		//String json = stationservice.getStation(stationId, "", "");
		String json = "{\"errorCode\":\"0\",\"errorMsg\":\"xxxx\",\"data\": {\"station\": {\"uuid\":\"st_test_0001\",\"name\": \"测试站\",\"orgUuid\": \"机构uuid\",\"tradeUuid\":\"商户uuid\",\"provinceCode\":\"110000\",\"cityCode\":\"110100\",\"districtCode\":\"110101\",\"address\": \"北京市东城区市辖区测试区\",\"openAt\":\"开始时间\",\"closeAt\": \"结束时间\"}}}";
		String json2 = "{\"uuid\":\"st_test_0001\",\"name\": \"修改测试站\",\"org\": \"机构uuid\",\"trade\":\"商户uuid\",\"provinceCode\":\"110000\",\"cityCode\":\"110100\",\"districtCode\":\"110101\",\"address\": \"北京市东城区市辖区测试区\",\"openAt\":\"开始时间\",\"closeAt\": \"结束时间\"}";

		log.info(new Date() + " 获取到站的信息： " + json2);
		
		
		//转化成vo
		//GetStationVO station = (GetStationVO) JsonUtil.genObjectFromJsonString(json, GetStationVO.class);
		//调用接口查到的站的信息
		StationVO result = new StationVO() ; //(StationVO) JsonUtil.genObjectFromJsonString(json2,StationVO.class);
		//StationStatus getStation = getOrgStationStatus(result.getProvinceCode(), stationId);
		if (StringUtils.isNoneBlank(result.getUuid())) {
			
		
		//从redis获取到的原来的省-站-状态信息
		StationStatus lastProStatus = getOrgStationStatus(result.getProvinceCode(), stationId);
		//更新省-站-状态信息
		StationStatus newProStatus = lastProStatus;
		//更新站名称
		newProStatus.setStationName(result.getName());
		//更新站纬度
		newProStatus.setLat(result.getLat());
		//更新站经度
		newProStatus.setLat(result.getLng());
		//将更新后的信息存到redis
		setOrgStationStatus(result.getProvinceCode(), stationId, newProStatus);
		log.info(new Date() + " 更新：省-站---状态信息： " + JsonUtil.genJsonString(newProStatus));
		
		//从redis获取到的原来的市-站-状态信息
		StationStatus lastCitStatus = getOrgStationStatus(result.getCityCode(), stationId);
		//更新市-站-状态信息
		StationStatus newCitStatus = lastCitStatus;
		//更新站名称
		newCitStatus.setStationName(result.getName());
		//更新站纬度
		newCitStatus.setLat(result.getLat());
		//更新站经度
		newCitStatus.setLat(result.getLng());
		//将更新后的信息存到redis
		setOrgStationStatus(result.getCityCode(), stationId, newCitStatus);
		log.info(new Date() + " 更新：市-站---状态信息： " + JsonUtil.genJsonString(newCitStatus));

		//从redis获取到的原来的区县-站-状态信息
		StationStatus lastAreStatus = getOrgStationStatus(result.getDistrictCode(), stationId);
		//更新区县-站-状态信息
		StationStatus newAreStatus = lastAreStatus;
		//更新站名称
		newAreStatus.setStationName(result.getName());
		//更新站纬度
		newAreStatus.setLat(result.getLat());
		//更新站经度
		newAreStatus.setLat(result.getLng());
		//将更新后的信息存到redis
		setOrgStationStatus(result.getDistrictCode(), stationId, newAreStatus);
		log.info(new Date() + " 更新：区县-站---状态信息： " + JsonUtil.genJsonString(newAreStatus));
		}
	}
	
	/**
	 * 删除redis里站的状态
	 * @param 站的uuid
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public  void deleteStationStatusRed(String stationId ,final ApplicationContext ctx) {
		log.debug(new Date() + " 删除站： " + stationId );
		
		RedisClientDao redisClientDao = (RedisClientDao) SysContextUtil.getSpringApplicationContext().getBean("redisClientDao");
		log.info(new Date() + " 引入获取一个站接口 ");
		//？？？;
		//ISendStationService stationservice = ( ISendStationService ) ctx.getBean( "StationGet" );
		
		//根据给出的站id，获取到要删除的站的信息，
		//String json = stationservice.getStation(stationId, "", "");
		String json = "{\"errorCode\":\"0\",\"errorMsg\":\"xxxx\",\"data\": {\"station\": {\"uuid\":\"st_test_0001\",\"name\": \"测试站\",\"orgUuid\": \"机构uuid\",\"tradeUuid\":\"商户uuid\",\"provinceCode\":\"110000\",\"cityCode\":\"110100\",\"districtCode\":\"110101\",\"address\": \"北京市东城区市辖区测试区\",\"openAt\":\"开始时间\",\"closeAt\": \"结束时间\"}}}";
		String json2 = "{\"uuid\":\"st_test_0001\",\"name\": \"测试站\",\"org\": \"机构uuid\",\"trade\":\"商户uuid\",\"provinceCode\":\"110000\",\"cityCode\":\"110100\",\"districtCode\":\"110101\",\"address\": \"北京市东城区市辖区测试区\",\"openAt\":\"开始时间\",\"closeAt\": \"结束时间\"}";
		
		log.info(new Date() + " 获取到要删除站的信息： " + json);
		
		//删除站-桩--状态的信息
		redisClientDao.del(stationId);
		log.info(new Date() + " 删除站-桩--状态的信息 成功" + " 站ID:" + stationId);
		
		
		//转化成vo
//		GetStationVO station = (GetStationVO) JsonUtil.genObjectFromJsonString(json, GetStationVO.class);
		//调用接口查到的站的信息
		StationVO result = new StationVO() ; 	//(StationVO) JsonUtil.genObjectFromJsonString(json2,StationVO.class);
		
		
		//获取省--站--原来告警状态-----------------------------------------
		//从redis获取到的原来的省-站-状态信息
		StationStatus lastProStatus = getOrgStationStatus(result.getProvinceCode(), stationId);
		int lastProAlaSta = lastProStatus.getAlarmState();
		//判断省--站--原来告警状态，修改省-xx状态-1
		if (lastProAlaSta==1) {
			//获取redis中省-状态信息
			MechanismStatus lastProMSAl = getOrgStatus(result.getProvinceCode());
			MechanismStatus newProMSAl = lastProMSAl;
			newProMSAl.setGa(lastProMSAl.getGa()-1);
			setOrgStatus(result.getProvinceCode(), newProMSAl);
			
		}
		if (lastProAlaSta==2) {
			//获取redis中省-状态信息
			MechanismStatus lastProMSAl = getOrgStatus(result.getProvinceCode());
			MechanismStatus newProMSAl = lastProMSAl;
			newProMSAl.setCa(lastProMSAl.getCa()-1);
			setOrgStatus(result.getProvinceCode(), newProMSAl);
			
		}
		//获取市--站--原来告警状态-----------------------------------------
		//从redis获取到的原来的市-站-状态信息
		StationStatus lastCitStatus = getOrgStationStatus(result.getCityCode(), stationId);
		int lastCitAlaSta = lastCitStatus.getAlarmState();
		//判断市--站--原来告警状态，修改市-xx状态-1
		if (lastCitAlaSta==1) {
			//获取redis中市-状态信息
			MechanismStatus lastCitMSAl = getOrgStatus(result.getCityCode());
			MechanismStatus newCitMSAl = lastCitMSAl;
			newCitMSAl.setGa(lastCitMSAl.getGa()-1);
			setOrgStatus(result.getCityCode(), newCitMSAl);
			
		}
		if (lastCitAlaSta==2) {
			//获取redis中市-状态信息
			MechanismStatus lastCitMSAl = getOrgStatus(result.getCityCode());
			MechanismStatus newCitMSAl = lastCitMSAl;
			newCitMSAl.setCa(lastCitMSAl.getCa()-1);
			setOrgStatus(result.getCityCode(), newCitMSAl);
			
		}
		//获取区县--站--原来告警状态-----------------------------------------
		//从redis获取到的原来的区县-站-状态信息
		StationStatus lastAreStatus = getOrgStationStatus(result.getDistrictCode(), stationId);
		int lastAreAlaSta = lastAreStatus.getAlarmState();
		//判断区县--站--原来告警状态，修改区县-xx状态-1
		if (lastAreAlaSta==1) {
			//获取redis中区县-状态信息
			MechanismStatus lastAreMSAl = getOrgStatus(result.getDistrictCode());
			MechanismStatus newAreMSAl = lastAreMSAl;
			newAreMSAl.setGa(lastAreMSAl.getGa()-1);
			setOrgStatus(result.getDistrictCode(), newAreMSAl);
			
		}
		if (lastAreAlaSta==2) {
			//获取redis中区县-状态信息
			MechanismStatus lastAreMSAl = getOrgStatus(result.getDistrictCode());
			MechanismStatus newAreMSAl = lastAreMSAl;
			newAreMSAl.setCa(lastAreMSAl.getCa()-1);
			setOrgStatus(result.getDistrictCode(), newAreMSAl);
			
		}
		
		//删除省-站-状态信息
		redisClientDao.hdel(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z, stationId);
		log.info(new Date() + " 省-站-状态信息 删除成功 ！" + " 省id：" + result.getProvinceCode() + " 站id： " + stationId );
		//删除市-站-状态信息
		redisClientDao.hdel(result.getCityCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z, stationId);
		log.info(new Date() + " 市-站-状态信息 删除成功 ！" + " 市id：" + result.getCityCode() + " 站id： " + stationId );
		//删除区县-站-状态信息
		redisClientDao.hdel(result.getDistrictCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z, stationId);
		log.info(new Date() + " 区县-站-状态信息 删除成功 ！" + " 区县id：" + result.getDistrictCode() + " 站id： " + stationId );
		
		//省-状态	-1
		//获取redis中省-状态信息
		MechanismStatus lastProMS = getOrgStatus(result.getProvinceCode());
		MechanismStatus newProMS = lastProMS;
		newProMS.setTsn(lastProMS.getTsn()-1);
		//更新到redis
		setOrgStatus(result.getProvinceCode(), newProMS);
		log.info(new Date() + " 将省-状态---站总数-1:" + JsonUtil.genJsonString(newProMS) );
				
		//市 -状态 -1
		//获取redis中市-状态信息
		MechanismStatus lastCitMS = getOrgStatus(result.getCityCode());
		MechanismStatus newCitMS = lastCitMS;
		newCitMS.setTsn(lastCitMS.getTsn()-1);
		//更新到redis
		setOrgStatus(result.getCityCode(), newCitMS);
		log.info(new Date() + " 将市-状态---站总数-1:" + JsonUtil.genJsonString(newCitMS) );
		
		//区县 -状态 -1
		//获取redis中区县-状态信息
		MechanismStatus lastAreMS = getOrgStatus(result.getDistrictCode());
		MechanismStatus newAreMS = lastAreMS;
		newAreMS.setTsn(lastAreMS.getTsn()-1);
		//更新到redis
		setOrgStatus(result.getDistrictCode(), newAreMS);
		log.info(new Date() + " 将区县-状态---站总数-1:" + JsonUtil.genJsonString(newAreMS) );
		
	}

	
	//公共方法
		/*从redis获取机构--状态信息
		 * @param 机构id  OrgId
		*/
		public MechanismStatus getOrgStatus(String OrgId){
			RedisClientDao redisClientDao = (RedisClientDao) SysContextUtil.getSpringApplicationContext().getBean("redisClientDao");
			MechanismStatus mechanismStatus = new MechanismStatus();
			try {
				mechanismStatus = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDao.get(OrgId), MechanismStatus.class);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
			return mechanismStatus;
		}
		/*向redis写入机构--状态信息
		 * @param 机构id,机构状态信息     OrgId,mechanismStatus
		*/
		public void setOrgStatus(String OrgId , MechanismStatus mechanismStatus){
			RedisClientDao redisClientDao = (RedisClientDao) SysContextUtil.getSpringApplicationContext().getBean("redisClientDao");
			redisClientDao.set(OrgId, JsonUtil.genJsonString(mechanismStatus) );
		}
		
		/*从redis获取机构__站--状态信息
		 * @param 机构id,站id,机构   OrgId,StationId
		*/
		public StationStatus getOrgStationStatus(String OrgId , String StationId ){
			RedisClientDao redisClientDao = (RedisClientDao) SysContextUtil.getSpringApplicationContext().getBean("redisClientDao");
			StationStatus stationStatus = new StationStatus();
			try {
				stationStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDao.hget(OrgId+ConstantsInfo.MECHANISM_STATION_STATUS_Z , StationId) , StationStatus.class);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
			return stationStatus;
		}
		/*向redis放入 机构__站--状态信息
		 * @param 机构id,站id,站状态信息  OrgId,StationId,stationStatus
		*/
		public void setOrgStationStatus(String OrgId , String StationId , StationStatus stationStatus){
			RedisClientDao redisClientDao = (RedisClientDao) SysContextUtil.getSpringApplicationContext().getBean("redisClientDao");
			redisClientDao.hset(OrgId+ConstantsInfo.MECHANISM_STATION_STATUS_Z , StationId , JsonUtil.genJsonString(stationStatus));
		}
	
}
