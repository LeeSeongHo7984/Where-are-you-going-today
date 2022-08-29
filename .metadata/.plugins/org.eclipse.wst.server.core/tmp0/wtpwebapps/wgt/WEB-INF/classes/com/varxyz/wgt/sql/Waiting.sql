CREATE TABLE Waiting(
	aid					BIGINT			PRIMARY KEY AUTO_INCREMENT,
	barName				VARCHAR(20)		NOT NULL,
	num_people			BIGINT			NOT NULL,
	userId				VARCHAR(20)		NOT NULL,
	regDate				TIMESTAMP		DEFAULT CURRENT_TIMESTAMP,
	waitingStartTime	VARCHAR(20)		DEFAULT 0,
	CONSTRAINT Waiting_userId_FK FOREIGN KEY (userId) REFERENCES User(userId)
);

SELECT * FROM Waiting;

DROP TABLE Waiting;

--------------------------------------------------------------------------------------------------

UPDATE Account SET balance = 1000000 WHERE customerId = 'lty';
UPDATE Account A INNER JOIN Customer C ON A.customerId = C.id 
DELETE FROM Waiting WHERE barName = '사랑이야';
SET A.balance = 1000000 WHERE A.accountNum = '539-11-1187' AND C.passwd = '1234';
alter table Menu add picture VARCHAR(100) not null;