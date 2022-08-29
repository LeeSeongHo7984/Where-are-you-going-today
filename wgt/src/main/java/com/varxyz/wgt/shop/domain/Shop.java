package com.varxyz.wgt.shop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Shop {
	private String businessNumber;
	private String shopName;
	private String shopTel;
	private String shopPostCode;
	private String shopAddress;
	private String shopDetailAddress;
	private String shopExtraAddress;
	private String shopHours;
	private String shopTables;
	private String shopMaxPeoples;
	private String shopImg;
	private String ownerId;
}
