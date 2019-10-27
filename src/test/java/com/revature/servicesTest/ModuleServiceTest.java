package com.revature.servicesTest;

import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import com.revature.repositories.LinkRepository;
import com.revature.repositories.ReqLinkRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;

import com.revature.cmsforce.CMSforceApplication;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.entitiesTest.*;
import com.revature.repositories.ModuleRepository;
import com.revature.services.ModuleService;
import com.revature.services.ModuleServiceImpl;

@SpringBootTest(classes = CMSforceApplication.class)
public class ModuleServiceTest extends AbstractTestNGSpringContextTests {
	//Any time that two nulls appear in a test of a constructor, that is for a feature that was created after the tests were created to allow them to pass.
	
    //	===MOCKITO INJECTIONS===
	@InjectMocks
	private ModuleService msMock;
	@Mock
	private ModuleRepository mrMock;
	@Mock
	private LinkRepository lrMock;
	@Mock
	private ReqLinkRepository rlrMock;


    //	===TEST SETUP===
	//This will add some of the setup needed to use spring
	@BeforeClass
	public void setup()
	{
		//Create Mock object of the test service.
		msMock = new ModuleServiceImpl();
	}

	@BeforeMethod
	public void reinitMocks() {
		//Enable Mockito Annotations.
		MockitoAnnotations.initMocks(this);
	}

    //	===TESTS===
	//EachTest will test one function to make sure base functionality works
	
