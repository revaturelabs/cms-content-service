package com.revature.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockitoSession;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.ReqLinkController;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.services.ReqLinkService;

@SpringBootTest(classes = CMSforceApplication.class)
public class ReqLinkControllerTest  {
	
	//what u need to mock is everything that is autowired in class testing
	private MockMvc mvc;
	private ObjectMapper objMapper = new ObjectMapper();
	
	@InjectMocks
	private ReqLinkController rlc;
	
	@Mock
	private ReqLinkService rls;
	
	@BeforeClass
	public void setup() {
		rlc = new ReqLinkController();
		mvc = MockMvcBuilders.standaloneSetup(rlc).build();
		
	}
	@BeforeMethod
	public void reinitMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void CreateReqLinkTestIsOk() throws Exception {
		//needed to mock the reqLink it takes these specfic parameters
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Request request = new Request(1, "title", "format", "description", content, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		// this is the object that will eventually hold actual result
		ReqLink reqLink = new ReqLink(0, request, module, "affiliation");
		
		Mockito.when(rls.createReqLink(reqLink)).thenReturn(reqLink);
		
		ResultActions result = mvc.perform(post("/req-links").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(reqLink)));
		
		
		ReqLink actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), ReqLink.class);
		result.andExpect(status().isOk());
		
		
		
		
		 
	}
	@Test
	public void CreateReqLinkTestVertify() throws Exception {
		//needed to mock the reqLink it takes these specfic parameters
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Request request = new Request(1, "title", "format", "description", content, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		// this is the object that will eventually hold actual result
		ReqLink reqLink = new ReqLink(0, request, module, "affiliation");
		
		Mockito.when(rls.createReqLink(reqLink)).thenReturn(reqLink);
		
		ResultActions result = mvc.perform(post("/req-links").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(reqLink)));
		
		
		ReqLink actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), ReqLink.class);
	//	result.andExpect(status().isOk());
		Mockito.verify(rls).createReqLink(reqLink);
	}
	
	@Test
	public void CreateReqLinkTestIsAssertEquals() throws Exception {
		//needed to mock the reqLink it takes these specfic parameters
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Request request = new Request(1, "title", "format", "description", content, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		// this is the object that will eventually hold actual result
		ReqLink reqLink = new ReqLink(0, request, module, "affiliation");
		
		Mockito.when(rls.createReqLink(reqLink)).thenReturn(reqLink);
		
		ResultActions result = mvc.perform(post("/req-links").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(reqLink)));
		
		
		ReqLink actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), ReqLink.class);
		result.andExpect(status().isOk());
		Mockito.verify(rls).createReqLink(reqLink);
		Assert.assertEquals(actual, reqLink, "Failed to retrieve expected reqlink");
	}
	
	
	@Test
	public void getAllReqLinkTestIsOk() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Request request = new Request(1, "title", "format", "description", content, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		ReqLink reqLink = new ReqLink(0, request, module, "affiliation");
		//wat is Set<Link>
		Set<ReqLink> allReqLinks = new HashSet<ReqLink>();
		allReqLinks.add(reqLink);
		
		Mockito.when(rls.getAllReqLinks()).thenReturn(allReqLinks);
		
		ResultActions result = mvc.perform(get("/req-links").contentType(MediaType.APPLICATION_JSON_VALUE));
				//.content(objMapper.writeValueAsString(allReqLinks)));
		// this is the object that will eventually hold actual result
		Set<ReqLink> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<ReqLink>>() {
		});
		
		result.andExpect(status().isOk());
	
		
		
	}
	@Test
	public void getAllReqLinkTestIsVerify() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Request request = new Request(1, "title", "format", "description", content, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		ReqLink reqLink = new ReqLink(0, request, module, "affiliation");
		//wat is Set<Link>
		Set<ReqLink> allReqLinks = new HashSet<ReqLink>();
		allReqLinks.add(reqLink);
		
		Mockito.when(rls.getAllReqLinks()).thenReturn(allReqLinks);
		
		ResultActions result = mvc.perform(get("/req-links").contentType(MediaType.APPLICATION_JSON_VALUE));
				//.content(objMapper.writeValueAsString(allReqLinks)));
		// this is the object that will eventually hold actual result
		Set<ReqLink> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<ReqLink>>() {
		});
		
		
		Mockito.verify(rls).getAllReqLinks();
		
		
		
	}
	@Test
	public void getAllReqLinkTestAssertEquals() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Request request = new Request(1, "title", "format", "description", content, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		ReqLink reqLink = new ReqLink(0, request, module, "affiliation");
		//wat is Set<Link>
		Set<ReqLink> allReqLinks = new HashSet<ReqLink>();
		allReqLinks.add(reqLink);
		
		Mockito.when(rls.getAllReqLinks()).thenReturn(allReqLinks);
		
		ResultActions result = mvc.perform(get("/req-links").contentType(MediaType.APPLICATION_JSON_VALUE));
				//.content(objMapper.writeValueAsString(allReqLinks)));
		// this is the object that will eventually hold actual result
		Set<ReqLink> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<ReqLink>>() {
		});
		
		result.andExpect(status().isOk());
		Mockito.verify(rls).getAllReqLinks();
		Assert.assertEquals(actual, allReqLinks, "Failed to retrieve expected reqlinks");
		
		
	}
	
	@Test
	public void getReqLinkByIdTestIsOk() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Request request = new Request(1, "title", "format", "description", content, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		ReqLink reqLink = new ReqLink(0, request, module, "affiliation");
		Mockito.when(rls.getReqLinkById(reqLink.getId())).thenReturn(reqLink);		
		
		ResultActions result = mvc.perform(get("/req-links/" + reqLink.getId()).contentType(MediaType.APPLICATION_JSON_VALUE));
		
		ReqLink actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), ReqLink.class);
		result.andExpect(status().isOk());
		
	}
	@Test
	public void getReqLinkByIdTestVerfiy() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Request request = new Request(1, "title", "format", "description", content, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		ReqLink reqLink = new ReqLink(0, request, module, "affiliation");
		Mockito.when(rls.getReqLinkById(reqLink.getId())).thenReturn(reqLink);		
		
		ResultActions result = mvc.perform(get("/req-links/" + reqLink.getId()).contentType(MediaType.APPLICATION_JSON_VALUE));
		
		ReqLink actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), ReqLink.class);
	
		Mockito.verify(rls).getReqLinkById(reqLink.getId());
	
	}
	@Test
	public void getReqLinkByIdTestAssertEquals() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Request request = new Request(1, "title", "format", "description", content, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		ReqLink reqLink = new ReqLink(0, request, module, "affiliation");
		Mockito.when(rls.getReqLinkById(reqLink.getId())).thenReturn(reqLink);		
		
		ResultActions result = mvc.perform(get("/req-links/" + reqLink.getId()).contentType(MediaType.APPLICATION_JSON_VALUE));
		
		ReqLink actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), ReqLink.class);
	
		Assert.assertEquals(actual, reqLink, "failed to retrieve expected reqlink");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void GetSearchResultsTest() throws Exception {
		String[] params = { "title", "format", "modules" };
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Request request = new Request(1, "title", "format", "description", content, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		List<Integer> listOfModuleIds = new ArrayList<>();
		//String listOfModuleIds = "1";
		//listOfModuleIds.add(module.getId());
		//listOfModuleIds.add(module.getId());
		ReqLink reqLink = new ReqLink(0, request, module, "affiliation");
		
		Set<ReqLink> allReqLinks = new HashSet<ReqLink>();
		allReqLinks.add(reqLink);
		Set<Set<ReqLink>> setOfSets = new HashSet<>();
		setOfSets.add(allReqLinks);
		
		Mockito.when(rls.filter("title", "format", listOfModuleIds)).thenReturn(setOfSets);
		
		ResultActions result = mvc
				.perform(get("/req-links/").param(params[0], request.getTitle()).param(params[1], request.getFormat())
						.param(params[2], "1"));
		Set<Set<ReqLink>> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), 
				new TypeReference<Set<Set<ReqLink>>>() {
				});
	//	ResponseEntity<T>
		result.andExpect(status().isOk());
		//Mockito.verify(rls).filter("title", "format", listOfModuleIds);
		//Assert.assertEquals(actual, setOfSets, "Failed to retrieve expected links");
		//Mockito.verify(rls).filter(anyString(), anyString(), any(ArrayList.class));
		//Assert.assertEquals(actual, setOfSets, "failed to retrieve expected reqlinks");
		
	}
	@Test
	public void updateLinkTestIsOk() throws Exception {
		
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Request request = new Request(1, "title", "format", "description", content, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		ReqLink reqLink = new ReqLink(0, request, module, "affiliation");
		
		Mockito.when(rls.updateReqLink(reqLink)).thenReturn(reqLink);
		
		ResultActions result = mvc.perform(put("/req-links/" + reqLink.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(reqLink)));
	
		ReqLink actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), ReqLink.class);
		
		result.andExpect(status().isOk());

		
	
	}
	@Test
	public void updateLinkTestIsVerify() throws Exception {
		
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Request request = new Request(1, "title", "format", "description", content, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		ReqLink reqLink = new ReqLink(0, request, module, "affiliation");
		
		Mockito.when(rls.updateReqLink(reqLink)).thenReturn(reqLink);
		
		ResultActions result = mvc.perform(put("/req-links/" + reqLink.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(reqLink)));
	
		ReqLink actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), ReqLink.class);
		

		Mockito.verify(rls).updateReqLink(reqLink);
		
		
	
	}
	@Test
	public void updateLinkTestIsEqual() throws Exception {
		
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Request request = new Request(1, "title", "format", "description", content, 1L, 1L, new HashSet<ReqLink>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		ReqLink reqLink = new ReqLink(0, request, module, "affiliation");
		
		Mockito.when(rls.updateReqLink(reqLink)).thenReturn(reqLink);
		
		ResultActions result = mvc.perform(put("/req-links/" + reqLink.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(reqLink)));
	
		ReqLink actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), ReqLink.class);
		
	
		Assert.assertEquals(actual, reqLink, "failed to update ReqLink");
		
	
	}
	
	@Test
	public void deleteReqLink() throws Exception {

		int proxyReqLinkId = 1;
		
		Mockito.doNothing().when(rls).deleteReqLinkById(proxyReqLinkId);
		ResultActions result = mvc.perform(delete("/req-links/" + proxyReqLinkId).contentType(MediaType.APPLICATION_JSON_VALUE));
		result.andExpect(status().isOk());
	}
	
	
	// @formatter:on

	
	
}
