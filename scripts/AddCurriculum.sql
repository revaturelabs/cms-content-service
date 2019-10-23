drop table if exists curriculum_module;
drop table if exists curriculum;


create table curriculum(
	curriculum_id serial PRIMARY KEY,
	name text NOT NULL
);

create table curriculum_module(
	curriculum_module_id serial,
	curriculum_id int,
	module_id int,
	priority int,
	FOREIGN KEY (curriculum_id) REFERENCES curriculum(curriculum_id) on DELETE CASCADE,
	FOREIGN KEY (module_id) REFERENCES module(m_id) on DELETE CASCADE,
	PRIMARY KEY (curriculum_module_id)
);
