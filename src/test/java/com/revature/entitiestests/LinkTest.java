package com.revature.entitiestests;

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
	
	//Links that are being tested
	Link l1 = null;
	Link l2 = null;
	Module m1 = null;
	Module m2 = null;
	Content c1 = null;
	Content c2 = null;
	
	//The setup for the Links
	@BeforeTest
	public void setup() {
		m1 = new Module(1, "b", 1, null, null, null, null);
		m2 = new Module(2, "c", 2, null, null, null, null);
		c1 = new Content(1, "a", "b", "c", "d", 1, 2, null);
		c2 = new Content(2, "e", "f", "g", "h", 3, 4, null);
		l1 = new Link(1, c1, m1, "string here",0);
		l2 = new Link(5, c2, m2, "a",0);
	}
	
	//null out the Links
	@AfterTest
	public void teardown() {
		l1 = null;
		l2 = null;
		m1 = null;
		m2 = null;
		c1 = null;
		c2 = null;
	}
	
	//testing the constructors
	@Test
	public void testLink() {
		Link one = new Link();
		assertTrue(one instanceof Link);
		Link two = new Link();
		assertTrue(one != two);
	}

	@Test
	public void testLinkIntContentModuleString() {
		Link one = new Link(1, c1, m1, "test1",0);
		assertTrue(one instanceof Link);
		Link two = new Link(5, c2, m2, "test2",0);
		assertTrue(one != two);
	}

	//testing the getters and setters
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

	//Testing the ToString
	@Test
	public void testToString() {
		assertTrue(l1.toString() instanceof String);
		assertTrue(l2.toString() instanceof String);
	}

	//Test the equals and hash
	@Test
	public void testEqualsObject() {
		//equalsVerifier will test both equals and hash. It will fail and throw an error if it
		//finds something that it doesn't like. I am suppressing the nonfinal_fields warning
		//because it is necessary for spring boot
		EqualsVerifier.forClass(Link.class)
		.withPrefabValues(Content.class, c1, c2)
		.withPrefabValues(Module.class, m1, m2)
		.suppress(Warning.NONFINAL_FIELDS)
		.verify();
	}
}
