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
    last_modified numeric
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