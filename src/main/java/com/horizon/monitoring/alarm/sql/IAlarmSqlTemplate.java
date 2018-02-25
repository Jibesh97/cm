package com.horizon.monitoring.alarm.sql;


/**
 * 
 * Title:<br>
 * Description: 告警信息SQL语句的接口<br>
 * Date: 2017-5-4 <br>
 * Copyright (c) 2017 <br>
 * 
 * @author 
 */
public interface IAlarmSqlTemplate {
	static final String RESP_ALARM_INFO = "SELECT ID, PROVINCE_NAME provinces, CITY_NAME city, STATION_NAME station, IS_AFFRIM isAffrim, PILE_ID pile, FAULT_TYPE faultType, FAULT_START_TIME faultTime, ifnull(    TIMESTAMPDIFF(       MINUTE,       FAULT_START_TIME,       ifnull(FAULT_END_TIME, SYSDATE())    ),    '' ) faultTimer, FAULT_DETAILS faultDetails, ifnull(WORK_ORDER_ID, '') orderId, ifnull(WORK_ORDER_STATE, '') orderState, ifnull(WORK_ORDER_START_TIME, '') orderPayoutTime, ifnull(    TIMESTAMPDIFF(       MINUTE,       WORK_ORDER_START_TIME,       ifnull(          WORK_ORDER_END_TIME,          SYSDATE()       )    ),    '0' ) orderElapsedTime, CASE WHEN ifnull( TIMESTAMPDIFF(    MINUTE,    WORK_ORDER_START_TIME,    ifnull(       WORK_ORDER_END_TIME,       SYSDATE()    ) ), '0' ) >= 120 AND WORK_ORDER_STATE <> 4 THEN 1 WHEN ifnull( TIMESTAMPDIFF(    MINUTE,    WORK_ORDER_START_TIME,    ifnull(       WORK_ORDER_END_TIME,       SYSDATE()    ) ), '0' ) >= 15 AND WORK_ORDER_STATE = 2 THEN 1 ELSE 0 END ORDER1 FROM CM_FAULT_WORK_ORDER WHERE WORK_ORDER_ID =:orderId";
	/** 添加用户 */
	static final String ALARM_ADD = "insert into sys_user" +
			" (username, password, name, age, createTime) value" +
			" (:username, :password, :name, :age, sysdate())";
	
	static final String ALARM_COUNT =
			"SELECT DISTINCT faultNumber+payoutNumber+disposeNumber+concludeNumber allNumber,CMWO.* " +
					"FROM( " +
					"SELECT  " +
					"(SELECT count(*) from CM_FAULT_WORK_ORDER WHERE WORK_ORDER_STATE='1' and FAULT_STATE<>'0') faultNumber, " +
					"(SELECT count(*) from CM_FAULT_WORK_ORDER WHERE WORK_ORDER_STATE='2') payoutNumber, " +
					"(SELECT count(*) from CM_FAULT_WORK_ORDER WHERE WORK_ORDER_STATE='3') disposeNumber, " +
					"(SELECT count(*) from CM_FAULT_WORK_ORDER WHERE WORK_ORDER_STATE='4') concludeNumber " +
					"FROM CM_FAULT_WORK_ORDER where 1=1 #if{ and (STATION_NAME like :name or PILE_ID like :name )} ) CMWO ";
			
			  
   
	static final String ALARM_LIST =   
					"select ID,  PROVINCE_NAME provinces,CITY_NAME city, " +
					"STATION_ID stationId,STATION_NAME stationName,IS_AFFRIM isAffrim, " +
					"PILE_ID pile,"+
					"FAULT_TYPE faultType, " +
					"FAULT_START_TIME faultStateTime," +
					"FAULT_END_TIME faultEndTime," +
					
					"FAULT_STATE faultState,"+ 
					"FAULT_MARKING faultMarking,"+ 
					"FAULT_DETAILS faultDetails,"+ 
					
					"ifnull(WORK_ORDER_ID,'')  orderId, " +
					"ifnull(WORK_ORDER_STATE,'') orderState, " +  
					"WORK_ORDER_START_TIME orderStartTime, "  +
					"WORK_ORDER_END_TIME orderEndTime, "  +
					
