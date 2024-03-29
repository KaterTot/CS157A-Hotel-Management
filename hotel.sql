DROP DATABASE IF EXISTS HOTEL;
CREATE DATABASE HOTEL;
USE HOTEL;

DROP TABLE IF EXISTS MANAGER;
CREATE TABLE MANAGER
(mID INT AUTO_INCREMENT,
 uNAME VARCHAR(30),
 pWORD VARCHAR(30),
 PRIMARY KEY (mID),
 UNIQUE (uName)
) ;
ALTER table MANAGER AUTO_INCREMENT = 1001;

DROP TABLE IF EXISTS CUSTOMER;
CREATE TABLE CUSTOMER
(cID INT AUTO_INCREMENT,
 uNAME VARCHAR(30),
 pWORD VARCHAR(30),
 CreditCard VARCHAR(19) NOT NULL,
 PRIMARY KEY (cID),
 UNIQUE (uName)
) ;
ALTER table CUSTOMER AUTO_INCREMENT = 1001;

DROP TABLE IF EXISTS ROOMTYPE;
CREATE TABLE ROOMTYPE
(roomType VARCHAR(30),
 price DOUBLE,
 numBeds INT,
 PRIMARY KEY(roomType)
) ;

DROP TABLE IF EXISTS ROOM;
CREATE TABLE ROOM
(rID INT AUTO_INCREMENT,
 roomType VARCHAR(30),
 numRented INT,
 PRIMARY KEY (rID),
 FOREIGN KEY(roomType) REFERENCES ROOMTYPE(roomType) on delete cascade
) ;

DROP TABLE IF EXISTS RATING;
CREATE TABLE RATING
(cID INT,
 rID INT,
 stars INT,
 ratingDate TIMESTAMP,
 FOREIGN KEY(cID) REFERENCES CUSTOMER(cID) on delete cascade,
 FOREIGN KEY(rID) REFERENCES ROOM(rID)
 ) ;

DROP TABLE IF EXISTS RESERVATION;
CREATE TABLE RESERVATION
(resID INT AUTO_INCREMENT,
 cID INT,
 rID INT,
 beginDate DATE,
 endDate DATE,
 updateAt DATE,
 PRIMARY KEY(resID),
 FOREIGN KEY(cID) REFERENCES CUSTOMER(cID) on delete cascade, 
 FOREIGN KEY(rID) REFERENCES ROOM(rID) on delete cascade 
) ;

DROP TABLE IF EXISTS CANCELLATION;
CREATE TABLE CANCELLATION
(canclID INT AUTO_INCREMENT,
 rID INT,
 cID INT,
 cancellationDate timestamp default current_timestamp on update current_timestamp,
 UNIQUE (canclID),
 FOREIGN KEY(rID) REFERENCES ROOM(rID) on delete cascade,
 FOREIGN KEY(cID) REFERENCES CUSTOMER(cID) on delete cascade
) ;

DROP TABLE IF EXISTS ARCHIVE;
CREATE TABLE ARCHIVE
(resID INT,
 cID INT,
 rID INT,
 beginDate DATE,
 endDate DATE,
 PRIMARY KEY(resID),
 FOREIGN KEY(cID) REFERENCES CUSTOMER(cID) on delete set null, 
 FOREIGN KEY(rID) REFERENCES ROOM(rID) on delete set null 
) ;

DROP PROCEDURE IF EXISTS archiveReservation;
DELIMITER //
CREATE PROCEDURE archiveReservation (IN archiveDate DATE)
BEGIN
	INSERT INTO ARCHIVE (resID, cID, rID, beginDate, endDate)
		SELECT resID, cID, rID, beginDate, endDate FROM RESERVATION WHERE RESERVATION.updateAt < archiveDate;
	DELETE FROM RESERVATION WHERE RESERVATION.updateAt < archiveDate;
	END //
DELIMITER ;

DROP TRIGGER IF EXISTS reserveARoom;
CREATE TRIGGER reserveARoom
AFTER INSERT ON reservation 
FOR EACH ROW
UPDATE room SET numRented = numRented + 1 WHERE new.rID = room.rID;

DELIMITER //
DROP TRIGGER IF EXISTS cancelReservation;
CREATE TRIGGER cancelReservation
AFTER DELETE ON reservation 
FOR EACH ROW
BEGIN
     IF CURDATE() < OLD.beginDate THEN
  	UPDATE room SET numRented = numRented - 1 WHERE old.rID = room.rID;
	INSERT INTO cancellation(rID, cID, cancellationDate) VALUES (old.rID, old.cID, current_timestamp);
    END IF; 	
END//
DELIMITER ;


LOAD DATA LOCAL INFILE 'E:\\katel\\Documents\\Skool\\CS157A-Hotel-Management-master\\cancellation.txt' INTO TABLE CANCELLATION;
LOAD DATA LOCAL INFILE 'E:\\katel\\Documents\\Skool\\CS157A-Hotel-Management-master\\customer.txt' INTO TABLE CUSTOMER;
LOAD DATA LOCAL INFILE 'E:\\katel\\Documents\\Skool\\CS157A-Hotel-Management-master\\manager.txt' INTO TABLE MANAGER;
LOAD DATA LOCAL INFILE 'E:\\katel\\Documents\\Skool\\CS157A-Hotel-Management-master\\ratings.txt' INTO TABLE RATING;
LOAD DATA LOCAL INFILE 'E:\\katel\\Documents\\Skool\\CS157A-Hotel-Management-master\\reservation.txt' INTO TABLE RESERVATION;
LOAD DATA LOCAL INFILE 'E:\\katel\\Documents\\Skool\\CS157A-Hotel-Management-master\\room.txt' INTO TABLE ROOM;
LOAD DATA LOCAL INFILE 'E:\\katel\\Documents\\Skool\\CS157A-Hotel-Management-master\\roomType.txt' INTO TABLE ROOMTYPE;
