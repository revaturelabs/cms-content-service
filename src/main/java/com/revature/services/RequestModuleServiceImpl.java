package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Requests;
import com.revature.entities.ReqLink;
import com.revature.entities.ReqModule;
import com.revature.entities.RequestModuleHierarchy;
import com.revature.repositories.RequestRepository;
import com.revature.repositories.ReqLinkRepository;
import com.revature.repositories.ReqModuleHierarchyRepository;
import com.revature.repositories.ReqModuleRepository;
import com.revature.util.LogException;

@Service
public class RequestModuleServiceImpl implements RequestModuleService {
	
	@Autowired
	ReqModuleRepository rmr;
	@Autowired
	ReqLinkRepository rlr;
	@Autowired
	ReqModuleHierarchyRepository rmhr;
	@Autowired
	RequestRepository rr;

	/**
	 * Get all the modules in the database and returns a set
	 * of reqModule objects.
	 */
	@Override
	@LogException
	public Set<ReqModule> getAllReqModules() {
		Set<ReqModule> modules = new HashSet<>();
		rmr.findAll().forEach(modules :: add);
		return modules;
	}

	/**
	 * Get a reqModule from the database that matches the id passed in\
	 * then returns a reqModule object.
	 */
	@Override
	@LogException
	public ReqModule getReqModuleById(int id) {
		return rmr.findById(id);
	}

	/**
	 * Take the reqModule passed in and change the created value 
	 * to the current time then add it to the database.
	 */
	@Override
	@LogException
	public ReqModule createReqModule(ReqModule reqModule) {
		reqModule.setCreated(System.currentTimeMillis());
		if(reqModule.getChildrenModules() == null){
			reqModule.setChildrenModules(Collections.emptySet());
		}
		if(reqModule.getParentModules() == null){
			reqModule.setParentModules(Collections.emptySet());
		}
		reqModule = rmr.save(reqModule);
		return reqModule;
	}

	
	/**
	 * Calculate the average for all of the reqModule's resources based on the specific inputed modules
	 * */
	@Override
	@LogException
	public double getAverageByReqModuleIds(ArrayList<Integer> ids) {
		int size = rlr.findByReqModuleIdIn(ids).size();
		
		return (double) size / (double) ids.size();
	}

	/**
	 * Calculate the average for all of the modules's resources
	 */
	@Override
	public double getAverageByAllReqModules() {
		Set<ReqModule> allMods = this.getAllReqModules();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		for(ReqModule m : allMods) {
			ids.add(m.getId());
		}
		return this.getAverageByReqModuleIds(ids);
	}

	/**
	 * Remove a specified reqModule from the database
	 */
	@Override
	public void deleteReqModule(ReqModule reqModule) {
		if(reqModule != null) {
			rmr.delete(reqModule);
		}
	}
	
	public Set<ReqModule> getAllReqModulesByRoot(){
		Set<ReqModule> modules = new HashSet<>();
		findReqModuleByNoParent().forEach(modules :: add);
		return modules;
	}
	
	Set<ReqModule> findReqModuleByNoParent(){
		Set<ReqModule> modules = getAllReqModules();
		Set<ReqModule> finModules = new HashSet<>();
		for(ReqModule specModule: modules) {
			if(specModule.getParentModules().size() == 0) {
				finModules.add(specModule);
			}
		}
		return finModules;
	}	
	@Override
	public Set<ReqModule> getChildrenByReqModuleId(int id){
		ReqModule parent = rmr.findById(id);
		Set<ReqModule> childrenModules = new HashSet<>();
		getChildren(parent).forEach(childrenModules :: add);
		return childrenModules;
	}
	
	Set<ReqModule> getChildren(ReqModule parent){
		Set<ReqModule> childrenModule = new HashSet<>();
		Set<Integer> children = parent.getChildrenModules();
		for(Integer moduleID: children) {
			ReqModule child = rmr.findById(moduleID.intValue());
			childrenModule.add(child);
		}
		return childrenModule;
	}
	
	@Override
	public void setChildToParent(int parentId, int childId) {
		RequestModuleHierarchy reqModuleHierarchy = new RequestModuleHierarchy(parentId,childId);
		reqModuleHierarchy = rmhr.save(reqModuleHierarchy);
	}
	
	@Override
	public void deleteReqModuleWithAllContent(ReqModule reqModule) {
		Set<ReqLink> moduleList = reqModule.getReqLinks();
		for(ReqLink specReqLink:moduleList) {
			int requestId = specReqLink.getRequestId();
			rr.deleteById(requestId);
		}
		rmr.delete(reqModule);
	}
	
	@Override
	public void deleteReqModuleWithSpecificContent(ReqModule reqModule) {
		Set<ReqLink> moduleList = reqModule.getReqLinks();
		for(ReqLink specReqLink:moduleList) {
			int requestId = specReqLink.getRequestId();
			Set<Requests> contentList = rr.findById(requestId);
			for(Requests specReq:contentList) {
				if(specReq.getReqLinks().size() == 1) {
					rr.deleteById(specReq.getId());
				}
				specReq.getReqLinks().remove(specReqLink);
			}
		}
		rmr.delete(reqModule);
	}
}

