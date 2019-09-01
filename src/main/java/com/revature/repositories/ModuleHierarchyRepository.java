package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.ModuleHierarchy;

@Repository
public interface ModuleHierarchyRepository extends CrudRepository<ModuleHierarchy,Integer>{

}
