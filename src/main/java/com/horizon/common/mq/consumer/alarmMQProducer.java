package com.horizon.common.mq.consumer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;

public class alarmMQProducer {
	 public static void main(String[] args) {
	        Properties producerProperties = new Properties();
	        producerProperties.setProperty(PropertyKeyConst.ProducerId, "PID_MONITOR_MSG_L");
	        producerProperties.setProperty(PropertyKeyConst.AccessKey, "LTAI7qxXEJhiKf6F");
	        producerProperties.setProperty(PropertyKeyConst.SecretKey, "rK92txvpdUgNEOijBhEzY445qq8fgz");
	        producerProperties.setProperty(PropertyKeyConst.ONSAddr, "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
	        Producer producer = ONSFactory.createProducer(producerProperties);
	        producer.start();
	        System.out.println("故障告警信息    Producer Started");
	        
	        //直流充电桩充电监测信息数据
	        String json1 = "{\"chargingPileNum\":\"a6b4fefd0f4040e19c202223e29e2e3a\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":8,\"value\":1},{\"indexNum\":9,\"value\":1}], \"status\":2, \"sampTime\":1494664247085}" ;
	        String json2 = "{\"chargingPileNum\":\"a6b4fefd0f4040e19c202223e29e2e3a\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":1494664247085}" ;
	        String json3 = "{\"chargingPileNum\":\"a6b4fefd0f4040e19c202223e29e2e3a\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":1494664247085}" ;
	        String json4 = "{\"chargingPileNum\":\"a6b4fefd0f4040e19c202223e29e2e3a\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":1494664247085}" ;
	        String json5 = "{\"chargingPileNum\":\"a6b4fefd0f4040e19c202223e29e2e3a\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":1494664247085}" ;
	        String json6 = "{\"chargingPileNum\":\"a6b4fefd0f4040e19c202223e29e2e3a\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":1494664247085}" ;
	        String json7 = "{\"chargingPileNum\":\"a6b4fefd0f4040e19c202223e29e2e3a\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":1494664247085}" ;
	        String json8 = "{\"chargingPileNum\":\"a6b4fefd0f4040e19c202223e29e2e3a\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":1494664247085}" ;
	        String json9 = "{\"chargingPileNum\":\"ZZ00009\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json10 = "{\"chargingPileNum\":\"ZZ00010\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json11 = "{\"chargingPileNum\":\"ZZ00011\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json12 = "{\"chargingPileNum\":\"ZZ00012\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json13 = "{\"chargingPileNum\":\"ZZ00013\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json14 = "{\"chargingPileNum\":\"ZZ00014\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json15 = "{\"chargingPileNum\":\"ZZ00015\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json16 = "{\"chargingPileNum\":\"ZZ00016\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json17 = "{\"chargingPileNum\":\"ZZ00017\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json18 = "{\"chargingPileNum\":\"ZZ00018\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json19 = "{\"chargingPileNum\":\"ZZ00019\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json20 = "{\"chargingPileNum\":\"ZZ00020\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json21 = "{\"chargingPileNum\":\"ZZ00021\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json22 = "{\"chargingPileNum\":\"ZZ00022\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json23 = "{\"chargingPileNum\":\"ZZ00023\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json24 = "{\"chargingPileNum\":\"ZZ00024\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json25 = "{\"chargingPileNum\":\"ZZ00025\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json26 = "{\"chargingPileNum\":\"ZZ00026\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json27 = "{\"chargingPileNum\":\"ZZ00027\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json28 = "{\"chargingPileNum\":\"ZZ00028\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json29 = "{\"chargingPileNum\":\"ZZ00029\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json30 = "{\"chargingPileNum\":\"ZZ00030\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json31 = "{\"chargingPileNum\":\"ZZ00031\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json32 = "{\"chargingPileNum\":\"ZZ00032\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json33 = "{\"chargingPileNum\":\"ZZ00033\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json34 = "{\"chargingPileNum\":\"ZZ00034\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json35 = "{\"chargingPileNum\":\"ZZ00035\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json36 = "{\"chargingPileNum\":\"ZZ00036\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json37 = "{\"chargingPileNum\":\"ZZ00037\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json38 = "{\"chargingPileNum\":\"ZZ00038\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json39 = "{\"chargingPileNum\":\"ZZ00039\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json40 = "{\"chargingPileNum\":\"ZZ00040\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json41 = "{\"chargingPileNum\":\"ZZ00041\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json42 = "{\"chargingPileNum\":\"ZZ00042\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json43 = "{\"chargingPileNum\":\"ZZ00043\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json44 = "{\"chargingPileNum\":\"ZZ00044\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json45 = "{\"chargingPileNum\":\"ZZ00045\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json46 = "{\"chargingPileNum\":\"ZZ00046\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json47 = "{\"chargingPileNum\":\"ZZ00047\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json48 = "{\"chargingPileNum\":\"ZZ00048\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json49 = "{\"chargingPileNum\":\"ZZ00049\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        String json50 = "{\"chargingPileNum\":\"ZZ00050\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
	        
//	        String json9 = "{\"chargingPileNum\":\"ZZ00009\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json10 = "{\"chargingPileNum\":\"ZZ00010\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json11 = "{\"chargingPileNum\":\"ZZ00011\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json12 = "{\"chargingPileNum\":\"ZZ00012\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json13 = "{\"chargingPileNum\":\"ZZ00013\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json14 = "{\"chargingPileNum\":\"ZZ00014\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json15 = "{\"chargingPileNum\":\"ZZ00015\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json16 = "{\"chargingPileNum\":\"ZZ00016\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json17 = "{\"chargingPileNum\":\"ZZ00017\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json18 = "{\"chargingPileNum\":\"ZZ00018\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json19 = "{\"chargingPileNum\":\"ZZ00019\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json20 = "{\"chargingPileNum\":\"ZZ00020\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json21 = "{\"chargingPileNum\":\"ZZ00021\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json22 = "{\"chargingPileNum\":\"ZZ00022\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json23 = "{\"chargingPileNum\":\"ZZ00023\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json24 = "{\"chargingPileNum\":\"ZZ00024\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json25 = "{\"chargingPileNum\":\"ZZ00025\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json26 = "{\"chargingPileNum\":\"ZZ00026\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json27 = "{\"chargingPileNum\":\"ZZ00027\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json28 = "{\"chargingPileNum\":\"ZZ00028\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json29 = "{\"chargingPileNum\":\"ZZ00029\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json30 = "{\"chargingPileNum\":\"ZZ00030\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json31 = "{\"chargingPileNum\":\"ZZ00031\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json32 = "{\"chargingPileNum\":\"ZZ00032\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json33 = "{\"chargingPileNum\":\"ZZ00033\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json34 = "{\"chargingPileNum\":\"ZZ00034\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json35 = "{\"chargingPileNum\":\"ZZ00035\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json36 = "{\"chargingPileNum\":\"ZZ00036\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json37 = "{\"chargingPileNum\":\"ZZ00037\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json38 = "{\"chargingPileNum\":\"ZZ00038\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json39 = "{\"chargingPileNum\":\"ZZ00039\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json40 = "{\"chargingPileNum\":\"ZZ00040\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json41 = "{\"chargingPileNum\":\"ZZ00041\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json42 = "{\"chargingPileNum\":\"ZZ00042\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json43 = "{\"chargingPileNum\":\"ZZ00043\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json44 = "{\"chargingPileNum\":\"ZZ00044\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json45 = "{\"chargingPileNum\":\"ZZ00045\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json46 = "{\"chargingPileNum\":\"ZZ00046\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json47 = "{\"chargingPileNum\":\"ZZ00047\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json48 = "{\"chargingPileNum\":\"ZZ00048\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json49 = "{\"chargingPileNum\":\"ZZ00049\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        String json50 = "{\"chargingPileNum\":\"ZZ00050\", \"chargingPileInterfaceNum\":0, \"faultStatus\":1, \"warningStatus\":1, \"faultWarningNum\":1, \"indexValue\":[{\"indexNum\":1,\"value\":1},{\"indexNum\":1,\"value\":1}], \"status\":2, \"sampTime\":201705}" ;
//	        
	        //将数据放入list中
	        List<String> dcchargeList = new ArrayList<String>();
	        dcchargeList.add(json1);
	        dcchargeList.add(json2);
	        dcchargeList.add(json3);
	        dcchargeList.add(json4);
	        dcchargeList.add(json5);
	        dcchargeList.add(json6);
	        dcchargeList.add(json7);
	        dcchargeList.add(json8);
	        dcchargeList.add(json9);
	        dcchargeList.add(json10);
	        dcchargeList.add(json11);
	        dcchargeList.add(json12);
	        dcchargeList.add(json13);
	        dcchargeList.add(json14);
	        dcchargeList.add(json15);
	        dcchargeList.add(json16);
	        dcchargeList.add(json17);
	        dcchargeList.add(json18);
	        dcchargeList.add(json19);
	        dcchargeList.add(json20);
//	        dcchargeList.add(json21);
//	        dcchargeList.add(json22);
//	        dcchargeList.add(json23);
//	        dcchargeList.add(json24);
//	        dcchargeList.add(json25);
//	        dcchargeList.add(json26);
//	        dcchargeList.add(json27);
//	        dcchargeList.add(json28);
//	        dcchargeList.add(json29);
//	        dcchargeList.add(json30);
//	        dcchargeList.add(json31);
//	        dcchargeList.add(json32);
//	        dcchargeList.add(json33);
//	        dcchargeList.add(json34);
//	        dcchargeList.add(json35);
//	        dcchargeList.add(json36);
//	        dcchargeList.add(json37);
//	        dcchargeList.add(json38);
//	        dcchargeList.add(json39);
//	        dcchargeList.add(json40);
//	        dcchargeList.add(json41);
//	        dcchargeList.add(json42);
//	        dcchargeList.add(json43);
//	        dcchargeList.add(json44);
//	        dcchargeList.add(json45);
//	        dcchargeList.add(json46);
//	        dcchargeList.add(json47);
//	        dcchargeList.add(json48);
//	        dcchargeList.add(json49);
//	        dcchargeList.add(json50);

	        

	        for (int i = 0; i < 1; i++) {
	            Message message = new Message("TOPIC_FES_ALARM_LI", "fes_faultalarm_tag", dcchargeList.get(i).getBytes());
	            SendResult sendResult = producer.send(message);
	            if (sendResult != null) {
	                System.out.println(new Date() + " Topic:" + "TOPIC_FES_ALARM_LI" + " TAG:" + "fes_faultalarm_tag" + " msgId: " + sendResult.getMessageId() + " body:~"+new  String(message.getBody()));
	            }
	        }
	    }
}
