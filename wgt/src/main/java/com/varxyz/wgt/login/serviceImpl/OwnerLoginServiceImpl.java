package com.varxyz.wgt.login.serviceImpl;

import com.varxyz.wgt.login.service.OwnerLoginService;
import com.varxyz.wgt.owner.doamin.Owner;

public class OwnerLoginServiceImpl implements OwnerLoginService {
	
	@Override
	public Owner ownerLogin(String ownerId) {
		return ownerLoginDao.login(ownerId);
	}
}
