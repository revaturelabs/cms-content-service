package com.revature.servicesTest;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.revature.cmsforce.CMSforceApplication;
import com.revature.entities.Curriculum;
import com.revature.entities.CurriculumModule;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.repositories.CurriculumModuleRepository;
import com.revature.repositories.CurriculumRepository;
import com.revature.services.CurriculumService;
import com.revature.services.CurriculumServiceImpl;

@SpringBootTest(classes = CMSforceApplication.class)
public class CurriculumServiceTest extends AbstractTestNGSpringContextTests{
	
	@InjectMocks
	private CurriculumService csMock = new CurriculumServiceImpl();
	@Mock
	private CurriculumRepository crMock;
	@Mock
	private CurriculumModuleRepository cmrMock;
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
	 * This method Tests {@link com.revature.services.CurriculumServiceImpl#getCurriculumById(int) getCurriculumById(int).}
	 * This Method assumes an int id was passed in as an argument, and returns a Curriculum Object. 
	 * This Method Mocks the CurriculumRepository.
	 */
	@Test
	public void testGetAllCurriculums() {
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
	 * This method Tests {@link com.revature.services.CurriculumServiceImpl#getCurriculumById(int) getCurriculumById(int).}
	 * This Method assumes an int id was passed in as an argument, and returns a Curriculum Object. 
	 * This Method Mocks the CurriculumRepository.
	 */
	@Test
	public void testGetCurriculumById() {
		Curriculum  curr1 = new Curriculum(1, "Test get By Id 1");
		Mockito.when(crMock.findById(curr1.getId())).thenReturn(curr1);
		
		Curriculum tmp = csMock.getCurriculumById(curr1.getId());
		assertTrue(curr1.equals(tmp));
	}
	
	/**
	 * This method Tests {@link com.revature.services.CurriculumServiceImpl#getCurriculumByName(String) getCurriculumByName(String).}
	 * This Method assumes a String name was passed in as an argument, and returns a Curriculum Object. 
	 * This Method Mocks the CurriculumRepository.
	 */
	@Test
	public void testGetCurriculumByname() {
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
	public void testCreateCurriculum() {
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
	public void testUpdateModule() {
		csMock.updateCurriculum(curriculum);
		verify(crMock).save(curriculum);
	}
	
	/**
	 * The main purpose of this method is delegation to the repository for deletion.
	 * Therefore verification is used.
	 */
	@Test
	public void testDeleteCurriculum() {	
		csMock.deleteCurriculum(curriculum);
		verify(crMock).delete(curriculum);
	}
	
	@Test
	public void testDeleteCurriculumNull() {	
		csMock.deleteCurriculum(null);
		verify(crMock, times(0)).delete(any(Curriculum.class));
	}
	
	/**
	 * This method Tests {@link com.revature.services.CurriculumServiceImpl#getLinksByCurricumId(int) getLinksByCurriculumId(int).}
	 * This Method assumes an int id was passed in as an argument, and returns a set of links. 
	 * This Method Mocks the CurriculumModuleRepository.
	 */
	@Test
	public void testgetLinksByCurriculumId() {
		CurriculumModule cm = new CurriculumModule();
		Set<Link> links = new HashSet<Link>();
		links.add(new Link());
		cm.setModule(new Module(1, null, 0, links, null, null, null));
		Mockito.when(cmrMock.findById(anyInt())).thenReturn(cm);
		
		Set<Link> actual = csMock.getLinksByCurricumId(1);
		
		assertEquals(links, actual);
	}
}
