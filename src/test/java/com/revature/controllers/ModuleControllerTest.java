package com.revature.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.services.ModuleService;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@SpringBootTest(classes = CMSforceApplication.class)
public class ModuleControllerTest extends AbstractTestNGSpringContextTests {
	private static final int id = 1;
	private static final String subject = "subject";
	private static final long created = 1;
	private static final String affiliation = "affiliation";
	private static final int priority = 0;
	private static Set<Link> links = new HashSet<>();
	private static Link link = new Link (id,new Content(),new Module(),affiliation,priority);

	
	//allows us to send mocked http requests
	private MockMvc mvc;
	private ObjectMapper objMapper = new ObjectMapper();
	@InjectMocks
	private ModuleController mc;

	@Mock
	private ModuleService ms;
	private Module module;

	/**
	 * Initialize Mockito and mocking dependencies
	 */
	@BeforeClass
	public void setup() {
		mc = new ModuleController();
		mvc = MockMvcBuilders.standaloneSetup(mc).build();

		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Ensure clean module for each test
	 */
	@BeforeTest 
	public void preTestSetup () {

		 links = new HashSet<Link> ();
		//caution: content and module not sprint beans here

		 link = new Link (id,new Content(),new Module(),affiliation,priority);

		links.add(link);
		module = new Module(id, subject, created, links, new HashSet<ReqLink>(), new HashSet<Module>(),
				new HashSet<Module>());
	}

	/**
	 * Test adding a new module to the back-end
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void givenValidDataCreateModuleStatusOK() throws Exception {
		Mockito.when(ms.createModule(module)).thenReturn(module);
		ResultActions result = mvc.perform(
				post("/modules").contentType(MediaType.APPLICATION_JSON).content(objMapper.writeValueAsString(module)));
		result.andExpect(status().isOk());
	}

	@Test
	public void givenValidDataCreateModuleTestReturnTrue() throws Exception {
		Mockito.when(ms.createModule(module)).thenReturn(module);
		ResultActions result = mvc.perform(
				post("/modules").contentType(MediaType.APPLICATION_JSON).content(objMapper.writeValueAsString(module)));
		Module actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Module.class);
		assertEquals(actual, module, "Module was not created");
	}

	/**
	 * Tests retrieving all the modules from the back-end
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void getAllModulesStatusOK() throws Exception {
		Set<Module> modules = new HashSet<Module>();
		modules.add(module);
		Mockito.when(ms.getAllModules()).thenReturn(modules);
		ResultActions result = mvc.perform(get("/modules").contentType(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}

	@Test
	public void getAllModulesTestReturnTrue() throws Exception {
		Set<Module> modules = new HashSet<Module>();
		modules.add(module);
		Mockito.when(ms.getAllModules()).thenReturn(modules);
		ResultActions result = mvc.perform(get("/modules").contentType(MediaType.APPLICATION_JSON));
		String actual = result.andReturn().getResponse().getContentAsString();
		assertEquals(actual, convertToJSONModuleSetString(modules), "Failed to get back modules");
	}

	private String convertToJSONModuleSetString(Set<Module> allModules) throws Exception {
		StringBuilder result = new StringBuilder("[");
		for (Module mod : allModules) {
			result.append(objMapper.writeValueAsString(mc.moduleToJSONModule(mod)));
			result.append(",");
		}
		if (allModules.size() > 0)
			result.deleteCharAt(result.length() - 1);
		result.append("]");
		return result.toString();
	}

	/**
	 * Tests retrieving a module from the back-end by id
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void getModuleByIdStatusOK() throws Exception {
		Mockito.when(ms.getModuleById(id)).thenReturn(module);
		ResultActions result = mvc.perform(get("/modules/" + id));
		result.andExpect(status().isOk());
	}

	@Test
	public void getModuleById() throws Exception {
		Mockito.when(ms.getModuleById(id)).thenReturn(module);
		ResultActions result = mvc.perform(get("/modules/" + id));
		Module actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Module.class);
		assertEquals(actual, module, "Module was not created");
	}

	/**
	 * Tests removing a module from the back-end
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void deleteModuleStatusOK() throws Exception {
		Mockito.doNothing().when(ms).deleteModule(module);
		Mockito.when(ms.getModuleById(id)).thenReturn(module);
		ResultActions result = mvc.perform(delete("/modules/" + id));
		result.andExpect(status().isOk());
	}
	
	/**
	 * Tests that updating a module based on its id, when successful, will return a status code of 200
	 * @throws Exception 
	 */
	@Test
	public void updateModuleBasedOnId () throws Exception {
		//given
		links.add(link);
		Mockito.when(ms.updateLinksByModuleId(id, links)).thenReturn(links);
		//then
		ResultActions result = mvc.perform(put ("/modules/"+id+"/links").contentType(MediaType.APPLICATION_JSON)
				.content (objMapper.writeValueAsString(links)));
		
		//expect status of OK
		result.andExpect (status().isOk ());
		//expect controller to return set of links
		String actual = result.andReturn().getResponse()
				.getContentAsString();
		String linksJson = objMapper.writeValueAsString(links);
		
		assertEquals (linksJson, actual);
		
	}
}