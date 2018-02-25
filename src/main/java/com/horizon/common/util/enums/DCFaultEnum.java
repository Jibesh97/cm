package com.horizon.common.util.enums;

public enum DCFaultEnum {

	FAULT_0("BMS 通信异常", 0), FAULT_1("直流母线输出过压告警", 1), FAULT_2("直流母线输出欠压告警", 2), FAULT_3(
			"蓄电池充电过流告警", 3), FAULT_4("蓄电池模块采样点过温告警", 4), FAULT_5("急停按钮动作故障", 5), FAULT_6(
			"读卡器通讯异常故障", 6), FAULT_7("电度表异常故障", 7), FAULT_8("绝缘监测故障", 8), FAULT_9(
			"电池反接故障", 9), FAULT_10("避雷器故障", 10), FAULT_11("充电枪未归位", 11), FAULT_12(
			"充电机柜过温故障", 12), FAULT_13("烟雾报警告警", 13), FAULT_14("交易记录已满告警", 14), FAULT_15(
			"充电机输出并联接触器告警", 15), FAULT_16("充电模块故障", 16), FAULT_17("充电模块风扇故障",
			17), FAULT_18("充电模块过温告警", 18), FAULT_19("充电模块交流输入告警", 19), FAULT_20(
			"充电模块输出短路故障", 20), FAULT_21("充电模块输出过流告警", 21), FAULT_22(
			"充电模块输出过压告警", 22), FAULT_23("充电模块输出欠压告警", 23), FAULT_24(
			"充电模块输入过压告警", 24), FAULT_25("充电模块输入欠压告警", 25), FAULT_26(
			"充电模块输入缺相告警", 26), FAULT_27("充电模块通信告警", 27), FAULT_28(
			"充电中车辆控制导引告警", 28), FAULT_29("交流断路器故障", 29), FAULT_30("直流母线输出过流告警",
			30), FAULT_31("直流母线输出熔断器故障", 31), FAULT_32("输入电压过压", 32), FAULT_33(
			"输入电压欠压", 33), FAULT_34("充电接口电子锁故障", 34), FAULT_35("充电机风扇故障", 35), FAULT_36(
			"充电枪过温", 36), FAULT_37("充电机其他故障", 37), FAULT_38("TCU 其他故障", 38), FAULT_39(
			"CANPLIE 通讯故障", 39), FAULT_40("ESAM 故障", 40);

	// 成员变量
	private String desc;
	private int code;

	// 构造方法
	private DCFaultEnum(String desc, int code) {
		this.code = code;
		this.desc = desc;
	}

	// 普通方法
	public static String getName(int code) {
		for (DCFaultEnum c : DCFaultEnum.values()) {
			if (c.getCode() == code) {
				return c.desc;
			}
		}
		return null;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
