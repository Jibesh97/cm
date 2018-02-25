package com.horizon.common.util;

import java.util.List;
import java.util.Map;

import com.horizon.common.base.GenericVO;
import com.horizon.common.util.bean.DwzPageBean;

public class PageView {
	
	private DwzPageBean pagination = null;
	
	private List<?> viewList = null;
	
	private List<?> data;
	
	private GenericVO vo = null;
	
	private int totalSize;
	
	private Map<?, ?> paraMap;
	
	public Map<?, ?> getParaMap() {
		return paraMap;
	}

	public DwzPageBean getPagination() {
		return pagination;
	}

	public void setPagination(DwzPageBean pagination) {
		this.pagination = pagination;
	}

	public List<?> getViewList() {
		return viewList;
	}

	public void setViewList(List<?> viewList) {
		this.viewList = viewList;
	}

	public GenericVO getVo() {
		return vo;
	}

	public void setVo(GenericVO vo) {
		this.vo = vo;
	}

	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}

	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
}
