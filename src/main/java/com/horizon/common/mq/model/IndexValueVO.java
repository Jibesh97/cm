package com.horizon.common.mq.model;

public class IndexValueVO {
	
	private int indexNum;//告警类型
	
	private Short value;//故障、告警值
	
	public int getIndexNum() {
		return indexNum;
	}
	public void setIndexNum(int indexNum) {
		this.indexNum = indexNum;
	}
	public Short getValue() {
		return value;
	}
	public void setValue(Short value) {
		this.value = value;
	}
	
	
}
