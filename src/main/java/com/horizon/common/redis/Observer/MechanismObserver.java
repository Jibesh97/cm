package com.horizon.common.redis.Observer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.horizon.common.base.RedisClientDao;
import com.horizon.common.base.Observer.GoodNotifier;
import com.horizon.common.base.Observer.Notifier;
import com.horizon.common.redis.Observer.constants.PileConstantsInfo;
import com.horizon.common.redis.Observer.util.RedisUtli;
import com.horizon.common.redis.model.MechanismStatus;
import com.horizon.common.util.JsonUtil;
import com.horizon.common.vo.StationVO;

/**
 * 机构状态 观察者
 * 
 * @author liy
 * 
 */
public class MechanismObserver {
	private static Logger log = Logger.getLogger(PileObserver.class);

	public void updatePileRedis(ObMessage messagees, String[] Keys) {
		log.info("机构状态 观察者");
		try {
			// 获取对应站的信息
			String disStaPsStr = messagees.getRdao().get(Keys[0]);
			log.info("原机构-->" + disStaPsStr);
			log.info("原机构key-->" + Keys[0]);
			if (StringUtils.isNotBlank(disStaPsStr)) {
				MechanismStatus ma = (MechanismStatus) JsonUtil
						.genObjectFromJsonString(disStaPsStr,
								MechanismStatus.class);
				messagees.setOldMechStatus(ma);
				// 区县机构的需要根据区县站的信息更新自己
				if (PileConstantsInfo.THIS_POINT_2 == messagees
						.getThisPointMa()) {
					messagees = changeDownStatus(messagees);
				} else {
					// 根据区县机构更新上级机构
					messagees = changeUpStatus(messagees);
				}
				disStaPsStr = JsonUtil.genJsonString(messagees
						.getOldMechStatus());
				log.info("新机构-->" + disStaPsStr);
				// 更新所有redis里的状态
				messagees.getRdao().set(Keys[0], disStaPsStr);
				// 当改变时 通知上级
				createNotifier(messagees);
			}
		} catch (Exception e) {

			log.error(e.getMessage());
		}
	}
	
	/**
	 * 系统初始化时，初始站状态改变时  机构的站总+1
	 * @param messagees
	 */
	public void updateInitStationData(ObMessage messagees) {
		//省市县的站总都+1
		//新增站的状态
		StationVO svo = messagees.getSvo();
		RedisClientDao rDao = messagees.getRdao();
		try {
			//区县机构
			//获取状态
			stationAdd1ByKey(svo.getDistrictCode(),rDao);
			//市机构
			//获取状态
			stationAdd1ByKey(svo.getCityCode(),rDao);
			//省机构
			//获取状态
			stationAdd1ByKey(svo.getProvinceCode(),rDao);
		} catch (Exception e) {
			log.error(e.getMessage());
		} 
	}
	
