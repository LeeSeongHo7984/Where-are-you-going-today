package com.varxyz.wgt.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.varxyz.wgt.shop.domain.Shop;

public class ShopRowMapper implements RowMapper<Shop> {

	@Override
	public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
		Shop shop = new Shop();
		shop.setBusinessNumber(rs.getString("BUSINESS_NUMBER"));
		shop.setShopName("SHOP_NAME");
		shop.setShopTel("SHOP_TEL");
		shop.setShopPostCode("SHOP_POSTCODE");
		shop.setShopAddress("SHOP_ADDRESS");
		shop.setShopDetailAddress("SHOP_DETAILADDRESS");
		shop.setShopExtraAddress("SHOP_EXTRAADDRESS");
		shop.setShopHours("SHOP_HOURS");
		shop.setShopTables("SHOP_TABLES");
		shop.setShopMaxPeoples("SHOP_MAX_PEOPLES");
		shop.setShopImg("SHOP_IMG");
		
		return shop;
	}
	
}
