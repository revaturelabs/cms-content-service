drop table link;
drop table module;
drop table content;
?drop table users;

CREATE TABLE USERS(
ID                    NUMBER(19) PRIMARY KEY,
CREATED_DATE          DATE    ,
EMAIL                 VARCHAR2(255 CHAR) ,
FIRST_NAME            VARCHAR2(255 CHAR) ,
LAST_NAME             VARCHAR2(255 CHAR) ,
PASSWORD              VARCHAR2(255 CHAR) ,
RESET_TOKEN           VARCHAR2(255 CHAR) ,
ROLE                  VARCHAR2(255 CHAR)
);
?
create table content(
c_id number (10) primary key,
title varchar2 (200) not null,
format varchar2 (200) not null,
description varchar2 (200) not null,
url varchar2 (200) not null,
created number (20),
last_modified number (20),
userid number(10),
status varchar2(50),
foreign key (userid) references users(id) on delete cascade
?
);
?
?
?
create table module(
m_id number (10) primary key,
subject varchar2 (200) unique,
created number (20)
);
?
?
create table link(
cm_id number (10) primary key,
fk_c number (10) ,
affiliation varchar2 (200),
fk_m number (10),
foreign key (fk_c) references content(c_id) on delete cascade,
foreign key (fk_m) references module(m_id)on delete cascade
);
?
create sequence HIBERNATE_SEQUENCE;