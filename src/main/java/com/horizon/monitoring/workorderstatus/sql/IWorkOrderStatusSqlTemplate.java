package com.horizon.monitoring.workorderstatus.sql;

/**
 * 
 * Title:<br>
 * Description: 告警信息SQL语句的接口<br>
 * Date: 2017-5-4 <br>
 * Copyright (c) 2017 <br>
 * 
 * @author 
 */
public interface IWorkOrderStatusSqlTemplate {
	/**
	 * 更新工单信息
	 */
	static final String UPDATA_WORK_ORDER_STATUS = "UPDATE CM_FAULT_WORK_ORDER SET WORK_ORDER_STATE = :status,WORK_ORDER_USER_ID = :workOrderUserId  WHERE WORK_ORDER_ID = :orderId ";
	
	static final String UPDATA_WORK_ORDER_STATUS_HISTORY = "UPDATE CM_FAULT_WORK_ORDER_HISTORY SET WORK_ORDER_STATE = :status,WORK_ORDER_USER_ID = :workOrderUserId  WHERE WORK_ORDER_ID = :orderId ";
	
	static final String UPDATA_WORK_ORDER_STATUS_OVER = "UPDATE CM_FAULT_WORK_ORDER SET WORK_ORDER_STATE = :status,WORK_ORDER_USER_ID = :workOrderUserId,WORK_ORDER_END_TIME = :completeTime  WHERE WORK_ORDER_ID = :orderId ";

	static final String UPDATA_WORK_ORDER_STATUS_OVER_HISTORY = "UPDATE CM_FAULT_WORK_ORDER_HISTORY SET WORK_ORDER_STATE = :status,WORK_ORDER_USER_ID = :workOrderUserId,WORK_ORDER_END_TIME = :completeTime  WHERE WORK_ORDER_ID = :orderId ";
	
//	static final String COUNT_WORK_ORDER_STATUS = "SELECT COUNT(1) FROM CM_FAULT_WORK_ORDER";
//
//	static final String COUNT_WORK_ORDER_STATUS_OVER = "SELECT COUNT(1) FROM CM_FAULT_WORK_ORDER WHERE WORK_ORDER_STATE = \"已处理完成\"";
//	
//	static final String COUNT_WORK_ORDER_STATUS_WORKING = "SELECT COUNT(1) FROM CM_FAULT_WORK_ORDER WHERE WORK_ORDER_STATE = \"已受理未完成\"";
//	
//	static final String COUNT_WORK_ORDER_STATUS_FAULT = "SELECT COUNT(1) FROM CM_FAULT_WORK_ORDER WHERE FAULT_STATE = 1";
//	
//	static final String COUNT_WORK_ORDER_STATUS_PIL = "SELECT COUNT(1) FROM CM_FAULT_WORK_ORDER WHERE WORK_ORDER_STATE = \"派发未受理\"";
	
//	static final String SELECT_STATION_PILE = "SELECT * FROM CM_FAULT_WORK_ORDER WHERE WORK_ORDER_ID = :orderId";
	
//	static final String SELECT_STATION_PILE_HISTORY = "SELECT * FROM CM_FAULT_WORK_ORDER_HISTORY WHERE WORK_ORDER_ID = :orderId";

	static final String SELECT_PILE_DB = "SELECT ID AS ID,PROVINCE_ID as provinceId,PROVINCE_NAME as provinceName,CITY_ID as cityId,CITY_NAME as cityName,COUNTY_ID as countyId,COUNTY_NAME as countyName,STATION_ID as stationId,STATION_NAME as stationName,IS_AFFRIM as isAffrim,PILE_ID as pileId,FAULT_TYPE as faultType,FAULT_STATE as faultState,FAULT_START_TIME as faultStartTime,FAULT_END_TIME as faultEndTime,FAULT_MARKING as faultMarking,FAULT_DETAILS as faultDetails,WORK_ORDER_ID as workOrderId,WORK_ORDER_STATE as workOrderState,WORK_ORDER_START_TIME as workOrderStartTime,WORK_ORDER_END_TIME as workOrderEndTime,WORK_ORDER_USER_ID as workOrderUserId FROM CM_FAULT_WORK_ORDER WHERE WORK_ORDER_ID = :orderId";
	
	static final String SELECT_PILE_DB_HISTORY = "SELECT ID AS ID,PROVINCE_ID as provinceId,PROVINCE_NAME as provinceName,CITY_ID as cityId,CITY_NAME as cityName,COUNTY_ID as countyId,COUNTY_NAME as countyName,STATION_ID as stationId,STATION_NAME as stationName,IS_AFFRIM as isAffrim,PILE_ID as pileId,FAULT_TYPE as faultType,FAULT_STATE as faultState,FAULT_START_TIME as faultStartTime,FAULT_END_TIME as faultEndTime,FAULT_MARKING as faultMarking,FAULT_DETAILS as faultDetails,WORK_ORDER_ID as workOrderId,WORK_ORDER_STATE as workOrderState,WORK_ORDER_START_TIME as workOrderStartTime,WORK_ORDER_END_TIME as workOrderEndTime,WORK_ORDER_USER_ID as workOrderUserId FROM CM_FAULT_WORK_ORDER_HISTORY WHERE WORK_ORDER_ID = :orderId";
}
