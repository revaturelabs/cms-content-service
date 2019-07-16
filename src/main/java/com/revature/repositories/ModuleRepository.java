package com.revature.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Module;

@Repository
public interface ModuleRepository extends CrudRepository<Module,Integer>{

	//Gets modules by subject
	Set<Module> findBysubject(String subject);
	Module findById(int id);
}
