package com.revature.servicestests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.Iterator;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.revature.entities.Content;
import com.revature.services.ContentService;
import com.revature.services.SearchService;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=com.revature.cmsforce.CMSforceApplication.class)
@SpringBootTest
class ContentServiceTest {

	@Autowired
	ContentService cs;
	
	@Autowired
	SearchService ss;
		
	
	@Test
	@Order(1)
	void testCreateContent() {
		assertNotNull(cs.createContent(new Content(0, "AngularServices", "Code", "Teaching service injection", "http://localhost:4200/file.txt")));
		assertNotNull(cs.createContent(new Content(0, "SpringDATA Example", "Document", "Configuring PersistenceConfig", "http://localhost:4200/JPAconfig.java")));
		assertNull(cs.createContent(new Content(0, null, "Code", "Teaching service injection", "http://localhost:4200/file.txt")));
		assertNull(cs.createContent(new Content(0, "AngularServices", null, "Teaching service injection", "http://localhost:4200/file.txt")));
		assertNull(cs.createContent(new Content(0, "AngularServices", "Code", null, "http://localhost:4200/file.txt")));
		assertNull(cs.createContent(new Content(0, "AngularServices", "Code", "Teaching service injection", null)));
	}

	@Test
	@Order(2)
	void testGetAllContent() {
		assertNotNull(cs.getAllContent());
		assertFalse(cs.getAllContent().isEmpty());
	}
	
	@Test
	@Order(3)
	void testGetAllContentMultipleItems() {
		int size = cs.getAllContent().size();
		assertTrue((size>1));
	}

	@Test
	@Order(4)
	void testGetContentById() {
		Set<Content> allContents = cs.getAllContent();
		Iterator<Content> iter = allContents.iterator();
		Content first = iter.next();
		int id = first.getId();
		assertNotNull(cs.getContentById(id));
	}

	@Test
	@Order(5)
	void testUpdateContent() {
		Set<Content> allContents = cs.getAllContent();		
		Iterator<Content> iter = allContents.iterator();
		Content first = iter.next();
		int id = first.getId();
		assertNotNull(cs.updateContent(new Content(id, "Updated Title", "Code", "Updated Description", "Updated URL")));
	}

	@Test
	@Order(6)
	void testAddContentModules() {
		Set<Content> allContents = cs.getAllContent();
		
		Iterator<Content> iter = allContents.iterator();
		Content first = iter.next();		
		
		assertNotNull(cs.addContentModules(first, new String[]{"JavaScript"}));
	}

	@Test
	@Order(7)
	void testRemoveContentModules() {
		Set<Content> allContents = ss.filterContentBySubjects(new String[]{"JavaScript"});
		
		Iterator<Content> iter = allContents.iterator();
		Content first = iter.next();		
		
		System.out.println(allContents.size());
		System.out.println(first);
		
		assertNotNull(cs.removeContentModules(first, new String[]{"JavaScript"}));
		assertNull(cs.removeContentModules(first, new String[]{"JavaScript"}));
	}

	@Test
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
	void passes() {
		assertTrue(1==1);
	}

}