package com.revature.repositorytests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
@SpringBootTest
class SimpleRepositoryTests {

	@Autowired
	ModuleRepository mr;

	@Autowired
	ContentRepository cr;

	@Autowired
	LinkRepository lr;
	
//	@Test
//	@Order(1)
//	void clearDatabase() {
//		lr.deleteAll();
//		mr.deleteAll();
//		cr.deleteAll();
//		assertTrue(true);
//	}
	
	@Test
	@Order(2)
	void createModules() {
		
		Module module1 = new Module(1, "flaming", 0, null);
		mr.save(module1);
		
		Module module2 = new Module(2, "elmo", 0, null);
		mr.save(module2);
	}
	
	@Test
	@Order(3)
	void createContent() {
		Content content = new Content(5, "Flaming Elmo", "code", "test desc", "www.google.com", null);
		cr.save(content);
	}
	
	@Test
	@Order(4)
	void createLinks() {
		Module module1 = mr.findBysubject("flaming").iterator().next();
		Module module2 = mr.findBysubject("elmo").iterator().next();
		Content content = cr.findByTitle("Flaming Elmo").iterator().next();
		Link link1 = new Link(0, content.getId(), module1.getId(), "RelevantTo");
		Link link2 = new Link(0, content.getId(), module2.getId(), "RelevantTo");

		lr.save(link1);
		lr.save(link2);
	}
}
