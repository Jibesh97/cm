package com.horizon.common.hsf;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.horizon.common.base.RedisClientDao;
import com.horizon.common.base.Observer.GoodNotifier;
import com.horizon.common.base.Observer.Notifier;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.common.hsf.constants.HSFConstantsInfo;
import com.horizon.common.hsf.model.PileMonitorInitDataVO;
import com.horizon.common.redis.Observer.MechanismObserver;
import com.horizon.common.redis.Observer.ObMessage;
import com.horizon.common.redis.Observer.PileObserver;
import com.horizon.common.redis.Observer.StationObserver;
import com.horizon.common.redis.Observer.constants.PileConstantsInfo;
import com.horizon.common.redis.Observer.util.RedisUtli;
import com.horizon.common.redis.model.MechanismStatus;
import com.horizon.common.redis.model.PileStatus;
import com.horizon.common.redis.model.StationStatus;
import com.horizon.common.util.JsonUtil;
import com.horizon.common.vo.OrgTreeVO;
import com.horizon.common.vo.PileHsfVO;
import com.horizon.common.vo.ResPileVO;
import com.horizon.common.vo.ResStationVO;
import com.horizon.common.vo.RespVO;
import com.horizon.common.vo.StationVO;
import com.send.edas.property.service.ISendStakeService;
import com.send.edas.property.service.ISendStationService;
import com.send.org.service.OrgInfoGetter;

/**
 * <P>
 * 初始化redis里的机构树
 * 
 * 
 */
public class OrgTreeInitData {

	/**
	 * 用于输出log
	 */
	private static Logger log = Logger.getLogger(OrgTreeInitData.class);

	public static class InitMap {
		/**
		 * 初始化redis信息
		 */
		public static void init(final ApplicationContext ctx) {
			// 获取redis dao
			RedisClientDao rDao = (RedisClientDao) ctx
					.getBean("redisClientDao");
			// 机构接口
			OrgInfoGetter orgInfoGetter = (OrgInfoGetter) ctx
					.getBean("orgInfoGetter");
			// 站接口
			ISendStationService stationService = (ISendStationService) ctx
					.getBean("C1");
			// 桩接口
			ISendStakeService pileService = (ISendStakeService) ctx
					.getBean("C2");

			// String pId = Config.props.getProperty("interfaces.hdfsRoot");
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
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			//查询成功
			if(rvo.getCode() == HSFConstantsInfo.RES_CODE) {
				if (rvo.getData() != null) {
					organizationVO = rvo.getData().getSubOrganization();
				}
				// 循环各省，更新各省的机构 站 桩信息
				for (OrgTreeVO org : organizationVO) {
					if(org.getOuCode().equals("210000")) {
						// 初始化一个省的机构信息
						exeUpdateRedis(rDao, org.getOuCode(), orgInfoGetter);
						// 初始化一个省的站桩信息
						getAllProStation(org, rDao, stationService, pileService);
					}
				}
			} else {
				log.info("从机构相关接口返回数据");
				log.info(rvo.getError());
			}
			
		}

