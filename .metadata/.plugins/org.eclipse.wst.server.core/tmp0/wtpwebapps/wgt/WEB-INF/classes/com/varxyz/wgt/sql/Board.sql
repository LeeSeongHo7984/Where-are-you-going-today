CREATE TABLE Board(
   number			BIGINT			PRIMARY KEY AUTO_INCREMENT,
   title			VARCHAR(100)	NOT NULL,
   content			VARCHAR(150)	NOT NULL,
   businessNumber	VARCHAR(13) 	NOT NULL,
   likecount		INT				DEFAULT 0,
   regDate			TIMESTAMP		DEFAULT CURRENT_TIMESTAMP,
   imgname			VARCHAR(100)	DEFAULT NULL,
   userId			VARCHAR(20)		NOT NULL,
   likeImg			VARCHAR(20)		DEFAULT "dislikeheart"
);

   CONSTRAINT Board_userId_FK FOREIGN KEY (userId) REFERENCES User(userId);

SELECT * FROM Board ORDER BY regDate DESC;

SELECT * FROM Board;

SELECT * FROM Board WHERE businessNumber = '111-11-1111' ORDER BY regDate DESC

DROP TABLE Board;

SELECT DATE_FORMAT(regDate,'%y년 %m월 %d일 %H시 %i분') AS DATE FROM Board;

CREATE TABLE Likes(
   userId		VARCHAR(20)		NOT NULL,
   number		BIGINT			DEFAULT 0,
   likeCheck 	VARCHAR(7)		DEFAULT false,
	CONSTRAINT Likes_number_FK FOREIGN KEY (number) REFERENCES Board(number) ON DELETE CASCADE
);

SELECT * FROM Likes;

DROP TABLE Board;

INSERT INTO Likes VALUES(0);
