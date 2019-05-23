package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.entities.Content;

class ContentServiceTest {

//	@Autowired
//	ContentService cs = new ContentServiceimpl();
	
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
		ArrayList<Content> lst = cs.getAllContent();
		int id = lst[0].getId();
		assertNotNull(cs.getContentById(id));
	}

	@Test
	@Order(5)
	void testUpdateContent() {
		ArrayList<Content> lst = cs.getAllContent();
		int id = lst[0].getId();
		assertNotNull(cs.updateContent(new Content(id, "Updated Title", "Code", "Updated Description", "Updated URL")));
	}

	@Test
	@Order(6)
	void testAddContentTags() {
		ArrayList<Content> lst = cs.getAllContent();
		assertNotNull(cs.addContentTags(lst[0], ["Java", "OOP"]));
	}

	@Test
	@Order(7)
	void testRemoveContentTags() {
		ArrayList<Content> lst = cs.getAllContent();
		assertNotNull(cs.removeContentTags(lst[0], ["OOP"]));
		assertNull(cs.removeContentTags(lst[0], ["OOP"]));
	}

	@Test
	@Order(8)
	void testDeleteContent() {
		ArrayList<Content> lst = cs.getAllContent();
		int id = lst[0].getId();
		cs.deleteContent(id);
		assertNotNull(cs.getContentById(id));
	}

}