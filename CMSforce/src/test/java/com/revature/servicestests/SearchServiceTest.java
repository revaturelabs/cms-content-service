package com.revature.servicestests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Content;
import com.revature.entities.Module;
import com.revature.services.ContentService;
import com.revature.services.ModuleService;
import com.revature.services.SearchService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=com.revature.cmsforce.CMSforceApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@SpringBootTest
class SearchServiceTest {

	@Autowired
	SearchService ss;
	
	@Autowired
	ContentService cs;
	
 	@Autowired
	ModuleService ms;
	
	@Test
	void testFilterContentByTitle() {
		Set<Content> lst = cs.getAllContent();
		
		Iterator<Content> iter = lst.iterator();
		Content first = iter.next();
		
		String title = first.getTitle();
		assertNotNull(ss.filterContentByTitle(title));
		
		assertEquals(1, ss.filterContentByTitle(title).size());
		assertEquals(0, ss.filterContentByTitle("NON EXISTENT TITLE").size());
	}

	@Test
	void testFilterContentByFormat() {
		Set<Content> lst = cs.getAllContent();
		Iterator<Content> iter = lst.iterator();
		Content first = iter.next();
		
		String format = first.getFormat();
		assertEquals(1, ss.filterContentByFormat(format).size());
		assertEquals(0, ss.filterContentByFormat("NON EXISTENT FORMAT").size());
	}

	@Test
	void testFilterContentBySubjects() {
		Set<Module> lst = ms.getAllModules();
		Iterator<Module> iter = lst.iterator();
		Module first = iter.next();
		
		int modId = first.getLinks().iterator().next().getModuleId();
		List<Integer> modIdArray = new ArrayList();
		modIdArray.add(modId);
		
		List<Integer> modIdArray2 = new ArrayList();
		modIdArray2.add(-1);
		
		// to test filter content with only 1 subject
		assertEquals(1, ss.filterContentBySubjects(modIdArray).size());
		modId = first.getLinks().iterator().next().getModuleId();
		modIdArray.add(modId);
		
		// to test filter content with more than one subject
		assertEquals(1, ss.filterContentBySubjects(modIdArray).size());
		
		// tests filter content by Subject with non-existent subject
		assertEquals(0, ss.filterContentBySubjects(modIdArray2).size());
	}

	@Test
	void testGetContentByModuleId() {
		Set<Module> allModules= ms.getAllModules();
		Iterator<Module> iter = allModules.iterator();
		Module first = iter.next();
		int MID = first.getId();
				
		assertThrows(NoSuchElementException.class, ()->{ss.getContentByModuleId(-1);});
		assertNotNull(ss.getContentByModuleId(MID));
	}
}
