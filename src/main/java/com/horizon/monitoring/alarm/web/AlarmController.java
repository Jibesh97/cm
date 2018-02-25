package com.horizon.monitoring.alarm.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.monitoring.alarm.service.IAlarmService;
import com.horizon.monitoring.alarm.vo.AlarmVO;
import com.horizon.monitoring.alarm.vo.OrderObject;

/**
 * 故障告警.
 * 
 * @author liy.
 * @param <T>.
 */
@Controller
@RequestMapping("monitoring/alarm")
public class AlarmController<T> {

	/**
	 * 用于输出log.
	 */
	private static Logger log = Logger.getLogger(AlarmController.class);

	/**
	 * 注入service对象
	 */
	@Autowired
	private IAlarmService alarmService;

	/**
	 * 初始化故障告警页面
	 * 
	 * @author liy
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/initAlarmPage")
	public String initMainPage(HttpServletRequest request) throws Exception {
		log.info("Start AlarmController --> initAlarmPage()");
		// 页面websocket的路径
		request.setAttribute("urlGloble", ConstantsInfo.ALARM_WEBSOCKET);
		request.setAttribute("jurisdiction", "110000,111100");
		return "monitoring/alarm/AlarmList";
	}

	/**
	 * 振伟测试页面
	 * 
	 * @author liy
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/initTest")
	public String initTest(HttpServletRequest request,HttpServletResponse res) throws Exception {
		log.info("Start AlarmController --> initAlarmPage()");
		log.info(request.getSession().getId());
		java.util.Enumeration<String> attributes = request.getSession().getAttributeNames();
		while(attributes.hasMoreElements()){
			log.info(request.getSession().getAttribute(attributes.nextElement()));
		}
		log.info("1");
		// 页面websocket的路径
		request.setAttribute("urlGloble", "alarm");
		request.setAttribute("orgId", request.getParameter("orgId"));
		return "monitoring/alarm/AlarmTest";
	}

	/**
	 * 告警列表页面初始化查询
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public AlarmVO search(HttpServletRequest request, AlarmVO vo)
			throws Exception {
		log.info("Start AlarmController --> search()");

		AlarmVO alarmVO = new AlarmVO();

		alarmVO = alarmService.getAlarmVO(vo);

		return alarmVO;
	}

	/**
	 * 查询分页的总数
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPageAllNumber")
	@ResponseBody
	public AlarmVO getPageAllNumber(HttpServletRequest request, AlarmVO vo)
			throws Exception {
		log.info("Start AlarmController --> search()");

		AlarmVO alarmVO = new AlarmVO();

		alarmVO = alarmService.getPageAllNumber(vo);

		return alarmVO;
	}
	
	
	/**
	 * 确认
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/affirm")
	@ResponseBody
	public AlarmVO affirm(HttpServletRequest request, AlarmVO vo)
			throws Exception {
		log.info("Start AlarmController --> search()");

		AlarmVO alarmVO = new AlarmVO();
		 
		List<String> ruleList = JSONArray.parseArray(vo.getIdsJSON(), String.class);

		vo.setIds(ruleList);
		
		alarmVO = alarmService.updateAllAffirm(vo);

		return alarmVO;
	}
	
	
	/**
	 * 工单
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/workOrderPayout")
	@ResponseBody
	public AlarmVO workOrderPayout(HttpServletRequest request, String json)
			throws Exception {
		log.info("Start AlarmController --> search()");

		List<OrderObject> list = JSONArray.parseArray(json,OrderObject.class);
		
		
		AlarmVO OrderObjectList=alarmService.updateWorkOrderPayout(list);
		
	 
		 

		return OrderObjectList;
	}

	
	
}
