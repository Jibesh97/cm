package com.horizon.sm.user.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.horizon.sm.user.dao.ISysUserDAO;
import com.horizon.sm.user.service.ISysUserManagerService;

@Service("sysUserService")
class SysUserManagerServiceImpl implements ISysUserManagerService {

	private static Logger log = Logger
			.getLogger(SysUserManagerServiceImpl.class);

	@Autowired
	private ISysUserDAO sysUserDAO;

	
	public void setSysUserDAO(ISysUserDAO sysUserDAO) {
		this.sysUserDAO = sysUserDAO;
	}


}
