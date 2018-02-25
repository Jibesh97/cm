package com.horizon.common.vo;

import java.sql.Timestamp;

//充电桩VO
public class PileHsfVO {
	
	//主键
	private  String uuid ;
	//桩名称
	private  String name ;
	//所属机构UUID
	private  String org ;
	//所属商户 UUID
	private  String trade ;
	//所属站 UUID
	private  String station ;
	//资产编码，有可能变化
	private  String equipId ;
	//资产状态
	private  String equipStatus ;
	//资产类别
	private  String equipCate ;
	//出厂编号
	private  String madeNo  ;
	//生产批次
	private  String batchNo ;
	//创建时间
	private  Timestamp createdAt ;
	//创建者编号
	private  String creatorNo ;
	//资产的出厂日期
	private  Timestamp madeAt ;
	//资产规定的使用年限
	private  int lifeTime ;
	//地址
	private  String address ;
	//充电桩电机容量
	private  double cap ;
	//充电桩的计量桩类型  直流桩02、交流桩 01code_type ='30002000'
	private  String measureModel ;
	//充电桩的IC卡槽类型
	private  String neckModel ;
	//终端通信的地址码信息
	private  String terminalAddr ;
	//规约类型，暂不编码
	private  String protocolTypeCode  ;
	//建档人员姓名
	private  String creatorName ;
	//经度，调用GIS服务器提供接口写入
	private  double lng ;
	//纬度，调用GIS服务器提供接口写入
	private  double lat ;
	//海拔高度
	private  double altitude ;
	//注SIM 卡号
	private  String simCardNo ;
	//用户名
	private  String loginUser ;
	//密码
	private  String password ;
	//省编码
	private  String provinceCode ;
	//城市编码
	private  String cityCode ;
	//区编码
	private  String districtCode ;
	//是否可以预约
	private  int isAppointment  ;
	//桩别名
	private  String chargePoint ;
	//硬件识别信息
	private  String hardwareId ;
	//注册码
	private  String registerCode ;
	//注册时间
	private  Timestamp registerAt ;
	//接入状态
	private  int accessStatus ;
	//桩接入方式 0:代表国网桩(通过系统桩注册方式接入)；1:代表国网桩(通过系统内第三方平台接入 如高速系统、运营监控系统)；2:代表第三方桩(通过人工导入方式接入)；3:代表第三方桩(通过第三方平台对接方式)4:代表第三方桩（通过系统桩注册方式接入）
	private  int isThird ;
	//注册码ID:D_REGISTE_CODE表的REGISTER_ID
	private  String registerId ;
	//桩的地理位置：1高速，2其他
	private  String isGs ;
	//责任人
	private  String keeperName  ;
	//枪头编号
	private  String gunNo ;
	//充电站是否可用标志 1表示可用 0表示不可用(直接导入的方式接入站)
	private  int isUse ;
	//充电桩类型1专用2公用
	private  String type ;
	//是否可以预约
	private  int isYy ;
	//TCU厂商编码，1000：普瑞特，1001：三优
	private  int tcuManuCode ;
	//桩支持的交易方式，以逗号分隔
	private  String tradeTypes ;
	//充电接口标准: 01 国标、02美标、03 欧标 code_type =30029300
	private  String interfaceStandard ;
	//厂商编码
	private  String operatorCode ;
	//充电站id
	private  String stationId ;
	//站类型：01高速、02城市公共、03单位内部、04公交、05其他；code_type=60006001
	private  String stationType ;
	private  String stakeGroup ;//桩组
	
	
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEquipStatus() {
		return equipStatus;
	}
	public void setEquipStatus(String equipStatus) {
		this.equipStatus = equipStatus;
	}
	public String getEquipCate() {
		return equipCate;
	}
	public void setEquipCate(String equipCate) {
		this.equipCate = equipCate;
	}
	public String getMadeNo() {
		return madeNo;
	}
	public void setMadeNo(String madeNo) {
		this.madeNo = madeNo;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getCreatorNo() {
		return creatorNo;
	}
	public void setCreatorNo(String creatorNo) {
		this.creatorNo = creatorNo;
	}
	public int getLifeTime() {
		return lifeTime;
	}
	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getCap() {
		return cap;
	}
	public void setCap(double cap) {
		this.cap = cap;
	}
	public String getMeasureModel() {
		return measureModel;
	}
	public void setMeasureModel(String measureModel) {
		this.measureModel = measureModel;
	}
	public String getNeckModel() {
		return neckModel;
	}
	public void setNeckModel(String neckModel) {
		this.neckModel = neckModel;
	}
	public String getTerminalAddr() {
		return terminalAddr;
	}
	public void setTerminalAddr(String terminalAddr) {
		this.terminalAddr = terminalAddr;
	}
	public String getProtocolTypeCode() {
		return protocolTypeCode;
	}
	public void setProtocolTypeCode(String protocolTypeCode) {
		this.protocolTypeCode = protocolTypeCode;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	public String getSimCardNo() {
		return simCardNo;
	}
	public void setSimCardNo(String simCardNo) {
		this.simCardNo = simCardNo;
	}
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public int getIsAppointment() {
		return isAppointment;
	}
	public void setIsAppointment(int isAppointment) {
		this.isAppointment = isAppointment;
	}
	public String getChargePoint() {
		return chargePoint;
	}
	public void setChargePoint(String chargePoint) {
		this.chargePoint = chargePoint;
	}
	public String getHardwareId() {
		return hardwareId;
	}
	public void setHardwareId(String hardwareId) {
		this.hardwareId = hardwareId;
	}
	public String getRegisterCode() {
		return registerCode;
	}
	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}
	public int getAccessStatus() {
		return accessStatus;
	}
	public void setAccessStatus(int accessStatus) {
		this.accessStatus = accessStatus;
	}
	public int getIsThird() {
		return isThird;
	}
	public void setIsThird(int isThird) {
		this.isThird = isThird;
	}
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public String getIsGs() {
		return isGs;
	}
	public void setIsGs(String isGs) {
		this.isGs = isGs;
	}
	public String getKeeperName() {
		return keeperName;
	}
	public void setKeeperName(String keeperName) {
		this.keeperName = keeperName;
	}
	public String getGunNo() {
		return gunNo;
	}
	public void setGunNo(String gunNo) {
		this.gunNo = gunNo;
	}
	public int getIsUse() {
		return isUse;
	}
	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getIsYy() {
		return isYy;
	}
	public void setIsYy(int isYy) {
		this.isYy = isYy;
	}
	public int getTcuManuCode() {
		return tcuManuCode;
	}
	public void setTcuManuCode(int tcuManuCode) {
		this.tcuManuCode = tcuManuCode;
	}
	public String getTradeTypes() {
		return tradeTypes;
	}
	public void setTradeTypes(String tradeTypes) {
		this.tradeTypes = tradeTypes;
	}
	public String getInterfaceStandard() {
		return interfaceStandard;
	}
	public void setInterfaceStandard(String interfaceStandard) {
		this.interfaceStandard = interfaceStandard;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getStationType() {
		return stationType;
	}
	public void setStationType(String stationType) {
		this.stationType = stationType;
	}
	public String getStakeGroup() {
		return stakeGroup;
	}
	public void setStakeGroup(String stakeGroup) {
		this.stakeGroup = stakeGroup;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getMadeAt() {
		return madeAt;
	}
	public void setMadeAt(Timestamp madeAt) {
		this.madeAt = madeAt;
	}
	public Timestamp getRegisterAt() {
		return registerAt;
	}
	public void setRegisterAt(Timestamp registerAt) {
		this.registerAt = registerAt;
	}
}
