package com.varxyz.wgt.waiting.serviceImpl;

import java.util.List;

import com.varxyz.wgt.waiting.domain.Waiting;
import com.varxyz.wgt.waiting.service.WaitingService;

public class WaitingServiceImpl implements WaitingService{

	@Override
	public void addWaiting(String barName, String userId, long num_people) {
		dao.addWaiting(barName, userId, num_people);
	}

	@Override
	public void deleteWaiting(String userId) {
		dao.deleteWaiting(userId);
	}

	@Override
	public List<Waiting> findWaitingById(String userId) {
		return dao.findWaitingById(userId);
	}

	@Override
	public List<Waiting> findAllWaiting(String barName) {
		return dao.findAllWaiting(barName);
	}

	@Override
	public void deteleAllWaiting(String barName) {
		dao.deteleAllWaiting(barName);
	}

	@Override
	public void addWaitingTime(String userId, String time) {
		dao.addWaitingTime(userId, time);
	}
	
}
