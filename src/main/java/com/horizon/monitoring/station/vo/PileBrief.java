package com.horizon.monitoring.station.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PileBrief {
	private String id;
	private String pileName;
	private String runSta;
	private String soc;
	private String groupName;//桩属于哪个组

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPileName() {
		return pileName;
	}

	public void setPileName(String pileName) {
		this.pileName = pileName;
	}

	public String getRunSta() {
		return runSta;
	}

	public void setRunSta(String runSta) {
		this.runSta = runSta;
	}

	public String getSoc() {
		return soc;
	}

	public void setSoc(String soc) {
		this.soc = soc;
	}


}
