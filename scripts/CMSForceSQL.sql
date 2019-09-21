--the ordering in which these are dropped is important
--because of constraints between tables
drop table if exists link;
drop table if exists joins;
drop table if exists req_link;
drop table if exists requests;
drop table if exists module;
drop table if exists content;


create table content(
   c_id serial PRIMARY KEY,
   title text NOT NULL,
   format text NOT NULL,
   description text NOT NULL,
   url text unique NOT NULL,
   created numeric NOT NULL,
   last_modified numeric NOT NULL
);
create table module(
   m_id serial PRIMARY KEY,
   subject text unique,
   created numeric
);

--the affiliation column is needed, but not implemented yet
--it is for future features and currently is asked to be made available
create table link(
   cm_id serial PRIMARY KEY,
   fk_c int,
   fk_m int,
   affiliation text,
   FOREIGN KEY (fk_c) REFERENCES content(c_id) on DELETE CASCADE,
   FOREIGN KEY (fk_m) REFERENCES module(m_id) on DELETE CASCADE
);
--this table is simply a join table to create the parent-child hierarchy
create table joins(
   fk_m_parent int,
   fk_m_child int,
   FOREIGN KEY (fk_m_parent) REFERENCES module(m_id) on DELETE CASCADE,
   FOREIGN KEY (fk_m_child) REFERENCES module(m_id) on DELETE CASCADE
);
create table requests(
  r_id serial PRIMARY KEY,
  title text NOT NULL,
  format text NOT NULL,
  description text NOT NULL,
  fk_rc int unique,
  created numeric NOT NULL,
  last_modified numeric NOT NULL,
  FOREIGN KEY (fk_rc) REFERENCES content(c_id)
);
create table req_link(
  z_id serial PRIMARY KEY,
  fk_r int,
  fk_rm int,
  affiliation text,
  FOREIGN KEY (fk_r) REFERENCES requests(r_id) on DELETE CASCADE,
  FOREIGN KEY (fk_rm) REFERENCES module(m_id) on DELETE CASCADE
);

--insert into requests (r_id, title, format, description, url, created, last_modified) values (100, 'something', 'something2', 'something3', 'http://www.b.com', 2, 2);
--insert into requests (r_id, title, format, description, url, created, last_modified) values (101, 'something', 'something2', 'something3', 'http://www.a.com', 1, 1);
--select * from requests;
commit work;