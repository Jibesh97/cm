package com.horizon.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.sql.DataSource;
import javax.websocket.Session;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;


/**
 * <P>
 * FileName: SysContextUtil.java
 * 
 * <P>
 * CreateTime: 2012-09-26
 * <P>
 * Description: 系统框架上下文环境相关的工具方法
 * <P>
 * Version:v1.0
 * <P>
 * History:
 */
public class SysContextUtil {

	/**
     * 
     */
	private static Logger logger = Logger.getLogger(SysContextUtil.class);
	/**
	 * 定义Spring 配置文件路径
	 */
	private static final String APPLICATION_CONTEXT_FILE = "classpath:application-config.xml";

	/**
     * 
     */
	// private String defaultDsName = "dataSource";

	/**
     * 
     */
	private String defaultJdbcTemplateName = "jdbcTemplate";

	/**
     * 
     */
	// private DataSource defaultDs;

	/**
     * 
     */
	private ApplicationContext ctx;

	/**
	 * 
	 */
	// private Map<String, SimpleJdbcInsert> insertObjMap = new HashMap<String,
	// SimpleJdbcInsert>();

	private static Map<String, SimpleJdbcInsert> insertObjMap = new HashMap<String, SimpleJdbcInsert>();

	/**
     * 
     */
	private static SysContextUtil instance = new SysContextUtil();

	/**
     * 
     * 
     */
	private SysContextUtil() {

	}

	/**
	 * 
	 * @param ds
	 */
	// private SysContextUtil(DataSource ds) {
	// this.defaultDs = ds;
	// }

	/**
	 * 系统初始化
	 */
	public static synchronized void init() {
		init(null);
	}

	/**
	 * 系统初始化
	 */
	public static synchronized void init(final ApplicationContext ctx) {
		logger.info("into SysContextUtil.init method begin ....");
		instance.ctx = ctx;
		if (null == instance.ctx) {
			logger.info("初始化spring context 对象...");
			instance.ctx = new ClassPathXmlApplicationContext(
					APPLICATION_CONTEXT_FILE);
		}
		// if (null == instance.defaultDs) {
		// logger.info("初始化系统默认数据源 dataSource 对象...");
		// instance.defaultDs = (DataSource) instance.ctx
		// .getBean(instance.defaultDsName);
		// }
		logger.info("SysContextUtil.init method end ....");
	}

	/**
	 * 获取Spring 框架管理的bean对象
	 * 
	 * @param beanName
	 * @return Object
	 */
	public static ApplicationContext getSpringApplicationContext() {
		if (logger.isDebugEnabled()) {
			logger.debug("into SysContextUtil.getSpringApplicationContext method begin ....");
		}
		if (null == instance.ctx) {
			init();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("SysContextUtil.getSpringApplicationContext method end ....");
		}
		return instance.ctx;
	}

	/**
	 * 获取Spring 框架管理的bean对象
	 * 
	 * @param beanName
	 * @return Object
	 */
	public static Object getSpringBeanObject(String beanName) {
		if (logger.isDebugEnabled()) {
			logger.debug("into SysContextUtil.getSpringBeanObject method begin ....");
		}
		ApplicationContext ctx = instance.ctx;
		if (null == instance.ctx) {
			logger.info("SysContext 由于未进行初始化,开始初始化上下文环境 ....");
			init();
			ctx = instance.ctx;
		}
		if (null == ctx) {
			return null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("SysContextUtil.getSpringBeanObject method end ....");
		}
		return ctx.getBean(beanName);
	}

	/**
	 * 创建Spring SimpleJdbcTemplate
	 * 
	 * @param dsName
	 * @return SimpleJdbcTemplate
	 */
	public static JdbcTemplate createJdbcTemplate() {
		if (logger.isDebugEnabled()) {
			logger.debug("into createJdbcTemplate method begin ....");
		}
		if (null == instance.ctx) {
			init();
		} else {
			JdbcTemplate jdbcTemplate = (JdbcTemplate) instance.ctx
					.getBean(instance.defaultJdbcTemplateName);
			if (logger.isDebugEnabled()) {
				logger.debug("createSimpleJdbcTemplate method end ....");
			}
			return jdbcTemplate;
		}
		
		return null;
	}

	/**
	 * 获取JdbcTemplate
	 * 
	 * @return JdbcTemplate
	 */
	public static JdbcTemplate getConfigedSimpleJdbcTemplate() {
		if (logger.isDebugEnabled()) {
			logger.debug("into getConfigedSimpleJdbcTemplate method begin ....");
			logger.debug("get a getConfigedSimpleJdbcTemplate Object!");
		}
		JdbcTemplate jdbcTemplate = null;
		if (null == instance.ctx) {
			jdbcTemplate = createJdbcTemplate();
		} else {
			jdbcTemplate = (JdbcTemplate) instance.ctx
					.getBean(instance.defaultJdbcTemplateName);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getConfigedSimpleJdbcTemplate method end ....");
		}
		return jdbcTemplate;
	}

	/**
	 * 
	 * @param tableName
	 * @param KeyColumn
	 * @param ds
	 * @return
	 */
	public static SimpleJdbcInsert getSimpleJdbcInsert(String tableName,
			String KeyColumn, DataSource ds) {
		SimpleJdbcInsert simpleJdbcInsert = insertObjMap.get(tableName);
		if (null == simpleJdbcInsert) {
			simpleJdbcInsert = new SimpleJdbcInsert(ds)
					.withTableName(tableName).usingGeneratedKeyColumns(
							KeyColumn);
			insertObjMap.put(tableName, simpleJdbcInsert);
		}
		return simpleJdbcInsert;
	}
	
	/**
	 * 
	 * @param tableName
	 * @param KeyColumn
	 * @param ds
	 * @return
	 */
	public static SimpleJdbcInsert getSimpleJdbcInsert(String tableName, DataSource ds) {
		SimpleJdbcInsert simpleJdbcInsert = insertObjMap.get(tableName);
		if (null == simpleJdbcInsert) {
			simpleJdbcInsert = new SimpleJdbcInsert(ds)
					.withTableName(tableName);
			insertObjMap.put(tableName, simpleJdbcInsert);
		}
		return simpleJdbcInsert;
	}
	
	/**
	 * 主函数用于测试
	 */
	public static void main(String[] args) {
		SysContextUtil.init();
	}

}
