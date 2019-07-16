package com.revature.servicestests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
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
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.services.ContentService;
import com.revature.services.ModuleService;
import com.revature.services.SearchService;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ContentCreation {
	
	@Autowired
	SearchService ss;
	
	@Autowired
	ContentService cs;
	
 	@Autowired
	ModuleService ms;
 	
	@Autowired
	JdbcTemplate template;
 	
	@Test
	@Commit
	@Order(1)
	void deleteAllfromDatabase() {
		
		JdbcTestUtils.deleteFromTables(template, "content");
		JdbcTestUtils.deleteFromTables(template, "module");
		JdbcTestUtils.deleteFromTables(template, "link");
		
	}
	
 	
	@Test
	@Rollback
	@Order(2)
	// integration test, checks different services at once
	void createContent() {
 		
 		Module module1 = new Module(0, "Element Zero", 0, null);
		module1 = ms.createModule(module1);
		
		Module module2 = new Module(0, "Mass Effect Fields", 0, null);
		module2 = ms.createModule(module2);
		
		Set<Link> links = new HashSet<Link>();
		
		//we will know the module id but not the content id or link id
		Link link1 = new Link(0,0,module1.getId(),"relaventTo");
		Link link2 = new Link(0,0,module2.getId(),"relaventTo");
		
		links.add(link1);
		links.add(link2);
		
		Content content = new Content(0,
				"Mass Effect Theory",
				"Hologram",
				"AI explains Mass Effect Theory",
				"www.mass.test",
				links);
		
		cs.createContent(content);
		
		
	}
	
	
	
	

}
