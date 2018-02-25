package com.horizon.monitoring.map.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.horizon.common.redis.model.StationStatus;


/**
 * 站信息
 * @author Administrator
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StationInfo extends  StationStatus{
	
 
	private String local;// 充电桩名称
	  
	private String code;// code唯一标识

	public String getLocal() {
		return local;
	}

	public void setStationName(String name) { 
		 super.setStationName(name);
		this.local = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

 
  
	 
}
