package com.varxyz.wgt.owner.dao;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.varxyz.wgt.owner.doamin.Owner;

@Repository("ownerDao")
public class OwnerDao {
	private JdbcTemplate jdbcTemplate;
	
	public OwnerDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// 점주가입
	public void addOwner(Owner owner) {
		String sql = "INSERT INTO Owner (ownerId, passwd, name) "
				+ " VALUES (?, ?, ?)";
		
		jdbcTemplate.update(sql, owner.getOwnerId(), owner.getPasswd(), owner.getName());
	}

	// 점주 정보 가져오기
	public Owner findAllOwner(String ownerId) {
		String sql = "SELECT * FROM Owner WHERE ownerId = ?";
		
		try {
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Owner>(Owner.class), ownerId);
		} catch (EmptyResultDataAccessException e) {	
			Owner owner = new Owner();
			
			return owner;	
		}
	}

	// 점주정보 수정
	public void modifyOwner(Owner owner) {
		String sql = "UPDATE Owner SET passwd = ?, name = ? WHERE ownerId = ?";
		
		jdbcTemplate.update(sql, owner.getPasswd(), owner.getName(), owner.getOwnerId());
	}

	public void delete(String ownerId) {
		String sql = "DELETE FROM Owner WHERE ownerId = ?";
		
		jdbcTemplate.update(sql, ownerId);
	}
	
	// 사업자번호 중복검사 (boolean 타입 형식)
//	public boolean duplicationBn(String bnumber) {
//		String sql = "SELECT * FROM Owner WHERE bnumber = ?";
//		boolean result = false;
//		
//		try {
//			jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Owner>(Owner.class), bnumber);
//			System.out.println(jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Owner>(Owner.class), bnumber).getBnumber());
//			System.out.println("오류없음 중복");
//			return result;
//		} catch (EmptyResultDataAccessException e) {	
//			result = true;
//			System.out.println("오류 있음 중복아님");
//			
//			return result;	
//		}
//	}

}
