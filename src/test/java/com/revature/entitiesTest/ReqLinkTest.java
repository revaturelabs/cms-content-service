package com.revature.entitiesTest;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.testingutils.TestingUtil;
import com.revature.entities.Module;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class ReqLinkTest {
	ReqLink rl1;
	ReqLink rl2;
	Request r1;
	Request r2;
	Module m1;
	Module m2;

	@BeforeTest
	public void setUp() {
		r1 = new Request(1, "This", "is", "a test", null, 1l, 2l, null);
		r2 = new Request(2, "Here", "is another", "test", null, 3l, 4l, null);
		m1 = new Module(1, "Hello", 1l, null, null, null, null);
		m2 = new Module(2, "There", 2l, null, null, null, null);
		rl1 = new ReqLink(1, r1, m1, "test");
		rl2 = new ReqLink(2, r2, m2, "test squared");
	}

	@AfterTest
	public void tearDown() {
		rl1 = null;
		rl2 = null;
		m1 = null;
		m2 = null;
		r1 = null;
		r2 = null;
	}
	
	@Test
	public void toStringTest() {
		assertTrue(rl1.toString() instanceof String);
	}

	@Test
	public void equalsTest() {
		EqualsVerifier.forClass(ReqLink.class).suppress(Warning.NONFINAL_FIELDS).withPrefabValues(Request.class, r1, r2)
				.withPrefabValues(Module.class, m1, m2).verify();
	}

	@Test
	public void testAccesors() {
		TestingUtil.validate(ReqLink.class);
	}

}
