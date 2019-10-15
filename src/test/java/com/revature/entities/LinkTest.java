package com.revature.entities;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class LinkTest {

	Link l1 = null;
	Link l2 = null;
	Module m1 = null;
	Module m2 = null;
	Content c1 = null;
	Content c2 = null;

	@BeforeTest
	public void setup() {
		m1 = new Module(1, "b", 1, null, null, null, null);
		m2 = new Module(2, "c", 2, null, null, null, null);
		c1 = new Content(1, "a", "b", "c", "d", 1, 2, null);
		c2 = new Content(2, "e", "f", "g", "h", 3, 4, null);
		l1 = new Link(1, c1, m1, "string here");
		l2 = new Link(5, c2, m2, "a");
	}

	@AfterTest
	public void teardown() {
		l1 = null;
		l2 = null;
		m1 = null;
		m2 = null;
		c1 = null;
		c2 = null;
	}

	@Test
	public void testLinkInstanceOF() {
		Link one = new Link(1, c1, m1, "test1");
		assertTrue(one instanceof Link);
	}
	
	@Test
	public void testLinkNotEqual() {
		Link one = new Link(1, c1, m1, "test1");
		Link two = new Link(5, c2, m2, "test2");
		assertTrue(one != two);
	}

	@Test
	public void testGetId() {
		assertTrue(l1.getId() == 1);
	}

	@Test
	public void testSetId() {
		l1.setId(55);
		assertTrue(l1.getId() == 55);
	}

	@Test
	public void testGetContent() {
		assertTrue(l2.getContent().getId() == 2);
	}

	@Test
	public void testSetContent() {
		l2.setContent(c1);
		assertTrue(l2.getContent().getId() == 1);
	}

	@Test
	public void testGetModule() {
		assertTrue(l1.getModule().getId() == 1);
	}

	@Test
	public void testSetModule() {
		l1.setModule(m2);
		assertTrue(l1.getModule().getId() == 2);
	}

	@Test
	public void testToString() {
		assertTrue(l1.toString() instanceof String);
	}

	/**
	 * equalsVerifier will test both equals and hash. It will fail and throw an
	 * error if it finds something that it doesn't like. I am suppressing the
	 * nonfinal_fields warning because it is necessary for spring boot
	 */
	@Test
	public void testEqualsObject() {
		EqualsVerifier.forClass(Link.class).withPrefabValues(Content.class, c1, c2)
				.withPrefabValues(Module.class, m1, m2).suppress(Warning.NONFINAL_FIELDS).verify();
	}
}
