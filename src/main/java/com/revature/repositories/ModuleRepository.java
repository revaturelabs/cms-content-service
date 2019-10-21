package com.revature.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Module;

@Repository
@Transactional
public interface ModuleRepository extends CrudRepository<Module,Integer>{

	/**
	 * Finds modules that have a matching subject field.
	 * @param subject - the subject string to match.
	 * @return The set of modules that have that subject.
	 */
	Set<Module> findBysubject(String subject);
	
	/**
	 * Finds a module by the id number.
	 * @param id - the id to match.
	 * @return The module with the matching ID.
	 */
	public Module findById(int id);
}
