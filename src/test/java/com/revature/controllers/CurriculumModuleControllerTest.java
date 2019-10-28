package com.revature.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.CurriculumModuleController;
import com.revature.entities.Curriculum;
import com.revature.entities.CurriculumModule;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.services.CurriculumModuleService;

/**
 * Tests the {@link com.revature.controllers.CurriculumModuleController CurriculumModuleController} class.
 */
@SpringBootTest(classes = CMSforceApplication.class)
public class CurriculumModuleControllerTest {

	private MockMvc mvc;
	private ObjectMapper objMapper = new ObjectMapper();

	@InjectMocks
	private CurriculumModuleController curriculumModuleController;

	@Mock
	private CurriculumModuleService curriculumModuleService;

	/**
	 * Set up for testing the CurriculumModuleController.
	 * Instantiates mocks.
	 */
	@BeforeClass
	public void setup() {
		curriculumModuleController = new CurriculumModuleController();
		mvc = MockMvcBuilders.standaloneSetup(curriculumModuleController).build();
	}

	/**
	 * Initializes mocks before each method.
	 */
	@BeforeMethod
	public void reinitMocks() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Tests the result of the {@link com.revature.controllers.CurriculumModuleController#createCurrModule(CurriculumModule) createCurrModule(CurriculumModule)} method.
	 * 
	 * This method assumes that the {@link com.revature.entities.CurriculumModule CurriculumModule} being created is valid and that the {@link com.revature.services.CurriculumModuleService#createCurriculumModule(CurriculumModule) createCurriculumModule(CurriculumModule)}
	 * in the {@link com.revature.services.CurriculumModuleService CurriculumModuleService} returns a given CurriculumModule.
	 * 
	 * The expected result is a CurriculumModule mapped from a ResponseEntity<CurriculumModule> matching the test CurriculumModule.
	 * @throws Exception
	 */
	@Test
	public void testCreateCurriculumModule() throws Exception {
		Curriculum curriculum = new Curriculum(1, "Java Full Stack");
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		CurriculumModule curriculumModule = new CurriculumModule(1, curriculum.getId(), module, 1);

		Mockito.when(curriculumModuleService.createCurriculumModule(curriculumModule)).thenReturn(curriculumModule);

		ResultActions result = mvc.perform(post("/curriculum-modules").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(curriculumModule)));

		CurriculumModule actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				CurriculumModule.class);

		Assert.assertEquals(actual, curriculumModule, "Failed to retrieve expected curriculum module");
	}

	/**
	 * Tests the {@link com.revature.controllers.CurriculumModuleController#getCurrModuleById(int) getCurrModuleById(int)} method from the {@link com.revature.controllers.CurriculumModuleController CurriculumModuleController}.
	 * 
	 * This method assumes that the {@link com.revature.services.CurriculumModuleService#getCurriculumModuleById(int) getCurriculumModuleById(int)} method in the {@link com.revature.services.CurriculumModuleService CurriculumModuleService}
	 * returns a given {@link com.revature.entities.CurriculumModule CurriculumModule}.
	 * 
	 * The expected result is a CurriculumModule mapped from a ResponseEntity<CurriculumModule> matching the test CurriculumModule.
	 * @throws Exception
	 */
	@Test
	public void testGetCurriculumModuleById() throws Exception {
		Curriculum curriculum = new Curriculum(1, "Java Full Stack");
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		CurriculumModule curriculumModule = new CurriculumModule(1, curriculum.getId(), module, 1);

		Mockito.when(curriculumModuleService.getCurriculumModuleById(curriculumModule.getId()))
				.thenReturn(curriculumModule);

		ResultActions result = mvc.perform(
				get("/curriculum-modules/" + curriculumModule.getId()).contentType(MediaType.APPLICATION_JSON_VALUE));

		CurriculumModule actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				CurriculumModule.class);

