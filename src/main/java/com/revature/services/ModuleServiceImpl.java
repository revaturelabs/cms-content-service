package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Content;
import com.revature.entities.Module;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.util.LogException;

@Service
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	ModuleRepository mr;
	/*
	@Autowired
	LinkRepository lr;
	@Autowired
	ModuleHierarchyRepository mhr;
	*/
	@Autowired
	ContentRepository cr;

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
		if(module.getChildModules() == null){
			module.setChildModules(Collections.emptySet());
		}
		/*
		if(module.getParentModules() == null){
			module.setParentModules(Collections.emptySet());
		}
		*/
		System.out.println("Module to be saved: " + module);
		module = mr.save(module);
		System.out.println("Module has been saved");
		return module;
	}

	
	/**
	 * Calculate the average for all of the module's resources based on the specific inputed modules
	 * */
	@Override
	@LogException
	public double getAverageByModuleIds(ArrayList<Integer> ids) {
		// int size = lr.findByModuleIdIn(ids).size();
		
		// return (double) size / (double) ids.size();
		return 0.0;
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
	
	public Set<Module> findModuleByNoParent(){
		/*
		Set<Module> modules = getAllModules();
		Set<Module> finModules = new HashSet<>();
		for(Module specModule: modules) {
			if(specModule.getParentModules().size() == 0) {
				finModules.add(specModule);
			}
		}
		return finModules;
		*/
		return null;
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
		Set<Module> children = parent.getChildModules();
		for(Module module: children) {
			Module child = mr.findById(module.getId());
			childrenModule.add(child);
		}
		return childrenModule;
	}
	
	@Override
	public void deleteModuleWithAllContent(Module module) {
		/*
		Set<ContentPlusModules> moduleList = module.getLinks();
		for(ContentPlusModules specLink:moduleList) {
			int contentId = specLink.getContentId();
			cr.deleteById(contentId);
		}
		mr.delete(module);
		*/
	}
	
	@Override
	public void deleteModuleWithSpecificContent(Module module) {
		// Set<ContentPlusModules> moduleList = module.getLinks();
		/*
		for(ContentPlusModules specLink:moduleList) {
			int contentId = specLink.getContentId();
			Set<Content> contentList = cr.findById(contentId);
			for(Content specCon:contentList) {
				if(specCon.getLinks().size() == 1) {
					cr.deleteById(specCon.getId());
				}
				specCon.getLinks().remove(specLink);
			}
		}
		mr.delete(module);
		*/
	}

	@Override
	public Module updateModule(Module module) {
		mr.save(module);
		return module;
		
	}
}

