package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Module;

@Repository
public interface ModuleRepository extends CrudRepository<Module,Integer>{

}
