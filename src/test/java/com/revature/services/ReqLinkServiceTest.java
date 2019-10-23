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
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.repositories.ModuleRepository;
import com.revature.repositories.ReqLinkRepository;
import com.revature.repositories.RequestRepository;
import com.revature.services.ReqLinkServiceImpl;
import com.revature.services.SearchService;

/**
 * Testing for the ReqLinkService class.
 * 
 * @author fjgiv
 * @version 1.0
 */
@SpringBootTest(classes = CMSforceApplication.class)
public class ReqLinkServiceTest extends AbstractTestNGSpringContextTests {

	@Mock
	ReqLinkRepository reqLinkRep;

	@Mock
	RequestRepository reqRep;

	@Mock
	ModuleRepository modRep;

	@Mock
	SearchService searchServe;

	@InjectMocks
	ReqLinkServiceImpl reqLinkServe;

	Request request;
	Module module;
	ReqLink reqLink;
	ReqLink actualReqLink;
	Set<ReqLink> reqLinks;
	Set<ReqLink> actualReqLinks;

	@BeforeClass
	public void setupMocks() {
		MockitoAnnotations.initMocks(this);

		request = new Request();
		request.setContent(null);
		request.setDateCreated(1L);
		request.setDescription("yo momma");
		request.setFormat("Code");
		request.setId(5);
		request.setLastModified(1L);
		request.setTitle("Testing Requests 1");

		module = new Module();
		module.setChildren(null);
		module.setCreated(1L);
		module.setId(25);
		module.setLinks(null);
		module.setParents(null);
		module.setSubject("SOAP");

		reqLink = new ReqLink();
		reqLink.setAffiliation("relevantTo");
		reqLink.setId(5);
		reqLink.setModule(module);
		reqLink.setRequest(request);

		reqLinks = new HashSet<>();
		reqLinks.add(reqLink); 
		 //request.setReqLinks(reqLinks);
		 //module.setReqLinks(reqLinks);
		 

	}

	@Test
	public void testCreateReqLink() {
//		reqLink.setRequest(rr.save(reqLink.getRequest()));
		Request reqExpected = request;
		when(reqRep.save(request)).thenReturn(reqExpected);

//		reqLink.setModule(mr.save(reqLink.getModule()));
		Module modExpected = module;
		when(modRep.save(module)).thenReturn(modExpected);

//		return rlr.save(reqLink);
		ReqLink reqLinkExpected = reqLink;
		when(reqLinkRep.save(reqLink)).thenReturn(reqLinkExpected);

		actualReqLink = reqLinkServe.createReqLink(reqLinkExpected);

		verify(reqRep).save(request);
		verify(modRep).save(module);
		verify(reqLinkRep).save(reqLink);
		
		reset(reqRep);
		reset(modRep);
		reset(reqLinkRep);
		assertEquals(reqLinkExpected, actualReqLink);

	}

	@Test
	public void testGetAllReqLinksWhenSetContainsZeroElements() {

//	Set<ReqLink> reqLinks = new HashSet<ReqLink>();
//	rlr.findAll().forEach(reqLinks::add);
		Set<ReqLink> reqLinksExpected = new HashSet<>();
		when(reqLinkRep.findAll()).thenReturn(reqLinksExpected);
		
		actualReqLinks = reqLinkServe.getAllReqLinks();
		
		verify(reqLinkRep).findAll();
		reset(reqLinkRep);
		assertEquals(reqLinksExpected, actualReqLinks);
//	return reqLinks;
	}
	
	@Test
	public void testGetAllReqLinksWhenSetContainsElements() {

//	Set<ReqLink> reqLinks = new HashSet<ReqLink>();
//	rlr.findAll().forEach(reqLinks::add);
		Set<ReqLink> reqLinksExpected = reqLinks;
		when(reqLinkRep.findAll()).thenReturn(reqLinksExpected);
		
		actualReqLinks = reqLinkServe.getAllReqLinks();
		
		verify(reqLinkRep).findAll();
		reset(reqLinkRep);
		assertEquals(reqLinksExpected, actualReqLinks);
//	return reqLinks;
	}
	
	@Test
	public void testGetReqLinkById() {
		
		ReqLink reqLinkExpected = reqLink;
		int dummyId = 1;
		
		when(reqLinkRep.findById(dummyId)).thenReturn(reqLinkExpected);
		
		actualReqLink = reqLinkServe.getReqLinkById(dummyId);
		
		verify(reqLinkRep).findById(dummyId);
		reset(reqLinkRep);
		assertEquals(reqLinkExpected, actualReqLink);
	
	}
	
