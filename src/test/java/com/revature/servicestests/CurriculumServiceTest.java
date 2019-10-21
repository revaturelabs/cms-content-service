package com.revature.servicestests;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import com.revature.cmsforce.CMSforceApplication;
import com.revature.entities.Curriculum;
import com.revature.repositories.CurriculumRepository;
import com.revature.services.CurriculumService;
import com.revature.services.CurriculumServiceImpl;


@SpringBootTest(classes = CMSforceApplication.class)
public class CurriculumServiceTest extends AbstractTestNGSpringContextTests{
	
	 //	===MOCKITO INJECTIONS===
	@InjectMocks
	private CurriculumService csMock;
	@Mock
	private CurriculumRepository crMock;
	
	
	
	
	//	===TEST SETUP===
	/**
	 * This will add some of the setup needed to use spring
	 * 
	 * */ 
	@BeforeClass
	public void setup() {
		//Create Mock object of the test service.
		
		csMock = new CurriculumServiceImpl();
	}
	
	
	
	@BeforeMethod
	public void reinitMocks() {
		//Enable Mockito Annotations.
		MockitoAnnotations.initMocks(this);
	}
	
	
	/**
	 * Test getAllCurriculums
	 * Curriculum Repository - findAll()
	 * Assert True is Curriculum List is returned
	 */
	
	@Test
	void test_get_all_Curriculums() {
		Curriculum curr1 = new Curriculum(1, "Test Curriculum 1");
		Curriculum curr2 = new Curriculum(2, "Test Curriculum 2");
		
		Set<Curriculum> allCurriculums = new HashSet<Curriculum>();
		
		allCurriculums.add(curr1);
		allCurriculums.add(curr2);
		
		Mockito.when(crMock.findAll()).thenReturn(allCurriculums);
		
		Set<Curriculum> tmp = csMock.getAllCurriculums();
		
		Mockito.verify(crMock).findAll();
		assertTrue(allCurriculums.equals(tmp));

	}
	
	/**
	 * Tests getCurriculumById()
	 * Curriculum Repository - findById()
	 * Asserts True if tmp variable equals the curr1 Curriculum 
	 */
	
	@Test
	void test_get_curriculum_By_Id() {
		Curriculum  curr1 = new Curriculum(1, "Test get By Id 1");
		Mockito.when(crMock.findById(curr1.getId())).thenReturn(curr1);
		
		Curriculum tmp = csMock.getCurriculumById(curr1.getId());
		Mockito.verify(crMock).findById(curr1.getId());
		assertTrue(curr1.equals(tmp));
	}
	
	/**
	 * Tests getCurriculumById()
	 * Curriculum Repository - findByName()
	 * Asserts True if tmp variable equals the curr1 Curriculum 
	 */
	
	
	@Test
	void test_get_curriculum_By_name() {
		
		Curriculum curr1 = new Curriculum(1, "Java");
		Mockito.when(crMock.findByName(curr1.getName())).thenReturn(curr1);
		
		Curriculum tmp = csMock.getCurriculumByName(curr1.getName());
		Mockito.verify(crMock).findByName(curr1.getName());
		assertTrue(curr1.equals(tmp));
	}
	
	/**
	 * Tests createCurriculum()
	 * Curriculum Repository - save()
	 * Asserts True if tmp variable equals the curr1 Curriculum 
	 */
	
	@Test
	void test_create_curriculum() {
		
		Curriculum curr1 = new Curriculum(1, "Test create new curriculum");
		Mockito.when(crMock.save(curr1)).thenReturn(curr1);
		
		Curriculum tmp = csMock.createCurriculum(curr1);
		Mockito.verify(crMock).save(curr1);
		assertTrue(curr1.equals(tmp));
	}
	
	/**
	 * Tests update Curriculum updateCurriculum()
	 * Curriculum Repository - save() 
	 */
	
	
	@Test
	void test_update_module() {
		
		Curriculum curr1 = new Curriculum(1, "Java");
		
		Mockito.when(crMock.save(curr1)).thenReturn(curr1);
		Curriculum actual = csMock.updateCurriculum(curr1);
		Mockito.verify(crMock).save(curr1);
		Assert.assertEquals(actual, curr1);
	}
	
	/**
	 * Test delete curriculum - deleteCurriculum()
	 * Curriculum Repository - delete()
	 * 
	 */
	
	@Test
	void test_delete_curriculum() {
		
		Curriculum curr1 = new Curriculum(1, "Java");
		Mockito.doNothing().when(crMock).delete(curr1);
		
		csMock.deleteCurriculum(curr1);
		Mockito.verify(crMock).delete(curr1);
	}
	

}
