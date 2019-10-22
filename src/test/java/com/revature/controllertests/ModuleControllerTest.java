package com.revature.controllertests;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.util.Modules;

import org.apache.http.entity.ContentType;
import org.hibernate.engine.query.spi.sql.NativeSQLQueryCollectionReturn;
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
import com.revature.entities.Request;
import com.revature.services.LinkService;
import com.revature.services.ModuleService;

@SpringBootTest(classes = CMSforceApplication.class)
public class ModuleControllerTest extends AbstractTestNGSpringContextTests {
	// Any time that two nulls appear in a test of a constructor, that is for a
	// feature that was created after the tests were created to allow them to pass.

	private static final int id = 1;
	private static final String subject = "subject";
	private static final long created = 1;
	private static final String affiliation = "affiliation";
	private static final String type = "type";

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

	private Request request;

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

		Set<Link> links = new HashSet<Link>();
		// caution: content and module not sprint beans here
		Link link = new Link(id, new Content(), new Module(), affiliation);
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
	public void getAllModules() throws Exception {
		// given
		Set<Module> modules = new HashSet<Module>();
		modules.add(module);
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

	// Helps with discrepency between
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
	public void getModuleById() throws Exception {
		// this mocks the method getting fake expected value
		Mockito.when(ms.getModuleById(id)).thenReturn(module);

		// this is a actual call to the method geetting back actual data from method
		ResultActions result = mvc.perform(get("/modules/" + id));
		Module actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Module.class);

		// then
		// expect status of OK
		// test(1) test if response to method actaully returns and is okay when getting
		// actual data
		result.andExpect(status().isOk());
		// expect should get back same module
		assertEquals(actual, module, "Module was not created");
		// test(2) is checking if actual actaully equals module
		// expect same id as return
		// assertEquals (actual.getId(), id, "Module has the incorrect id");
	}

	@Test
	public void getAllRootModulesStatusOk() throws Exception {
		// actaul result saving it into a varible
		ResultActions result = mvc.perform(get("/modules/roots").contentType(MediaType.APPLICATION_JSON));
		String actual = result.andReturn().getResponse().getContentAsString();
		// then
		// making sure we actaully hot a response
		result.andExpect(status().isOk());

	}

	@Test
	public void getAllRootModulesTestReturn() throws Exception {
		// waht is expected
		Set<Module> modules = new HashSet<Module>();
		modules.add(module);
		Mockito.when(ms.getAllRootModules()).thenReturn(modules);

		// actaul result saving it into a varible
		ResultActions result = mvc.perform(get("/modules/roots").contentType(MediaType.APPLICATION_JSON));
		String actual = result.andReturn().getResponse().getContentAsString();

		// checking to see if expected module = actual module
		// check if in json format, to get around compare warnings
		assertEquals(actual, convertToJSONModuleSetString(modules), "Failed to get back modules");

	}

	@Test
	public void getChildrenByModuleId() throws Exception {
		// given
		Set<Module> modules = new HashSet<Module>();
		modules.add(module);
		Mockito.when(ms.getChildrenByParentId(id)).thenReturn(modules);

		// when
		ResultActions result = mvc.perform(get("/modules/" + id + "/children").contentType(MediaType.APPLICATION_JSON));
		String actual = result.andReturn().getResponse().getContentAsString();

		// then
		result.andExpect(status().isOk());

		// check if JSON
		assertEquals(actual, convertToJSONModuleSetString(modules), "Failed to get back modules");
	}

	@Test
	public void getLinksByModuleId() throws Exception {
		// given
		Set<Module> modules = new HashSet<Module>();
		modules.add(module);
		Mockito.when(ms.getLinksByModuleId(id)).thenReturn(null);
		// when
		ResultActions result = mvc.perform(get("/modules/" + id + "/links").contentType(MediaType.APPLICATION_JSON));
		// then
		result.andExpect(status().isOk());

		// check if JSON

	}

	@Test
	public void getReqLinksByModuleId() throws Exception {
		// what is expected to return are list?
		Set<ReqLink> links = new HashSet<>();
		links.add(new ReqLink());
		Mockito.when(ms.getRequestLinksByModuleId(id)).thenReturn(links);

		// getting the actual results from the method
		ResultActions result = mvc
				.perform(get("/modules/" + id + "/req-links").contentType(MediaType.APPLICATION_JSON));

		Set<ReqLink> actual = objMapper.readValue
		// taking the mocked result from the methood
		(result.andReturn().getResponse().getContentAsString(), objMapper
				// maps that into a reqLink object
				.getTypeFactory().constructCollectionType(Set.class, ReqLink.class));

		// comparing the results
		assertEquals(actual, links, "failed to get back modules");

	}

	@Test
	public void DeleteModule() throws Exception {
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		Mockito.doNothing().when(ms).deleteModule(module);

		ResultActions result = mvc
				.perform(delete("/modules/" + module.getId()).contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
	}

	@Test
	public void deleteModuleVerifyDeleteModuleWithAllContent() throws Exception {
		Mockito.when(ms.getModuleById(module.getId())).thenReturn(module);

		mvc.perform(delete("/modules/" + module.getId()).contentType(MediaType.APPLICATION_JSON).param("type", "all")
				.content(objMapper.writeValueAsString(module)));

		verify(ms, times(1)).deleteModuleWithAllContent(module);
	}

	@Test
	public void deleteModuleVerifyDeleteModuleWithSpecificContent() throws Exception {
		Set<Module> modules = new HashSet<Module>();
		modules.add(module);

		Mockito.doNothing().when(ms).deleteModuleWithSpecificContent(module);

		ResultActions result = mvc.perform(delete("/modules/" + id).contentType(MediaType.APPLICATION_JSON)
				.param("type", "unique").content(objMapper.writeValueAsString(module)));
		String actual = result.andReturn().getResponse().getContentAsString();

		result.andExpect(status().isOk());

		// verify(ms).deleteModuleWithSpecificContent(module);
	}

	@Test
	public void deleteModuleVerifyCheckWhenTypeIsWrongStautsNotFound() throws Exception {
		Set<Module> modules = new HashSet<Module>();
		modules.add(module);

		Mockito.doNothing().when(ms).deleteModuleWithSpecificContent(module);

		ResultActions result = mvc.perform(delete("/modules/" + id).contentType(MediaType.APPLICATION_JSON)
				.param("type", "uniue").content(objMapper.writeValueAsString(module)));
		String actual = result.andReturn().getResponse().getContentAsString();

		result.andExpect(status().isNotFound());

		// verify(ms).deleteModuleWithSpecificContent(module);
	}

	@Test
	public void updateModuleTestEquals() throws Exception {
		// set the expected
		Set<Module> modules = new HashSet<Module>();
		modules.add(module);

		// Mockito.doNothing().when(ms).updateModule(module);
		Mockito.when(ms.updateModule(module)).thenReturn(module);

		ResultActions result = mvc.perform(put("/modules/" + id).contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(module)));

		Module actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Module.class);

		result.andExpect(status().isOk());

		// checking if the test expected is equal to the actual
		// assertEquals(actual, convertToJSONModuleSetString(modules), "failed to
		// retrieve modules");
	}

}
