package com.horizon.monitoring.alarm.websocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.horizon.common.base.RedisClientDao;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.common.util.JsonUtil;
import com.horizon.common.util.SysContextUtil;
import com.horizon.monitoring.alarm.dao.IAlarmDAO;
import com.horizon.monitoring.alarm.vo.AlarmInfo;
import com.horizon.monitoring.map.vo.CityInfo;
import com.horizon.monitoring.map.vo.MapResponse;
import com.horizon.monitoring.map.vo.StationInfo;
import com.horizon.monitoring.pile.vo.PileDetail;
import com.horizon.monitoring.pile.vo.PileResponse;
import com.horizon.monitoring.pile.vo.StationBrief;
import com.horizon.monitoring.station.vo.PileBrief;
import com.horizon.monitoring.station.vo.StationResponse;

public class RspObjGetter {
	private static final Logger log = Logger.getLogger(RspObjGetter.class);
	private static RedisClientDao redisClient = (RedisClientDao) SysContextUtil
			.getSpringBeanObject("redisClientDao");

	private static IAlarmDAO alarmDAO = (IAlarmDAO) SysContextUtil
			.getSpringBeanObject("alarmDAO");

	public static void test() {
		AlarmInfo alarmInfo = alarmDAO.getAlarmVOBaseOrder("456");
		log.info(JsonUtil.genJsonString(alarmInfo));
	}

	// /////////////////////////////////测试成功
	// ////////////////////////////////待测试
//	public static PileResponse getPileDetail(String pileId) {
//		Map<String, String> stationMap = redisClient.hmgetall(pileId);
//		if (stationMap.size() == 1) {
//			Map.Entry<String, String> first = stationMap.entrySet().iterator()
//					.next();
//			String stationId = first.getKey();
//			String orgId = first.getValue();
//			return getPileDetail(orgId, stationId, pileId);
//		} else
//			return null;
//
//	}

	public static PileResponse getPileDetail(String orgId, String stationId,
			String pileId) {
		log.info("getPileDetail");
		try {

			// 站的粗略信息
			String statJson = redisClient.hget(orgId
					+ ConstantsInfo.MECHANISM_STATION_STATUS_Z, stationId);
			log.info("stationJson:" + statJson);
			StationBrief stationBrief = (StationBrief) JsonUtil
					.genObjectFromJsonString(statJson, StationBrief.class);

			// 桩的信息
			String pileJson = redisClient.hget(stationId, pileId);
			log.info("pileJson:" + pileJson);
			PileDetail pileDetail = (PileDetail) JsonUtil
					.genObjectFromJsonString(pileJson, PileDetail.class);

			// 返回VO
			PileResponse response = new PileResponse();
			response.setIsNew(1);
			response.setStationBrief(stationBrief);
			response.setPileDetail(pileDetail);
			return response;

		} catch (Exception e) {
			log.error("getPileDetail error:"+ e.getMessage());
			return null;
		}
	}

//	public static StationResponse getStationDetail(String stationId) {
//		String orgId = redisClient.get(stationId + ConstantsInfo.CHILD_SUF);
//		if (orgId != null) {
//			return getStationDetail(orgId, stationId);
//		} else {
//			return null;
//		}
//	}

	public static StationResponse getStationDetail(String orgId,
			String stationId) {
		log.info("->getStationDetail");
		try {
			// station的信息
			String statJson = redisClient.hget(orgId
					+ ConstantsInfo.MECHANISM_STATION_STATUS_Z, stationId);
			log.info(statJson);
			StationResponse response = (StationResponse) JsonUtil
					.genObjectFromJsonString(statJson, StationResponse.class);
			// station的piles信息
			Map<String, String> stationMap = redisClient.hmgetall(stationId);
			List<PileBrief> piles = new ArrayList<PileBrief>();
			for (Map.Entry<String, String> entry : stationMap.entrySet()) {
				log.info("pileId:" + entry.getKey() + ",pileInfo"
						+ entry.getValue());
				PileBrief pileInfo = (PileBrief) JsonUtil
						.genObjectFromJsonString(entry.getValue(),
								PileBrief.class);
				pileInfo.setId(entry.getKey());
				piles.add(pileInfo);
			}
			// station的返回VO
			response.setIsNew(1);
			response.setPileInfos(piles);
			return response;
		} catch (Exception e) {
			log.error("getStationDetail error:"+ e.getMessage());
			return null;
		}
	}

	// ////////////////////////////////待修改
	// public static Set<String> getPosterity(String orgId) {
	// return new HashSet<String>();
	// }
/**
 * 根据权限编码获取地图页信息
 * @param orgIds
 * @return
 */
	public static MapResponse getMapInfo(String[] orgIds) {
		MapResponse mapInfo = new MapResponse();
		mapInfo.setIsNew(1);
		List<CityInfo> citys = new ArrayList<CityInfo>();
		for (String orgId : orgIds) {
			CityInfo cityInfo = getOrgInfo(orgId);
			if (cityInfo != null)
				citys.add(cityInfo);
		}
		mapInfo.setCitys(citys);
		return mapInfo;
	}
/**
 * 获取城市和站信息
 * @param orgId
 * @return
 */
	public static CityInfo getOrgInfo(String orgId) {
		log.debug("->getOrgInfo");
		try {
			String cityJson = redisClient.get(orgId);// 城市信息
			  
			log.debug("cityJson:" + cityJson);
			if (cityJson == null)
				return null;
			CityInfo city = (CityInfo) JsonUtil.genObjectFromJsonString(
					cityJson, CityInfo.class);
			//添加站信息
			Map<String, String> stationMap = redisClient.hmgetall(orgId
					+ ConstantsInfo.MECHANISM_STATION_STATUS_Z);
			List<StationInfo> stations = new ArrayList<StationInfo>();
			for (Map.Entry<String, String> entry : stationMap.entrySet()) {
				log.info("stationId:" + entry.getKey() + ",stationInfo"
						+ entry.getValue());
				StationInfo stationInfo = (StationInfo) JsonUtil
						.genObjectFromJsonString(entry.getValue(),
								StationInfo.class);
				stationInfo.setCode(entry.getKey());
				stations.add(stationInfo);
			}
			city.setStationList(stations);
			
			//添加下级机构信息
			List<String> children = redisClient.lrange(orgId+ConstantsInfo.CHILD_SUF, 0, -1);
		
			if (children != null) {// 添加机构的子机构信息				
				List<CityInfo> citys = new ArrayList<CityInfo>();
				for (String child : children) {
					log.debug(orgId + " has subOrg: " + child);
					String citychildrenJson = redisClient.get(child);// 城市信息
					if (citychildrenJson == null)
						break;
					CityInfo childrenCity = (CityInfo) JsonUtil.genObjectFromJsonString(
							citychildrenJson, CityInfo.class);
					
					
					citys.add(childrenCity);
				}
				city.setCitys(citys);
			}
			
			 
				return city;
			 
		} catch (Exception e) {
			log.error("getMapInfo error:"+ e.getMessage());
			return null;
		}
	}
	
	 

	// /**
	// * 判断机构是不是区级机构
	// *
	// * @param orgId
	// * @return
	// */
	// public static boolean isCounty(String orgId) {
	// String orgJson = redisClient.get(orgId);
	// if(orgJson==null)
	// return true;
	// String districtCode = JsonUtil.getField(orgJson,
	// ConstantsInfo.ORG_DISTRICT);
	// if ("".equals(districtCode))
	// return false;
	// else
	// return true;
	// }
}
