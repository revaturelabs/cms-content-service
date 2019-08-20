package com.revature.servicestests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.cmsforce.CMSforceApplication;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.services.ModuleService;
import com.revature.services.ModuleServiceImpl;

@SpringBootTest(classes = CMSforceApplication.class)
public class ModuleServiceTest extends AbstractTestNGSpringContextTests {
	
//	===MOCKITO INJECTIONS===
	@InjectMocks
	private ModuleService msMock;
	@Mock
	private ModuleRepository mrMock;
	@Mock
	private LinkRepository lrMock;
	
//	===FIELDS===
	private Link l1;
	private Link l2;
	private Link l3;
	private Module m1;
	private Module m2;
	private Module m3;
	private Module m4;
	private int idTest;
	private Set<Module> moduleList;
	private Set<Link> links;
	private ArrayList<Integer> lints;
	private ArrayList<Integer> lintsMod;
	
	
//	===TEST SETUP===
	//This will add some of the setup needed to use spring
	@BeforeClass
	public void setup() {
		//Create Mock object of the test service.
		msMock = new ModuleServiceImpl();
		
		//Enable Mockito Annotations.
		MockitoAnnotations.initMocks(this);
	}
	
	//This will set up the values before the tests
	@BeforeTest
	public void testSetup() {
		
		//Create a Link object to add to module object.
		l1 = new Link(250, 10, 130, "testblah");
		l2 = new Link(255, 10, 130, "testblah");
		l3 = new Link(224, 10, 130, "testblah");
		links = new HashSet<Link>();
		links.add(l1);
		
		lints = new ArrayList<Integer>();
		lints.add(250);
		lints.add(255);
		lints.add(224);
		
		//Create a module object for testing.
		m1 = new Module(130, "TestSubject", 10, links);
		m2 = new Module(145, "Java", 10, links);	
		m3 = new Module(150, "CSS", 10, links);	
		m4 = new Module(170, "HTML", 0, links);
		idTest = 130;
		
		lintsMod = new ArrayList<Integer>();
		lintsMod.add(130);
		lintsMod.add(145);
		lintsMod.add(150);
		//Create a list of modules for testing.
		moduleList = new HashSet<Module>();
		moduleList.add(m1);
		moduleList.add(m2);
		moduleList.add(m3);
		
		links.add(l2);
		links.add(l3);
	}
	
	//tear down the setup
	@AfterTest
	void teardown() {
		m1 = null;
		m2 = null;
		m3 = null;
		moduleList = null;
		l1 = null;
		l2 = null;
		l3 = null;
		
	}
	
//	===TESTS===
	//EachTest will test one function to make sure base functionality works
	
	/**
	 * Tests getAllModules()
	 * Module Repository - findAll()
	 * Asserts true if a module list is returned
	 */
	@Test
	void testGetAllModules() {
		Mockito.when(mrMock.findAll()).thenReturn(moduleList);
		
		Set<Module> tmp = msMock.getAllModules();
		assertTrue(tmp.equals(moduleList));
	}
	
	/**
	 * Tests GetModuleById()
	 * Module Repository - findById()
	 * Asserts True if temp variable equals m1 Module object
	 */
	@Test
	void testGetModuleById() {
		Mockito.when(mrMock.findById(idTest)).thenReturn(m1);
		
		Module tmp = msMock.getModuleById(idTest);
		assertTrue(tmp.equals(m1));
	}
	
	/**
	 * Tests createModule()
	 * Module Repository - save()
	 * Asserts true if temp variable equals m4 Module object
	 */
	@Test
	void testCreateModule() {
		Mockito.when(mrMock.save(m4)).thenReturn(m4);
		
		Module tmp = msMock.createModule(m4);
		assertTrue(tmp.equals(m4));
		
	}
	
	/**
	 * Tests getAverageByModuleIds
	 * Link Repository - findByModuleIdin()
	 * Assert True if temp variable is greater than | equal to 0.0
	 */
	@Test
	void testGetAverageByModuleIds() {
		Mockito.when(lrMock.findByModuleIdIn(lints)).thenReturn(links);
		
		double tmp = msMock.getAverageByModuleIds(lints);
		
		assertTrue(tmp >= 0.0);
	}
	
	/**
	 * Tests getAverageByAllModules()
	 * Link Repository - findByModuleIdIn()
	 * Module Repository - findAll()
	 * Asserts true if temp variable is greater than | equal to 0.0
	 */
	@Test
	void testGetAverageByAllModules() {
		Mockito.when(lrMock.findByModuleIdIn(lintsMod)).thenReturn(links);
		Mockito.when(mrMock.findAll()).thenReturn(moduleList);
		
		double tmp = msMock.getAverageByAllModules();
		
		assertTrue(tmp >= 0.0);
		
	}
	
	/**
	 * Tests delete()
	 */
	@Test
	void testDelete() {
		Mockito.doNothing().when(mrMock).delete(m1);
		
		msMock.deleteModule(m1);		
	}
	

}