		Assert.assertEquals(actual, curriculumModule, "Failed to retrieve expected curriculum Module");
	}

	/**
	 * Tests the {@link com.revature.controllers.CurriculumModuleController#getAllCurrModules() getAllCurrModules()} method from the {@link com.revature.controllers.CurriculumModuleController CurriculumModuleController}.
	 * 
	 * This method assumes that the {@link com.revature.services.CurriculumModuleService#getAllCurriculumModules() getAllCurriculumModules()} method in the {@link com.revature.services.CurriculumModuleService CurriculumModuleService}
	 * returns a given Set<{@link com.revature.entities.CurriculumModule CurriculumModule}>.
	 * 
	 * The expected result is a Set<CurriculumModule> mapped from a ResponseEntity<Set<CurriculumModule>> matching the test Set<CurriculumModule>.
	 * @throws Exception
	 */
	@Test
	public void testGetAllCurriculumModules() throws Exception {
		Curriculum curriculum = new Curriculum(1, "Java Full Stack");
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		CurriculumModule curriculumModule = new CurriculumModule(1, curriculum.getId(), module, 1);

		Set<CurriculumModule> allCurriculumModules = new HashSet<CurriculumModule>();
		allCurriculumModules.add(curriculumModule);

		Mockito.when(curriculumModuleService.getAllCurriculumModules()).thenReturn(allCurriculumModules);

		ResultActions result = mvc.perform(get("/curriculum-modules").contentType(MediaType.APPLICATION_JSON_VALUE));

		Set<CurriculumModule> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<CurriculumModule>>() {
				});

		Assert.assertEquals(actual, allCurriculumModules, "Failed to retrieve expected curriculum modules");
	}

	/**
	 * Tests the return status of the {@link com.revature.controllers.CurriculumModuleController#updateCurrModules(int, Set) updateCurrModules(int, Set)} method from the {@link com.revature.controllers.CurriculumModuleController CurriculumModuleController}.
	 * 
	 * This method assumes that the {@link com.revature.services.CurriculumModuleService#updateCurriculumModule(Set) updateCurriculumModule(Set)} method in the {@link com.revature.services.CurriculumModuleService CurriculumModuleService}
	 * returns a given ArrayList<{@link com.revature.entities.CurriculumModule CurriculumModule}>.
	 * 
	 * The expected result is a status of OK (200).
	 * @throws Exception
	 */
	@Test
	public void testUpdateCurriculumModule_StatusOk() throws Exception {
		Curriculum curriculum = new Curriculum(1, "Java Full Stack");
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		CurriculumModule curriculumModule = new CurriculumModule(1, curriculum.getId(), module, 1);
		Set<CurriculumModule> thisCurriculumModule = new HashSet<CurriculumModule>();
		thisCurriculumModule.add(curriculumModule);
		List<CurriculumModule> thisCurriculumModule2 = new ArrayList<CurriculumModule>();
		thisCurriculumModule2.add(curriculumModule);

		Mockito.when(curriculumModuleService.updateCurriculumModule(thisCurriculumModule))
				.thenReturn(thisCurriculumModule2);

		ResultActions result = mvc
				.perform(put("/curriculum-modules/curriculum/1").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(objMapper.writeValueAsString(thisCurriculumModule)));

		result.andExpect(status().isOk());
	}

	/**
	 * Tests the return status of the {@link com.revature.controllers.CurriculumModuleController#deleteCurrModule(int) deleteCurrModule(int)} method from the {@link com.revature.controllers.CurriculumModuleController CurriculumModuleController}.
	 * 
	 * This method assumes that the {@link com.revature.services.CurriculumModuleService#deleteCurriculumModule(CurriculumModule) deleteCurriculumModule(CurriculumModule)} method in the {@link com.revature.services.CurriculumModuleService CurriculumModuleService}
	 * returns a given {@link com.revature.entities.CurriculumModule CurriculumModule}.
	 * 
	 * The expected result is a status of OK (200).
	 * @throws Exception
	 */
	@Test
	public void testDeleteCurriculumModule_StatusOk() throws Exception {
		Curriculum curriculum = new Curriculum(1, "Java Full Stack");
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		CurriculumModule curriculumModule = new CurriculumModule(1, curriculum.getId(), module, 1);

		Mockito.doNothing().when(curriculumModuleService).deleteCurriculumModule(curriculumModule);

		ResultActions result = mvc.perform(delete("/curriculum-modules/" + curriculumModule.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE));

		result.andExpect(status().isOk());
	}
}
