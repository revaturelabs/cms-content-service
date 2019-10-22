package com.revature.services;

import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import java.util.Set;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.entities.Curriculum;
import com.revature.repositories.CurriculumRepository;

@SpringBootTest(classes = CMSforceApplication.class)
public class CurriculumServiceTest extends AbstractTestNGSpringContextTests{
	
	@InjectMocks
	private CurriculumService csMock;
	@Mock
	private CurriculumRepository crMock;
	private Curriculum curriculum;

		
	/**
	 * This will add some of the setup needed to use spring
	 * 
	 */ 
	@BeforeClass
	public void setup() {
		csMock = new CurriculumServiceImpl();
	}
	
	/**
	 * Reinitialize mocks for logical independence
	 */
	@BeforeMethod
	public void reInit() {
		curriculum = new Curriculum(1, "Java");
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Test getAllCurriculums
	 * Curriculum Repository - findAll()
	 * Assert True is Curriculum List is returned
	 */
	@Test
	void testGetAllCurriculums() {
		Curriculum curr1 = new Curriculum(1, "Test Curriculum 1");
		Curriculum curr2 = new Curriculum(2, "Test Curriculum 2");
		
		Set<Curriculum> allCurriculums = new HashSet<Curriculum>();
		
		allCurriculums.add(curr1);
		allCurriculums.add(curr2);
		
		Mockito.when(crMock.findAll()).thenReturn(allCurriculums);
		
		Set<Curriculum> tmp = csMock.getAllCurriculums();
		
		assertTrue(allCurriculums.equals(tmp));

	}
	
	/**
	 * Tests getCurriculumById()
	 * Curriculum Repository - findById()
	 * Asserts True if tmp variable equals the curr1 Curriculum 
	 */
	@Test
	void testGetCurriculumById() {
		Curriculum  curr1 = new Curriculum(1, "Test get By Id 1");
		Mockito.when(crMock.findById(curr1.getId())).thenReturn(curr1);
		
		Curriculum tmp = csMock.getCurriculumById(curr1.getId());
		assertTrue(curr1.equals(tmp));
	}
	
	/**
	 * Tests getCurriculumById()
	 * Curriculum Repository - findByName()
	 * Asserts True if tmp variable equals the curr1 Curriculum 
	 */
	@Test
	void testGetCurriculumByname() {
		Curriculum curr1 = new Curriculum(1, "Java");
		Mockito.when(crMock.findByName(curr1.getName())).thenReturn(curr1);
		
		Curriculum tmp = csMock.getCurriculumByName(curr1.getName());
		assertTrue(curr1.equals(tmp));
	}
	
	/**
	 * Tests createCurriculum()
	 * Curriculum Repository - save()
	 * The main purpose of this method is delegation to the repository for persistence.
	 * Therefore verification is used.
	 */
	@Test
	void testCreateCurriculum() {
		csMock.createCurriculum(curriculum);
		verify(crMock).save(curriculum);
	}
	
	/**
	 * Tests update Curriculum updateCurriculum()
	 * Curriculum Repository - save()
	 * The main purpose of this method is delegation to the repository for updating.
	 * Therefore verification is used.
	 */
	@Test
	void testUpdateModule() {
		csMock.updateCurriculum(curriculum);
		verify(crMock).save(curriculum);
	}
	
	/**
	 * The main purpose of this method is delegation to the repository for deletion.
	 * Therefore verification is used.
	 */
	@Test
	void testDeleteCurriculum() {	
		csMock.deleteCurriculum(curriculum);
		verify(crMock).delete(curriculum);
	}
	
	@Test
	void testDeleteCurriculumNull() {	
		csMock.deleteCurriculum(null);
		verify(crMock, times(0)).delete(any(Curriculum.class));
	}
	

}
