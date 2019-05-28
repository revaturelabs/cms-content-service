package com.revature.servicestests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.services.ContentService;
import com.revature.services.ModuleService;
import com.revature.services.SearchService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=com.revature.cmsforce.CMSforceApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@SpringBootTest
class ModuleServiceTest {


	@Autowired
	ContentService cs;
	
	@Autowired
	ModuleService ms;
	
	@Autowired
	SearchService ss;
	
	@Autowired
	JdbcTemplate template;
	
	@Test
	@Commit
	@Order(1)
	void deleteAllfromDatabase() {				
		JdbcTestUtils.deleteFromTables(template, "content");
		JdbcTestUtils.deleteFromTables(template, "module");
		JdbcTestUtils.deleteFromTables(template, "content_module");		
	}	
	
	@Test
	@Commit
	@Order(2)
	void createModule() {
		ms.createModule(new Module(0, "Super Random Subject", 0, new HashSet<Link>()));
//		 assertThrows(NullPointerException.class, () -> { cs.createContent(new Content(0, "Futurism", "Document", "MOCK DATA", "http://localhost:4200/JPAconfig.java", null));});
//		cs.createContent(new Content(0, null, "Code", "MOCK DATA", "http://localhost:4200/file.txt", new HashSet<Link>()));
//		cs.createContent(new Content(0, "Philosophy", null, "MOCK DATA", "http://localhost:4200/file.txt", new HashSet<Link>()));
//		cs.createContent(new Content(0, "Tropical Fish Anatomy", "Code", null, "http://localhost:4200/file.txt", new HashSet<Link>()));
//		cs.createContent(new Content(0, "Cubism", "Code", "MOCK DATA", null, new HashSet<Link>()));		
	}
	
	@Test
	@Commit
	@Order(3)
	void testCreateModule() {
		assertEquals(1, JdbcTestUtils.countRowsInTable(template, "module"));
	}

	@Test
	@Commit
	@Order(4)
	void testGetAllModules() {
		assertEquals(ms.getAllModules().size(), JdbcTestUtils.countRowsInTable(template, "module"));
	}
		
	
	@Test
	@Commit
	@Order(5)
	void testGetModuleById() {
		Set<Module> allModules = ms.getAllModules();
		Iterator<Module> iter = allModules.iterator();
		Module first = iter.next();
		int id = first.getId();
		assertThrows(NoSuchElementException.class, ()->{ms.getModuleById(-1);});
		assertNotNull(ms.getModuleById(id));
	}

	
	@Test
	@Commit
	@Order(6)
	void passes() {
		assertTrue(1==1);
	}

}
