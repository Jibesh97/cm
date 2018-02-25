package com.horizon.sm.user.dao;

import java.util.List;

import com.horizon.common.dao.ISDAO;

public interface ISysUserDAO extends ISDAO<Object>{

	public List<?> queryList(String sql ,Class<?> elementType);
}