	@Test
	public void testUpdateReqLink() {
		
		ReqLink reqLinkExpected = reqLink;
	
		when(reqLinkRep.save(reqLink)).thenReturn(reqLinkExpected);
		
		actualReqLink = reqLinkServe.updateReqLink(reqLink);
		
		verify(reqLinkRep).save(reqLink);
		reset(reqLinkRep);
		assertEquals(reqLinkExpected, actualReqLink);
	
	}

	@Test
	public void testDeleteReqLinkById() {
		
		ReqLink reqLinkExpected = reqLink;
		int dummyId = 1;
		
		when(reqLinkRep.findById(dummyId)).thenReturn(reqLinkExpected);
		doNothing().when(reqLinkRep).delete(reqLink);
		
		reqLinkServe.deleteReqLinkById(dummyId);
		
		verify(reqLinkRep).findById(dummyId);
		verify(reqLinkRep).delete(reqLink);
		reset(reqLinkRep);
	}
	
	@Test
	public void testFilter() {
		
		Request req1 = new Request();
		req1.setContent(null);
		req1.setDateCreated(1L);
		req1.setDescription("Dummy description 1");
		req1.setFormat("Code");
		req1.setId(5);
		req1.setLastModified(1L);
		req1.setTitle("Testing Requests");
		
		Request req2 = new Request();
		req2.setContent(null);
		req2.setDateCreated(1L);
		req2.setDescription("Dummy description 2");
		req2.setFormat("Code");
		req2.setId(6);
		req2.setLastModified(1L);
		req2.setTitle("Testing Requests");
		
		Set<Request> filteredRequestsExpected = new HashSet<>();
		
		filteredRequestsExpected.add(req1);
		filteredRequestsExpected.add(req2);
		
		Module mod1 = new Module();
		
		mod1.setChildren(null);
		mod1.setCreated(1L);
		mod1.setId(30);
		mod1.setLinks(null);
		mod1.setParents(null);
		mod1.setSubject("SOAP");

		ReqLink reqLink1 = new ReqLink();
		reqLink1.setAffiliation("relevantTo");
		reqLink1.setId(5);
		reqLink1.setModule(mod1);
		reqLink1.setRequest(req1);

		Set<ReqLink> reqLinks1 = new HashSet<>();
		reqLinks1.add(reqLink1); 
		
		req1.setReqLinks(reqLinks1);
		mod1.setReqLinks(reqLinks1);
		
		Module mod2 = new Module();
		
		mod2.setChildren(null);
		mod2.setCreated(1L);
		mod2.setId(31);
		mod2.setLinks(null);
		mod2.setParents(null);
		mod2.setSubject("SOAP");

		ReqLink reqLink2 = new ReqLink();
		reqLink2.setAffiliation("relevantTo");
		reqLink2.setId(6);
		reqLink2.setModule(mod2);
		reqLink2.setRequest(req2);

		Set<ReqLink> reqLinks2 = new HashSet<>();
		reqLinks2.add(reqLink2); 
		
		req2.setReqLinks(reqLinks2);
		mod2.setReqLinks(reqLinks2);
		
		Set<Set<ReqLink>> setOfSetOfReqLinks = new HashSet<>();
		setOfSetOfReqLinks.add(reqLinks1);
		setOfSetOfReqLinks.add(reqLinks2);
		
		List<Integer> moduleIds = new ArrayList<>();
		moduleIds.add(mod1.getId());
		moduleIds.add(mod2.getId());
		
		when(searchServe.filterReq("Code", "Testing Requests", moduleIds)).thenReturn(filteredRequestsExpected);
		
		Iterator<Request> reqItr = filteredRequestsExpected.iterator();
		Iterator<Set<ReqLink>> setReqLinkItr = setOfSetOfReqLinks.iterator();
		
		while(reqItr.hasNext() && setReqLinkItr.hasNext()) {
			when(reqLinkRep.findByRequest(reqItr.next())).thenReturn(setReqLinkItr.next());
		}
		
		Set<Set<ReqLink>> actualSetOfSetOfReqLinks = reqLinkServe.filter("Code", "Testing Requests", moduleIds);
		
		reqItr = filteredRequestsExpected.iterator();
		setReqLinkItr = setOfSetOfReqLinks.iterator();
		
		while(reqItr.hasNext() && setReqLinkItr.hasNext()) {
			verify(reqLinkRep).findByRequest(reqItr.next());
		}
		
		assertEquals(setOfSetOfReqLinks, actualSetOfSetOfReqLinks);
	}	
	
}
