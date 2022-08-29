CREATE TABLE User(
	userId		VARCHAR(20)		PRIMARY KEY,
	passwd		VARCHAR(20)		NOT NULL,
	name		VARCHAR(20)		NOT NULL,
	ssn			VARCHAR(20)		NOT NULL,
	phone		VARCHAR(20)		NOT NULL,
	addr1		VARCHAR(100)	NOT NULL,
	addr2		VARCHAR(100)	NOT NULL,
	addr3		VARCHAR(100)	NOT NULL,
	addr4		VARCHAR(100)	NOT NULL,
	imgName		VARCHAR(100)	DEFAULT NULL
);

SELECT * FROM User;

DROP TABLE User;