		/**
		 * 初始化一个省的机构信息
		 * 
		 * @param ctx
		 * @param proId
		 * @param orgInfoGetter
		 */
		public static void exeUpdateRedis(RedisClientDao rDao, String proId,
				OrgInfoGetter orgInfoGetter) {

			/********************** 更新机构和机构状态信息 *************************/
			// 获取机构数据
			String org = orgInfoGetter.getOrgs(null, proId);
			log.info("机构---------->" + org);
			// List<OrgTreeVO> organizationVO = new ArrayList<>();
			OrgTreeVO proOrgTreeVO = new OrgTreeVO();
			try {
				//
				RespVO vo = (RespVO) JsonUtil.genObjectFromJsonString(org,
						RespVO.class);
				if (vo != null) {
					proOrgTreeVO = vo.getData();
				}
			} catch (JsonGenerationException e) {
				log.error(e.getMessage());
			} catch (JsonMappingException e) {
				log.error(e.getMessage());
			} catch (IOException e) {
				log.error(e.getMessage());
			}
			// 省级
			if (proOrgTreeVO != null) {
				// 添加新的机构信息
				updateNewData(rDao, proOrgTreeVO);
				// 市级
				List<OrgTreeVO> cityOrgTreeVO = proOrgTreeVO
						.getSubOrganization();
				if (cityOrgTreeVO != null) {
					for (OrgTreeVO cityOrgVO : cityOrgTreeVO) {
						// 添加新的机构信息
						updateNewData(rDao, cityOrgVO, proOrgTreeVO);
						// 区县级
						List<OrgTreeVO> disOrgTreeVO = cityOrgVO
								.getSubOrganization();
						if (disOrgTreeVO != null) {
							for (OrgTreeVO disOrgVO : disOrgTreeVO) {
								// 添加新的机构信息
								updateNewData(rDao, disOrgVO, cityOrgVO,
										proOrgTreeVO);
							}
						}

					}
				}

			}
		}

		/**
		 * 收集机构状态的map
		 * 
		 * @param mapStatus
		 *            机构状态的MAP
		 * @param map
		 *            机构的MAP
		 * @param orgVO
		 * @return
		 */
		public static void updateNewData(RedisClientDao rDao,
				OrgTreeVO... orgVO) {
			// Map<String,Map<String,String>> allMap = new HashMap<String,
			// Map<String,String>>();
			// 省机构状态
			MechanismStatus me = new MechanismStatus();
			// 省名
			me.setMechanismName(orgVO[0].getOuName());
			if (orgVO[0].getOuType() == HSFConstantsInfo.UO_TYPE_PRO) {
				// 省编码
				me.setProvinceCode(orgVO[0].getOuCode());
			} else if (orgVO[0].getOuType() == HSFConstantsInfo.UO_TYPE_CITY) {
				// 市 省
				// 市编码
				me.setCityCode(orgVO[0].getOuCode());
				// 省编码
				me.setProvinceCode(orgVO[1].getOuCode());
				// 更新redis 省_C -->市
				rDao.rpush(orgVO[1].getOuCode() + ConstantsInfo.CHILD_SUF,
						orgVO[0].getOuCode());

			} else {
				// 区 市 省
				// 区编码
				me.setDistrictCode(orgVO[0].getOuCode());
				// 市编码
				me.setCityCode(orgVO[1].getOuCode());
				// 省编码
				me.setProvinceCode(orgVO[2].getOuCode());
				// 更新
				// 市_C --> 区
				rDao.rpush(orgVO[1].getOuCode() + ConstantsInfo.CHILD_SUF,
						orgVO[0].getOuCode());
			}
			// 将机构状态更新到redis里
			String newMe = JsonUtil.genJsonString(me);
			rDao.set(orgVO[0].getOuCode(), newMe);
		}

