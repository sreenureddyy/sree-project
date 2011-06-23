/*
SQLyog - Free MySQL GUI v5.02
Host - 5.5.8 : Database - sree
*********************************************************************
Server version : 5.5.8
*/


/*Table structure for table `address` */

CREATE TABLE `address` (
  `ADDRESS_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `ADDRESS_TYPE` bigint(20) DEFAULT NULL,
  `CITY` varchar(255) DEFAULT NULL,
  `COUNTRY` varchar(255) DEFAULT NULL,
  `IS_PRIMARY` tinyint(1) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ADDRESS_ID`),
  KEY `FKE66327D4DC255182` (`USER_ID`),
  CONSTRAINT `FKE66327D4DC255182` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `address` */

insert into `address` values 
(1,NULL,NULL,NULL,'in',1,1),
(2,NULL,NULL,NULL,'in',1,2);

/*Table structure for table `authority` */

CREATE TABLE `authority` (
  `AUTHORITY_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AUTHORITY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`AUTHORITY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `authority` */

insert into `authority` values 
(1,'ROLE_ALLACCESS');

/*Table structure for table `contact_details` */

CREATE TABLE `contact_details` (
  `CONTACT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTACT` varchar(255) DEFAULT NULL,
  `CONTACT_TYPE` bigint(20) DEFAULT NULL,
  `IS_PRIMARY` tinyint(1) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CONTACT_ID`),
  KEY `FKE206D4E3DC255182` (`USER_ID`),
  CONSTRAINT `FKE206D4E3DC255182` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `contact_details` */

insert into `contact_details` values 
(1,'sreenureddyy@gmail.com',NULL,1,1),
(2,'techreddys@gmail.com',NULL,1,2);

/*Table structure for table `user` */

CREATE TABLE `user` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_NON_EXPIRED` tinyint(1) DEFAULT NULL,
  `ACCOUNT_NON_LOCKED` tinyint(1) DEFAULT NULL,
  `CREDENTIALS_NON_EXPIRED` tinyint(1) DEFAULT NULL,
  `DOB` datetime NOT NULL,
  `ENABLED` tinyint(1) DEFAULT NULL,
  `FITSTNAME` varchar(255) NOT NULL,
  `GENDER` bigint(20) NOT NULL,
  `LASTNAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `USER_NAME` varchar(255) NOT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_NAME` (`USER_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert into `user` values 
(1,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree',NULL),
(2,1,1,1,'2011-01-05 00:00:00',1,'admin',1,'admi','21232f297a57a5a743894a0e4a801fc3','admin',NULL),
(3,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree3',NULL),
(4,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree4',NULL),
(5,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree5',NULL),
(6,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree6',NULL),
(7,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree7',NULL),
(8,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree8',NULL),
(9,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree9',NULL),
(10,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree10',NULL),
(11,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree11',NULL),
(12,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree12',NULL),
(13,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree13',NULL),
(14,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree14',NULL),
(15,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree15',NULL),
(16,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree16',NULL),
(17,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree17',NULL),
(18,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree18',NULL),
(19,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree19',NULL),
(20,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree20',NULL),
(21,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree21',NULL),
(22,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree22',NULL),
(23,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree23',NULL),
(24,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree24',NULL),
(25,1,1,1,'1983-08-07 00:00:00',1,'Sreenivasa',1,'Reddy','694300511347317d7617ca43d28dd5b4','sree25',NULL),
(26,1,1,1,'2011-01-05 00:00:00',1,'admin',1,'admi','21232f297a57a5a743894a0e4a801fc3','admin26',NULL);

/*Table structure for table `user_authority` */

CREATE TABLE `user_authority` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AUTHORITY_ID` bigint(20) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKBC8EF3AFDC255182` (`USER_ID`),
  KEY `FKBC8EF3AFD15B9832` (`AUTHORITY_ID`),
  CONSTRAINT `FKBC8EF3AFD15B9832` FOREIGN KEY (`AUTHORITY_ID`) REFERENCES `authority` (`AUTHORITY_ID`),
  CONSTRAINT `FKBC8EF3AFDC255182` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `user_authority` */

insert into `user_authority` values 
(1,1,1),
(2,1,2);
