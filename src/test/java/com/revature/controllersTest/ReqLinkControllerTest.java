package com.revature.controllersTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyList;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.ReqLinkController;
import com.revature.entities.ReqLink;
import com.revature.services.ReqLinkService;

public class ReqLinkControllerTest {
	
	@Mock
	private ReqLinkService rlsMock;
	
	@InjectMocks
	private ReqLinkController rlc;
	
	private MockMvc mvc;
    private ObjectMapper objMapper = new ObjectMapper();

	
	@BeforeClass
	public void setup() {
		rlc = new ReqLinkController ();
		mvc = MockMvcBuilders.standaloneSetup(rlc).build();
		
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * this method tests {@link com.revature.controllers.ReqLinkController#createReqLink(ReqLink) createReqLink(ReqLinks).}
	 * this method tests the creation of a reqLink by passing a ReqLink object as an argument and expecting a response status of ok
	 * @throws Exception
	 */
	@Test
	public void testCreateReqLink() throws Exception {
		ReqLink reqLink = new ReqLink();
		
		Mockito.when(rlsMock.createReqLink(reqLink)).thenReturn(reqLink);

		ResultActions result = mvc.perform(post ("/req-links")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("id", "1")
				.content(objMapper.writeValueAsString(reqLink)));
		
				result.andExpect(status().isOk());
	}
	
	/**
	 * this method tests {@link com.revature.controllers.ReqLinkController#getAllReqLinks() getAllReqLinks().}
	 * this method tests the retrieval of a all reqLink objects by expecting a response status of ok
	 * @throws Exception
	 */
	@Test
	public void testGetAllReqLinks() throws Exception {
		Set<ReqLink> reqLinks = new HashSet<ReqLink>();
		
		Mockito.when(rlsMock.getAllReqLinks()).thenReturn(reqLinks);

		ResultActions result = mvc.perform(get ("/req-links")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(reqLinks)));
		
				result.andExpect(status().isOk());
	}
	
	/**
	 * this method tests {@link com.revature.controllers.ReqLinkController#getReqLinkById(int) getReqLinkById(int).}
	 * this method tests the retrieval of a reqLink by passing an int Id as an argument and expecting a response status of ok
	 * @throws Exception
	 */
	@Test
	public void testGetReqLinkById() throws Exception {
		ReqLink reqLink = new ReqLink();
		
		Mockito.when(rlsMock.getReqLinkById(anyInt())).thenReturn(reqLink);

		ResultActions result = mvc.perform(get ("/req-links/" + anyInt() )
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(reqLink)));
		
				result.andExpect(status().isOk());
	}
	
	/**
	 * this method tests {@link com.revature.controllers.ReqLinkController#getSearchResults(String, String, String) getSearchResults(String, String, String).}
	 * this method tests the retrieval of a set of sets of reqLink objects by passing a String title, String format, and String moduleIds (which is tokenized and delimited by "," ) as arguments and expecting a response status of ok
	 * @throws Exception
	 * 
	 * Note From Tester: could be refactored-renamed to getFilteredResults to make more clear what the method is doing.
	 */
	@Test
	public void testGetSearchResults() throws Exception {
		Set<Set<ReqLink>> reqLinks = new HashSet<Set<ReqLink>>();
		
		Mockito.when(rlsMock.filter(anyString(), anyString(), anyList())).thenReturn(reqLinks);

		ResultActions result = mvc.perform(get ("/req-links/")
				.param("title", anyString())
				.param("format", anyString())
				.param("modules", anyString() + "," + "1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(reqLinks)));
		
				result.andExpect(status().isOk());
	}
	
	/**
	 * this method tests {@link com.revature.controllers.ReqLinkController#updatReqLink(ReqLink) updateReqLink(ReqLink).}
	 * this method tests the updating of a reqLink objects by passing a ReqLink object as an argument and expecting a response status of ok
	 * @throws Exception
	 */
	@Test
	public void testUpdateReqLink() throws Exception {
		ReqLink reqLink = new ReqLink();
		
		Mockito.when(rlsMock.updateReqLink(reqLink)).thenReturn(reqLink);

		ResultActions result = mvc.perform(put ("/req-links/" + anyInt())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(reqLink)));
		
				result.andExpect(status().isOk());
	}
	
	/**
	 * this method tests {@link com.revature.controllers.ReqLinkController#deleteReqLink(int) deleteReqLink(ReqLink).}
	 * this method tests the deletion of a reqLink objects by passing a ReqLink object as an argument and expecting a response status of ok
	 * @throws Exception
	 */
	@Test
	public void testDeleteReqLink() throws Exception {
		ReqLink reqLink = new ReqLink();
		
		Mockito.doNothing().when(rlsMock).deleteReqLinkById(anyInt());

		ResultActions result = mvc.perform(delete ("/req-links/" + anyInt() )
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(reqLink)));
		
				result.andExpect(status().isOk());
	}
	
	

		

}
