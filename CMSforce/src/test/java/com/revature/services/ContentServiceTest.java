package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.entities.Content;

class ContentServiceTest {

//	@Autowired
//	ContentService cs = new ContentServiceimpl();
	
	@Test
	@Order(1)
	void testCreateContent() {
		assertTrue(cs.createContent(new Content(0, "AngularServices", "Code", "Teaching service injection", "http://localhost:4200/file.txt")));
	}
	
	@Test
	@Order(2)
	void testCreateContentNull() {
		assertFalse(cs.createContent(new Content(0, null, null, null, null)));
	}
	
	

	@Test
	void testGetAllContent() {
		fail("Not yet implemented");
	}

	@Test
	void testGetContentById() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateContent() {
		fail("Not yet implemented");
	}

	@Test
	void testAddContentTags() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveContentTags() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteContent() {
		fail("Not yet implemented");
	}

}
