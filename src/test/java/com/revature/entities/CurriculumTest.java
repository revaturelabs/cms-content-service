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

public class CurriculumTest {
	Content c1;
	Content c2;
	Link l1;
	Link l2;
	CurriculumModule cm1;
	CurriculumModule cm2;

	@BeforeTest
	public void setup() {
		c1 = new Content(1, "a", "b", "c", "d", 1, 2, null);
		c2 = new Content(2, "e", "f", "g", "h", 3, 4, null);
		l1 = new Link(1, null, null, "different", 1);
		l2 = new Link(2, null, null, "affiliations", 1);
		cm1 = new CurriculumModule(1, 1, null, 1);
		cm2 = new CurriculumModule(2, 2, null, 2);
	}

	@AfterTest
	public void tearDown() {
		c1 = null;
		c2 = null;
		l1 = null;
		l2 = null;
		cm1 = null;
		cm2 = null;
	}

	@Test
	public void testToString() {
		assertTrue(new Curriculum(1, null).toString() instanceof String);
	}

	@Test
	public void testEqualsAndHash() {
		EqualsVerifier.forClass(Curriculum.class).usingGetClass().withPrefabValues(Content.class, c1, c2)
				.withPrefabValues(Link.class, l1, l2).withPrefabValues(CurriculumModule.class, cm1, cm2)
				.withIgnoredAnnotations(Entity.class, Id.class).suppress(Warning.NONFINAL_FIELDS).verify();
	}

	@Test
	public void testAccesors() {
		TestingUtil.validate(Curriculum.class);
	}
}
