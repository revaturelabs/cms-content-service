package com.revature.servicestests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.HashSet;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.repositories.ModuleRepository;
import com.revature.services.ContentService;
import com.revature.services.ModuleService;
import com.revature.services.SearchService;

/**
 * Simple test for the ModuleService functionality.
 * 
 * @author wsm
 * @version 2.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@SpringBootTest
class ModuleServiceTest {

	@Autowired
	ModuleRepository mr;
	
	@Autowired
	ContentService cs;

	@Autowired
	ModuleService ms;

	@Autowired
	SearchService ss;

	@Autowired
	JdbcTemplate template;

  /**
   * Test for getAllModules.
   */
  @Test
  @Rollback
  void moduleServiceAllTest()
  {
		Module m = ms.createModule(new Module(0, "FIRST TEST MODULE", 0, new HashSet<Link>()));

		boolean allContains = ms.getAllModules().contains(m);
		
		mr.delete(m);
		
		assertTrue(allContains);
  }

  /**
   * Test for getModuleById.
   */
  @Test
  @Rollback
  void moduleServiceIdTest()
  {
		Module m = ms.createModule(new Module(0, "FIRST TEST MODULE", 0, new HashSet<Link>()));

		Module dup = ms.getModuleById(m.getId());
		
		boolean idEquals = m.equals(dup);
		
		mr.delete(m);
		
		assertTrue(idEquals);
  }
}
