CREATE TABLE test(
name 			VARCHAR(25) 	NOT NULL,
address			VARCHAR(100)	NOT NULL PRIMARY KEY,
longitude		DOUBLE			NOT NULL,
latitude		DOUBLE			NOT NULL
)

SELECT * FROM test;

INSERT INTO test VALUES('고영희식당', '대구 중구 달구벌대로 2109-10', 35.8658211, 128.5944444);
INSERT INTO test VALUES('영희네 김치찜', '대구광역시 중구 중앙대로 366', 35.8659896, 128.5937806);
INSERT INTO test VALUES('뜨돈', '대구 중구 달구벌대로 2109-30', 35.8666504, 128.5941254);
DROP TABLE test
SELECT * FROM test WHERE name like '%영희%'