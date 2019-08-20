package com.revature.servicestests;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.services.ContentService;
import com.revature.services.SearchService;
import com.revature.services.SearchServiceImpl;

public class SearchServiceTest {
	
//	===Mock Injections===
	@Mock
	private ContentRepository crMock;
	@Mock
	private ModuleRepository mrMock;
	@Mock
	private LinkRepository lrMock;
	@Mock
	private ContentService csMock;
	
	@InjectMocks
	private SearchService ss = new SearchServiceImpl();

//	===Fields===
	Link linkMock;
	Set<Link> linkSetMock = new HashSet<Link>();
	
	Content contentMock;
	Set<Content> contentSetMock = new HashSet<Content>();
	
	Module moduleMock;
	
//	===Tests===
	@BeforeClass
	public void setup() {
		//Enable mocks for TestNG
		MockitoAnnotations.initMocks(this);
		
	}
	@BeforeTest
	public void testSetup() {
		//Link Objects and Set
		//Constructor (ID, ContentID, ModuleID, "Affiliation")
		Link link;
		link = new Link(1,50,100,"link-affiliation");
		linkSetMock.add(link);
		link = new Link(2,51,100,"link-affiliation2");
		linkSetMock.add(link);
		link = new Link(3,52,101,"link-affiliation3");
		linkSetMock.add(link);
		this.linkMock = link;
		
		//Content Objects and Set
		//Constructor (ID, "Title", "Format", "Desc", "URL", Set<Link>, DateCreated, DateModified)
		Content content;
		content = new Content(50, "Test 1", "format", "Test Content #1", 
				"www.example.com", this.linkSetMock, 1L, 1L );
		this.contentSetMock.add(content);
		content = new Content(51, "Test 2", "format", "Test Content #3", 
				"www.example.com", this.linkSetMock, 1L, 1L );
		this.contentSetMock.add(content);
		content = new Content(52, "Test 3", "format", "Test Content #3", 
				"www.example.com", this.linkSetMock, 1L, 1L );
		this.contentSetMock.add(content);
		this.contentMock = content;
		//Module
		//Constructor (Id, "Subject", DateCreated, Set<Link>)
		this.moduleMock = new Module(100, "Test Subject 1", 1L, this.linkSetMock);
	}
	
	@AfterTest
	public void tearDown() {
		this.contentSetMock = null;
		this.contentMock = null;
		this.linkSetMock = null;
		this.linkMock = null;
		this.moduleMock = null;
	}
	
	/**
	 * Tests filterContentById()
	 * Content Repository - findByTitle(String title)
	 */
	@Test
	public void filterContentByTitleTest() {
		
		//Local Variables
		String title = "Test Subject 3";
		Set<Content> contentSetExpected  = new HashSet<Content>();
		contentSetExpected.add(this.contentMock);
		
		//Given
		Mockito.when(crMock.findByTitle(title)).thenReturn(contentSetExpected);
		
		//When
		ss.filterContentByTitle(title);
		
		//then
		verify(crMock, times(1)).findByTitle(title);
	}
	
	/**
	 * Tests filterContentByFormat()
	 * Content Repository - findByFormat(String format)
	 */
	@Test
	public void filterContentByFormatTest() {
		//Local Variables
		String format = "format";
		
		//Given
		Mockito.when(crMock.findByFormat(format)).thenReturn(contentSetMock);
		
		//When
		ss.filterContentByFormat(format);
		
		//then
		verify(crMock, times(1)).findByFormat(format);
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
		//Local Variables
		List<Integer> moduleIds = new ArrayList<Integer>();
		
		moduleIds.add(100);
		//Given
		Mockito.when(lrMock.findByModuleId(Mockito.anyInt())).thenReturn(this.linkSetMock);
		Mockito.when(crMock.findAllById(Mockito.any())).thenReturn(this.contentSetMock);
		
		//When 
		ss.filterContentBySubjects(moduleIds);
		verify(lrMock).findByModuleId(Mockito.anyInt());
		verify(crMock).findAllById(Mockito.any());
	}
	
	/**
	 * Tests getContentByModuleId()
	 * Link Repository - findByModuleId()
	 * Content Repository - findById()
	 */
	@Test
	public void getContentByModuleIdTest() {
		//Local Variables
		int moduleId = 100;
		//Given
		Mockito.when(lrMock.findByModuleId(moduleId)).thenReturn(linkSetMock);
		Mockito.when(crMock.findById(Mockito.anyInt())).thenReturn(this.contentSetMock);
		
		//When
		ss.getContentByModuleId(moduleId);
		
		//Then
		verify(lrMock, times(2)).findByModuleId(moduleId);
		verify(crMock, times(1)).findById(Mockito.anyInt());
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
	@Test
	public void filterTest() {
		//Local Variables
		String format = "format";
		String titleContaining = "Test";
		List<Integer> moduleIds = new ArrayList<Integer>();
		moduleIds.add(100);
		
		
		//When
		Mockito.when(crMock.findByFormat(format)).thenReturn(contentSetMock);
		Mockito.when(crMock.findByTitleContaining(titleContaining)).thenReturn(contentSetMock);
		Mockito.when(csMock.getAllContent()).thenReturn(contentSetMock);
		Mockito.when(lrMock.findByModuleIdIn(moduleIds)).thenReturn(linkSetMock);
		
		//Given/Then Test 1 - fields are not null
		ss.filter(titleContaining, format, moduleIds);
		
		verify(crMock, times(2)).findByFormat(format);
		verify(lrMock, times(1)).findByModuleIdIn(moduleIds);
		
		//Given/Then Test 2 - format is null.
		ss.filter(titleContaining, null, moduleIds);
		verify(crMock, times(1)).findByTitleContaining(titleContaining);
		
		//Given/Then Test 3 - title and format is null
		ss.filter(null, null, moduleIds);
		verify(csMock, times(1)).getAllContent();
	}
	

}