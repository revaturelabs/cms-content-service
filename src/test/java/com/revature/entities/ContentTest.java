package com.revature.entities;

import static org.testng.Assert.assertTrue;
import java.util.HashSet;
import org.springframework.stereotype.Component;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.revature.testingutils.TestingUtil;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

@Component
public class ContentTest {

	Content c1;;
	Link l1;
	Link l2;

	@BeforeTest
	public void setup() {
		c1 = new Content(99, "Java a New Begining", "String", "The Java the brought hope back",
				"https://en.wikipedia.org/wiki/Star_Wars_(film)", 15554l, 15554l, new HashSet<Link>());
		l1 = new Link(1, null, null, "different", 0);
		l2 = new Link(2, null, null, "affiliations", 0);
	}

	@AfterTest
	public void tearDown() {
		c1 = null;
		l1 = null;
		l2 = null;
	}
	@Test
	public void testToString() {
		assertTrue(c1.toString() instanceof String);
	}

	@Test
	public void testEqualsAndHash() {
		EqualsVerifier.forClass(Content.class).usingGetClass().withPrefabValues(Link.class, l1, l2)
				.withIgnoredFields("links").suppress(Warning.NONFINAL_FIELDS).verify();
	}

	@Test
	public void testAccesors() {
		TestingUtil.validate(Content.class);
	}

}