		public static void getAllProStation(OrgTreeVO org, RedisClientDao rDao,
				ISendStationService stationService,
				ISendStakeService pileService) {
			// 统计省下有多少站
			// 某省下的所有站
			log.info("查询站所用的机构id --------->" + org.getOuCode());
			String json = stationService
					.getStation(org.getOuCode(), null, null);
			log.info("站 --------->" + json);
			List<StationVO> oneStations = new ArrayList<StationVO>();
			ResStationVO staVo = new ResStationVO();
			try {
				 staVo = (ResStationVO) JsonUtil
						.genObjectFromJsonString(json, ResStationVO.class);
			} catch (JsonGenerationException e) {
				log.error(e.getMessage());
			} catch (JsonMappingException e) {
				log.error(e.getMessage());
			} catch (IOException e) {
				log.error(e.getMessage());
			}
			
			//查询成功
			if(staVo.getErrorCode() == HSFConstantsInfo.RES_CODE) {
				oneStations = staVo.getData();
				for (StationVO sta : oneStations) {
					// redis添加站的信息
					addStationStatusRed(sta, rDao);

					List<PileHsfVO> onePiles = new ArrayList<PileHsfVO>();
					// 通过站的uuid找桩
					String pileFshStr = pileService.getStake(sta.getUuid(), null,
							null, null);
					log.info("桩----->" + pileFshStr);
					ResPileVO vo = new ResPileVO();
					try {
						 vo = (ResPileVO) JsonUtil
								.genObjectFromJsonString(pileFshStr,
										ResPileVO.class);
					} catch (JsonGenerationException e) {
						log.error(e.getMessage());
					} catch (JsonMappingException e) {
						log.error(e.getMessage());
					} catch (IOException e) {
						log.error(e.getMessage());
					}
					//查询成功
					if(staVo.getErrorCode() == HSFConstantsInfo.RES_CODE) {
						onePiles = vo.getData();
						// 所有桩
						for (PileHsfVO pvo : onePiles) {
							// redis添加桩的信息
							addPileStatusRed(pvo, rDao, sta.getUuid());
						}
					} else {
						log.info("从桩相关接口返回数据");
						log.info(vo.getErrorMsg());
					}
				}
			} else {
				log.info("从站相关接口返回数据");
				log.info(staVo.getErrorMsg());
			}
			
		}

		/**
		 * 新增redis里站的状态
		 * 
		 * @param sta
		 */
		public static void addStationStatusRed(StationVO sta,
				RedisClientDao rDao) {
			StationStatus stationSta = new StationStatus();
			// 充电站名称
			stationSta.setStationName(sta.getName());
			// 站状态 0 正常
			stationSta.setAlarmState(PileConstantsInfo.STATION_FAULT_STATUS_0);
			// 站坐标
			stationSta.setLat(sta.getLat());
			stationSta.setLng(sta.getLng());
			ObMessage messagees = new ObMessage();
			// 封装站值
			messagees.setOldStationStatus(stationSta);
			messagees.setStationId(sta.getUuid());
			messagees.setSvo(sta);
			messagees.setRdao(rDao);
			// 监听并更新上级状态
			createNotifier(messagees);
		}

		/**
		 * 新增redis里桩的状态
		 * 
		 * @param sta
		 */
		public static void addPileStatusRed(PileHsfVO pvo, RedisClientDao rDao,
				String stationId) {
			PileStatus ps = new PileStatus();
			// 桩编号
			ps.setPileCode(pvo.getUuid());
			// 桩名
			ps.setPileName(pvo.getName());
			// 桩电源pvo.getMeasureModel()
			ps.setPs(pvo.getMeasureModel());
			// 桩分组
			ps.setGroupName(pvo.getStakeGroup());
			Long tim = System.currentTimeMillis();
			// Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");// 可以方便地修改日期格式
			String NewDate = dateFormat.format(tim);
			ps.setTime(Integer.parseInt(NewDate));
			// 时间
			ObMessage messagees = new ObMessage();
			// 桩所在的站id
			messagees.setStationId(stationId);
			// 封装站值
			messagees.setOldPineStatus(ps);
			messagees.setPvo(pvo);
			messagees.setRdao(rDao);
			// 监听并更新上级状态
			createNotifierPile(messagees);
		}

		/**
		 * 创建监听者 当站的信息改变时，通知站状态（站区县、站机构 区县）改变值
		 */
		public static void createNotifier(ObMessage messagees) {
			// 创建监听者
			Notifier goodNotifier = new GoodNotifier();
			// 设置观察者级别
			messagees.setThisPointSta(PileConstantsInfo.THIS_POINT_1);
			// 设置观察者级别
			messagees.setThisPointMa(PileConstantsInfo.THIS_POINT_1);
			// 站 区县
			StationObserver sObjServerDis = new StationObserver();
			goodNotifier.addListener(sObjServerDis, "updateInitStationData",
					messagees);
			// 机构 区县
			MechanismObserver mObServerDis = new MechanismObserver();
			goodNotifier.addListener(mObServerDis, "updateInitStationData",
					messagees);
			// 通知有改变
			goodNotifier.notifyX();
		}

