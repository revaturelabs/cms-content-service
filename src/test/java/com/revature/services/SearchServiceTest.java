
package com.revature.services;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleRepository;

/**
 * Testing for the RequestService class.
 * 
 * @author unknown, fjgiv
 * @version 2.0
 */
public class SearchServiceTest {
	// Any time that two nulls appear in a test of a constructor, that is for a
	// feature that was created after the tests were created to allow them to pass.

	// ===Mock Injections===
	@Mock
	private ContentRepository crMock;
	@Mock
	private ModuleRepository mrMock;

	@Mock
	private LinkRepository lrMock;
	@Mock
	private ContentService csMock;
	@Mock
	private ModuleService msMock;

	@InjectMocks
	private SearchServiceImpl ss;// = new SearchServiceImpl();

	// ===Fields===
	// Link linkMock;
	// Set<Link> linkSetMock = new HashSet<Link>();

	// Content contentMock;
	// Set<Content> contentSetMock = new HashSet<Content>();

	// Module moduleMock;

	// ===Tests===
	@BeforeClass
	public void reinitMocks() {
		// Enable mocks for TestNG
		MockitoAnnotations.initMocks(this);

	}

	@BeforeTest
	public void testSetup() {
		// Link link;
		// Content content;

		// content = new Content(50, "Test 1", "format", "Test Content #1",
		// "www.example.com", linkSetMock, 1L, 1L, );
		// link = new Link(1,content,100,"link-affiliation");
		// linkSetMock.add(link);
		// contentSetMock.add(content);

		// content = new Content(51, "Test 2", "format", "Test Content #3",
		// "www.example.com", linkSetMock, 1L, 1L );
		// link = new Link(2,content,100,"lonk-affiliation");
		// linkSetMock.add(link);
		// contentSetMock.add(content);

		// content = new Content(52, "Test 3", "format", "Test Content #3",
		// "www.example.com", linkSetMock, 1L, 1L );
		// link = new Link(3,content,101,"lank-affiliation");
		// linkSetMock.add(link);
		// contentSetMock.add(content);

		// linkMock = link;
		// contentMock = content;

		// //Module
		// //Constructor (Id, "Subject", DateCreated, Set<Link>)
		// this.moduleMock = new Module(100, "Test Subject 1", 1L, this.linkSetMock,
		// null, null);

	}

	/**
	 * Tests filterContentById() Content Repository - findByTitle(String title)
	 */
	@Test
	public void filterContentByTitleTest() {
		String title = "Test Subject 3";

		// Local Variables
		Set<Content> contentSetExpected = new HashSet<Content>();
		contentSetExpected
				.add(new Content(1, title, "code", "something", "http://blah.com", 1L, 1L, new HashSet<Link>()));
		contentSetExpected
				.add(new Content(2, title, "code", "something else", "http://blah2.com", 1L, 1L, new HashSet<Link>()));

		// Given
		Mockito.when(crMock.findByTitle(title)).thenReturn(contentSetExpected);

		// When
		Set<Content> actual = ss.filterContentByTitle(title);

		// then
		verify(crMock, times(1)).findByTitle(title);
		Assert.assertEquals(actual, contentSetExpected);
	}

	/**
	 * Tests filterContentByFormat() Content Repository - findByFormat(String
	 * format)
	 */
	@Test
	public void filterContentByFormatTest() {
		// Local Variables
		String format = "format";
		Set<Content> contentSetExpected = new HashSet<Content>();
		contentSetExpected
				.add(new Content(1, "title 1", format, "something", "http://blah.com", 1L, 1L, new HashSet<Link>()));
		contentSetExpected.add(
				new Content(2, "title 2", format, "something else", "http://blah2.com", 1L, 1L, new HashSet<Link>()));

		// Given
		Mockito.when(crMock.findByFormat(format)).thenReturn(contentSetExpected);

		// When
		Set<Content> actual = ss.filterContentByFormat(format);

		// then
		verify(crMock, times(1)).findByFormat(format);
		Assert.assertEquals(actual, contentSetExpected);
	}

	/**
	 * Tests fliterContentBySubjects() Link Repository - findByModuleID() Content
	 * Repository - findAllById() Currently throws an IndexOutOfBounds Exception
	 * when you put in a ModuleId list with more than one number.
	 */

