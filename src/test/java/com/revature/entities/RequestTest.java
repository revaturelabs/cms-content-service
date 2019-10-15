package com.revature.entities;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.AfterTest;

import com.revature.entities.Request;
import com.revature.entities.Content;
import com.revature.entities.ReqLink;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class RequestTest {
	
	//Variables needed to fully test requests
	Request r1 = null;
	Request r2 = null;
	Content c1 = null;
	Content c2 = null;
	ReqLink rl1 = null;
	ReqLink rl2 = null;
	Set<ReqLink> set1 = null;
	Set<ReqLink> set2 = null;
	
	@BeforeTest
	public void setUp() {
		c1 = new Content(1, "a", "b", "c", "d", 1, 2, null);
		c2 = new Content(2, "e", "f", "g", "h", 3, 4, null);
		rl1 = new ReqLink(1, null, null, "test 1");
		rl2 = new ReqLink(2, null, null, "Tests 2");
		set1 = new HashSet<ReqLink>();
		set2 = new HashSet<ReqLink>();
		set1.add(rl1);
		set2.add(rl2);
		r1 = new Request(1, "This", "is", "a test", c1, 1l, 2l, null);
		r2 = new Request(2, "Here", "is another", "test", c2, 3l, 4l, null);
	}

	@AfterTest
	public void tearDown() {
		r1 = null;
		r2 = null;
		set1 = null;
		set2 = null;
		rl1 = null;
		rl2 = null;
		c1 = null;
		c2 = null;
	}
//testing the constructors
	@Test
	public void TestRequest() {
		Request one = new Request();
		assertTrue(one instanceof Request);
		Request two = new Request();
		assertTrue(one != two);
	}

	@Test
	public void RequestTestintStringStringStringContentLongLongSetReqLink() {
		Request one = new Request(1, "This", "is", "a test", c1, 1l, 2l, set1);
		assertTrue(one instanceof Request);
		Request two = new Request(2, "Here", "is another", "test", c2, 3l, 4l, set2);
		assertTrue(one != two);
	}
// test the equals and hash
	@Test
	public void equalsTest() {
		//equalsVerifier will test both equals and hash. It will fail and throw an error if it
		//finds something that it doesn't like. I am suppressing the nonfinal_fields warning
		//because it is necessary for spring boot
		EqualsVerifier.forClass(Request.class)
		.withPrefabValues(Content.class, c1, c2)
		.withIgnoredFields("reqLinks")
		.withPrefabValues(ReqLink.class, rl1, rl2)
		.suppress(Warning.NONFINAL_FIELDS)
		.verify();
	}
// tests getters and setters
	@Test
	public void getContentTest() {
		assertTrue(r1.getContent().getId() == 1);
	}

	@Test
	public void getDateCreatedTest() {
		assertTrue(r1.getDateCreated() == 1l);
	}

	@Test
	public void getDescriptionTest() {
		assertTrue(r1.getDescription().equals("a test"));
	}

	@Test
	public void getFormatTest() {
		assertTrue(r1.getFormat().equals("is"));
	}

	@Test
	public void getIdTest() {
		assertTrue(r1.getId() == 1);
	}

	@Test
	public void getLastModifiedTest() {
		assertTrue(r1.getLastModified() == 2l);
	}

	@Test
	public void getReqLinksTest() {
		assertTrue(r1.getReqLinks() == null);
	}

	@Test
	public void getTitleTest() {
		assertTrue(r1.getTitle().equals("This"));
	}

	@Test
	public void setContentTest() {
		Request one = new Request();
		one.setContent(c2);
		assertTrue(one.getContent().getId() == 2);
	}

	@Test
	public void setDateCreatedTest() {
		Request one = new Request();
		one.setDateCreated(1l);
		assertTrue(one.getDateCreated() == 1l);
	}

	@Test
	public void setDescriptionTest() {
		Request one = new Request();
		one.setDescription("test test");
		assertTrue(one.getDescription().equals("test test"));
	}

	@Test
	public void setFormatTest() {
		Request one = new Request();
		one.setFormat("Test");
		assertTrue(one.getFormat().equals("Test"));
	}

	@Test
	public void setIdTest() {
		Request one = new Request();
		one.setId(1);
		assertTrue(one.getId() == 1);
	}

	@Test
	public void setLastModifiedTest() {
		Request one = new Request();
		one.setLastModified(1l);
		assertTrue(one.getLastModified() == 1l);
	}

	@Test
	public void setReqLinksTest() {
		Request one = new Request();
		one.setReqLinks(set1);
		assertTrue(one.getReqLinks().equals(set1));
	}

	@Test
	public void setTitleTest() {
		Request one = new Request();
		one.setTitle("test test test");
		assertTrue(one.getTitle().equals("test test test"));
	}
// tests the toString method
	@Test
	public void toStringTest() {
		assertTrue(r1.toString() instanceof String);
		assertTrue(r2.toString() instanceof String);
	}
}
