package com.horizon.common.util.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelBean {

	public List<MapBean> columnNameList = new ArrayList<MapBean>();

	public List<Map<String, Object>> columnValueMapList = new ArrayList<Map<String, Object>>();

	public List<MapBean> getColumnNameList() {
		return columnNameList;
	}

	public void setColumnNameList(List<MapBean> columnNameList) {
		this.columnNameList = columnNameList;
	}

	public List<Map<String, Object>> getColumnValueMapList() {
		return columnValueMapList;
	}

	public void setColumnValueMapList(List<Map<String, Object>> columnValueMapList) {
		this.columnValueMapList = columnValueMapList;
	}

}
