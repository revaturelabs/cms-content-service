package com.revature.servicestests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.entities.Content;
import com.revature.entities.Module;
import com.revature.services.ContentService;
import com.revature.services.ModuleService;
import com.revature.services.SearchService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=com.revature.cmsforce.CMSforceApplication.class)
@SpringBootTest
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
	@Test
	@Transactional
	void testgetContentByModuleId() {
		Set<Module> lst = ms.getAllModules();
		Iterator<Module> iter = lst.iterator();
		Module first = iter.next();
		
		int id = first.getId();
		assertNotNull(ss.getContentByModuleId(id));
	}

}