	@Test
	public void filterContentBySubjectsTest() {
		Content contentJavaSoup = new Content(1, "title 1", "format", "something", "http://blah.com", 1L, 1L,
				new HashSet<Link>());
		Content contentJava = new Content(2, "title 2", "format", "something else", "http://blah2.com", 1L, 1L,
				new HashSet<Link>());

		Set<Content> expected = new HashSet<Content>();
		expected.add(contentJavaSoup);

		Link soupLink = new Link(1, contentJavaSoup, new Module(), "affiliation 1");
		Link javaLink1 = new Link(2, contentJavaSoup, new Module(), "affiliation 2");
		Link javaLink2 = new Link(3, contentJava, new Module(), "affiliation 3");

		Set<Link> soupLinks = new HashSet<Link>();
		soupLinks.add(soupLink);

		Module module1 = new Module(1, "Soup", 1L, soupLinks, new HashSet<ReqLink>(), new HashSet<Module>(),
				new HashSet<Module>());

		Set<Link> javaLinks = new HashSet<Link>();
		javaLinks.add(javaLink1);
		javaLinks.add(javaLink2);

		Module module2 = new Module(2, "Java", 1L, javaLinks, new HashSet<ReqLink>(), new HashSet<Module>(),
				new HashSet<Module>());

		List<Integer> moduleIds = new ArrayList<Integer>();
		moduleIds.add(module1.getId());
		moduleIds.add(module2.getId());

		// Given
		Mockito.when(msMock.getModuleById(module1.getId())).thenReturn(module1);
		Mockito.when(msMock.getModuleById(module2.getId())).thenReturn(module2);

		// When 
		Set<Content> actual = ss.filterContentBySubjectIds(moduleIds);

		verify(msMock, times(moduleIds.size())).getModuleById(anyInt());
		reset(msMock);
		Assert.assertEquals(actual, expected);
	}

	/**
	 * this test is built so that content has content but after the intersection with tempContent it has no content
	 * 
	 * Please Note: when testContent set is cleared in searchServiceImpl it clears
	 * content as a side effect. Implementing clone may be solution.
	 * 
	 * Methods effects by this testContent/Subject.clear() filterContentBySubjects,
	 * filterRequestBySubjectIds, filterReq, filter
	 * 
	 *  I am module: 2 
	 * Content before tempContent.clear(): [] 
	 * Content after tempContent.clear(): [] 
	 * Link.getContent: Content [id=1, title=Test Content, format=Code, description=Test Description for content, url=www.google.com, dateCreated=1, lastModified=1] 
	 * TempContent: [Content [id=1, title=Test Content, format=Code, description=Test Description for content, url=www.google.com, dateCreated=1, lastModified=1]]
	 *  I am module: 1 Content
	 * before tempContent.clear(): [Content [id=1, title=Test Content, format=Code, description=Test Description for content, url=www.google.com, dateCreated=1, lastModified=1]] 
	 * Content after tempContent.clear(): []
	 * 
	 * ******************************************************************************
	 * Replace current subsection in SearchServiceImpl to replicate output when testing test below. 
	 * ******************************************************************************
	  for (Module module : modules) {
			System.out.println("I am module: "+ module.getId());
			// get links of module, the links hold the content within them
			links = module.getLinks();
			System.out.println("Content before tempContent.clear(): " + content);
			// make sure tempContent is empty at the start of a new iteration
			tempContent.clear();
			System.out.println("Content after tempContent.clear(): " + content);
		
			// add content in links to tempContent so we can work with it
			for (Link link : links) {
				tempContent.add(link.getContent());
				System.out.println("Link.getContent: " + link.getContent());
				System.out.println("TempContent: " + tempContent);
			}
			
			// if it is the first iteration, we just want to put tempContent into content
			if (content.isEmpty()) {
				// this causes content to be deleted when tempContent is cleared. Cloning may handle this side effect.
				content = tempContent;
			}
	 * ******************************************************************************
	 * 
	 */
	@Test
	public void filterContentBySubjectsTestNoContentMatches() {
		Content content = new Content();
		content.setId(1);
		content.setTitle("Test Content");
		content.setDescription("Test Description for content");
		content.setFormat("Code");
		content.setUrl("www.google.com");
		content.setDateCreated(1L);
		content.setLastModified(1L);

		Link link = new Link(1, content, new Module(), "affiliation 1");

		Set<Link> noLinks = new HashSet<Link>();

		Set<Link> links = new HashSet<Link>();
		links.add(link);

		Module mod1 = new Module(1, "no intersection", 1L, noLinks, new HashSet<ReqLink>(), new HashSet<Module>(),
				new HashSet<Module>());

		Module mod2 = new Module(2, "no intersection", 1L, links, new HashSet<ReqLink>(), new HashSet<Module>(),
				new HashSet<Module>());

		Module mod3 = new Module(3, "no intersection", 1L, links, new HashSet<ReqLink>(), new HashSet<Module>(),
				new HashSet<Module>());

		Module mod4 = new Module(4, "no intersection", 1L, links, new HashSet<ReqLink>(), new HashSet<Module>(),
				new HashSet<Module>());

		List<Integer> moduleIds = new ArrayList<Integer>();
		moduleIds.add(mod1.getId());
		moduleIds.add(mod2.getId());
		// moduleIds.add(mod3.getId());
		// moduleIds.add(mod4.getId());

		Mockito.when(msMock.getModuleById(mod1.getId())).thenReturn(mod1);
		Mockito.when(msMock.getModuleById(mod2.getId())).thenReturn(mod2);
		// Mockito.when(msMock.getModuleById(mod3.getId())).thenReturn(mod3);
		// Mockito.when(msMock.getModuleById(mod4.getId())).thenReturn(mod4);

		Set<Content> actual = ss.filterContentBySubjectIds(moduleIds);
		
		Assert.fail("SSImpl needs to be fixed. this test is built so that content has content but after the intersection with tempContent"+
				"it has no content but tempcontent.clear() clears content as side effect needs to be fixed");

	}

