package com.horizon.common.constants;

public class ConstantsInfo {

	/************** 常用变量 ********************/

	
    /**
     * user session key
     */
    public static final String USER_TOKEN = "TOKEN";
    
    /**
     * separator
     */
    public static final int DATE_FORMAT_SEPARATOR_INDEX = 10;

    /**
     * 
     */
    public static final int DATATIME_FORMAT_SEPARATOR_INDEX = 19;
    
    /**
     * 向前台推送消息的时间间隔
     */
    public static final long TIMER_PERIOD = 10 * 1000;//单位毫秒
    
    public static final String ALARM_WEBSOCKET = "alarm";
    
    public static final String PILE_WEBSOCKET = "pile";
    
    public static final String MAP_WEBSOCKET = "map";

    public static final String STATION_WEBSOCKET = "station";
    
    /**
     * 直流充电桩充电 tag
     */
    public static final String FES_DCCHARGE_TAG = "fes_dccharge_tag";
    /**
     * 直流充电桩非充电 tag
     */
    public static final String FES_DCNOCHARGE_TAG = "fes_dcnocharge_tag";
    /**
     * 交流充电桩充电 tag
     */
    public static final String FES_ACCHARGE_TAG = "fes_accharge_tag";
    /**
     * 交流充电桩非充电 tag
     */
    public static final String FES_ACNOCHARGE_TAG = "fes_acnocharge_tag";
    /**
     * 故障告警  tag
     */
    public static final String FES_FAULTALARM_TAG = "fes_faultalarm_tag";
    /**
     * 充电桩工作状态 tag
     */
    public static final String FES_WORKSTATUS_TAG = "fes_workstatus_tag";
    
    /**
     * 机构--站--状态  机构后+_Z
     */
    public static final String MECHANISM_STATION_STATUS_Z = "_Z";
    /**
     * 父机构找子机构的需求时的key要加_C
     */
    public static final String CHILD_SUF = "_C";
    /**
     * 查询机构本身的信息加后缀_I
     */
    public static final String ORG_INFO_SUF = "_I";
    /**
     * 机构json的“区”字段的名称
     */
    public static final String ORG_DISTRICT = "districtCode";
    /**
     * 工作状态     0001-故障，
     */
    public static final String WORKSTATUS_ALARM = "0001";
    /**
     * 工作状态 0002-待机
     */
    public static final String WORKSTATUS_FREE = "0002";
    /**
     * 工作状态 0003-工作 
     */
    public static final String WORKSTATUS_WORK = "0003";
    /**
     * 工作状态  0004-离线
     */
    public static final String WORKSTATUS_OFF_LINE = "0004";
    /**
     * 工作状态  0005-充满
     */
    public static final String WORKSTATUS_FULL = "0005";
    
    /**
     * 工作状态     0001-故障，
     */
    public static final int WORKSTATUS_ALARM_INT = 0001;
    /**
     * 工作状态 0002-待机
     */
    public static final int WORKSTATUS_FREE_INT = 0002;
    /**
     * 工作状态 0003-工作 
     */
    public static final int WORKSTATUS_WORK_INT = 0003;
    /**
     * 工作状态  0004-离线
     */
    public static final int WORKSTATUS_OFF_LINE_INT = 0004;
    /**
     * 工作状态  0005-充满
     */
    public static final int WORKSTATUS_FULL_INT = 0005;
    
    /**
     * HSF 监控信息
     */
    public static final String HSF_MONITOR = "MONITOR";
    /**
     * HSF 告警状态
     */
    public static final String HSF_ALARM = "ALARM";
    /**
     * HSF 状态
     */
    public static final String HSF_STATUS = "STATUS";
    /**
     * 桩的告警状态 否
     */
    public static final int PILE_ALERM_NO = 0;
    /**
     * 桩的告警状态 是
     */
    public static final int PILE_ALERM_IS = 1;
    /**
     * 桩是直流
     */
    public static final String PILE_POWER_SUPPLY_DC = "DC";
    /**
     * 桩是交流
     */
    public static final String PILE_POWER_SUPPLY_AC = "AC";
    
    //redis sub pub channel
    public static final String CHANNEL_WS_PIPLE = "ChargingPileStatus";
    
//    public static final String HSF_STATUS = "STATUS";
}
