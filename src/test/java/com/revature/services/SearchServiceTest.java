package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.entities.Link;
import com.revature.entities.ReqLink;
import com.revature.repositories.LinkRepository;
import com.revature.services.ModuleService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.*;

import com.revature.entities.Content;
import com.revature.entities.Module;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.services.ContentService;
import com.revature.services.SearchService;
import com.revature.services.SearchServiceImpl;

import static org.mockito.Mockito.*;

public class SearchServiceTest {
	//Any time that two nulls appear in a test of a constructor, that is for a feature that was created after the tests were created to allow them to pass.
	
    //	===Mock Injections===
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
	private SearchService ss = new SearchServiceImpl();

    //	===Fields===
	// Link linkMock;
	// Set<Link> linkSetMock = new HashSet<Link>();

	// Content contentMock;
	// Set<Content> contentSetMock = new HashSet<Content>();

	// Module moduleMock;
	
    //	===Tests===
	@BeforeMethod
	public void reinitMocks() {
		//Enable mocks for TestNG
		MockitoAnnotations.initMocks(this);
		
	}
	@BeforeTest
	public void testSetup() {
		// Link link;
		// Content content;

		// content = new Content(50, "Test 1", "format", "Test Content #1",
		// 		"www.example.com", linkSetMock, 1L, 1L, );
		// link = new Link(1,content,100,"link-affiliation");
		// linkSetMock.add(link);
		// contentSetMock.add(content);

		// content = new Content(51, "Test 2", "format", "Test Content #3",
		// 		"www.example.com", linkSetMock, 1L, 1L );
		// link = new Link(2,content,100,"lonk-affiliation");
		// linkSetMock.add(link);
		// contentSetMock.add(content);

		// content = new Content(52, "Test 3", "format", "Test Content #3",
		// 		"www.example.com", linkSetMock, 1L, 1L );
		// link = new Link(3,content,101,"lank-affiliation");
		// linkSetMock.add(link);
		// contentSetMock.add(content);

		// linkMock = link;
		// contentMock = content;

		// //Module
		// //Constructor (Id, "Subject", DateCreated, Set<Link>)
		// this.moduleMock = new Module(100, "Test Subject 1", 1L, this.linkSetMock, null, null);

	}

	/**
	 * Tests filterContentById()
	 * Content Repository - findByTitle(String title)
	 */
	@Test
	public void filterContentByTitleTest() {
		String title = "Test Subject 3";

		//Local Variables
		Set<Content> contentSetExpected  = new HashSet<Content>();
		contentSetExpected.add(new Content(1, title, "code", "something", "http://blah.com",
			1L, 1L, new HashSet<Link>()));
		contentSetExpected.add(new Content(2, title, "code", "something else", "http://blah2.com",
				1L, 1L, new HashSet<Link>()));

		//Given
		Mockito.when(crMock.findByTitle(title)).thenReturn(contentSetExpected);
		
		//When
		Set<Content> actual = ss.filterContentByTitle(title);
		
		//then
		verify(crMock, times(1)).findByTitle(title);
		Assert.assertEquals(actual, contentSetExpected);
	}
	
	/**
	 * Tests filterContentByFormat()
	 * Content Repository - findByFormat(String format)
	 */
	@Test
	public void filterContentByFormatTest() {
		//Local Variables
		String format = "format";
		Set<Content> contentSetExpected  = new HashSet<Content>();
		contentSetExpected.add(new Content(1, "title 1", format, "something", "http://blah.com",
				1L, 1L, new HashSet<Link>()));
		contentSetExpected.add(new Content(2, "title 2", format, "something else", "http://blah2.com",
				1L, 1L, new HashSet<Link>()));

		//Given
		Mockito.when(crMock.findByFormat(format)).thenReturn(contentSetExpected);
		
		//When
		Set<Content> actual = ss.filterContentByFormat(format);
		
		//then
		verify(crMock, times(1)).findByFormat(format);
		Assert.assertEquals(actual, contentSetExpected);
	}
	
