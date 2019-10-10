package com.revature.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Curriculum;

@Repository
public interface CurriculumRepository extends CrudRepository<Curriculum,Integer> {

	Curriculum findByName(String name);
	
	
}
