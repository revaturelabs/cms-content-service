package com.revature.servicesTest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

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
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.entities.Content;
import com.revature.entities.Curriculum;
import com.revature.entities.CurriculumModule;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.repositories.RequestRepository;
import com.revature.services.ContentService;
import com.revature.services.CurriculumModuleService;
import com.revature.services.CurriculumService;
import com.revature.services.ModuleService;
import com.revature.services.RequestService;
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
	private RequestService rsMock;
	@Mock
	private RequestRepository rrMock;
	@Mock
	private CurriculumService crsMock;
	@Mock
	private CurriculumModuleService cmsMock;
	
	@InjectMocks
	private SearchService ss = new SearchServiceImpl();


    //	===Tests===
	@BeforeMethod
	public void reinitMocks() {
		//Enable mocks for TestNG
		MockitoAnnotations.initMocks(this);
		
	}
	@BeforeTest
	public void testSetup() {
	}

	/**
	 * This method tests {@link com.revature.services.SearchServiceImpl#filterContentByTitle(String) filterContentByTiyle(String)}.
	 * This Method assumes a String Title was passed in as an argument, and returns a Set of Content. 
	 * This Method Mocks the ContentRepository
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
	 * This method Tests {@link com.revature.services.SearchServiceImpl#filterContentByFormat(String) filterContentByFormat(String)}.
	 * This Method assumes a String Title was passed in as an argument, and returns a Set of Content. 
	 * This Method Mocks the ContentRepository.
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
	 * This method Tests {@link com.revature.services.SearchServiceImpl#filterContentByFormat(String) filterContentByFormat(String)}.
	 * This Method assumes format.equals("flagged"), and returns a Set of Content that has no format assigned to it. 
	 * This Method Mocks the ContentService.
	 */
	@Test
	public void filterContentByFormatFlaggedEmptyTest() {
		//Local Variables
		String format = "Flagged";
		Set<Content> contentSetExpected  = new HashSet<Content>();
		
		contentSetExpected.add(new Content(1, "title 1", "", "something", "http://blah.com",
				1L, 1L, new HashSet<Link>()));
		contentSetExpected.add(new Content(2, "title 2", "", "something else", "http://blah2.com",
				1L, 1L, new HashSet<Link>()));

		//Given
		Mockito.when(csMock.getAllContent()).thenReturn(contentSetExpected);
		
		//When
		//The filterContentByFormat() method should only return contents with empty formats
		Set<Content> actual = ss.filterContentByFormat(format);
		
		//then
		verify(csMock, atLeastOnce()).getAllContent();
		Assert.assertEquals(actual, contentSetExpected);
	}
	
	/**
	 * This method Tests {@link com.revature.services.SearchServiceImpl#filterContentByFormat(String) filterContentByFormat(String)}.
	 * This Method assumes format.equals("flagged") where no content has an empty format, and returns an empty Set of Content. 
	 * This Method Mocks the ContentService.
	 */
	@Test
	public void filterContentByFormatFlaggedNonEmptyTest() {
		//Local Variables
		String format = "Flagged";
		Set<Content> allContent  = new HashSet<Content>();
		Set<Content> emptyContentSetExpected  = new HashSet<Content>();
		
		//Add the empty format Content and a non-empty format Content to a set
		allContent.add(new Content(1, "title 1", "Document", "something", "http://blah.com",
				1L, 1L, new HashSet<Link>()));
		allContent.add(new Content(2, "title 2", "Code", "something else", "http://blah2.com",
				1L, 1L, new HashSet<Link>()));
		
		//Given
		//The getAllContent() method will return the allContent set
		Mockito.when(csMock.getAllContent()).thenReturn(allContent);
		
		//When
		//The filterContentByFormat() method should only return contents with empty formats (none in this case)
		Set<Content> actual = ss.filterContentByFormat(format);
		
		//then
		verify(csMock, atLeastOnce()).getAllContent();
		Assert.assertEquals(actual, emptyContentSetExpected);
	}


	/**
	 * This method Tests {@link com.revature.services.SearchServiceImpl#filterContentBySubjectIds(List) filterContentBySubjectIds(List<Integers>)}.
	 * This Method assumes a list of Integers representing moduleIDs is passed as an argument, and returns a set of Content.
	 * This Method Mocks the ModuleService
	 * 
	 * Note from Developer: Currently throws an IndexOutOfBounds Exception when you put in a ModuleId list with
	 * more than one number.
	 */
	@Test
	public void filterContentBySubjectIdsTest() {
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
	 * This method Tests {@link com.revature.services.SearchServiceImpl#getContentByModuleId(int) getContentByModuleId(int)}.
	 * This Method assumes an int representing a moduleID is passed as an argument, and returns a single Content.
	 * This Method Mocks the ModuleRepository.
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
	 * This method Tests {@link com.revature.services.SearchServiceImpl#filter(String, List, List) filter(String, List<String>, List<Integer>)}.
	 * This Method assumes a String Title and/or List of String Formats and/or a List Integer ModuleIds passed as an argument, and returns a set of Content.
	 * Any of the parameters can be empty.
	 * This Method Mocks the ContentRepository and the ContentService.
	 * 
	 * Notes are left for the developers, This Test and Its Method should be removed. The Over-ridden version of this test and its method
	 * can effectively replace these
	 */
	@Test()
	public void filterTest() {
		 List<Integer> moduleIds = new ArrayList<Integer>();

		Content content1 = new Content(1, "title 1", "format1", "something", "http://blah.com",
					1L, 1L, new HashSet<Link>());
		Content content2 = new Content(2, "title 2", "format2", "something else", "http://blah2.com",
					1L, 1L, new HashSet<Link>());

		Set<Content> expected = new HashSet<Content>();
		expected.add(content1);
		expected.add(content2);
		Set<Content> expected2 = new HashSet<Content>();
		expected2.add(content1);
		
		
		Module module = new Module();
		Link link = new Link(1, content1, module, "String", 1);
		Link link2 = new Link(2, content2, module, "String", 2);
		String format1 = "format1";
		String format2 = "format2";
		List<String> formats = new ArrayList<String>();
		formats.add(format1);
		formats.add(format2);
		
		//given
		Mockito.when(crMock.findByFormat("format1")).thenReturn(expected);
		Mockito.when(crMock.findByTitle("title 1")).thenReturn(expected2);
		Mockito.when(csMock.getAllContent()).thenReturn(expected);

		//when
		Set<Content> actualContent1 = ss.filter("title 1", formats, moduleIds);
		Set<Content> actualContent2 = ss.filter("", formats, moduleIds);

		verify(crMock).findByTitle("title 1");
		verify(crMock, times(2)).findByFormat(format1);
		verify(csMock,  times(2)).getAllContent();
		
		Assert.assertEquals(actualContent1, expected2);
		Assert.assertEquals(actualContent2, expected);

	}
	
	/**
	 * This method Tests {@link com.revature.services.SearchServiceImpl#filter(String, List, List) filter(String, List<String>, List<Integer>, List<Integer>)}.
	 * This Method assumes a String Title and/or List of String Formats and/or a List Integer ModuleIdsm and/or a list of Integer curriculaIds passed as an argument, and returns a set of Content.
	 * Any of the parameters can be empty.
	 * This Method Mocks the ContentRepository and the ContentService.
	 */
	@Test()
	public void filterTest_OverrideVersion() {
		 List<Integer> moduleIds = new ArrayList<Integer>();
		 List<Integer> curriculaIds = new ArrayList<Integer>();

		Content content1 = new Content(1, "title 1", "format1", "something", "http://blah.com",
					1L, 1L, new HashSet<Link>());
		Content content2 = new Content(2, "title 2", "format2", "something else", "http://blah2.com",
					1L, 1L, new HashSet<Link>());
		Curriculum curriculum = new Curriculum(1, "testCurriculum");
		

		Set<Content> expected = new HashSet<Content>();
		expected.add(content1);
		expected.add(content2);
		Set<Content> expected2 = new HashSet<Content>();
		expected2.add(content1);
		
		
		Module module = new Module();
		Link link = new Link(1, content1, module, "String", 1);
		Link link2 = new Link(2, content2, module, "String", 2);
		String format1 = "format1";
		String format2 = "format2";
		List<String> formats = new ArrayList<String>();
		formats.add(format1);
		formats.add(format2);
		
		//given
		Mockito.when(crMock.findByFormat("format1")).thenReturn(expected);
		Mockito.when(crMock.findByTitle("title 1")).thenReturn(expected2);
		Mockito.when(csMock.getAllContent()).thenReturn(expected);
		Mockito.when(crsMock.getCurriculumById(anyInt())).thenReturn(curriculum);


		//when
		Set<Content> actualContent1 = ss.filter("title 1", formats, moduleIds, curriculaIds);
		Set<Content> actualContent2 = ss.filter("", formats, moduleIds, curriculaIds);

		verify(crMock).findByTitle("title 1");
		verify(crMock, times(2)).findByFormat(format1);
		verify(csMock,  times(2)).getAllContent();
		
		Assert.assertEquals(actualContent1, expected2);
		Assert.assertEquals(actualContent2, expected);

	}
	
	/**
	 * This method tests {@link com.revature.services.SearchServiceImpl#filterContentByCurriculaIds(List) filterContentByCurriculaIds(List<Integer>)}.
	 * This method assumes a List of Integers and returns a set of Content after filtering by curriculaIds
	 * This Method Mocks the curriculumModulesService, and CurriculumService
	 */
	@Test()
	public void TestfilterContentByCurriculaIds() {
		List<Integer> curriculaIds = new ArrayList<Integer>();
		Set<CurriculumModule> cm = new HashSet<CurriculumModule>();
		curriculaIds.add(1);
		
		Curriculum curriculum = new Curriculum(1, "curriculumTest");
		
		//given
		Mockito.when(cmsMock.getAllCurriculumModules()).thenReturn(cm);
		Mockito.when(crsMock.getCurriculumById(anyInt())).thenReturn(curriculum);
		
		//When
		Set<Content> actualContent = ss.filterContentByCurriculaIds(curriculaIds);
		
		verify(cmsMock).getAllCurriculumModules();
		verify(crsMock).getCurriculumById(anyInt());
		
		assertNotNull(actualContent);

	}
	
	
	/**
	 * This method Tests {@link com.revature.services.SearchServiceImpl#filterContent(Set, java.util.Map) filterContent(Set<Content>, Map<String, Object>)}.
	 * This Method assumes a Set of content and a Map of string-Object pairs is passed as an argument, and returns a set of Content.
	 */
	@Test()
	public void filterContentTest() {
		Set<Content> contents = new HashSet<Content>();
		Map<String, Object> filters = new HashMap<String, Object>();
		Set<Link> links = new HashSet<Link>();

		
		Content content = new Content(1, "titleTest", "formatTest", null, null, 0, 0, null );
		Module module = new Module(1, null, 0, links, null, null, null);
		Link link = new Link(1, content, module, null, 0);
		content.setLinks(links);
		contents.add(content);
		links.add(link);
		
		
		filters.put("title", "titleTest");
		filters.put("format", "formatTest");
		filters.put("modules", "");
		
		//when
		Set<Content> actualContent = ss.filterContent(contents, filters);
		
		assertNotNull(actualContent);
		assertEquals(contents, actualContent);
		
	}
	
	/**
	 * This method Tests {@link com.revature.services.SearchServiceImpl#filterRequestByTitle(String) filterRequestByTitle(String)}.
	 * This Method assumes a String Title and/or List of String Formats and/or a List Integer ModuleIdsm and/or a list of Integer curriculaIds passed as an argument, and returns a set of Content.
	 * Any of the parameters can be empty.
	 * This Method Mocks the RequestRepository
	 */
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
	
		/**
	 	* This method Tests {@link com.revature.services.SearchServiceImpl#filterRequestByFormat(String) filterRequestByFormat(String)}.
	 	* This Method assumes a String Title and/or List of String Formats and/or a List Integer ModuleIdsm and/or a list of Integer curriculaIds passed as an argument, and returns a set of Content.
	 	* Any of the parameters can be empty.
	 	* This Method Mocks the RequestRepository
	 	*/
		@Test
		public void filterRequestByFormatTest(){
			String format = "format";
			Set<ReqLink> reqLinks = new HashSet<ReqLink>();
			Content content = new Content();
			Long dateCreated = 20180201110400L;
			Long lastModified = 20180201110400L;
			Set<Request> requestSetExpected = new HashSet<Request>();
			requestSetExpected.add(new Request(1, "title", format, "description", content, dateCreated, lastModified, reqLinks));
			requestSetExpected.add(new Request(2, "title", format, "description", content, dateCreated, lastModified, reqLinks));
		
			Mockito.when(rrMock.findByFormat(format)).thenReturn(requestSetExpected);
			
			Set<Request> actual = ss.filterRequestByFormat(format);
			
			verify(rrMock, times(1)).findByFormat(format);
		    Assert.assertEquals(actual, requestSetExpected);
		}
		
		/**
	 	* This method Tests {@link com.revature.services.SearchServiceImpl#filterRequestBySubjectIds(List) filterRequestsBySubjectIds(List<Integer>)}.
	 	* This Method assumes a List of Integer SubjectIds is passed as an argument, and returns a set of Requests.
	 	* This Method Mocks the ModuleService
	 	*/
		@Test
		public void filterRequestBySubjectIdsTest() {
			List<Integer> moduleIds = new ArrayList();
			moduleIds.add(1);
			String format = "format";
			Content content = new Content();
			Long dateCreated = 20180201110400L;
			Long lastModified = 20180201110400L;
			Set<ReqLink> reqLinks1 = new HashSet<ReqLink>();
			Set<Request> requestSetExpected = new HashSet<Request>();
			Request r1 = new Request(1, "title", format, "description", content, dateCreated, lastModified, reqLinks1);
			requestSetExpected.add(r1);
			
			Set<ReqLink> reqLinksForM1 = new HashSet<ReqLink>();
			ReqLink rl1 = new ReqLink(1, new Request(1, "title", format, "description", content, dateCreated, lastModified, reqLinks1), new Module(1, "", 1L, null, null, null, null), "affiliation 1");
			ReqLink rl2 = new ReqLink(2, new Request(1, "title", format, "description", content, dateCreated, lastModified, reqLinks1), new Module(1, "", 1L, null, null, null, null), "affiliation 1");
			reqLinksForM1.add(rl1);
			reqLinksForM1.add(rl2);
			
			Module m1 = new Module(1, "", 1L, null, reqLinksForM1, null, null);
			Mockito.when(msMock.getModuleById(1)).thenReturn(m1);
			
			Set<Request> actual = ss.filterRequestBySubjectIds(moduleIds);
			verify(msMock, times(1)).getModuleById(1);
		    Assert.assertEquals(actual, requestSetExpected);
		}
				
		/**
		 * This Method tests {@link com.revature.services.SearchServiceImpl#filterReq(String, List, List) filter request method}
		 * This method assumes a string title and/Or list of Strings formatList and/or list of integer moduleIds, and returns a list of requests.
		 * This Method mocks the RequestRepositroy, RequestService, and ModuleService.
		 */
		@Test
		public void filterReqTest() {
			String title = "title"; 
			String format1 = "format1";
			String format2 = "format2";
			List<String> formats = new ArrayList<String>();
			formats.add(format1);
			formats.add(format2);

			List<Integer> moduleIds = new ArrayList();
			
			Content content = new Content();
			Long dateCreated = 1L;
			Long lastModified = 1L;
			Set<ReqLink> reqLinks1 = new HashSet<ReqLink>();
			Set<Request> requestSetExpected = new HashSet<Request>();
			Request r1 = new Request(1, "title", format1, "description", content, dateCreated, lastModified, reqLinks1);
			Request r2 = new Request(2, "title", format2, "description", content, dateCreated, lastModified, reqLinks1);
			requestSetExpected.add(r1);
			requestSetExpected.add(r2);
			
			Mockito.when(rsMock.getAllRequests()).thenReturn(requestSetExpected);
			
			//Mocking filterRequestByTitle
			Mockito.when(rrMock.findByTitle(title)).thenReturn(requestSetExpected);
			//Mocking filterRequestByFormat
			Mockito.when(rrMock.findByFormat(format1)).thenReturn(requestSetExpected);
			//Mocking filterRequestBySubjectIds
			Set<ReqLink> reqLinksForM1 = new HashSet<ReqLink>();
			ReqLink rl1 = new ReqLink(1, new Request(1, "title", format1, "description", content, dateCreated, lastModified, reqLinks1), new Module(1, "", 1L, null, null, null, null), "affiliation 1");
			ReqLink rl2 = new ReqLink(2, new Request(1, "title", format2, "description", content, dateCreated, lastModified, reqLinks1), new Module(1, "", 1L, null, null, null, null), "affiliation 1");
			reqLinksForM1.add(rl1);
			reqLinksForM1.add(rl2);
			
			Module m1 = new Module(1, "", 1L, null, reqLinksForM1, null, null);
			Mockito.when(msMock.getModuleById(1)).thenReturn(m1);
			
			
			Set<Request> actual = ss.filterReq(title, formats, moduleIds);
			verify(rsMock, times(1)).getAllRequests();
			verify(rrMock, times(1)).findByTitle(title);
			verify(rrMock, times(1)).findByFormat(format1);
		    Assert.assertEquals(actual, requestSetExpected);
		}
	
}