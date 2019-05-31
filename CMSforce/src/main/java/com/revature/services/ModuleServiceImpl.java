package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Module;
import com.revature.repositories.ModuleRepository;

@Service
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	ModuleRepository mr;

	/**
	 * Get all the modules in the database and returns a set
	 * of module objects.
	 */
	@Override
	public Set<Module> getAllModules() {
		Set<Module> modules = new HashSet<Module>();
		mr.findAll().forEach(modules :: add);
		return modules;
	}

	/**
	 * Get a module from the database that matches the id passed in\
	 * then returns a module object.
	 */
	@Override
	public Module getModuleById(int id) {
		return mr.findById(id).get();
	}

	/**
	 * Take the module passed in and change the created value 
	 * to the current time then add it to the database.
	 */
	@Override
	public Module createModule(Module module) {
		module.setCreated(System.currentTimeMillis());
		module = mr.save(module);
		return module;
	}

}
