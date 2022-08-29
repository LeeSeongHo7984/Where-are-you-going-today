package com.varxyz.wgt.owner.serviceImpl;


import com.varxyz.wgt.owner.doamin.Owner;
import com.varxyz.wgt.owner.service.OwnerService;

public class OwnerServiceImpl implements OwnerService {

	// 점주생성
	@Override
	public void addOwner(Owner owner) {
		ownerDao.addOwner(owner);
	}

	// 점주조회
	@Override
	public Owner findAllOwner(String ownerId) {
		return ownerDao.findAllOwner(ownerId);
	}

	//점주수정
	@Override
	public void modifyOwner(Owner owner) {
		ownerDao.modifyOwner(owner);
	}

	// 점주탈퇴
	@Override
	public void delete(String ownerId) {
		ownerDao.delete(ownerId);
	}
	
	// 사업자번호 중복검사 (boolean 타입 형식)
//	@Override
//	public boolean duplicationBn(String bNumber) {
//		return ownerDao.duplicationBn(bNumber);
//	}
}
