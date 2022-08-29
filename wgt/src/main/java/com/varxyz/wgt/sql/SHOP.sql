CREATE TABLE shop (
	businessNumber		VARCHAR(13) 	PRIMARY KEY,
	shopName 			VARCHAR(50) 	NOT NULL,
	shopTel				VARCHAR(13)		NOT NULL,
	shopPostCode		VARCHAR(50)		NOT NULL,
	shopAddress			VARCHAR(50)		NOT NULL,
	shopDetailAddress	VARCHAR(50)		NOT NULL,
	shopExtraAddress	VARCHAR(50)		DEFAULT '없음',
	shopHours			VARCHAR(20)		NOT NULL,
	shopTables			VARCHAR(2)		NOT NULL,
	shopMaxPeoples		VARCHAR(2)		NOT NULL,
	shopImg				VARCHAR(10)		NOT NULL DEFAULT 'default',
	ownerId				VARCHAR(20)		NOT NULL,
	CONSTRAINT Owner_Name_FK
	FOREIGN KEY(ownerId) REFERENCES Owner(ownerId)
);


CREATE TABLE menu (
	businessNumber		VARCHAR(13) 	NOT NULL,
	menuName			VARCHAR(20)		NOT NULL DEFAULT '-',
	menuIntro			VARCHAR(100)	NOT NULL DEFAULT '-',
	menuPrice			INT(6)			NOT NULL DEFAULT 0,
	menuImg				VARCHAR(10)		NOT NULL DEFAULT 'default',
	CONSTRAINT Shop_Name_FK
	FOREIGN KEY(businessNumber) REFERENCES shop(businessNumber)
);



DELETE FROM shop ;
DELETE FROM MENU ;

SELECT * FROM shop;
SELECT * FROM MENU;

DROP TABLE shop;
DROP TABLE menu;

SELECT s.BUSINESS_NUMBER,
	   s.SHOP_NAME,
	   s.SHOP_TEL,
	   s.SHOP_ADDRESS,
	   s.SHOP_HOURS,
	   s.SHOP_IMG,
	   m.SHOP_MENU_NAME,
	   m.SHOP_MENU_INTRO,
	   m.SHOP_MENU_PRICE,
	   m.SHOP_MENU_IMG
	   FROM shop s INNER JOIN
menu m WHERE s.BUSINESS_NUMBER = '123-4568-7891';

SELECT * FROM shop WHERE BUSINESS_NUMBER = '123-456-789';

SELECT businessNumber FROM menu;

--가게 추가
INSERT INTO shop VALUES('999-99-9999', 'testShopName', '053-111-123', '1234', '1234', '1234', '1234', '1234', '1', '1', '1234');

--메뉴 추가
INSERT INTO menu VALUES('999-99-9999', 'testMenu', 'testIntro', 1000, '1234');

SELECT * FROM menu WHERE menuName = 'test10' AND businessNumber = '123-456-789';

/*가게 인설트*/
INSERT INTO shop VALUES('192-83-74655', '시류', '053-898-5837', '41943', '대구 중구 성내1동 동성로1길 46-12', '1층', '(봉산동)', '17:00 ~ 02:00', '10', '4', '036ce847');
INSERT INTO shop VALUES('918-27-36455', '헤기', '053-519-9045', '41913', '대구 중구 경상감영길 184', '1층', '(문화동)', '17:00 ~ 04:00', '14', '8', '9db51a27');
INSERT INTO shop VALUES('554-67-38291', '크로바', '053-753-8284', '41913', '대구 중구 국채보상로125길 17', '1층', '(공평동)', '20:00 ~ 01:00', '8', '7', '3066b592');

/*시류 메뉴*/
INSERT INTO menu VALUES('192-83-74655', '로제 파스타', '진한 로제 파스타', 30000, 'c165c0e6');
INSERT INTO menu VALUES('192-83-74655', '야채 고기 볶음', '신선한 돼지고기', 45000, 'ba7bb83a');

/*헤기 메뉴*/
INSERT INTO menu VALUES('918-27-36455', '고기 짬뽕', '맛있는 짬뽕', 15000, '04d68794');
INSERT INTO menu VALUES('918-27-36455', '야채 김밥', '우영우 김밥', 5000, 'b52605c5');
INSERT INTO menu VALUES('918-27-36455', '뭉티기', '신선한 고기', 55000, 'ba872bf3');

/*크로바 메뉴*/
INSERT INTO menu VALUES('554-67-38291','전골','야채 전골', 21000, '89ab076e');
