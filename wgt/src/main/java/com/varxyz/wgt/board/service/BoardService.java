package com.varxyz.wgt.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.varxyz.wgt.board.dao.BoardDao;
import com.varxyz.wgt.board.domain.Board;
import com.varxyz.wgt.board.domain.Likes;
import com.varxyz.wgt.data.DataSourceConfig;
import com.varxyz.wgt.shop.domain.Shop;

public interface BoardService {

	AnnotationConfigApplicationContext context =
		new AnnotationConfigApplicationContext(DataSourceConfig.class);

	BoardDao dao = context.getBean("boardDao", BoardDao.class);

	// 게시글 목록 조회
		public List<Board> list();
		
		// 게시글 쓰기
		public void create(Board board, String imgname, String userId, Shop shop);
		
		// 게시글 읽기
		public List<Board> read(String businessNumber);
		
		// 유저 아이디로 게시글 정보 보기
		public List<Board> readmypage(String userId);
		
		// 게시글 수정
		public void update(Board board, String imgname);
		
		// 게시글 삭제
		public int delete(int number, String imgname);

		// 게시글 검색
		public List<Board> search(String title);
		
		// 게시물 정보
		public Board searchByNumber(long number);
		
		public List<Board> boardList = new ArrayList<>();

		// 게시글 총 좋아요 개수 1 증가
		public void likecountPlus(int likecount, long number);
		
		// 게시글 총 좋아요 개수 1 다운
		public void likecountDown(int likecount, long number);
		
		// likes테이블에 정보 추가
		public void likeuser(Likes likes);
		
		// 아이디, 게시글고유번호로 정보 가져오기
		public List<Likes> findLikes(String userId, long number);
		
		// likes테이블에 check 업데이트
		public void checkUpdate(String userId, long number, String check);
		
		public void updateLikeImg(long number, String likeImg);
}
