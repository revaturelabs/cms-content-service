package com.revature.services;

import java.util.Set;

import com.revature.entities.Module;

public interface ModuleService {
	
	public Module createModule(Module module);

	public Set<Module> getAllModules();

	public Module getModuleById(int id);

	public double getAverageByModuleIds(Set<Module> modules);

	public double getAverageByAllModules();
	
	public Set<Module> getAllRootModules();
	
	public Set<Module> getChildrenByParentId(int id);
	
	public Module updateModule(Module module);
	
	public void deleteModule(Module module);
	
	public void deleteModuleWithAllContent(Module module);

	public void deleteModuleWithSpecificContent(Module module);

}
