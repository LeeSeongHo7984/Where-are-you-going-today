package com.varxyz.wgt.waiting.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Waiting {
	private String barName;
	private long num_people;
	private String userId;
	private String regDate;
	private String waitingStartTime;
}
