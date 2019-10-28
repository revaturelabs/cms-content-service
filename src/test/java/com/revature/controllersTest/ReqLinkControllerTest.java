package com.revature.controllersTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
	
	@Test
	public void testGetAllReqLinks() throws Exception {
		Set<ReqLink> reqLinks = new HashSet<ReqLink>();
		
		Mockito.when(rlsMock.getAllReqLinks()).thenReturn(reqLinks);

		ResultActions result = mvc.perform(get ("/req-links")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(reqLinks)));
		
				result.andExpect(status().isOk());
	}
		

}
