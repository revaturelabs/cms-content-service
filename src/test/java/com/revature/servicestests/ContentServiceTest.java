package com.revature.servicestests;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.services.ContentServiceImpl;

/**
 * Testing for the ContentService class.
 * 
 * @author wsm
 * @version 2.0
 */
@SpringBootTest(classes = CMSforceApplication.class)
class ContentServiceTest extends AbstractTestNGSpringContextTests 
{	
	//Variable to store the Content object
	Content mockContent;
	//The mocked ContentServiceImple into which the dependencies are injected
	@InjectMocks
	ContentServiceImpl contServe;
	//The mocked implementation of the ContentRepository interface
	@Mock
	ContentRepository contRep;
	//The mocked implementation of the LinkRepository interface
	@Mock
	LinkRepository linkRep;
	//The mocked implementation of the ModuleRepository interface
	@Mock
	ModuleRepository modRep;
	//A Set of Link objects to store the links within the content
	Set<Link> links;
	//A variable to store the Link value for the content
	Link mockLink;
	//A variable for storing the value of the Link returned
	Link retLink;
	//A variable for storing the value for the Content returned
	Content ret;
	//A variable for storing the value of another Content object
	Content testContent;
	//A Set of Content objects to store multiple items for testing
	Set<Content> contentSet = new HashSet<Content>();
	//A second Set of Content to store multiple items for comparing in testing
	Set<Content> secondContentSet;
	//A Map of String and Integer objects for testing retrieval of Content by its format field
	Map<String, Integer> contentByFormat;
	//A Map of String and Integer objects for testing comparison of Content found by format field
	Map<String, Integer> secondContentByFormat;
	//An array of Strings to be used to test retrieval of Content by format
	String[] contentFormats = {"Blooh", "Blah"};
	//An ArrayList of Content items to mock a method within the getContentWithStrings method
	ArrayList<Content> contentList = new ArrayList<Content>();
	
	/*
	 * A method to set up mocked methods and dependencies for the tests that follow
	 * */
	@BeforeClass
	public void mockTheContent()
	{
		//Initiate Mockito mocks
		MockitoAnnotations.initMocks(this);
		//Create a new Content object and set fields as shown
		mockContent = new Content();
		mockContent.setId(5);
		mockContent.setDateCreated(1L);
		mockContent.setLastModified(1L);
		mockContent.setTitle("Blahbitty");
		mockContent.setFormat("Blooh");
		mockContent.setDescription("Bloohbitty");
		mockContent.setUrl("www.blahbitty.com");
		//Create a new Link object
		mockLink = new Link(3,mockContent.getId(),7,"Blah");
		//create a new HashSet of Link objects
		links = new HashSet<Link>();
		//Add the mock link to the list of links
		links.add(mockLink);
		//
		for (Link link : links) {
			link.setContentId(mockContent.getId());
		}
		
		//Set the value of content links to null and mock the save function within the ContentRepository
		mockContent.setLinks(null);
		when(contRep.save(mockContent)).thenReturn(mockContent);
		
		//Set the value of content links to their previous values and mock the saveAll method of the LinkRepository
		mockContent.setLinks(links);
		when(linkRep.saveAll(mockContent.getLinks())).thenReturn(mockContent.getLinks());
		
		testContent = contServe.createContent(mockContent);
	}
	
	/*
	 * This test is testing the functionality of the createContent method within the ContentServiceImpl class
	 * */
	@Test
	public void testCreateContent() 
	{
		ret = contServe.createContent(mockContent);
		assertEquals(ret, mockContent, "Should get back same content");
	}
	
	/*
	 * This method tests the functionality of the .getConentById method within the ContentServiceImpl class
	 * */
	@Test
	public void testGetContentById()
	{
		assertTrue(contRep.findById(testContent.getId()).equals(contRep.findById(mockContent.getId())));
	}
	
	/*
	 * This method tests the functionality of the .updateContent method within the ContentServiceImpl class
	 * */
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
	
	/*
	 * This method tests the functionality of the .getAllContent method within the ContentServiceImpl class
	 * */
	@Test
	public void testGetAllContent()
	{
		contentSet.add(mockContent);
		when(contRep.findAll()).thenReturn(contentSet);
		secondContentSet = contServe.getAllContent();
		assertTrue(contServe.getAllContent().equals(secondContentSet));
	}
	
	//This method tests the functionality of the .getAllContentMinusLinks method within the ContentServiceImpl class
	@Test
	public void testGetAllContentMinusLinks()
	{
		contentSet = contServe.getAllContentMinusLinks();
		assertNotNull(contentSet);
	}
	
	/*
	 * This method tests the functionality of the .getContentByFormatWithStrings method within the ContentServiceImpl class
	 * */
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
	
	/*
	 * This method tests the functionality of the .getContentByFormatWithStrings method within the ContentServiceImpl class
	 * */
	@Test(dependsOnMethods = {"testGetContentById"})
	public void testGetContentByFormatWithContents()
	{
		contentByFormat = contServe.getContentByFormat(contentSet);
		secondContentByFormat = contServe.getContentByFormat(contentSet);
		assertTrue(contentByFormat.equals(secondContentByFormat));
	}
}