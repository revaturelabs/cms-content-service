package com.revature.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.MetricsController;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.services.ContentService;
import com.revature.services.ModuleService;
import com.revature.services.SearchService;
import com.revature.services.TimegraphService;
import com.revature.util.MetricsData;
import com.revature.util.TimeGraphData;

@SpringBootTest(classes = CMSforceApplication.class)
public class MetricsControllerTest extends AbstractTestNGSpringContextTests {
	@InjectMocks
	private MetricsController metricsController;
	@Mock
	private ModuleService moduleService;
	@Mock
	private ContentService contentService;
	@Mock
	private SearchService searchService;
	@Mock
	private TimegraphService timegraphService;

	private long timeRange;
	private Map<String, Object> filter;
	private MockMvc mvc;
	private ObjectMapper objMapper = new ObjectMapper();
	private ResultActions result;
	private MetricsData actual;
	private Module module;
	
	private Set<Module> modules = new HashSet<>();
	private TimeGraphData timeGraphData = new TimeGraphData();
	private Integer numCode = 0;
	private Integer numDoc = 0;
	private Integer numPpt = 0;
	private int modSize;
	private double avgMods = 0;
	private Map<String, Integer> contentFormats;

	@BeforeClass
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(metricsController).build();

		timeRange = 1000;
		filter = new HashMap<String, Object>();
		module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(), new HashSet<Module>(),
				new HashSet<Module>());
		filter.put("modules", module.getId());
		
		modules.add(module);
		modSize = modules.size();
	}

	@BeforeMethod
	public void beforeEachTest() throws JsonProcessingException, Exception {
		contentFormats = new HashMap<>();
	}

	@Test
	public void testidsInNotEmpty() throws JsonProcessingException, Exception {
		Mockito.when(moduleService.getModuleById(Mockito.anyInt())).thenReturn(module);
		Mockito.when( moduleService.getAllModules()).thenReturn(modules);
		Mockito.when(timegraphService.getTimeGraphData(Mockito.anyLong(), Mockito.anySet())).thenReturn(timeGraphData);
		
		MetricsData gatheredMetrics = new MetricsData(
				numCode, numDoc, numPpt, 
				modSize, avgMods, timeGraphData);
		
		result = mvc.perform(post("/metrics/" + timeRange).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(filter)));
		actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), MetricsData.class);

		assertEquals(actual, gatheredMetrics);
	}
	
	@Test
	public void testidsInNotEmptyCodeExists() throws JsonProcessingException, Exception {
		Mockito.when(moduleService.getModuleById(Mockito.anyInt())).thenReturn(module);
		Mockito.when( moduleService.getAllModules()).thenReturn(modules);
		Mockito.when(timegraphService.getTimeGraphData(Mockito.anyLong(), Mockito.anySet())).thenReturn(timeGraphData);
		contentFormats.put("Code", 5);
		Mockito.when(contentService.getFormatCount(Mockito.anySet())).thenReturn(contentFormats);
		MetricsData gatheredMetrics = new MetricsData(
				contentFormats.get("Code"), numDoc, numPpt, 
				modSize, avgMods, timeGraphData);
		
		result = mvc.perform(post("/metrics/" + timeRange).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(filter)));
		actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), MetricsData.class);

		assertEquals(actual, gatheredMetrics);
	}
	
	@Test
	public void testidsInNotEmptyPowerpointExists() throws JsonProcessingException, Exception {
		Mockito.when(moduleService.getModuleById(Mockito.anyInt())).thenReturn(module);
		Mockito.when( moduleService.getAllModules()).thenReturn(modules);
		Mockito.when(timegraphService.getTimeGraphData(Mockito.anyLong(), Mockito.anySet())).thenReturn(timeGraphData);
		contentFormats.put("Powerpoint", 3);
		Mockito.when(contentService.getFormatCount(Mockito.anySet())).thenReturn(contentFormats);
		MetricsData gatheredMetrics = new MetricsData(
				numCode, numDoc, contentFormats.get("Powerpoint"), 
				modSize, avgMods, timeGraphData);
		
		result = mvc.perform(post("/metrics/" + timeRange).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(filter)));
		actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), MetricsData.class);

		assertEquals(actual, gatheredMetrics);
	}
	
	@Test
	public void testidsInNotEmptyDocumentExists() throws JsonProcessingException, Exception {
		Mockito.when(moduleService.getModuleById(Mockito.anyInt())).thenReturn(module);
		Mockito.when( moduleService.getAllModules()).thenReturn(modules);
		Mockito.when(timegraphService.getTimeGraphData(Mockito.anyLong(), Mockito.anySet())).thenReturn(timeGraphData);
		contentFormats.put("Document", 8);
		Mockito.when(contentService.getFormatCount(Mockito.anySet())).thenReturn(contentFormats);
		MetricsData gatheredMetrics = new MetricsData(
				numCode, contentFormats.get("Document"), numPpt, 
				modSize, avgMods, timeGraphData);
		
		result = mvc.perform(post("/metrics/" + timeRange).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(filter)));
		actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), MetricsData.class);

		assertEquals(actual, gatheredMetrics);
	}
	
	@Test
	public void testidsInEmpty() throws JsonProcessingException, Exception {
		Mockito.when(moduleService.getModuleById(Mockito.anyInt())).thenReturn(module);
		Mockito.when( moduleService.getAllModules()).thenReturn(modules);
		Mockito.when(timegraphService.getTimeGraphData(Mockito.anyLong(), Mockito.anySet())).thenReturn(timeGraphData);
		
		MetricsData gatheredMetrics = new MetricsData(
				numCode, numDoc, numPpt, 
				modSize, avgMods, timeGraphData);
		
		result = mvc.perform(post("/metrics/" + timeRange).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(new HashMap<String, Object>())));
		actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), MetricsData.class);

		assertEquals(actual, gatheredMetrics);
	}

}
