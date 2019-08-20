package com.revature.servicestests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;

import com.revature.cmsforce.CMSforceApplication;
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
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes=com.revature.cmsforce.CMSforceApplication.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Transactional
@SpringBootTest(classes = CMSforceApplication.class)
class SearchServiceTest {

	@Mock
	JdbcTemplate mockTemplate;
	@Mock
	ModuleRepository moduleRep;
	@Mock
	ContentRepository contRep;
	@Mock
	LinkRepository linkRep;
	@InjectMocks
	SearchService searchServe;
	@InjectMocks
	ContentService contServe;
 	@InjectMocks
	ModuleService moduleServe;
 	
 	@BeforeClass
 	void mockSearchService()
 	{
 		
 	}
	
	
//	@Autowired
//	SearchService ss;
//	
//	@Autowired
//	ContentService cs;
//	
// 	@Autowired
//	ModuleService ms;
// 	
//	@Autowired
//	JdbcTemplate template;
//	
//	// could use services
//	@Autowired
//	ModuleRepository mr;
//
//	@Autowired
//	ContentRepository cr;
//
//	@Autowired
//	LinkRepository lr;
//
//	/**
//	 * Tests filtering via title.
//	 */
//	@Test
//	@Rollback
//	void searchServiceTitleTest()
//	{
//		Module module1 = new Module(0, "FIRST TEST MODULE", 0, null);
//		Module module2 = new Module(0, "SECOND TEST MODULE", 0, null);
//		Module module3 = new Module(0, "THIRD TEST MODULE", 0, null);
//		
//		Content content = new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION", "http://www.elmo.test", new HashSet<Link>(), 1563378565, 1563378565);
//
//		module1 =mr.save(module1);
//		module2 =mr.save(module2);
//		module3 = mr.save(module3);
//		content = cr.save(content);
//		
//		Link link1 = new Link(0, content.getId(), module1.getId(), "RelevantTo");
//		Link link2 = new Link(0, content.getId(), module2.getId(), "RelevantTo");
//		
//		Set<Link> contentLinks = new HashSet<Link>();
//		contentLinks.add(link1);
//		contentLinks.add(link2);
//		
//		content.setLinks(contentLinks);
//		
//		content = cr.save(content);
//		
//		String title = content.getTitle();
//		boolean titleTest = ss.filterContentByTitle(title).contains(content);
//
//		assertTrue(titleTest);
//	}
//	
//	/**
//	 * Tests filtering via format.
//	 */
//	@Test
//	@Rollback
//	void searchServiceFormatTest()
//	{
//		Module module1 = new Module(0, "FIRST TEST MODULE", 0, null);
//		Module module2 = new Module(0, "SECOND TEST MODULE", 0, null);
//		Module module3 = new Module(0, "THIRD TEST MODULE", 0, null);
//		
//		Content content = new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION", "http://www.elmo.test", new HashSet<Link>(), 1563378565, 1563378565);
//
//		module1 =mr.save(module1);
//		module2 =mr.save(module2);
//		module3 = mr.save(module3);
//		content = cr.save(content);
//		
//		Link link1 = new Link(0, content.getId(), module1.getId(), "RelevantTo");
//		Link link2 = new Link(0, content.getId(), module2.getId(), "RelevantTo");
//		
//		Set<Link> contentLinks = new HashSet<Link>();
//		contentLinks.add(link1);
//		contentLinks.add(link2);
//		
//		content.setLinks(contentLinks);
//		
//		content = cr.save(content);
//
//		String format = content.getFormat();
//		boolean formatTest = ss.filterContentByFormat(format).contains(content);
//		assertTrue(formatTest);
//	}
//	
//	/**
//	 * Test filtering by module.
//	 */
//	@Test
//	@Rollback
//	void searchServiceSubjectTest()
//	{
//		Module module1 = new Module(0, "FIRST TEST MODULE", 0, null);
//		Module module2 = new Module(0, "SECOND TEST MODULE", 0, null);
//		Module module3 = new Module(0, "THIRD TEST MODULE", 0, null);
//		
//		Content content = new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION", "http://www.elmo.test", new HashSet<Link>(), 1563378565, 1563378565);
//
//		module1 =mr.save(module1);
//		module2 =mr.save(module2);
//		module3 = mr.save(module3);
//		content = cr.save(content);
//		
//		Link link1 = new Link(0, content.getId(), module1.getId(), "RelevantTo");
//		Link link2 = new Link(0, content.getId(), module2.getId(), "RelevantTo");
//		
//		Set<Link> contentLinks = new HashSet<Link>();
//		contentLinks.add(link1);
//		contentLinks.add(link2);
//		
//		content.setLinks(contentLinks);
//		
//		content = cr.save(content);
//		
//		List<Integer> mlist = new ArrayList<Integer>();
//		mlist.add(module1.getId());
//		mlist.add(module2.getId());
//		boolean subjectTestContains = ss.filterContentBySubjects(mlist).contains(content);
//		assertTrue(subjectTestContains);
//	}
//	
//	/**
//	 * Tests filtering by set of modules.
//	 */
//	@Test
//	@Rollback
//	void searchServiceModuleTest()
//	{
//		Module module1 = new Module(0, "FIRST TEST MODULE", 0, null);
//		Module module2 = new Module(0, "SECOND TEST MODULE", 0, null);
//		Module module3 = new Module(0, "THIRD TEST MODULE", 0, null);
//		
//		Content content = new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION", "http://www.elmo.test", new HashSet<Link>(), 1563378565, 1563378565);
//
//		module1 =mr.save(module1);
//		module2 =mr.save(module2);
//		module3 = mr.save(module3);
//		content = cr.save(content);
//		
//		Link link1 = new Link(0, content.getId(), module1.getId(), "RelevantTo");
//		Link link2 = new Link(0, content.getId(), module2.getId(), "RelevantTo");
//		
//		Set<Link> contentLinks = new HashSet<Link>();
//		contentLinks.add(link1);
//		contentLinks.add(link2);
//		
//		content.setLinks(contentLinks);
//		
//		content = cr.save(content);
//		
//		List<Integer> mlist = new ArrayList<Integer>();
//		mlist.add(module1.getId());
//		mlist.add(module2.getId());
//		boolean moduleIdTestContains = ss.getContentByModuleId(module1.getId()).contains(content);
//		assertTrue(moduleIdTestContains);
//	}
//	
//	/**
//	 * Tests filtering by all measures.
//	 */
//	@Test
//	@Rollback
//	void searchServiceOverallFilter()
//	{
//		Module module1 = new Module(0, "FIRST TEST MODULE", 0, null);
//		Module module2 = new Module(0, "SECOND TEST MODULE", 0, null);
//		Module module3 = new Module(0, "THIRD TEST MODULE", 0, null);
//		
//		Content content = new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION", "http://www.elmo.test", new HashSet<Link>(), 1563378565, 1563378565);
//
//		module1 =mr.save(module1);
//		module2 =mr.save(module2);
//		module3 = mr.save(module3);
//		content = cr.save(content);
//		
//		Link link1 = new Link(0, content.getId(), module1.getId(), "RelevantTo");
//		Link link2 = new Link(0, content.getId(), module2.getId(), "RelevantTo");
//		
//		Set<Link> contentLinks = new HashSet<Link>();
//		contentLinks.add(link1);
//		contentLinks.add(link2);
//		
//		content.setLinks(contentLinks);
//		
//		content = cr.save(content);
//
//		String title = content.getTitle();
//		String format = content.getFormat();
//		List<Integer> mlist = new ArrayList<Integer>();
//		mlist.add(module1.getId());
//		mlist.add(module2.getId());
//
//		Set<Content> filtered = ss.filter(title, format, mlist);
//		boolean validFilter = filtered.contains(content);
//		assertTrue(validFilter);
//	}
//
//	/**
//	 * Tests to confirm the filter removes inaccurate titles.
//	 */
//	@Test
//	@Rollback
//	void searchServiceBadTitle()
//	{
//		Module module1 = new Module(0, "FIRST TEST MODULE", 0, null);
//		Module module2 = new Module(0, "SECOND TEST MODULE", 0, null);
//		Module module3 = new Module(0, "THIRD TEST MODULE", 0, null);
//		
//		Content content = new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION", "http://www.elmo.test", new HashSet<Link>(), 1563378565, 1563378565);
//
//		module1 =mr.save(module1);
//		module2 =mr.save(module2);
//		module3 = mr.save(module3);
//		content = cr.save(content);
//		
//		Link link1 = new Link(0, content.getId(), module1.getId(), "RelevantTo");
//		Link link2 = new Link(0, content.getId(), module2.getId(), "RelevantTo");
//		
//		Set<Link> contentLinks = new HashSet<Link>();
//		contentLinks.add(link1);
//		contentLinks.add(link2);
//		
//		content.setLinks(contentLinks);
//		
//		content = cr.save(content);
//
//		String title = content.getTitle();
//		String format = content.getFormat();
//		List<Integer> mlist = new ArrayList<Integer>();
//		mlist.add(module1.getId());
//		mlist.add(module2.getId());
//
//		String badtitle = "inaccurate title";
//		Set<Content> filtered = ss.filter(title, format, mlist);
//		filtered = ss.filter(badtitle, format, mlist);
//		boolean badTitleFilter = filtered.contains(content);
//		
//		assertFalse(badTitleFilter);
//	}
//	
//	/**
//	 * Tests to make sure filtering removes irrelevant formats.
//	 */
//	@Test
//	@Rollback
//	void searchServiceBadFormat()
//	{
//		Module module1 = new Module(0, "FIRST TEST MODULE", 0, null);
//		Module module2 = new Module(0, "SECOND TEST MODULE", 0, null);
//		Module module3 = new Module(0, "THIRD TEST MODULE", 0, null);
//		
//		Content content = new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION", "http://www.elmo.test", new HashSet<Link>(), 1563378565, 1563378565);
//
//		module1 =mr.save(module1);
//		module2 =mr.save(module2);
//		module3 = mr.save(module3);
//		content = cr.save(content);
//		
//		Link link1 = new Link(0, content.getId(), module1.getId(), "RelevantTo");
//		Link link2 = new Link(0, content.getId(), module2.getId(), "RelevantTo");
//		
//		Set<Link> contentLinks = new HashSet<Link>();
//		contentLinks.add(link1);
//		contentLinks.add(link2);
//		
//		content.setLinks(contentLinks);
//		
//		content = cr.save(content);
//
//		String title = content.getTitle();
//		String format = content.getFormat();
//		List<Integer> mlist = new ArrayList<Integer>();
//		mlist.add(module1.getId());
//		mlist.add(module2.getId());
//
//		String badformat = "Document";
//		Set<Content> filtered = ss.filter(title, format, mlist);
//		filtered = ss.filter(title, badformat, mlist);
//		boolean badFormatFilter = filtered.contains(content);
//		
//		assertFalse(badFormatFilter);
//	}
//	
//	/**
//	 * Tests to see if the filter ignores irrelevant modules.
//	 */
//	@Test
//	@Rollback
//	void searchServiceBadModule()
//	{
//		Module module1 = new Module(0, "FIRST TEST MODULE", 0, null);
//		Module module2 = new Module(0, "SECOND TEST MODULE", 0, null);
//		Module module3 = new Module(0, "THIRD TEST MODULE", 0, null);
//		
//		Content content = new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION", "http://www.elmo.test", new HashSet<Link>(), 1563378565, 1563378565);
//
//		module1 =mr.save(module1);
//		module2 =mr.save(module2);
//		module3 = mr.save(module3);
//		content = cr.save(content);
//		
//		Link link1 = new Link(0, content.getId(), module1.getId(), "RelevantTo");
//		Link link2 = new Link(0, content.getId(), module2.getId(), "RelevantTo");
//		
//		Set<Link> contentLinks = new HashSet<Link>();
//		contentLinks.add(link1);
//		contentLinks.add(link2);
//		
//		content.setLinks(contentLinks);
//		
//		content = cr.save(content);
//		
//		String title = content.getTitle();
//		String format = content.getFormat();
//
//		List<Integer> mlist = new ArrayList<Integer>();
//		mlist.add(module3.getId());
//		Set<Content >filtered = ss.filter(title, format, mlist);
//		boolean badModuleFilter = filtered.contains(content);
//		assertFalse(badModuleFilter);
//	}
//
//	/*
//	 * Handles testing of the new filtering method with no DB calls
//	 */
//	@Test
//	@Rollback
//	void testMetricsFiltering() {
//		Module module1 = new Module(0, "FIRST TEST MODULE", 0, null);
//		Module module2 = new Module(0, "SECOND TEST MODULE", 0, null);
//		Module module3 = new Module(0, "THIRD TEST MODULE", 0, null);
//		
//		Content content = new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION", "http://www.elmo.test", new HashSet<Link>(), 1563378565, 1563378565);
//
//		module1 = mr.save(module1);
//		module2 = mr.save(module2);
//		module3 = mr.save(module3);
//		content = cr.save(content);
//		
//		Link link1 = new Link(0, content.getId(), module1.getId(), "RelevantTo");
//		Link link2 = new Link(0, content.getId(), module2.getId(), "RelevantTo");
//		
//		Set<Link> contentLinks = new HashSet<Link>();
//		contentLinks.add(link1);
//		contentLinks.add(link2);
//		
//		content.setLinks(contentLinks);
//		
//		content = cr.save(content);
//		
//		Set<Content> testCont = new HashSet<Content>();
//		testCont.add(content);
//		
//		String title = content.getTitle();
//		String format = content.getFormat();
//		List<Integer> mlist = new ArrayList<Integer>();
//		mlist.add(module1.getId());
//		mlist.add(module2.getId());
//		Map<String, Object> testFilters = new HashMap<String, Object>();
//		testFilters.put("title", title);
//		testFilters.put("format", format);
//		testFilters.put("modules", mlist);
//		
//		Set<Content> hold = new HashSet<Content>(testCont);
//		Set<Content> filtered = ss.filterContent(testCont, testFilters);
//		boolean goodFiltered = filtered.equals(hold);
//		
//		assertTrue(goodFiltered);
//	}
//	
//	@Test
//	@Rollback
//	void testMetricsFilteringBadInfo() {
//		Module module1 = new Module(0, "FIRST TEST MODULE", 0, null);
//		Module module2 = new Module(0, "SECOND TEST MODULE", 0, null);
//		Module module3 = new Module(0, "THIRD TEST MODULE", 0, null);
//			
//		Content content = new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION", "http://www.elmo.test", new HashSet<Link>(), 1563378565, 1563378565);
//
//		module1 = mr.save(module1);
//		module2 = mr.save(module2);
//		module3 = mr.save(module3);
//		content = cr.save(content);
//				
//		Link link1 = new Link(0, content.getId(), module1.getId(), "RelevantTo");
//		Link link2 = new Link(0, content.getId(), module2.getId(), "RelevantTo");
//				
//		Set<Link> contentLinks = new HashSet<Link>();
//		contentLinks.add(link1);
//		contentLinks.add(link2);
//				
//		content.setLinks(contentLinks);
//		
//		content = cr.save(content);
//			
//		Set<Content> testCont = new HashSet<Content>();
//		testCont.add(content);
//		
//		String badTitle = "notTitle";
//		String badFormat = "notFormat";
//		List<Integer> nolist = new ArrayList<Integer>();
//		Map<String, Object> badFilters = new HashMap<String, Object>();
//		badFilters.put("title", badTitle);
//		badFilters.put("format", badFormat);
//		badFilters.put("modules", nolist);
//
//		Set<Content> hold = new HashSet<Content>(testCont);
//		Set<Content> filtered = ss.filterContent(testCont, badFilters);
//		boolean badFiltered = filtered.equals(hold);
//		
//		assertFalse(badFiltered);
//	}
	
}
