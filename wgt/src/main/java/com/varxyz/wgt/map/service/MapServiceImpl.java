package com.varxyz.wgt.map.service;

import java.util.List;

import com.varxyz.wgt.map.domain.Map;

public class MapServiceImpl implements MapService{

	@Override
	public List<Map> search(String name) {
		return dao.search(name);
	}

	@Override
	public List<Map> findBnsMap(String businessNumber) {
		return dao.findBnsMap(businessNumber);
	}

	@Override
	public List<Map> findAll() {
		return dao.findAll();
	}

	@Override
	public void insertPosition(Map map) {
		dao.insertPosition(map);
	}
}
