package com.varxyz.wgt.shop.dao;

import java.io.File;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.varxyz.wgt.shop.domain.Menu;
import com.varxyz.wgt.shop.domain.MenuCommand;
import com.varxyz.wgt.shop.domain.Shop;


@Repository
public class ShopDao {
private JdbcTemplate jdbcTemplate;
	
	public ShopDao(DataSource dataSourceConfig) {
		jdbcTemplate = new JdbcTemplate(dataSourceConfig);
	}
	
	// 매장명으로 매장 정보 가져오기
	public List<Shop> findAllByShopName(String shopName){
		String sql = "SELECT * FROM shop WHERE shopName = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Shop>(Shop.class), shopName);
	}
	
	// 매장명으로 매장 정보 가져오기
	public Shop findAllByShopNameObject(String shopName){
		String sql = "SELECT * FROM shop WHERE shopName = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Shop>(Shop.class), shopName);
	}
	// 매장 추가
	public boolean addShop(Shop shop) {
		String sql = "INSERT INTO SHOP (businessNumber, shopName, shopTel, shopPostCode, shopAddress, "
				+ " shopDetailAddress, shopExtraAddress, shopHours, shopTables, shopMaxPeoples, shopImg, ownerId) "
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, shop.getBusinessNumber(), shop.getShopName(), shop.getShopTel(),
								shop.getShopPostCode(), shop.getShopAddress(), shop.getShopDetailAddress(),
								shop.getShopExtraAddress(), shop.getShopHours(), shop.getShopTables(), 
								shop.getShopMaxPeoples(), shop.getShopImg(), shop.getOwnerId());
		return true;
	}
	
	// 메뉴 추가
	public boolean addMenu(Menu menu) {
		String sql = "INSERT INTO MENU (businessNumber, menuName, menuIntro, menuPrice, menuImg) "
				+ " VALUES(?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, menu.getBusinessNumber(), menu.getMenuName(), menu.getMenuIntro(), 
								menu.getMenuPrice(), menu.getMenuImg());
		return true;
	}
	
	// 사업자 번호로 매장 검색
	public Shop findShopByBnsNum(String bnsNum) {
		String sql = "SELECT * FROM shop WHERE businessNumber = ?";
		Shop shop = new Shop();
		try {
			shop = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Shop>(Shop.class), bnsNum);
		}catch (EmptyResultDataAccessException e) {			
			return shop;
		}
		return shop;
	}
	
	// 사업자 번호로 매장 메뉴 전체 검색
	public List<Menu> findShopMenuByBnsNum(String bnsNum) {
		String sql = "SELECT * FROM menu WHERE businessNumber = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Menu>(Menu.class), bnsNum);
	}
	
	// 매장 메뉴에 대한 모든 정보 메뉴 이름으로 검색
	public Menu findMenuByMenuName(String menuName) {
		String sql = "SELECT * FROM menu WHERE menuName = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Menu>(Menu.class),menuName);
	}
	
	// 매장 메뉴 수정
	public boolean updateShopMenu(MenuCommand updatedMenu, Menu oldMenu) {
		String sql = "UPDATE menu SET menuName=?, menuIntro=?, menuPrice=?, menuImg=? "
				+ " WHERE menuName = ?";
		jdbcTemplate.update(sql, updatedMenu.getMenuName(), updatedMenu.getMenuIntro(), 
								 updatedMenu.getMenuPrice(), updatedMenu.getMenuImg(), oldMenu.getMenuName());
		File file = new File("C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\shop\\menu_img\\" + oldMenu.getMenuImg() + ".jpg");
		// 집 경로
//		File file = new File("C:\\Users\\hanta\\Desktop\\mycoding\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\shop\\menu_img" + oldMenu.getMenuImg() + ".jpg");
		if(!updatedMenu.getMenuImg().equals(oldMenu.getMenuImg())) {
			file.delete();
		}
		return true;
	}
	
	// 매장 메뉴 삭제
	public boolean deleteShopMenu(String menuName, String menuImg) {
		String sql ="DELETE FROM menu WHERE menuName = ?";
		jdbcTemplate.update(sql, menuName);
		File file = new File("C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\shop\\menu_img\\" + menuImg + ".jpg");
		file.delete();
		return true;
	}
	
	// 매장 정보 수정
	public boolean updateShop(Shop shop ,String oldImg) {
		if(!oldImg.equals(shop.getShopImg())) {
			File file = new File("C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\shop\\shop_Img\\" + oldImg + ".jpg");
			file.delete();
		}

		String sql = "UPDATE shop SET shopTel=?, shopPostCode = ?, shopAddress=?, shopDetailAddress=?, shopExtraAddress=?, shopHours=?, "
				+ " shopTables=?, shopMaxPeoples=?, shopImg = ? WHERE businessNumber = ?";
		jdbcTemplate.update(sql, shop.getShopTel(), shop.getShopPostCode(), shop.getShopAddress(), shop.getShopDetailAddress(), shop.getShopExtraAddress(),
								 shop.getShopHours(), shop.getShopTables(), shop.getShopMaxPeoples(), shop.getShopImg(), shop.getBusinessNumber());
		return true;
	}
	
	
	// 모든 매장 정보 불러오기
	public List<Shop> findAllShop() {
		String sql ="SELECT * FROM shop";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Shop>(Shop.class));
	}

	public List<Menu> findAllMenu() {
		String sql ="SELECT * FROM menu";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Menu>(Menu.class));
	}

	public List<String> findAllBns() {
		String sql ="SELECT businessNumber FROM menu";
		List<String> data = jdbcTemplate.queryForList(sql ,String.class);
		return data;
	}
	
	// 메뉴 중복 검사
	public boolean shopFindMenuCheck(String menuName, String bnsNum) {
		String sql = "SELECT * FROM menu WHERE menuName = ? AND businessNumber = ?";
		try {
			jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Menu>(Menu.class), menuName, bnsNum);			
		}catch (EmptyResultDataAccessException e) {
			return true;
		}
		return false;
	}

	public Shop findShopByOwnerId(String ownerId) {
		String sql = "SELECT * FROM shop WHERE ownerId = ?";
		Shop shop = new Shop();
		try {
			shop = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Shop>(Shop.class), ownerId);
		}catch (EmptyResultDataAccessException e) {
			return shop;
		}
		return shop;
	}
}