	/**
	 * This method tests { @link com.revature.services.ModuleServiceImpl#getAllModules() getAllModules() } 
	 * This takes no argument and is expected to return a set containing all modules.
	 * This mocks ModuleRepository and ModuleServiceImpl
	 */
	@Test
	void testGetAllModules() {
		Module mod1 = new Module(1, "Java", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
			new HashSet<Module>(), new HashSet<Module>());
		Module mod2 = new Module(5, "Sandwiches", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Set<Module> allModules = new HashSet<Module>();
		allModules.add(mod1);
		allModules.add(mod2);
		
		Mockito.when(mrMock.findAll()).thenReturn(allModules);

		Set<Module> tmp = msMock.getAllModules();

		Mockito.verify(mrMock).findAll();
		assertTrue(allModules.equals(tmp));
	}
	
	/**
	 * This method tests { @link com.revature.services.ModuleServiceImpl#getModuleById() getModuleById() } 
	 * This takes an int and is expected to return a set containing all modules.
	 * This mocks ModuleRepository and ModuleServiceImpl.
	 */
	@Test
	void testGetModuleById() {

		
		Module mod1 = new Module(1, "Java", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Mockito.when(mrMock.findById(mod1.getId().intValue())).thenReturn(mod1);
 
		Module tmp = msMock.getModuleById(mod1.getId());
		Mockito.verify(mrMock).findById(mod1.getId().intValue());
		assertTrue(mod1.equals(tmp));
	}
	
	/**
	 * This method tests { @link com.revature.services.ModuleServiceImpl#createModule(module) createModule(module) } 
	 * This takes a Module and is expected to return a Module.
	 * This mocks ModuleRepository and ModuleServiceImpl.
	 */
	@Test
	void testCreateModule() {
		
		Module mod1 = new Module(0, "Java", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Mockito.when(mrMock.save(mod1)).thenReturn(mod1);

		Module tmp = msMock.createModule(mod1);
		Mockito.verify(mrMock).save(mod1);
		assertTrue(mod1.equals(tmp));
	}
	
	/**
	 * This method tests {@link com.revature.services.ModuleServiceImpl#getAverageByModules(Set) getAverageByModules(Set)} 
	 * This takes a Module and is expected to return a Module.
	 * This mocks ModuleRepository and ModuleServiceImpl.
	 */
	@Test
	void testGetAverageByModules() {

	 	Link link1 = new Link(1, new Content(), new Module(), "affiliation1",0);
		Link link2 = new Link(2, new Content(), new Module(), "affiliation2",0);
		Link link3 = new Link(3, new Content(), new Module(), "affiliation3",0);

		Set<Link> links1 = new HashSet<Link>();
		links1.add(link1);
		links1.add(link2);

		Set<Link> links2 = new HashSet<Link>();
		links2.add(link3);

		Module mod1 = new Module(1, "Java", 1L, links1, new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Module mod2 = new Module(2, "Testing", 1L, links2, new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		Set<Module> modules = new HashSet<Module>();
		modules.add(mod1);
		modules.add(mod2);

		double tmp = msMock.getAverageByModules(modules);

		assertTrue(Double.compare(tmp, 1.5) == 0);
	}
	
	/**
	 * This method tests {@link com.revature.services.ModuleServiceImpl#getAverageByAllModules() getAverageByAllModules()} 
	 * This takes no argument and is expected to return a double/average value of all the resources' resources.
	 * This mocks ModuleRepository and ModuleServiceImpl.
	 */
	@Test
	void testGetAverageByAllModules() {

	 	Link link1 = new Link(1, new Content(), new Module(), "affiliation1",0);
		Link link2 = new Link(2, new Content(), new Module(), "affiliation2",0);
		Link link3 = new Link(3, new Content(), new Module(), "affiliation3",0);

		Set<Link> links1 = new HashSet<Link>();
		links1.add(link1);
		links1.add(link2);

		Set<Link> links2 = new HashSet<Link>();
		links2.add(link3);

		Module mod1 = new Module(1, "Java", 1L, links1, new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Module mod2 = new Module(2, "Testing", 1L, links2, new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		Set<Module> modules = new HashSet<Module>();
		modules.add(mod1);
		modules.add(mod2);

		Mockito.when(msMock.getAllModules()).thenReturn(modules); 
		double tmp = msMock.getAverageByAllModules();

		assertTrue(Double.compare(tmp, 1.5) == 0);
	}

	/**
	 * This method tests { @link com.revature.services.ModuleServiceImpl#getChildrenByParentId(int) getChildrenByParentId(int) }
	 * This method assumes an int that refers to a Module ID is passed as an argument and returns a set of Modules.
	 * This mocks ModuleRepository and ModuleServiceImpl classes.
	 */
	@Test	
	void testGetChildrenByParentId() {
		Module mod1 = new Module(1, "Java", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		Set<Module> children = new HashSet<Module>();
		children.add(mod1);		
		Module mod2 = new Module(2, "Testing", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), children);

		Mockito.when(mrMock.findById(mod2.getId().intValue())).thenReturn(mod2);

		Set<Module> results = msMock.getChildrenByParentId(mod2.getId());

		Mockito.verify(mrMock).findById(mod2.getId().intValue());
		Assert.assertEquals(results, children);
	}

	/**
	 * This method tests {@link com.revature.services.ModuleServiceImpl#getAllRootModules() getAllRootModules()}
	 * This method takes no argument and is expected to return a set of root modules.
	 * This mocks ModuleRepository and ModuleServiceImpl.
	 */
	@Test
	void testGetAllRootModules() {
		Module mod1 = new Module(1, "Java", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				null, new HashSet<Module>());

		Set<Module> childrenOfMod2 = new HashSet<Module>();
		childrenOfMod2.add(mod1);
		Module mod2 = new Module(2, "Testing", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), childrenOfMod2);

		Set<Module> parentsOfMod1 = new HashSet<Module>();
		parentsOfMod1.add(mod2);
		mod1.setParents(parentsOfMod1);

		Set<Module> allModules = new HashSet<Module>();
		allModules.add(mod1);
		allModules.add(mod2);

		Mockito.when(mrMock.findAll()).thenReturn(allModules);

		Set<Module> results = msMock.getAllRootModules();

		Mockito.verify(mrMock).findAll();
		Assert.assertEquals(results, parentsOfMod1);
		
	}

	/**
	 * This method tests {@link com.revature.services.ModuleServiceImpl#updateModule(Module) updateModule(Module)}
	 * This method assumes a Module and is expected to update and return that module.
	 * This mocks ModuleRepository and ModuleServiceImpl.
	 */
	@Test
	void testUpdateModule() {
		Module mod1 = new Module(1, "Java", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		Mockito.when(mrMock.save(mod1)).thenReturn(mod1);
		Module actual = msMock.updateModule(mod1);
		Mockito.verify(mrMock).save(mod1);
		Assert.assertEquals(actual, mod1);
	}

	/**
	 * This method tests {@link com.revature.services.ModuleServiceImpl#deleteModule(Module) deleteModule(Module)}
	 * This method assumes a Module and is expected to delete that module.
	 * This mocks ModuleRepository and ModuleServiceImpl.
	 */
	@Test
	void testDeleteModule() {
		Module mod1 = new Module(1, "Java", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		Mockito.doNothing().when(mrMock).delete(mod1);
		msMock.deleteModule(mod1);

		Mockito.verify(mrMock).delete(mod1);
	}

	/**
	 * This method tests {@link com.revature.services.ModuleServiceImpl#deleteModule(Module) deleteModule(Module)}
	 * This method assumes a null value and is expected not perform the delete function.
	 * This mocks ModuleRepository and ModuleServiceImpl.
	 */
	@Test
	void testDeleteModuleWithNullModule() {
		Mockito.doNothing().when(mrMock).delete(null);
		msMock.deleteModule(null);

		Mockito.verify(mrMock, Mockito.times(0)).delete(null);
	}

	/**
	 * This method tests {@link com.revature.services.ModuleServiceImpl#deleteModuleWithAllContent(Module) deleteModuleWithAllContent(Module)}
	 * This method assumes a Module and is expected to delete the contents within that module and the module.
	 * This mocks LinkRepository, ModuleRepository and ModuleServiceImpl.
	 */
	@Test
	void testDeleteModuleWithAllContent() {

		Link link1 = new Link(1, new Content(), new Module(), "affiliation1",0);
		Link link2 = new Link(2, new Content(), new Module(), "affiliation2",0);
		Link link3 = new Link(3, new Content(), new Module(), "affiliation3",0);

		Set<Link> links1 = new HashSet<Link>();
		links1.add(link1);
		links1.add(link2);
		links1.add(link3);

		Module mod1 = new Module(1, "Java", 1L, links1, new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		Mockito.doNothing().when(lrMock).delete(Mockito.any());
		Mockito.doNothing().when(mrMock).delete(mod1);

		msMock.deleteModuleWithAllContent(mod1);

		Mockito.verify(lrMock, Mockito.times(3)).delete(Mockito.any());
		Mockito.verify(mrMock).delete(mod1);
	}

	/**
	 * This method tests {@link com.revature.services.ModuleServiceImpl#deleteModuleWithSpecificContent(Module) deleteModuleWithSpecificContent(Module)}
	 * This method takes a Module and is expected to delete contents that is only associated with that module.
	 * This mocks LinkRepository, ModuleRepository and ModuleServiceImpl.
	 */
	@Test
	void testDeleteModuleWithSpecificContent() {
		// Given
		
		Link link1 = new Link(1, new Content(), new Module(), "affiliation1",0);
		Link link2 = new Link(2, new Content(), new Module(), "affiliation2",0);
		Link linkToDelete = new Link(3, new Content(), new Module(), "affiliation3",0);


		Set<Link> multiLinks = new HashSet<Link>();
		multiLinks.add(link1);
		multiLinks.add(link2);

		Set<Link> singleLink = new HashSet<Link>();
		singleLink.add(linkToDelete);

		Set<Link> allLinks = new HashSet<Link>();
		allLinks.add(link1);
		allLinks.add(link2);
		allLinks.add(linkToDelete);

		Content cont1 = new Content(1, "Only one module", "code", "description", "http://ferfre.gov",
				1L, 1L, singleLink);
		linkToDelete.setContent(cont1);
		Content cont2 = new Content(2, "Has other modules", "code", "description", "http://ferfre.gov",
				1L, 1L, multiLinks);
		link1.setContent(cont2);
		link2.setContent(cont2);

		Module mod1 = new Module(1, "Java", 1L, allLinks, new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		// When
		Mockito.doNothing().when(lrMock).delete(Mockito.any());
		Mockito.doNothing().when(mrMock).delete(mod1);

		msMock.deleteModuleWithSpecificContent(mod1);

		// Should only delete linkToDelete, not either of the other links
		Mockito.verify(lrMock, Mockito.times(1)).delete(linkToDelete);
		Mockito.verify(lrMock, Mockito.times(1)).delete(Mockito.any());
		Mockito.verify(mrMock).delete(mod1);
	}

	/**
	 * This method tests {@link com.revature.services.ModuleServiceImpl#getLinksByModuleId(int) getLinksByModuleId(int)}
	 * This method takes an int that refers to the module ID and is expected to return a set of Links.
	 * This mocks LinkRepository and ModuleServiceImpl.
	 */
	@Test
	void testGetLinksByModuleId() {
		final int id = 1;

		Link link1 = new Link(1, new Content(), new Module(), "affiliation1",0);
		Link link2 = new Link(2, new Content(), new Module(), "affiliation2",0);

		Set<Link> links = new HashSet<Link>();
		links.add(link1);
		links.add(link2);

		Mockito.when(lrMock.findByModuleId(id)).thenReturn(links);

		Set<Link> actual = msMock.getLinksByModuleId(id);

		Mockito.verify(lrMock).findByModuleId(id);
		Assert.assertEquals(actual, links);
	}

	/**
	 * This method tests {@link com.revature.services.ModuleServiceImpl#getRequestLinksByModuleId(int) getRequestLinksByModuleId(int)}
	 * This method takes an int that refers to the module ID and is expected to return a set of ReqLinks.
	 * This mocks ReqLinkRepository, ModuleRepository and ModuleServiceImpl.
	 */
	@Test
	void testGetRequestLinksByModuleId() {
		ReqLink reqLink1 = new ReqLink(1, new Request(), new Module(), "affiliation1");
		ReqLink reqLink2 = new ReqLink(2, new Request(), new Module(), "affiliation2");

		Set<ReqLink> reqLinks = new HashSet<ReqLink>();
		reqLinks.add(reqLink1);
		reqLinks.add(reqLink2);

		Module mod1 = new Module(1, "Java", 1L, new HashSet<Link>(), reqLinks,
				new HashSet<Module>(), new HashSet<Module>());

		Mockito.when(mrMock.findById(mod1.getId().intValue())).thenReturn(mod1);
		Mockito.when(rlrMock.findByModule(mod1)).thenReturn(reqLinks);

		Set<ReqLink> actual = msMock.getRequestLinksByModuleId(mod1.getId());

		Mockito.verify(rlrMock).findByModule(mod1);
		Assert.assertEquals(actual, reqLinks);
	}
	
	/**
	 * This method tests {@link com.revature.services.ModuleServiceImpl#updateLinksByModuleId(int, Set) updateLinksByModuleId(int, Set)}
	 * This method takes an int that refers to the module ID and a set of links
	 * and is expected to update and return that set.
	 * This mocks ReqLinkRepository, ModuleRepository and ModuleServiceImpl.
	 * 
	 * Note from tester: this does not need/use the int argument
	 */
	@Test
	void testUpdateLinksByModuleId () {
		Link link = new Link(1, new Content(), new Module(), "affiliation1",0);
		Set<Link> links = new HashSet<Link>();
		links.add(link);
		Set<Link> actual = msMock.updateLinksByModuleId(1, links);
		
		Assert.assertEquals(actual, links);
			
	}
}
