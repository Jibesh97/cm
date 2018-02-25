package com.horizon.monitoring.station.web;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.horizon.common.util.JsonUtil;
import com.horizon.monitoring.alarm.websocket.RspObjGetter;
import com.horizon.monitoring.station.vo.StationResponse;
import com.horizon.sm.user.vo.TokenVO;

/**
 * <P>
 * FileName: StationController.java
 * 
 * @author lzd
 *         <P>
 *         CreateTime: 2017-05-18 上午10:46:50
 *         <P>
 *         Description: 充电站
 *         <P>
 *         Version:v1.0
 *         <P>
 *         History:
 */
@Controller
@RequestMapping("monitoring/station")
public class StationController<T> {
	/**
	 * 用于输出log
	 */
	private static Logger log = Logger.getLogger(StationController.class);
	
	/**
	 * <P>
	 * Function: initLogin
	 * <P>
	 * Description:初始化站页面
	 * <P>
	 * Others:
	 * 
	 * @author liuzd
	 * @param request
	 * @param id 要获取的站的 uuid
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/initStationPage")
	public String initMapPage(HttpServletRequest request,String id) throws Exception {
		log.info("Start StationController --> initStationPage()");
		TokenVO vo = (TokenVO) request.getSession().getAttribute("token");
		request.setAttribute("stationCode", id);
		request.setAttribute("mechanismCode", vo.getProvinceid());
		//跳转站页面
		return "monitoring/station/station";
	} 
	
	/**
	 * <P>
	 * Function: initLogin
	 * <P>
	 * Description:查询数据
	 * <P>
	 * Others:
	 * 
	 * @author liuzd
	 * @param request 请求
	 * @param stationCode 要获取的站的 id
	 * @param mechanismCode 要获机构权限 的id
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "throughIdQuery")
	@ResponseBody
	public String throughIdQuery(HttpServletRequest request,String stationCode,String mechanismCode) throws Exception {
		log.info("Start StationController --> throughIdQuery()");
		//redis中取站信息
		StationResponse srp = RspObjGetter.getStationDetail(mechanismCode,stationCode);
		//转换json串
		return JsonUtil.genJsonString(srp);
	} 
}
