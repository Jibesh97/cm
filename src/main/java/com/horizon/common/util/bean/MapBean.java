package com.horizon.common.util.bean;

public class MapBean {

	private Object[] mapArray = new Object[2];

	private String key = "";

	private Object value = "";

	public void put(String key, Object value) {
		this.setKey(key);
		this.setValue(value);
	}

	public String getKey() {
		return key;
	}

	private void setKey(String key) {
		this.key = key;
		mapArray[0] = key;
	}

	public Object getValue() {
		return value;
	}

	private void setValue(Object value) {
		this.value = value;
		mapArray[1] = value;
	}
}