	/**
	 * Tests getContentByModuleId() Link Repository - findByModuleId() Content
	 * Repository - findById()
	 */
	@Test
	public void getContentByModuleIdTest() {
		Content contentJavaSoup = new Content(1, "title 1", "format", "something", "http://blah.com", 1L, 1L,
				new HashSet<Link>());
		Content contentJava = new Content(2, "title 2", "format", "something else", "http://blah2.com", 1L, 1L,
				new HashSet<Link>());

		Set<Content> expected = new HashSet<Content>();
		expected.add(contentJavaSoup);
		expected.add(contentJava);

		Link javaLink1 = new Link(2, contentJavaSoup, new Module(), "affiliation 2");
		Link javaLink2 = new Link(3, contentJava, new Module(), "affiliation 3");

		Set<Link> javaLinks = new HashSet<Link>();
		javaLinks.add(javaLink1);
		javaLinks.add(javaLink2);

		Module module1 = new Module(2, "Java", 1L, javaLinks, new HashSet<ReqLink>(), new HashSet<Module>(),
				new HashSet<Module>());

		// Given
		Mockito.when(mrMock.findById(module1.getId().intValue())).thenReturn(module1);

		// When
		Set<Content> actual = ss.getContentByModuleId(module1.getId());

		verify(mrMock).findById(module1.getId().intValue());
		Assert.assertEquals(actual, expected);
	}

	/**
	 * Tests filter() There are verifications: 1)verifies when fields are not null.
	 * 2)verifies when the format is null. 3)verifies when the title and format is
	 * null. Content Repository - findByFormat(), findByTitleContaining() Content
	 * Service - getAllContent() Link Repository - findByModuleIdIn()
	 */
	// @Test
	// public void filterTest() {
	// //Local Variables
	// String format = "format";
	// String titleContaining = "Test";
	// List<Integer> moduleIds = new ArrayList<Integer>();
	// moduleIds.add(100);

	// //When
	// Mockito.when(crMock.findByFormat(format)).thenReturn(contentSetMock);
	// Mockito.when(crMock.findByTitleContaining(titleContaining)).thenReturn(contentSetMock);
	// Mockito.when(csMock.getAllContent()).thenReturn(contentSetMock);
	// Mockito.when(lrMock.findByModuleIdIn(moduleIds)).thenReturn(linkSetMock);

	// //Given/Then Test 1 - fields are not null
	// ss.filter(titleContaining, format, moduleIds);

	// verify(crMock, times(2)).findByFormat(format);
	// verify(lrMock, times(1)).findByModuleIdIn(moduleIds);

	// //Given/Then Test 2 - format is null.
	// ss.filter(titleContaining, null, moduleIds);
	// verify(crMock, times(1)).findByTitleContaining(titleContaining);

	// //Given/Then Test 3 - title and format is null
	// ss.filter(null, null, moduleIds);
	// verify(csMock, times(1)).getAllContent();

	// }

}