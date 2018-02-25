package com.horizon.common.vo;

import java.util.List;

//机构VO
public class OrgTreeVO {
	
	//组织机构标识
	private  String ouCode ;
	//机构名称
	private  String ouName ;
	//组织机构类型
	private  int ouType ;
	//父级机构标识
	private  String parentOuCode ;
	//机构简称
	private  String shortName ;
	//组织机构标准编号
	private  String standardCode ;
	//子组织结构信息
	private  List<OrgTreeVO> subOrganization ;
	
	public String getOuCode() {
		return ouCode;
	}
	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}
	public String getOuName() {
		return ouName;
	}
	public void setOuName(String ouName) {
		this.ouName = ouName;
	}
	public int getOuType() {
		return ouType;
	}
	public void setOuType(int ouType) {
		this.ouType = ouType;
	}
	public String getParentOuCode() {
		return parentOuCode;
	}
	public void setParentOuCode(String parentOuCode) {
		this.parentOuCode = parentOuCode;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getStandardCode() {
		return standardCode;
	}
	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}
	public List<OrgTreeVO> getSubOrganization() {
		return subOrganization;
	}
	public void setSubOrganization(List<OrgTreeVO> subOrganization) {
		this.subOrganization = subOrganization;
	}

}
