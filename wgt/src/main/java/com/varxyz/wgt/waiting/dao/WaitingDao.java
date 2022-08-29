package com.varxyz.wgt.waiting.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.varxyz.wgt.waiting.domain.Waiting;

public class WaitingDao {
	private JdbcTemplate jdbcTemplate;

	public WaitingDao(DataSource dataSourceConfig) {
		jdbcTemplate = new JdbcTemplate(dataSourceConfig);
	}

	// 웨이팅 추가
	public void addWaiting(String barName, String userId, long num_people) {
		String sql = "INSERT INTO Waiting (barName, userId, num_people) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, barName, userId, num_people);
	}

	// 웨이팅 마지노선 시간 추가
	public void addWaitingTime(String userId, String time) {
		String sql = "UPDATE Waiting SET waitingStartTime = ? WHERE userId = ?";

		jdbcTemplate.update(sql, time, userId);
	}

	// 선택적 웨이팅 삭제
	public void deleteWaiting(String userId) {
		String sql = "DELETE FROM Waiting WHERE userId = ?";
		jdbcTemplate.update(sql, userId);
	}

	// 특정 매장 내 모든 웨이팅 삭제
	public void deteleAllWaiting(String shopName) {
		String sql = "DELETE FROM Waiting WHERE barName = ?";
		jdbcTemplate.update(sql, shopName);
	}

	// 회원님의 현재 웨이팅 조회
	public List<Waiting> findWaitingById(String userId) {
		String sql = "SELECT * FROM Waiting WHERE userId = ?";
		if (jdbcTemplate.query(sql, new BeanPropertyRowMapper<Waiting>(Waiting.class), userId).isEmpty()) {
			List<Waiting> waiting = new ArrayList<Waiting>();
			Waiting error = new Waiting();
			error.setBarName("없음");
			error.setNum_people(0);
			waiting.add(error);
			return waiting;
		}
		if ( jdbcTemplate.query(sql, new BeanPropertyRowMapper<Waiting>(Waiting.class), userId).get(0).getWaitingStartTime() == null) {
			List<Waiting> waiting = new ArrayList<Waiting>();
			Waiting error = new Waiting();
			error.setWaitingStartTime(null);
			waiting.add(error);
			return waiting;
		}
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Waiting>(Waiting.class), userId);
	}

	// 술집의 현재 웨이팅 내역
	public List<Waiting> findAllWaiting(String barName) {
		String sql = "SELECT * FROM Waiting WHERE barName = ?";
		if ( jdbcTemplate.query(sql, new BeanPropertyRowMapper<Waiting>(Waiting.class), barName).isEmpty() ) {
			List<Waiting> waiting = new ArrayList<Waiting>();
			Waiting error = new Waiting();
			error.setUserId("없음");
			waiting.add(error);
			return waiting;
		}
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Waiting>(Waiting.class), barName);
	}

}
