package com.revature.entitiestests;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.entities.Content;
import com.revature.entities.Link;

public class ContentTest {
	Content c1 = null;
	Content c2 = null;
	
	@BeforeTest
	public void setup() {
		Content c1 = new Content(99, "Java a New Begining", "String", "The Java the brought hope back", "https://en.wikipedia.org/wiki/Star_Wars_(film)",
				new HashSet<Link>(), 15554l, 15554l);
		Content c2 = new Content(114, "Java the phantom menance", "String", "The one with the cool darth", "https://en.wikipedia.org/wiki/Star_Wars_(film)",
				new HashSet<Link>(), 1555444l, 1555444l);
	}
	
	@Test
	public void testContent() {
		Content one = new Content();
		assertTrue(one instanceof Content);
		Content two = new Content();
		assertTrue(one != two);
	}

	@Test
	public void testContentIntStringStringStringStringSetOfLinkLongLong() {
		Content one = new Content(99, "Java a New Begining", "String", "The Java the brought hope back", "https://en.wikipedia.org/wiki/Star_Wars_(film)",
				new HashSet<Link>(), 15554l, 15554l);
		assertTrue(one instanceof Content);
		Content two = new Content(114, "Java the phantom menance", "String", "The one with the cool darth", "https://en.wikipedia.org/wiki/Star_Wars_(film)",
				new HashSet<Link>(), 1555444l, 1555444l);
		assertTrue(one != two);
	}

	@Test
	public void testGetId() {
		assertTrue(c1.getId() == 99);
		assertTrue(c2.getId() == 114);
	}

	@Test
	public void testSetId() {
		c2.setId(1144);
		assertTrue(c2.getId() == 1144);
	}

	@Test
	public void testGetTitle() {
		assertTrue(c1.getTitle().equals("Java a New Begining"));
	}

	@Test
	public void testSetTitle() {
		c1.setTitle("Java The last of the Jars");
		assertTrue(c2.getTitle().equals("Java The last of the Jars"));
	}
	
	@Test
	public void testGetFormat() {
		
	}

	@Test
	public void testSetFormat() {
		
	}

	@Test
	public void testGetDescription() {
		
	}

	@Test
	public void testSetDescription() {
		
	}

	@Test
	public void testGetUrl() {
		
	}

	@Test
	public void testSetUrl() {
		
	}

	@Test
	public void testGetLinks() {
		
	}

	@Test
	public void testSetLinks() {
		
	}

	@Test
	public void testGetDateCreated() {
		
	}

	@Test
	public void testSetDateCreated() {
		
	}

	@Test
	public void testGetLastModified() {
		
	}

	@Test
	public void testSetLastModified() {
		
	}

	@Test
	public void testToString() {
		
	}

	@Test
	public void testEqualsObject() {
		
	}
}
