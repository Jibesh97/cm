package com.horizon.common.mq.observer;
import com.horizon.common.util.JsonUtil;

public class test {

	public static void main(String[] args) throws Exception {

		String json1 = "{\"chargingPileNum\":\"CDJ000001\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":\"0\", \"faultWarningNum\":0, \"status\":2, \"sampTime\":201705}";
		System.out.println(json1);
		 AlarmInfo a = (AlarmInfo)JsonUtil.genObjectFromJsonString(json1,
		 AlarmInfo.class);
		 System.out.println(a.getFaultStatus());
		 System.out.println(a.getWarningStatus());
		String jsonObject = JsonUtil.getField(json1, "chargingPileNum");
		System.out.println(jsonObject);
	}

}
