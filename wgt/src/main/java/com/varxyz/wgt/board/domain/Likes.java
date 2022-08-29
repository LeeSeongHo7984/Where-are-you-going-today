package com.varxyz.wgt.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Likes {
	private String userId;
	private long number;
	private String likeCheck;
	private String likeImg;
}
