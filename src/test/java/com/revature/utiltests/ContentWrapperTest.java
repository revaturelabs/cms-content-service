package com.revature.utiltests;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.util.ContentWrapper;

public class ContentWrapperTest {
	
	//The components needed to test
	Content c1;
	Content c2;
	Link[] links;
	
	ContentWrapper cw1;
	ContentWrapper cw2;
	
	//initialize the components
	@BeforeTest
	void setup() {
		c1 = new Content(99, "Java a New Begining", "String", 
						"The Java the brought hope back", 
						"https://en.wikipedia.org/wiki/Star_Wars_(film)",
				new HashSet<Link>(), 15554l, 15554l);
		c2 = new Content(114, "Java the phantom menance", "String", 
						"The one with the cool darth", 
						"https://en.wikipedia.org/wiki/Star_Wars_(film)",
				new HashSet<Link>(), 1555444l, 1555444l);
		links = new Link[2];
		
		cw1 = new ContentWrapper(c1, links);
		cw2 = new ContentWrapper(c2, links);
	}
	
	//tear down the components
	@AfterTest
	void teardown() {
		c1 = null;
		c2 = null;
		links = null;
		cw1 = null;
		cw2 = null;
	}
	
	//test the constructors
	@Test
	public void testContentWrapper() {
		ContentWrapper one = new ContentWrapper();
		assertTrue(one instanceof ContentWrapper);
		ContentWrapper two = new ContentWrapper();
		assertTrue(one != two);
	}

	@Test
	public void testContentWrapperContentLinkArray() {
		ContentWrapper one = new ContentWrapper(c1, links);
		assertTrue(one instanceof ContentWrapper);
		ContentWrapper two = new ContentWrapper(c2, links);
		assertTrue(one != two);
	}

	//test the getters and setters
	@Test
	public void testGetContent() {
		assertTrue(cw1.getContent().equals(c1));
	}

	@Test
	public void testSetContent() {
		Content c3 = new Content();
		cw1.setContent(c3);
		assertTrue(cw1.getContent().equals(c3));
	}

	@Test
	public void testGetLinks() {
		assertTrue(cw2.getLinks().equals(links));
	}

	@Test
	public void testSetLinks() {
		Link[] tmp = new Link[8];
		cw2.setLinks(tmp);
		assertTrue(cw2.getLinks().equals(tmp));
	}

	//test the toString
	@Test
	public void testToString() {
		assertTrue(cw1.toString() instanceof String);
		assertTrue(cw2.toString() instanceof String);
	}
}
