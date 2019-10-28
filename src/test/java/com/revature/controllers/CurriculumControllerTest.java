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
import com.revature.entities.Content;
import com.revature.entities.Curriculum;
import com.revature.entities.CurriculumModule;
import com.revature.entities.Link;
import com.revature.entities.Module;
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
	 * This {@link com.revature.controllers.CurriculumControllerTest#setup() setup} method initializes the Mockito and
	 * mocked dependencies once before any tests are run.
	 */
	@BeforeClass
	public void setup() {
		curriculumController = new CurriculumController();
		mvc = MockMvcBuilders.standaloneSetup(curriculumController).build();

		MockitoAnnotations.initMocks(this);
	}

	/**
	 * This {@link com.revature.controllers.CurriculumControllerTest#preTestSetup() preTestSetup} ensures clean curriculum for each test.
	 */
	@BeforeTest
	public void preTestSetup() {
		curriculum = new Curriculum(ID1, NAME1);
		curriculum2 = new Curriculum(ID2, NAME2);
	}

	/**
	 * This {@link com.revature.controllers.CurriculumControllerTest#givenValidDataCreateCurriculum() givenValidDataCreateCurriculum} method tests the
	 * {@link com.revature.controllers.CurriculumController#createCurriculum(Curriculum) createCurriculum} method.
	 * This method assumes a mocked curriculum is returned after adding Curriculum to back-end.
	 * The result expected is that the mocked Curriculum matches the returned Curriculum.
	 *
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void givenValidDataCreateCurriculum() throws Exception {
		Mockito.when(curriculumService.createCurriculum(curriculum)).thenReturn(curriculum);

		ResultActions result = mvc.perform(post("/curricula").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(curriculum)));

		Curriculum actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				Curriculum.class);

		assertEquals(actual, curriculum, "Curriculum was not created");
	}

	/**
	 * The {@link #getAllCurriculum() getAllCurriculum} method tests the 
	 * {@link com.revature.controllers.CurriculumController#getAllCurriculums() getAllCurriculums} method.
	 * This method assumes that a set of curriculum will be returned.
	 * The result expected is a JSON string of the curriculums.
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void getAllCurriculum() throws Exception {
		Set<Curriculum> curriculums = new HashSet<Curriculum>();
		curriculums.add(curriculum);
		curriculums.add(curriculum2);
		Mockito.when(curriculumService.getAllCurriculums()).thenReturn(curriculums);

		ResultActions result = mvc.perform(get("/curricula").contentType(MediaType.APPLICATION_JSON));
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
	 * This {@link #getCurriculumById() getCurriculumById} method tests the
	 * {@link com.revature.controllers.CurriculumController#getCurriculumById(int) getCurriculumById} method.
	 * This method assumes a mocked curriculum will be returned.
	 * The result expected is an a mocked curriculum match.
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void getCurriculumById() throws Exception {
		Mockito.when(curriculumService.getCurriculumById(ID1)).thenReturn(curriculum);
		Set<CurriculumModule> currModules = new HashSet<CurriculumModule>();
		CurriculumModule curriculumModule = new CurriculumModule(1,curriculum.getId(),null,1);
		currModules.add(curriculumModule);
		Mockito.when(curriculumModuleService.getAllCurriculumModules()).thenReturn(currModules);
		ResultActions result = mvc.perform(get("/curricula/" + ID1));
		Curriculum actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				Curriculum.class);

		assertEquals(curriculum, actual, "Curriculum was not pulled");
	}
	
	/**
	 * The {@link #getCurriculumByIdNotFound() getCurriculumByIdNotFound} method tests the
	 * {@link com.revature.controllers.CurriculumController#getCurriculumById(int)} method.
	 * The method assumes that a curriculum does not exist.
	 * The result expected is a status not found.
	 * @throws Exception
	 */
	@Test
	public void getCurriculumByIdNotFound() throws Exception {
		Mockito.when(curriculumService.getCurriculumById(ID1)).thenReturn(null);
		ResultActions result = mvc.perform(get("/curricula/" + ID1));
		result.andExpect(status().isNotFound());
	}

	/**
	 * The {@link #deleteCurriculum() deleteCurriculum} method tests the 
	 * {@link com.revature.controllers.CurriculumController#deleteCurriculum(int)  controller deleteCurriculum} method.
	 * The method assumes no delete will actually occur.
	 * The result expected is a status OK.
	 * Test removing a module from the back-end
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void deleteCurriculum() throws Exception {
		Mockito.doNothing().when(curriculumService).deleteCurriculum(curriculum);
		Mockito.when(curriculumService.getCurriculumById(ID1)).thenReturn(curriculum);

		ResultActions result = mvc.perform(delete("/curricula/" + ID1));

		result.andExpect(status().isOk());
	}
	/**
	 * The {@link #updateCurriculum() updateCurriculum} method tests the
	 * {@link com.revature.controllers.CurriculumController#updateCurriculum(int, Curriculum) controller updateCurriculum} method.
	 * The method assumes the id of the curriculum is 1.
	 * The result expected is a returned curriculum matching mocked curriculum.
	 * @throws Exception
	 */
	@Test
	public void updateCurriculum() throws Exception {
		curriculum.setName("UpdatedTest");
		Mockito.when(curriculumService.updateCurriculum(curriculum)).thenReturn(curriculum);

		ResultActions result = mvc.perform(put("/curricula/1").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(curriculum)));

		String actual = result.andReturn().getResponse().getContentAsString();
		String expected = objMapper.writeValueAsString(curriculum);
		assertEquals(expected, actual, "Did not update curriculum correctly");
	}
	/**
	 * The {@link #getLinksByCurriculumId() getLinksByCurriculumId} method tests the
	 * {@link com.revature.controllers.CurriculumController#getLinksByCurricumId(int) controller getLinksByCurricumId} method.
	 * The method assumes an id of 1.
	 * The result expected is a status of OK that a curriculum was found.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getLinksByCurriculumId() throws Exception {
		CurriculumModule curr =new CurriculumModule ();
		curr.setId(ID1);
		Link link = new Link (1,new Content(),new Module(),"affiliation",1);
		Set<Link >links=new HashSet<>();
		links.add(link);
		Mockito.when(curriculumService.getLinksByCurricumId(curr.getId())).thenReturn(links);
		
		ResultActions result = mvc.perform(get("/curricula/"+ID1+"/links"));
		result.andExpect(status().isOk());
	}
}
