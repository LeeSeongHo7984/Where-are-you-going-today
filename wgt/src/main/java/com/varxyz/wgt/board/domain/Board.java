package com.varxyz.wgt.board.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Board {
	private long number;
	private String title;
	private String content;
	private Date regDate;
	private String imgname;
	private String businessNumber;
	private int likecount; //게시글 총 좋아요 개수
	private String userId;
	private String likeImg;
//	public String toString() {
//		return "번호 : " + number +  "제목 : " + title 
//				+ "내용 : " + content + "날짜 : " + regDate;
//	}

}
