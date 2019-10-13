package com.revature.entitiestests;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;

public class ModuleTest {
	
	Module module;
	Set<Module> children;
	Set<ReqLink> reqLinks;
	Set<Link> links;

	@BeforeTest
	public void setup() {
		children = new HashSet<Module>();
		reqLinks = new HashSet<ReqLink>();
		links = new HashSet<Link>();
		module = new Module();
		module.setChildren(children);
		module.setReqLinks(reqLinks);
		module.setLinks(links);
	}

	@AfterTest
	public void teardown() {
		module = null;
		children = null;
	}

	@Test
	public void equals_WhenThisEqualsObject_True() {
		Module module1 = new Module(1, "Java", 56890, null, null, null, null);
		assertTrue(module1.equals(module1));
	}

	@Test
	public void equals_WhenCreatedNotEqual_False() {
		Module module1 = new Module(1, "Java", 56890, null, null, null, null);
		Module module2 = new Module(2, "C#", 67953, null, null, null, null);
		assertFalse(module1.equals(module2));
	}

	@Test
	public void equals_WhenObjectEqualsNull_False() {
		Module module1 = new Module(1, "Java", 56890, null, null, null, null);
		Module module2 = null;
		assertFalse(module1.equals(module2));
	}

	@Test
	public void equals_GetClassNotEqual_False() {
		Module module1 = new Module(1, "Java", 56890, null, null, null, null);
		class ModuleSubclass extends Module {
		}
		assertFalse(module1.equals(new ModuleSubclass()));
	}

	@Test
	public void equals_WhenIdSubjectCreatedEqual_True() {
		Module module1 = new Module(1, "Java", 56890, null, null, null, null);
		Module module2 = new Module(1, "Java", 56890, null, null, null, null);
		assertTrue(module1.equals(module2));
	}

	@Test
	public void equals_WhenIdNull_False() {
		Module module1 = new Module(null, "Java", 56890, null, null, null, null);
		Module module2 = new Module(1, "Java", 56890, null, null, null, null);
		assertFalse(module1.equals(module2));
	}

	@Test
	public void equals_WhenIdAndObjectIdNull_True() {
		Module module1 = new Module(null, "Java", 56890, null, null, null, null);
		Module module2 = new Module(null, "Java", 56890, null, null, null, null);
		assertTrue(module1.equals(module2));
	}

	@Test
	public void equals_WhenIdAndObjectIdNotEqual_False() {
		Module module1 = new Module(1, "Java", 56890, null, null, null, null);
		Module module2 = new Module(2, "Java", 56890, null, null, null, null);
		assertFalse(module1.equals(module2));
	}

	@Test
	public void equals_WhenObjectParentsNotNull_False() {
		Module module1 = new Module(1, "Java", 56890, null, null, null, null);
		Module module2 = new Module(1, "Java", 56890, null, null, new HashSet<Module>(), null);
		assertFalse(module1.equals(module2));
	}

	@Test
	public void equals_WhenParentsNotEqual_False() {
		HashSet<Module> hashSet1 = new HashSet<>();
		hashSet1.add(new Module());
		Module module1 = new Module(1, "Java", 56890, null, null, hashSet1, null);
		Module module2 = new Module(1, "Java", 56890, null, null, new HashSet<Module>(), null);
		assertFalse(module1.equals(module2));
	}

	@Test
	public void equals_WhenParentsEqual_True() {
		Module module1 = new Module(1, "Java", 56890, null, null, new HashSet<Module>(), null);
		Module module2 = new Module(1, "Java", 56890, null, null, new HashSet<Module>(), null);
		assertTrue(module1.equals(module2));
	}

	@Test
	public void equals_WhenSubjectNull_False() {
		Module module1 = new Module(1, null, 56890, null, null, new HashSet<Module>(), null);
		Module module2 = new Module(1, "Java", 56890, null, null, new HashSet<Module>(), null);
		assertFalse(module1.equals(module2));
	}

	@Test
	public void equals_WhenBothSubjectNull_False() {
		Module module1 = new Module(1, null, 56890, null, null, new HashSet<Module>(), null);
		Module module2 = new Module(1, null, 56890, null, null, new HashSet<Module>(), null);
		assertTrue(module1.equals(module2));
	}

