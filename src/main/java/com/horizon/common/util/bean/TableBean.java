package com.horizon.common.util.bean;
public class TableBean {
	private int sEcho;// 当前页数
	private int iTotalRecords;// 总条数
	private int iTotalDisplayRecords;// 总条数
	private Object aaData;// 数据
	
	public int getsEcho() {
		return sEcho;
	}

	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public Object getAaData() {
		return aaData;
	}

	public void setAaData(Object aaData) {
		this.aaData = aaData;
	}

}
