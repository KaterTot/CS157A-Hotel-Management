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
 CreditCard VARCHAR(19),
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
 ratingDate DATE,
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
 PRIMARY KEY(resID),
 FOREIGN KEY(cID) REFERENCES CUSTOMER(cID) on delete cascade, 
 FOREIGN KEY(rID) REFERENCES ROOM(rID) on delete cascade 
) ;

DROP TABLE IF EXISTS CANCELLATION;
CREATE TABLE CANCELLATION
(canclID INT,
 rID INT,
 cID INT,
 cancellationDate timestamp not null on update current_timestamp,
 UNIQUE (canclID),
 FOREIGN KEY(rID) REFERENCES ROOM(rID) on delete cascade,
 FOREIGN KEY(cID) REFERENCES CUSTOMER(cID) on delete cascade
) ;


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
	UPDATE room SET numRented = numRented - 1 WHERE old.rID = room.rID;
	INSERT INTO cancellation(canclID, rID, cID, cancellationDate) VALUES (old.resID, old.rID, old.cID, current_timestamp);
END//
DELIMITER ; 

