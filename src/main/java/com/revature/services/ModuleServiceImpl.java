package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Module;
import com.revature.entities.ModuleHierarchy;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.util.LogException;

@Service
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	ModuleRepository mr;
	@Autowired
	LinkRepository lr;

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

	/**
	 * Get all the root modules in the database and returns a set
	 * of module objects.
	 */

	public Set<Module> getAllModulesByRoot(){
		Set<Module> modules = new HashSet<>();
		findModuleByNoParent().forEach(modules :: add);
		return modules;
	}

	/**
	 * Find all the root modules in the database by checking if each module have a parent
	 * and returns a set of module objects
	 */
	
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
	
	/**
	 * Get all the children modules in the database and returns a set
	 * of module objects.
	 */

	@Override
	public Set<Module> getChildrenByModuleId(int id){
		Module parent = mr.findById(id);
		Set<Module> childrenModules = new HashSet<>();
		getChildren(parent).forEach(childrenModules :: add);
		return childrenModules;
	}

	/**
	 * Get all the children modules in the database from a particular parent and returns a set
	 * of module objects.
	 */
	
	Set<Module> getChildren(Module parent){
		Set<Module> childrenModule = new HashSet<>();
		Set<ModuleHierarchy> children = parent.getChildrenModules();
		for(ModuleHierarchy specModuleHierarchy: children) {
			Module child = mr.findById(specModuleHierarchy.getmChild());
			childrenModule.add(child);
		}
		return childrenModule;
	}
//	@Override
//    @LogException
//    public Set<Module> getChildrenByModuleId(int id) {
//        Module parent = mr.findById(id);
//        Set<Module> childModule = new HashSet<>();
//        Set<ModuleHierarchy> children = parent.getChildrenModules();
//        for(ModuleHierarchy specModuleHierarchy: children){
//            childModule.add(specModuleHierarchy.getmChild());
//        }
//        return childModule;
//    }
}

