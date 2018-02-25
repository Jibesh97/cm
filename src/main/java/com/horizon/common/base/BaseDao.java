package com.horizon.common.base;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.horizon.common.util.PageView;
import com.horizon.common.util.SysContextUtil;
import com.horizon.common.util.sql.SqlBuilder;

public class BaseDao extends NamedParameterJdbcDaoSupport {
	/**
	 * 日志对象
	 */
	public Log log = LogFactory.getLog(getClass());

	/**
	 * <p>
	 * Discription: 批量执行方法(BatchPreparedStatementSetter结构入参)
	 * </p>
	 */
//	public int[] batchUpdate(String sql, BatchPreparedStatementSetter setter) {
//		return getJdbcTemplate().batchUpdate(sql, setter);
//	}

	/**
	 * <p>
	 * Discription: 批量执行方法(数组入参)
	 * </p>
	 */
	public int[] batchUpdate(String[] sql) {
		if (log.isDebugEnabled()) {
			if (sql != null && sql.length != 0) {
				for (String subSql : sql) {
					log.debug(subSql);
				}
			}
		}
		return getJdbcTemplate().batchUpdate(sql);
	}

	/**
	 * <p>
	 * Discription: 不带参数,返回SqlRowSet.SqlRowSet是对JDBC中ResultSet的简单封装
	 * </p>
	 */
	public SqlRowSet queryRowSet(String sql) {
		return getJdbcTemplate().queryForRowSet(sql);
	}

	/**
	 * <p>
	 * Discription: 带参数,返回SqlRowSet.SqlRowSet是对JDBC中ResultSet的简单封装
	 * </p>
	 */
	public SqlRowSet queryRowSet(String sql, Object[] obj) {
		return getJdbcTemplate().queryForRowSet(sql, obj);
	}

	/**
	 * <p>
	 * Discription: 不带参数的update方法
	 * </p>
	 */
	public int executeUpdate(String sql) {
		return getJdbcTemplate().update(sql);
	}

	/**
	 * <p>
	 * Discription: 带参数的update方法
	 * </p>
	 */
	public int executeUpdate(String sql, Object[] obj) {
		return getJdbcTemplate().update(sql, obj);
	}

	/**
	 * <p>
	 * Discription:返回整型数据,不带参数
	 * </p>
	 */
	public int queryForInt(String sql) {
		return getJdbcTemplate().queryForObject(sql,Integer.class);
	}

	/**
	 * <p>
	 * Discription:返回整型数据,带参数
	 * </p>
	 */
	public int queryForInt(String sql, Object[] obj) {
		return getJdbcTemplate().queryForObject(sql, obj,Integer.class);
	}

	/**
	 * <p>
	 * Discription:返回Long类型数据,不带参数
	 * </p>
	 */
	public long queryForLong(String sql) {
		return getJdbcTemplate().queryForObject(sql,Long.class);
	}

	/**
	 * <p>
	 * Discription:返回Long类型数据,带参数
	 * </p>
	 */
	public long queryForLong(String sql, Object[] obj) {
		return getJdbcTemplate().queryForObject(sql, obj,Long.class);
	}

	/**
	 * <p>
	 * Discription:取回结果,封装成List
	 * </p>
	 */
	public List<?> queryForList(String sql, Object[] obj) {
		return getJdbcTemplate().queryForList(sql, obj);
	}

	/**
	 * 
	 * <P>
	 * Function: findByPK
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object findByPK(Object instance, String sqlParam,
			Class<?> mappedClass) {
		SqlBuilder sql = new SqlBuilder(sqlParam, instance);
		SqlParameterSource param = sql.getParam();
		return this.getNamedParameterJdbcTemplate().queryForObject(sql.getSql(),
				param, new BeanPropertyRowMapper(mappedClass));

	}

	/**
	 * 
	 * <P>
	 * Function: findByPK
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */
	public Object findByPK(Object instance, String sqlParam) {
		SqlBuilder sql = new SqlBuilder(sqlParam, instance);
		SqlParameterSource param = sql.getParam();
		return this.getNamedParameterJdbcTemplate().queryForMap(sql.getSql(), param);

	}

	/**
	 * 
	 * <P>
	 * Function: findByVO
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */
	@SuppressWarnings({})
	public List<?> findByVO(Object instance, String sqlParam) {

		SqlBuilder sql = new SqlBuilder(sqlParam, instance);
		SqlParameterSource param = sql.getParam();
		return this.getNamedParameterJdbcTemplate().queryForList(sql.getSql(), param);
	}

	/**
	 * 
	 * <P>
	 * Function: findByVO
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<?> findByVO(Object instance, String sqlParam,
			Class<?> mappedClass) {

		SqlBuilder sql = new SqlBuilder(sqlParam, instance);
		SqlParameterSource param = sql.getParam();

		return this.getNamedParameterJdbcTemplate().query(sql.getSql(),
				param,new BeanPropertyRowMapper(mappedClass));
	}

	/**
	 * 
	 * <P>
	 * Function: findByVO4ParsedSql
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */

