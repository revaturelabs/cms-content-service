package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.entities.Content;
import com.revature.entities.Module;

class SearchServiceTest {

//	@Autowire
//	SearchService ss; = new SearchServiceImpl();
	
//	@Autowired
//	ContentService cs = new ContentServiceimpl();
	
// 	@Autowired
//	ModuleService ms = new ModuleServiceImpl();
	
	@Test
//	@Order(9)
	void testFilterContentByTitle() {
		ArrayList<Content> lst = cs.getAllContent();
		String title = lst[0].getTitle();
		assertNotNull(ss.filterContentByTitle(title));
	}

	@Test
//	@Order(10)
	void testFilterContentByFormat() {
		ArrayList<Content> lst = cs.getAllContent();
		String format = lst[0].getFormat();
		assertNotNull(ss.filterContentByTitle(format));
	}

	@Test
//	@Order(11)
	void testFilterContentBySubjects() {
		ArrayList<Module> lst = ms.getAllContent();
		String subject = lst[0].getSubject();
		assertNull(ss.filterContentByTitle(subject));
	}

}
