package com.revature.entitiestests;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.entities.Module;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class ReqLinkTest {
	//variables needed to test reqlink
	ReqLink rl1 = null;
	ReqLink rl2 = null;
	Request r1 = null;
	Request r2 = null;
	Module m1 = null;
	Module m2 = null;
	
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
// constructors
	@Test
	public void TestReqLink() {
		ReqLink one = new ReqLink();
		assertTrue(one instanceof ReqLink);
		ReqLink two = new ReqLink();
		assertTrue(one != two);
	}

	@Test
	public void ReqLinkTestintRequestModuleString() {
		ReqLink one = new ReqLink(1, r1, m1, "test");
		assertTrue(one instanceof ReqLink);
		ReqLink two = new ReqLink(2, r2, m2, "test squared");
		assertTrue(one != two);
	}
// tests the equals and hash
	@Test
	public void equalsTest() {
		//equalsVerifier will test both equals and hash. It will fail and throw an error if it
		//finds something that it doesn't like. I am suppressing the nonfinal_fields warning
		//because it is necessary for spring boot
		EqualsVerifier.forClass(ReqLink.class)
		.suppress(Warning.NONFINAL_FIELDS)
		.withPrefabValues(Request.class, r1, r2)
		.withPrefabValues(Module.class, m1, m2)
		.verify();
	}
// tests getters and setters
	@Test
	public void getAffiliationTest() {
		assertTrue(rl1.getAffiliation().equals("test"));
	}

	@Test
	public void getIdTest() {
		assertTrue(rl1.getId() == 1);
	}

	@Test
	public void getModuleTest() {
		assertTrue(rl1.getModule().getId() == 1);
	}

	@Test
	public void getRequestTest() {
		assertTrue(rl1.getRequest().getId() == 1);
	}

	@Test
	public void setAffiliationTest() {
		ReqLink one = new ReqLink();
		one.setAffiliation("test here");
		assertTrue(one.getAffiliation().equals("test here"));
	}

	@Test
	public void setIdTest() {
		ReqLink one = new ReqLink();
		one.setId(1);
		assertTrue(one.getId() == 1);
	}

	@Test
	public void setModuleTest() {
		ReqLink one = new ReqLink();
		one.setModule(m1);
		assertTrue(one.getModule().getId()== 1);
	}

	@Test
	public void setRequestTest() {
		ReqLink one = new ReqLink();
		one.setRequest(r1);
		assertTrue(one.getRequest().getId() == 1);
	}
// tests the toString method
	@Test
	public void toStringTest() {
		assertTrue(r1.toString() instanceof String);
		assertTrue(r2.toString() instanceof String);
	}
}
