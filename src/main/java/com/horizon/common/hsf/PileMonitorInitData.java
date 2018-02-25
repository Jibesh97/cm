package com.horizon.common.hsf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.horizon.common.base.RedisClientDao;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.common.hsf.model.PileMonitorInitDataVO;
import com.horizon.common.redis.Observer.ObMessage;
import com.horizon.common.redis.Observer.util.RedisUtli;
import com.horizon.common.redis.model.PileStatus;
import com.horizon.common.util.JsonUtil;
import com.horizon.common.vo.OrgTreeVO;
import com.horizon.common.vo.OrganizationVO;
import com.horizon.common.vo.RespVO;
import com.send.org.service.OrgInfoGetter;
import com.send.pile.service.PileInfoGetter;

/**
 * <P>
 * 初始化充电桩实时监测信息
 * 
 * 
 */
public class PileMonitorInitData {

	/**
	 * 用于输出log
	 */
	private static Logger log = Logger.getLogger(PileMonitorInitData.class);	

  	public static class InitMap {	
  		/**
  		 * 初始化redis信息
  		 */
  		public static void init(final ApplicationContext ctx) {
  			//获取redis dao
  			RedisClientDao rDao = (RedisClientDao)ctx.getBean("redisClientDao");
  			 // 机构接口
			OrgInfoGetter orgInfoGetter = (OrgInfoGetter) ctx.getBean("orgInfoGetter");
			PileInfoGetter pileInfoGetter = (PileInfoGetter) ctx.getBean("pileInfoGetter");
			
			
			
			 //存所有省的code
  			List<String> provinceIds = new ArrayList<String>();
			// 通过桩接口获取所有省级机构id
			String allPid = orgInfoGetter.getOrgs(null, "100000");
			log.info("所有机构Code---------->" + allPid);
			// 所有省的集合
			List<OrgTreeVO> organizationVO = new ArrayList<OrgTreeVO>();
			// 第一级机构的信息
			RespVO rvo = new RespVO();
			try {
				rvo = (RespVO) JsonUtil.genObjectFromJsonString(allPid,
						RespVO.class);
				// organizationVO.add(vo.getData());
			} catch (JsonGenerationException e) {
				log.error(e.getMessage());
			} catch (JsonMappingException e) {
				log.error(e.getMessage());
			} catch (IOException e) {
				log.error(e.getMessage());
			}
			if (rvo.getData() != null) {
				organizationVO = rvo.getData().getSubOrganization();
			}
  			
  			//IPileService pileService = (IPileService) SysContextUtil.getSpringApplicationContext().getBean("pileService");
  		  
  			//api提供的机构
  			//List<OrganizationVO> organizationVO = organizationVO();
  		     
  			//统计所有的省
  			for(OrgTreeVO vo : organizationVO) {
  				provinceIds.add(vo.getOuCode());
  			}
			//provinceIds.add("210000");
  			
  			if(provinceIds != null && provinceIds.size() > 0) {
  				 //存所有站的id
  	  			List<String> stationIds = new ArrayList<String>();
  	  			//统计省下有多少站
  	             for(String pid: provinceIds) {
  	            	 Set<String> set = rDao.hkeys(pid + ConstantsInfo.MECHANISM_STATION_STATUS_Z);
  	            	 if(set != null && rDao.hkeys(pid + ConstantsInfo.MECHANISM_STATION_STATUS_Z).size() > 0) {
  	            		  for(Iterator<String> it = set.iterator(); it.hasNext();) {            
  	            			  stationIds.add(it.next().toString());           
  	            	      } 
  	            	 //stationIds = (String[]) ArrayUtils.addAll(stationIds, set.toArray());
  	            	 }
  	             } 
  	             if(stationIds != null && stationIds.size() >0) {
  	            	 //获取所有桩id
  	  	             List<String> pileIds = new ArrayList<String>();
  	  	  			
  	  	  			//通过省所有的站  ——>获取所有的桩
  	  	  			for(int i = 0; i < stationIds.size(); i++) {
  	  	  				 Set<String> set = rDao.hkeys(stationIds.get(i));
  	  	            	 if(set != null && rDao.hkeys(stationIds.get(i)).size() > 0) {
  	  	            		 for(Iterator<String> it = set.iterator(); it.hasNext();) {            
  	  	            			 pileIds.add(it.next().toString());           
  	  	           	      } 
  	  	            		 //pileIds = (String[]) ArrayUtils.addAll(pileIds, set.toArray());
  	  	            	 }
  	  	  			}  
  	  	  		    List<Map<String,String>> list = pileInfoGetter.searchPileStatus(pileIds,ConstantsInfo.HSF_MONITOR);
  	  	  		    //获取接口数据
  	  	  			//Map<String,String> searchPileStatusData = new  HashMap<String, String>();
  	  	  			for(int i = 0 ; i < list.size(); i++) {
  	  	  				//根据桩id 和查询类型 获取初始化数据
  	  	  				//searchPileStatusData = searchPileStatus(pileIds.get(i),ConstantsInfo.HSF_MONITOR);
  	  	  			   //pileInfoGetter.searchPileStatus(pileIds.get(i),ConstantsInfo.HSF_MONITOR);
  	  	  				//
  	  	  				String mess = list.get(i).get(ConstantsInfo.HSF_MONITOR);
  	  	  				log.info("桩信息--->"+mess);
  	  	  			    updatePileRedisInit(mess,rDao);
  	  	  			    //根据状态更新桩初始化状态
  	  	  				log.info("充电桩初始化状态更新成功");
  	  	  			}
  	             }
  			}
  		}
  		@SuppressWarnings("unchecked")
		public static Map<String,String> searchPileStatus(String chargingPileNum, String satusType) {
  			String json = "{\"sampTime\":\"201705\",\"chargingPileNum\":\"a6b4fefd0f4040e19c202223e29e2e3a\", \"chargingPileInterfaceNum\":\"0\", \"conncetFlag\":\"0\", \"chargingGunFlag\":\"0\", \"electronicLockStatus\":\"0\", \"dcOutputContactorStatus\":\"0\", \"workStatus\":\"0002\", \"outputVoltage\":\"220.2\", \"outputCurrent\":\"5.11\", \"demandVoltage\":\"220.2\", \"demandCurrent\":\"5.11\", \"chargeMode\":\"1\", \"soc\":\"10\", \"batteryType\":\"1\", \"minimumBatteryTemperature\":\"10.5\", \"maximumBatteryTemperature\":\"50.5\", \"cumulativeChargeTime\":\"10\", \"estimatedFullChargeTime\":\"90\", \"maximumBatteryVoltage\":\"110.11\", \"maximumBatteryVoltage\":\"78.88\", \"totalActivePower\":\"10.5\", \"electricityConsumptionAmount\":\"10.1234\", \"serviceFee\":\"10.1234\", \"chargingType\":\"1\", \"userIdentification\":\"111\", \"tariffModelNumber\":\"1001\", \"serviceChargeModelNumber\":\"2001\", \"batch\":\"100001\"}" ;
  			//pileService.updatePileRedis(json);
  			Map<String, String> map = null;
			try {
				map = (Map<String, String>) JsonUtil.genObjectFromJsonString(json, Map.class);
			} catch (Exception e) {
  			 
			}
			return map;
  		}
  		
  		public static List<OrganizationVO> organizationVO() {
  			List<OrganizationVO> organizationVO = new ArrayList<OrganizationVO>();
  			OrganizationVO voA = new OrganizationVO();
  			voA.setProvinceCode("500000");
  			organizationVO.add(voA);
  			return organizationVO;
  		}
  		public static void updatePileRedisInit(String searchPileStatusData,RedisClientDao rDao){
  			try {
  				// 直流充电桩充电监测信息
  				//String newMess = JsonUtil.genJsonString(searchPileStatusData);
  				PileMonitorInitDataVO on = (PileMonitorInitDataVO) JsonUtil
  						.genObjectFromJsonString(searchPileStatusData,
  								PileMonitorInitDataVO.class);
  				//初始化观察者的参数
  				 ObMessage obMessage = RedisUtli.initObMessage(on.getChargingPileNum(),rDao);
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
  				log.error(e.getMessage());
  			}
  		}
  	}

}
