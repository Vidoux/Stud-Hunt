DROP TABLE APP_USER CASCADE CONSTRAINTS PURGE
/
DROP TABLE COMPANY CASCADE CONSTRAINTS PURGE
/
DROP TABLE STUDENT CASCADE CONSTRAINTS PURGE
/
DROP TABLE PROJECT CASCADE CONSTRAINTS PURGE
/
DROP TABLE CV CASCADE CONSTRAINTS PURGE
/
DROP TABLE PROFILE_PICTURE CASCADE CONSTRAINTS PURGE
/
DROP TABLE JOB_OFFER CASCADE CONSTRAINTS PURGE
/
DROP TABLE INDUSTRY CASCADE CONSTRAINTS PURGE
/
DROP TABLE SCHOOL CASCADE CONSTRAINTS PURGE
/
DROP TABLE ADMIN CASCADE CONSTRAINTS PURGE
/
DROP TABLE Accomplish CASCADE CONSTRAINTS PURGE
/
DROP TABLE Asso_8 CASCADE CONSTRAINTS PURGE
/
DROP TABLE concern CASCADE CONSTRAINTS PURGE
/
DROP TABLE is_part_of CASCADE CONSTRAINTS PURGE
/

DROP SEQUENCE id_User_Seq
/
DROP SEQUENCE id_Project_seq
/
DROP SEQUENCE id_CV_Seq
/
DROP SEQUENCE id_ProfilePicture_Seq
/
DROP SEQUENCE id_JobOffer_Seq
/
DROP SEQUENCE id_Industry_Seq
/
DROP SEQUENCE id_School_Seq
/

CREATE SEQUENCE id_User_Seq
/
CREATE SEQUENCE id_Project_seq
/
CREATE SEQUENCE id_CV_Seq
/
CREATE SEQUENCE id_ProfilePicture_Seq
/
CREATE SEQUENCE id_JobOffer_Seq
/
CREATE SEQUENCE id_Industry_Seq
/
CREATE SEQUENCE id_School_Seq
/


CREATE TABLE APP_USER(
   id_User INTEGER,
   name VARCHAR(20) NOT NULL,
   forname VARCHAR(20) NOT NULL,
   password VARCHAR(30) NOT NULL,
   PRIMARY KEY(id_User)
);

CREATE TABLE COMPANY(
   id_User INTEGER,
   PRIMARY KEY(id_User),
   FOREIGN KEY(id_User) REFERENCES APP_USER(id_User)
);

CREATE TABLE STUDENT(
   id_User INTEGER,
   apprenticeship NUMBER(1) NOT NULL,
   internship NUMBER(1) NOT NULL,
   PRIMARY KEY(id_User),
   FOREIGN KEY(id_User) REFERENCES APP_USER(id_User)
);

CREATE TABLE PROJECT(
   id_Project INTEGER,
   realisation_year DATE NOT NULL,
   PRIMARY KEY(id_Project)
);

CREATE TABLE CV(
   id_CV INTEGER,
   id_User INTEGER NOT NULL,
   PRIMARY KEY(id_CV),
   FOREIGN KEY(id_User) REFERENCES STUDENT(id_User)
);

CREATE TABLE PROFILE_PICTURE(
   id_ProfilePicture INTEGER,
   imageBlob BLOB,
   id_User INTEGER NOT NULL,
   PRIMARY KEY(id_ProfilePicture),
   FOREIGN KEY(id_User) REFERENCES STUDENT(id_User)
);

CREATE TABLE JOB_OFFER(
   id_JobOffer INTEGER,
   offerType INTEGER NOT NULL,
   id_User INTEGER NOT NULL,
   PRIMARY KEY(id_JobOffer),
   FOREIGN KEY(id_User) REFERENCES COMPANY(id_User)
);

CREATE TABLE INDUSTRY(
   id_Industry INTEGER,
   PRIMARY KEY(id_Industry)
);

CREATE TABLE SCHOOL(
   id_School INTEGER,
   PRIMARY KEY(id_School)
);

CREATE TABLE ADMIN(
   id_User INTEGER,
   PRIMARY KEY(id_User),
   FOREIGN KEY(id_User) REFERENCES APP_USER(id_User)
);

CREATE TABLE Accomplish(
   id_Project INTEGER,
   id_User INTEGER NOT NULL,
   PRIMARY KEY(id_Project),
   FOREIGN KEY(id_Project) REFERENCES PROJECT(id_Project),
   FOREIGN KEY(id_User) REFERENCES STUDENT(id_User)
);

CREATE TABLE Asso_8(
   id_User INTEGER,
   id_Industry INTEGER,
   PRIMARY KEY(id_User, id_Industry),
   FOREIGN KEY(id_User) REFERENCES COMPANY(id_User),
   FOREIGN KEY(id_Industry) REFERENCES INDUSTRY(id_Industry)
);

CREATE TABLE concern(
   id_User INTEGER,
   id_Industry INTEGER,
   PRIMARY KEY(id_User, id_Industry),
   FOREIGN KEY(id_User) REFERENCES STUDENT(id_User),
   FOREIGN KEY(id_Industry) REFERENCES INDUSTRY(id_Industry)
);

CREATE TABLE is_part_of(
   id_User INTEGER,
   id_School INTEGER,
   PRIMARY KEY(id_User, id_School),
   FOREIGN KEY(id_User) REFERENCES STUDENT(id_User),
   FOREIGN KEY(id_School) REFERENCES SCHOOL(id_School)
);
