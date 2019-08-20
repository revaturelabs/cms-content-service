package com.revature.controllertests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.ModuleController;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.services.ModuleService;

@SpringBootTest(classes = CMSforceApplication.class)
public class ModuleControllerTest extends AbstractTestNGSpringContextTests {
	
	private static final int id = 1;
	private static final String subject = "subject";
	private static final long created = 1;
	private static final String affiliation = "affiliation";
	
	//allows us to send mocked http requests
	private MockMvc mvc;

	//allows json<->object conversion
	@Autowired
	private Gson gson;

	//the controller being tested
	@InjectMocks
	private ModuleController mc;
	
	//the service the controller depends on 
	@Mock
	private ModuleService ms;
	
	//module being used in http requests
	private Module module;
	
	/**
	 * Initialize Mockito and mocking dependencies 
	 */
	@BeforeClass
	public void setup() {
		//build mock MVC so can build mock requests
		mc = new ModuleController ();
		mvc = MockMvcBuilders.standaloneSetup(mc).build();
		
		//enables mockito annotations
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Ensure clean module for each test
	 */
	@BeforeTest 
	public void preTestSetup () {
		Set<Link> links = new HashSet<Link> ();
		Link link = new Link (id,id,id,affiliation);
		links.add(link);
		
		module = new Module (id,subject,created,links);
	}
	
	/**
	 * Test adding a new module to the back-end
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void givenValidDataCreateModule () throws Exception {
		//given 
		Mockito.when(ms.createModule(module)).thenReturn(module);
		
		//when
		ResultActions result = mvc.perform(post ("/module")
							.contentType (MediaType.APPLICATION_JSON)
							.content (gson.toJson(module)));
		Module actual = gson.fromJson(result.andReturn()
							.getResponse().getContentAsString() , Module.class);
		
		//then
		//expect status of OK
		result.andExpect (status().isOk ());
		//expect should get back same module
		assertEquals (actual, module, "Module was not created");
	}
	
	/**
	 * Tests retrieving all the modules from the back-end
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void getAllModules () throws Exception {
		//given 
		Set<Module> modules = new HashSet<Module> ();
		modules.add(module);
		Mockito.when(ms.getAllModules()).thenReturn(modules);
		
		//when
		ResultActions result = mvc.perform(get ("/module")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(module)));
		String actual = result.andReturn().getResponse()
						.getContentAsString();
		
		//then
		//expect status of OK
		result.andExpect(status().isOk());
		//check in json format to get around compare warnings
		assertEquals (actual, gson.toJson(modules), 
						"Failed to get back modules");
	}
	
	/**
	 * Tests retrieving a module from the back-end by id
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void getModuleById () throws Exception {
		//given
		Mockito.when(ms.getModuleById(id)).thenReturn(module);
		
		//then
		ResultActions result = mvc.perform(get ("/module/" + id));
		Module actual = gson.fromJson(result.andReturn()
				.getResponse().getContentAsString() , Module.class); 
		
		//then
		//expect status of OK
		result.andExpect (status().isOk ());
		//expect should get back same module
		assertEquals (actual, module, "Module was not created");
		//expect same id as return
		assertEquals (actual.getId(), id, "Module has the incorrect id");
	}
	
	/**
	 * Tests removing a module from the back-end
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void deleteModule () throws Exception {
		//given
		Mockito.doNothing().when(ms).deleteModule(module);
		Mockito.when(ms.getModuleById(id)).thenReturn(module);
		
		//then
		ResultActions result = mvc.perform(delete ("/module/" + id));
		
		//then
		//expect status of OK
		result.andExpect (status().isOk ());
	}
}
