package com.horizon.sm.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.horizon.common.base.BaseDao;
import com.horizon.sm.user.dao.ISysUserDAO;

@Component("sysUserDAO")
public class SysUserDAOImpl extends BaseDao implements ISysUserDAO {
	
	public List<?> queryList(String sql ,Class<?> elementType){
		return super.getJdbcTemplate().queryForList(sql, elementType);
	}

	
}
