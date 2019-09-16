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
		module = mr.save(module);
		return module;
	}
	
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
	
	@Override
	public Set<Module> getChildrenByParentId(int id){
		Module parent = mr.findById(id);
		return parent.getChildModules();
	}
	
	@Override
	public Set<Module> getAllRootModules() {
		Set<Module> modules = this.getAllModules();
		Set<Module> roots = new HashSet<Module>();
		for (Module module : modules) {
			if (module.getParentModule() == null) {
				roots.add(module);
			}
		}
		return roots;
	}	
	
	@Override
	public Module updateModule(Module module) {
		return mr.save(module);
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
	public void deleteModuleWithAllContent(Module module) {
		//delete all content associated with given module
		Set<Content> mContent = module.getContent();
		for (Content content : mContent) {
			cr.delete(content);
		}
		//transfer children of module to parent of module
		Set<Module> childModules = module.getChildModules();
		Module newParent = module.getParentModule();
		for (Module child : childModules) {
			child.setParentModule(newParent);
			mr.save(child);
		}
		//delete module
		mr.delete(module);
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
		//transfer children of module to parent of module
		Set<Module> childModules = module.getChildModules();
		Module newParent = module.getParentModule();
		for (Module child : childModules) {
			child.setParentModule(newParent);
		}
		//delete module
		mr.delete(module);
	}

}

