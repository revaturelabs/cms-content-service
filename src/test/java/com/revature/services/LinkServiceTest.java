package com.revature.services;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;
import com.revature.services.LinkServiceImpl;
import com.revature.services.SearchService;

/**
 * Testing for the LinkService class.
 * 
 * @author fjgiv
 * @version 1.0
 */
@SpringBootTest(classes = CMSforceApplication.class)

public class LinkServiceTest extends AbstractTestNGSpringContextTests {
	
	@Mock
	LinkRepository linkRep;
	
	@Mock
	ContentRepository contRep;

	@Mock
	SearchService searchServe;
	
	@InjectMocks
	LinkServiceImpl linkServe;

	
	Link link1;
	Link link2;
	Set<Link> links;
	Content content;
	Module module;
	
	@BeforeClass
	public void setupMocks() {
		MockitoAnnotations.initMocks(this);
		
		module = new Module();
		module.setChildren(null);
		module.setCreated(1L);
		module.setId(25);
		module.setParents(null);
		module.setSubject("Test");
		
		content = new Content();
		content.setId(1);
		content.setTitle("Test Content");
		content.setDescription("Test Description for content");
		content.setFormat("Code");
		content.setUrl("www.google.com");
		content.setDateCreated(1L);
		content.setLastModified(1L);
		
		link1 = new Link();
		link1.setId(1);
		link1.setModule(module);
		link1.setContent(content);
		link1.setAffiliation("Test Link 1");
		
		link2 = new Link();
		link2.setId(1);
		link2.setModule(module);
		link2.setContent(content);
		link2.setAffiliation("Test Link 2");
		
		links = new HashSet<>();
		links.add(link1);
		links.add(link2);
		
		module.setLinks(links);
		content.setLinks(links);
		
	}
	
	@Test
	public void testCreateLink() {
		
		//link.setContent(cr.save(link.getContent()));
		when(contRep.save(link1.getContent())).thenReturn(content);
		//return lr.save(link);
		Link linkExpected = link1;
		when(linkRep.save(link1)).thenReturn(linkExpected);
		
		Link actualLink = linkServe.createLink(link1);
		
		verify(contRep).save(link1.getContent());
		verify(linkRep).save(link1);
		
		reset(contRep);
		reset(linkRep);
		assertEquals(linkExpected, actualLink);
		
	}
	
	
	@Test
	public void testGetAllLinksWhenSetContainsZeroElements() {

//		Set<Link> links = new HashSet<Link>();
//		lr.findAll().forEach(links::add);
		
		Set<Link> linksExpected = new HashSet<>();
		when(linkRep.findAll()).thenReturn(linksExpected);
		
		Set<Link> actualLinks = linkServe.getAllLinks();
		
		verify(linkRep).findAll();
		reset(linkRep);
		assertEquals(linksExpected, actualLinks);
		
//		return links;
		
	}
	
	@Test
	public void testGetAllLinksWhenSetContainsElements() {

//		Set<Link> links = new HashSet<Link>();
//		lr.findAll().forEach(links::add);
		Set<Link> linksExpected = links;
		when(linkRep.findAll()).thenReturn(linksExpected);
		
		Set<Link> actualLinks = linkServe.getAllLinks();
		
		verify(linkRep).findAll();
		reset(linkRep);
		assertEquals(linksExpected, actualLinks);
//		return links;
		
	
	}
	
	
	@Test
	public void testGetLinkById() {
		
		//return lr.findById(id);
		
		Link linkExpected = link1;
		int dummyId = 1;
		
		when(linkRep.findById(dummyId)).thenReturn(linkExpected);
		
		Link actualLink = linkServe.getLinkById(dummyId);
		
		verify(linkRep).findById(dummyId);
		reset(linkRep);
		assertEquals(linkExpected, actualLink);
	
	}
	
	
	@Test
	public void testUpdateLink() {
		
//		Link savedLink = lr.findById(lr.save(link).getId());
//		return savedLink;
		
		int dummyId = 1;
		
		Link linkExpected = link1;
	
		when(linkRep.save(link1)).thenReturn(linkExpected);
		when(linkRep.findById(dummyId)).thenReturn(link1);
		
		Link actualLink = linkServe.updateLink(link1);
		
		verify(linkRep).save(link1);
		verify(linkRep).findById(dummyId);
		reset(linkRep);
		assertEquals(linkExpected, actualLink);
	
	}

