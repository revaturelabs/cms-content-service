package com.revature.services;

import java.util.Set;

import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;

public interface ModuleService {
	
	public Module createModule(Module module);

	public Set<Module> getAllModules();
	
	public Module getModuleById(int id);

	public double getAverageByModules(Set<Module> modules);

	public double getAverageByAllModules();
	
	public Set<Module> getAllRootModules();
	
	public Set<Module> getChildrenByParentId(int id);
	
	public Module updateModule(Module module);
	
<<<<<<< HEAD
=======

>>>>>>> b36202c5487cc2497d9edf324889484cc1096f04
	public void deleteModule(Module module);
	
	public void deleteModuleWithAllContent(Module module);

	public void deleteModuleWithSpecificContent(Module module);

	public Set<Link> getLinksByModuleId(int id);

	public Set<ReqLink> getRequestLinksByModuleId(int id);
<<<<<<< HEAD
=======
	
	public Set<Link> updateLinksByModuleId(int id, Set<Link> links );

>>>>>>> b36202c5487cc2497d9edf324889484cc1096f04

}
