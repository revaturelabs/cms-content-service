package com.revature.servicesTest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import java.util.HashSet;
import java.util.Set;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.revature.entities.CurriculumModule;
import com.revature.repositories.CurriculumModuleRepository;
import com.revature.services.CurriculumModuleService;
import com.revature.services.CurriculumModuleServiceImpl;

public class CurriculumModuleServiceImplTest {

	@InjectMocks
	private CurriculumModuleService curriculumModuleService;
	@Mock
	private CurriculumModuleRepository curriculumModuleRepository;
	private CurriculumModule curriculumModule;

	@BeforeClass
	public void setup() {
		curriculumModuleService = new CurriculumModuleServiceImpl();
	}

	/**
	 * Reinitialize mocks for logical independence
	 */
	@BeforeMethod
	public void reInit() {
		curriculumModule = new CurriculumModule();
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * The main purpose of this method is delegation to the repository for
	 * persistence. Therefore verification is used.
	 */
	@Test
	public void createCurriculumModuleTest() {
		curriculumModuleService.createCurriculumModule(curriculumModule);
		verify(curriculumModuleRepository).save(curriculumModule);
	}

	/**
	 * The main purpose of this method is delegation to the repository for deletion.
	 * Therefore verification is used.
	 */
	@Test
	public void deleteCurriculumModuleTest() {
		curriculumModuleService.deleteCurriculumModule(curriculumModule);
		verify(curriculumModuleRepository).delete(curriculumModule);
	}

	/**
	 * Tests getting all objects. The purpose is to test whether the
	 * expected objects are returned regardless of method logic. As of now their is
	 * no additional logic so the expected objects are the same as that returned from
	 * the repository
	 */
	@Test
	public void getAllCurriculumModulesTest() {
		Set<CurriculumModule> expected = new HashSet<>();
		expected.add(curriculumModule);
		when(curriculumModuleRepository.findAll()).thenReturn(expected);
		Set<CurriculumModule> actual = curriculumModuleService.getAllCurriculumModules();
		assertEquals(actual, expected);
	}

	/**
	 * Tests getting an object based on an Id. The purpose is to test whether the
	 * expected object is returned regardless of method logic. As of now their is
	 * no additional logic so the expected object is the same as that returned from
	 * the repository
	 */
	@Test
	public void getCurriculumModuleByIdTest() {
		CurriculumModule expected = curriculumModule;
		when(curriculumModuleRepository.findById(expected.getId())).thenReturn(expected);
		CurriculumModule actual = curriculumModuleService.getCurriculumModuleById(expected.getId());
		assertEquals(actual, expected);
	}

	/**
	 * The main purpose of this method is delegation to the repository for updating.
	 * Therefore verification is used.
	 */
	@Test
	public void updateCurriculumModuleTest() {
		Set<CurriculumModule> curriculumModules = new HashSet<>();
		curriculumModuleService.updateCurriculumModule(curriculumModules);
		verify(curriculumModuleRepository).saveAll(curriculumModules);
	}
}
