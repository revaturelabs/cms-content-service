/*Test Variant of Setup Script*/

create table if not exists content(
c_id number (10) primary key,
title varchar2 (200) not null,
format varchar2 (200) not null,
description varchar2 (200) not null,
url varchar2 (200) not null,
created number (20),
last_modified number (20)
);


create table if not exists module(
m_id number (10) primary key,
subject varchar2 (200) unique,
created number (20)
);


create table if not exists link(
cm_id number (10) primary key,
fk_c number (10) ,
affiliation varchar2 (200),
fk_m number (10),
foreign key (fk_c) references content(c_id) on delete cascade,
foreign key (fk_m) references module(m_id)on delete cascade
);

create sequence if not exists HIBERNATE_SEQUENCE;