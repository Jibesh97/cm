package com.horizon.monitoring.workorderstatus.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.horizon.common.base.BaseDao;
import com.horizon.monitoring.workorderstatus.dao.IWorkOrderStatusDAO;
import com.horizon.monitoring.workorderstatus.sql.IWorkOrderStatusSqlTemplate;
import com.horizon.monitoring.workorderstatus.vo.WorkOrderStatusDBInfo;
import com.horizon.monitoring.workorderstatus.vo.WorkOrderStatusVO;

@Component("workOrderStatusDAO")
public class WorkOrderStatusDAOImpl extends BaseDao implements IWorkOrderStatusDAO {

	private static Logger log = Logger.getLogger(WorkOrderStatusDAOImpl.class);
	
	/**
	 * 更新数据库信息
	 * @return 
	 */
	@Override
	public int updateWorkOrderStatus(WorkOrderStatusVO workOrderStatusVO) {
		log.info("updateWorkOrderStatus");
		
		int res = super.update(workOrderStatusVO,IWorkOrderStatusSqlTemplate.UPDATA_WORK_ORDER_STATUS);
		return res;
	}
	/**
	 * 更新数据库历史告警工单信息
	 * @return 
	 */
	@Override
	public int updateWorkOrderStatusHistory(WorkOrderStatusVO workOrderStatusVO) {
		log.info("updateWorkOrderStatusHistory");
		int res = super.update(workOrderStatusVO,IWorkOrderStatusSqlTemplate.UPDATA_WORK_ORDER_STATUS_HISTORY);
		return res;
	}
	/**
	 * 更新数据库信息
	 * @return 
	 */
	@Override
	public int updateWorkOrderStatusOver(WorkOrderStatusVO workOrderStatusVO) {
		log.info("updateWorkOrderStatus");
		int res = super.update(workOrderStatusVO,IWorkOrderStatusSqlTemplate.UPDATA_WORK_ORDER_STATUS_OVER);
		return res;
	}
	/**
	 * 更新数据库历史告警工单信息
	 * @return 
	 */
	@Override
	public int updateWorkOrderStatusOverHistory(WorkOrderStatusVO workOrderStatusVO) {
		log.info("updateWorkOrderStatusOverHistory");
		int res = super.update(workOrderStatusVO,IWorkOrderStatusSqlTemplate.UPDATA_WORK_ORDER_STATUS_OVER_HISTORY);
		return res;
	}
	/**
	 * 根据工单编号获取表信息
	 */
	@Override
	public List<?> getAll(WorkOrderStatusVO workOrderStatusVO) {
		log.info("getAll");
		List<?> lis = super.findByVO(workOrderStatusVO,IWorkOrderStatusSqlTemplate.SELECT_PILE_DB,WorkOrderStatusDBInfo.class);
//		List<?> lis = super.findByVO(workOrderStatusVO,IWorkOrderStatusSqlTemplate.SELECT_STATION_PILE);
		return lis;
	}
	/**
	 * 根据工单编号获取历史表信息
	 */
	@Override
	public List<?> getAllHistory(WorkOrderStatusVO workOrderStatusVO) {
		log.info("getAllHistory");
		List<?> lis = super.findByVO(workOrderStatusVO,IWorkOrderStatusSqlTemplate.SELECT_PILE_DB_HISTORY,WorkOrderStatusDBInfo.class);
//		List<?> lis = super.findByVO(workOrderStatusVO,IWorkOrderStatusSqlTemplate.SELECT_STATION_PILE_HISTORY);
		return lis;
	}
	
}
