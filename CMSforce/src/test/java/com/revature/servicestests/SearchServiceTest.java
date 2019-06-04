package com.revature.servicestests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import java.util.Set;

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
import com.revature.entities.Module;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleRepository;
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
 	
	@Autowired
	JdbcTemplate template;
	
	// could use services
	@Autowired
	ModuleRepository mr;

	@Autowired
	ContentRepository cr;

	@Autowired
	LinkRepository lr;
	
	
	// using repositories for now for creation
	@Test
	@Commit
	@Order(1)
	void addTestData() {
		
		Module module1 = new Module(1, "FIRST TEST MODULE", 0, null);
		
		mr.save(module1);
		
		Module module2 = new Module(2, "SECOND TEST MODULE", 0, null);
		mr.save(module2);
		
		Content content = new Content(5, "FIRST TEST CONTENT", "code", "FIRST TEST CONTENT DESCRIPTION", "www.elmo.test", null);
		cr.save(content);
	
		
		module1 = mr.findBysubject("FIRST TEST MODULE").iterator().next();
	    module2 = mr.findBysubject("SECOND TEST MODULE").iterator().next();
		content = cr.findByTitle("FIRST TEST CONTENT").iterator().next();
		
		Link link1 = new Link(0, content.getId(), module1.getId(), "RelevantTo");
		
		Link link2 = new Link(0, content.getId(), module2.getId(), "RelevantTo");
		
		link1 = lr.save(link1);
		
		link2 = lr.save(link2);
		
	}
	
	@Test
	@Commit
	@Order(2)
	void testFilterContentByTitle() {
		Set<Content> lst = cs.getAllContent();
		
		Iterator<Content> iter = lst.iterator();
		Content first = iter.next();
		
		String title = first.getTitle();
		assertNotNull(ss.filterContentByTitle(title));
		System.out.println(title);
		
		int rows = JdbcTestUtils.countRowsInTableWhere(template, "content", String.format("title = '%s'", title));
		
		assertEquals(rows, ss.filterContentByTitle(title).size());
		assertEquals(0, ss.filterContentByTitle("NON EXISTENT TITLE").size());
	}

	@Test
	@Commit
	@Order(3)
	void testFilterContentByFormat() {
		System.out.println("Number of records in Module "+JdbcTestUtils.countRowsInTable(template, "module"));
		Set<Content> lst = cs.getAllContent();
		Iterator<Content> iter = lst.iterator();
		Content first = iter.next();
		
		String format = first.getFormat();
		
		int rows = JdbcTestUtils.countRowsInTableWhere(template, "content", String.format("format = '%s'", format));
		assertEquals(rows, ss.filterContentByFormat(format).size());
		assertEquals(0, ss.filterContentByFormat("NON EXISTENT FORMAT").size());
	}	

	@Test
	@Commit
	@Order(4)
	void testFilterContentBySubjects() {
		Set<Module> lst = ms.getAllModules();
		Iterator<Module> iter = lst.iterator();		
		
		int flamingModId = -1;
		int elmoModId = -1;
		while(iter.hasNext()) {			
			Module current = iter.next();
			if(current.getSubject().equals("FIRST TEST MODULE")) {
				flamingModId = current.getId();
			}
			if(current.getSubject().equals("SECOND TEST MODULE")) {
				elmoModId = current.getId();
			}				
		}
		
		List<Integer> modIdArray = new ArrayList<Integer>();
		
		// to test filter content with only 1 subject
		modIdArray.add(flamingModId);
		int rows = JdbcTestUtils.countRowsInTableWhere(template, "link", String.format("fk_m = '%d'", flamingModId));
		assertEquals(rows, ss.filterContentBySubjects(modIdArray).size());
		
		// to test filter content with more than one subject
		modIdArray.add(elmoModId);
		assertEquals(1, ss.filterContentBySubjects(modIdArray).size());
		
		// tests filter content by Subject with non-existent subject
		List<Integer> modIdArray2 = new ArrayList<Integer>();
		modIdArray2.add(-1);
		assertEquals(0, ss.filterContentBySubjects(modIdArray2).size());
	}

	@Test
	@Commit
	@Order(5)
	void testGetContentByModuleId() {
		Set<Module> allModules= ms.getAllModules();
		Iterator<Module> iter = allModules.iterator();
		Module first = iter.next();
		int MID = first.getId();
				
		assertThrows(NoSuchElementException.class, ()->{ss.getContentByModuleId(-1);});
		assertNotNull(ss.getContentByModuleId(MID));
	}
	
	@Test
	@Commit
	@Order(6)
	void deleteTestData() {
		JdbcTestUtils.deleteFromTableWhere(template, "module", "subject = 'FIRST TEST MODULE'");
		JdbcTestUtils.deleteFromTableWhere(template, "module", "subject = 'SECOND TEST MODULE'");
		JdbcTestUtils.deleteFromTableWhere(template, "content", "title = 'FIRST TEST CONTENT'");
	}
	
}