		/**
		 * 创建监听者 当桩的信息更新时，更改上级的信息
		 */
		public static void createNotifierPile(ObMessage messagees) {
			// 创建监听者
			Notifier goodNotifier = new GoodNotifier();
			// 站 区县
			PileObserver sObjServerDis = new PileObserver();
			goodNotifier.addListener(sObjServerDis, "updateInitPileData",
					messagees);
			// 通知有改变
			goodNotifier.notifyX();
		}

		@SuppressWarnings("unchecked")
		public static Map<String, String> searchPileStatus(
				String chargingPileNum, String satusType) {
			String json = "{\"sampTime\":\"201705\",\"chargingPileNum\":\"a6b4fefd0f4040e19c202223e29e2e3a\", \"chargingPileInterfaceNum\":\"0\", \"conncetFlag\":\"0\", \"chargingGunFlag\":\"0\", \"electronicLockStatus\":\"0\", \"dcOutputContactorStatus\":\"0\", \"workStatus\":\"0002\", \"outputVoltage\":\"220.2\", \"outputCurrent\":\"5.11\", \"demandVoltage\":\"220.2\", \"demandCurrent\":\"5.11\", \"chargeMode\":\"1\", \"soc\":\"10\", \"batteryType\":\"1\", \"minimumBatteryTemperature\":\"10.5\", \"maximumBatteryTemperature\":\"50.5\", \"cumulativeChargeTime\":\"10\", \"estimatedFullChargeTime\":\"90\", \"maximumBatteryVoltage\":\"110.11\", \"maximumBatteryVoltage\":\"78.88\", \"totalActivePower\":\"10.5\", \"electricityConsumptionAmount\":\"10.1234\", \"serviceFee\":\"10.1234\", \"chargingType\":\"1\", \"userIdentification\":\"111\", \"tariffModelNumber\":\"1001\", \"serviceChargeModelNumber\":\"2001\", \"batch\":\"100001\"}";
			// pileService.updatePileRedis(json);
			Map<String, String> map = null;
			try {
				map = (Map<String, String>) JsonUtil.genObjectFromJsonString(
						json, Map.class);
			} catch (Exception e) {

			}
			return map;
		}

		public static List<OrgTreeVO> organizationVO() {
			List<OrgTreeVO> organizationVO = new ArrayList<OrgTreeVO>();
			OrgTreeVO dist = new OrgTreeVO();
			dist.setOuCode("210423");
			dist.setOuName("清原满族自治县");
			dist.setOuType(4);
			dist.setParentOuCode("210400");
			List<OrgTreeVO> disl = new ArrayList<OrgTreeVO>();
			disl.add(dist);

			OrgTreeVO dist1 = new OrgTreeVO();
			dist1.setOuCode("210404");
			dist1.setOuName("望花区");
			dist1.setOuType(4);
			dist1.setParentOuCode("210400");
			disl.add(dist1);

			/*
			 * OrgTreeVO dist2 = new OrgTreeVO(); dist2.setOuCode("210102");
			 * dist2.setOuName("和平区"); dist2.setOuType(4);
			 * dist2.setParentOuCode("210000"); disl.add(dist2);
			 */

			OrgTreeVO cityt = new OrgTreeVO();
			cityt.setOuCode("210400");
			cityt.setOuName("抚顺市");
			cityt.setOuType(3);
			cityt.setParentOuCode("210000");
			cityt.setSubOrganization(disl);
			List<OrgTreeVO> cityl = new ArrayList<OrgTreeVO>();
			cityl.add(cityt);
			/**
			 * OrgTreeVO cityt1 = new OrgTreeVO(); cityt1.setOuCode("210100");
			 * cityt1.setOuName("沈阳市"); cityt1.setOuType(3);
			 * cityt1.setParentOuCode("210000");
			 * cityt1.setSubOrganization(disl); cityl.add(cityt1);
			 */

			OrgTreeVO prol = new OrgTreeVO();
			prol.setOuCode("210000");
			prol.setOuName("辽宁省");
			prol.setOuType(2);
			prol.setSubOrganization(cityl);
			organizationVO.add(prol);
			return organizationVO;
		}

