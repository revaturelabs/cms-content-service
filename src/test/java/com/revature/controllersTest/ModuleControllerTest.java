package com.revature.controllersTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.MediaType;

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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anySet;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.ModuleController;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.repositories.ModuleRepository;
import com.revature.services.ModuleService;

@SpringBootTest(classes = CMSforceApplication.class)
public class ModuleControllerTest extends AbstractTestNGSpringContextTests {
	// Any time that two nulls appear in a test of a constructor, that is for a
	// feature that was created after the tests were created to allow them to pass.

	private static final int id = 1;
	private static final String subject = "subject";
	private static final long created = 1;
	private static final String affiliation = "affiliation";

	// allows us to send mocked http requests
	private MockMvc mvc;

	// allows json<->object conversion
	private ObjectMapper objMapper = new ObjectMapper();

	// the controller being tested
	@InjectMocks
	private ModuleController mc;

	// the service the controller depends on
	@Mock
	private ModuleService ms;

	// module being used in http requests
	private Module module;
	
	@Mock
	private ModuleRepository mr; 

	// links for modules
	private Set<Link> links = new HashSet<Link>();

	/**
	 * Initialize Mockito and mocking dependencies
	 */
	@BeforeClass
	public void setup() {
		// build mock MVC so can build mock requests
		mc = new ModuleController();
		mvc = MockMvcBuilders.standaloneSetup(mc).build();

		// enables mockito annotations
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Ensure clean module for each test
	 */
	@BeforeTest
	public void preTestSetup() {

		// caution: content and module not spring beans here
		Link link = new Link(id, new Content(), new Module(), affiliation,0);
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
	public void givenValidDataCreateModule() throws Exception {
		// given
		Mockito.when(ms.createModule(module)).thenReturn(module);

		// when
		ResultActions result = mvc.perform(
				post("/modules").contentType(MediaType.APPLICATION_JSON).content(objMapper.writeValueAsString(module)));
		Module actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Module.class);

		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// expect should get back same module
		assertEquals(actual, module, "Module was not created");
	}

	/**
	 * Tests retrieving all the modules from the back-end
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void testGetAllModules() throws Exception {
		Set<Module> parents = new HashSet<Module>();
		parents.add(new Module());

		// given
		Module moduleWithParent = new Module(id, subject, created, links, new HashSet<ReqLink>(), parents,
				new HashSet<Module>());

		Set<Module> modules = new HashSet<Module>();
		modules.add(moduleWithParent);
		//modules.add(module);
		
		Mockito.when(ms.getAllModules()).thenReturn(modules);

		// when
		ResultActions result = mvc.perform(get("/modules").contentType(MediaType.APPLICATION_JSON));
		String actual = result.andReturn().getResponse().getContentAsString();

		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// check in json format to get around compare warnings
		assertEquals(actual, convertToJSONModuleSetString(modules), "Failed to get back modules");
	}

	// tests returning module as a JSON string
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
	public void testGetModuleById() throws Exception {
		// given
		Mockito.when(ms.getModuleById(id)).thenReturn(module);

		// then
		ResultActions result = mvc.perform(get("/modules/" + id));
		Module actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Module.class);

		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// expect should get back same module
		assertEquals(actual, module, "Module was not created");
		// expect same id as return
		// assertEquals (actual.getId(), id, "Module has the incorrect id");
	}

	/**
	 * Tests removing a module from the back-end
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void deleteModule() throws Exception {
		// given
		Mockito.doNothing().when(ms).deleteModule(module);
		Mockito.when(ms.getModuleById(id)).thenReturn(module);

		// then
		ResultActions result = mvc.perform(delete("/modules/" + id));

		// then
		// expect status of OK
		result.andExpect(status().isOk());
	}

	@Test
	public void testGetAllRootModules() throws Exception {

		// module without parent
		Module rootModule = new Module(id, subject, created, links, new HashSet<ReqLink>(), new HashSet<Module>(),
				new HashSet<Module>());

		// given
		Set<Module> modules = new HashSet<Module>();
		modules.add(module);
		Mockito.when(ms.getAllRootModules()).thenReturn(modules);

		// when
		ResultActions result = mvc.perform(get("/modules/roots").contentType(MediaType.APPLICATION_JSON));
		String actual = result.andReturn().getResponse().getContentAsString();

		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// check in json format to get around compare warnings
		assertEquals(actual, convertToJSONModuleSetString(modules), "Failed to get back root modules");
	}

	@Test
	public void testGetChildrenByParentId() throws Exception {
		Module childModule = new Module();
		Set<Module> setWithChildModule = new HashSet<Module>();
		setWithChildModule.add(childModule);
		Module module = new Module(id, subject, created, links, new HashSet<ReqLink>(), new HashSet<Module>(),
				setWithChildModule);

		Mockito.when(ms.getChildrenByParentId(module.getId())).thenReturn(module.getChildren());

		// when
		ResultActions result = mvc
				.perform(get("/modules/" + module.getId() + "/children").contentType(MediaType.APPLICATION_JSON));
		String actual = result.andReturn().getResponse().getContentAsString();

		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// check in json format to get around compare warnings
		assertEquals(actual, convertToJSONModuleSetString(setWithChildModule), "Failed to get back child modules");
	}

	@Test
	public void testGetLinksByModuleId() throws Exception {

		Module module = new Module(id, subject, created, links, new HashSet<ReqLink>(), new HashSet<Module>(),
				new HashSet<Module>());

		Mockito.when(ms.getLinksByModuleId(id)).thenReturn(module.getLinks());

		ResultActions result = mvc
				.perform(get("/modules/" + module.getId() + "/links").contentType(MediaType.APPLICATION_JSON));

		Set<Link> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<Link>>() {
				});

		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// check in json format to get around compare warnings
		assertEquals(actual, links, "Failed to get back links");
	}

	@Test
	public void testGetReqLinksByModuleId() throws Exception {

		Set<ReqLink> reqLinks = new HashSet<ReqLink>();
		Module module = new Module(id, subject, created, links, reqLinks, new HashSet<Module>(),
				new HashSet<Module>());

		Mockito.when(ms.getRequestLinksByModuleId(id)).thenReturn(module.getReqLinks());

		ResultActions result = mvc
				.perform(get("/modules/" + module.getId() + "/req-links").contentType(MediaType.APPLICATION_JSON));

		Set<ReqLink> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<ReqLink>>() {
				});

		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// check in json format to get around compare warnings
		assertEquals(actual, reqLinks, "Failed to get back request links");
	}
	
	@Test
	public void testUpdateModule() throws Exception {


		Mockito.when(ms.updateModule(module)).thenReturn(module);

		ResultActions result = mvc
				.perform(put("/modules/" + module.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(module)));
		
		result.andExpect(status().isOk());
	}
		
		@Test
		public void testUpdateLinksById() throws Exception {
			Module moduleId = new Module();
			Set<Link> links = new HashSet<Link>();
			
			
			Mockito.when(ms.updateLinksByModuleId(anyInt(), anySet())).thenReturn(links);

			ResultActions result = mvc.perform(put("/modules/" + anyInt() + "/links")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objMapper.writeValueAsString(anySet())));

			result.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteModuleWithSpecificContent() throws Exception {
		Mockito.when(ms.getModuleById(anyInt())).thenReturn(module);
		Mockito.doNothing().when(ms).deleteModuleWithSpecificContent(module);
		
		ResultActions result = mvc.
				perform(delete("/modules/" + anyInt())
						.contentType(MediaType.APPLICATION_JSON)
						.param("type", "unique"));
		
		result.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteModuleWithAllContent() throws Exception {
		Mockito.when(ms.getModuleById(anyInt())).thenReturn(module);
		Mockito.doNothing().when(ms).deleteModuleWithAllContent(module);
		
		ResultActions result = mvc.
				perform(delete("/modules/" + anyInt())
						.contentType(MediaType.APPLICATION_JSON)
						.param("type", "all"));
		
		result.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteModuleWithTypeNotAllOrUnique() throws Exception {
		Mockito.when(ms.getModuleById(anyInt())).thenReturn(module);
		Mockito.doNothing().when(ms).deleteModuleWithAllContent(module);
		
		ResultActions result = mvc.
				perform(delete("/modules/" + anyInt())
						.contentType(MediaType.APPLICATION_JSON)
						.param("type", "else"));
		
		result.andExpect(status().isNotFound());
	}
}