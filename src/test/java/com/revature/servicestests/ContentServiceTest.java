package com.revature.servicestests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.transaction.Transactional;

import java.util.HashSet;

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

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.repositories.ContentRepository;
import com.revature.entities.Module;
import com.revature.services.ContentService;
import com.revature.services.ModuleService;
import com.revature.services.SearchService;

/**
 * Testing for the ContentService class.
 * 
 * @author wsm
 * @version 2.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ContentServiceTest {

	@Autowired
	ContentService cs;
	
	@Autowired
	ContentRepository cr;

	@Autowired
	SearchService ss;

	@Autowired
	JdbcTemplate template;
	
	@Autowired
	ModuleService ms;

	/**
	 * Standard test for content creation.
	 * 
	 * <p> Creates content, finds it via getAllContent and getContentById,
	 * then deletes it.
	 */
	@Test
	@Rollback
	void contentServiceCreateTest()
	{

		//Standalone Content
		Content aloneContent = new Content();
		aloneContent.setDateCreated(System.currentTimeMillis());
		aloneContent.setLastModified(System.currentTimeMillis());
		aloneContent.setDescription("Standalone Content Test Description");
		aloneContent.setTitle("Standalone Content Test Title");
		aloneContent.setFormat("Code");
		aloneContent.setUrl("http://test.test/");
		aloneContent.setLinks(new HashSet<Link>());
		
		aloneContent = cs.createContent(aloneContent);
				
		//getAll test
		Set<Content> allContent = cs.getAllContent();
		
		boolean containsStandalone = allContent.contains(aloneContent);
		
		//getId test
		Content c = cs.getContentById(aloneContent.getId());
		
		boolean idCheck = aloneContent.equals(c);
		
		//cleanup
		cr.delete(aloneContent);
		
		//assertions
		assertTrue(containsStandalone);
		assertTrue(idCheck);
	}
	
	/**
	 * An invalid content test, should fail due to DB constraints.
	 */
	@Test
	void createInvalidContent1()
	{
		boolean fail = false;
		try
		{
			cs.createContent(
				new Content(0, null, "Code", "MOCK DATA", "http://localhost:4200/file.txt", new HashSet<Link>(), 1563378565, 1563378565));
		}
		catch (Exception e) 
		{
			fail = true;
		}
		finally 
		{
			assertTrue(fail);
		}
	}
	
	/**
	 * An invalid content test, should fail due to DB constraints.
	 */
	@Test
	void createInvalidContent2()
	{
		boolean fail = false;
		try
		{
		cs.createContent(
				new Content(0, "Philosophy", null, "MOCK DATA", "http://localhost:4200/file.txt", new HashSet<Link>(), 1563378565, 1563378565));
		}
		catch (Exception e) 
		{
			fail = true;
		}
		finally 
		{
			assertTrue(fail);
		}
	}
	
	/**
	 * An invalid content test, should fail due to DB constraints.
	 */
	@Test
	void createInvalidContent3()
	{
		boolean fail = false;
		try
		{
		cs.createContent(new Content(0, "Tropical Fish Anatomy", "Code", null, "http://localhost:4200/file.txt",
				new HashSet<Link>(), 1563378565, 1563378565));
		}
		catch (Exception e) 
		{
			fail = true;
		}
		finally 
		{
			assertTrue(fail);
		}
	}
	
	/**
	 * An invalid content test, should fail due to DB constraints.
	 */
	@Test
	void createInvalidContent4()
	{
		boolean fail = false;
		try
		{
			Content c = cs.createContent(new Content(0, "Cubism", "Code", "MOCK DATA", null, new HashSet<Link>(), 1563378565, 1563378565));
			System.out.println(c);
		}
		catch (Exception e) 
		{
			fail = true;
		}
		finally 
		{
			assertTrue(fail);
		}
	}
	
	@Test
	@Rollback
	@Order(8)
	public void updateContent() {
		
		Module module1 = new Module(0, "Element Zero", 0, null);
		module1 = ms.createModule(module1);
		
		Module module2 = new Module(0, "Mass Effect Fields", 0, null);
		module2 = ms.createModule(module2);
		
		Set<Link> links = new HashSet<Link>();
		
		//we will know the module id but not the content id or link id
		Link link1 = new Link(0,0,module1.getId(),"relaventTo");
		Link link2 = new Link(0,0,module2.getId(),"relaventTo");
		
		links.add(link1);
		links.add(link2);
		
		Content content = new Content(0,
				"Mass Effect Theory",
				"Hologram",
				"AI explains Mass Effect Theory",
				"www.mass.test",
				links);
		
		content = cs.createContent(content);
		
		content.setDescription("RIP Bioware...");
		
		assertNotNull(cs.updateContent(content));
		assertEquals(0, content.getDescription().compareTo(cs.getContentById(content.getId()).getDescription()));
	}

}