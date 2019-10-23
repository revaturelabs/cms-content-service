package com.revature.services;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.exceptions.InvalidRequestException;
import com.revature.exceptions.InvalidRequestIdException;
import com.revature.repositories.ModuleRepository;
import com.revature.repositories.ReqLinkRepository;
import com.revature.repositories.RequestRepository;
import com.revature.services.RequestServiceImpl;

/**
 * Testing for the RequestService class.
 * 
 * @author fjgiv
 * @version 1.0
 */
@SpringBootTest(classes = CMSforceApplication.class)


public class RequestServiceTest extends AbstractTestNGSpringContextTests {
	
	@Mock
	RequestRepository reqRep;
	@Mock
	ModuleRepository modRep;
	@Mock
	ReqLinkRepository reqLinkRep;
	
	@InjectMocks
	RequestServiceImpl reqServe;
	
	Request request;
	Request request2;
	Set<Request> requests;
	Content content;
	Module module;
	ReqLink reqLink;
	ReqLink reqLink2;
	List<ReqLink> listOfReqLinks;
	Set<ReqLink> reqLinks;
	
	@BeforeClass
	public void setupMocks() {
		MockitoAnnotations.initMocks(this);
		
		request = new Request();
		request.setId(1);
		request.setTitle("Test Request");
		request.setDescription("this is a desc for test request");
		
		request.setFormat("Code");
		request.setDateCreated(1L);
		request.setLastModified(1L);
		
		request2 = new Request();
		request2.setId(2);
		request2.setTitle("Test Request2");
		request2.setDescription("this is a desc for test request2");
		
		request2.setFormat("Code");
		request2.setDateCreated(1L);
		request2.setLastModified(1L);
		
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
		
		request.setContent(content);
		request2.setContent(content);
		
		reqLink = new ReqLink();
		reqLink.setAffiliation("relevantTo");
		reqLink.setId(5);
		reqLink.setModule(module);
		reqLink.setRequest(request);

		reqLinks = new HashSet<>();
		reqLinks.add(reqLink); 
		
		//may want to comment these out
		request.setReqLinks(reqLinks);
		request2.setReqLinks(reqLinks);
		module.setReqLinks(reqLinks);
		
		requests = new HashSet<>();
		requests.add(request);
		requests.add(request2);
		
		reqLink2 = new ReqLink();
		reqLink2.setAffiliation("relevantTo");
		reqLink2.setId(7);
		reqLink2.setModule(module);
		reqLink2.setRequest(request);
		
		listOfReqLinks = new ArrayList<>();
		listOfReqLinks.add(reqLink);
		listOfReqLinks.add(reqLink2);

	}
	
	@Test
	public void testCreateRequest() 
	{
		
		Request reqExpected = request;
		
		when(reqRep.save(request)).thenReturn(reqExpected);
		
		Request actual = reqServe.createRequest(request);
		
		verify(reqRep).save(request);
		reset(reqRep);
		
		assertEquals(reqExpected, actual, "Should get back same content");
		
	}

	@Test
	public void testCreateNewRequestHasCreationDate() 
	{
		
		Request newRequest = new Request();
		newRequest.setDateCreated(0L);
		newRequest.setLastModified(0L);
		
		Request newRequestExpected = new Request();
		newRequestExpected.setDateCreated(System.currentTimeMillis());
		newRequestExpected.setLastModified(System.currentTimeMillis());

		when(reqRep.save(newRequest)).thenReturn(newRequestExpected);
		
		Request actual = reqServe.createRequest(newRequest);
		
		verify(reqRep).save(newRequest);
		reset(reqRep);
		
		assertNotEquals(actual.getDateCreated(), 0L);
		
	}
	

	@Test
	public void testCreateNewRequestHasLastModifiedDate() 
	{
		Request newRequest = new Request();
		newRequest.setDateCreated(0L);
		newRequest.setLastModified(0L);
		
		Request newRequestExpected = new Request();
		newRequestExpected.setDateCreated(System.currentTimeMillis());
		newRequestExpected.setLastModified(System.currentTimeMillis());

		when(reqRep.save(newRequest)).thenReturn(newRequestExpected);

		Request actual = reqServe.createRequest(newRequest);
		
		verify(reqRep).save(newRequest);
		reset(reqRep);
		
		assertNotEquals(actual.getLastModified(), 0L);
		
	}
	
	@Test
	public void testGetAllRequests() {

		Set<Request> requestsExpected = requests;
		when(reqRep.findAll()).thenReturn(requestsExpected);
		
		Set<Request> actualRequests = reqServe.getAllRequests();
		
		verify(reqRep).findAll();
		reset(reqRep);
		
		assertEquals(requestsExpected, actualRequests);
		
	}
	
