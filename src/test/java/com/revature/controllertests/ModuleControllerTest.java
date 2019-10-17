package com.revature.controllertests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.ModuleController;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.services.ModuleService;

@SpringBootTest(classes = CMSforceApplication.class)
public class ModuleControllerTest extends AbstractTestNGSpringContextTests {
	//Any time that two nulls appear in a test of a constructor, that is for a feature that was created after the tests were created to allow them to pass.
	
	private static final int id = 1;
	private static final String subject = "subject";
	private static final long created = 1;
	private static final String affiliation = "affiliation";
	
	//allows us to send mocked http requests
	private MockMvc mvc;

	//allows json<->object conversion
    private ObjectMapper objMapper = new ObjectMapper();

	//the controller being tested
	@InjectMocks
	private ModuleController mc;
	
	//the service the controller depends on 
	//to touch the database.
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
		//caution: content and module not sprint beans here
		Link link = new Link (id,new Content(),new Module(),affiliation);
		links.add(link);
		module = new Module (id,subject,created,links,new HashSet<ReqLink>(),new HashSet<Module>(),new HashSet<Module>());
	}
	
	/**
	 * Test that when we hit the "/modules" endpoint 
	 * with a POST request, our servlet and controller 
	 * trigger the method that persists the module to 
	 * our DB.
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void testCreateModule() throws Exception {
		//given 
		Mockito.when(ms.createModule(module)).thenReturn(module);
		
		//when
		ResultActions result = mvc.perform(post ("/modules")
							.contentType (MediaType.APPLICATION_JSON)
							.content (objMapper.writeValueAsString(module)));
		Module actual = objMapper.readValue(result.andReturn()
							.getResponse().getContentAsString() , Module.class);
		
		//then
		//expect status of Post Request to be OK
		result.andExpect (status().isOk ());
		//expect should get back same module
		assertEquals (actual, module, "Module was not created");
	}
	
	/**
	 * Tests that when we hit the "/modules" endpoint, 
	 * with a GET request, our servlet and controller 
	 * triggers the the method that Retrieves all 
	 * module objects from our db. 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void testGetAllModules() throws Exception {
		//given 
		Set<Module> modules = new HashSet<Module> ();
		modules.add(module);
		Mockito.when(ms.getAllModules()).thenReturn(modules);
		
		//when
		ResultActions result = mvc.perform(get ("/modules")
				.contentType(MediaType.APPLICATION_JSON));
		String actual = result.andReturn().getResponse()
						.getContentAsString();
		
		//then
		//expect status of OK
		result.andExpect(status().isOk());
		//check in json format to get around compare warnings
		assertEquals (actual, convertToJSONModuleSetString(modules),
						"Failed to get back modules");
	}

	/*
	 * This Helper function converts a Set containing modules
	 * to a string that can be compared with other String 
	 * representations of module Sets. (String follows a JSON format)
	 */
	private String convertToJSONModuleSetString(Set<Module> allModules) throws Exception
	{
		StringBuilder result = new StringBuilder("[");
		for (Module mod : allModules)
		{
			result.append(objMapper.writeValueAsString(mc.moduleToJSONModule(mod)));
			result.append(",");
		}
		if (allModules.size() > 0)
			result.deleteCharAt(result.length() - 1);

		result.append("]");

		return result.toString();
	}

	/**
	 * Tests that when we hit the "/modules/{id}" endpoint, 
	 * with a GET request, our servlet and controller 
	 * triggers the the method that retrieves a 
	 * module object from our db based on an ID. 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void testGetModuleById() throws Exception {
		//given
		Mockito.when(ms.getModuleById(id)).thenReturn(module);
		
		//then
		ResultActions result = mvc.perform(get ("/modules/" + id));
		Module actual = objMapper.readValue(result.andReturn()
				.getResponse().getContentAsString() , Module.class); 
		
		//then
		//expect status of OK
		result.andExpect (status().isOk ());
		//expect should get back same module
		assertEquals (actual, module, "Module was not created");
		//expect same id as return
		//assertEquals (actual.getId(), id, "Module has the incorrect id");
	}
	
	/*
	 * Test that when hitting the "/modules/roots" 
	 * endpoint with a GET request...
	 * check ModuleController 
	 * @throws Exception - if the http request fails
	 */
	@Test(enabled=false)
	public void testGetAllRootModules() throws Exception
	{
		//Needs to be Implemented
	}
	
	/*
	 * Test that when hitting the "/modules/{id}" 
	 * endpoint with a GET request...
	 * check ModuleController 
	 * @throws Exception - if the http request fails
	 */
	@Test(enabled=false)
	public void testGetChildrenByModuleId() throws Exception
	{
		//Needs to be Implemented
	}
	
	/*
	 * Test that when hitting the "/modules/{id}/links" 
	 * endpoint with a GET request...
	 * check ModuleController 
	 * @throws Exception - if the http request fails
	 */
	@Test(enabled=false)
	public void testGetLinksByModuleId() throws Exception
	{
		//Needs to be Implemented
	}
	
	/*
	 * Test that when hitting the "/modules/{id}/req-links" 
	 * endpoint with a GET request...
	 * check ModuleController 
	 * @throws Exception - if the http request fails
	 */
	@Test(enabled=false)
	public void testGetReqLinksByModuleId() throws Exception
	{
		//Needs to be Implemented
	}
	
	/*
	 * Test that when hitting the "/modules/{id}" 
	 * endpoint with a PUT request, our servlet 
	 * and controller triggers the the method 
	 * that retrieves a module object from our db 
	 * based on an ID. 
	 * @throws Exception - if the http request fails
	 */
	@Test(enabled=false)
	public void testUpdateModule() throws Exception
	{
		//Needs to be Implemented
	}
	
	/**
	 * Tests that when we hit the "/modules/{id}" endpoint, 
	 * with a DELETE request, our servlet and controller 
	 * triggers the the method that retrieves a 
	 * module object from our db based on an ID. 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void deleteModule () throws Exception {
		//given
		Mockito.doNothing().when(ms).deleteModule(module);
		Mockito.when(ms.getModuleById(id)).thenReturn(module);
		
		//then
		ResultActions result = mvc.perform(delete ("/modules/" + id));
		
		//then
		//expect status of OK
		result.andExpect (status().isOk ());
	}
	
	
	/*
	 * Test that when hitting the 
	 * "/modules/{id}?type={type}" endpoint 
	 * with a DELETE request, our servlet 
	 * and controller triggers the the method 
	 * that deletes a module object, and some 
	 * level of associated content from our 
	 * db based on a Module ID. 
	 * @throws Exception - if the http request fails
	 */
	@Test(enabled=false)
	public void testDeleteModule() throws Exception
	{
		//Needs to be Implemented
	}
}
