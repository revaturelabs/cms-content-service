package com.revature.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Module;
import com.revature.entities.ReqLink;

@Repository
public interface ReqLinkRepository extends CrudRepository<ReqLink,Integer> {
	
	//Gets links for a content
	Set<ReqLink> findByRequestId(int requestId);
	
	//Gets links for a module
	Set<ReqLink> findByReqModuleId(int reqModuleId);
	
	//sends back links for Modules given
	Set<ReqLink> findByReqModuleIdIn(List<Module> reqModules);

}
