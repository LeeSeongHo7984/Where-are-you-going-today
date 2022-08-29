package com.varxyz.wgt.shop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Menu {
	private String businessNumber;
	private String menuName;
	private int menuPrice;
	private String menuIntro;
	private String menuImg;
}
