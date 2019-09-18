package com.revature.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.entities.Request;

@Repository
public interface ReqLinkRepository extends CrudRepository<ReqLink,Integer> {
	
	//Gets links for a content
	Set<ReqLink> findByRequest(Request request);
	
	//Gets links for a module
	Set<ReqLink> findByReqModule(Module reqModule);
	
	//sends back links for Modules given
	Set<ReqLink> findByReqModuleIdIn(List<Module> reqModules);

}