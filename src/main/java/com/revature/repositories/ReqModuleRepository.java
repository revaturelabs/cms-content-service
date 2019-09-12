package com.revature.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.ReqModule;

@Repository
public interface ReqModuleRepository extends CrudRepository<ReqModule,Integer>{

	Set<ReqModule> findBysubject(String subject);
	
	ReqModule findById(int id);
}
