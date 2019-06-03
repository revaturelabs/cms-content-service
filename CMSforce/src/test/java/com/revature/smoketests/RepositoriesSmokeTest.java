package com.revature.smoketests;

import org.junit.jupiter.api.Assertions;

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
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleRepository;

import org.junit.jupiter.api.MethodOrderer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class RepositoriesSmokeTest {

	@Autowired
	ModuleRepository mr;

	@Autowired
	ContentRepository cr;

	@Autowired
	LinkRepository lr;
	
	@Autowired
	JdbcTemplate template;
	
	int rows;
	
	
	@Test
	@Commit
	@Order(1)
	void sampleWalkthrough() {
		rows = JdbcTestUtils.countRowsInTable(template, "link");
		
		Module module1 = new Module(1, "flaming", 0, null);
		mr.save(module1);
		
		Module module2 = new Module(2, "elmo", 0, null);
		mr.save(module2);
	
		Content content = new Content(5, "Flaming Elmo Hello World", "code", "Hello World written in Elmo++", "www.elmo.test", null);
		cr.save(content);
	
		module1 = mr.findBysubject("flaming").iterator().next();
	    module2 = mr.findBysubject("elmo").iterator().next();
		content = cr.findByTitle("Flaming Elmo Hello World").iterator().next();
		Link link1 = new Link(0, content.getId(), module1.getId(), "RelevantTo");
		Link link2 = new Link(0, content.getId(), module2.getId(), "RelevantTo");

		link1 = lr.save(link1);
		link2 = lr.save(link2);
		
	}
	
	@Test	
	@Rollback
	@Order(2)
	public void recordNumberVerification() {
		
		int contentnum = JdbcTestUtils.countRowsInTableWhere(template, "content", "title = 'Flaming Elmo Hello World'");
		int modulenum = JdbcTestUtils.countRowsInTableWhere(template, "module", "subject = 'flaming' or subject = 'elmo'");
		
		Assertions.assertEquals(1, contentnum, "Content should be 1");
		Assertions.assertEquals(2, modulenum, "module should be 2");
		Assertions.assertEquals(rows + 2, JdbcTestUtils.countRowsInTable(template, "link"));
		
	}
	
	@Test
	@Commit
	@Order(3)
	void deleteTestData() {
		JdbcTestUtils.deleteFromTableWhere(template, "module", "subject = 'flaming'");
		JdbcTestUtils.deleteFromTableWhere(template, "module", "subject = 'elmo'");
		JdbcTestUtils.deleteFromTableWhere(template, "content", "title = 'Flaming Elmo Hello World'");
	}
	
	
	
}
