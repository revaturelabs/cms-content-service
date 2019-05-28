package com.revature.servicestests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Content;
import com.revature.entities.Module;
import com.revature.services.ModuleService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=com.revature.cmsforce.CMSforceApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@SpringBootTest
class ModuleServiceTest {

	@Autowired
	ModuleService ms;
	
	@Test
	void testGetAllModules() {
		assertNotNull(ms.getAllModules());
	}

//	@Test
//	void testGetModuleById() {				
//		Set<Module> lst = ms.getAllModules();
//		Iterator<Module> iter = lst.iterator();
//		Module first = iter.next();
//		
//		int id = first.getId();
//		assertNotNull(ms.getModuleById(id));
//	}

}
