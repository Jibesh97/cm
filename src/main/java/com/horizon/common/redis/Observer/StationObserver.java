package com.horizon.common.redis.Observer;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import com.horizon.common.base.RedisClientDao;
import com.horizon.common.base.Observer.GoodNotifier;
import com.horizon.common.base.Observer.Notifier;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.common.redis.Observer.constants.PileConstantsInfo;
import com.horizon.common.redis.Observer.util.RedisUtli;
import com.horizon.common.redis.model.StationStatus;
import com.horizon.common.util.JsonUtil;
import com.horizon.common.vo.StationVO;

/**
 * 站观察者
 * 
 * @author liy
 * 
 */
public class StationObserver {
	private static Logger log = Logger.getLogger(PileObserver.class);

	// 更新区县站状态
	public void updatePileRedis(ObMessage messagees, String[] Keys) {
		log.info("站观察者");
		try {
			// 获取对应站的信息
			String disStaPsStr = messagees.getRdao().hget(Keys[0], Keys[1]);
			log.info("原站-->" + disStaPsStr);
			log.info("原站key-->" + Keys[0] + "--" + Keys[1]);
			if (StringUtils.isNotBlank(disStaPsStr)) {
				StationStatus sta = (StationStatus) JsonUtil
						.genObjectFromJsonString(disStaPsStr,
								StationStatus.class);
				messagees.setOldStationStatus(sta);
				// 区县站的值需要自己更新
				if (PileConstantsInfo.THIS_POINT_1 == messagees.getThisPointSta()) {
					// 更新原始工作状态
					messagees = RedisUtli.updataOldWorkStatus(messagees);
					// 更新新的工作状态
					messagees = RedisUtli.updataNewWorkStatus(messagees);
				} else {
					// 更新新信息
					// 省 市站 需要根据区县站 变化的值更新
					messagees = changeDownStatus(messagees);
				}
				disStaPsStr = JsonUtil.genJsonString(messagees
						.getOldStationStatus());
				log.info("新站-->" + disStaPsStr);
				// 更新所有redis里的状态
				messagees.getRdao().hset(Keys[0], Keys[1], disStaPsStr);
				// 当改变时 通知上级
				createNotifier(messagees);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	/**
	 * 系统初始化时，初始站状态
	 * @param messagees
	 */
	public void updateInitStationData(ObMessage messagees) {
		//新增站的状态
		StationStatus sta = messagees.getOldStationStatus();
		StationVO svo = messagees.getSvo();
		String staS = JsonUtil.genJsonString(sta);
		RedisClientDao rDao = messagees.getRdao();
		//区县站状态
		rDao.hset(svo.getDistrictCode() + ConstantsInfo.MECHANISM_STATION_STATUS_Z,svo.getUuid(),staS);
		//市站状态
		rDao.hset(svo.getCityCode() + ConstantsInfo.MECHANISM_STATION_STATUS_Z,svo.getUuid(),staS);
		//省站状态
		rDao.hset(svo.getProvinceCode() + ConstantsInfo.MECHANISM_STATION_STATUS_Z,svo.getUuid(),staS);
	}
	
	/**
	 * 系统初始化时，初始桩状态
	 * @param messagees
	 */
	public void updateInitPileData(ObMessage messagees,String[] Keys) {
		try {
			// 获取对应站的信息
			String disStaPsStr = messagees.getRdao().hget(Keys[0], Keys[1]);
			log.info("原站-->" + disStaPsStr);
			log.info("原站key-->" + Keys[0] + "--" + Keys[1]);
			if (StringUtils.isNotBlank(disStaPsStr)) {
				StationStatus sta = (StationStatus) JsonUtil
						.genObjectFromJsonString(disStaPsStr,
								StationStatus.class);
				//桩总 +1
				sta.setAllNum(sta.getAllNum() + 1);
				disStaPsStr = JsonUtil.genJsonString(sta);
				log.info("新站-->" + disStaPsStr);
				// 更新所有redis里的状态
				messagees.getRdao().hset(Keys[0], Keys[1], disStaPsStr);
				// 当改变时 通知上级
				createNotifierPile(messagees);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * 创建监听者 当改变时，就通知上级
	 */
	public void createNotifierPile(ObMessage messagees) {
		// 创建监听者
		Notifier goodNotifier = new GoodNotifier();
		switch (messagees.getThisPointSta()) {
		case 1:
			// 设置观察者级别
			messagees.setThisPointSta(PileConstantsInfo.THIS_POINT_2);
			// 站 市
			StationObserver sObjServerCity = new StationObserver();
			String[] sObjServerCityKeys = {
					messagees.getPvo().getCityCode()
							+ ConstantsInfo.MECHANISM_STATION_STATUS_Z,
					messagees.getStationId(), };
			messagees.setFindStatus(sObjServerCityKeys);
			goodNotifier.addListener(sObjServerCity, "updateInitPileData",
					messagees, sObjServerCityKeys);

			break;
		case 2:
			// 设置观察者级别
			messagees.setThisPointSta(PileConstantsInfo.THIS_POINT_3);
			// 站 省
			StationObserver sObjServerPro = new StationObserver();
			String[] sObjServerProKeys = {
					messagees.getPvo().getProvinceCode()
							+ ConstantsInfo.MECHANISM_STATION_STATUS_Z,
					messagees.getStationId(), };
			messagees.setFindStatus(sObjServerProKeys);
			goodNotifier.addListener(sObjServerPro, "updateInitPileData",
					messagees, sObjServerProKeys);
			break;

		}
		// 通知有改变
		goodNotifier.notifyX();
	}
	/**
	 * 创建监听者 当改变时，就通知上级
	 */
	public void createNotifier(ObMessage messagees) {
		// 创建监听者
		Notifier goodNotifier = new GoodNotifier();
		switch (messagees.getThisPointSta()) {
		case 1:
			// 设置观察者级别
			messagees.setThisPointSta(PileConstantsInfo.THIS_POINT_2);
			// 站 市
			StationObserver sObjServerCity = new StationObserver();
			String[] sObjServerCityKeys = {
					messagees.getMechanismCode().getCityCode()
							+ ConstantsInfo.MECHANISM_STATION_STATUS_Z,
					messagees.getStationId(), };
			messagees.setFindStatus(sObjServerCityKeys);
			goodNotifier.addListener(sObjServerCity, "updatePileRedis",
					messagees, sObjServerCityKeys);

			messagees.setThisPointMa(PileConstantsInfo.THIS_POINT_2);
			// 机构 区县
			MechanismObserver mObServerDis = new MechanismObserver();
			String[] mObServerDisKeys = { messagees.getMechanismCode()
					.getDistrictCode() };
			messagees.setFindStatus(mObServerDisKeys);
			goodNotifier.addListener(mObServerDis, "updatePileRedis",
					messagees, mObServerDisKeys);
			break;
		case 2:
			// 设置观察者级别
			messagees.setThisPointSta(PileConstantsInfo.THIS_POINT_3);
			// 站 省
			StationObserver sObjServerPro = new StationObserver();
			String[] sObjServerProKeys = {
					messagees.getMechanismCode().getProvinceCode()
							+ ConstantsInfo.MECHANISM_STATION_STATUS_Z,
					messagees.getStationId(), };
			messagees.setFindStatus(sObjServerProKeys);
			goodNotifier.addListener(sObjServerPro, "updatePileRedis",
					messagees, sObjServerProKeys);
			break;

		}
		// 通知有改变
		goodNotifier.notifyX();
	}

	/**
	 * 根据下级站的信息更新上级
	 * 
	 * @param messagees
	 * @return
	 */
	public ObMessage changeDownStatus(ObMessage messagees) {
		StationStatus sta = messagees.getOldStationStatus();
		Map<String, String> map = messagees.getStationMap();

		Iterator<Entry<String, String>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Entry<String, String> entry = entries.next();
			log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			if (entry.getKey().equals(PileConstantsInfo.FREENUM_STATUS)) {
				// 空闲数
				sta.setFreeNum(sta.getFreeNum()
						+ Integer.parseInt(entry.getValue()));
			} else if (entry.getKey()
					.equals(PileConstantsInfo.CHARGENUM_STATUS)) {
				// 充电数
				sta.setChargeNum(sta.getChargeNum()
						+ Integer.parseInt(entry.getValue()));
			} else if (entry.getKey().equals(PileConstantsInfo.FAULTNUM_STATUS)) {
				// 告警数
				sta.setFaultNum(sta.getFaultNum()
						+ Integer.parseInt(entry.getValue()));
			} else if (entry.getKey().equals(PileConstantsInfo.OFFNUM_STATUS)) {
				// 离线数
				sta.setOffNum(sta.getOffNum()
						+ Integer.parseInt(entry.getValue()));
			} else if (entry.getKey().equals(PileConstantsInfo.PILE_ALL_NUM)) {
				// 桩总数
				sta.setAllNum(sta.getAllNum()
						+ Integer.parseInt(entry.getValue()));
			}
			if (entry.getKey().equals(PileConstantsInfo.CHANGE_STATUS_NEW)) {
				// 告警状态
				// 告警状态发生变化
				if (!map.get(PileConstantsInfo.CHANGE_STATUS_OLD).equals(
						map.get(PileConstantsInfo.CHANGE_STATUS_NEW))) {
					sta.setAlarmState(Integer.parseInt(map
							.get(PileConstantsInfo.CHANGE_STATUS_NEW)));
				}
			}
		}
		messagees.setStationMap(map);
		messagees.setOldStationStatus(sta);
		return messagees;
	}
}
