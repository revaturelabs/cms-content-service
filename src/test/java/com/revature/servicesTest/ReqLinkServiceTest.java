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

import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.repositories.ModuleRepository;
import com.revature.repositories.ReqLinkRepository;
import com.revature.repositories.RequestRepository;
import com.revature.services.ReqLinkService;
import com.revature.services.ReqLinkServiceImpl;
import com.revature.services.SearchService;

public class ReqLinkServiceTest {
	@Mock
	private RequestRepository rrMock;
	@Mock
	private ModuleRepository mrMock;
	@Mock
	private ReqLinkRepository rlrMock;
	@Mock
	private SearchService ssMock;
	
	@InjectMocks
	private ReqLinkService rls = new ReqLinkServiceImpl();
	
	@BeforeClass
	public void mock() {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * this method tests {@link com.revature.services.ReqLinkService#createReqLink(com.revature.entities.ReqLink) createReqLink(ReqLink).}
	 * this method assumes a ReqLink is passed as an argument and returns the newly persisted reqLink. this method also persists the reqLinks request and Module.
	 * This method Mocks ReqLinkRepository, ModuleRepository, and RequestRepository
	 */
	@Test
	public void testCreateReqLink() {
		Request request = new Request();
		Module module = new Module();
		ReqLink reqLink = new ReqLink(1, request, module, null);
		Mockito.when(rrMock.save(request)).thenReturn(request);
		Mockito.when(mrMock.save(module)).thenReturn(module);
		Mockito.when(rlrMock.save(reqLink)).thenReturn(reqLink);
		
		ReqLink actual = rls.createReqLink(reqLink);
		
		assertEquals(actual, reqLink);		
	}
	
	/**
	 * this method tests {@link com.revature.services.ReqLinkService#getAllReqLinks() getAllReqLinks().}
	 * returns a set of reqLink objects. 
	 * This method Mocks ReqLinkRepository.
	 */
	@Test
	public void testGetAllCreateReqLinks() {
		Set<ReqLink> reqLinks = new HashSet<ReqLink>();
		ReqLink reqLink = new ReqLink(1, null, null, null);
		reqLinks.add(reqLink);
		Mockito.when(rlrMock.findAll()).thenReturn(reqLinks);
		
		Set<ReqLink> actual = rls.getAllReqLinks();
		
		assertEquals(actual, reqLinks);		
	}
	
	/**
	 * this method tests {@link com.revature.services.ReqLinkService#getReqLinkById(int) getReqLinkById(int).}
	 * this method assumes an int id, and returns a reqLink object. 
	 * This method Mocks ReqLinkRepository.
	 */
	@Test()
	public void testGetReqLinkById() {
		ReqLink reqLink = new ReqLink();
		Mockito.when(rlrMock.findById(anyInt())).thenReturn(reqLink);
		
		ReqLink actual = rls.getReqLinkById(anyInt());
		
		assertEquals(actual, reqLink);		
	}
	
	/**
	 * this method tests {@link com.revature.services.ReqLinkService#updateReqLink(ReqLink) updateReqLink(ReqLink).}
	 * this method assumes a ReqLink is passed as an argument and returns the newly persisted reqLink. 
	 * This method Mocks ReqLinkRepository
	 */
	@Test
	public void testupdateReqLink() {
		ReqLink reqLink = new ReqLink();
		Mockito.when(rlrMock.save(reqLink)).thenReturn(reqLink);
		
		ReqLink actual = rls.updateReqLink(reqLink);
		
		assertEquals(actual, reqLink);		
	}
	
	/**
	 * this method tests {@link com.revature.services.ReqLinkService#deleteReqLinkById(int) deleteReqLinkById(int).}
	 * this method assumes an int Id is passed as an argument. 
	 * This method Mocks ReqLinkRepository
	 */
	@Test
	public void testDeleteReqLinkById() {
		ReqLink reqLink = new ReqLink();
		Mockito.when(rlrMock.findById(anyInt())).thenReturn(reqLink);
		rls.deleteReqLinkById(anyInt());
		verify(rlrMock).delete(reqLink);


	}
	
	/**
	 * this method tests {@link com.revature.services.ReqLinkService#filter(String, String, java.util.List) filter(String, String, List<Integer>) .}
	 * this method assumes a String title, String format, and a List of Integer moduleIds are passed as an argument and returns a set of sets of filtered reqLink objects. 
	 * This method Mocks ReqLinkRepository and SearchService
	 */
	@Test
	public void testFilter() {
		List<Integer> moduleIds = new ArrayList<Integer>();
		List<String> formats = new ArrayList<String>();
		Set<ReqLink> reqLinks = new HashSet<ReqLink>();
		Set<Request> requests = new HashSet<Request>();
		Set<Set<ReqLink>> setOfSetOfReqLinks = new HashSet<Set<ReqLink>>();
		Request request = new Request(1, null, null, null, null, null, null, null);
		ReqLink reqLink = new ReqLink();

		requests.add(request);
		formats.add("format");
		moduleIds.add(1);
		reqLinks.add(reqLink);
		setOfSetOfReqLinks.add(reqLinks);
		
		Mockito.when(ssMock.filterReq("title", formats, moduleIds)).thenReturn(requests);
		Mockito.when(rlrMock.findByRequest(request)).thenReturn(reqLinks);

		Set<Set<ReqLink>> actual = rls.filter("title", "format", moduleIds);
		
		assertEquals(actual, setOfSetOfReqLinks);		
	}
	
	

}
