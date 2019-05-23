package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.revature.entities.Module;

class ModuleServiceTest {

//	@Autowire
//	ModuleService ms = new ModuleServiceImpl();
	
	@Test
	void testGetAllModules() {
		assertNotNull(ms.getAllModules());
	}

	@Test
	void testGetModuleById() {
		ArrayList<Module> lst = ms.getAllContent();
		int id = lst[0].getId();
		assertNotNull(ms.getModuleById(id));
	}

}
