package com.revature.entitiestests;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.entities.Content;
import com.revature.entities.Link;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class ContentTest {
	
	//The content that will be tested
	Content c1 = null;
	Content c2 = null;
	
	//initialize the testing content
	@BeforeTest
	public void setup() {
		c1 = new Content(99, "Java a New Begining", "String", "The Java the brought hope back", "https://en.wikipedia.org/wiki/Star_Wars_(film)",
				new HashSet<Link>(), 15554l, 15554l);
		c2 = new Content(114, "Java the phantom menance", "String", "The one with the cool darth", "https://en.wikipedia.org/wiki/Star_Wars_(film)",
				new HashSet<Link>(), 1555444l, 1555444l);
	}
	
	//null out the testing content
	@AfterTest
	public void teardown() {
		c1 = null;
		c2 = null;
	}
	
	//run tests
	//Constructor Tests
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

	//Getters and Setters tests
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
		assertTrue(c1.getTitle().equals("Java The last of the Jars"));
	}
	
	@Test
	public void testGetFormat() {
		assertTrue(c2.getFormat().equals("String"));
	}

	@Test
	public void testSetFormat() {
		c1.setFormat("TrueJava");
		assertTrue(c1.getFormat().equals("TrueJava"));
	}

	@Test
	public void testGetDescription() {
		assertTrue(c1.getDescription().equals("The Java the brought hope back"));
	}

	@Test
	public void testSetDescription() {
		c2.setDescription("Some Java Wars refference");
		assertTrue(c2.getDescription().equals("Some Java Wars refference"));
	}

	@Test
	public void testGetUrl() {
		assertTrue(c2.getUrl().equals("https://en.wikipedia.org/wiki/Star_Wars_(film)"));
	}

	@Test
	public void testSetUrl() {
		c1.setUrl("https://www.imdb.com/title/tt0076759/");
		assertTrue(c1.getUrl().equals("https://www.imdb.com/title/tt0076759/"));
	}

	@Test
	public void testGetLinks() {
		Set<Link> link = new HashSet<Link>();
		assertTrue(c1.getLinks().equals(link));
	}

	@Test
	public void testSetLinks() {
		Set<Link> link = new HashSet<Link>();
		link.add(new Link(55, 484, 555, "Java Wars"));
		c2.setLinks(link);
		assertTrue(c2.getLinks().equals(link));
	}

	@Test
	public void testGetDateCreated() {
		assertTrue(c2.getDateCreated() == 1555444l);
	}

	@Test
	public void testSetDateCreated() {
		c1.setDateCreated(114254l);
		assertTrue(c1.getDateCreated() == 114254l);
	}

	@Test
	public void testGetLastModified() {
		assertTrue(c2.getLastModified() == 1555444l);
	}

	@Test
	public void testSetLastModified() {
		c2.setLastModified(5542441l);
		assertTrue(c2.getLastModified() == 5542441l);
	}

	//toString Test
	@Test
	public void testToString() {
		assertTrue(c1.toString() instanceof String);
		assertTrue(c2.toString() instanceof String);
	}

	//Equals and Hash test
	@Test
	public void testEqualsObject() {
		//equalsVerifier will test both equals and hash. It will fail and throw an error if it
		//finds something that it doesn't like. I am suppressing the nonfinal_fields warning
		//because it is necessary for spring boot
		EqualsVerifier.forClass(Content.class)
		.suppress(Warning.NONFINAL_FIELDS)
		.verify();
	}
}
