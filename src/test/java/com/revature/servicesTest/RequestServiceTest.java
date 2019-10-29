package com.revature.servicesTest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.exceptions.InvalidRequestException;
import com.revature.exceptions.InvalidRequestIdException;
import com.revature.repositories.ReqLinkRepository;
import com.revature.repositories.RequestRepository;
import com.revature.services.RequestService;
import com.revature.services.RequestServiceImpl;

public class RequestServiceTest {

	@Mock
	private RequestRepository rrMock;
	
	@Mock
	private ReqLinkRepository rlrMock;
	
	@InjectMocks
	private RequestService requestService = new RequestServiceImpl();
	
	@BeforeMethod
	public void reinitMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * this method tests {@link com.revature.services.RequestService#createRequest(com.revature.entities.Request) createRequest(Request).}
	 * this method assumes a Request is passed as an argument and returns the newly persisted object with a date created and a last date modified populated.
	 * This method mocks the RequestRepository
	 */
	@Test()
	public void testCreateRequest() {
		Request request = new Request(1, null, null, null, null, 0l, 0l, null);
		
		//given
		Mockito.when(rrMock.save(request)).thenReturn(request);
				
		//when
		Request actual = requestService.createRequest(request);
		
		assertNotNull(actual);
	}
	
	/**
	 * this method tests {@link com.revature.services.RequestService#getAllRequests(). getAllRequests().}
	 * this method returns a set of requests.
	 * This method mocks the RequestRepository
	 */
	@Test()
	public void testgetAllRequests() {
		Set<Request> request = new HashSet<Request>();
		Mockito.when(rrMock.findAll()).thenReturn(request);
		
		Set<Request> Actualrequest = requestService.getAllRequests();
		
		verify(rrMock).findAll();
		assertEquals(Actualrequest, request);
	}
	
	/**
	 * this method tests {@link com.revature.services.RequestService#getRequestsById(int) getRequestbyId().}
	 * this method assumed an int id is passed as an argument and returns a single Request.
	 * this method mocks the RequestRepository
	 */
	@Test()
	public void testGetRequestById() {
		Request request = new Request();
		Mockito.when(rrMock.findById(anyInt())).thenReturn(request);
		Request actual = requestService.getRequestsById(1);
		assertEquals(request, actual);
	}
	
	/**
	 * this method tests {@link com.revature.services.RequestService#updateRequest(Request) updateRequest.}
	 * this method assumed a Request object is passed as an argument and returns a single newly updated Request object.
	 * this method mocks the RequestRepository
	 */
	@Test()
	public void testGetUpdateRequest() {
		Request request = new Request();
		Mockito.when(rrMock.save(request)).thenReturn(request);
		Request actual = requestService.updateRequest(request);
		assertEquals(request, actual);
	}

	/**
	 * this method tests {@link com.revature.services.RequestService#updateRequest(Request) updateRequest.}
	 * this method assumed a null Request object is passed as an argument and throws an Exception
	 * this method mocks the RequestRepository
	 */
	@Test(expectedExceptions = {InvalidRequestException.class})
	public void testGetUpdateRequest_NullRequest() {
		Request request = null;
		Mockito.when(rrMock.save(request)).thenReturn(request);
		Request actual = requestService.updateRequest(request);
	}
	
	/**
	 * this method tests {@link com.revature.services.RequestService#getRequestsByFormat(String[]) GetRequestByFormat(String[]).}
	 * this method assumed a String array of formats is passed as an argument and returns a Map<String, Integer> pair denoting a format, and the number of times the format is used
	 * this method mocks the RequestRepository
	 * 
	 * Note - the method tested requires a String array but does not use the string array in its body
	 */
	@Test()
	public void testGetRequestByFormat_StringArrayVersion() {
		String formats[] = {"format1, format2"};
		List<Request> requests = new ArrayList<Request>();
		Request request1 = new Request(1, null, "format1", null, null, null, null, null);
		Request request2 = new Request(2, null, "format2", null, null, null, null, null);
		Request request3 = new Request(3, null, "format1", null, null, null, null, null);

		requests.add(request1);
		requests.add(request2);
		requests.add(request3);
		
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("format1", 2);
		expected.put("format2", 1);

		
		Mockito.when(rrMock.findAll()).thenReturn(requests);
		
		Map<String, Integer> actual = requestService.getRequestsByFormat(formats);
		
		assertEquals(actual, expected);
	}
	
	/**
	 * this method tests {@link com.revature.services.RequestService#getRequestsByFormat(Set) GetRequestByFormat(Set<Request>).}
	 * this method assumed a Set of Requests is passed as an argument and returns a Map<String, Integer> pair denoting a format, and the number of times the format is used
	 * this method uses no repository
	 * 
	 * Note: Id is not enough to distinguish the objects in a set. Tester Had to include title. if Titles were set to null the method would fail
	 */
	@Test()
	public void testGetRequestByFormat_SetVersion() {
		Set<Request> requests = new HashSet<Request>();
		Request request1 = new Request(1, "title1", "format1", null, null, null, null, null);
		Request request2 = new Request(2, "title2", "format2", null, null, null, null, null);
		Request request3 = new Request(3, "title3", "format1", null, null, null, null, null);

		requests.add(request1);
		requests.add(request2);
		requests.add(request3);
		
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("format1", 2);
		expected.put("format2", 1); 	
		
		Map<String, Integer> actual = requestService.getRequestsByFormat(requests);
		
		assertEquals(actual, expected);
	}
	
	/**
	 * this method tests {@link com.revature.services.RequestService#deleteRequest(Request) deleteRequest(Request).}
	 * 
	 */
	@Test()
	public void testDelete() {
		Request request1 = new Request(1, null, "format1", null, null, null, null, null);
		requestService.deleteRequest(request1);
		verify(rrMock).delete(request1);
	}
	
	/**
	 * this method tests {@link com.revature.services.RequestService#updateReqLinks(int, List) updateReqLinks(int, List<ReqLink>.}
	 * this method assumed an int id and a List ReqLink is passed as an argument and returns a List of the newly persisted Reqlink
	 * this method mocks the ReqLinkRepository
	 */
	@Test()
	public void testUpdateReqLinks() {
		List<ReqLink> reqLinks = new ArrayList<ReqLink>();
		ReqLink reqLink = new ReqLink();
		reqLinks.add(reqLink);
		
		Mockito.when(rlrMock.save(reqLink)).thenReturn(reqLink);
		
		List<ReqLink> actual = requestService.updateReqLinks(1, reqLinks);
		
		assertEquals(actual, reqLinks);
	}
	
	/**
	 * this method tests {@link com.revature.services.RequestService#createReqLinksByRequestId(int, List) createReqLinksByRequestId(int, list<ReqLink>).}
	 * this method assumed an int id and a List ReqLink is passed as an argument and returns a List of the newly persisted Reqlink
	 * this method mocks the ReqLinkRepository
	 */
	@Test()
	public void testCreateReqLinksByRequestId() {
		List<ReqLink> reqLinks = new ArrayList<ReqLink>();
		ReqLink reqLink = new ReqLink();
		reqLinks.add(reqLink);
		
		Mockito.when(rlrMock.save(reqLink)).thenReturn(reqLink);
		
		List<ReqLink> actual = requestService.createReqLinksByRequestId(1, reqLinks);
		
		assertEquals(actual, reqLinks);
	}
	
	/**
	 * this method tests {@link com.revature.services.RequestService#getReqLinksByRequestId(int) getReqLinksByRequestId(int).}
	 * this method assumed an int id passed as an argument and returns a List of Reqlink
	 * this method mocks the ReqLinkRepository
	 */
	@Test()
	public void testgetReqLinksByRequestId() {
		List<ReqLink> reqLinks = new ArrayList<ReqLink>();
		ReqLink reqLink = new ReqLink();
		reqLinks.add(reqLink);
		
		Mockito.when(rlrMock.findByRequestId(anyInt())).thenReturn(reqLinks);
		
		List<ReqLink> actual = requestService.getReqLinksByRequestId(1);
		
		assertEquals(actual, reqLinks);
	}
	

	
}
