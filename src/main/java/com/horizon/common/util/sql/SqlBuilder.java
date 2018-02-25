package com.horizon.common.util.sql;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.horizon.common.util.DateUtil;
import com.horizon.common.util.StringUtil;

public class SqlBuilder {
	/**
	 * 日志对象
	 */
	public Log log = LogFactory.getLog(getClass());

	private StringBuffer sqlBuilder = new StringBuffer();

	private SqlParameterSource param = null;
	
	private String parsedSql = null;
	
	private boolean parseFlag = false;
	
	private String originalSql = "";
	
	private String [] paramSegArr = {" ",",",")"};
	
	private Map<String,Object> paramsMap = new HashMap<String,Object>();

	public SqlParameterSource getParam() {
		return param;
	}

	public void setParam(Object param) {
		
		this.param = getSqlParameters(param);
	}

	public SqlBuilder() {
	}

	public SqlBuilder(String sql) {
		this(sql,null);
	}

	public SqlBuilder(String sql, Object param) {
		this.originalSql = sql;
		this.param = getSqlParameters(param);
	}

	public void append(String sql) {
		this.sqlBuilder.append(sql);
	}

	public void insert(int index, String str) {
		this.sqlBuilder.insert(index, str);
	}

	public String getSql() {
		if(log.isDebugEnabled()){
			log.debug("原始SQL:"+this.originalSql);
		}
		if(isParsed()){
			this.parsedSql = this.sqlBuilder.toString();
		} else{
			this.parsedSql = this.parse(originalSql, param);
		}
		if (log.isDebugEnabled()) {
			if (0 < paramsMap.size()) {
				log.debug("解析处理后SQL:" + this.parsedSql);
				String logSql = this.parsedSql;
				for (Object sqlParam : paramsMap.keySet()) {
					Object paramValue = paramsMap.get(sqlParam);
					String paramValueStr = "";
					if (paramValue instanceof Date) {
						paramValueStr = "'"
								+ DateUtil.dateTime2Str((Date) paramValue)
								+ "'";
					} else if (paramValue instanceof String) {
						paramValueStr = "'" + paramValue + "'";
					} else {
						paramValueStr = StringUtil.toString(paramValue);
					}
					logSql = logSql.replaceAll(":" + sqlParam, paramValueStr);
				}
				log.debug("参数绑定处理后SQL:" + logSql);
			}
		}
		return this.parsedSql;
	}
	
	public void parsedReset(){
		this.parseFlag = false;
	}
	
	private void parsedSet(){
		this.parseFlag = true;
	}
	
	private boolean isParsed(){
		return this.parseFlag;
	}
	/**
	 * sql 解析
	 * @param paramSql
	 * @param param
	 * @return
	 */
	public String parse(String paramSql,
			SqlParameterSource param) {
		
		if(isParsed()){
			return sqlBuilder.toString();
		}
		
		Matcher m = regularIfMatch(paramSql);
		
		boolean isReplace = isReplaceFistDot(paramSql);
		
		while (m.find()) {
			String tempSqlSegmt = m.group(1);
			String paramName = getParamName(tempSqlSegmt);

			if (param != null && param.hasValue(paramName)) {
				if (null == param.getValue(paramName)
						|| "".equals(param.getValue(paramName))) {
					m.appendReplacement(sqlBuilder, "");
				} else {
					if(isReplace){
						tempSqlSegmt = tempSqlSegmt.replaceFirst(",", " ");
						isReplace = false;
					}
					paramsMap.put(paramName, param.getValue(paramName));
					m.appendReplacement(sqlBuilder, tempSqlSegmt);
				}
			} else {
				m.appendReplacement(sqlBuilder, "");
			}
		}
		m.appendTail(sqlBuilder);
		parsedSet();
		return sqlBuilder.toString();
	}

	private boolean isReplaceFistDot(String paramSql) {
		boolean isReplace = false;
		int u = paramSql.indexOf("update");
		if(-1==u){
			u = paramSql.indexOf("UPDATE");
		}
		if(-1!= u){
			isReplace = true;
		}
		return isReplace;
	}

	/**
	 * 获取参数名称
	 * 
	 * @param tempSqlSegmt
	 * @return
	 */
	private String getParamName(String tempSqlSegmt) {
		String paramName = tempSqlSegmt
				.substring(tempSqlSegmt.indexOf(":") + 1).trim();
		if(StringUtil.isEmpty(paramName)){
			return paramName;
		}
		for (int i = 0; i < paramSegArr.length; i++) {
			if (paramName.indexOf(paramSegArr[i]) != -1) {
				paramName = paramName.substring(0,
						paramName.indexOf(paramSegArr[i]));
				break;
			}
		}
		return paramName;
	}
	
	/**
	 * 匹配查询条件
	 * 
	 * @param sql
	 * @return
	 */
	private static Matcher regularIfMatch(String sql) {
		Pattern p = Pattern.compile("#if\\{(.+?)\\}");
		Matcher m = p.matcher(sql);
		return m;
	}

	/**
	 * 
	 * @param paraObj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static SqlParameterSource getSqlParameters(Object paraObj) {
		SqlParameterSource param;
		if (paraObj instanceof Map) {
			param = new MapSqlParameterSource(
					(Map<String, ?>) paraObj);
		} else {
			param = new BeanPropertySqlParameterSource(paraObj);
		}
		return param;
	}

}

