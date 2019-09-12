package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.RequestModuleHierarchy;

@Repository
public interface ReqModuleHierarchyRepository extends CrudRepository<RequestModuleHierarchy,Integer>{

}
