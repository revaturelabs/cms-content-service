package com.revature.entities;

import static org.testng.Assert.assertTrue;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.revature.testingutils.TestingUtil;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class ModuleTest {

	Link l1;
	Link l2;
	ReqLink rL1;
	ReqLink rL2;
	Module m1;
	Module m2;

	@BeforeTest
	public void setup() {
		l1 = new Link(1, null, null, "different", 1);
		l2 = new Link(2, null, null, "affiliations", 1);
		rL1 = new ReqLink(1, null, null, "different");
		rL2 = new ReqLink(2, null, null, "affiliations");
		m1 = new Module(1, "Java", 56890, null, null, null, null);
		m2 = new Module(2, "C#", 67953, null, null, null, null);
	}

	@AfterTest
	public void tearDown() {
		l1 = null;
		l2 = null;
		rL2 = null;
		rL2 = null;
		m1 = null;
		m2 = null;
	}
	
	@Test
	public void testToString() {
		assertTrue(new Module().toString() instanceof String);
	}

	@Test
	public void testEqualsAndHash() {
		EqualsVerifier.forClass(Module.class).usingGetClass().withPrefabValues(Link.class, l1, l2)
				.withPrefabValues(ReqLink.class, rL1, rL2).withPrefabValues(Module.class, m1, m2)
				.withIgnoredFields("links").withIgnoredFields("reqLinks").withIgnoredFields("children")
				.withIgnoredAnnotations(Entity.class, Id.class).suppress(Warning.NONFINAL_FIELDS).verify();
	}

	@Test
	public void testAccesors() {
		TestingUtil.validate(Module.class);
	}
}
