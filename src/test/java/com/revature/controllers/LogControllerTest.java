package com.revature.controllers;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
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
import org.springframework.test.web.servlet.RequestBuilder;
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
	// BufferedReader br =
	// Files.newBufferedReader(Paths.get("/home/ec2-user/.jenkins/workspace/CMSforce/CMSforce/src/main/resources/ErrorLog.html"));

	@BeforeClass
	public void setup() {
		lc = new LogController();
		mvc = MockMvcBuilders.standaloneSetup(lc).build();

		MockitoAnnotations.initMocks(this);
	}

	@BeforeTest
	public void preTestSetup() {

	}

//	@Test
//	public void getLogVertify() throws Exception {
//		// StringBuilder log = new StringBuilder("[");
//		// String actual ="";
//		BufferedReader br = Files.newBufferedReader(
//				Paths.get("/home/ec2-user/.jenkins/workspace/CMSforce/CMSforce/src/main/resources/ErrorLog.html"));
//		String line;
//		line = br.readLine();
//		Mockito.when(lc.getLog()).thenReturn(log.toString());
//
//		ResultActions result = mvc
//				.perform(get("/log/home/ec2-user/.jenkins/workspace/CMSforce/CMSforce/src/main/resources/ErrorLog.html")
//						.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(log)));
//		Log log = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Log.class);
//
//		result.andExpect(status().isOk());
//	}
}
