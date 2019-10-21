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

public class LinkTest {

	Module m1;
	Module m2;
	Content c1;
	Content c2;

	@BeforeTest
	public void setup() {
		m1 = new Module(1, "b", 1, null, null, null, null);
		m2 = new Module(2, "c", 2, null, null, null, null);
		c1 = new Content(1, "a", "b", "c", "d", 1, 2, null);
		c2 = new Content(2, "e", "f", "g", "h", 3, 4, null);
	}

	@AfterTest
	public void tearDown() {
		m1 = null;
		m2 = null;
		c1 = null;
		c2 = null;
	}
	
	@Test
	public void testToString() {
		assertTrue(new Link(1, null, null, null, 0).toString() instanceof String);
	}

	@Test
	public void testEqualsAndHash() {
		EqualsVerifier.forClass(Link.class).usingGetClass().withPrefabValues(Content.class, c1, c2)
				.withPrefabValues(Module.class, m1, m2).withIgnoredAnnotations(Entity.class, Id.class)
				.suppress(Warning.NONFINAL_FIELDS).verify();
	}

	@Test
	public void testAccesors() {
		TestingUtil.validate(Link.class);
	}

}
