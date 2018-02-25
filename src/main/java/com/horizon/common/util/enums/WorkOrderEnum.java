package com.horizon.common.util.enums;

public enum WorkOrderEnum {
	/**
	 * 工单状态码
	 */
	WORKORDER_1("已新建未派发", 1), WORKORDER_2("派发未受理", 2), WORKORDER_3("已受理未完成", 3), WORKORDER_4("已处理完成", 4)
	, WORKORDER_5("驳回", 5), WORKORDER_6("未领取", 6), WORKORDER_7("已领取未执行", 7), WORKORDER_8("已领取执行中", 8)
	, WORKORDER_9("已完成", 9), WORKORDER_10("被驳回", 10), WORKORDER_11("待接单", 11), WORKORDER_12("退单", 12)
	, WORKORDER_13("待办结", 13), WORKORDER_99("已保存未提交", 99);

	// 成员变量
	private String desc;
	private int code;
	
    // 构造方法
	private WorkOrderEnum(String desc, int code) {
		this.code = code;
		this.desc = desc;
	}

	// 普通方法
	public static String getName(int code) {
		for (WorkOrderEnum w : WorkOrderEnum.values()) {
			if (w.getCode() == code) {
				return w.desc;
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
