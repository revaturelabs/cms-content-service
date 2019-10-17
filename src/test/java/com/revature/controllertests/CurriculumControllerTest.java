package com.revature.controllertests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.CurriculumController;
import com.revature.entities.Curriculum;
import com.revature.entities.CurriculumModule;
import com.revature.services.CurriculumModuleService;
import com.revature.services.CurriculumService;

@SpringBootTest(classes = CMSforceApplication.class)
public class CurriculumControllerTest {

	private static final int ID1 = 1;
	private static final int ID2 = 2;

	private static final String NAME1 = "testCurriculum";
	private static final String NAME2 = "testCurriculum2";

	private MockMvc mvc;
	private ObjectMapper objMapper = new ObjectMapper();

	@InjectMocks
	private CurriculumController curriculumController;

	@Mock
	private CurriculumService curriculumService;

	@Mock
	private CurriculumModuleService curriculumModuleService;

	private Curriculum curriculum;

	private Curriculum curriculum2;

	/**
	 * Initialize Mockito and mocking dependencies
	 */
	@BeforeClass
	public void setup() {
		curriculumController = new CurriculumController();
		mvc = MockMvcBuilders.standaloneSetup(curriculumController).build();

		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Ensures clean curriculum for each test
	 */
	@BeforeTest
	public void preTestSetup() {
		curriculum = new Curriculum(ID1, NAME1);
		curriculum2 = new Curriculum(ID2, NAME2);
	}

	/**
	 * Test adding a new Curriculum into the back-end
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void givenValidDataCreateCurriculum() throws Exception {
		Mockito.when(curriculumService.createCurriculum(curriculum)).thenReturn(curriculum);

		ResultActions result = mvc.perform(post("/curriculums").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(curriculum)));

		Curriculum actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				Curriculum.class);

		assertEquals(actual, curriculum, "Curriculum was not created");
	}

	/**
	 * Test retrieving all the modules from the back-end
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void getAllCurriculum() throws Exception {
		Set<Curriculum> curriculums = new HashSet<Curriculum>();
		curriculums.add(curriculum);
		curriculums.add(curriculum2);
		Mockito.when(curriculumService.getAllCurriculums()).thenReturn(curriculums);

		ResultActions result = mvc.perform(get("/curriculums").contentType(MediaType.APPLICATION_JSON));
		String actual = result.andReturn().getResponse().getContentAsString();

		assertEquals(actual, convertToJSONCurriculumSetString(curriculums), "Failed to get back Curriculums");

	}

	/**
	 * Helper for creating string of Set<Curriculum>
	 * 
	 * @param allCurriculums - Set of all Curriculums
	 * @return - String modified set of curriculums
	 * @throws Exception - If the set of curriculums is null will throw an exception
	 *                   (NullPointerException)
	 */
	private String convertToJSONCurriculumSetString(Set<Curriculum> allCurriculums) throws Exception {
		StringBuilder result = new StringBuilder("[");
		for (Curriculum curr : allCurriculums) {
			result.append(objMapper.writeValueAsString(curr));
			result.append(",");
		}
		if (allCurriculums.size() > 0) {
			result.deleteCharAt(result.length() - 1);
		}
		result.append("]");

		return result.toString();
	}

	/**
	 * Test pulling a specific curriculum from the back-end
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void getCurriculumById() throws Exception {
		Mockito.when(curriculumService.getCurriculumById(ID1)).thenReturn(curriculum);
		Set<CurriculumModule> currModules = new HashSet<CurriculumModule>();
		Mockito.when(curriculumModuleService.getAllCurriculumModules()).thenReturn(currModules);
		ResultActions result = mvc.perform(get("/curriculums/" + ID1));
		Curriculum actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				Curriculum.class);


		assertEquals(curriculum, actual, "Curriculum was not pulled");
	}

	/**
	 * Test removing a module from the back-end
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void deleteCurriculum() throws Exception {
		Mockito.doNothing().when(curriculumService).deleteCurriculum(curriculum);
		Mockito.when(curriculumService.getCurriculumById(ID1)).thenReturn(curriculum);

		ResultActions result = mvc.perform(delete("/curriculums/" + ID1));

		result.andExpect(status().isOk());
	}

	@Test
	public void updateCurriculum() throws Exception {
		curriculum.setName("UpdatedTest");
		Mockito.when(curriculumService.updateCurriculum(curriculum)).thenReturn(curriculum);

		ResultActions result = mvc.perform(put("/curriculums/1").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(curriculum)));

		String actual = result.andReturn().getResponse().getContentAsString();
		String expected = objMapper.writeValueAsString(curriculum);
		assertEquals(expected, actual, "Did not update curriculum correctly");
	}
}