	@Test
	public void testGetRequestsById() {
		
		int dummyId = 1;
		Request requestExpected = request;
		
		when(reqRep.findById(dummyId)).thenReturn(requestExpected);
		
		Request actualRequest = reqServe.getRequestsById(dummyId);
		
		verify(reqRep).findById(dummyId);
		reset(reqRep);
		
		assertEquals(requestExpected, actualRequest);
		
		
	}
	
	@Test(expectedExceptions = InvalidRequestException.class)
	public void testUpdateNullRequest() {
		reqServe.updateRequest(null);
	}

	@Test(expectedExceptions = InvalidRequestIdException.class)
	public void testUpdateWithNonExistentRequestId() {
		Request actual = new Request();
		
		int requestIdNotInDatabase = -1;
		actual.setId(requestIdNotInDatabase);
		
		when(reqRep.findById(requestIdNotInDatabase)).thenReturn(null);
		reqServe.updateRequest(actual);
		verify(reqRep).findById(requestIdNotInDatabase);
		reset(reqRep);
	}
	
	@Test 
	public void testUpdateRequest() {
		
		Request requestExpected = request;
		int dummyId = 1;
		
		when(reqRep.findById(dummyId)).thenReturn(request);
		when(reqRep.save(request)).thenReturn(requestExpected);
		
		Request actualRequest = reqServe.updateRequest(request);
		
		verify(reqRep).findById(dummyId);
		verify(reqRep).save(request);
		reset(reqRep);
		
		assertEquals(requestExpected, actualRequest);
		
	}
	
	@Test
	public void testGetRequestByFormatWithStrings() {
		
		List<Request> requestsExpected = new ArrayList<>();
		requestsExpected.add(request);
		requestsExpected.add(request2);
		
		Map<String, Integer> mapExpected = new HashMap<>();
		mapExpected.put("Code",  2);
		
		when(reqRep.findAll()).thenReturn(requestsExpected);
		
		Map<String, Integer> actualMap = reqServe.getRequestsByFormat(new String[] { "Code" });
		
		verify(reqRep).findAll();
		reset(reqRep);
		assertEquals(actualMap, mapExpected);
				
	}
	
	
	@Test
	public void testGetRequestsByFormat() {
		Set<Request> requestsExpected = requests;
		
		Map<String, Integer> mapExpected = new HashMap<>();
		mapExpected.put("Code",  2);

		Map<String, Integer> actualMap = reqServe.getRequestsByFormat(requestsExpected);
		
		assertEquals(actualMap, mapExpected);

	}
	
	@Test
	public void testDeleteRequest() {
		
		
		doNothing().when(reqRep).delete(request);
		reqServe.deleteRequest(request);
		verify(reqRep).delete(request);
		reset(reqRep);
	}
	
	@Test
	public void testUpdateReqLinks() {

		int dummyId = 1; 
		List<ReqLink> reqLinksExpected = listOfReqLinks;
		
		for (ReqLink rl : reqLinksExpected) {
		
			when(reqLinkRep.save(rl)).thenReturn(rl);
		}
		
		List<ReqLink> actualReqLinks = reqServe.updateReqLinks(dummyId, listOfReqLinks);
		
		for (ReqLink rl : reqLinksExpected) {
			
			verify(reqLinkRep, times(2)).save(rl);
		}
		reset(reqLinkRep);
		assertEquals(reqLinksExpected, actualReqLinks);

	}
	
	@Test
	public void testCreateReqLinksByRequestIdEvenThoughRequestIdIsNotUsed() {

		int dummyId = 1; 
		List<ReqLink> reqLinksExpected = listOfReqLinks;
		
		for (ReqLink rl : reqLinksExpected) {
		
			when(reqLinkRep.save(rl)).thenReturn(rl);
		}
		
		List<ReqLink> actualReqLinks = reqServe.createReqLinksByRequestId(dummyId, listOfReqLinks);
		
		for (ReqLink rl : reqLinksExpected) {
			
			verify(reqLinkRep, times(2)).save(rl);
		}
		reset(reqLinkRep);
		assertEquals(reqLinksExpected, actualReqLinks);

	}
	
	@Test
	public void testReqLinksByRequestId() {
		
		int dummyId = 1;
		
		when(reqLinkRep.findByRequestId(dummyId)).thenReturn(listOfReqLinks);
		
		reqServe.getReqLinksByRequestId(dummyId);
		
		verify(reqLinkRep).findByRequestId(dummyId);
		reset(reqLinkRep);
	}
}
