package com.revature.servicesTest;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Request;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;
import com.revature.services.LinkService;
import com.revature.services.LinkServiceImpl;
import com.revature.services.SearchService;

public class LinkServiceTest {
	
	@Mock
	LinkRepository lrMock;
	
	@Mock
	ContentRepository crMock;

	@Mock
	SearchService ssMock;
	
	@InjectMocks
	private LinkService linkService = new LinkServiceImpl();
	
	@BeforeClass
	public void mock() {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * This Method tests {@link com.revature.services.LinkServiceImpl#createLink(Link) createLink(Link)}
	 * it assumes a Link object is passed as an argument, then it persists the content object thats inside the link object then returns the newly persisted Link Object
	 * This Method Mocks ContentRepository, and Link Repository
	 */
	@Test
	public void testCreateLink() {
		Link link = new Link();
		
		Mockito.when(crMock.save(link.getContent())).thenReturn(link.getContent());
		Mockito.when(lrMock.save(link)).thenReturn(link);
		
		Link actual = linkService.createLink(link);
		
		assertEquals(actual, link);
	}
	
	/**
	 * This Method tests {@link com.revature.services.LinkServiceImpl#getAllLinks() getAllLinks()}
	 * it returns all Link objects
	 * This Method Mocks Link Repository
	 */
	@Test
	public void testgGetAllLinks() {
		Set<Link> link = new HashSet<Link>();
		
		Mockito.when(lrMock.findAll()).thenReturn(link);
		
		Set<Link> actual = linkService.getAllLinks();
		
		assertEquals(actual, link);
	}
	
	/**
	 * This Method tests {@link com.revature.services.LinkServiceImpl#getLinkById(int) getLinkById(int)}
	 * it assumes an int id is passed as an argument and it returns a Link object
	 * This Method Mocks Link Repository
	 */
	@Test
	public void testgGetLinkById() {
		Link link = new Link();
		
		Mockito.when(lrMock.findById(anyInt())).thenReturn(link);
		
		Link actual = linkService.getLinkById(anyInt());
		
		assertEquals(actual, link);
	}
	
	/**
	 * This Method tests {@link com.revature.services.LinkServiceImpl#createLink(Link) createLink(Link)}
	 * it assumes a Link object is passed as an argument, then it persists the content object thats inside the link object then returns the newly persisted Link Object
	 * This Method Mocks Link Repository
	 */
	@Test
	public void testUpdateLink() {
		Link link = new Link();
		
		Mockito.when(lrMock.save(link)).thenReturn(link);
		
		Link actual = linkService.updateLink(link);
		
		assertEquals(actual, link);
	}
	
	/**
	 * This Method tests {@link com.revature.services.LinkServiceImpl#deleteLinkById(int) deleteLinkById(int)}
	 * it assumes an int Id is passed as an argument, then it deletes the Link Object associated with the Id.
	 * This Method Mocks and Link Repository
	 */
	@Test
	public void testDeleteLinkById() {
		Link link = new Link();
		
		Mockito.when(lrMock.findById(anyInt())).thenReturn(link);
		linkService.deleteLinkById(anyInt());
		
		verify(lrMock).delete(link);
	}

	/**
	 * this method tests {@link com.revature.services.LinkService#filter(String, String, ArrayList) filter(String, String, List<Integer>) .}
	 * this method assumes a String title, String format, and a List of Integer moduleIds are passed as an argument and returns a set of sets of filtered Link objects. 
	 * This method Mocks LinkRepository and SearchService
	 */
	@Test
	public void testFilter() {
		ArrayList<Integer> moduleIds = new ArrayList<Integer>();
		List<String> formats = new ArrayList<String>();
		Set<Link> links = new HashSet<Link>();
		Set<Content> contents = new HashSet<Content>();
		Set<Set<Link>> setOfSetOfLinks = new HashSet<Set<Link>>();
		Content content = new Content();
		Link link = new Link();

		contents.add(content);
		formats.add("format");
		moduleIds.add(1);
		links.add(link);
		setOfSetOfLinks.add(links);
		
		Mockito.when(ssMock.filter("title", formats, moduleIds)).thenReturn(contents);
		Mockito.when(lrMock.findByContentId(anyInt())).thenReturn(links);

		Set<Set<Link>> actual = linkService.filter("title", "format", moduleIds);
		
		assertEquals(actual, setOfSetOfLinks);		
	}
}
