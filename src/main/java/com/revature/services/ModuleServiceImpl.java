package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Module;
import com.revature.entities.ModuleHierarchy;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleHierarchyRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.util.LogException;

@Service
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	ModuleRepository mr;
	@Autowired
	LinkRepository lr;
	@Autowired
	ModuleHierarchyRepository mhr;

	/**
	 * Get all the modules in the database and returns a set
	 * of module objects.
	 */
	@Override
	@LogException
	public Set<Module> getAllModules() {
		Set<Module> modules = new HashSet<>();
		mr.findAll().forEach(modules :: add);
		return modules;
	}

	/**
	 * Get a module from the database that matches the id passed in\
	 * then returns a module object.
	 */
	@Override
	@LogException
	public Module getModuleById(int id) {
		return mr.findById(id);
	}

	/**
	 * Take the module passed in and change the created value 
	 * to the current time then add it to the database.
	 */
	@Override
	@LogException
	public Module createModule(Module module) {
		module.setCreated(System.currentTimeMillis());
		module = mr.save(module);
		return module;
	}

	
	/**
	 * Calculate the average for all of the module's resources based on the specific inputed modules
	 * */
	@Override
	@LogException
	public double getAverageByModuleIds(ArrayList<Integer> ids) {
		int size = lr.findByModuleIdIn(ids).size();
		
		return (double) size / (double) ids.size();
	}

	/**
	 * Calculate the average for all of the modules's resources
	 */
	@Override
	public double getAverageByAllModules() {
		Set<Module> allMods = this.getAllModules();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		for(Module m : allMods) {
			ids.add(m.getId());
		}
		return this.getAverageByModuleIds(ids);
	}

	/**
	 * Remove a specified module from the database
	 */
	@Override
	public void deleteModule(Module module) {
		if(module != null) {
			mr.delete(module);
		}
	}
	
	public Set<Module> getAllModulesByRoot(){
		Set<Module> modules = new HashSet<>();
		findModuleByNoParent().forEach(modules :: add);
		return modules;
	}
	
	Set<Module> findModuleByNoParent(){
		Set<Module> modules = getAllModules();
		Set<Module> finModules = new HashSet<>();
		for(Module specModule: modules) {
			if(specModule.getParentModules().size() == 0) {
				finModules.add(specModule);
			}
		}
		return finModules;
	}	
	@Override
	public Set<Module> getChildrenByModuleId(int id){
		Module parent = mr.findById(id);
		Set<Module> childrenModules = new HashSet<>();
		getChildren(parent).forEach(childrenModules :: add);
		return childrenModules;
	}
	
	Set<Module> getChildren(Module parent){
		Set<Module> childrenModule = new HashSet<>();
		Set<Integer> children = parent.getChildrenModules();
		for(Integer moduleID: children) {
			Module child = mr.findById(moduleID.intValue());
			childrenModule.add(child);
		}
		return childrenModule;
	}
	
	@Override
	public void setChildToParent(int parentId, int childId) {
		ModuleHierarchy moduleHierarchy = new ModuleHierarchy(parentId,childId);
		moduleHierarchy = mhr.save(moduleHierarchy);
	}
}

