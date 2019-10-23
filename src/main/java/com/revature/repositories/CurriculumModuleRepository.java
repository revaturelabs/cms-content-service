package com.revature.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.CurriculumModule;

@Repository
/**
 * 
 * @author Java batch 1908
 * 
 * CurriculumModule Service Interface
 *
 */
public interface CurriculumModuleRepository extends CrudRepository<CurriculumModule,Integer> {
	
	Set<CurriculumModule> findByModuleIdIn(List<Integer> modules);
	
	CurriculumModule findById(int cModuleId);
}
