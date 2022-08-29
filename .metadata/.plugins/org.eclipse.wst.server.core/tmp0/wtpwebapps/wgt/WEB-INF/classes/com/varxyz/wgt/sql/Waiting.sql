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

INSERT INTO shop 
			VALUES ("1", "시류", "053-587-3180", "대구","1", "10", "50", "ㅇㄴㅇ");

INSERT INTO Waiting (barName, num_people, userId)
			VALUES ("시류", 7, "lty");
INSERT INTO User VALUES ("psg", "1234", "상규박", "980618", "01034903180", "대구요", "dsds");
--------------------------------------------------------------------------------------------------

UPDATE Account SET balance = 1000000 WHERE customerId = 'lty';
UPDATE Account A INNER JOIN Customer C ON A.customerId = C.id 
DELETE FROM Waiting WHERE barName = '시류';
SET A.balance = 1000000 WHERE A.accountNum = '539-11-1187' AND C.passwd = '1234';
alter table Menu add picture VARCHAR(100) not null;