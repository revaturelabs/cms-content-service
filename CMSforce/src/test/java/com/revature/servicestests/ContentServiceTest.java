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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.services.ContentService;
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

}