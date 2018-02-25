package com.horizon.common.mq.model;

import java.util.List;

import com.horizon.common.vo.StationVO;

public class PropStation {
	
	private String type ; //: CREATE/UPDATE/DELETE 创建 / 更新 / 删除
	private String uuid ; //: 电桩 uuid
	private String opAt ; //: 变更时间
	private StationVO extrData ; //额外数据，以 key/value 形式出现
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getOpAt() {
		return opAt;
	}
	public void setOpAt(String opAt) {
		this.opAt = opAt;
	}
	public StationVO getExtrData() {
		return extrData;
	}
	public void setExtrData(StationVO extrData) {
		this.extrData = extrData;
	}
	

	
	
	
}
