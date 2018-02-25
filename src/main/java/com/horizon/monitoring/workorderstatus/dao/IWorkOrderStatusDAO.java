package com.horizon.monitoring.workorderstatus.dao;

import java.util.List;

import com.horizon.common.dao.ISDAO;
import com.horizon.monitoring.workorderstatus.vo.WorkOrderStatusVO;

public interface IWorkOrderStatusDAO  extends ISDAO<Object>{

	int updateWorkOrderStatus(WorkOrderStatusVO workOrderStatusVO);
	
	int updateWorkOrderStatusOver(WorkOrderStatusVO workOrderStatusVO);
	
	List<?> getAll(WorkOrderStatusVO workOrderStatusVO);

	int updateWorkOrderStatusHistory(WorkOrderStatusVO workOrderStatusVO);

	int updateWorkOrderStatusOverHistory(WorkOrderStatusVO info);

	List<?> getAllHistory(WorkOrderStatusVO info);


}
