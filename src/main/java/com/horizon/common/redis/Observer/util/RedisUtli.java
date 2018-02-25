package com.horizon.common.redis.Observer.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import com.horizon.common.base.RedisClientDao;
import com.horizon.common.base.Observer.GoodNotifier;
import com.horizon.common.base.Observer.Notifier;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.common.redis.Observer.ObMessage;
import com.horizon.common.redis.Observer.PileObserver;
import com.horizon.common.redis.Observer.constants.PileConstantsInfo;
import com.horizon.common.redis.model.MechanismStatus;
import com.horizon.common.redis.model.PileStationMechanism;
import com.horizon.common.redis.model.PileStatus;
import com.horizon.common.redis.model.StationStatus;
import com.horizon.common.util.JsonUtil;

/**
 * 从接口获取数据时 更新redis的工具类
 * 
 * @author liy
 * 
 */
public class RedisUtli {
	private static Logger log = Logger.getLogger(PileObserver.class);

	/**
	 * 初始化观察者对象们
	 * 
	 * @return
	 */
	public static void initObServer(ObMessage obMessage) {
		// 创建一个监听者
		Notifier goodNotifier = new GoodNotifier();
		obMessage.setThisPointSta(PileConstantsInfo.THIS_POINT_0);
		obMessage.setThisPointMa(PileConstantsInfo.THIS_POINT_0);
		// 桩
		PileObserver pObServer = new PileObserver();
		String[] pObServerKeys = { obMessage.getStationId(),
				obMessage.getPineId() };
		obMessage.setFindStatus(pObServerKeys);
		goodNotifier.addListener(pObServer, "updatePileRedis", obMessage,
				pObServerKeys);

		goodNotifier.notifyX();
	}