	/**
	 * 系统初始化时，初始桩状态
	 * @param messagees
	 */
	public void updateInitPileData(ObMessage messagees,String[] Keys) {
		try {
			// 获取对应站的信息
			String disStaPsStr = messagees.getRdao().get(Keys[0]);
			log.info("原机构-->" + disStaPsStr);
			log.info("原机构key-->" + Keys[0]);
			if (StringUtils.isNotBlank(disStaPsStr)) {
				MechanismStatus ma = (MechanismStatus) JsonUtil
						.genObjectFromJsonString(disStaPsStr,
								MechanismStatus.class);
				ma.setAllNum(ma.getAllNum() + 1);
				disStaPsStr = JsonUtil.genJsonString(ma);
				log.info("新机构-->" + disStaPsStr);
				// 更新所有redis里的状态
				messagees.getRdao().set(Keys[0], disStaPsStr);
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
		switch (messagees.getThisPointMa()) {
		case 1:
			// 设置观察者级别
			messagees.setThisPointMa(PileConstantsInfo.THIS_POINT_2);
			// 机构 市
			MechanismObserver mObServerCity = new MechanismObserver();
			String[] mObServerCityKeys = { messagees.getPvo()
					.getCityCode() };
			messagees.setFindStatus(mObServerCityKeys);
			goodNotifier.addListener(mObServerCity, "updateInitPileData",
					messagees, mObServerCityKeys);
			break;
		case 2:
			// 设置观察者级别
			messagees.setThisPointMa(PileConstantsInfo.THIS_POINT_3);
			// 机构 省
			MechanismObserver mObServerPro = new MechanismObserver();
			String[] mObServerProKeys = { messagees.getPvo()
					.getProvinceCode() };
			messagees.setFindStatus(mObServerProKeys);
			goodNotifier.addListener(mObServerPro, "updateInitPileData",
					messagees, mObServerProKeys);
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
		switch (messagees.getThisPointMa()) {
		case 2:
			// 设置观察者级别
			messagees.setThisPointMa(PileConstantsInfo.THIS_POINT_3);
			// 机构 市
			MechanismObserver mObServerCity = new MechanismObserver();
			String[] mObServerCityKeys = { messagees.getMechanismCode()
					.getCityCode() };
			messagees.setFindStatus(mObServerCityKeys);
			goodNotifier.addListener(mObServerCity, "updatePileRedis",
					messagees, mObServerCityKeys);
			break;
		case 3:
			// 设置观察者级别
			messagees.setThisPointMa(PileConstantsInfo.THIS_POINT_4);
			// 机构 省
			MechanismObserver mObServerPro = new MechanismObserver();
			String[] mObServerProKeys = { messagees.getMechanismCode()
					.getProvinceCode() };
			messagees.setFindStatus(mObServerProKeys);
			goodNotifier.addListener(mObServerPro, "updatePileRedis",
					messagees, mObServerProKeys);
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
		MechanismStatus ma = messagees.getOldMechStatus();
		// 区县站的更改信息
		Map<String, String> map = messagees.getStationMap();
		// 区县机构的更改信息 给机构的上级使用
		Map<String, String> maMap = new HashMap<String, String>();

		Iterator<Entry<String, String>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Entry<String, String> entry = entries.next();
			log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			if (entry.getKey().equals(PileConstantsInfo.FAULTNUM_STATUS)) {
				// 告警数
				ma.setFaultNum(ma.getFaultNum()
						+ Integer.parseInt(entry.getValue()));
				maMap.put(PileConstantsInfo.OFFNUM_STATUS, entry.getValue());
			} else if (entry.getKey().equals(PileConstantsInfo.OFFNUM_STATUS)) {
				// 离线数
				ma.setOffNum(ma.getOffNum()
						+ Integer.parseInt(entry.getValue()));
				maMap.put(PileConstantsInfo.FAULTNUM_STATUS, entry.getValue());
			} else if (entry.getKey().equals(PileConstantsInfo.PILE_ALL_NUM)) {
				// 桩总数
				ma.setAllNum(ma.getAllNum()
						+ Integer.parseInt(entry.getValue()));
				maMap.put(PileConstantsInfo.PILE_ALL_NUM, entry.getValue());
			}
		}
		messagees.setMechMap(map);
		messagees.setOldMechStatus(ma);
		// 告警状态
		// 告警状态发生变化
		if (!map.get(PileConstantsInfo.CHANGE_STATUS_OLD).equals(
				map.get(PileConstantsInfo.CHANGE_STATUS_NEW))) {
			messagees = RedisUtli.updataOldFault(messagees);
			messagees = RedisUtli.updataNewFault(messagees);
		}
		return messagees;
	}

	/**
	 * 根据下级站的信息更新上级
	 * 
	 * @param messagees
	 * @return
	 */
	public ObMessage changeUpStatus(ObMessage messagees) {
		MechanismStatus ma = messagees.getOldMechStatus();
		// 区县机构的更改信息 给机构的上级使用
		Map<String, String> maMap = messagees.getMechMap();

		Iterator<Entry<String, String>> entries = maMap.entrySet().iterator();
        //根据下级信息变化 更改上级信息
		while (entries.hasNext()) {
			Entry<String, String> entry = entries.next();
			log.info("Key = " + entry.getKey() + ", Value = "+ entry.getValue());
			if (entry.getKey().equals(PileConstantsInfo.FAULTNUM_STATUS)) {
				// 告警数
				ma.setFaultNum(ma.getFaultNum()
						+ Integer.parseInt(entry.getValue()));
			} else if (entry.getKey().equals(PileConstantsInfo.OFFNUM_STATUS)) {
				// 离线数
				ma.setOffNum(ma.getOffNum()
						+ Integer.parseInt(entry.getValue()));
			} else if (entry.getKey().equals(PileConstantsInfo.GA_STATUS)) {
				// 一般告警数
				ma.setGa(ma.getGa() + Integer.parseInt(entry.getValue()));
			} else if (entry.getKey().equals(PileConstantsInfo.CA_STATUS)) {
				// 严重告警数
				ma.setCa(ma.getCa() + Integer.parseInt(entry.getValue()));
			}
		}
		messagees.setOldMechStatus(ma);

		return messagees;
	}
    /**
     * 根据key 把机构的站总+1
     * @param key
     * @param rDao
     */
	public void stationAdd1ByKey(String key,RedisClientDao rDao) {
		//获取原数据
		String disStr = rDao.get(key);
		if(StringUtils.isNotBlank(disStr)) {
			MechanismStatus me = null;
			try {
				me = (MechanismStatus)JsonUtil.genObjectFromJsonString(disStr, MechanismStatus.class);
			} catch (JsonGenerationException e) {
				log.error(e.getMessage());
			} catch (JsonMappingException e) {
				log.error(e.getMessage());
			} catch (IOException e) {
				log.error(e.getMessage());
			}
			//站总+1
			me.setTsn(me.getTsn() + 1);
			//保存新数据
			rDao.set(key, JsonUtil.genJsonString(me));
		}
	}

}
