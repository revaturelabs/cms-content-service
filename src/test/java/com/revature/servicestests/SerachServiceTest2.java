package com.revature.servicestests;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.ITestContext;
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
import com.revature.services.SearchService;
import com.revature.services.SearchServiceImpl;
import com.revature.testingutils.ContentFactory;

public class SerachServiceTest2 {
	
//	===Mock Injections===
	@Mock
	private ContentRepository crMock;
	@Mock
	private ModuleRepository mrMock;
	@Mock
	private LinkRepository lrMock;
	
	@InjectMocks
	private SearchService ssMock;
	
//	===Fields===
	//Content
	Content c1;
	Content c2;
	Set<Content> contentSet;
	Set<Content> contentSetFiltered;
	Set<Content> contentSetExpected;
	
	//Links
	Link l1;
	Set<Link> linkSet;
	
	//Modules
	Module m1;
	
//	===Tests===
	@BeforeClass
	public void setup() {
		
		//Create service object for testing.
		ssMock = new SearchServiceImpl();	

		//Create Content objects for testing
		Content content;
		this.contentSet = new HashSet<Content>();
		this.contentSetFiltered = new HashSet<Content>();
		
		//Content object #1
		content = ContentFactory.getContent();
		this.c1 = content;
		this.contentSet.add(content);
		this.contentSetFiltered.add(content);
		
		//Content object #2
		int newId = content.getId()+1;
		content.setId(newId);
		this.c2 = content;
		this.contentSet.add(content);
		
		//Create Link objects for testing
		//Link Set
		this.linkSet = new HashSet<Link>();
		//Link object #1
		this.l1 = new Link(100, 117, 130, "testblah");
		this.linkSet.add(this.l1);
		
		//Module object #1
		this.m1 = new Module(130, "TestSubject", 1, this.linkSet);
		
		//Enable Mock Annotations
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void filterContentByTitleTest() {
		//Given
		Mockito.when(crMock.findByTitle(this.c1.getTitle())).thenReturn(contentSetFiltered);
		
		//When
		this.contentSetExpected = ssMock.filterContentByTitle(c1.getTitle());
		//Retrieve the content from the returned set
		Iterator<Content> iter = contentSetExpected.iterator();
		Content contentExpected = (Content) iter.next();
		
		//Then
		assertTrue(c1.getTitle().equals(contentExpected.getTitle()));
	}
	
	@Test
	public void filterByFormatTest() {
		//Given
		Mockito.when(crMock.findByFormat(this.c1.getFormat())).thenReturn(contentSetFiltered);
		
		//When
		this.contentSetExpected = ssMock.filterContentByFormat(c1.getFormat());
		Iterator<Content> iter = contentSetExpected.iterator();
		Content contentExpected = (Content) iter.next();
		
		//Then
		assertTrue(this.c1.getId() == contentExpected.getId());
	}
	
//	@Test
//	public void filterByModuleId() {
//		//Given
//		Mockito.when(lrMock.findByModuleId(this.l1.getModuleId())).thenReturn(this.linkSet);
//		Mockito.when(crMock.findById(this.c1.getId())).thenReturn(contentSetExpected);
//		
//		//When
//		this.contentSetExpected = ssMock.getContentByModuleId(l1.getModuleId());
//		Iterator<Content> iter = contentSetExpected.iterator();
//		Content contentExpected = (Content) iter.next();
//		
//		//Then
//		assertTrue(contentExpected.getId() == c1.getId());
//	}
}
