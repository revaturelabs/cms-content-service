package com.revature.controllertests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.ContentController;
import com.revature.controllers.LogController;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.services.ContentService;
import com.revature.services.SearchService;
import com.revature.testingutils.ContentFactory;

@SpringBootTest(classes = CMSforceApplication.class)
public class LogControllerTest extends AbstractTestNGSpringContextTests {
	
	private MockMvc mvc;
	private ObjectMapper objMapper = new ObjectMapper();
	
	@InjectMocks
	private LogController lc;
	
	private Log log;
	private Content content;
	private String string;
	
	@BeforeClass
	public void setup() {
		lc = new LogController();
		mvc = MockMvcBuilders.standaloneSetup(lc).build();
		
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeTest
	public void preTestSetup() {
		
	}
	
	@Test //(expectedExceptions = IOException.class)
	public void getLogTest() throws IOException {	
		
			StringBuilder log = new StringBuilder("[");
	//	log.append("]");
		//Mockito.when(lc.getLog()).thenReturn(log.toString());
	//	when(Paths.get("")).thenReturn(null);
		try {
			lc.getLog();
			
		} catch (Exception e) {
			
			// TODO: handle exception
		}
	
		
	/*	ResultActions result = mvc.perform(get("/log")
				.contentType(MediaType.APPLICATION_JSON_VALUE));
		Content actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Content.class);
		
		result.andExpect(status().isOk()); */
		
	


	}
}

