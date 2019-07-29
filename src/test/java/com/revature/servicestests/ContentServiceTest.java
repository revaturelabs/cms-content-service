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
	 * <p> Creates content, finds it via getAllContent,
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
				
		//cleanup
		cr.delete(aloneContent);
		
		//assertions
		assertTrue(containsStandalone);
	}
	
	/**
	 * Standard test for getting Id.
	 * 
	 * <p> Creates content, finds it via getContentById, then deletes it.
	 */
	@Test
	@Rollback
	void contentServiceCreateGetIdCheck()
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
		Content c = cs.getContentById(aloneContent.getId());
		
		boolean idCheck = aloneContent.equals(c);
		
		//cleanup
		cr.delete(aloneContent);
		
		//assertions
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

}