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

	@Override
	public Set<Module> getAllModules() {
		Set<Module> modules = new HashSet<Module>();
		mr.findAll().forEach(modules :: add);
		return modules;
	}

	@Override
	public Module getModuleById(int id) {
		return mr.findById(id).get();
	}

	@Override
	public Module createModule(Module mod) {
		mod = mr.save(mod);
		return mod;
	}

}
