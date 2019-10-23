package com.revature.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Curriculum;

@Repository
/**
 * 
 * @author Java batch 1908
 * 
 * Curriculum Service Interface
 *
 */
public interface CurriculumRepository extends CrudRepository<Curriculum,Integer> {

	Curriculum findByName(String name);
	
	Curriculum findById(int id);
	
}
