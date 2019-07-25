package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Module;
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
	 * Calculate the average for all of the module's resources 
	 * */
	@Override
	@LogException
	public double getAverageByModuleIds(ArrayList<Integer> ids, Set<Module> modules) {
		
		int contentLinks = 0;
		
		// implement my own thing here-- don't make a database call
		for (Module m : modules)
		{
			System.out.println(m.toString());
			// step 1: check if current module matches array list of passed in module IDs
			for (Integer i : ids)
			{
				if (i == m.getId())
				{
					contentLinks += m.getLinks().size();
				}
			}
		}
		double ret = (double) contentLinks/(double) ids.size();
		
		return ret;
	}

	@Override
	public double getAverageByModuleIds(ArrayList<Integer> ids) {
		int size = lr.findByModuleIdIn(ids).size();
		double ret = (double) size / (double) ids.size();
		return ret;
	}

}