					"	ifnull( " +
					"		TIMESTAMPDIFF( " +
					"			minute, " +
					"			WORK_ORDER_START_TIME, " +
					"			ifnull(WORK_ORDER_END_TIME,SYSDATE()) " +
					"		), " +
					"		'0' " +
					"	) orderElapsedTime," +
					 
					"CASE  " +
					"WHEN  " +
					"	ifnull( " +
					"		TIMESTAMPDIFF( " +
					"			minute, " +
					"			WORK_ORDER_START_TIME, " +
					"			ifnull(WORK_ORDER_END_TIME,SYSDATE()) " +
					"		), " +
					"		'0' " +
					"	) " +
					">=120 AND WORK_ORDER_STATE<>4 THEN 1 "+
					
					"WHEN  " +
					"	ifnull( " +
					"		TIMESTAMPDIFF( " +
					"			minute, " +
					"			WORK_ORDER_START_TIME, " +
					"			ifnull(WORK_ORDER_END_TIME,SYSDATE()) " +
					"		), " +
					"		'0' " +
					"	) " +
					">=15 AND WORK_ORDER_STATE=2 THEN 1 "+
					"ELSE 0 END isTop"+
					" from CM_FAULT_WORK_ORDER where  CASE WHEN WORK_ORDER_STATE='1' and FAULT_STATE='0' THEN 0 ELSE 1 END=1   "+
					" #if{ and WORK_ORDER_STATE=:type} "+
					" #if{ and ( STATION_NAME like :name or PILE_ID like :name )} "+
					"order by isTop desc,WORK_ORDER_STATE,orderElapsedTime desc,FAULT_START_TIME desc  limit :startLimit,:endLimit " ;
	
	

	static final String ALARM_LIST_COUNT =   
					"select count(*) pageAllNumber " + 
					" from CM_FAULT_WORK_ORDER where  CASE WHEN WORK_ORDER_STATE='1' and FAULT_STATE='0' THEN 0 ELSE 1 END=1   "+
					" #if{ and WORK_ORDER_STATE=:type} "+
					" #if{ and ( STATION_NAME like :name or PILE_ID like :name )} ";
	static final String ALL_AFFIRM = "update CM_FAULT_WORK_ORDER set IS_AFFRIM='1' where 1=1  #if{and id in (:ids)} ";

	
//	static final String SELECT_PILE_ID = "SELECT PILE_ID From CM_PILE_FAULT WHERE PILE_ID=:chargingPileNum AND FAULT_TYPE = :indexNum";
	
	static final String GET_LAST_ID = "SELECT ID FROM CM_FAULT_WORK_ORDER ORDER BY ID DESC LIMIT 1;";
	
	static final String INSERT_PILE_ID = "	INSERT INTO CM_FAULT_WORK_ORDER (PROVINCE_ID,PROVINCE_NAME,CITY_ID,CITY_NAME,COUNTY_ID,COUNTY_NAME, FAULT_STATE,FAULT_TYPE, STATION_ID,STATION_NAME, PILE_ID, FAULT_MARKING, FAULT_DETAILS , FAULT_START_TIME) VALUES (:provinceId,:provinceName,:cityId,:cityName,:countyId,:countyName,:faultState,:faultType,:stationId,:stationName,:pileId,:faultMarking,:faultDetails,:faultStartTime)";

