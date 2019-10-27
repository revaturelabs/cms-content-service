package com.revature.servicesTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.cmsforce.CMSforceApplication;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.exceptions.InvalidContentException;
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
public class ContentServiceTest// extends AbstractTestNGSpringContextTests
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
	Set<Content> contentList = new HashSet<Content>();

	
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
//		//Create a new Link object

		mockLink = new Link(3,mockContent, null, "stuff",0);
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
	}
	
	
	/**
	 * This test is testing the functionality of the createContent method within the ContentServiceImpl class
	 * {@link com.revature.services.ContentService#createContent(Content) createContent(Content).}
	 * 
	 */
	
	@Test
	public void testCreateContent() 
	{	
		Content content = new Content(1, null, null, null, null, 0, 0, links);
		Mockito.when(contRep.save(content)).thenReturn(content);
		Content actual = contServe.createContent(content);
		assertEquals(content, actual);
	}
	
	/**
	 * This method tests the functionality of the .getConentById method within the ContentServiceImpl class
	 * {@link com.revature.services.ContentService#getContentById(int) getContentById(int).}
	 */
	
	@Test
	public void testGetContentById()
	{
		when(contRep.findById(testContent.getId())).thenReturn(testContent);
		assertTrue(contServe.getContentById(testContent.getId()).equals(testContent));
	}	
	
	/**
	 * This method tests the functionality of the .getAllContent method within the ContentServiceImpl class
	 * {@link com.revature.services.ContentService#getAllContent() getAllContent().}
	 */
	@Test
	public void testGetAllContent()
	{
		contentSet.add(mockContent);
		when(contRep.findAll()).thenReturn(contentSet);
		secondContentSet = contServe.getAllContent();
		assertTrue(contServe.getAllContent().equals(secondContentSet));
	}
	/**
	 * this method tests {@link com.revature.services.ContentService#getLinksByContentId(int) getLinksByContentId(int).}
	 */
	@Test 
	public void testGetLinksByContentId() 
	{
	    Link testLink1 = new Link(1,testContent,null, "affiliation1", 1);
	    Link testLink2 = new Link(2,testContent,null, "affiliation2", 2);
	    Link testLink3 = new Link(3,testContent,null, "affiliation3", 3);
	    Set<Link> linkSet = new HashSet<Link>();
	    linkSet.add(testLink1); linkSet.add(testLink2); linkSet.add(testLink3);
	    
	    when(linkRep.findByContentId(testLink2.getId())).thenReturn((linkSet));
	    assertTrue(contServe.getLinksByContentId(testLink2.getId()).equals(linkSet));
	}

	/**
	 * this method tests {@link com.revature.services.ContentService#getFormatCount(String[]) getFormatCount(String[]).}
	 * this method assumes a string array of formats is passed in as an argument, and returns the amount of appearances a format has in the form of a Map<String, Integer>
	 */
	@Test(dependsOnMethods = {"testGetContentById"})
	public void testGetFormatCount_StringVersion()
	{
		String [] mockArray = {"Java"};
		Set<Content> mockSet = new HashSet<Content>();
		Content mockContent = new Content(1, "title", "Java", "OOPL", "URL", 12345L, 54321L, links);
		mockSet.add(mockContent);
		
		Map <String, Integer> expected = new HashMap<> ();
		expected.put("Java", 1);
		
		Mockito.when(contRep.findAll()).thenReturn(mockSet);
		Map <String, Integer> actualMap = new HashMap<> ();
		actualMap = contServe.getFormatCount(mockArray);
		
		assertTrue(actualMap.equals(expected));
	}
	
	/**
	 * this method tests {@link com.revature.services.ContentService#getFormatCount(Set) getFormatCount(Set<Content>).}
	 * this method assumes a Set of Content is passed in as an argument, and returns the amount of appearances a format has in the form of a Map<String, Integer>
	 * 
	 * Defect Present: Bug - The method does not correctly handle multiple formats if the format in question is not the first format. you can replicate this bug by adding a new object with format hello and setting the expected map to expected.pun("hello", 2)
	 */
	@Test(dependsOnMethods = {"testGetContentById"})
	public void testGetFormatCount_ContentVersion()
	{ 
		Set<Content> mockSet = new HashSet<Content>();
		Content mockContent = new Content(1, "title", "Java", "OOPL", "URL", 12345L, 54321L, links);
		Content mockContent2 = new Content(2, "title", "Java", "OOPL", "URL", 2345L, 4321L, links);
		Content mockContent3 = new Content(3, "title", "Hello", "OOPL", "URL", 2345L, 4321L, links);

		mockSet.add(mockContent); mockSet.add(mockContent2); mockSet.add(mockContent3);
		
		Map <String, Integer> expected = new HashMap<> ();
		expected.put("Java", 2);
		expected.put("Hello", 1);
		
		Map <String, Integer> actualMap = new HashMap<> ();
		actualMap = contServe.getFormatCount(mockSet);
	
		assertEquals(actualMap, expected);
	}
	
	/**
 	* this method tests {@link com.revature.services.ContentService#updateContent(Content) updateContent(Content).}
 	*/
	@Test
	public void testUpdateContent() {
		contServe.updateContent(mockContent);
		verify(contRep, times(2)).save(mockContent);
	}
	
	/**
 	* this method tests {@link com.revature.services.ContentService#updateContent(Content) updateContent(Content).}
 	* assuming that the content passed in as an argument is null, then throws an exception
 	*/
	@Test(expectedExceptions = {InvalidContentException.class})
	public void updateContentTest_ContentIsNull() {
		Content cont = null;
		contServe.updateContent(cont);
		
	}
	
	/**
 	* this method tests {@link com.revature.services.ContentService#deleteContent(Content) deleteContent(Content).}
 	*/
	@Test
	public void testDeleteContent() {
		contServe.deleteContent(mockContent);
		verify(contRep).delete(mockContent);
	}
	
	/**
 	* this method tests {@link com.revature.services.ContentService#updateLinksByContentId(int, List) updateLinksByContentId(int).}
 	* this method assumes an integer Id is passed and then returns a set of the newly persisted link objects
 	*/
	@Test
	public void updateLinksByContentIdTest() {
		Link link = new Link();
		Link link2 = new Link();
		List<Link> list = new ArrayList<Link>();
		list.add(link2);
		list.add(link);
		
		when(linkRep.save(link)).thenReturn(link);
		when(linkRep.save(link2)).thenReturn(link2);

		//Id is unused in the method. Id is irrelevant.
		contServe.updateLinksByContentId(5, list);
		verify(linkRep, times(4)).save(link);
		verify(linkRep, times(4)).save(link2);
	}
	
	/**
	 * This method Tests {@link com.revature.services.ContentServiceImpl#createLinksByContentId(int, List) createLinksByContentId(int List<Link>.}
	 * This Method assumes an int id and list of link objects are passed as arguments, and returns a List of the newly persisted Link Objects. 
	 */
	@Test
	public void testCreateLinksByContentId() {
		Link link = new Link();
		Link link2 = new Link();
		List<Link> list = new ArrayList<Link>();
		list.add(link2);
		list.add(link);
		
		when(linkRep.save(link)).thenReturn(link);
		when(linkRep.save(link2)).thenReturn(link2);

		//Id is unused in the method. Id is irrelevant.
		contServe.createLinksByContentId(5, list);
		verify(linkRep, times(2)).save(link);
		verify(linkRep, times(2)).save(link2);
	}

}