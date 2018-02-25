package com.horizon.common.util.enums;

public enum ACFaultEnum {

	FAULT_0("避雷器故障", 0), FAULT_1("过温故障", 1), FAULT_2("充电枪未归位", 2), FAULT_3(
			"充电中车辆控制导引告警", 3), FAULT_4("电度表异常故障", 4), FAULT_5("读卡器通讯异常故障", 5), FAULT_6(
			"急停按钮动作故障", 6), FAULT_7("交流接触器故障", 7), FAULT_8("交易记录已满告警", 8), FAULT_9(
			"交流输出过流告警", 9), FAULT_10("交流输入过压告警", 10), FAULT_11("交流输入欠压告警", 11), FAULT_12(
			"充电接口电子锁故障", 12), FAULT_13("充电接口过温故障", 13), FAULT_14("PE 断线故障", 14), FAULT_15(
			"交流输出过流保护动作", 15), FAULT_16("充电桩其他故障", 16), FAULT_17("TCU 其他故障", 17), FAULT_18(
			"CANPLIE 通讯故障", 18), FAULT_19("ESAM 故障", 19), FAULT_20(
			"TCU 与控制器通讯故障", 20), FAULT_21("交流断路器故障", 21);

	// 成员变量
	private String desc;
	private int code;

	// 构造方法
	private ACFaultEnum(String desc, int code) {
		this.code = code;
		this.desc = desc;
	}

	// 普通方法
	public static String getName(int code) {
		for (ACFaultEnum c : ACFaultEnum.values()) {
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