	/**
	 * 初始化观察者的参数
	 * 
	 * @param pileId
	 * @param rdao
	 * @return
	 */
	public static ObMessage initObMessage(String pileId, RedisClientDao rdao) {
		// 获取站的id
		String stationId = RedisUtli.getStationId(pileId, rdao);
		if(StringUtils.isNotBlank(stationId)) {
			// 获取原始桩状态
			String pilePsStr = rdao.hget(stationId, pileId);
			if (StringUtils.isNotBlank(pilePsStr)) {
				// 如果状态变化了 则进行修改操作 除工作状态 要更新soc的值
				PileStatus pilePsOld = new PileStatus();
				try {
					pilePsOld = (PileStatus) JsonUtil.genObjectFromJsonString(
							pilePsStr, PileStatus.class);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
				// 给观察者传递的参数
				ObMessage obMessage = new ObMessage();
				// 放入redis dao
				obMessage.setRdao(rdao);

				// 放入桩id
				obMessage.setPineId(pileId);
				// 放入站id
				obMessage.setStationId(stationId);
				// 放入机构码 省_市_区
				PileStationMechanism psmId = RedisUtli.getMechanismIds(pileId,
						stationId, rdao);
				obMessage.setMechanismCode(psmId);
				// 放入旧的桩状态
				obMessage.setOldPineStatus(pilePsOld);
				return obMessage;
			}
		}
		return null;
	}

	/**
	 * 更新原始工作状态
	 */
	public static ObMessage updataOldWorkStatus(ObMessage messagees) {
		StationStatus sta = messagees.getOldStationStatus();
		Map<String, String> StationSta = new HashMap<String, String>();
		if (messagees.getStationMap() != null) {
			StationSta = messagees.getStationMap();
		}
		// 原工作状态
		if(StringUtils.isNotBlank(messagees.getPileMap().get(
				PileConstantsInfo.CHANGE_STATUS_OLD))) {
			switch (Integer.parseInt(messagees.getPileMap().get(
					PileConstantsInfo.CHANGE_STATUS_OLD))) {
			// 工作状态 0001-故障
			case ConstantsInfo.WORKSTATUS_ALARM_INT:
				// 所有的故障数-1
				// 更新站下的信息
				sta.setFaultNum(sta.getFaultNum() - 1);
				StationSta.put(PileConstantsInfo.FAULTNUM_STATUS,
						PileConstantsInfo.REDUCE_1);
				break;
			// 工作状态 0002-待机 空闲
			case ConstantsInfo.WORKSTATUS_FREE_INT:
				// 所有的空闲数 -1
				// 更新站下的信息
				sta.setFreeNum(sta.getFreeNum() - 1);
				StationSta.put(PileConstantsInfo.FREENUM_STATUS,
						PileConstantsInfo.REDUCE_1);
				break;
			// 工作状态 0003-工作
			case ConstantsInfo.WORKSTATUS_WORK_INT:
				// 更新站下的信息
				sta.setChargeNum(sta.getChargeNum() - 1);
				StationSta.put(PileConstantsInfo.CHARGENUM_STATUS,
						PileConstantsInfo.REDUCE_1);
				break;
			// 工作状态 0004-离线
			case ConstantsInfo.WORKSTATUS_OFF_LINE_INT:
				// 所有的离线数 -1
				// 更新站下的信息
				sta.setOffNum(sta.getOffNum() - 1);
				StationSta.put(PileConstantsInfo.OFFNUM_STATUS,
						PileConstantsInfo.REDUCE_1);
				break;
			// 工作状态 0005-充满
			case ConstantsInfo.WORKSTATUS_FULL_INT:

				break;
			}
		}
		messagees.setOldStationStatus(sta);
		messagees.setStationMap(StationSta);
		return messagees;
	}

	/**
	 * 更新新的工作状态
	 */
	public static ObMessage updataNewWorkStatus(ObMessage messagees) {
		StationStatus sta = messagees.getOldStationStatus();
		Map<String, String> StationSta = new HashMap<String, String>();
		if (messagees.getStationMap() != null) {
			StationSta = messagees.getStationMap();
		}
		// 新工作状态
		if(StringUtils.isNotBlank(messagees.getPileMap().get(
				PileConstantsInfo.CHANGE_STATUS_NEW))) {
			switch (Integer.parseInt(messagees.getPileMap().get(
					PileConstantsInfo.CHANGE_STATUS_NEW))) {
			// 工作状态 0001-故障
			case ConstantsInfo.WORKSTATUS_ALARM_INT:
				// 所有的故障数+1
				// 更新站下的信息
				sta.setFaultNum(sta.getFaultNum() + 1);
				StationSta.put(PileConstantsInfo.FAULTNUM_STATUS,
						PileConstantsInfo.ADD_1);
				break;
			// 工作状态 0002-待机 空闲
			case ConstantsInfo.WORKSTATUS_FREE_INT:
				// 所有的空闲数 +1
				// 更新站下的信息
				sta.setFreeNum(sta.getFreeNum() + 1);
				StationSta.put(PileConstantsInfo.FREENUM_STATUS,
						PileConstantsInfo.ADD_1);
				//
				break;
			// 工作状态 0003-工作
			case ConstantsInfo.WORKSTATUS_WORK_INT:
				// 更新站下的信息
				sta.setChargeNum(sta.getChargeNum() + 1);
				StationSta.put(PileConstantsInfo.CHARGENUM_STATUS,
						PileConstantsInfo.ADD_1);
				break;
			// 工作状态 0004-离线
			case ConstantsInfo.WORKSTATUS_OFF_LINE_INT:
				// 所有的离线数 +1
				// 更新站下的信息
				sta.setOffNum(sta.getOffNum() + 1);
				StationSta.put(PileConstantsInfo.OFFNUM_STATUS,
						PileConstantsInfo.ADD_1);
				break;
			}
		}
		
		// 计算站的状态 一般告警 严重告警
		// 存原状态
		StationSta.put(PileConstantsInfo.CHANGE_STATUS_OLD,
				String.valueOf(sta.getAlarmState()));
		double rate = (double) sta.getFaultNum() / sta.getAllNum();
		log.info("rate-->" + rate);
		if (rate == PileConstantsInfo.STATION_FAULT_STATUS_RATE_0) {
			// 正常
			StationSta.put(PileConstantsInfo.CHANGE_STATUS_NEW,
					String.valueOf(PileConstantsInfo.STATION_FAULT_STATUS_0));
			sta.setAlarmState(PileConstantsInfo.STATION_FAULT_STATUS_0);
		} else if (rate >= PileConstantsInfo.STATION_FAULT_STATUS_RATE_05) {
			// 严重告警
			StationSta.put(PileConstantsInfo.CHANGE_STATUS_NEW,
					String.valueOf(PileConstantsInfo.STATION_FAULT_STATUS_2));
			sta.setAlarmState(PileConstantsInfo.STATION_FAULT_STATUS_2);
		} else {
			// 一般告警
			StationSta.put(PileConstantsInfo.CHANGE_STATUS_NEW,
					String.valueOf(PileConstantsInfo.STATION_FAULT_STATUS_1));
			sta.setAlarmState(PileConstantsInfo.STATION_FAULT_STATUS_1);
		}
		messagees.setOldStationStatus(sta);
		messagees.setStationMap(StationSta);
		return messagees;
	}

	/**
	 * 更新原始故障状态
	 */
	public static ObMessage updataOldFault(ObMessage messagees) {
		MechanismStatus ma = messagees.getOldMechStatus();
		Map<String, String> maSta = new HashMap<String, String>();
		if (messagees.getMechMap() != null) {
			maSta = messagees.getMechMap();
		}
		if(StringUtils.isNotBlank(maSta.get(PileConstantsInfo.CHANGE_STATUS_OLD))) {
			// 原工作状态
			switch (Integer
					.parseInt(maSta.get(PileConstantsInfo.CHANGE_STATUS_OLD))) {
			// 站告警状态 一般
			case PileConstantsInfo.STATION_FAULT_STATUS_1:
				// 一般告警数-1
				ma.setGa(ma.getGa() - 1);
				maSta.put(PileConstantsInfo.GA_STATUS, PileConstantsInfo.REDUCE_1);
				break;
			// 严重告警
			case PileConstantsInfo.STATION_FAULT_STATUS_2:
				// 严重告警数 -1
				ma.setCa(ma.getCa() - 1);
				maSta.put(PileConstantsInfo.CA_STATUS, PileConstantsInfo.REDUCE_1);
				break;
			}
		}
		
		messagees.setOldMechStatus(ma);
		messagees.setMechMap(maSta);
		return messagees;
	}

	/**
	 * 更新新的故障状态
	 */
	public static ObMessage updataNewFault(ObMessage messagees) {
		// 新工作状态
		MechanismStatus ma = messagees.getOldMechStatus();
		Map<String, String> maSta = new HashMap<String, String>();
		if (messagees.getMechMap() != null) {
			maSta = messagees.getMechMap();
		}
		if(StringUtils.isNoneBlank(maSta.get(PileConstantsInfo.CHANGE_STATUS_NEW))) {
			switch (Integer
					.parseInt(maSta.get(PileConstantsInfo.CHANGE_STATUS_NEW))) {
			// 站告警状态 一般
			case PileConstantsInfo.STATION_FAULT_STATUS_1:
				// 一般告警数+1
				ma.setGa(ma.getGa() + 1);
				maSta.put(PileConstantsInfo.GA_STATUS, PileConstantsInfo.ADD_1);
				break;
			// 严重告警
			case PileConstantsInfo.STATION_FAULT_STATUS_2:
				// 严重告警数 +1
				ma.setCa(ma.getCa() + 1);
				maSta.put(PileConstantsInfo.CA_STATUS, PileConstantsInfo.ADD_1);
				break;
			}
		}
		messagees.setOldMechStatus(ma);
		messagees.setMechMap(maSta);
		return messagees;
	}

	/**
	 * 根据桩id获取站id
	 * 
	 * @param pileId
	 * @param redisClientDAO
	 * @return
	 */
	public static String getStationId(String pileId,
			RedisClientDao redisClientDAO) {
		// 根据桩id 获取站信息
		Set<String> stationSet = redisClientDAO.hkeys(pileId);
		// 站id
		String station = null;
		if (stationSet != null && stationSet.size() > 0) {
			station = (String) stationSet.toArray()[0];
		}
		log.info("桩id-->" + pileId);
		log.info("站id-->" + station);
		return station;
	}

	/**
	 * 根据 桩id和站is获取机构id
	 * 
	 * @param pileId
	 * @param station
	 * @param redisClientDAO
	 * @return
	 */
	public static PileStationMechanism getMechanismIds(String pileId,
			String station, RedisClientDao redisClientDAO) {
		String mechanismIds = redisClientDAO.hget(pileId, station);
		// 机构信息
		String[] mids = mechanismIds.split("_");
		PileStationMechanism mMap = new PileStationMechanism();
		mMap.setProvinceCode(mids[0]);
		mMap.setCityCode(mids[1]);
		mMap.setDistrictCode(mids[2]);
		return mMap;
	}

}
