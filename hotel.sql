DROP DATABASE IF EXISTS HOTEL;
CREATE DATABASE HOTEL;
USE HOTEL; 

DROP TABLE IF EXISTS ROOM;
CREATE TABLE ROOM
(rID INT AUTO_INCREMENT,
 numRented INT,
 PRIMARY KEY (rID),
 FOREIGN KEY(roomType) REFERENCES roomType(roomType) on delete cascade
) ;

DROP TABLE IF EXISTS ROOMTYPE;
CREATE TABLE ROOMTYPE
(roomType VARCHAR(30),
 price DOUBLE,
 numBeds INT,
 PRIMARY KEY(roomType)
) ;

DROP TABLE IF EXISTS MANAGER;
CREATE TABLE MANAGER
(mID INT AUTO_INCREMENT,
 uNAME VARCHAR(30),
 pWORD VARCHAR(30),
 PRIMARY KEY (uID),
 UNIQUE (uName)
) ;
ALTER table mID AUTO_INCREMENT = 1001;

DROP TABLE IF EXISTS CUSTOMER;
CREATE TABLE CUSTOMER
(cID INT AUTO_INCREMENT,
 uNAME VARCHAR(30),
 pWORD VARCHAR(30),
 CreditCard INT,
 PRIMARY KEY (cID),
 UNIQUE (uName)
) ;
ALTER table cID AUTO_INCREMENT = 1001;

DROP TABLE IF EXISTS RESERVATION;
CREATE TABLE RESERVATION
(resID INT AUTO_INCREMENT,
 cID INT,
 rID INT,
 beginDate DATE,
 endDate DATE,
 PRIMARY KEY(resID),
 FOREIGN KEY(cID) REFERENCES customer(cID) on delete cascade, 
 FOREIGN KEY(rID) REFERENCES room(rID) on delete cascade 
) ;

DROP TABLE IF EXISTS CANCELLATION;
CREATE TABLE CANCELLATION
(canclID INT,
 rID INT,
 cID INT,
 cancellationDate timestamp not null on update current_timestamp,
 FOREIGN KEY(canclID) REFERENCES reservation(resID) on delete cascade,
 FOREIGN KEY(rID) REFERENCES room(rID) on delete cascade,
 FOREIGN KEY(cID) REFERENCES customer(cID) on delete cascade
) ;


