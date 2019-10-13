package com.revature.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.CurrModule;

@Repository
public interface CurrModuleRepository extends CrudRepository<CurrModule,Integer> {
	
	CurrModule findByCurriculumName(String name);
	
	Set<CurrModule> findByModuleIdIn(List<Integer> modules);
	
	CurrModule findById(int cModuleId);
}
