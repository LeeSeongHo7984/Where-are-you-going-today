package com.varxyz.wgt.data;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.varxyz.wgt.board.dao.BoardDao;
import com.varxyz.wgt.login.dao.LoginDao;
import com.varxyz.wgt.login.dao.OwnerLoginDao;
import com.varxyz.wgt.map.dao.MapDao;
import com.varxyz.wgt.owner.dao.OwnerDao;
import com.varxyz.wgt.shop.dao.ShopDao;
import com.varxyz.wgt.user.dao.UserDao;
import com.varxyz.wgt.waiting.dao.WaitingDao;



@Configuration
@ComponentScan(basePackageClasses = {BoardDao.class, OwnerLoginDao.class, ShopDao.class})
public class DataSourceConfig {

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/wgt?serverTimezone=Asia/Seoul");
		ds.setUsername("wgt");
		ds.setPassword("wgt");
		ds.setInitialSize(2); //커넥션 풀 초기화시 생성할 초기 커넥션 갯수(기본값 10)
		ds.setMaxActive(10);  //풀에서 가져올 수 있는 최대 커넥션 갯수(기본값 100)
		ds.setMaxIdle(10);    //풀에 유지할 수 있는 최대 커넥션 수(기본값은 maxActive와 동일)
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		txManager.setDataSource(dataSource());
		return txManager;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public MapDao mapDao() {
		return new MapDao(dataSource());
	}
	
	@Bean
	public WaitingDao waitingDao() {
		return new WaitingDao(dataSource());
	}
	
	@Bean
	public UserDao userDao() {
		return new UserDao(dataSource());
	}	
	
	@Bean
	public OwnerDao ownerDao() {
		return new OwnerDao(dataSource());
	}
	
	@Bean
	public LoginDao loginDao() {
		return new LoginDao(dataSource());
	}
	
}
