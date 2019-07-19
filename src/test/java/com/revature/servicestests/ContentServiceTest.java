package com.revature.servicestests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@SpringBootTest
class ContentServiceTest {

	@Autowired
	ContentService cs;

	@Autowired
	SearchService ss;

	@Autowired
	JdbcTemplate template;
	
	@Autowired
	ModuleService ms;

	int rows = 0;

	@Test
	@Commit
	@Order(1)
	void createValidContent() {
		cs.createContent(new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION",
				"http://TESTURL.COM", new HashSet<Link>()));
	}

	@Test
	@Order(2)
	void createInvalidContent() {
		rows = JdbcTestUtils.countRowsInTable(template, "content");
		// cs.createContent(new Content(0, null, "Code", "MOCK DATA",
		// "http://localhost:4200/file.txt", null));
		cs.createContent(
				new Content(0, null, "Code", "MOCK DATA", "http://localhost:4200/file.txt", new HashSet<Link>()));
		cs.createContent(
				new Content(0, "Philosophy", null, "MOCK DATA", "http://localhost:4200/file.txt", new HashSet<Link>()));
		cs.createContent(new Content(0, "Tropical Fish Anatomy", "Code", null, "http://localhost:4200/file.txt",
				new HashSet<Link>()));
		cs.createContent(new Content(0, "Cubism", "Code", "MOCK DATA", null, new HashSet<Link>()));
		assertEquals(rows, JdbcTestUtils.countRowsInTable(template, "content"));
	}

	@Test
	@Commit
	@Order(3)
	void testCreateContent() {
		assertEquals(1, JdbcTestUtils.countRowsInTableWhere(template, "content",
				String.format("title = '%s'", "FIRST TEST CONTENT")));
	}

	@Test
	@Commit
	@Order(4)
	void testGetAllContent() {
		assertEquals(cs.getAllContent().size(), JdbcTestUtils.countRowsInTable(template, "content"));
	}

	@Test
	@Commit
	@Order(5)
	void testGetContentById() {
		Set<Content> allContents = cs.getAllContent();
		Iterator<Content> iter = allContents.iterator();
		Content first = iter.next();
		int id = first.getId();
		assertNotNull(cs.getContentById(id));
	}

	@Test
	@Order(6)
	void testInvalidGetContentById() {
		// to test non existing content
		assertThrows(NullPointerException.class, () -> {
			cs.getContentById(-1);
		});
	}

	@Test
	@Commit
	@Order(7)
	void deleteTestData() {
		JdbcTestUtils.deleteFromTableWhere(template, "content", String.format("title = '%s'", "FIRST TEST CONTENT"));
	}
	
	@Test
	@Rollback
	@Order(8)
	public void updateContent() {
		
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
		
		content = cs.createContent(content);
		
		content.setDescription("RIP Bioware...");
		//System.out.println("Getting: " + content.toString());
		
		assertNotNull(cs.updateContent(content));
		assertEquals(0, content.getDescription().compareTo(cs.getContentById(content.getId()).getDescription()));
	}

}