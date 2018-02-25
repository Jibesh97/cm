package com.horizon.monitoring.map.web;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.monitoring.alarm.websocket.RspObjGetter;
import com.horizon.monitoring.map.vo.MapResponse;
import com.horizon.sm.user.service.ISysUserManagerService;
import com.horizon.sm.user.vo.TokenVO;

/**
 * 
 * @author liw
 *
 * @param <T>
 */
@Controller
@RequestMapping("monitoring/map")
public class MapController<T> {
	/**
	 * 用于输出log
	 */
	private static Logger log = Logger.getLogger(MapController.class);

	/**
	 * 注入service对象
	 */
	@Autowired
	private ISysUserManagerService sysUserService;

	public void setSysUserService(ISysUserManagerService sysUserService) {
		this.sysUserService = sysUserService;
	}

	/**
	 * 地图map页初始化跳转
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/initMapPage")
	public String initMapPage(HttpServletRequest request) throws Exception {
		log.info("Start SysUserController --> initMapPage()");

		// 页面websocket的路径
		request.setAttribute("urlGloble", ConstantsInfo.MAP_WEBSOCKET);

		// 初始化时需要查询默认显示的城市

		TokenVO vo = (TokenVO) request.getSession().getAttribute("token");

		try {
			request.setAttribute("city", vo.getSysOrg());

			request.setAttribute("refreshTime", 30000);

			request.setAttribute("jurisdiction", vo.getProvinceid());
		} catch (Exception e) {
			request.setAttribute("city", "辽宁省");
			request.setAttribute("refreshTime", 30000);

			request.setAttribute("jurisdiction", "210000,220000");
		}

		return "monitoring/map/map";
	}

	/**
	 * 地图mapIndex页初始化跳转
	 * 
	 * @param address
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "map")
	public String map(String city, HttpServletRequest request)
			throws UnsupportedEncodingException {

		request.setAttribute("city", city); // 初始化时显示的城市
		return "monitoring/map/mapIndex";
	}

	/**
	 * 地图页面数据获取
	 * 
	 * @param request
	 * @param orgIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchMap")
	@ResponseBody
	public MapResponse searchMap(HttpServletRequest request, String orgIds)
			throws Exception {
		log.info("Start AlarmController --> search()");

		String[] ids = orgIds.split(",");

		MapResponse m = RspObjGetter.getMapInfo(ids);

		return m;
	}

}
