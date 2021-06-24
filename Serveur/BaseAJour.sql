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
   name VARCHAR(20) NOT NULL,
   password VARCHAR(30) NOT NULL,
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
   levelstudy VARCHAR(20),
   industry VARCHAR(20),
   startingdate DATE,
   contractlen INTEGER,
   diploma VARCHAR(20),
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
   projectName VARCHAR(30) NOT NULL,
   projectBio VARCHAR(500),
   realisation_year NUMBER(4) NOT NULL,
   email VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_Project),
   FOREIGN KEY(email) REFERENCES STUDENT(email)
);

CREATE TABLE JOB_OFFER(
   id_JobOffer INTEGER,
   offerType INTEGER NOT NULL,
   email VARCHAR(50) NOT NULL,
   apprenticeship NUMBER(1) NOT NULL,
   internship NUMBER(1) NOT NULL,
   levelstudy VARCHAR(20),
   industry VARCHAR(20),
   startingdate DATE,
   contractlen INTEGER,
   PRIMARY KEY(id_JobOffer),
   FOREIGN KEY(email) REFERENCES COMPANY(email)
);

CREATE TABLE SCHOOL(
   id_School INTEGER,
   schoolName VARCHAR(20),
   PRIMARY KEY(id_School)
);

CREATE TABLE is_part_of(
   id_School INTEGER,
   email VARCHAR(50),
   PRIMARY KEY(id_School, email),
   FOREIGN KEY(id_School) REFERENCES SCHOOL(id_School),
   FOREIGN KEY(email) REFERENCES STUDENT(email)
);

insert into PROJECT (id_Project, projectName, realisation_year, email) values (1, 'PJS4', 2021, 'bonjour@hotmail.fr');
insert into PROJECT (id_Project, projectName, realisation_year, email) values (2, 'DessinEclate', 2002, 'etudiant@hotmail.fr');
insert into PROJECT (id_Project, projectName, realisation_year, email) values (3, 'SiteWeb', 2006, 'salut@hotmail.fr');
insert into APP_USER (email, name, password) values ('bonjour@hotmail.fr', 'sacko', 'doublebang');
insert into APP_USER (email, name, password) values ('company@hotmail.fr','BMW', 'Tchop');
insert into APP_USER (email, name, password) values ('etudiant@hotmail.fr', 'lamba', 'mdp');
insert into APP_USER (email, name, password) values ('salut@hotmail.fr', 'lamba', 'mdp');
insert into COMPANY (email) values ('company@hotmail.fr');
insert into STUDENT (email, forname, apprenticeship, internship, levelstudy, industry, startingdate, contractlen, diploma) values ('bonjour@hotmail.fr', 'julien', 1, 0, 5,'informatique',TO_DATE('2021-09-01', 'YYYY/MM/DD'), 3,'ingenieur');
insert into STUDENT (email, forname, apprenticeship, internship, levelstudy, industry, startingdate, contractlen, diploma) values ('etudiant@hotmail.fr', 'blazeArt', 0, 1, 2,'art',TO_DATE('2021-07-01', 'YYYY/MM/DD'), 2, 'beauxarts');
insert into STUDENT (email, forname, apprenticeship, internship, levelstudy, industry, startingdate, contractlen, diploma) values ('salut@hotmail.fr', 'blazeInfo', 1, 1, 7,'informatique',TO_DATE('2021-05-01', 'YYYY/MM/DD'), 3, 'universite');
insert into SCHOOL(id_School, schoolName) values (1, 'Esiee Paris');
insert into SCHOOL(id_School, schoolName) values (2, 'ParisDescartes');
insert into SCHOOL(id_School, schoolName) values (3, 'Magnifico');
insert into is_part_of(id_School, email) values (1,'bonjour@hotmail.fr');
insert into is_part_of(id_School, email) values (2,'bonjour@hotmail.fr');
insert into is_part_of(id_School, email) values (1,'salut@hotmail.fr');
insert into is_part_of(id_School, email) values (3,'etudiant@hotmail.fr');
insert into JOB_OFFER(id_JobOffer, offerType, email, levelstudy, industry, startingdate, contractlen, apprenticeship, internship) values (1, 50, 'company@hotmail.fr', 3,'informatique',TO_DATE('2021-10-01', 'YYYY/MM/DD'),3, 1, 1);
insert into JOB_OFFER(id_JobOffer, offerType, email, levelstudy, industry, startingdate, contractlen, apprenticeship, internship) values (2, 100, 'company@hotmail.fr', 6,'informatique',TO_DATE('2021-06-01', 'YYYY/MM/DD'),3, 1, 1);
insert into JOB_OFFER(id_JobOffer, offerType, email, levelstudy, industry, startingdate, contractlen, apprenticeship, internship) values (3, 101, 'company@hotmail.fr', 1,'art',TO_DATE('2021-08-01', 'YYYY/MM/DD'),2, 1, 1);
insert into PROJECT (id_Project, projectName, realisation_year, email) values (1, 'PJS4', 2021, 'bonjour@hotmail.fr');
insert into PROJECT (id_Project, projectName, realisation_year, email) values (2, 'DessinEclate', 2002, 'etudiant@hotmail.fr');
insert into PROJECT (id_Project, projectName, realisation_year, email) values (3, 'SiteWeb', 2006, 'salut@hotmail.fr');