	@Test
	public void equals_WhenBothSubjectNotEqual_False() {
		Module module1 = new Module(1, "Java", 56890, null, null, new HashSet<Module>(), null);
		Module module2 = new Module(1, "C#", 56890, null, null, new HashSet<Module>(), null);
		assertFalse(module1.equals(module2));
	}

	@Test
	public void setChildrenTest_True() {
		assertTrue(module.getChildren().equals(children));
	}

	@Test
	public void setChildrenTest_False() {
		children.add(new Module());
		assertFalse(module.getChildren().equals(new HashSet<Module>()));
	}

	@Test
	public void setReqLinksTest_True() {
		assertTrue(module.getReqLinks().equals(reqLinks));
	}

	@Test
	public void setReqLinksTest_False() {
		reqLinks.add(new ReqLink());
		assertFalse(module.getReqLinks().equals(new HashSet<ReqLink>()));
	}

	@Test
	public void setLinksTest_True() {
		assertTrue(module.getLinks().equals(links));
	}

	@Test
	public void setLinksTest_False() {
		links.add(new Link());
		assertFalse(module.getLinks().equals(new HashSet<Link>()));
	}
	
	
	
	//Any time that two nulls appear in a test of a constructor, that is for a feature that was created after the tests were created to allow them to pass.
	/*
	//Module to be tested
	Module m1 = null;
	Module m2 = null;
	
	//make the modules that are being tested
	@BeforeTest
	public void setup() {
//		m1 = new Module(1, "Java", 1544l, new HashSet<Link>(), null, null);
//		m2 = new Module(2, "HTML", 154554l, new HashSet<Link>(), null, null);
	}
	
	//null the modules being tested
	@AfterTest
	public void teardown() {
		m1 = null;
		m2 = null;
	}
	
	//testing the constructors
	@Test
	void testModule() {
		Module one = new Module();
		assertTrue(one instanceof Module);
		Module two = new Module();
		assertTrue(one != two);
	}

	@Test
	void testModuleIntStringIntSetOfLink() {
//		Module one = new Module(1, "Java", 1544l, new HashSet<Link>(), null, null);
//		assertTrue(one instanceof Module);
//		Module two = new Module(2, "HTML", 154554l, new HashSet<Link>(), null, null);
//		assertTrue(one != two);

	}

	//testing the getters and setters
	@Test
	void testGetId() {
		assertTrue(m1.getId() == 1);
	}

	@Test
	void testSetId() {
		m1.setId(1414);
		assertTrue(m1.getId() == 1414);
	}

	@Test
	void testGetSubject() {
		assertTrue(m2.getSubject().equals("HTML"));
	}

	@Test
	void testSetSubject() {
		m2.setSubject("CSS");
		assertTrue(m2.getSubject().equals("CSS"));
	}

	@Test
	void testGetCreated() {
		assertTrue(m1.getCreated() == 1544l);
	}

	@Test
	void testSetCreated() {
		m1.setCreated(457744l);
		assertTrue(m1.getCreated() == 457744l);
	}

	@Test
	void testGetLinks() {
//		Set<Link> link = new HashSet<Link>();
//		assertTrue(m2.getLinks().equals(link));

	}

	@Test
	void testSetLinks() {
		Set<Link> link = new HashSet<Link>();
		link.add(new Link(1, 2, 3, "affiliation"));
		m2.setLinks(link);
		assertTrue(m2.getLinks().equals(link));
	}

	//testing the toString()
	@Test
	void testToString() {
		assertTrue(m1.toString() instanceof String);
		assertTrue(m2.toString() instanceof String);
	}

	//testing the equals and hash
	@Test
	void testEqualsObject() {
		//equalsVerifier will test both equals and hash. It will fail and throw an error if it
		//finds something that it doesn't like. I am suppressing the nonfinal_fields warning
		//because it is necessary for spring boot
		EqualsVerifier.forClass(Module.class)
		.suppress(Warning.NONFINAL_FIELDS)
		.verify();
	}
	*/
}
