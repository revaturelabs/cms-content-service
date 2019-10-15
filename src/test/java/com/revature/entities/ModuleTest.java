package com.revature.entities;

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
	public void equalsWhenThisEqualsObjectTrue() {
		Module module1 = new Module(1, "Java", 56890, null, null, null, null);
		assertTrue(module1.equals(module1));
	}

	@Test
	public void equalsWhenCreatedNotEqualFalse() {
		Module module1 = new Module(1, "Java", 56890, null, null, null, null);
		Module module2 = new Module(2, "C#", 67953, null, null, null, null);
		assertFalse(module1.equals(module2));
	}

	@Test
	public void equalsWhenObjectEqualsNullFalse() {
		Module module1 = new Module(1, "Java", 56890, null, null, null, null);
		Module module2 = null;
		assertFalse(module1.equals(module2));
	}

	@Test
	public void equalsGetClassNotEqualFalse() {
		Module module1 = new Module(1, "Java", 56890, null, null, null, null);
		class ModuleSubclass extends Module {
		}
		assertFalse(module1.equals(new ModuleSubclass()));
	}

	@Test
	public void equalsWhenIdSubjectCreatedEqualTrue() {
		Module module1 = new Module(1, "Java", 56890, null, null, null, null);
		Module module2 = new Module(1, "Java", 56890, null, null, null, null);
		assertTrue(module1.equals(module2));
	}

	@Test
	public void equalsWhenIdNullFalse() {
		Module module1 = new Module(null, "Java", 56890, null, null, null, null);
		Module module2 = new Module(1, "Java", 56890, null, null, null, null);
		assertFalse(module1.equals(module2));
	}

	@Test
	public void equalsWhenIdAndObjectIdNullTrue() {
		Module module1 = new Module(null, "Java", 56890, null, null, null, null);
		Module module2 = new Module(null, "Java", 56890, null, null, null, null);
		assertTrue(module1.equals(module2));
	}

	@Test
	public void equalsWhenIdAndObjectIdNotEqualFalse() {
		Module module1 = new Module(1, "Java", 56890, null, null, null, null);
		Module module2 = new Module(2, "Java", 56890, null, null, null, null);
		assertFalse(module1.equals(module2));
	}

	@Test
	public void equalsWhenObjectParentsNotNullFalse() {
		Module module1 = new Module(1, "Java", 56890, null, null, null, null);
		Module module2 = new Module(1, "Java", 56890, null, null, new HashSet<Module>(), null);
		assertFalse(module1.equals(module2));
	}

	@Test
	public void equalsWhenParentsNotEqualFalse() {
		HashSet<Module> hashSet1 = new HashSet<>();
		hashSet1.add(new Module());
		Module module1 = new Module(1, "Java", 56890, null, null, hashSet1, null);
		Module module2 = new Module(1, "Java", 56890, null, null, new HashSet<Module>(), null);
		assertFalse(module1.equals(module2));
	}

	@Test
	public void equalsWhenParentsEqualTrue() {
		Module module1 = new Module(1, "Java", 56890, null, null, new HashSet<Module>(), null);
		Module module2 = new Module(1, "Java", 56890, null, null, new HashSet<Module>(), null);
		assertTrue(module1.equals(module2));
	}

	@Test
	public void equalsWhenSubjectNullFalse() {
		Module module1 = new Module(1, null, 56890, null, null, new HashSet<Module>(), null);
		Module module2 = new Module(1, "Java", 56890, null, null, new HashSet<Module>(), null);
		assertFalse(module1.equals(module2));
	}

	@Test
	public void equalsWhenBothSubjectNullFalse() {
		Module module1 = new Module(1, null, 56890, null, null, new HashSet<Module>(), null);
		Module module2 = new Module(1, null, 56890, null, null, new HashSet<Module>(), null);
		assertTrue(module1.equals(module2));
	}

	@Test
	public void equalsWhenBothSubjectNotEqualFalse() {
		Module module1 = new Module(1, "Java", 56890, null, null, new HashSet<Module>(), null);
		Module module2 = new Module(1, "C#", 56890, null, null, new HashSet<Module>(), null);
		assertFalse(module1.equals(module2));
	}

	@Test
	public void setChildrenTestTrue() {
		assertTrue(module.getChildren().equals(children));
	}

	@Test
	public void setChildrenTestFalse() {
		children.add(new Module());
		assertFalse(module.getChildren().equals(new HashSet<Module>()));
	}

	@Test
	public void setReqLinksTestTrue() {
		assertTrue(module.getReqLinks().equals(reqLinks));
	}

	@Test
	public void setReqLinksTestFalse() {
		reqLinks.add(new ReqLink());
		assertFalse(module.getReqLinks().equals(new HashSet<ReqLink>()));
	}

	@Test
	public void setLinksTestTrue() {
		assertTrue(module.getLinks().equals(links));
	}

	@Test
	public void setLinksTestFalse() {
		links.add(new Link());
		assertFalse(module.getLinks().equals(new HashSet<Link>()));
	}
}
