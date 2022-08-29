package com.varxyz.wgt.login.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.varxyz.wgt.data.DataSourceConfig;
import com.varxyz.wgt.login.dao.LoginDao;
import com.varxyz.wgt.user.domain.User;

public interface LoginService {
	AnnotationConfigApplicationContext context = 
			new AnnotationConfigApplicationContext(DataSourceConfig.class);
	
	LoginDao loginDao = context.getBean("loginDao", LoginDao.class);
	
	// 로그인
	public User login(String userId);

}
