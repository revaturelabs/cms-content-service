package com.revature.services;

import java.util.Set;

import com.revature.entities.Module;

public interface ModuleService {

	public Iterable<Module> getAllModules();
	
	public Module getModuleById(int id);
	
	public Module createModule(Module module);
	
}
