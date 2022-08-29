package com.varxyz.wgt.user.dao;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.varxyz.wgt.user.domain.User;

@Repository("userDao")
public class UserDao {
	private JdbcTemplate jdbcTemplate;
	private User imgName;
	
	public UserDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// 회원가입
	public void addUser(User user, String imgName) {
		String sql = "INSERT INTO User (userId, passwd, name, ssn, phone, addr1, addr2, addr3, addr4, imgName) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		jdbcTemplate.update(sql, user.getUserId(), user.getPasswd(), user.getName(), 
								user.getSsn(), user.getPhone(), user.getAddr1(), user.getAddr2(), user.getAddr3(), user.getAddr4(), imgName);
	}
	
	// 회원 조회
	public List<User> inquiryUser(String userId) {
		String sql = "SELECT * FROM User WHERE userId = ?";
		
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), userId);
	}
	
	// 회원정보 수정
	public void modifyUser(User user, String imgName) {
		String sql = "UPDATE User SET passwd = ?, name = ?, ssn = ?, phone = ?, addr1 = ?, addr2 = ?, addr3 = ?, addr4 = ?, imgName = ? WHERE  userId = ?";
		
		jdbcTemplate.update(sql, user.getPasswd(), user.getName(), user.getSsn(), user.getPhone(), user.getAddr1(),
								user.getAddr2(), user.getAddr3(), user.getAddr4(), imgName, user.getUserId());
		
	}
	
	// 회원탈퇴
	public void delete(String userId, String imgName) {
		String sql = "DELETE FROM User WHERE userId = ?";
		File file = new File("C:\\LSH\\Where-are-you-going-today-\\wgt\\src\\main\\webapp\\resources\\user\\img" + imgName + ".jpg");
		file.delete();
		
		jdbcTemplate.update(sql, userId);
	}
	
}
