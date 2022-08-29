package com.varxyz.wgt.board.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.varxyz.wgt.board.domain.Board;
import com.varxyz.wgt.board.domain.Likes;
import com.varxyz.wgt.shop.domain.Shop;

@Component("boardDao")
public class BoardDao {
	private JdbcTemplate jdbcTemplate;

	public BoardDao(DataSource dataSource) {
	      jdbcTemplate = new JdbcTemplate(dataSource);
	}

	//게시글 생성
	public void create(Board board, String imgName, String userId, Shop shop) {
		String sql = "INSERT INTO Board (title, content, imgname, userId, businessNumber) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, board.getTitle(), board.getContent(), imgName, userId, shop.getBusinessNumber());
	}
	
	//게시글 조회(businessNumber)
	public List<Board> read(String businessNumber) {
		String sql = "SELECT * FROM Board WHERE Board.businessNumber =? ORDER BY regDate DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Board>(Board.class), businessNumber);
	}
	
	//게시글 조회(ID)
	public List<Board> readmypage(String userId) {
		String sql = "SELECT * FROM Board WHERE userId =? ORDER BY regDate DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Board>(Board.class), userId);
	}
	
	//게시글 수정
	public void update(Board board, String imgname) { //3가지 방법, request.getparameter / Board board / Requestparam
		String sql = "UPDATE Board SET title = ?, content = ?, imgname = ? WHERE number = ?";
		jdbcTemplate.update(sql, board.getTitle(), board.getContent(), imgname, board.getNumber());
	}

	//게시글 삭제
	public int delete(int number, String imgname) {
		String sql = "DELETE FROM Board WHERE number = ?";
		File file = new File("C:\\NCS\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\board\\img\\upload" + imgname + ".jpg");
		file.delete();
		return jdbcTemplate.update(sql, number);
	}
	
	//제목으로 게시글 찾기
	public List<Board> search(String title) {
		String sql = "SELECT * FROM Board WHERE title like '%" + title + "%' ORDER BY regDate DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Board>(Board.class));
	}
	
	//고유번호로 게시물 정보 갖고오기
	public Board searchByNumber(long number) {
		String sql = "SELECT * FROM Board WHERE number = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Board>(Board.class), number);
	}//query = list<object>로 반환  queryforobject = 객체로만 반환
	
	// 게시글 총 좋아요 개수 1 증가
	public void likecountPlus(int likecount, long number) {
		String sql = "UPDATE Board SET likecount = ? WHERE number = ?";
		jdbcTemplate.update(sql, likecount+1, number);
	}
	
	// 게시글 총 좋아요 개수 1 다운
	public void likecountDown(int likecount, long number) {
		String sql = "UPDATE Board SET likecount = ? WHERE number = ?";
		jdbcTemplate.update(sql, likecount-1, number);
	}
	
	// likes테이블에 정보 추가
	public void likeuser(Likes likes) {
		String sql = "INSERT INTO Likes (userId, likeCheck, number) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, likes.getUserId(), likes.getLikeCheck(), likes.getNumber());
	}
	
	// 아이디, 게시글고유번호로 좋아요 정보 가져오기
	public List<Likes> findLikes(String userId, long number){
		String sql = "SELECT * FROM Likes WHERE userId = ? AND number = ?";
		if ( jdbcTemplate.query(sql, new BeanPropertyRowMapper<Likes>(Likes.class), userId, number).isEmpty() ) {
			List<Likes> errorList = new ArrayList<Likes>();
			Likes errorLikes = new Likes();
			errorLikes.setUserId("없음");
			errorLikes.setNumber(-1);
			errorList.add(errorLikes);
			return errorList;
		}
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Likes>(Likes.class), userId, number);
	}
	
	// likes테이블에 check 업데이트
	public void checkUpdate(String userId, long number, String check) {
		String sql = "UPDATE Likes SET likeCheck = ? WHERE userId =? AND number = ?";
		jdbcTemplate.update(sql, check, userId, number);
	}
	
	// board 테이블 좋아요 사진 업데이트
	public void updateLikeImg(long number, String likeImg) {
		String sql = "UPDATE Board SET likeImg = ? WHERE number = ?";
		jdbcTemplate.update(sql, likeImg, number);
	}
	
	//ID로 게시글 찾기
//	public List<Board> findByuserId(String userId) {
//		String sql = "SELECT * FROM Board WHERE userId = ?";
//		List<Board> list = new ArrayList<>();
//		list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Board>(Board.class));
//		return list;
//	}

}
