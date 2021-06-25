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
   FOREIGN KEY(email) REFERENCES APP_USER(email) ON DELETE CASCADE
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
   FOREIGN KEY(email) REFERENCES APP_USER(email) ON DELETE CASCADE
);

CREATE TABLE CV(
   email VARCHAR(50),
   linkedFile BLOB,
   PRIMARY KEY(email),
   FOREIGN KEY(email) REFERENCES STUDENT(email) ON DELETE CASCADE
);

CREATE TABLE PROFILE_PICTURE(
   email VARCHAR(50),
   linkedFile BLOB NOT NULL,
   PRIMARY KEY(email),
   FOREIGN KEY(email) REFERENCES APP_USER(email) ON DELETE CASCADE
);

CREATE TABLE PROJECT(
   id_Project INTEGER,
   email VARCHAR(50) NOT NULL,
   projectName VARCHAR(30) NOT NULL,
   projectBio VARCHAR(500),
   realisation_year NUMBER(4) NOT NULL,
   PRIMARY KEY(id_Project),
   FOREIGN KEY(email) REFERENCES STUDENT(email) ON DELETE CASCADE
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
   FOREIGN KEY(email) REFERENCES COMPANY(email) ON DELETE CASCADE
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
   FOREIGN KEY(id_School) REFERENCES SCHOOL(id_School) ON DELETE CASCADE,
   FOREIGN KEY(email) REFERENCES STUDENT(email) ON DELETE CASCADE
);

INSERT INTO APP_USER 
VALUES ('hugo.bernard@etu.u-paris.fr', 'BERNARD', '0combsHB', 'Le meilleur en Java');
INSERT INTO STUDENT
VALUES ('hugo.bernard@etu.u-paris.fr', 'Hugo', 1, 0, 2, 'Nucleaire', TO_DATE('01/01/2022', 'DD/MM/YYYY'), 12, 'DUT Informatique');
INSERT INTO PROJECT
VALUES (ID_PROJECT_SEQ.NEXTVAL, 'hugo.bernard@etu.u-paris.fr', 'Stud-Hunt', 'Plateforme de recrutement d alternants', 2021);
INSERT INTO SCHOOL
VALUES (ID_SCHOOL_SEQ.NEXTVAL, 'IUT Paris V (Versailles)');
INSERT INTO is_part_of
VALUES (ID_SCHOOL_SEQ.CURRVAL, 'hugo.bernard@etu.u-paris.fr');

INSERT INTO APP_USER 
VALUES ('tanguy.vidal@etu.u-paris.fr', 'VIDAL', '0alfortTV', 'Le meilleur en IHM');
INSERT INTO STUDENT
VALUES ('tanguy.vidal@etu.u-paris.fr', 'Tanguy', 0, 1, 3, 'Securite', TO_DATE('01/09/2021', 'DD/MM/YYYY'), 12, 'Diplome d Ingénieur');
INSERT INTO PROJECT
VALUES (ID_PROJECT_SEQ.NEXTVAL, 'tanguy.vidal@etu.u-paris.fr', 'Demenager', 'Il habite dans un commisariat et c est pas ouf', 2020);
INSERT INTO SCHOOL
VALUES (ID_SCHOOL_SEQ.NEXTVAL, 'EFREI');
INSERT INTO is_part_of
VALUES (ID_SCHOOL_SEQ.CURRVAL, 'tanguy.vidal@etu.u-paris.fr');

INSERT INTO APP_USER 
VALUES ('frederic.lopez@etu.u-paris.fr', 'LOPEZ', '0jesaisplusFL', 'Le meilleur en voitures');
INSERT INTO STUDENT
VALUES ('frederic.lopez@etu.u-paris.fr', 'Frederic', 1, 1, 1, 'Automobile', TO_DATE('05/06/2025', 'DD/MM/YYYY'), 120, 'Dimplome Carglass');
INSERT INTO PROJECT
VALUES (ID_PROJECT_SEQ.NEXTVAL, 'frederic.lopez@etu.u-paris.fr', 'Mercedes', 'Avoir mieux qu une 207 eclatax', 2050);
INSERT INTO SCHOOL
VALUES (ID_SCHOOL_SEQ.NEXTVAL, 'Mecanico School');
INSERT INTO is_part_of
VALUES (ID_SCHOOL_SEQ.CURRVAL, 'frederic.lopez@etu.u-paris.fr');

INSERT INTO APP_USER 
VALUES ('julien.sacko@etu.u-paris.fr', 'SACKO', '0risorangisJS', 'Le meilleur en Essonne');
INSERT INTO STUDENT
VALUES ('julien.sacko@etu.u-paris.fr', 'Julien', 0, 0, 0, 'Rap', TO_DATE('25/06/2021', 'DD/MM/YYYY'), 6, 'Chouf a Evry');
INSERT INTO PROJECT
VALUES (ID_PROJECT_SEQ.NEXTVAL, 'julien.sacko@etu.u-paris.fr', 'Quitter le 91', 'C est eclate', 2021);
INSERT INTO SCHOOL
VALUES (ID_SCHOOL_SEQ.NEXTVAL, 'L ecole de la rue');
INSERT INTO is_part_of
VALUES (ID_SCHOOL_SEQ.CURRVAL, 'julien.sacko@etu.u-paris.fr');

INSERT INTO APP_USER
VALUES ('contact@companyname1.com', 'CompanyName1', 'CompanyPassword1', 'Meilleure entreprise des entreprises');
INSERT INTO COMPANY
VALUES ('contact@companyname1.com');
INSERT INTO JOB_OFFER
VALUES (ID_JOBOFFER_SEQ.NEXTVAL, 1, 'contact@companyname1.com', 1, 0, 2, 'Nucleaire', TO_DATE('01/01/2022', 'DD/MM/YYYY'), 12);

INSERT INTO APP_USER
VALUES ('contact@companyname2.com', 'CompanyName2', 'CompanyPassword2', 'Meilleure entreprise des ingénieurs');
INSERT INTO COMPANY
VALUES ('contact@companyname2.com');
INSERT INTO JOB_OFFER
VALUES (ID_JOBOFFER_SEQ.NEXTVAL, 2, 'contact@companyname2.com', 0, 1, 3, 'Demenager', TO_DATE('01/06/2021', 'DD/MM/YYYY'), 12);

INSERT INTO APP_USER
VALUES ('contact@companyname3.com', 'CompanyName3', 'CompanyPassword3', 'Meilleure entreprise des mecanos');
INSERT INTO COMPANY
VALUES ('contact@companyname3.com');
INSERT INTO JOB_OFFER
VALUES (ID_JOBOFFER_SEQ.NEXTVAL, 3, 'contact@companyname3.com', 1, 1, 1, 'Automobile', TO_DATE('05/06/2025', 'DD/MM/YYYY'), 120);
INSERT INTO JOB_OFFER
VALUES (ID_JOBOFFER_SEQ.NEXTVAL, 2, 'contact@companyname3.com', 0, 0, 0, 'Rap', TO_DATE('25/06/2021', 'DD/MM/YYYY'), 6);

INSERT INTO APP_USER
VALUES ('contact@companyname4.com', 'CompanyName4', 'CompanyPassword4', 'Meilleure entreprise des gens de la rue');
INSERT INTO COMPANY
VALUES ('contact@companyname4.com');
INSERT INTO JOB_OFFER
VALUES (ID_JOBOFFER_SEQ.NEXTVAL, 4, 'contact@companyname4.com', 0, 0, 0, 'Rap', TO_DATE('25/06/2021', 'DD/MM/YYYY'), 6);