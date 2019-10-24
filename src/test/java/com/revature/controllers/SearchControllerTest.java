package com.revature.controllers;


import static org.testng.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.ContentController;
import com.revature.entities.Content;
import com.revature.services.SearchService;
import com.revature.testingutils.ContentFactory;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;


@SpringBootTest(classes = CMSforceApplication.class)
public class SearchControllerTest extends AbstractTestNGSpringContextTests {
	
	private static final String filterTitle = "title",
								filterFormat = "format",
								filterModules = "modules",
								filterCurricula = "curriculum";	

	private MockMvc mvc;

    private ObjectMapper objMapper = new ObjectMapper();

	private Content content;
	private List<Integer> modules;
	private List<String> formats;
	private Set<Content> retValue;
	private Map<String, Object> reqBody;

	@InjectMocks
	private ContentController cc;
	
	@Mock
	private SearchService ss;
	
	/**
	 * Initialize Mockito and mocking dependencies 
	 */
	@BeforeClass
	public void setup() {
		cc = new ContentController ();
		mvc = MockMvcBuilders.standaloneSetup(cc).build();
		
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Ensure clean content and request information for each test
	 */
	@BeforeTest 
	public void preTestSetup () {
		content = ContentFactory.getContent();
		
		modules = new ArrayList<Integer> ();
		formats = new ArrayList<String> ();
		modules.add(1);
		formats.add(ContentFactory.format);
		
		retValue = new HashSet<Content> ();

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
	public void givenValidSearchGetContentNotNull() throws Exception {
		retValue.add(content);
		//mock service return
		
		ArrayList<String> forList = new ArrayList<String>();
		ArrayList<Integer> curList = new ArrayList<Integer>();
		forList.add(ContentFactory.format);
		curList.add(1);
		
		Mockito.when(ss.filter(anyString(), anyList(), anyList(), anyList()))
						.thenReturn(retValue);
		ResultActions result = mvc.perform(get ("/content")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.param(filterTitle, (String) reqBody.get(filterTitle))
				.param(filterFormat, (String) reqBody.get(filterFormat))
				.param(filterModules, modules.get(0).toString())
				.param(filterCurricula, "1"));
		Set<Content> resultContent = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<Content>>() { });
		assertEquals (resultContent.size(), 1, "Invalid number of resulting content");
	}
	
	@Test
	public void givenValidSearchGetContentNotEqual() throws Exception {
		retValue.add(content);
		Mockito.when(ss.filter(ContentFactory.title, formats, modules))
						.thenReturn(retValue);
		ResultActions result = mvc.perform(get ("/content")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.param(filterTitle, (String) reqBody.get(filterTitle))
				.param(filterFormat, formats.get(0))
				.param(filterModules, modules.get(0).toString()));
		Set<Content> resultContent = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<Content>>() { });
		assertNotNull (resultContent, "No content was not found");
	}
	
	/**
	 * Test searching for content that is not in the system
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void givenInvalidSearchGetNoContentStatusOk () throws Exception {
		Mockito.when(ss.filter(ContentFactory.title, formats, modules))
						.thenReturn(retValue);
		ResultActions result = mvc.perform(get ("/content")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.param(filterTitle, (String) reqBody.get(filterTitle))
				.param(filterFormat, formats.get(0))
				.param(filterModules, modules.get(0).toString()));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void givenInvalidSearchGetNoContentEquals () throws Exception {
		//given
		//mock service return, expect empty search result
		
		ArrayList<String> forList = new ArrayList<String>();
		forList.add(ContentFactory.format);
		
		Mockito.when(ss.filter(ContentFactory.title, forList, modules))
						.thenReturn(retValue);
		ResultActions result = mvc.perform(get ("/content")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.param(filterTitle, (String) reqBody.get(filterTitle))
				.param(filterFormat, (String) reqBody.get(filterFormat))
				.param(filterModules, modules.get(0).toString()));
		Set<Content> resultContent = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<Content>>() { });
		assertEquals (resultContent.size(), 0, "Invalid number of resulting content");
	}
	
	@Test
	public void givenInvalidSearchGetNoContentNotNull () throws Exception {
		Mockito.when(ss.filter(ContentFactory.title, formats, modules))
						.thenReturn(retValue);
		ResultActions result = mvc.perform(get ("/content")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.param(filterTitle, (String) reqBody.get(filterTitle))
				.param(filterFormat, formats.get(0))
				.param(filterModules, modules.get(0).toString()));
		Set<Content> resultContent = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<Content>>() { });
		assertNotNull (resultContent, "Invalid content response");
	}
}

