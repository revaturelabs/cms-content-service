package com.revature.services;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import com.revature.entities.Module;

public interface ModuleService {

	public Set<Module> getAllModules();
	
	public Set<Module> getAllRootModules();

	public Module getModuleById(int id);

	public Module createModule(Module module);

	public double getAverageByModuleIds(Set<Module> modules);

	public double getAverageByAllModules();

	public void deleteModule(Module module);
	
	public Set<Module> getAllModulesByRoot();
	
	public Set<Module> getChildrenByModuleId(int id);
	
	public void deleteModuleWithAllContent(Module module);

	public void deleteModuleWithSpecificContent(Module module);

	public Module updateModule(Module module);
}
