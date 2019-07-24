package com.revature.servicestests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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

/**
 * Overarching test for SearchService.
 * 
 * @author wsm
 * @version 2.0
 */
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

	/**
	 * Handles an elaborate test for all the notable filtering methods.
	 */
	@Test
	@Rollback
	void searchServiceTest()
	{
		//Create
		Module module1 = new Module(0, "FIRST TEST MODULE", 0, null);
		Module module2 = new Module(0, "SECOND TEST MODULE", 0, null);
		Module module3 = new Module(0, "THIRD TEST MODULE", 0, null);
		
		Content content = new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION", "http://www.elmo.test", new HashSet<Link>(), 1563378565, 1563378565);

		module1 =mr.save(module1);
		module2 =mr.save(module2);
		module3 = mr.save(module3);
		content = cr.save(content);
		
		Link link1 = new Link(0, content.getId(), module1.getId(), "RelevantTo");
		Link link2 = new Link(0, content.getId(), module2.getId(), "RelevantTo");
		
		Set<Link> contentLinks = new HashSet<Link>();
		contentLinks.add(link1);
		contentLinks.add(link2);
		
		content.setLinks(contentLinks);
		
		content = cr.save(content);
		
		//Actual testing.		
		String title = content.getTitle();
		String format = content.getFormat();
		boolean titleTest = ss.filterContentByTitle(title).contains(content);
		boolean formatTest = ss.filterContentByFormat(format).contains(content);
		List<Integer> mlist = new ArrayList<Integer>();
		mlist.add(module1.getId());
		mlist.add(module2.getId());
		boolean subjectTestContains = ss.filterContentBySubjects(mlist).contains(content);
		boolean moduleIdTestContains = ss.getContentByModuleId(module1.getId()).contains(content);
		
		//Testing for the overarching Filter method
		String badtitle = "inaccurate title";
		String badformat = "Document";
		
		//Valid testing.
		Set<Content> filtered = ss.filter(title, format, mlist);
		boolean validFilter = filtered.contains(content);
		
		filtered = ss.filter(badtitle, format, mlist);
		boolean badTitleFilter = filtered.contains(content);
		
		filtered = ss.filter(title, badformat, mlist);
		boolean badFormatFilter = filtered.contains(content);
		
		mlist.clear();
		mlist.add(module3.getId());
		filtered = ss.filter(title, format, mlist);
		boolean badModuleFilter = filtered.contains(content);
		
		
		//Cleanup courtesy of @Transactional and @Rollback.
		

		assertTrue(titleTest);
		assertTrue(formatTest);
		assertTrue(subjectTestContains);
		assertTrue(moduleIdTestContains);
		
		assertTrue(validFilter);
		assertFalse(badTitleFilter);
		assertFalse(badFormatFilter);
		assertFalse(badModuleFilter);
	}
	
}
