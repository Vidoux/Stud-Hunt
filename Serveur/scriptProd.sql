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

DROP SEQUENCE id_Project_seq
/
DROP SEQUENCE id_JobOffer_Seq
/
DROP SEQUENCE id_Industry_Seq
/
DROP SEQUENCE id_School_Seq
/
/
CREATE SEQUENCE id_Project_seq
/
CREATE SEQUENCE id_JobOffer_Seq
/
CREATE SEQUENCE id_Industry_Seq
/
CREATE SEQUENCE id_School_Seq
/

CREATE TABLE APP_USER(
   email VARCHAR(50),
   name VARCHAR(20) NOT NULL,
   forname VARCHAR(20) NOT NULL,
   password VARCHAR(30) NOT NULL,
   PRIMARY KEY(email)
);

CREATE TABLE COMPANY(
   email VARCHAR(50),
   PRIMARY KEY(email),
   FOREIGN KEY(email) REFERENCES APP_USER(email)
);

CREATE TABLE STUDENT(
   email VARCHAR(50),
   apprenticeship NUMBER(1) NOT NULL,
   internship NUMBER(1) NOT NULL,
   PRIMARY KEY(email),
   FOREIGN KEY(email) REFERENCES APP_USER(email)
);

CREATE TABLE PROJECT(
   id_Project INTEGER,
   realisation_year DATE NOT NULL,
   PRIMARY KEY(id_Project)
);

CREATE TABLE CV(
   email VARCHAR(50),
   linkedFile BLOB,
   PRIMARY KEY(email),
   FOREIGN KEY(email) REFERENCES STUDENT(email)
);

CREATE TABLE PROFILE_PICTURE(
   email VARCHAR(50),
   linkedFile BLOB NOT NULL,
   PRIMARY KEY(email),
   FOREIGN KEY(email) REFERENCES STUDENT(email)
);

CREATE TABLE JOB_OFFER(
   id_JobOffer INTEGER,
   offerType INTEGER NOT NULL,
   email VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_JobOffer),
   FOREIGN KEY(email) REFERENCES COMPANY(email)
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
   email VARCHAR(50),
   PRIMARY KEY(email),
   FOREIGN KEY(email) REFERENCES APP_USER(email)
);

CREATE TABLE Accomplish(
   id_Project INTEGER,
   email VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_Project),
   FOREIGN KEY(id_Project) REFERENCES PROJECT(id_Project),
   FOREIGN KEY(email) REFERENCES STUDENT(email)
);

CREATE TABLE Asso_8(
   email VARCHAR(50),
   id_Industry INTEGER,
   PRIMARY KEY(email, id_Industry),
   FOREIGN KEY(email) REFERENCES COMPANY(email),
   FOREIGN KEY(id_Industry) REFERENCES INDUSTRY(id_Industry)
);

CREATE TABLE concern(
   email VARCHAR(50),
   id_Industry INTEGER,
   PRIMARY KEY(email, id_Industry),
   FOREIGN KEY(email) REFERENCES STUDENT(email),
   FOREIGN KEY(id_Industry) REFERENCES INDUSTRY(id_Industry)
);

CREATE TABLE is_part_of(
   email VARCHAR(50),
   id_School INTEGER,
   PRIMARY KEY(email, id_School),
   FOREIGN KEY(email) REFERENCES STUDENT(email),
   FOREIGN KEY(id_School) REFERENCES SCHOOL(id_School)
);