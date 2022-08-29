package com.varxyz.wgt.waiting.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.varxyz.wgt.data.DataSourceConfig;
import com.varxyz.wgt.waiting.dao.WaitingDao;
import com.varxyz.wgt.waiting.domain.Waiting;

public interface WaitingService {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	WaitingDao dao = context.getBean("waitingDao", WaitingDao.class);
	
	// 웨이팅 추가
	public void addWaiting(String barName, String userId, long num_people);
	
	// 
	public void addWaitingTime(String userId, String time);
	
	// 웨이팅 삭제
	public void deleteWaiting(String userId);
	
	// 회원님의 현재 웨이팅 조회
	public List<Waiting> findWaitingById(String userId);
	
	// 술집의 현재 웨이팅 내역
	public List<Waiting> findAllWaiting(String barName);
	
	// 특정 매장 내 모든 웨이팅 삭제
	public void deteleAllWaiting(String barName);
}
