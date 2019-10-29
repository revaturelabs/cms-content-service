package com.revature.entitiesTest;

import static org.testng.Assert.assertTrue;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.entities.CurriculumModule;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.testingutils.TestingUtil;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class CurriculumModuleTest {

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
		m1 = new Module(1, "b", 1, null, null, null, null);
		m2 = new Module(2, "c", 2, null, null, null, null);
	}

	@AfterTest
	public void tearDown() {
		l1 = null;
		l2 = null;
		rL1 = null;
		rL2 = null;
		m1 = null;
		m2 = null;
	}

	@Test
	public void testToString() {
		assertTrue(new CurriculumModule(1,1,null,1).toString() instanceof String);
	}

	@Test
	public void testEqualsAndHash() {
		EqualsVerifier.forClass(CurriculumModule.class).usingGetClass().withPrefabValues(Link.class, l1, l2)
				.withPrefabValues(ReqLink.class, rL1, rL2).withPrefabValues(Module.class, m1, m2)
				.withIgnoredAnnotations(Entity.class, Id.class).suppress(Warning.NONFINAL_FIELDS).verify();
	}

	@Test
	public void testAccesors() {
		TestingUtil.validate(CurriculumModule.class);
	}
}
