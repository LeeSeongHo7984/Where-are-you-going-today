package com.varxyz.wgt.map.dao;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.varxyz.wgt.map.domain.Map;

public class MapDao {
	private JdbcTemplate jdbcTemplate;
	
	public MapDao(DataSource dataSourceConfig) {
		jdbcTemplate = new JdbcTemplate(dataSourceConfig);
	}
	
	public List<Map> findBnsMap(String businessNumber){
		String sql = "SELECT longitude, latitude FROM map ORDER BY mid ASC ";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Map>(Map.class), businessNumber);
	}
	
	public List<Map> search(String name){
		String sql = "SELECT * FROM map WHERE name like '%" + name +"%' ";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Map>(Map.class));
	}
	
	public List<Map> findAll(){
		String sql = "SELECT * FROM map ORDER BY businessNumber ASC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Map>(Map.class));
	}
	
	public void insertPosition(Map map) {
		String sql = "INSERT INTO map (businessNumber, longitude, latitude) VALUES( ? ,? ,? )";
		jdbcTemplate.update(sql,map.getBusinessNumber(), map.getLongitude(), map.getLatitude());
	}
}
