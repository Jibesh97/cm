package com.horizon.monitoring.workorderstatus.service;

import java.util.List;

import com.horizon.monitoring.workorderstatus.vo.WorkOrderStatusVO;

public interface IWorkOrderStatusService {
	
	void updateRedisComplete(WorkOrderStatusVO workOrderStatusVO);

	void updateRedisPay(WorkOrderStatusVO workOrderStatusVO);

	void updateRedisWork(WorkOrderStatusVO workOrderStatusVO);

	List<?> getAll(WorkOrderStatusVO workOrderStatusVO);

	List<?> getAllHistory(WorkOrderStatusVO workOrderStatusVO);

	int updateWorkOrderStatusOver(WorkOrderStatusVO workOrderStatusVO);

	int updateWorkOrderStatusOverHistory(WorkOrderStatusVO workOrderStatusVO);

	int updateWorkOrderStatusHistory(WorkOrderStatusVO workOrderStatusVO);

	int updateWorkOrderStatus(WorkOrderStatusVO workOrderStatusVO);

}
