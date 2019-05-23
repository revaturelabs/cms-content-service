package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Content;
import com.revature.entities.Module;

@Service
class SearchServiceTest {

	@Autowired
	SearchService ss;
	
	@Autowired
	ContentService cs;
	
 	@Autowired
	ModuleService ms;
	
	@Test
//	@Order(9)
	void testFilterContentByTitle() {
		Set<Content> lst = cs.getAllContent();
		
		Iterator<Content> iter = lst.iterator();
		Content first = iter.next();
		
		String title = first.getTitle();
		assertNotNull(ss.filterContentByTitle(title));
	}

	@Test
//	@Order(10)
	void testFilterContentByFormat() {
		Set<Content> lst = cs.getAllContent();
		Iterator<Content> iter = lst.iterator();
		Content first = iter.next();
		
		String format = first.getFormat();
		assertNotNull(ss.filterContentByTitle(format));
	}

	@Test
//	@Order(11)
	void testFilterContentBySubjects() {
		Set<Module> lst = ms.getAllModules();
		Iterator<Module> iter = lst.iterator();
		Module first = iter.next();
		
		String subject = first.getSubject();
		assertNotNull(ss.filterContentByTitle(subject));
	}

}
