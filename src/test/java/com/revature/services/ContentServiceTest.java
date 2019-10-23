
package com.revature.services;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.revature.entities.Module;
import com.revature.exceptions.InvalidContentException;
import com.revature.exceptions.InvalidContentId;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleRepository;

/**
 * Testing for the ContentService class.
 * 
 * @author wsm, fjgiv
 * @version 2.0
 */
@SpringBootTest(classes = CMSforceApplication.class)
public class ContentServiceTest extends AbstractTestNGSpringContextTests
{	

	private static final String Answer = null;
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
	Set<Content> contentList = new HashSet<Content>();

	Content createNewContentActual = new Content();
	Content createNewContentExpected = new Content();
	
	/**
	 * A method to set up mocked methods and dependencies for the tests that follow
	 */
	
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
//		//Create a new Link object
		mockLink = new Link(3,mockContent, null, "stuff");
//		//create a new HashSet of Link objects
		links = new HashSet<Link>();
//		//Add the mock link to the list of links
		links.add(mockLink);
//		//
		for (Link link : links) {
			link.setContent(mockContent);
		}
//		
//		//Set the value of content links to null and mock the save function within the ContentRepository
		mockContent.setLinks(null);
		when(contRep.save(mockContent)).thenReturn(mockContent);
//		
//		//Set the value of content links to their previous values and mock the saveAll method of the LinkRepository
		mockContent.setLinks(links);
		when(linkRep.saveAll(mockContent.getLinks())).thenReturn(mockContent.getLinks());
		
