package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Content;
import com.revature.entities.Module;

@Service
class ModuleServiceTest {

	@Autowired
	ModuleService ms;
	
	@Test
	void testGetAllModules() {
		assertNotNull(ms.getAllModules());
	}

	@Test
	void testGetModuleById() {				
		Set<Module> lst = ms.getAllModules();
		Iterator<Module> iter = lst.iterator();
		Module first = iter.next();
		
		int id = first.getId();
		assertNotNull(ms.getModuleById(id));
	}

}