	@Test
	public void testDeleteLinkById() {
		
		Link linkExpected = link1;
		int dummyId = 1;
		
		when(linkRep.findById(dummyId)).thenReturn(linkExpected);
		doNothing().when(linkRep).delete(link1);
		
		linkServe.deleteLinkById(dummyId);
		
		verify(linkRep).findById(dummyId);
		verify(linkRep).delete(link1);
		reset(linkRep);
	
	}
	
	@Test
	public void testFilter() {
		
		Content cont1 = new Content();
		cont1.setId(2);
		cont1.setTitle("Test Content 1");
		cont1.setDescription("Testing Content");
		cont1.setUrl("www.google.com");
		
		cont1.setFormat("Code");
		cont1.setDateCreated(1L);
		cont1.setLastModified(1L);
		
		Content cont2 = new Content();
		cont2.setId(3);
		cont2.setTitle("Test Content 2");
		cont2.setDescription("Testing Content");
		cont2.setUrl("www.google.com");
		
		cont2.setFormat("Code");
		cont2.setDateCreated(1L);
		cont2.setLastModified(1L);
		
		Set<Content> filteredRequestsExpected = new HashSet<>();
		
		filteredRequestsExpected.add(cont1);
		filteredRequestsExpected.add(cont2);
		
		Module mod1 = new Module();
		
		mod1.setChildren(null);
		mod1.setCreated(1L);
		mod1.setId(30);
		mod1.setLinks(null);
		mod1.setParents(null);
		mod1.setSubject("SOAP");

		Link link1 = new Link();
		link1.setId(5);
		link1.setContent(cont1);
		link1.setModule(mod1);
		link1.setAffiliation("testing link affiliation");
		
		Set<Link> links1 = new HashSet<>();
		links1.add(link1);
		
		cont1.setLinks(links1);
		mod1.setLinks(links1);
		
		Module mod2 = new Module();
		
		mod2.setChildren(null);
		mod2.setCreated(1L);
		mod2.setId(31);
		mod2.setLinks(null);
		mod2.setParents(null);
		mod2.setSubject("SOAP");

		Link link2 = new Link();
		link1.setId(6);
		link1.setContent(cont2);
		link1.setModule(mod2);
		link1.setAffiliation("testing link affiliation");
		
		Set<Link> links2 = new HashSet<>();
		links2.add(link2);
		
		cont2.setLinks(links2);
		mod2.setLinks(links2);
		
		Set<Set<Link>> setOfSetOfLinks = new HashSet<>();
		setOfSetOfLinks.add(links1);
		setOfSetOfLinks.add(links2);
		
		List<Integer> moduleIds = new ArrayList<>();
		moduleIds.add(mod1.getId());
		moduleIds.add(mod2.getId());
		
		when(searchServe.filter("Code", "Testing Requests", moduleIds)).thenReturn(filteredRequestsExpected);
		
		Iterator<Content> contItr = filteredRequestsExpected.iterator();
		Iterator<Set<Link>> setLinkItr = setOfSetOfLinks.iterator();
		
		while(contItr.hasNext() && setLinkItr.hasNext()) {
			when(linkRep.findByContentId(contItr.next().getId())).thenReturn(setLinkItr.next());
		}
		
		Set<Set<Link>> actualSetOfSetOfLinks = linkServe.filter("Code", "Testing Requests", (ArrayList<Integer>) moduleIds);
		
		contItr = filteredRequestsExpected.iterator();
		setLinkItr = setOfSetOfLinks.iterator();
		
		while(contItr.hasNext() && setLinkItr.hasNext()) {
			verify(linkRep).findByContentId(contItr.next().getId());
		}
		
		assertEquals(setOfSetOfLinks, actualSetOfSetOfLinks);
	}	
}
