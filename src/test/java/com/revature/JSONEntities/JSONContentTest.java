package com.revature.JSONEntities;

import static org.testng.Assert.assertTrue;

import java.util.HashSet;

import org.testng.annotations.Test;

import com.revature.JSONEntities.JSONContent;
import com.revature.entities.Link;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class JSONContentTest {

	@Test
	public void testToString() {
		assertTrue(new JSONContent().toString() instanceof String);
	}

	@Test
	public void testContent() {
		JSONContent one = new JSONContent(99, "Java a New Begining", "String", "The Java the brought hope back",
				"https://en.wikipedia.org/wiki/Star_Wars_(film)", 15554l, 15554l, new HashSet<Link>());
		assertTrue(one instanceof JSONContent);
		JSONContent two = new JSONContent();
		assertTrue(one != two);
	}

	/**
	 * EqualsVerifier will throw an AssertionError if there are any issues with its
	 * utilization. The suppression for non final fields is for the error
	 * "Mutability: equals depends on mutable field". It is generally not
	 * recommended to use this approach but the JSONContent class itself or its
	 * fields would have to be modified with final to properly address this.
	 */
	@Test
	public void equalsTest() {
		EqualsVerifier.forClass(JSONContent.class)
				.withPrefabValues(Link.class, new Link(1, null, null, "different"),
						new Link(2, null, null, "affiliations"))
				.usingGetClass().suppress(Warning.NONFINAL_FIELDS).verify();
	}

}
