package com.varxyz.wgt.login.serviceImpl;

import com.varxyz.wgt.login.service.LoginService;
import com.varxyz.wgt.user.domain.User;

public class LoginServiceImpl implements LoginService {

	@Override
	public User login(String userId) {
		return loginDao.login(userId);
	}
	
}