	/**
	 * Tests fliterContentBySubjects()
	 * Link Repository - findByModuleID()
	 * Content Repository - findAllById()
	 * Currently throws an IndexOutOfBounds Exception when you put in a ModuleId list with
	 * more than one number.
	 */
	@Test
	public void filterContentBySubjectsTest() {
		Content contentJavaSoup = new Content(1, "title 1", "format", "something", "http://blah.com",
				1L, 1L, new HashSet<Link>());
		Content contentJava = new Content(2, "title 2", "format", "something else", "http://blah2.com",
				1L, 1L, new HashSet<Link>());

		Set<Content> expected = new HashSet<Content>();
		expected.add(contentJavaSoup);

		Link soupLink = new Link(1, contentJavaSoup, new Module(), "affiliation 1",0);
		Link javaLink1 = new Link(2, contentJavaSoup, new Module(), "affiliation 2",0);
		Link javaLink2 = new Link(3, contentJava, new Module(), "affiliation 3",0);

		Set<Link> soupLinks = new HashSet<Link>();
		soupLinks.add(soupLink);

		Module module1 = new Module(1, "Soup", 1L, soupLinks, new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		Set<Link> javaLinks = new HashSet<Link>();
		javaLinks.add(javaLink1);
		javaLinks.add(javaLink2);

		Module module2 = new Module(2, "Java", 1L, javaLinks, new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		List<Integer> moduleIds = new ArrayList<Integer>();
		moduleIds.add(module1.getId());
		moduleIds.add(module2.getId());

		//Given
		Mockito.when(msMock.getModuleById(module1.getId())).thenReturn(module1);
		Mockito.when(msMock.getModuleById(module2.getId())).thenReturn(module2);

		//When 
		Set<Content> actual = ss.filterContentBySubjectIds(moduleIds);

		verify(msMock, times(moduleIds.size())).getModuleById(anyInt());
		Assert.assertEquals(actual, expected);
	}
	
	/**
	 * Tests getContentByModuleId()
	 * Link Repository - findByModuleId()
	 * Content Repository - findById()
	 */
	@Test
	public void getContentByModuleIdTest() {
		Content contentJavaSoup = new Content(1, "title 1", "format", "something", "http://blah.com",
				1L, 1L, new HashSet<Link>());
		Content contentJava = new Content(2, "title 2", "format", "something else", "http://blah2.com",
				1L, 1L, new HashSet<Link>());

		Set<Content> expected = new HashSet<Content>();
		expected.add(contentJavaSoup);
		expected.add(contentJava);


		Link javaLink1 = new Link(2, contentJavaSoup, new Module(), "affiliation 2",0);
		Link javaLink2 = new Link(3, contentJava, new Module(), "affiliation 3",1);

		Set<Link> javaLinks = new HashSet<Link>();
		javaLinks.add(javaLink1);
		javaLinks.add(javaLink2);

		Module module1 = new Module(2, "Java", 1L, javaLinks, new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		//Given
		Mockito.when(mrMock.findById(module1.getId().intValue())).thenReturn(module1);

		//When
		Set<Content> actual = ss.getContentByModuleId(module1.getId());

		verify(mrMock).findById(module1.getId().intValue());
		Assert.assertEquals(actual, expected);
	}
	
	/**
	 * Tests filter()
	 * There are verifications:
	 * 1)verifies when fields are not null.
	 * 2)verifies when the format is null.
	 * 3)verifies when the title and format is null.
	 * Content Repository - findByFormat(), findByTitleContaining()
	 * Content Service - getAllContent()
	 * Link Repository - findByModuleIdIn() 
	 */
	// @Test
	// public void filterTest() {
	// 	//Local Variables
	// 	String format = "format";
	// 	String titleContaining = "Test";
	// 	List<Integer> moduleIds = new ArrayList<Integer>();
	// 	moduleIds.add(100);


	// 	//When
	// 	Mockito.when(crMock.findByFormat(format)).thenReturn(contentSetMock);
	// 	Mockito.when(crMock.findByTitleContaining(titleContaining)).thenReturn(contentSetMock);
	// 	Mockito.when(csMock.getAllContent()).thenReturn(contentSetMock);
	// 	Mockito.when(lrMock.findByModuleIdIn(moduleIds)).thenReturn(linkSetMock);

	// 	//Given/Then Test 1 - fields are not null
	// 	ss.filter(titleContaining, format, moduleIds);

	// 	verify(crMock, times(2)).findByFormat(format);
	// 	verify(lrMock, times(1)).findByModuleIdIn(moduleIds);

	// 	//Given/Then Test 2 - format is null.
	// 	ss.filter(titleContaining, null, moduleIds);
	// 	verify(crMock, times(1)).findByTitleContaining(titleContaining);

	// 	//Given/Then Test 3 - title and format is null
	// 	ss.filter(null, null, moduleIds);
	// 	verify(csMock, times(1)).getAllContent();

	// }
}