		testContent = contServe.createContent(mockContent);
		
	
		createNewContentExpected.setDateCreated(System.currentTimeMillis());
		createNewContentExpected.setLastModified(System.currentTimeMillis());
	}
	
	
	/**
	 * This test is testing the functionality of the createContent method within the ContentServiceImpl class
	 */
	
	@Test
	public void testCreateContent() 
	{
		ret = contServe.createContent(mockContent);
		assertEquals(ret, mockContent, "Should get back same content");
		
	}

	@Test
	public void testCreateNewContentHasCreationDate() 
	{
		when(contRep.save(createNewContentActual)).thenReturn(createNewContentExpected);
		
		ret = contServe.createContent(createNewContentActual);
		
		verify(contRep).save(createNewContentActual);
		reset(contRep);
		assertNotEquals(ret.getDateCreated(), 0L);
		
	}
	

	@Test
	public void testCreateNewContentHasLastModifiedDate() 
	{
		when(contRep.save(createNewContentActual)).thenReturn(createNewContentExpected);
		
		ret = contServe.createContent(createNewContentActual);
		
		verify(contRep).save(createNewContentActual);
		reset(contRep);
		assertNotEquals(ret.getLastModified(), 0L);
		
	}
	/*
	 * This method tests the functionality of the .getConentById method within the ContentServiceImpl class
	 * */
	
	@Test
	public void testGetContentById()
	{
		when(contRep.findById(testContent.getId())).thenReturn(testContent);
		assertTrue(contServe.getContentById(testContent.getId()).equals(testContent));
	}
	
	/*
	 * This method tests the functionality of the .updateContent method within the ContentServiceImpl class
	 * */
	/*
	@Test
	public void testUpdateContent()
	{

		links.add(new Link(5,mockContent.getId(),9, "other stuff"));
		testContent.setLinks(links);
		contentSet.add(testContent);
		when(contRep.findById(testContent.getId())).thenReturn(contentSet);
		mockContent = contServe.updateContent(testContent);
		assertTrue(testContent.equals(mockContent));
	}
	*/
	
	/**
	 * This method tests the functionality of the .getAllContent method within the ContentServiceImpl class
	 */
	
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
//		contentSet = contServe.getAllContentMinusLinks();
//		assertNotNull(contentSet);
	}
	

	
	
	 
	
	/**
	 * 	  This method tests the functionality of the .getContentByFormatWithStrings method within the ContentServiceImpl class
	 */
	@Test(dependsOnMethods = {"testGetContentById"})
	public void testGetContentByFormatWithStrings()
	{
		secondContentSet = contServe.getAllContent();
		for(Content item: secondContentSet)
		{
			contentList.add(item);
		}
		when(contRep.findAll()).thenReturn(contentList);
		contentByFormat = contServe.getFormatCount(contentFormats);
		secondContentByFormat = contServe.getFormatCount(contentFormats);
		assertTrue(contentByFormat.equals(secondContentByFormat));
	}
	
	
	/**
	 * This method tests the functionality of the .getContentByFormatWithStrings method within the ContentServiceImpl class
	 */

	@Test(dependsOnMethods = {"testGetContentById"})
	public void testGetContentByFormatWithContents()
	{
		contentByFormat = contServe.getFormatCount(contentSet);
		secondContentByFormat = contServe.getFormatCount(contentSet);
		assertTrue(contentByFormat.equals(secondContentByFormat));
	}
	
	@Test(dependsOnMethods = {"testGetContentById"})
	public void testGetContentByFormatWithContentsWithDuplicateFormat()
	{
		Content c = new Content();
		c.setFormat("Blooh");
		contentSet.add(c);
		
		contentByFormat = contServe.getFormatCount(contentSet);
		secondContentByFormat = contServe.getFormatCount(contentSet);
		assertTrue(contentByFormat.equals(secondContentByFormat));
	}
	
	@Test(expectedExceptions = InvalidContentException.class)
	public void testUpdateNullContent() {
		contServe.updateContent(null);
	}

	@Test(expectedExceptions = InvalidContentId.class)
	public void testUpdateWithNonExistentContentId() {
		Content actual = new Content();
		
		int contentIdNotInDatabase = -1;
		actual.setId(contentIdNotInDatabase);
		
		when(contServe.updateContent(actual)).thenReturn(null);
		contServe.updateContent(actual);
		verify(contServe).updateContent(actual);
		
	}
	
	@Test
	public void testUpdateContent() {
		when(contRep.save(mockContent)).thenReturn(mockContent);
		ret = contServe.updateContent(mockContent);
		assertEquals(ret, mockContent, "Should get back same content");
		verify(contRep).save(mockContent);
	}
	
	@Test
	public void testDeleteContent() {
		contServe.deleteContent(mockContent);
		verify(contRep).delete(mockContent);
	}
	
	@Test
	public void testGetLinksByContentId() {
		
		int dummyId = 1;
		
		Link link1 = new Link(1, new Content(), new Module(), "affiliation1");
		Link link2 = new Link(2, new Content(), new Module(), "affiliation2");
		Link link3 = new Link(3, new Content(), new Module(), "affiliation3");

		Set<Link> expected = new HashSet<>();
		expected.add(link1);
		expected.add(link2);
		expected.add(link3);
		
		when(linkRep.findByContentId(dummyId)).thenReturn(expected);
		
		Set<Link> linksReturned = contServe.getLinksByContentId(dummyId);
		
		verify(linkRep).findByContentId(dummyId);
		
		assertEquals(linksReturned, expected);
	}
	
	@Test
	public void testUpdateLinksByContentId() {
		
		int dummyId = 1;
		
		Link link1 = new Link(1, new Content(), new Module(), "affiliation1");
		Link link2 = new Link(2, new Content(), new Module(), "affiliation2");
		Link link3 = new Link(3, new Content(), new Module(), "affiliation3");

		List<Link> expected = new ArrayList<>();
		expected.add(link1);
		expected.add(link2);
		expected.add(link3);
		
		//when(linkRep.save(dummyLink)).thenReturn(link1, link2, link3);
		
		for (Link link : expected) {
			when(linkRep.save(link)).thenReturn(link);
		}
		
		//doNothing().when(linkRep.save(dummyLink));
		
		List<Link> returnedLinks = contServe.updateLinksByContentId(dummyId, expected);
		
		for (Link link : expected) {
			verify(linkRep).save(link);
		}
		reset(linkRep);
		assertEquals(returnedLinks, expected);
	}
	
	@Test(dependsOnMethods = {"testUpdateLinksByContentId"})
	public void testCreateLinksByContentId() {
		
		int dummyId = 1;
		
		Link link1 = new Link(1, new Content(), new Module(), "affiliation1");
		Link link2 = new Link(2, new Content(), new Module(), "affiliation2");
		Link link3 = new Link(3, new Content(), new Module(), "affiliation3");

		List<Link> expected = new ArrayList<>();
		expected.add(link1);
		expected.add(link2);
		expected.add(link3);
		
		//when(linkRep.save(dummyLink)).thenReturn(link1, link2, link3);
		
		for (Link link : expected) {
			when(linkRep.save(link)).thenReturn(link);
		}
		
		//doNothing().when(linkRep.save(dummyLink));
		
		List<Link> returnedLinks = contServe.createLinksByContentId(dummyId, expected);
		
		for (Link link : expected) {
			verify(linkRep).save(link);
		}
		reset(linkRep);
		assertEquals(returnedLinks, expected);
	}
	

}