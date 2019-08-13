package com.revature.entitiestests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.entities.Link;
import com.revature.entities.Module;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;



public class ModuleTest {
	
	Module m1 = null;
	Module m2 = null;
	
	@BeforeTest
	public void setup() {
		m1 = new Module(1, "Java", 1544l, new HashSet<Link>());
		m2 = new Module(2, "HTML", 154554l, new HashSet<Link>());
	}
	
	@Test
	void testModule() {
		Module one = new Module();
		assertTrue(one instanceof Module);
		Module two = new Module();
		assertTrue(one != two);
	}

	@Test
	void testModuleIntStringIntSetOfLink() {
		Module one = new Module(1, "Java", 1544l, new HashSet<Link>());
		assertTrue(one instanceof Module);
		Module two = new Module(2, "HTML", 154554l, new HashSet<Link>());
		assertTrue(one != two);
	}

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
		Set<Link> link = new HashSet<Link>();
		assertTrue(m2.getLinks().equals(link));
	}

	@Test
	void testSetLinks() {
		Set<Link> link = new HashSet<Link>();
		link.add(new Link(1, 2, 3, "Java Wars"));
		m2.setLinks(link);
		assertTrue(m2.getLinks().equals(link));
	}

	@Test
	void testToString() {
		assertTrue(m1.toString() instanceof String);
		assertTrue(m2.toString() instanceof String);
	}

	@Test
	void testEqualsObject() {
		EqualsVerifier.forClass(Module.class)
		.suppress(Warning.NONFINAL_FIELDS)
		.verify();
	}
}
