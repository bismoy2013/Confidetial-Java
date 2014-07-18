CREATE TABLE USERS(
USER_ID BIGINT PRIMARY KEY AUTO_INCREMENT, 
EMAIL_ADDRESS VARCHAR(100) NOT NULL, 
PASSWORD VARCHAR(100) NOT NULL, 
FIRST_NAME VARCHAR(100),
LAST_NAME VARCHAR(100),
DESIGNATION VARCHAR(100),
LAST_SIGNED_IN_DATE TIMESTAMP,
PROFILE_PIC_URL VARCHAR(500),
ENABLED BOOLEAN,
ACCOUNT_EXPIRED BOOLEAN,
ACCOUNT_LOCKED BOOLEAN,
CREDENTIALS_EXPIRED BOOLEAN,
CREATE_TIMESTAMP TIMESTAMP,
UPDATE_TIMESTAMP TIMESTAMP);

CREATE TABLE ROLES(
ROLE_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
ROLE_NAME VARCHAR(200) NOT NULL,
CREATE_TIMESTAMP TIMESTAMP,
UPDATE_TIMESTAMP TIMESTAMP);

CREATE TABLE GROUPS(
GROUP_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
GROUP_NAME VARCHAR(200) NOT NULL,
PARENT_GROUP_ID INT(10),
CREATE_TIMESTAMP TIMESTAMP,
UPDATE_TIMESTAMP TIMESTAMP);

CREATE TABLE USER_ROLES(
USER_ROLE_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
USER_ID BIGINT NOT NULL,
ROLE_ID BIGINT NOT NULL);

CREATE TABLE USER_GROUPS(
USER_GROUP_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
USER_ID BIGINT NOT NULL,
GROUP_ID BIGINT NOT NULL,
IS_GROUP_ADMIN BOOLEAN);

INSERT INTO USERS VALUES (1,'harsha.sake@fissionlabs.in','123456','HARSHA','SAKE','CEO',NOW(),'NA',TRUE,FALSE,FALSE,FALSE,NOW(),NOW());
INSERT INTO USERS VALUES (2,'seshagiri@fissionlabs.in','123456','SESHU','VELLANKI','CFO',NOW(),'NA',TRUE,TRUE,FALSE,FALSE,NOW(),NOW());
INSERT INTO USERS VALUES (3,'srikanth.suham@fissionlabs.in','123456','SRIKANTH','SUHAN','COO',NOW(),'NA',FALSE,TRUE,FALSE,FALSE,NOW(),NOW());
INSERT INTO USERS VALUES (4,'bismoy.murasing@fissionlabs.in','123456','BISMOY','MURASING','VP',NOW(),'NA',FALSE,TRUE,TRUE,FALSE,NOW(),NOW());
INSERT INTO USERS VALUES (5,'raghav.arram@fissionlabs.in','123456','SAI RAGHAV','ARRAM','AVP',NOW(),'NA',FALSE,TRUE,TRUE,TRUE,NOW(),NOW());

INSERT INTO ROLES VALUES (1, 'ADMIN',NOW(),NOW());
INSERT INTO ROLES VALUES (2, 'BUSINESS_MANAGER',NOW(),NOW());
INSERT INTO ROLES VALUES (3, 'DATA_ANALYST',NOW(),NOW());

INSERT INTO GROUPS (GROUP_ID,GROUP_NAME,CREATE_TIMESTAMP,UPDATE_TIMESTAMP) VALUES (1,'USER_GROUP_1',NOW(),NOW());
INSERT INTO GROUPS (GROUP_ID,GROUP_NAME,CREATE_TIMESTAMP,UPDATE_TIMESTAMP) VALUES (2,'USER_GROUP_2',NOW(),NOW());
INSERT INTO GROUPS VALUES (3,'USER_GROUP_3',1,NOW(),NOW());
INSERT INTO GROUPS VALUES (4,'USER_GROUP_4',1,NOW(),NOW());
INSERT INTO GROUPS VALUES (5,'USER_GROUP_5',2,NOW(),NOW());

INSERT INTO USER_ROLES VALUES(1,1,1);
INSERT INTO USER_ROLES VALUES(2,2,2);
INSERT INTO USER_ROLES VALUES(3,1,2);
INSERT INTO USER_ROLES VALUES(4,3,3);
INSERT INTO USER_ROLES VALUES(5,1,3);

INSERT INTO USER_GROUPS VALUES (1,1,1,TRUE);
INSERT INTO USER_GROUPS VALUES (2,1,2,FALSE);
INSERT INTO USER_GROUPS VALUES (3,2,1,TRUE);
INSERT INTO USER_GROUPS VALUES (4,2,2,TRUE);
INSERT INTO USER_GROUPS VALUES (5,2,3,FALSE);
INSERT INTO USER_GROUPS VALUES (6,3,3,FALSE);
INSERT INTO USER_GROUPS VALUES (7,3,4,FALSE);
INSERT INTO USER_GROUPS VALUES (8,4,4,FALSE);
INSERT INTO USER_GROUPS VALUES (9,4,5,TRUE);
INSERT INTO USER_GROUPS VALUES (10,3,5,TRUE);

CREATE TABLE USER_TOKENS(
USER_TOKEN_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
USER_ID BIGINT NOT NULL,
AUTHENTICATION_TOKEN VARCHAR(200) NOT NULL,
TOKEN_TYPE VARCHAR(100) NOT NULL,
EXPIRY_DATE TIMESTAMP);
