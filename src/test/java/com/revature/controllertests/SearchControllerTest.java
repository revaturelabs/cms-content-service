package com.revature.controllertests;


import static org.testng.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.SearchController;
import com.revature.entities.Content;
import com.revature.services.SearchService;
import com.revature.testingutils.ContentFactory;


@SpringBootTest(classes = CMSforceApplication.class)
public class SearchControllerTest extends AbstractTestNGSpringContextTests {
	
	//SearchController filter for request's body data
	private static final String filterTitle = "title",
								filterFormat = "format",
								filterModules = "modules";	

	//allows us to send mocked http requests
	private MockMvc mvc;

	//allows json<->object conversion
	@Autowired
	private Gson gson;
	
	//entities being used in requests
	private Content content;
	private List<Integer> modules;
	private Set<Content> retValue;
	private Map<String, Object> reqBody;

	//controller being tested
	@InjectMocks
	private SearchController sc;
	
	//service the controller depends on
	@Mock
	private SearchService ss;
	
	/**
	 * Initialize Mockito and mocking dependencies 
	 */
	@BeforeClass
	public void setup() {
		//build mock MVC so can build mock requests
		sc = new SearchController ();
		mvc = MockMvcBuilders.standaloneSetup(sc).build();
		
		//enables mockito annotations
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Ensure clean content and request information for each test
	 */
	@BeforeTest 
	public void preTestSetup () {
		//generate the basic content
		content = ContentFactory.getContent();
		
		//add id value for module for this content
		modules = new ArrayList<Integer> ();
		modules.add(1);
		
		//create container for expected return values
		retValue = new HashSet<Content> ();

		//generate the request body
		reqBody = new HashMap<String,Object> ();
		reqBody.put (filterModules, modules);
		reqBody.put (filterTitle, ContentFactory.title);
		reqBody.put (filterFormat, ContentFactory.format);
	}
	
	
	/**
	 * Test searching for content by the title and format
	 * @throws Exception - if the http method fails
	 */
	@Test
	public void givenValidSearchGetContent() throws Exception {
		//given
		//add expected result from service search 
		retValue.add(content);
		//mock service return
		Mockito.when(ss.filter(ContentFactory.title, ContentFactory.format, modules))
						.thenReturn(retValue);
		
		//when
		//perform mock search
		ResultActions result = mvc.perform(post ("/search")
								.contentType(MediaType.APPLICATION_JSON_VALUE)
								.content(gson.toJson(reqBody)));
		//grab the resulting json response, into content[] object
		Content[] resultContent = gson.fromJson(result.andReturn().getResponse()
								.getContentAsString(), Content[].class);
		
		//then
		//expect to get a non-null return
		assertNotNull (resultContent, "No content was not found");
		//expect one result from the query
		assertEquals (resultContent.length, 1, "Invalid number of resulting content");
	}
	
	/**
	 * Test searching for content that is not in the system
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void givenInvalidSearchGetNoContent () throws Exception {
		//given
		//mock service return, expect empty search result
		Mockito.when(ss.filter(ContentFactory.title, ContentFactory.format, modules))
						.thenReturn(retValue);
	
		//when
		//perform mock search
		ResultActions result = mvc.perform(post ("/search")
								.contentType(MediaType.APPLICATION_JSON_VALUE)
								.content(gson.toJson(reqBody)));	
		//grab the resulting json response, into content[] object
		Content[] resultContent = gson.fromJson(result.andReturn().getResponse()
								.getContentAsString(), Content[].class);
				
		//then
		//expect to get a non-null return
		assertNotNull (resultContent, "Invalid content response");
		//expect status of OK
		result.andExpect(status().isOk());
		//expect no results from the query
		assertEquals (resultContent.length, 0, "Invalid number of resulting content");
	}
}