		public static List<StationVO> stationVO(String orgUuid, String offset,
				String limit) {
			List<StationVO> organizationVO = new ArrayList<StationVO>();
			StationVO svo = new StationVO();
			svo.setDistrictCode("210423");
			svo.setCityCode("210400");
			svo.setProvinceCode("210000");
			svo.setLat("200");
			svo.setLng("300");
			svo.setName("清原充电站");
			svo.setUuid("100200300400500");
			svo.setOrg("210423");
			organizationVO.add(svo);

			StationVO svo1 = new StationVO();
			svo1.setDistrictCode("210404");
			svo1.setCityCode("210400");
			svo1.setProvinceCode("210000");
			svo1.setLat("200");
			svo1.setLng("300");
			svo1.setName("望花站");
			svo1.setUuid("600700800900000");
			svo1.setOrg("210404");
			organizationVO.add(svo1);

			return organizationVO;

		}

		public static List<PileHsfVO> PileHsfVO(String orgUuid, String offset,
				String limit, String aa) {
			List<PileHsfVO> organizationVO = new ArrayList<PileHsfVO>();
			if (orgUuid.equals("100200300400500")) {
				PileHsfVO pvo = new PileHsfVO();
				pvo.setUuid("a00b00c00d00e00");
				pvo.setName("充电桩A");
				pvo.setMeasureModel("AC");
				pvo.setDistrictCode("210423");
				pvo.setStationId("100200300400500");
				pvo.setCityCode("210400");
				pvo.setProvinceCode("210000");
				organizationVO.add(pvo);
			}

			if (orgUuid.equals("100200300400500")) {

				PileHsfVO pvo1 = new PileHsfVO();
				pvo1.setUuid("aa0bb0cc0dd0ee0");
				pvo1.setName("充电桩B");
				pvo1.setMeasureModel("DC");
				pvo1.setDistrictCode("210423");
				pvo1.setCityCode("210400");
				pvo1.setProvinceCode("210000");
				pvo1.setStationId("100200300400500");
				organizationVO.add(pvo1);
			}
			if (orgUuid.equals("600700800900000")) {
				PileHsfVO pvo2 = new PileHsfVO();
				pvo2.setUuid("aa00bb00cc00dd00ee00");
				pvo2.setName("充电桩AA");
				pvo2.setMeasureModel("AC");
				pvo2.setDistrictCode("210404");
				pvo2.setCityCode("210400");
				pvo2.setProvinceCode("210000");
				pvo2.setStationId("aa00bb00cc00dd00ee00");
				organizationVO.add(pvo2);
			}
			return organizationVO;
		}

		public static void updatePileRedisInit(
				Map<String, String> searchPileStatusData, RedisClientDao rDao) {
			try {
				// 直流充电桩充电监测信息
				String newMess = JsonUtil.genJsonString(searchPileStatusData);
				PileMonitorInitDataVO on = (PileMonitorInitDataVO) JsonUtil
						.genObjectFromJsonString(newMess,
								PileMonitorInitDataVO.class);
				// 初始化观察者的参数
				ObMessage obMessage = RedisUtli.initObMessage(
						on.getChargingPileNum(), rDao);
				if (obMessage != null) {
					// 重新封装新值
					PileStatus pilePsNew = new PileStatus();
					pilePsNew.setTime(on.getSampTime());
					pilePsNew.setRunSta(on.getWorkStatus());
					pilePsNew.setSoc(on.getSoc());
					// 放入新的桩状态
					obMessage.setPineStatus(pilePsNew);
				}
				// 初始化观察者对象们
				RedisUtli.initObServer(obMessage);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
	}

}
