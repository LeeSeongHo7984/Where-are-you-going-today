package com.varxyz.wgt.map.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.varxyz.wgt.data.DataSourceConfig;
import com.varxyz.wgt.map.dao.MapDao;
import com.varxyz.wgt.map.domain.Map;

public interface MapService {
	AnnotationConfigApplicationContext context = 
			new AnnotationConfigApplicationContext(DataSourceConfig.class);
	
	MapDao dao = context.getBean("mapDao", MapDao.class);
	
	public List<Map> search(String name);
	public List<Map> findBnsMap(String businessNumber);
	public List<Map> findAll();
	public void insertPosition(Map map);
}
