package com.revature.services;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
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
		Module module = mr.findById(id);
		return module;
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
		if(module.getParentModule() == null){
			module.setParentModule(null);
		}
		//System.out.println("Module to be saved: " + module);
		module = mr.save(module);
		// System.out.println("Module has been saved");
		return module;
	}

	
	/**
	 * Calculate the average for all of the module's resources based on the specific inputed modules
	 * */
	@Override
	@LogException
	public double getAverageByModuleIds(Set<Module> modules) {
		Integer numContent = 0;
		for (Module module : modules) {
			numContent += module.getContent().size();
		}
		return (double) numContent / (double) modules.size();
	}

	/**
	 * Calculate the average for all of the modules's resources
	 */
	@Override
	public double getAverageByAllModules() {
		Set<Module> allMods = this.getAllModules();
		
		return this.getAverageByModuleIds(allMods);
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
	
	@Override
	public Set<Module> getChildrenByModuleId(int id){
		Module parent = mr.findById(id);
		return parent.getChildModules();
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

		//delete all content associated with given module
		Set<Content> mContent = module.getContent();
		for (Content content : mContent) {
			cr.delete(content);
		}
	}
	
	@Override
	public void deleteModuleWithSpecificContent(Module module) {
		//delete all content associated ONLY with given module
		Set<Content> mContent = module.getContent();
		for (Content content : mContent) {
			if (content.getModules().size() <= 1) {
				cr.delete(content);
			}
		}
	}

	@Override
	public Module updateModule(Module module) {
		mr.save(module);
		return module;
		
	}

	@Override
	public Set<Module> getAllRootModules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Module> getAllModulesByRoot() {
		Set<Module> modules = getAllModules();
		Set<Module> rootModules = new HashSet<>();
		for(Module module: modules) {
			if(module.getParentModule() == null) {
				rootModules.add(module);
			}
		}
		return rootModules;
	}

}

