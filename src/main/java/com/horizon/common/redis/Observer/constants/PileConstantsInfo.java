package com.horizon.common.redis.Observer.constants;

public class PileConstantsInfo {

	/************** 常用变量 ********************/

	/**
	 * 新状态的VO
	 */
	// public static final String ACCHARGING_PILE_MONITOR_ON = "on";

	/**
	 * 新状态的VO
	 */
	public static final String NEW_PILE_PS = "on";
	/**
	 * 区县站的状态
	 */
	public static final String DIS_STATION_PS = "disStationPs";
	/**
	 * 市站的状态
	 */
	public static final String CITY_STATION_PS = "cityStationPs";
	/**
	 * 省站的状态
	 */
	public static final String PRO_STATION_PS = "proStationPs";
	/**
	 * 区县的状态
	 */
	public static final String DIS_PS = "disPs";
	/**
	 * 市的状态
	 */
	public static final String CITY_PS = "cityPs";
	/**
	 * 省的状态
	 */
	public static final String PRO_PS = "proPs";
	/**
	 * 桩的状态
	 */
	public static final String PILE_PS = "pilePs";

	/**
	 * 桩id
	 */
	public static final String PILE_ID = "pileId";

	/**
	 * 站id
	 */
	public static final String STATION_ID = "stationId";

	/**
	 * 机构id
	 */
	public static final String MECHANISM_ID = "mechanismId";

	/**
	 * MQ的监控数据
	 */
	public static final String MQ_MONITOR = "mqMonitor";

	/**
	 * HSF的监控数据
	 */
	public static final String HSF_MONITOR = "hsfMonitor";

	/**
	 * 当前观察者 桩
	 */
	public static final int THIS_POINT_0 = 0;
	/**
	 * 当前观察者 区县
	 */
	public static final int THIS_POINT_1 = 1;
	/**
	 * 当前观察者 市
	 */
	public static final int THIS_POINT_2 = 2;
	/**
	 * 当前观察者 省
	 */
	public static final int THIS_POINT_3 = 3;
	/**
	 * 当前观察者 省
	 */
	public static final int THIS_POINT_4 = 4;

	/**
	 * 状态信息改变的值 原值
	 */
	public static final String CHANGE_STATUS_OLD = "changeStatusOld";
	/**
	 * 状态信息改变的值 新值
	 */
	public static final String CHANGE_STATUS_NEW = "changeStatusNew";
	/**
	 * 空闲数
	 */
	public static final String FREENUM_STATUS = "freenumStatus";
	/**
	 * 充电数
	 */
	public static final String CHARGENUM_STATUS = "chargenumStatus";
	/**
	 * 告警数 故障数
	 */
	public static final String FAULTNUM_STATUS = "faultnumStatus";
	/**
	 * 维护数
	 */
	public static final String MAINTAINNUM_STATUS = "maintainnumStatus";
	/**
	 * 离线数
	 */
	public static final String OFFNUM_STATUS = "offnumStatus";
	/**
	 * 告警状态
	 */
	public static final String ALARMSTATE_STATUS = "alarmstateStatus";
	/**
	 * 一般告警数
	 */
	public static final String GA_STATUS = "gaStatus";
	/**
	 * 严重告警数
	 */
	public static final String CA_STATUS = "caStatus";

	/**
	 * -1
	 */
	public static final String REDUCE_1 = "-1";
	/**
	 * +1
	 */
	public static final String ADD_1 = "+1";

	/**
	 * 站告警状态 正常
	 */
	public static final int STATION_FAULT_STATUS_0 = 0;

	/**
	 * 站告警状态 一般
	 */
	public static final int STATION_FAULT_STATUS_1 = 1;
	/**
	 * 站告警状态 严重
	 */
	public static final int STATION_FAULT_STATUS_2 = 2;
	/**
	 * 站告警状态率
	 */
	public static final double STATION_FAULT_STATUS_RATE_05 = 0.5;
	/**
	 * 站告警状态率
	 */
	public static final double STATION_FAULT_STATUS_RATE_0 = 0;
	
	/**
	 * 站总
	 */
	public static final String STATION_ALL_NUM = "stationAllNum";
	/**
	 * 桩总
	 */
	public static final String PILE_ALL_NUM = "stationAllNum";
	
	

}
