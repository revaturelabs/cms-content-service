package com.revature.servicestests;

import static org.mockito.ArgumentMatchers.anyInt;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.repositories.RequestRepository;
import com.revature.services.ContentService;
import com.revature.services.ModuleService;
import com.revature.services.SearchService;
import com.revature.services.SearchServiceImpl;

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
	
	@Mock
	private RequestRepository rrMock;
	
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
	 * Tests filterContentBySubjects()
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

		Link soupLink = new Link(1, contentJavaSoup, new Module(), "affiliation 1");
		Link javaLink1 = new Link(2, contentJavaSoup, new Module(), "affiliation 2");
		Link javaLink2 = new Link(3, contentJava, new Module(), "affiliation 3");

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

		Link javaLink1 = new Link(2, contentJavaSoup, new Module(), "affiliation 2");
		Link javaLink2 = new Link(3, contentJava, new Module(), "affiliation 3");

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
	
	//Complete - Author: Carlos
	@Test()
	public void filterTest() {
		 List<Integer> moduleIds = new ArrayList<Integer>();

		Content content1 = new Content(1, "title 1", "format", "something", "http://blah.com",
					1L, 1L, new HashSet<Link>());
		Content content2 = new Content(2, "title 2", "format", "something else", "http://blah2.com",
					1L, 1L, new HashSet<Link>());

		Set<Content> expected = new HashSet<Content>();
		expected.add(content1);
		expected.add(content2);
		Set<Content> expected2 = new HashSet<Content>();
		expected2.add(content1);
		List<Integer> ints = new ArrayList<Integer>();
		
		Module module = new Module();
		Link link = new Link(1, content1, module, "String");
		Link link2 = new Link(2, content2, module, "String");

		
		//given
		Mockito.when(crMock.findByFormat("format")).thenReturn(expected);
		Mockito.when(crMock.findByTitle("title 1")).thenReturn(expected2);
		Mockito.when(csMock.getAllContent()).thenReturn(expected);

		//when
		Set<Content> actualContent1 = ss.filter("title 1", "format", moduleIds);

		verify(crMock).findByTitle("title 1");
		verify(crMock).findByFormat("format");
		verify(csMock).getAllContent();
		
		Assert.assertEquals(actualContent1, expected2);
	}
	
	
	//Incomplete - Author: Carlos. Work In Progress
	@Test(enabled = false)
	public void filterContentTest() {
		String title = "title";
		String format = "format";
		String description = "description";
		Content content = new Content();
		Long dateCreated =  (long) 123;
		Long lastModified = (long) 12;
		Set<ReqLink> reqLinks = new HashSet<ReqLink>();
		
		Set<Request> RequestSetExpected = new HashSet<Request>();
		//Local Variable. Expected Set
		RequestSetExpected.add(new Request(1, title, format, description, content,dateCreated, lastModified, reqLinks));
		RequestSetExpected.add(new Request(2, title, format, description, content,dateCreated, lastModified, reqLinks));
	}

	//Complete test - Author: Carlos.
	@Test
	public void filterRequestByTitleTest() {
		String title = "title";
		String format = "format";
		String description = "description";
		Content content = new Content();
		Long dateCreated = (long) 123;
		Long lastModified = (long) 10;
		Set<ReqLink> reqLinks = new HashSet<ReqLink>();
		
		Set<Request> requestSetExpected = new HashSet<Request>();
		//Local Variable. Expected Set
		requestSetExpected.add(new Request(1, title, format, description, content,dateCreated, lastModified, reqLinks));
		requestSetExpected.add(new Request(2, title, format, description, content,dateCreated, lastModified, reqLinks));
		
		//given
		Mockito.when(rrMock.findByTitle(title)).thenReturn(requestSetExpected);
	
		//when
		Set<Request> actual = ss.filterRequestByTitle(title);
		
		verify(rrMock).findByTitle(title);
		Assert.assertEquals(actual, requestSetExpected);

	}
	
	
}