	public List<?> findByVO4ParsedSql(Object instance, String sqlParam) {

		SqlBuilder sql = new SqlBuilder(sqlParam, instance);
		SqlParameterSource param = sql.getParam();

		return this.getNamedParameterJdbcTemplate().queryForList(sql.getSql(), param);
	}

	/**
	 * 
	 * <P>
	 * Function: findByVO4ParsedSql
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime: 2012-09-17
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<?> findByVO4ParsedSql(Object instance, String sqlParam,
			Class<?> mappedClass) {
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(
				instance);
		SqlBuilder sql = new SqlBuilder(sqlParam, param);

		return this.getNamedParameterJdbcTemplate().query(sql.getSql(),
				param,new BeanPropertyRowMapper(mappedClass));
	}

	/**
	 * 返回字符串对象
	 * 
	 * @param sql
	 * @param map
	 * @return
	 */
	public String findString(String sql, Map<String, ?> map) {
		try {
			return this.getNamedParameterJdbcTemplate().queryForObject(sql,
					 map,String.class);
		} catch (EmptyResultDataAccessException e) {
			return "";
		}
	}

	/**
	 * 
	 * <P>
	 * Function: findPageViewByVO
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */
//	public PageView findPageViewByVO(String sqlParam, PageView pageView) {
//		SqlBuilder sql = new SqlBuilder(sqlParam, pageView.getParaMap());
//		return PageUtil.queryForPageView(this.getNamedParameterJdbcTemplate(), sql,
//				pageView);
//	}

	/**
	 * 
	 * <P>
	 * Function: findPageViewByVO
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */
//	public PageView findPageViewByVO(String sqlParam, PageView pageView,
//			Class<?> mappedClass) {
//		// SqlBuilder sql = new SqlBuilder(sqlParam);
//		SqlBuilder sql = new SqlBuilder(sqlParam, pageView.getVo());
//		return PageUtil.queryForPageView(this.getNamedParameterJdbcTemplate(), sql,
//				pageView, mappedClass);
//	}

	/**
	 * 此方法只适用于Mysql数据库，保存后返回主键ID
	 * <P>
	 * Function: save
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime: 2012-10-16
	 */
	public Number save(GenericVO instance, String tableName, String KeyColumn) {

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(
				instance);
		SimpleJdbcInsert simpleJdbcInsert = SysContextUtil.getSimpleJdbcInsert(
				tableName, KeyColumn, this.getDataSource());
		Number newId = (Number) simpleJdbcInsert
				.executeAndReturnKey(parameters);

		return newId;
	}

	/**
	 * 
	 * <P>
	 * Function: save
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */
	@SuppressWarnings("unchecked")
	public void save(Object instance, String tableName) {

		SqlParameterSource param;
		if (instance instanceof Map) {
			param = new MapSqlParameterSource((Map<String, ?>) instance);
		} else {
			param = new BeanPropertySqlParameterSource(instance);
		}
		SimpleJdbcInsert simpleJdbcInsert = SysContextUtil.getSimpleJdbcInsert(
				tableName, this.getDataSource());
		simpleJdbcInsert.execute(param);

	}

	/**
	 * 
	 * <P>
	 * Function: save
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */
	@SuppressWarnings("unchecked")
	public int save(String sql, Object instance) {

		SqlParameterSource param;
		if (instance instanceof Map) {
			param = new MapSqlParameterSource((Map<String, ?>) instance);
		} else {
			param = new BeanPropertySqlParameterSource(instance);
		}
		return this.getNamedParameterJdbcTemplate().update(sql, param);
	}

