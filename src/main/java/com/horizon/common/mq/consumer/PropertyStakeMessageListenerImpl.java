package com.horizon.common.mq.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.horizon.common.hsf.model.PileMonitorInitDataVO;
import com.horizon.common.mq.model.PropStake;
import com.horizon.common.redis.Observer.ObMessage;
import com.horizon.common.redis.Observer.util.RedisUtli;
import com.horizon.common.redis.model.MechanismStatus;
import com.horizon.common.redis.model.PileStatus;
import com.horizon.common.redis.model.StationStatus;
import com.horizon.common.util.JsonUtil;
import com.horizon.common.util.SysContextUtil;
import com.horizon.common.vo.GetStakeVO;
import com.horizon.common.vo.PileHsfVO;
import com.send.edas.property.service.ISendStakeService;
import com.send.pile.service.PileInfoGetter;
import com.send.pileInterface.service.IPileInterfaceService;
import com.send.pile.service.PileInfoGetter;
import com.send.pileInterface.service.IPileInterfaceService;

public class PropertyStakeMessageListenerImpl implements MessageListener {
	
	private static Logger log = Logger.getLogger(PropertyStakeMessageListenerImpl.class);
	
	public Action consume(Message message, ConsumeContext consumeContext) {
		
		log.debug(new Date() + " 资产--桩信息变更~~~~~~~~~~~~ ");
		log.debug(new Date() + " Receive PropertyStakeMessage, Topic is:" + message.getTopic() + ", MsgId is:" + message.getMsgID());
		
		byte[] body = message.getBody();
		
		log.debug(new Date() + " 监听信息如下： " + new String(body));
		
		try {
				
			PropStake stake = (PropStake) JsonUtil.genObjectFromJsonString(new String(body), PropStake.class);
			
			//获取变更类型
			String type  = stake.getType();//CREATE/UPDATE/DELETE 创建 / 更新 / 删除
			
			if("CREATE".equals(type)){
				log.debug(new Date() + " 增加--桩信息 " );
				addStakeStatusRed(stake.getUuid(), SysContextUtil
						.getSpringApplicationContext());
			}
			if ("UPDATE".equals(type)) {
				log.debug(new Date() + " 更新--桩信息 " );
				updateStakeStatusRed(stake.getUuid(), SysContextUtil
						.getSpringApplicationContext());
				
			}
			if ("DELETE".equals(type)) {
				log.debug(new Date() + " 删除--桩信息 ");
				deleteStakeStatusRed(stake.getUuid(), SysContextUtil
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
	 * 新增redis里桩的状态
	 * @param 桩的uuid
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public void addStakeStatusRed(String StakeId, final ApplicationContext ctx) {
	try{
		log.debug(new Date() + " 新增一个桩 ！");
		
		RedisClientDao redisClientDAO = (RedisClientDao) SysContextUtil.getSpringApplicationContext().getBean("redisClientDAO");
		//ChargerService接口
//		ChargerService stakeService = ( ChargerService ) ctx.getBean( "selStake" );
//		//调用接口获取 
//		ResponseCharger  vo = stakeService.get(StakeId);
//		Charger vo1 = vo.getCharger();
		
		
		//根据给出的站id，获取到新增站的信息，
//		String json = stakeService.get(StakeId);
		String json = "";
		//String json2 = "{\"uuid\":\"stake_test_0001\",\"name\": \"测试桩\",\"org\": \"机构uuid\", \"station\":\"1234\",\"provinceCode\":\"110000\",\"cityCode\":\"110100\",\"districtCode\":\"110101\",\"address\": \"北京市东城区市辖区测试区\",\"lng\":\"66.6\",\"lat\": \"88.8\"}";

		//json = 
		
		//转化成vo
		GetStakeVO stake = (GetStakeVO) JsonUtil.genObjectFromJsonString(json, GetStakeVO.class);
		//调用接口查到的桩的信息
		PileHsfVO result = (PileHsfVO) JsonUtil.genObjectFromJsonString(stake.getData(), PileHsfVO.class);
		
		//传回redis存储信息
		PileStatus stakeSta = new PileStatus();
		
		
		//桩编号
		stakeSta.setPileCode(result.getUuid());
		//桩名称
		stakeSta.setPileName(result.getName());
		//组名
		stakeSta.setGroupName(result.getStakeGroup());
		//时间
		Long tim = System.currentTimeMillis();
		stakeSta.setTime(tim);
		//电源类型
		stakeSta.setPs(result.getMeasureModel());
		
		
		//添加--站-桩-状态
		redisClientDAO.hset(result.getStation(), StakeId, JsonUtil.genJsonString(stakeSta));
		//添加--桩-站-机构
		redisClientDAO.hset(StakeId, result.getStation(), result.getProvinceCode()+"_"+result.getCityCode()+"_"+result.getDistrictCode());
		
		//从redis获取到的原来的省-站-状态信息
		StationStatus lastProStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
		//更新省-站-状态信息（--桩总数+1）
		StationStatus newProStatus = lastProStatus;
		newProStatus.setAllNum(lastProStatus.getAllNum()+1);
		redisClientDAO.hset(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newProStatus));
		
		//从redis获取到的原来的市-站-状态信息
		StationStatus lastCitStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getCityCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
		//更新市-站-状态信息（--桩总数+1）
		StationStatus newCitStatus = lastCitStatus;
		newCitStatus.setAllNum(lastCitStatus.getAllNum()+1);
		redisClientDAO.hset(result.getCityCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newCitStatus));
		
		//从redis获取到的原来的区县-站-状态信息
		StationStatus lastAreStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getDistrictCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
		//更新区县-站-状态信息（--桩总数+1）
		StationStatus newAreStatus = lastAreStatus;
		newAreStatus.setAllNum(lastAreStatus.getAllNum()+1);
		redisClientDAO.hset(result.getDistrictCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newAreStatus));
		
		//省-状态	--桩总数+1
		//获取redis中省-状态信息
		MechanismStatus lastProMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getProvinceCode()), MechanismStatus.class);
		MechanismStatus newProMS = lastProMS;
		newProMS.setAllNum(lastProMS.getAllNum()+1);
		//更新到redis
		redisClientDAO.set(result.getProvinceCode(), JsonUtil.genJsonString(newProMS) );
		
		//市-状态	--桩总数+1
		//获取redis中市-状态信息
		MechanismStatus lastCitMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getCityCode()), MechanismStatus.class);
		MechanismStatus newCitMS = lastCitMS;
		newCitMS.setAllNum(lastCitMS.getAllNum()+1);
		//更新到redis
		redisClientDAO.set(result.getCityCode(), JsonUtil.genJsonString(newCitMS) );
		
		//区县 -状态--桩总数 +1
		//获取redis中区县-状态信息
		MechanismStatus lastAreMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getDistrictCode()), MechanismStatus.class);
		MechanismStatus newAreMS = lastAreMS;
		newAreMS.setAllNum(lastAreMS.getAllNum()+1);
		//更新到redis
		redisClientDAO.set(result.getDistrictCode(), JsonUtil.genJsonString(newAreMS) );
		
		log.info("桩添加完毕");
		//调取桩状态信息
		PileInfoGetter pileInfoGetter = (PileInfoGetter) ctx.getBean("pileInfoGetter");
		List<String> stakeIdList = new ArrayList<String>();
		stakeIdList.add(StakeId);
		List<Map<String,String>> list = pileInfoGetter.searchPileStatus(stakeIdList,ConstantsInfo.HSF_MONITOR);
		if(null!=list&&list.size()>0){
			String mess = list.get(0).get(ConstantsInfo.HSF_MONITOR);
				log.info("桩信息--->"+mess);
				updatePileRedisInit(mess,redisClientDAO);
			    //根据状态更新桩初始化状态
				log.info("充电桩状态更新成功");
		}
	} catch (IOException e) {
		log.info(e);
	}
}
	
	
	/**
	 * 更新redis里桩的状态
	 * @param 桩的uuid
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public void updateStakeStatusRed(String StakeId, final ApplicationContext ctx) {
		try{
		log.debug(new Date() + " 更新一个桩 ！");
		
		//getStake接口修改
		IPileInterfaceService stakeService = ( IPileInterfaceService ) ctx.getBean( "selStake" );
		RedisClientDao redisClientDAO = (RedisClientDao) SysContextUtil.getSpringApplicationContext().getBean("redisClientDAO");
		
		//根据给出的站id，获取到新增桩的信息，
		//String json = stakeService.getStake(StakeId, "", "" ,"");
		String json2 = "{\"uuid\":\"stake_test_0001\",\"name\": \"测试桩02\",\"org\": \"机构uuid\", \"station\":\"1234\",\"provinceCode\":\"110000\",\"cityCode\":\"110100\",\"districtCode\":\"110101\",\"address\": \"北京市东城区市辖区测试区\",\"lng\":\"66.6\",\"lat\": \"88.8\"}";

		
		//转化成vo
		//GetStakeVO stake = (GetStakeVO) JsonUtil.genObjectFromJsonString(json2, GetStakeVO.class);
		//调用接口查到的桩的信息
		PileHsfVO result = (PileHsfVO) JsonUtil.genObjectFromJsonString(json2, PileHsfVO.class);
		
		//传回redis存储信息 --站-桩-状态
		PileStatus lastStakeSta = (PileStatus) JsonUtil.genObjectFromJsonString( redisClientDAO.hget(result.getStation(), StakeId) , PileStatus.class);
		PileStatus newStakeSta = lastStakeSta ;
		//桩编号
		newStakeSta.setPileCode(result.getUuid());
		//桩名称
		newStakeSta.setPileName(result.getName());
		//组名
		newStakeSta.setGroupName(result.getStakeGroup());
		//时间
		Long tim = System.currentTimeMillis();
		newStakeSta.setTime(tim);
		
		
		//修改--站-桩-状态
		redisClientDAO.hset(result.getStation(), StakeId, JsonUtil.genJsonString(newStakeSta));
		//修改--桩-站-机构
		redisClientDAO.hset(StakeId, result.getStation(), result.getProvinceCode()+"_"+result.getCityCode()+"_"+result.getDistrictCode());
		
		log.info("桩更新完毕");
		//调取桩状态信息
		PileInfoGetter pileInfoGetter = (PileInfoGetter) ctx.getBean("pileInfoGetter");
		List<String> stakeIdList = new ArrayList<String>();
		stakeIdList.add(StakeId);
		List<Map<String,String>> list = pileInfoGetter.searchPileStatus(stakeIdList,ConstantsInfo.HSF_MONITOR);
		if(null!=list&&list.size()>0){
			String mess = list.get(0).get(ConstantsInfo.HSF_MONITOR);
				log.info("桩信息--->"+mess);
				updatePileRedisInit(mess,redisClientDAO);
			    //根据状态更新桩初始化状态
				log.info("充电桩状态更新成功");
		}
		} catch (IOException e) {
			log.info(e);
		}
}
	
	/**
	 * 删除redis里桩的状态
	 * @param 桩的uuid
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public void deleteStakeStatusRed(String StakeId, final ApplicationContext ctx) {
		
		
		log.debug(new Date() + " 删除一个桩 ！");
		//???;
		//getStake接口修改！！！！
		//ISendStakeService stakeService = ( ISendStakeService ) ctx.getBean( "StakeGet" );
		RedisClientDao redisClientDAO = (RedisClientDao) SysContextUtil.getSpringApplicationContext().getBean("redisClientDAO");
		
		//根据给出的站id，获取到新增站的信息，
		//String json = stakeService.getStake(StakeId, "", "" ,"");
		String json2 = "{\"uuid\":\"stake_test_0001\",\"name\": \"测试桩02\",\"org\": \"机构uuid\", \"station\":\"1234\",\"provinceCode\":\"110000\",\"cityCode\":\"110100\",\"districtCode\":\"110101\",\"address\": \"北京市东城区市辖区测试区\",\"lng\":\"66.6\",\"lat\": \"88.8\"}";

		//转化成vo
		GetStakeVO stake;
		try {
			//stake = (GetStakeVO) JsonUtil.genObjectFromJsonString(json2, GetStakeVO.class);
		
		//调用接口查到的站的信息
		PileHsfVO result = (PileHsfVO) JsonUtil.genObjectFromJsonString(json2, PileHsfVO.class);
		
		//传回redis存储信息 --站-桩-状态
		PileStatus lastStakeSta = (PileStatus) JsonUtil.genObjectFromJsonString( redisClientDAO.hget(result.getStation(), StakeId) , PileStatus.class);
		
		
		
		//删除--站-桩-状态
		redisClientDAO.hdel(result.getStation(), StakeId);
		//删除--桩-站-机构
		redisClientDAO.hdel(StakeId, result.getStation());
		
		
		//获取删除的站-桩（桩的原来状态）
		String lastRunsta = lastStakeSta.getRunSta();
		//原来状态为1故障
		if ("1".equals(lastRunsta)) {
			//从redis获取到的原来的省-站-状态信息()
			StationStatus lastProStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
			//更新省-站-状态信息（--桩总数-1,告警数-1）
			StationStatus newProStatus = lastProStatus;
			newProStatus.setAllNum(lastProStatus.getAllNum()-1);
			newProStatus.setFaultNum(lastProStatus.getFaultNum()-1);
			redisClientDAO.hset(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newProStatus));
			
			//从redis获取到的原来的市-站-状态信息
			StationStatus lastCitStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getCityCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
			//更新市-站-状态信息（--桩总数-1,告警数-1）
			StationStatus newCitStatus = lastCitStatus;
			newCitStatus.setAllNum(lastCitStatus.getAllNum()-1);
			newCitStatus.setFaultNum(lastCitStatus.getFaultNum()-1);
			redisClientDAO.hset(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newCitStatus));
			
			//从redis获取到的原来的区县-站-状态信息
			StationStatus lastAreStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getDistrictCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
			//更新区县-站-状态信息（--桩总数-1,告警数-1）
			StationStatus newAreStatus = lastAreStatus;
			newAreStatus.setAllNum(lastAreStatus.getAllNum()-1);
			newAreStatus.setFaultNum(lastAreStatus.getFaultNum()-1);
			redisClientDAO.hset(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newAreStatus));
			
			
			//省-状态	--桩总数-1,故障数-1
			//获取redis中省-状态信息
			MechanismStatus lastProMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getProvinceCode()), MechanismStatus.class);
			MechanismStatus newProMS = lastProMS;
			newProMS.setAllNum(lastProMS.getAllNum()-1);
			newProMS.setFaultNum(lastProMS.getFaultNum()-1);
			//更新到redis
			redisClientDAO.set(result.getProvinceCode(), JsonUtil.genJsonString(newProMS) );
			
			//市-状态	--桩总数-1,故障数-1
			//获取redis中市-状态信息
			MechanismStatus lastCitMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getCityCode()), MechanismStatus.class);
			MechanismStatus newCitMS = lastCitMS;
			newCitMS.setAllNum(lastCitMS.getAllNum()-1);
			newCitMS.setFaultNum(lastCitMS.getFaultNum()-1);
			//更新到redis
			redisClientDAO.set(result.getCityCode(), JsonUtil.genJsonString(newCitMS) );
			
			//区县 -状态--桩总数 -1,故障数-1
			//获取redis中区县-状态信息
			MechanismStatus lastAreMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getDistrictCode()), MechanismStatus.class);
			MechanismStatus newAreMS = lastAreMS;
			newAreMS.setAllNum(lastAreMS.getAllNum()-1);
			newAreMS.setFaultNum(lastAreMS.getFaultNum()-1);
			//更新到redis
			redisClientDAO.set(result.getDistrictCode(), JsonUtil.genJsonString(newAreMS) );
			
			
		}
		//原来状态为2待机
		if ("1".equals(lastRunsta)) {
			//从redis获取到的原来的省-站-状态信息
			StationStatus lastProStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
			//更新省-站-状态信息（--桩总数-1,空闲-1）
			StationStatus newProStatus = lastProStatus;
			newProStatus.setAllNum(lastProStatus.getAllNum()-1);
			newProStatus.setFreeNum(lastProStatus.getFreeNum()-1);
			redisClientDAO.hset(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newProStatus));
			
			//从redis获取到的原来的市-站-状态信息
			StationStatus lastCitStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getCityCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
			//更新市-站-状态信息（--桩总数-1,空闲-1）
			StationStatus newCitStatus = lastCitStatus;
			newCitStatus.setAllNum(lastCitStatus.getAllNum()-1);
			newCitStatus.setFreeNum(lastCitStatus.getFreeNum()-1);
			redisClientDAO.hset(result.getCityCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newCitStatus));
			
			//从redis获取到的原来的区县-站-状态信息
			StationStatus lastAreStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getDistrictCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
			//更新区县-站-状态信息（--桩总数-1,空闲-1）
			StationStatus newAreStatus = lastAreStatus;
			newAreStatus.setAllNum(lastAreStatus.getAllNum()-1);
			newAreStatus.setFreeNum(lastAreStatus.getFreeNum()-1);
			redisClientDAO.hset(result.getDistrictCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newAreStatus));
			
			
			//省-状态	--桩总数-1
			//获取redis中省-状态信息
			MechanismStatus lastProMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getProvinceCode()), MechanismStatus.class);
			MechanismStatus newProMS = lastProMS;
			newProMS.setAllNum(lastProMS.getAllNum()-1);
			//更新到redis
			redisClientDAO.set(result.getProvinceCode(), JsonUtil.genJsonString(newProMS) );
			
			//市-状态	--桩总数-1
			//获取redis中市-状态信息
			MechanismStatus lastCitMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getCityCode()), MechanismStatus.class);
			MechanismStatus newCitMS = lastCitMS;
			newCitMS.setAllNum(lastCitMS.getAllNum()-1);
			//更新到redis
			redisClientDAO.set(result.getCityCode(), JsonUtil.genJsonString(newCitMS) );
			
			//区县 -状态--桩总数 -1
			//获取redis中区县-状态信息
			MechanismStatus lastAreMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getDistrictCode()), MechanismStatus.class);
			MechanismStatus newAreMS = lastAreMS;
			newAreMS.setAllNum(lastAreMS.getAllNum()-1);
			//更新到redis
			redisClientDAO.set(result.getDistrictCode(), JsonUtil.genJsonString(newAreMS) );
						
		}
		//原来状态为3工作
		if ("1".equals(lastRunsta)) {
			//从redis获取到的原来的省-站-状态信息
			StationStatus lastProStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
			//更新省-站-状态信息（--桩总数-1,充电数-1）
			StationStatus newProStatus = lastProStatus;
			newProStatus.setAllNum(lastProStatus.getAllNum()-1);
			newProStatus.setChargeNum(lastProStatus.getChargeNum()-1);
			redisClientDAO.hset(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newProStatus));
			
			//从redis获取到的原来的市-站-状态信息
			StationStatus lastCitStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getCityCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
			//更新市-站-状态信息（--桩总数-1,充电数-1）
			StationStatus newCitStatus = lastCitStatus;
			newCitStatus.setAllNum(lastCitStatus.getAllNum()-1);
			newCitStatus.setChargeNum(lastCitStatus.getChargeNum()-1);
			redisClientDAO.hset(result.getCityCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newCitStatus));
			
			//从redis获取到的原来的区县-站-状态信息
			StationStatus lastAreStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getDistrictCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
			//更新区县-站-状态信息（--桩总数-1,充电数-1）
			StationStatus newAreStatus = lastAreStatus;
			newAreStatus.setAllNum(lastAreStatus.getAllNum()-1);
			newAreStatus.setChargeNum(lastAreStatus.getChargeNum()-1);
			redisClientDAO.hset(result.getDistrictCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newAreStatus));
			
			
			//省-状态	--桩总数-1
			//获取redis中省-状态信息
			MechanismStatus lastProMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getProvinceCode()), MechanismStatus.class);
			MechanismStatus newProMS = lastProMS;
			newProMS.setAllNum(lastProMS.getAllNum()-1);
			//更新到redis
			redisClientDAO.set(result.getProvinceCode(), JsonUtil.genJsonString(newProMS) );
			
			//市-状态	--桩总数-1
			//获取redis中市-状态信息
			MechanismStatus lastCitMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getCityCode()), MechanismStatus.class);
			MechanismStatus newCitMS = lastCitMS;
			newCitMS.setAllNum(lastCitMS.getAllNum()-1);
			//更新到redis
			redisClientDAO.set(result.getCityCode(), JsonUtil.genJsonString(newCitMS) );
			
			//区县 -状态--桩总数 -1
			//获取redis中区县-状态信息
			MechanismStatus lastAreMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getDistrictCode()), MechanismStatus.class);
			MechanismStatus newAreMS = lastAreMS;
			newAreMS.setAllNum(lastAreMS.getAllNum()-1);
			//更新到redis
			redisClientDAO.set(result.getDistrictCode(), JsonUtil.genJsonString(newAreMS) );
			
		}
		//原来状态为4离线
		if ("1".equals(lastRunsta)) {
			//从redis获取到的原来的省-站-状态信息
			StationStatus lastProStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
			//更新省-站-状态信息（--桩总数-1，离线数-1）
			StationStatus newProStatus = lastProStatus;
			newProStatus.setAllNum(lastProStatus.getAllNum()-1);
			newProStatus.setOffNum(lastProStatus.getOffNum()-1);
			redisClientDAO.hset(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newProStatus));
			
			//从redis获取到的原来的市-站-状态信息
			StationStatus lastCitStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getCityCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
			//更新市-站-状态信息（--桩总数-1，离线数-1）
			StationStatus newCitStatus = lastCitStatus;
			newCitStatus.setAllNum(lastCitStatus.getAllNum()-1);
			newCitStatus.setOffNum(lastCitStatus.getOffNum()-1);
			redisClientDAO.hset(result.getCityCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newCitStatus));
			
			//从redis获取到的原来的区县-站-状态信息
			StationStatus lastAreStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getDistrictCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
			//更新区县-站-状态信息（--桩总数-1，离线数-1）
			StationStatus newAreStatus = lastAreStatus;
			newAreStatus.setAllNum(lastAreStatus.getAllNum()-1);
			newAreStatus.setOffNum(lastAreStatus.getOffNum()-1);
			redisClientDAO.hset(result.getDistrictCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newAreStatus));
			
			
			//省-状态	--桩总数-1，离线数-1
			//获取redis中省-状态信息
			MechanismStatus lastProMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getProvinceCode()), MechanismStatus.class);
			MechanismStatus newProMS = lastProMS;
			newProMS.setAllNum(lastProMS.getAllNum()-1);
			newProMS.setOffNum(lastProMS.getOffNum()-1);
			//更新到redis
			redisClientDAO.set(result.getProvinceCode(), JsonUtil.genJsonString(newProMS) );
			
			//市-状态	--桩总数-1，离线数-1
			//获取redis中市-状态信息
			MechanismStatus lastCitMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getCityCode()), MechanismStatus.class);
			MechanismStatus newCitMS = lastCitMS;
			newCitMS.setAllNum(lastCitMS.getAllNum()-1);
			newCitMS.setOffNum(lastCitMS.getOffNum()-1);
			//更新到redis
			redisClientDAO.set(result.getCityCode(), JsonUtil.genJsonString(newCitMS) );
			
			//区县 -状态--桩总数 -1，离线数-1
			//获取redis中区县-状态信息
			MechanismStatus lastAreMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getDistrictCode()), MechanismStatus.class);
			MechanismStatus newAreMS = lastAreMS;
			newAreMS.setAllNum(lastAreMS.getAllNum()-1);
			newAreMS.setOffNum(lastAreMS.getOffNum()-1);
			//更新到redis
			redisClientDAO.set(result.getDistrictCode(), JsonUtil.genJsonString(newAreMS) );
			
			
		}
		else{
			//从redis获取到的原来的省-站-状态信息
			StationStatus lastProStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
			//更新省-站-状态信息（--桩总数-1）
			StationStatus newProStatus = lastProStatus;
			newProStatus.setAllNum(lastProStatus.getAllNum()-1);
			redisClientDAO.hset(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newProStatus));
			
			//从redis获取到的原来的市-站-状态信息
			StationStatus lastCitStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getCityCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
			//更新市-站-状态信息（--桩总数-1）
			StationStatus newCitStatus = lastCitStatus;
			newCitStatus.setAllNum(lastCitStatus.getAllNum()-1);
			redisClientDAO.hset(result.getCityCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newCitStatus));
			
			//从redis获取到的原来的区县-站-状态信息
			StationStatus lastAreStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getDistrictCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
			//更新区县-站-状态信息（--桩总数-1）
			StationStatus newAreStatus = lastAreStatus;
			newAreStatus.setAllNum(lastAreStatus.getAllNum()-1);
			redisClientDAO.hset(result.getDistrictCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newAreStatus));
			
			
			//省-状态	--桩总数-1
			//获取redis中省-状态信息
			MechanismStatus lastProMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getProvinceCode()), MechanismStatus.class);
			MechanismStatus newProMS = lastProMS;
			newProMS.setAllNum(lastProMS.getAllNum()-1);
			//更新到redis
			redisClientDAO.set(result.getProvinceCode(), JsonUtil.genJsonString(newProMS) );
			
			//市-状态	--桩总数-1
			//获取redis中市-状态信息
			MechanismStatus lastCitMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getCityCode()), MechanismStatus.class);
			MechanismStatus newCitMS = lastCitMS;
			newCitMS.setAllNum(lastCitMS.getAllNum()-1);
			//更新到redis
			redisClientDAO.set(result.getCityCode(), JsonUtil.genJsonString(newCitMS) );
			
			//区县 -状态--桩总数 -1
			//获取redis中区县-状态信息
			MechanismStatus lastAreMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getDistrictCode()), MechanismStatus.class);
			MechanismStatus newAreMS = lastAreMS;
			newAreMS.setAllNum(lastAreMS.getAllNum()-1);
			//更新到redis
			redisClientDAO.set(result.getDistrictCode(), JsonUtil.genJsonString(newAreMS) );
			
			
		}
		
		//获取省--站--原来告警状态-----------------------------------------
		//从redis获取到的原来的省-站-状态信息
		StationStatus lastProStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
		StationStatus newProStatus = lastProStatus ; 
		int lastProAlaSta = lastProStatus.getAlarmState();
		//计算新的告警状态
		int newProAlaSta = 0 ;
		double Prostate = lastProStatus.getFaultNum()/lastProStatus.getAllNum();
		if (0<Prostate&&Prostate<0.5) {
			newProAlaSta = 1;
		}
		if (Prostate>=0.5) {
			newProAlaSta = 2;
		}
		newProStatus.setAlarmState(newProAlaSta);
		//更新告警状态 
		redisClientDAO.hset(result.getProvinceCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newProStatus));
		//判断省--站--原来告警状态，修改省-xx状态-1
		if (lastProAlaSta==1) {
			//获取redis中省-状态信息
			MechanismStatus lastProMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getProvinceCode()), MechanismStatus.class);
			MechanismStatus newProMS = lastProMS;
			newProMS.setGa(lastProMS.getGa()-1);
			redisClientDAO.set(result.getProvinceCode() , JsonUtil.genJsonString(newProMS));
			
		}
		if (lastProAlaSta==2) {
			//获取redis中省-状态信息
			MechanismStatus lastProMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getProvinceCode()), MechanismStatus.class);
			MechanismStatus newProMS = lastProMS;
			newProMS.setCa(lastProMS.getCa()-1);
			redisClientDAO.set(result.getProvinceCode() , JsonUtil.genJsonString(newProMS));
			
		}
		//判断省--站--新告警状态，修改省-xx状态+1
		if (newProAlaSta==1) {
			//获取redis中省-状态信息
			MechanismStatus lastProMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getProvinceCode()), MechanismStatus.class);
			MechanismStatus newProMS = lastProMS;
			newProMS.setGa(lastProMS.getGa()+1);
			redisClientDAO.set(result.getProvinceCode() , JsonUtil.genJsonString(newProMS));
			
		}
		if (newProAlaSta==2) {
			//获取redis中省-状态信息
			MechanismStatus lastProMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getProvinceCode()), MechanismStatus.class);
			MechanismStatus newProMS = lastProMS;
			newProMS.setCa(lastProMS.getCa()+1);
			redisClientDAO.set(result.getProvinceCode() , JsonUtil.genJsonString(newProMS));
			
		}
		
		//获取市--站--原来告警状态---------------------------------
		//从redis获取到的原来的市-站-状态信息
		StationStatus lastCitStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getCityCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
		StationStatus newCitStatus = lastCitStatus ; 
		int lastCitAlaSta = lastCitStatus.getAlarmState();
		int newCitAlaSta = 0;
		double Citstate = lastCitStatus.getFaultNum()/lastCitStatus.getAllNum();
		if(0<Citstate&&Citstate<0.5){
			newCitAlaSta = 1;
		}
		if(Citstate>=0.5){
			newCitAlaSta = 2;
		}
		//更新告警状态 
		redisClientDAO.hset(result.getCityCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newCitStatus));
		//判断市--站--原来告警状态，修改市-xx状态-1
		if (lastCitAlaSta==1) {
			//获取redis中市-状态信息
			MechanismStatus lastCitMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getCityCode()), MechanismStatus.class);
			MechanismStatus newCitMS = lastCitMS;
			newCitMS.setGa(lastCitMS.getGa()-1);
			redisClientDAO.set(result.getCityCode() , JsonUtil.genJsonString(newCitMS));
			
		}
		if (lastCitAlaSta==2) {
			//获取redis中市-状态信息
			MechanismStatus lastCitMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getCityCode()), MechanismStatus.class);
			MechanismStatus newCitMS = lastCitMS;
			newCitMS.setCa(lastCitMS.getCa()-1);
			redisClientDAO.set(result.getCityCode() , JsonUtil.genJsonString(newCitMS));
			
		}
		//判断市--站--新告警状态，修改市-xx状态+1
		if (newCitAlaSta==1) {
			//获取redis中省-状态信息
			MechanismStatus lastCitMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getCityCode()), MechanismStatus.class);
			MechanismStatus newCitMS = lastCitMS;
			newCitMS.setGa(lastCitMS.getGa()+1);
			redisClientDAO.set(result.getCityCode() , JsonUtil.genJsonString(newCitMS));
			
		}
		if (newCitAlaSta==2) {
			//获取redis中省-状态信息
			MechanismStatus lastCitMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getCityCode()), MechanismStatus.class);
			MechanismStatus newCitMS = lastCitMS;
			newCitMS.setCa(lastCitMS.getCa()+1);
			redisClientDAO.set(result.getCityCode() , JsonUtil.genJsonString(newCitMS));
			
		}
		
		//获取区县--站--原来告警状态-----------------------------------------
		//从redis获取到的原来的区县-站-状态信息
		StationStatus lastAreStatus = (StationStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.hget(result.getDistrictCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation()) , StationStatus.class);
		StationStatus newAreStatus = lastAreStatus ; 
		int lastAreAlaSta = lastAreStatus.getAlarmState();
		int newAreAlaSta = 0;
		double AreState = lastAreStatus.getFaultNum()/lastAreStatus.getAllNum();
		if(0<AreState&&AreState<0.5){
			newAreAlaSta = 1 ;
		}
		if(AreState>=0.5){
			newAreAlaSta = 2 ;
		}
		//更新告警状态 
		redisClientDAO.hset(result.getDistrictCode()+ConstantsInfo.MECHANISM_STATION_STATUS_Z,result.getStation(),JsonUtil.genJsonString(newAreStatus));
		//判断区县--站--原来告警状态，修改区县-xx状态-1
		if (lastAreAlaSta==1) {
			//获取redis中区县-状态信息
			MechanismStatus lastAreMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getDistrictCode()), MechanismStatus.class);
			MechanismStatus newAreMS = lastAreMS;
			newAreMS.setGa(lastAreMS.getGa()-1);
			redisClientDAO.set(result.getDistrictCode() , JsonUtil.genJsonString(newAreMS));
			
		}
		if (lastAreAlaSta==2) {
			//获取redis中区县-状态信息
			MechanismStatus lastAreMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getDistrictCode()), MechanismStatus.class);
			MechanismStatus newAreMS = lastAreMS;
			newAreMS.setCa(lastAreMS.getCa()-1);
			redisClientDAO.set(result.getDistrictCode() , JsonUtil.genJsonString(newAreMS));
			
		}
		//判断区县--站--新告警状态，修改区县-xx状态+1
		if (newAreAlaSta==1) {
			//获取redis中区县-状态信息
			MechanismStatus lastAreMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getDistrictCode()), MechanismStatus.class);
			MechanismStatus newAreMS = lastAreMS;
			newAreMS.setGa(lastAreMS.getGa()+1);
			redisClientDAO.set(result.getDistrictCode() , JsonUtil.genJsonString(newAreMS));
			
		}
		if (newAreAlaSta==2) {
			//获取redis中区县-状态信息
			MechanismStatus lastAreMS = (MechanismStatus) JsonUtil.genObjectFromJsonString(redisClientDAO.get(result.getDistrictCode()), MechanismStatus.class);
			MechanismStatus newAreMS = lastAreMS;
			newAreMS.setCa(lastAreMS.getCa()+1);
			redisClientDAO.set(result.getDistrictCode() , JsonUtil.genJsonString(newAreMS));
			
		}
		} catch (IOException e) {
			log.info(e);
		}
	}
	
	public static void updatePileRedisInit(String searchPileStatusData,RedisClientDao redisClientDAO){
			try {
				// 直流充电桩充电监测信息
				//String newMess = JsonUtil.genJsonString(searchPileStatusData);
				PileMonitorInitDataVO on = (PileMonitorInitDataVO) JsonUtil
						.genObjectFromJsonString(searchPileStatusData,
								PileMonitorInitDataVO.class);
				//初始化观察者的参数
				 ObMessage obMessage = RedisUtli.initObMessage(on.getChargingPileNum(),redisClientDAO);
				 if(obMessage != null) {
					//重新封装新值
					PileStatus pilePsNew = new PileStatus();
	  				pilePsNew.setTime(on.getSampTime());
	  				pilePsNew.setRunSta(on.getWorkStatus());
	  				pilePsNew.setSoc(on.getSoc());
	  			    // 放入新的桩状态
					obMessage.setPineStatus(pilePsNew);
				 }
			//初始化观察者对象们
			RedisUtli.initObServer(obMessage);
			} catch (IOException e) {
				log.error(e);
			}
		}

}
