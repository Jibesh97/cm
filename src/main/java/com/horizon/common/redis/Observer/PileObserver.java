package com.horizon.common.redis.Observer;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import com.horizon.common.base.RedisClientDao;
import com.horizon.common.base.Observer.GoodNotifier;
import com.horizon.common.base.Observer.Notifier;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.common.redis.Observer.constants.PileConstantsInfo;
import com.horizon.common.redis.model.PileStatus;
import com.horizon.common.util.JsonUtil;
import com.horizon.common.vo.PileHsfVO;

/**
 * 桩状态 观察者
 * 
 * @author liy
 * 
 */
public class PileObserver {
	private static Logger log = Logger.getLogger(PileObserver.class);

	public void updatePileRedis(ObMessage messagees, String[] Keys) {
		log.info("桩状态 观察者");
		try {
			// 获取对应站的信息
			String disStaPsStr = messagees.getRdao().hget(Keys[0], Keys[1]);
			log.info("原桩-->" + disStaPsStr);
			log.info("原桩key-->" + Keys[0] + "--" + Keys[1]);
			if (StringUtils.isNotBlank(disStaPsStr)) {
				// 更新桩信息
				messagees = countStatus(messagees);
				disStaPsStr = JsonUtil.genJsonString(messagees
						.getOldPineStatus());
				log.info("新桩-->" + disStaPsStr);
				// 更新所有redis里的状态
				messagees.getRdao().hset(Keys[0], Keys[1], disStaPsStr);
				// 当改变时 通知上级
				//if (!messagees.getPileMap().get(PileConstantsInfo.CHANGE_STATUS_OLD)
				//		.equals(messagees.getPileMap().get(
				//				PileConstantsInfo.CHANGE_STATUS_NEW))) {
					createNotifier(messagees);
				//}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	/**
	 * 新来一个桩时，添加桩的信息 并通知上级增加桩总
	 * @param messagees
	 */
	public  void updateInitPileData(ObMessage messagees) {
		//新增桩的状态
		PileStatus ps = messagees.getOldPineStatus();
		PileHsfVO pvo = messagees.getPvo();
		String staS = JsonUtil.genJsonString(ps);
		RedisClientDao rDao = messagees.getRdao();
		//保存桩信息
		rDao.hset(messagees.getStationId(),ps.getPileCode(),staS);
		//更新 桩 -> 站 -> 机构
		rDao.hset(ps.getPileCode(),messagees.getStationId(),pvo.getProvinceCode()+"_"+pvo.getCityCode()+"_"+pvo.getDistrictCode());
		Map<String,String> map = new HashMap<String, String>();
		map.put(PileConstantsInfo.PILE_ALL_NUM,PileConstantsInfo.ADD_1);
		messagees.setPileMap(map);
		//通知站和机构  桩+1
		createNotifierPile(messagees);
	}

	/**
	 * 创建监听者 当改变时，就通知上级
	 */
	public void createNotifier(ObMessage messagees) {
		// 创建监听者
		Notifier goodNotifier = new GoodNotifier();
		// 设置观察者级别
		messagees.setThisPointSta(PileConstantsInfo.THIS_POINT_1);
		messagees.setThisPointMa(PileConstantsInfo.THIS_POINT_1);
		// 站 区县
		StationObserver sObjServerDis = new StationObserver();
		String[] sObjServerDisKeys = {
				messagees.getMechanismCode().getDistrictCode()
						+ ConstantsInfo.MECHANISM_STATION_STATUS_Z,
				messagees.getStationId(), };
		messagees.setFindStatus(sObjServerDisKeys);
		goodNotifier.addListener(sObjServerDis, "updatePileRedis", messagees,
				sObjServerDisKeys);

		/*
		 * // 机构 区县 MechanismObserver mObServerDis = new MechanismObserver();
		 * String[] mObServerDisKeys = {
		 * messagees.getMechanismCode().getDistrictCode() };
		 * messagees.setFindStatus(mObServerDisKeys);
		 * goodNotifier.addListener(mObServerDis, "updatePileRedis", messagees,
		 * mObServerDisKeys);
		 */
		// 通知有改变
		goodNotifier.notifyX();
	}
	
	/**
	 * 创建监听者 当改变时，就通知上级
	 */
	public void createNotifierPile(ObMessage messagees) {
		// 设置观察者级别
		messagees.setThisPointSta(PileConstantsInfo.THIS_POINT_1);
		messagees.setThisPointMa(PileConstantsInfo.THIS_POINT_1);
		// 创建监听者
		Notifier goodNotifier = new GoodNotifier();
		// 机构 区县
		MechanismObserver mObServerDis = new MechanismObserver();
		String[] mObServerDisKeys = { messagees.getPvo().getDistrictCode() };
		messagees.setFindStatus(mObServerDisKeys);
		goodNotifier.addListener(mObServerDis, "updateInitPileData",
				messagees, mObServerDisKeys);
		
		
		// 站 区县
		StationObserver sObjServerDis = new StationObserver();
		String[] sObjServerDisKeys = {
				messagees.getPvo().getDistrictCode()
						+ ConstantsInfo.MECHANISM_STATION_STATUS_Z,
				messagees.getStationId(), };
		messagees.setFindStatus(sObjServerDisKeys);
		goodNotifier.addListener(sObjServerDis, "updateInitPileData", messagees,
				sObjServerDisKeys);
		// 通知有改变
		goodNotifier.notifyX();
	}

	public ObMessage countStatus(ObMessage message) {
		// 原桩信息
		PileStatus ps = message.getOldPineStatus();
		Map<String, String> pileSta = new HashMap<String, String>();
		// 如果前后状态一致，则置更新soc的时间
			if (!message.getPineStatus().getRunSta().equals(ps.getRunSta())) {
				/**
				 * 改成从新数据里取时间，目前时间没确定格式，
				 */
				//Date now = new Date();
				Long tim = System.currentTimeMillis();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");// 可以方便地修改日期格式
				String NewDate = dateFormat.format(tim);

				// 在一个月内的则+1 否则 清0
				// 更新桩下的信息
				if (NewDate.equals(String.valueOf(message.getOldPineStatus()
						.getTime()))) {
					switch (Integer.parseInt(message.getPineStatus().getRunSta())) {
					// 故障
					case ConstantsInfo.WORKSTATUS_ALARM_INT:
						// 故障次数+1
						ps.setFaultCount(ps.getFaultCount() + 1);
						break;
					// 离线
					case ConstantsInfo.WORKSTATUS_OFF_LINE_INT:
						// 离线次数 + 1
						ps.setOffCount(ps.getOffCount() + 1);
						break;
					// 充电
					case ConstantsInfo.WORKSTATUS_FULL_INT:
						ps.setChargeCount(ps.getChargeCount() + 1);
						break;
					}

				} else {
					ps.setFaultCount(0);
					ps.setOffCount(0);
					ps.setChargeCount(0);
				}
			}
			pileSta.put(PileConstantsInfo.CHANGE_STATUS_OLD, ps.getRunSta());
			pileSta.put(PileConstantsInfo.CHANGE_STATUS_NEW, message
					.getPineStatus().getRunSta());
		// 更新状态
		ps.setRunSta(message.getPineStatus().getRunSta());
		ps.setSoc(message.getPineStatus().getSoc());
		message.setOldPineStatus(ps);
		message.setPileMap(pileSta);

		return message;

	}

}
