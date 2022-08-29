package com.varxyz.wgt.user.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
	private String userId;
	private String passwd;
	private String name;
	private String ssn;
	private String phone;
	private String addr1;
	private String addr2;
	private String addr3;
	private String addr4;
	private String imgName;
	
}
