package com.horizon.common.init;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.horizon.common.base.RedisClientDao;
import com.horizon.common.constants.ConstantsInfo;
import com.horizon.common.redis.pubsub.RedisMsgPubSubListener;
import com.horizon.common.util.DateUtil;
import com.horizon.common.util.SysContextUtil;

/**
 * <P>
 * FileName: SysInit.java
 * 
 * @author
 *         <P>
 *         CreateTime: 2017-05-26
 *         <P>
 *         Description: 系统自定义组件初始化加载入口
 *         <P>
 *         Version:v1.0
 *         <P>
 *         History:
 */
public class SysInit extends HttpServlet {
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(SysInit.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -8632618512999044841L;

	/**
	 * Constructor of the object.
	 */
	public SysInit() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		logger.info("开始进行系统组件初始化....");
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		logger.info("开始进行系统上下文环境初始化....");

		SysContextUtil.init(ctx);
		// addby liy
		// 初始化资产信息
		// OrgTreeInitData.InitMap.init(ctx);
		// 初始化充电桩实时监测信息
		// PileMonitorInitData.InitMap.init(ctx);

		// StrutsContextUtil.init(this.getServletContext());

		// 测试定时任务
		java.util.Timer timer = new java.util.Timer();
		timer.schedule(new java.util.TimerTask() {
			public void run() {
				logger.info("timer run");
				com.horizon.common.mq.consumer.TestConsumer testConsumer = (com.horizon.common.mq.consumer.TestConsumer) SysContextUtil
						.getSpringBeanObject("testConsumer");
				String json1 = "{\"chargingPileNum\":\"4c53ea5127884a63b8886dd9f8cd9db3\", \"chargingPileInterfaceNum\":0, \"faultStatus\":0, \"warningStatus\":0, \"faultWarningNum\":0, \"indexValue\":[{\"indexNum\":8,\"value\":1},{\"indexNum\":9,\"value\":1}], \"status\":2, \"sampTime\":1494664247085}" ;
				testConsumer.consume(json1);
				
//				String strMessage = "aaaaaaaaaa";
//				com.horizon.common.websocket.WebSocketUtil.sendPileMessage("4c53ea5127884a63b8886dd9f8cd9db3",
//						strMessage);
			}
		}, 0, 10 * 1000);

		Date curDate = DateUtil.getDBDate();

		logger.info("系统初始化当前时间为：" + curDate);

		logger.info("系统组件初始化结束....");

		final RedisClientDao redisClientDAO = (RedisClientDao) ctx.getBean("redisClientDao");
		// 启动线程监听推送消息

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				redisClientDAO.subscribe(new RedisMsgPubSubListener(), ConstantsInfo.CHANNEL_WS_PIPLE);
			}
		});
		t.start();

	}

}
