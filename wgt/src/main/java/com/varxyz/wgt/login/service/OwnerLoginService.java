package com.varxyz.wgt.login.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.varxyz.wgt.data.DataSourceConfig;
import com.varxyz.wgt.login.dao.OwnerLoginDao;
import com.varxyz.wgt.owner.doamin.Owner;

public interface OwnerLoginService {
	AnnotationConfigApplicationContext context = 
			new AnnotationConfigApplicationContext(DataSourceConfig.class);
	
	OwnerLoginDao ownerLoginDao = context.getBean("ownerLoginDao", OwnerLoginDao.class);
	
	// 점주 로그인
	public Owner ownerLogin(String ownerId);
}
