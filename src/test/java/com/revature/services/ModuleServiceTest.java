package com.revature.services;

import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import com.revature.entities.*;
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
	 * Tests getAllModules()
	 * Module Repository - findAll()
	 * Asserts true if a module list is returned
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
	 * Tests GetModuleById()
	 * Module Repository - findById()
	 * Asserts True if temp variable equals m1 Module object
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
	 * Tests createModule()
	 * Module Repository - save()
	 * Asserts true if temp variable equals m4 Module object
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
	 * Tests getAverageByModuleIds
	 * Link Repository - findByModuleIdin()
	 * Assert True if temp variable is greater than | equal to 0.0
	 */
	@Test
	void testGetAverageByModules() {
	 	Link link1 = new Link(1, new Content(), new Module(), "affiliation1");
		Link link2 = new Link(2, new Content(), new Module(), "affiliation2");
		Link link3 = new Link(3, new Content(), new Module(), "affiliation3");

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
	 * Tests getAverageByAllModules()
	 * Link Repository - findByModuleIdIn()
	 * Module Repository - findAll()
	 * Asserts true if temp variable is greater than | equal to 0.0
	 */
	@Test
	void testGetAverageByAllModules() {
		Link link1 = new Link(1, new Content(), new Module(), "affiliation1");
		Link link2 = new Link(2, new Content(), new Module(), "affiliation2");
		Link link3 = new Link(3, new Content(), new Module(), "affiliation3");

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

		Mockito.when(mrMock.findAll()).thenReturn(modules);

		double tmp = msMock.getAverageByAllModules();

		Mockito.verify(mrMock).findAll();
		assertTrue(Double.compare(tmp, 1.5) == 0);
	}

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
	 * Tests delete()
	 */
	@Test
	void testDeleteModule() {
		Module mod1 = new Module(1, "Java", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());

		Mockito.doNothing().when(mrMock).delete(mod1);
		msMock.deleteModule(mod1);

		Mockito.verify(mrMock).delete(mod1);
	}

	@Test
	void testDeleteModuleWithNullModule() {
		Mockito.doNothing().when(mrMock).delete(null);
		msMock.deleteModule(null);

		Mockito.verify(mrMock, Mockito.times(0)).delete(null);
	}

	@Test
	void testDeleteModuleWithAllContent() {
		Link link1 = new Link(1, new Content(), new Module(), "affiliation1");
		Link link2 = new Link(2, new Content(), new Module(), "affiliation2");
		Link link3 = new Link(3, new Content(), new Module(), "affiliation3");

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

	@Test
	void testDeleteModuleWithSpecificContent() {
		// Given
		Link link1 = new Link(1, new Content(), new Module(), "affiliation1");
		Link link2 = new Link(2, new Content(), new Module(), "affiliation2");
		Link linkToDelete = new Link(3, new Content(), new Module(), "affiliation3");

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

	@Test
	void testGetLinksByModuleId() {
		final int id = 1;
		Link link1 = new Link(1, new Content(), new Module(), "affiliation1");
		Link link2 = new Link(2, new Content(), new Module(), "affiliation2");

		Set<Link> links = new HashSet<Link>();
		links.add(link1);
		links.add(link2);

		Mockito.when(lrMock.findByModuleId(id)).thenReturn(links);

		Set<Link> actual = msMock.getLinksByModuleId(id);

		Mockito.verify(lrMock).findByModuleId(id);
		Assert.assertEquals(actual, links);
	}

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
}
