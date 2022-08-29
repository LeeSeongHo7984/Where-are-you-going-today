package com.varxyz.wgt.owner.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.varxyz.wgt.data.DataSourceConfig;
import com.varxyz.wgt.owner.dao.OwnerDao;
import com.varxyz.wgt.owner.doamin.Owner;

public interface OwnerService {
	AnnotationConfigApplicationContext context = 
			new AnnotationConfigApplicationContext(DataSourceConfig.class);
	
	OwnerDao ownerDao = context.getBean("ownerDao", OwnerDao.class);

	// 점주 추가
	public void addOwner(Owner owner);

	// 점주 정보 가져오기
	public Owner findAllOwner(String ownerId);

	// 점주 수정
	public void modifyOwner(Owner owner);

	// 점주 삭제
	public void delete(String ownerId);
	
//	// 사업자번호 중복검사 (boolean 타입 형식)
//	public boolean duplicationBn(String bNumber);
		
}
