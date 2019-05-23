package com.revature.services;

import java.util.List;

import com.revature.entities.Module;

public interface ModuleService {

	public List<Module> getAllModules();
	
	public Module getModuleById(int id);
}
