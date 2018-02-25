package com.horizon.monitoring.pile.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PileDetail {
	
	private String runSta;
	private String soc;
	
	private String faultRate;
	private String restoreRate; 
	private String offRate;
	private String successRate;
	
	
	public void init(){
		runSta = "充电中";
		soc = "80";
		java.util.Random r=new java.util.Random();
		faultRate = String.valueOf(r.nextFloat());
		restoreRate = String.valueOf(r.nextFloat());
		offRate = String.valueOf(r.nextFloat());
		successRate = String.valueOf(r.nextFloat());
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

	public String getFaultRate() {
		return faultRate;
	}

	public void setFaultRate(String faultRate) {
		this.faultRate = faultRate;
	}

	public String getRestoreRate() {
		return restoreRate;
	}

	public void setRestoreRate(String restoreRate) {
		this.restoreRate = restoreRate;
	}

	public String getOffRate() {
		return offRate;
	}

	public void setOffRate(String offRate) {
		this.offRate = offRate;
	}

	public String getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(String successRate) {
		this.successRate = successRate;
	}
}
