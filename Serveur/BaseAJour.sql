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
DROP TABLE SCHOOL CASCADE CONSTRAINTS PURGE
/
DROP TABLE is_part_of CASCADE CONSTRAINTS PURGE
/

DROP SEQUENCE ID_PROJECT_SEQ
/
DROP SEQUENCE ID_JOBOFFER_SEQ
/
DROP SEQUENCE ID_SCHOOL_SEQ
/
CREATE SEQUENCE ID_PROJECT_SEQ
/
CREATE SEQUENCE ID_JOBOFFER_SEQ
/
CREATE SEQUENCE ID_SCHOOL_SEQ
/

CREATE TABLE APP_USER(
   email VARCHAR(50),
   name VARCHAR(30) NOT NULL,
   password VARCHAR(40) NOT NULL,
   bio VARCHAR(500),
   PRIMARY KEY(email)
);

CREATE TABLE COMPANY(
   email VARCHAR(50),
   PRIMARY KEY(email),
   FOREIGN KEY(email) REFERENCES APP_USER(email)
);

CREATE TABLE STUDENT(
   email VARCHAR(50),
   forname VARCHAR(20) NOT NULL,
   apprenticeship NUMBER(1) NOT NULL,
   internship NUMBER(1) NOT NULL,
   levelstudy INTEGER,
   industry VARCHAR(30),
   startingdate DATE,
   contractlen INTEGER,
   diploma VARCHAR(30),
   PRIMARY KEY(email),
   FOREIGN KEY(email) REFERENCES APP_USER(email)
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
   FOREIGN KEY(email) REFERENCES APP_USER(email)
);

CREATE TABLE PROJECT(
   id_Project INTEGER,
   email VARCHAR(50) NOT NULL,
   projectName VARCHAR(30) NOT NULL,
   projectBio VARCHAR(500),
   realisation_year NUMBER(4) NOT NULL,
   PRIMARY KEY(id_Project),
   FOREIGN KEY(email) REFERENCES STUDENT(email)
);

CREATE TABLE JOB_OFFER(
   id_JobOffer INTEGER,
   offerType INTEGER NOT NULL,
   email VARCHAR(50) NOT NULL,
   apprenticeship NUMBER(1) NOT NULL,
   internship NUMBER(1) NOT NULL,
   levelstudy INTEGER,
   industry VARCHAR(30),
   startingdate DATE,
   contractlen INTEGER,
   PRIMARY KEY(id_JobOffer),
   FOREIGN KEY(email) REFERENCES COMPANY(email)
);

CREATE TABLE SCHOOL(
   id_School INTEGER,
   schoolName VARCHAR(30),
   PRIMARY KEY(id_School)
);

CREATE TABLE is_part_of(
   id_School INTEGER,
   email VARCHAR(50),
   PRIMARY KEY(id_School, email),
   FOREIGN KEY(id_School) REFERENCES SCHOOL(id_School),
   FOREIGN KEY(email) REFERENCES STUDENT(email)
);

INSERT INTO APP_USER 
VALUES ('hugo.bernard@etu.u-paris.fr', 'BERNARD', '0combsHB', 'Le meilleur en Java');
INSERT INTO STUDENT
VALUES ('hugo.bernard@etu.u-paris.fr', 'Hugo', 1, 0, 2, 'Nucleaire', TO_DATE('01/09/2021', 'DD/MM/YYYY'), 12, 'DUT Informatique');
INSERT INTO PROJECT
VALUES (1, 'hugo.bernard@etu.u-paris.fr', 'Stud-Hunt', 'Plateforme de recrutement d alternants', 2021);
INSERT INTO SCHOOL
VALUES (1, 'IUT Paris V (Versailles)');
INSERT INTO is_part_of
VALUES (1, 'hugo.bernard@etu.u-paris.fr');

INSERT INTO APP_USER
VALUES ('contact@companyname.com', 'CompanyName', 'CompanyPassword', 'Meilleure entreprise des entreprises');
INSERT INTO COMPANY
VALUES ('contact@companyname.com');
INSERT INTO JOB_OFFER
VALUES (1, 1, 'contact@companyname.com', 1, 0, 2, 'Nucleaire', TO_DATE('01/09/2021', 'DD/MM/YYYY'), 12);