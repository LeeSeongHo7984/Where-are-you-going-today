package com.varxyz.wgt.login.dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.varxyz.wgt.owner.doamin.Owner;
import com.varxyz.wgt.user.domain.User;

@Repository("ownerLoginDao")
public class OwnerLoginDao {
private JdbcTemplate jdbcTemplate;
	
	public OwnerLoginDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	// 점주 로그인
	public Owner login(String ownerId) {
		String sql = "SELECT * FROM Owner WHERE ownerId = ?";
		
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Owner>(Owner.class), ownerId);
	}

}
