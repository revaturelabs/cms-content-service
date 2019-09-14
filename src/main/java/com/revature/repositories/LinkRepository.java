package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.ContentPlusModules;

@Repository
public interface LinkRepository extends CrudRepository<ContentPlusModules,Integer> {
	
	//Gets links for a content
	Set<ContentPlusModules> findByContentId(int contentId);
	
	//Gets links for a module
	Set<ContentPlusModules> findByModuleId(int moduleId);
	
	//sends back links for Modules given
	Set<ContentPlusModules> findByModuleIdIn(List<Integer> modules);

}