	/**
	 * 
	 * <P>
	 * Function: updateBatch
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */
	public int[] saveBatch(String sql, List<?> list) {
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list
				.toArray());
		return this.getNamedParameterJdbcTemplate().batchUpdate(sql, batch);

	}

	/**
	 * 
	 * <P>
	 * Function: update
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */
	public int update(Object instance, String sqlParam) {

		SqlBuilder sql = new SqlBuilder(sqlParam, instance);
		SqlParameterSource param = sql.getParam();

		return this.getNamedParameterJdbcTemplate().update(sql.getSql(), param);
	}

	/**
	 * 
	 * <P>
	 * Function: updateBatch
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */
	public int[] updateBatch(Object instance, String sqlParam, List<?> list) {

		SqlBuilder sql = new SqlBuilder(sqlParam, instance);

		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list
				.toArray());
		return this.getNamedParameterJdbcTemplate().batchUpdate(sql.getSql(), batch);

	}

	/**
	 * 
	 * <P>
	 * Function: updateBatch
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */
	@SuppressWarnings("rawtypes")
	public int[] updateBatch4Map(Object instance, String sqlParam,
			List<Map> list) {

		SqlBuilder sql = new SqlBuilder(sqlParam, instance);
		Map[] batchArr = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			batchArr[i] = list.get(i);
		}
		SqlParameterSource[] batch = SqlParameterSourceUtils
				.createBatch(batchArr);
		return this.getNamedParameterJdbcTemplate().batchUpdate(sql.getSql(), batch);

	}

	/**
	 * 
	 * <P>
	 * Function: updateBatch
	 * <P>
	 * Description:
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int[] updateBatch(String sqlParam, List<?> list) {

		if (null == list || 0 == list.size()) {
			return new int[0];
		}
		Object instance = list.get(0);
		if (instance instanceof Map) {
			return this.updateBatch4Map(instance, sqlParam, (List<Map>) list);
		}
		return this.updateBatch(instance, sqlParam, list);

	}

	/**
	 * 
	 * <P>
	 * Function: deleteBatchByPK
	 * <P>
	 * Description: 通过主键批量删除
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */
//	public int[] deleteBatchByPK(String sqlParam, String[] ids) {
//		final String[] idList = ids;
//		return this.getJdbcTemplate().batchUpdate(sqlParam,
//				new BatchPreparedStatementSetter() {
//					public void setValues(PreparedStatement ps, int i)
//							throws SQLException {
//						ps.setString(1, idList[i]);
//					}
//
//					public int getBatchSize() {
//						return idList.length;
//					}
//				});
//	}

	/**
	 * 
	 * <P>
	 * Function: deleteBatchByPK
	 * <P>
	 * Description: 通过主键批量删除
	 * <P>
	 * Others:
	 * 
	 * @CreateTime:
	 */
//	public int[] deleteBatchByPKInt(String sqlParam, int[] ids) {
//		final int[] idList = ids;
//		return this.getJdbcTemplate().batchUpdate(sqlParam,
//				new BatchPreparedStatementSetter() {
//					public void setValues(PreparedStatement ps, int i)
//							throws SQLException {
//						ps.setInt(1, idList[i]);
//					}
//
//					public int getBatchSize() {
//						return idList.length;
//					}
//				});
//	}
	
	
	/**
	 * 调用存储过程
	 * 
	 * @param instance
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map callProc(Object instance, String procName) {
		SimpleJdbcCall procReadActor = new SimpleJdbcCall(getJdbcTemplate()
				.getDataSource()).withProcedureName(procName);
		SqlParameterSource in;
		if (instance instanceof Map) {
			in = new MapSqlParameterSource((Map<String, ?>) instance);
		} else {
			in = new BeanPropertySqlParameterSource(instance);
		}
		Map out = procReadActor.execute(in);
		return out;
	}

	/**
	 * 调用存储过程
	 * 
	 * @param instance
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map callPkgProc(Object instance, String pkgName, String procName) {
		SimpleJdbcCall procReadActor = new SimpleJdbcCall(getJdbcTemplate()
				.getDataSource()).withCatalogName(pkgName).withProcedureName(
				procName);
		SqlParameterSource in;
		if (instance instanceof Map) {
			in = new MapSqlParameterSource((Map<String, ?>) instance);
		} else {
			in = new BeanPropertySqlParameterSource(instance);
		}
		Map out = procReadActor.execute(in);
		return out;
	}
	
	/**
	 * 返回List<String>,不带参数
	 * @param sql
	 * @return
	 */
	public List<String> queryForString(String sql) {
		List<String> str = getJdbcTemplate().queryForList(sql, String.class);
//		String[] sa=str.split(",");
//		List<String> list = Arrays.asList(sa);
		return str;
//		return this.getNamedParameterJdbcTemplate().queryForList(sql.getSql(), param);
	}
	
	/**
	 * 返回List<String>,带参数
	 * @param sql
	 * @param db_schema 
	 * @return
	 */
	public List<String> queryForTables(String sql, Object db_schema) {
		SqlBuilder str = new SqlBuilder(sql, db_schema);
		SqlParameterSource param = str.getParam();

		List<String> list = this.getNamedParameterJdbcTemplate().queryForList(str.getSql(), param,String.class);
		return list;
	}
	
	@SuppressWarnings({})
	public List<String> findByVOForColumn(Object instance, String sqlParam) {

		SqlBuilder sql = new SqlBuilder(sqlParam, instance);
		SqlParameterSource param = sql.getParam();
		List<String> list = this.getNamedParameterJdbcTemplate().queryForList(sql.getSql(), param,String.class);
		return list;
//		return  this.getNamedParameterJdbcTemplate().queryForList(sql.getSql(), param);
	}

	
}