package com.horizon.common.hsf.model;

import java.util.Map;

import com.horizon.common.base.RedisClientDao;
import com.horizon.common.redis.model.MechanismStatus;
import com.horizon.common.redis.model.PileStationMechanism;
import com.horizon.common.redis.model.PileStatus;
import com.horizon.common.redis.model.StationStatus;

/**
 * 封装观察者需要的参数
 * 
 * @author liy
 * 
 */
public class InitHsfDataVO {
	private String pineId;// 桩id
	private String stationId;// 站id
	private PileStatus pineStatus;// 新桩状态
	private PileStatus oldPineStatus;// 旧桩状态
	private PileStationMechanism mechanismCode;// 机构码
	private String messageType;// 消息类型 MONITOR监控信息 ALARM告警状态 STATUS 状态
	private RedisClientDao rdao;
	private String[] findStatus;
	private int thisPointSta;// 标识站 当前观察者在哪级 //0桩 1区县 2市 3省
	private int thisPointMa;// 标识机构当前观察者在哪级 //0桩 1区县 2市 3省
	private Map<String, String> pileMap; // 桩的变化值
	private Map<String, String> stationMap;// 站的变化值
	private Map<String, String> mechMap;// 机构的变化值

	private StationStatus oldStationStatus;// 旧桩站状态
	private MechanismStatus oldMechStatus;// 旧桩站状态

	public String getPineId() {
		return pineId;
	}

	public void setPineId(String pineId) {
		this.pineId = pineId;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public PileStatus getPineStatus() {
		return pineStatus;
	}

	public void setPineStatus(PileStatus pineStatus) {
		this.pineStatus = pineStatus;
	}

	public PileStationMechanism getMechanismCode() {
		return mechanismCode;
	}

	public void setMechanismCode(PileStationMechanism mechanismCode) {
		this.mechanismCode = mechanismCode;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public RedisClientDao getRdao() {
		return rdao;
	}

	public void setRdao(RedisClientDao rdao) {
		this.rdao = rdao;
	}

	public PileStatus getOldPineStatus() {
		return oldPineStatus;
	}

	public void setOldPineStatus(PileStatus oldPineStatus) {
		this.oldPineStatus = oldPineStatus;
	}

	public String[] getFindStatus() {
		return findStatus;
	}

	public void setFindStatus(String[] findStatus) {
		this.findStatus = findStatus;
	}

	public int getThisPointSta() {
		return thisPointSta;
	}

	public void setThisPointSta(int thisPointSta) {
		this.thisPointSta = thisPointSta;
	}

	public int getThisPointMa() {
		return thisPointMa;
	}

	public void setThisPointMa(int thisPointMa) {
		this.thisPointMa = thisPointMa;
	}

	public Map<String, String> getPileMap() {
		return pileMap;
	}

	public void setPileMap(Map<String, String> pileMap) {
		this.pileMap = pileMap;
	}

	public Map<String, String> getStationMap() {
		return stationMap;
	}

	public void setStationMap(Map<String, String> stationMap) {
		this.stationMap = stationMap;
	}

	public Map<String, String> getMechMap() {
		return mechMap;
	}

	public void setMechMap(Map<String, String> mechMap) {
		this.mechMap = mechMap;
	}

	public StationStatus getOldStationStatus() {
		return oldStationStatus;
	}

	public void setOldStationStatus(StationStatus oldStationStatus) {
		this.oldStationStatus = oldStationStatus;
	}

	public MechanismStatus getOldMechStatus() {
		return oldMechStatus;
	}

	public void setOldMechStatus(MechanismStatus oldMechStatus) {
		this.oldMechStatus = oldMechStatus;
	}

}
