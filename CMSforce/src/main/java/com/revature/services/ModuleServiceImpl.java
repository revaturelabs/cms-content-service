package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Module;
import com.revature.repositories.ModuleRepository;
import com.revature.util.LogException;

@Service
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	ModuleRepository mr;

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
		
		ArrayList<Module> i =  (ArrayList<Module>) mr.findAll();
		for(Module r:i) {
		
		if(module.getSubject().equalsIgnoreCase(r.getSubject())) {
			throw new EqualModuleSubjectException("This subject has already been created. Subjects are not case sensitive");
			
		}
		}
		module.setCreated(System.currentTimeMillis());
		module = mr.save(module);
		return module;
	}

}
class EqualModuleSubjectException extends RuntimeException{
	public EqualModuleSubjectException(String e) {
		
		
	}
	
}
