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
@ContextConfiguration(classes=com.revature.cmsforce.CMSforceApplication.class)
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
		
	
	@Test
	@Commit
	@Order(1)
	void testCreateContent() {
		assertNotNull(cs.createContent(new Content(0, "Art Expressionism", "Code", "MOCK DATA", "http://localhost:4200/file.txt", new HashSet<Link>())));
//		assertNotNull(cs.createContent(new Content(0, "Futurism", "Document", "MOCK DATA", "http://localhost:4200/JPAconfig.java", null)));
//		assertNull(cs.createContent(new Content(0, null, "Code", "MOCK DATA", "http://localhost:4200/file.txt", new HashSet<Link>())));
//		assertNull(cs.createContent(new Content(0, "Philosophy", null, "MOCK DATA", "http://localhost:4200/file.txt", new HashSet<Link>())));
//		assertNull(cs.createContent(new Content(0, "Tropical Fish Anatomy", "Code", null, "http://localhost:4200/file.txt", new HashSet<Link>())));
//		assertNull(cs.createContent(new Content(0, "Cubism", "Code", "MOCK DATA", null, new HashSet<Link>())));
//		System.out.println(JdbcTestUtils.countRowsInTable(template, "content"));
//		assertEquals(3, JdbcTestUtils.countRowsInTable(template, "content"));		
	}

	@Test
	@Commit
	@Order(2)
	void testGetAllContent() {
		assertNotNull(cs.getAllContent());
		assertFalse(cs.getAllContent().isEmpty());
	}
	
	@Test
	@Commit
	@Order(3)
	void testGetAllContentMultipleItems() {
		int size = cs.getAllContent().size();
		assertTrue((size>1));
	}

	@Test
	@Commit
	@Order(4)
	void testGetContentById() {
		Set<Content> allContents = cs.getAllContent();
		Iterator<Content> iter = allContents.iterator();
		Content first = iter.next();
		int id = first.getId();
		assertNotNull(cs.getContentById(id));
	}

	@Test
	@Commit
	@Order(5)
	void testUpdateContent() {
		Set<Content> allContents = cs.getAllContent();		
		Iterator<Content> iter = allContents.iterator();
		Content first = iter.next();
		int id = first.getId();
		assertNotNull(cs.updateContent(new Content(id, "Updated Title", "Code", "Updated Description", "Updated URL", null)));
	}

	@Test
	@Commit
	@Order(6)
	void testAddLinks() {
		Set<Content> allContents = cs.getAllContent();
		
		Iterator<Content> iter = allContents.iterator();
		Content first = iter.next();		
		
		assertNotNull(cs.addLinks(first, new String[]{"JavaScript"}));
	}

	@Test
	@Order(7)
	// FAILING, FIX
	void testRemoveLinks() {
		Set<Content> allContents = ss.filterContentBySubjects(new String[]{"JavaScript"});
		
		Iterator<Content> iter = allContents.iterator();
		Content first = iter.next();		
		
		System.out.println(allContents.size());
		System.out.println(first);
		
		assertNotNull(cs.removeLinks(first, new String[]{"JavaScript"}));
		assertNull(cs.removeLinks(first, new String[]{"JavaScript"}));
	}

	@Test
	@Commit
	@Order(8)
	void testDeleteContent() {
		Set<Content> allContents = cs.getAllContent();
		
		Iterator<Content> iter = allContents.iterator();
		Content first = iter.next();
		int id = first.getId();
		
		cs.deleteContent(id);
		assertNull(cs.getContentById(id));
	}
	
	@Test
	@Commit
	void passes() {
		assertTrue(1==1);
	}

}