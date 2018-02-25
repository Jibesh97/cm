//package com.horizon.common.util;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import com.horizon.common.base.GenericVO;
//import com.horizon.common.util.bean.DwzPageBean;
//import com.horizon.common.util.sql.SqlBuilder;
//
//public class PageUtil {
//	/**
//	 * 日志对象
//	 */
//	public static Log log = LogFactory.getLog(PageUtil.class);
//
//	private PageUtil() {
//	}
//
//	/**
//	 * 获得分页信息,包括每页显示记录数,和要显示的页.
//	 * 当没有得到值时默认值为-1.PagInfo.getPagSize()必须有正确的值.若为-1就无法得到正确结果
//	 * 
//	 * @param req
//	 *            HttpServletRequest
//	 * @return PagInfo
//	 */
//	static public DwzPageBean getPagination(HttpServletRequest req) {
//		DwzPageBean pagination = new DwzPageBean();
//		int pagSize = req.getParameter("pageSize") == null ? 1000 : Integer
//				.parseInt(req.getParameter("pagSize"));
//		int pageNo = req.getParameter("pageNum") == null ? -1 : Integer
//				.parseInt(req.getParameter("pageNum"));
//		if (log.isDebugEnabled()) {
//			log.debug("pageSize" + pagSize);
//		}
//		pagination.setPageSize(pagSize);
//		pagination.setPageNum(pageNo);
//		return pagination;
//	}
//
//	/**
//	 * 推荐使用] 获得分页信息,包括每页显示记录数,和要显示的页.
//	 * 
//	 * @param req
//	 *            HttpServletRequest
//	 * @param pagSize
//	 *            int
//	 * @return PagInfo
//	 */
//	public static DwzPageBean getPagination(int pageNum, int pagSize) {
//		DwzPageBean pagination = new DwzPageBean();
//		pagination.setPageSize(pagSize);
//		pagination.setPageNum(pageNum);
//		return pagination;
//	}
//
//	/**
//	 * 推荐使用] 获得分页信息,包括每页显示记录数,和要显示的页.
//	 * 
//	 * @param req
//	 *            HttpServletRequest
//	 * @param pagSize
//	 *            int
//	 * @return PagInfo
//	 */
//	static public DwzPageBean getPagination(HttpServletRequest req, int pagSize) {
//		DwzPageBean pagination = new DwzPageBean();
//		pagSize = req.getParameter("pageSize") == null ? pagSize : Integer
//				.parseInt(req.getParameter("pagSize"));
//		int pageNo = req.getParameter("pageNum") == null ? -1 : Integer
//				.parseInt(req.getParameter("pageNum"));
//		pagination.setPageSize(pagSize);
//		pagination.setPageNum(pageNo);
//		return pagination;
//	}
//
//	/**
//	 * 获得分页查询的SQL 获得的SQL仅仅使用于Oracle 用于JDBC方式的分页查询
//	 * 
//	 * @param sql
//	 *            String
//	 * @return String
//	 */
//	public static String getPageSql(SqlBuilder pageSql) {
//
//		return getPageSql4Mysql(pageSql);
//	}
//
//	public static String getPageSql4Oracle(SqlBuilder pageSql) {
//		pageSql.insert(0,
//				" select * from ( select row_.*, rownum rownum_ from ( ");
//		pageSql.append(" )  row_  where rownum <=:toRows) where rownum_ >:fromRows ");
//		String result = pageSql.getSql();
//		if (log.isDebugEnabled()) {
//			log.debug("pageSql=" + result);
//		}
//		return result;
//	}
//
//	public static String getPageSql4Mysql(SqlBuilder pageSql) {
//
//		pageSql.append(" limit :fromRows,:toRows ");
//		String result = pageSql.getSql();
//		if (log.isDebugEnabled()) {
//			log.debug("pageSql=" + result);
//		}
//		return result;
//	}
//
//	/**
//	 * 获得查询数目总数的SQL, 用于JDBC的查询,仅使用于Oracle
//	 * 
//	 * @param sql
//	 *            String
//	 * @return String
//	 */
//	public static String getCountSql(String sql) {
//		sql = deleteOrderBy(sql);
//		StringBuilder pagingSelect = new StringBuilder();
//		pagingSelect.append("select count(1) from (");
//		pagingSelect.append(sql);
//		pagingSelect.append(") paged");
//		return pagingSelect.toString();
//	}
//
//	/**
//	 * 
//	 * 截取传递进来的sql中的最后一个order by
//	 * 
//	 * @return String
//	 */
//	private static String deleteOrderBy(String tempStr) {
//		tempStr = tempStr.replaceAll(" {2,}", " ");
//		String s = tempStr;
//		// 如果首尾对括号，则去掉
//		if ("(".equals(s.substring(0, 1))
//				&& ")".equals(s.substring(s.length() - 1, s.length()))) {
//			tempStr = tempStr.substring(1, s.length() - 1);
//			s = s.substring(1, s.length() - 1);
//		}
//		int i = s.lastIndexOf(" order by ");
//		// 没有order by 直接返回 否则继续
//		if (i != -1) {
//			String temp = "";
//			temp = tempStr.toString().substring(i, s.length() - 1);
//			// 判断最后一个order by之后是否还存在“）” 则不做处理,否则截取最后一个order by之前的子句
//			int count = 0;
//			if (-1 == temp.indexOf("(") && -1 == temp.indexOf(")")) {
//				count++;
//			}
//			// 考虑其他可能的情况，补充if
//			if (count > 0)
//				tempStr = tempStr.substring(0, i);
//		}
//		return tempStr;
//	}
//
//	/**
//	 * 分页查询，返回PageView对象
//	 * 
//	 * @param sql
//	 * @param jdbcTemplate
//	 * @param pageView
//	 * @param mappedClass
//	 * @return
//	 */
//	public static PageView queryForPageView(NamedParameterJdbcTemplate jdbcTemplate,
//			SqlBuilder sql, PageView pageView) {
//		if (null == pageView) {
//			pageView = initPageView();
//		}
//
//		sql.setParam(pageView.getParaMap());
//
//		SqlParameterSource param = sql.getParam();
//
//		setPageViewObject(jdbcTemplate, sql, pageView, param);
//
//		String pagedSql = getPageSql(sql);
//		if (log.isDebugEnabled()) {
//			log.debug("pagedSql=" + pagedSql);
//		}
//
//		List<?> queryList = jdbcTemplate.queryForList(pagedSql, param);
//
//		// 数据过滤
//		filterListForMap(queryList);
//
//		pageView.setData(queryList);
//		return pageView;
//	}
//
//	/**
//	 * 方法名称：filterListForMap 创建日期：Oct 13, 2013 ；
//	 * 方法描述：对查询的结果list进行空数据处理，list中包含元素是Map类型的可以支持。
//	 * 
//	 * @param list
//	 *            void
//	 */
//	public static void filterListForMap(List<?> list) {
//		if (list == null || list.size() == 0) {
//			return;
//		}
//
//		for (int i = 0; i < list.size(); i++) {
//			@SuppressWarnings("unchecked")
//			Map<String,String> map = (Map<String, String>) list.get(i);
//			// log.debug("原来："+map);
//			for (Iterator<?> it = map.keySet().iterator(); it.hasNext();) {
//				String key = it.next().toString();
//				if (map.get(key) == null
//						|| map.get(key).toString().indexOf("null") > -1
//						|| map.get(key).toString().indexOf("NULL") > -1) {
//					map.put(key, "");
//				}
//			}
//			// log.debug("之后："+map);
//		}
//
//	}
//
//	public static void setPageViewObject(NamedParameterJdbcTemplate jdbcTemplate,
//			SqlBuilder sql, PageView pageView, SqlParameterSource param) {
//		int totalSize = getTotalCount(sql, jdbcTemplate, param);
//
//		// pageView.setTotalSize(totalSize);
//		pageView.getPagination().setTotalCount(totalSize);
//
//		pageView.getVo().setFromRows(
//				pageView.getPagination().getFirstElementOnPage());
//		pageView.getVo().setToRows(pageView.getPagination().getNumPerPage());
//	}
//
//	/**
//	 * 获取总记录数
//	 * 
//	 * @param sql
//	 * @param jdbcTemplate
//	 * @param param
//	 * @return
//	 */
//	public static int getTotalCount(SqlBuilder sql,
//			NamedParameterJdbcTemplate jdbcTemplate, SqlParameterSource param) {
//		String countSql = getCountSql(sql.getSql());
//		if (log.isDebugEnabled()) {
//			log.debug("countSql=" + countSql);
//		}
//		int totalSize = jdbcTemplate.queryForObject(countSql, param,Integer.class);
//		return totalSize;
//	}
//
//	/**
//	 * 分页查询，返回PageView对象
//	 * 
//	 * @param sql
//	 * @param jdbcTemplate
//	 * @param pageView
//	 * @param mappedClass
//	 * @return
//	 */
//	public static PageView queryForPageView(NamedParameterJdbcTemplate jdbcTemplate,
//			SqlBuilder sql, PageView pageView, Class<?> mappedClass) {
//		if (null == pageView) {
//			pageView = initPageView();
//		}
//
//		setPageViewObject(jdbcTemplate, sql, pageView, sql.getParam());
//
//		String pagedSql = getPageSql(sql);
//		if (log.isDebugEnabled()) {
//			log.debug("pagedSql=" + pagedSql);
//		}
//
//		List queryList = jdbcTemplate.query(pagedSql,
//				sql.getParam(),new BeanPropertyRowMapper(mappedClass));
//		pageView.setViewList(queryList);
//		return pageView;
//	}
//
//	public static void setPageViewObject(NamedParameterJdbcTemplate jdbcTemplate,
//			SqlBuilder sql, PageView pageView,
//			BeanPropertySqlParameterSource param) {
//		int totalCount = getTotalCount(sql, jdbcTemplate, param);
//
//		pageView.getPagination().setTotalCount(totalCount);
//
//		pageView.getVo().setFromRows(
//				pageView.getPagination().getFirstElementOnPage());
//		pageView.getVo().setToRows(pageView.getPagination().getNumPerPage());
//	}
//
//	/**
//	 * 获取总记录数
//	 * 
//	 * @param sql
//	 * @param jdbcTemplate
//	 * @param param
//	 * @return
//	 */
//	public static int getTotalCount(SqlBuilder sql,
//			NamedParameterJdbcTemplate jdbcTemplate,
//			BeanPropertySqlParameterSource param) {
//		String countSql = getCountSql(sql.getSql());
//		if (log.isDebugEnabled()) {
//			log.debug("countSql=" + countSql);
//		}
//		int totalCount = jdbcTemplate.queryForInt(countSql, param);
//		return totalCount;
//	}
//
//	/**
//	 * 初始化PageView对象
//	 * 
//	 * @return
//	 */
//	public static PageView initPageView() {
//		return initPageView(null);
//	}
//
//	/**
//	 * 初始化PageView对象
//	 * 
//	 * @param pageView
//	 * @return
//	 */
//	public static PageView initPageView(PageView pageView) {
//		DwzPageBean pagination = null;
//		GenericVO vo = null;
//		if (null != pageView) {
//			pagination = pageView.getPagination();
//			vo = pageView.getVo();
//		}
//		initPageView(pageView, pagination, vo);
//
//		return pageView;
//	}
//
//	/**
//	 * 初始化PageView对象
//	 * 
//	 * @param pageView
//	 * @return
//	 */
//	public static PageView initPageView(DwzPageBean pagination, GenericVO vo) {
//
//		PageView pageView = initPageView(null, pagination, vo);
//		return pageView;
//	}
//
//	/**
//	 * 初始化PageView对象
//	 * 
//	 * @param pageView
//	 * @return
//	 */
//	public static PageView initPageView(PageView pageView,
//			DwzPageBean pagination, GenericVO vo) {
//
//		if (null == pageView) {
//			pageView = new PageView();
//		}
//		if (null == pagination) {
//			pagination = new DwzPageBean();
//		}
//		if (null == vo) {
//			vo = new GenericVO();
//		}
//		pageView.setPagination(pagination);
//		pageView.setVo(vo);
//		return pageView;
//	}
//}
