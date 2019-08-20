package com.revature.servicestests;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
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
public class ModuleServiceTest2 extends AbstractTestNGSpringContextTests {
	
//	===MOCKITO INJECTIONS===
	@InjectMocks
	private ModuleService msMock;
	@Mock
	private ModuleRepository mrMock;
	@Mock
	private LinkRepository lrMock;
	
//	===FIELDS===
	private Link link;
	private Module module;
	private int idTest;
	private Set<Module> moduleList = new HashSet<Module>();
	
	
//	===TEST SETUP===
	@BeforeClass
	public void setup() {
		//Create Mock object of the test service.
		msMock = new ModuleServiceImpl();
		
		//Enable Mockito Annotations.
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeTest
	public void testSetup() {
		
		//Create a Link object to add to module object.
		this.link = new Link(250, 10, 130, "testblah");
		Set<Link> links = new HashSet<Link>();
		links.add(this.link);
		
		//Create a module object for testing.
		this.module = new Module(130, "TestSubject", 10, links);		
		this.idTest = 130;
		
		//Create a list of modules for testing.
		moduleList.add(module);
	}
	
//	===TESTS===
	@Test
	public void createModuleTest() {
		//Declare Mock return.
		 Mockito.when(mrMock.save(this.module)).thenReturn(module);
		 
		 //Save test module and compare it 
		 Module moduleExpected = msMock.createModule(module);
		 
		 assertTrue(moduleExpected.equals(module));
	}

	@Test
	public void getAllModulesTest() {
	  
		//Declare Mock return.
		Mockito.when(mrMock.findAll()).thenReturn(this.moduleList);
		
		Set<Module> modulesListTest = msMock.getAllModules();
	  
		//Get the first Module in the set.
		Iterator iter = modulesListTest.iterator();
		Module moduleExpected = (Module) iter.next();
		  
		//Compare Modules
		assertTrue(moduleExpected.equals(module));
	}
		 
	@Test
	public void getModuleByIdTest() {
	  Mockito.when(mrMock.findById(this.idTest)).thenReturn(module);
	  
	  Module moduleExpected = msMock.getModuleById(module.getId());
	  
	  assertEquals(moduleExpected.getId(), this.idTest);
	}

}
