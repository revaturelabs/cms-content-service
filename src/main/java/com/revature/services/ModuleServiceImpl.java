package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.CurriculumModule;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.CurriculumModuleRepository;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.repositories.ReqLinkRepository;
import com.revature.util.LogException;

@Service
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	ModuleRepository mr;
	@Autowired
	ContentRepository cr;
	@Autowired
	LinkRepository lr;
	@Autowired
	ReqLinkRepository rlr;
	@Autowired 
	CurriculumModuleRepository cmR;

	/**
	 * Take the module passed in and change the created value 
	 * to the current time then add it to the database.
	 */
	@Override
	@LogException
	public Module createModule(Module module) {
		module.setCreated(System.currentTimeMillis());
		return mr.save(module);
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
	public double getAverageByModules(Set<Module> modules) {
		Integer numContent = 0;
		for (Module module : modules) {
			numContent += module.getLinks().size();
		}
		return (double) numContent / (double) modules.size();
	}

	/**
	 * Calculate the average for all of the modules's resources
	 */
	@Override
	public double getAverageByAllModules() {
		Set<Module> allMods = this.getAllModules();		
		return this.getAverageByModules(allMods);
	}
	
	@Override
	public Set<Module> getChildrenByParentId(int id){
		Module parent = mr.findById(id);
		return parent.getChildren();
	}
	
	@Override
	public Set<Module> getAllRootModules() {
		Set<Module> modules = this.getAllModules();
		Set<Module> roots = new HashSet<Module>();
		for (Module module : modules) {
			if (module.getParents().size() <= 0) {
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
		
		Set<Link> links = module.getLinks();
		for (Link link : links) {
			lr.delete(link);
		}
		//delete module
		mr.delete(module);
	}
	
	@Override
	public void deleteModuleWithSpecificContent(Module module) {
		//delete all content associated ONLY with given module
		
		Set<Link> links = module.getLinks();
		for (Link link : links) {
			if (link.getContent().getLinks().size() <= 1) {
				lr.delete(link);
			}
		}
		//delete module
		mr.delete(module);
	}

	@Override
	public Set<Link> getLinksByModuleId(int id) {
		return lr.findByModuleId(id);
	}

	@Override
	public Set<ReqLink> getRequestLinksByModuleId(int id) {
		return rlr.findByModule(mr.findById(id));
	}

	@Override
	public  Set<Link> updateLinksByModuleId( Set<Link> links ) {
		for (Link link : links) 
		{
			lr.save(link);
		}
		return links;
		
	}







}

