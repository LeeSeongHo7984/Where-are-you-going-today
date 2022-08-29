package com.varxyz.wgt.login.dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.varxyz.wgt.user.domain.User;

@Repository("loginDao")
public class LoginDao {
	private JdbcTemplate jdbcTemplate;
	
	public LoginDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// 회원 로그인
	public User login(String userId) {
		String sql = "SELECT * FROM User WHERE userId = ?";
		
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userId);
	}

}
