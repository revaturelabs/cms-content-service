package com.revature.entitiestests;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.entities.Link;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class LinkTest {
	
	//Links that are being tested
	Link l1 = null;
	Link l2 = null;
	
	//The setup for the Links
	@BeforeTest
	public void setup() {
		l1 = new Link(1, 2, 3, "Java Wars");
		l2 = new Link(5, 6, 7, "HTML Track");
	}
	
	//null out the Links
	@AfterTest
	public void teardown() {
		l1 = null;
		l2 = null;
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
	public void testLinkIntIntIntString() {
		Link one = new Link(1, 2, 3, "Java Wars");
		assertTrue(one instanceof Link);
		Link two = new Link(5, 6, 7, "HTML Track");
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
	public void testGetContentId() {
		assertTrue(l2.getContentId() == 6);
	}

	@Test
	public void testSetContentId() {
		l2.setContentId(66);
		assertTrue(l2.getContentId() == 66);
	}

	@Test
	public void testGetModuleId() {
		assertTrue(l1.getModuleId() == 3);
	}

	@Test
	public void testSetModuleId() {
		l1.setModuleId(445);
		assertTrue(l1.getModuleId() == 445);
	}

	@Test
	public void testGetAffiliation() {
		assertTrue(l2.getAffiliation().equals("HTML Track"));
	}

	@Test
	public void testSetAffiliation() {
		l2.setAffiliation("CSS Blitz");
		assertTrue(l2.getAffiliation().equals("CSS Blitz"));
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
		.suppress(Warning.NONFINAL_FIELDS)
		.verify();
	}
}
