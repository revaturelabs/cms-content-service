package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.util.LogException;

@Service
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	ModuleRepository mr;
	@Autowired
	LinkRepository lr;
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
	
	@Override
	public void deleteModuleWithAllContent(Module module) {
		Set<Link> moduleList = module.getLinks();
		for(Link specLink:moduleList) {
			int contentId = specLink.getContentId();
			cr.deleteById(contentId);
		}
		mr.delete(module);
	}
	
	@Override
	public void deleteModuleWithSpecificContent(Module module) {
		Set<Link> moduleList = module.getLinks();
		for(Link specLink:moduleList) {
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
	}
}

