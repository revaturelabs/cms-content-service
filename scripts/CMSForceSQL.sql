drop table if exists link;
drop table if exists joins;
drop table if exists module;
drop table if exists content;

create table content(
    c_id serial PRIMARY KEY,
    title text NOT NULL,
    format text NOT NULL,
    description text NOT NULL,
    url text unique NOT NULL,
    created numeric,
    last_modified numeric,
    root text
);

create table module(
    m_id serial PRIMARY KEY,
    subject text,
    created numeric
);

create table link(
    cm_id serial PRIMARY KEY,
    fk_c int,
    affiliation text,
    fk_m int,
    FOREIGN KEY (fk_c) REFERENCES content(c_id) on DELETE CASCADE,
    FOREIGN KEY (fk_m) REFERENCES module(m_id) on DELETE CASCADE
);

create table joins(
	j_id serial primary key,
    fk_m_parent int,
    fk_m_child int,
    FOREIGN KEY (fk_m_parent) REFERENCES module(m_id) on DELETE CASCADE,
    FOREIGN KEY (fk_m_child) REFERENCES module(m_id) on DELETE CASCADE
);



Insert into module ("m_id","subject","created") values (1,'Java',1568327180877);
Insert into module ("m_id","subject","created") values (2,'JDBC',1568327190717);
Insert into module ("m_id","subject","created") values (3,'SQL',1568327195818);
Insert into module ("m_id","subject","created") values (4,'Angular',1568327201304);


Insert into content ("c_id","title","format","description","url","created","last_modified","root") values (1,'JDBC Monkey','Code','This monkey only knows JDBC and Java.','http://jdbcmonkey.org',1568327253876,1568327253876,null);
Insert into content ("c_id","title","format","description","url","created","last_modified","root") values (2,'Java Monkey','Code','This monkey only knows Java programming and likes him some coffee.','http://javamonkey.jp',1568327291691,1568327291691,null);
Insert into content ("c_id","title","format","description","url","created","last_modified","root") values (3,'Java SQL Monkey','Code','This monkey only knows Java and SQL.','http://javasqlmonkey.com',1568327452032,1568327452032,null);


Insert into link ("cm_id","fk_c","affiliation","fk_m") values (1,1,'relaventTo',1);
Insert into link ("cm_id","fk_c","affiliation","fk_m") values (2,1,'relaventTo',2);
Insert into link ("cm_id","fk_c","affiliation","fk_m") values (3,3,'relaventTo',1);
Insert into link ("cm_id","fk_c","affiliation","fk_m") values (4,3,'relaventTo',3);
Insert into link ("cm_id","fk_c","affiliation","fk_m") values (5,2,'relaventTo',1);

Insert into joins ("j_id","fk_m_parent","fk_m_child") values (1,1,2);
Insert into joins ("j_id","fk_m_parent","fk_m_child") values (2,1,3);

