package com.revature.entitiesTest;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.entities.Content;
import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.testingutils.TestingUtil;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class RequestTest {

	Content c1;
	Content c2;
	ReqLink rl1;
	ReqLink rl2;
	Request r1;
	Request r2;

	@BeforeTest
	public void setUp() {
		c1 = new Content(1, "a", "b", "c", "d", 1, 2, null);
		c2 = new Content(2, "e", "f", "g", "h", 3, 4, null);
		rl1 = new ReqLink(1, null, null, "test 1");
		rl2 = new ReqLink(2, null, null, "Tests 2");
		r1 = new Request(1, "This", "is", "a test", c1, 1l, 2l, null);
		r2 = new Request(2, "Here", "is another", "test", c2, 3l, 4l, null);
	}

	@AfterTest
	public void tearDown() {
		c1 = null;
		c2 = null;
		rl1 = null;
		rl2 = null;
		r1 = null;
		r2 = null;
	}
	
	@Test
	public void toStringTest() {
		assertTrue(r1.toString() instanceof String);
	}
	
	@Test
	public void equalsTest() {
		EqualsVerifier.forClass(Request.class).withPrefabValues(Content.class, c1, c2).withIgnoredFields("reqLinks")
				.withPrefabValues(ReqLink.class, rl1, rl2).suppress(Warning.NONFINAL_FIELDS).verify();
	}
	
	@Test
	public void testAccesors() {
		TestingUtil.validate(Request.class);
	}
}
