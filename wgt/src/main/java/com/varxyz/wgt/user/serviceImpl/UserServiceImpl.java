package com.varxyz.wgt.user.serviceImpl;

import java.util.List;

import com.varxyz.wgt.user.domain.User;
import com.varxyz.wgt.user.service.UserService;

public class UserServiceImpl implements UserService {

	// 회원생성
	@Override
	public void addUser(User user, String imgName) {
		userDao.addUser(user, imgName);
	}

	// 회원조회
	@Override
	public List<User> inquiryUser(String userId) {
		return userDao.inquiryUser(userId);
	}
	
	// 회원수정
	public void modifyUser(User user, String imgName) {
		 userDao.modifyUser(user, imgName);
	}
	
	// 회원탈퇴
	public void delete(String userId, String imgName) {
		userDao.delete(userId, imgName);
	}
}
