package com.horizon.monitoring.pile.vo;

public class PileResponse {
	private int isNew;
	private PileDetail pileDetail;
	private StationBrief stationBrief;
	public void init(int isNew){
		this.isNew = isNew;
		pileDetail = new PileDetail();
		pileDetail.init();
		stationBrief = new StationBrief();
		stationBrief.init();
	}
	public int getIsNew() {
		return isNew;
	}
	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}
	public PileDetail getPileDetail() {
		return pileDetail;
	}
	public void setPileDetail(PileDetail pileDetail) {
		this.pileDetail = pileDetail;
	}
	public StationBrief getStationBrief() {
		return stationBrief;
	}
	public void setStationBrief(StationBrief stationBrief) {
		this.stationBrief = stationBrief;
	}
	
}
