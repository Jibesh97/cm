package com.horizon.monitoring.pile.web;



import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.horizon.common.constants.ConstantsInfo;
import com.horizon.common.util.JsonIoConvertor;
import com.horizon.common.util.JsonUtil;
import com.horizon.monitoring.alarm.websocket.RspObjGetter;
import com.horizon.monitoring.pile.vo.ParVO;

/**
 * <P>
 * FileName: StationController.java
 * 
 * @author lzd
 *         <P>
 *         CreateTime: 2017-05-17 上午11:46:50
 *         <P>
 *         Description: 充电桩
 *         <P>
 *         Version:v1.0
 *         <P>
 *         History:
 */
@Controller
@RequestMapping("monitoring/pile")
public class PileController<T> { 
	/**
	 * 用于输出log
	 */
	private static Logger log = Logger.getLogger(PileController.class);
	
	/**
	 * <P>
	 * Function: initLogin
	 * <P>
	 * Description:初始化桩页面
	 * <P>
	 * Others:
	 * 
	 * @author liuzd
	 * @param request
	 * @param map 站页面信息传递桩页面信息
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/initPilePage")
	public String initPilePage(HttpServletRequest request,String map) throws Exception {
		log.info("Start PileController --> initPilePage()");
		request.setAttribute("urlGloble", ConstantsInfo.PILE_WEBSOCKET);
		//转码
		ParVO parvo;
		try {
			parvo = (ParVO) new JsonIoConvertor().read(map, ParVO.class);
			String name = java.net.URLDecoder.decode(parvo.getName(),"UTF-8");
			//直流桩名字 	A组直流桩
			request.setAttribute("name", name);
			//当前桩ID
			request.setAttribute("pileid", parvo.getPileid());
			//当前桩所属站ID         
			request.setAttribute("stationCode", parvo.getStationCode());
			//当前桩所属机构ID
			request.setAttribute("mechanismCode", parvo.getMechanismCode());
			//直流桩下的指定站 A1
			request.setAttribute("content", parvo.getContent());
		} catch (Throwable e) {
			log.error(e.getMessage());
		}
		return "monitoring/pile/pile";
	} 
	
	/**
	 * <P>
	 * Function: initLogin
	 * <P>
	 * Description:通过 机构ID 站ID 桩ID查询桩的详细信息
	 * <P>
	 * Others:
	 * 
	 * @author liuzd
	 * @param stationCode 要获取的站的 uuid
	 * @param mechanismCode 要获取机构权限的 id
	 * @param pileid 要获取桩 id
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "throughIdQuery")
	@ResponseBody
	public String throughIdQuery(HttpServletRequest request,String pileid,String stationCode,String mechanismCode) throws Exception {
		log.info("Start PileController --> throughIdQuery()");
		return JsonUtil.genJsonString(RspObjGetter.getPileDetail(mechanismCode, stationCode,pileid));
	} 
}