	static final String UPDATA_PILE_ID = "UPDATE CM_FAULT_WORK_ORDER SET FAULT_STATE = \"0\",FAULT_END_TIME = :faultEndTime  WHERE PILE_ID = :pileId AND FAULT_MARKING = :faultMarking AND FAULT_STATE = \"1\"";
	static final String UPDATE_WORK_ORDER = "update CM_FAULT_WORK_ORDER set "
			+ "WORK_ORDER_STATE='2' ,WORK_ORDER_ID= :order_id,"
			+ "WORK_ORDER_START_TIME=:receive_time, "
			+"WORK_ORDER_USER_ID=:distribute_person_id "
			+ "where FAULT_STATE='1' "
			+ "and (WORK_ORDER_STATE='1' or  WORK_ORDER_STATE IS NULL)"
			+ "and FAULT_MARKING=:fault_type "
			+ "and PILE_ID=:charge_station_id ";
	static final String UPDATE_FAULT_STATE = "update CM_FAULT_WORK_ORDER set FAULT_STATE='0' and  FAULT_END_TIME=:complete_time "
			+ " WHERE PILE_ID = :charge_station_id AND FAULT_MARKING = :fault_type AND FAULT_STATE = \"1\"";
	static final String UPDATE_FAULT_STATE_HISTORY = "update CM_FAULT_WORK_ORDER_HISTORY set FAULT_STATE='0' and  FAULT_END_TIME=:faultEndTime  "
			+ " WHERE PILE_ID = :pileId AND FAULT_MARKING = :faultMarking AND FAULT_STATE = \"1\"";
//	static final String INSERT_FAULT_STATE_HISTORY = "INSERT INTO CM_FAULT_WORK_ORDER_HISTORY SELECT * FROM CM_FAULT_WORK_ORDER where  PILE_ID = :pileId AND FAULT_MARKING = :faultMarking AND FAULT_STATE = \"1\"";
	
	static final String INSERT_FAULT_STATE_HISTORY = "INSERT INTO CM_FAULT_WORK_ORDER_HISTORY(PROVINCE_ID,PROVINCE_NAME,CITY_ID,CITY_NAME,COUNTY_ID,COUNTY_NAME,STATION_ID,STATION_NAME,IS_AFFRIM,PILE_ID,FAULT_TYPE,FAULT_STATE,FAULT_START_TIME,FAULT_END_TIME,FAULT_MARKING,FAULT_DETAILS,WORK_ORDER_ID,WORK_ORDER_STATE,WORK_ORDER_START_TIME,WORK_ORDER_END_TIME,WORK_ORDER_USER_ID) "
	+ " SELECT PROVINCE_ID,PROVINCE_NAME,CITY_ID,CITY_NAME,COUNTY_ID,COUNTY_NAME,STATION_ID,STATION_NAME,IS_AFFRIM,PILE_ID,FAULT_TYPE,FAULT_STATE,FAULT_START_TIME,FAULT_END_TIME,FAULT_MARKING,FAULT_DETAILS,WORK_ORDER_ID,WORK_ORDER_STATE,WORK_ORDER_START_TIME,WORK_ORDER_END_TIME,WORK_ORDER_USER_ID FROM CM_FAULT_WORK_ORDER WHERE PILE_ID = :pileId AND FAULT_MARKING = :faultMarking AND FAULT_STATE = \"1\"";
	
	static final String SELECT_STATION_PILE = "SELECT ID AS ID,PROVINCE_ID as provinceId,PROVINCE_NAME as provinceName,CITY_ID as cityId,CITY_NAME as cityName,COUNTY_ID as countyId,COUNTY_NAME as countyName,STATION_ID as stationId,STATION_NAME as stationName,IS_AFFRIM as isAffrim,PILE_ID as pileId,FAULT_TYPE as faultType,FAULT_STATE as faultState,FAULT_START_TIME as faultStartTime,FAULT_END_TIME as faultEndTime,FAULT_MARKING as faultMarking,FAULT_DETAILS as faultDetails,WORK_ORDER_ID as workOrderId,WORK_ORDER_STATE as workOrderState,WORK_ORDER_START_TIME as workOrderStartTime,WORK_ORDER_END_TIME as workOrderEndTime,WORK_ORDER_USER_ID as workOrderUserId FROM CM_FAULT_WORK_ORDER WHERE PILE_ID = :pileId AND FAULT_MARKING = :faultMarking AND FAULT_STATE = \"1\"";

	static final String SELECT_PILE_ONE = "SELECT * FROM CM_FAULT_WORK_ORDER WHERE PILE_ID = :pileId AND FAULT_MARKING = :faultMarking AND FAULT_STATE = \"1\"";
	
	static final String DELETE_FAULT_STATE_HISTORY = "DELETE FROM CM_FAULT_WORK_ORDER where  PILE_ID = :pileId AND FAULT_MARKING = :faultMarking AND FAULT_STATE = \"1\"";

}
