package com.revature.servicestests;

import static org.junit.Assert.assertNotNull;
//import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.junit.Before;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.cmsforce.CMSforceApplication;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.services.ContentService;
import com.revature.services.ContentServiceImpl;
import com.revature.services.ModuleService;
import com.revature.services.SearchService;
import com.revature.services.SearchServiceImpl;

/**
 * Testing for the ContentService class.
 * 
 * @author wsm
 * @version 2.0
 */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = CMSforceApplication.class)
class ContentServiceTest extends AbstractTestNGSpringContextTests 
{	
	
	
	Content mockContent;
	@InjectMocks
	ContentServiceImpl contServe;
	@Mock
	ContentRepository contRep;
	@Mock
	LinkRepository linkRep;
	@Mock
	ModuleRepository modRep;
	
	Set<Link> links;
	Link mockLink;
	Link retLink;
	Content ret;
	Content testContent;
	Set<Content> contentSet = new HashSet();
	Set<Content> secondContentSet;
	Map<String, Integer> contentByFormat;
	Map<String, Integer> secondContentByFormat;
	String[] contentFormats = {"Blooh", "Blah"};
	ArrayList<Content> contentList = new ArrayList<Content>();
	
	@BeforeClass
	public void mockTheContent()
	{
		MockitoAnnotations.initMocks(this);
		mockContent = new Content();
		mockContent.setId(5);
		mockContent.setDateCreated(1L);
		mockContent.setLastModified(1L);
		mockContent.setTitle("Blahbitty");
		mockContent.setFormat("Blooh");
		mockContent.setDescription("Bloohbitty");
		mockContent.setUrl("www.blahbitty.com");		
		mockLink = new Link(3,mockContent.getId(),7,"Blah");
		links = new HashSet<Link>();
		links.add(mockLink);
		for (Link link : links) {
			link.setContentId(mockContent.getId());
		}
		
		mockContent.setLinks(null);
		when(contRep.save(mockContent)).thenReturn(mockContent);
		

		mockContent.setLinks(links);
		when(linkRep.saveAll(mockContent.getLinks())).thenReturn(mockContent.getLinks());
		
		testContent = contServe.createContent(mockContent);
	}
	
	@Test
	public void testCreateContent() 
	{
		ret = contServe.createContent(mockContent);
		assertEquals(ret, mockContent, "Should get back same content");
	}
	
	@Test
	public void testGetContentById()
	{
		assertTrue(contRep.findById(testContent.getId()).equals(contRep.findById(mockContent.getId())));
	}
	
	@Test
	public void testUpdateContent()
	{
		links.add(new Link(5,mockContent.getId(),9,"BlahbittyBlooh"));
		testContent.setLinks(links);
		contentSet.add(testContent);
		when(contRep.findById(testContent.getId())).thenReturn(contentSet);
		mockContent = contServe.updateContent(testContent);
		assertTrue(testContent.equals(mockContent));
	}
	
	@Test
	public void testGetAllContent()
	{
		contentSet.add(mockContent);
		when(contRep.findAll()).thenReturn(contentSet);
		secondContentSet = contServe.getAllContent();
		assertTrue(contServe.getAllContent().equals(secondContentSet));
	}
	
	//This test is for a method that has no purpose
	@Test
	public void testGetAllContentMinusLinks()
	{
		contentSet = contServe.getAllContentMinusLinks();
		assertNotNull(contentSet);
	}
	
	@Test(dependsOnMethods = {"testGetContentById"})
	public void testGetContentByFormatWithStrings()
	{
		secondContentSet = contServe.getAllContent();
		for(Content item: secondContentSet)
		{
			contentList.add(item);
		}
		when(contRep.findAll()).thenReturn(contentList);
		contentByFormat = contServe.getContentByFormat(contentFormats);
		secondContentByFormat = contServe.getContentByFormat(contentFormats);
		assertTrue(contentByFormat.equals(secondContentByFormat));
	}
	
	@Test(dependsOnMethods = {"testGetContentById"})
	public void testGetContentByFormatWithContents()
	{
		contentByFormat = contServe.getContentByFormat(contentSet);
		secondContentByFormat = contServe.getContentByFormat(contentSet);
		assertTrue(contentByFormat.equals(secondContentByFormat));
	}
}