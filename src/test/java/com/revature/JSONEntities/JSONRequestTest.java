package com.revature.JSONEntities;

import static org.testng.Assert.assertTrue;

import java.util.HashSet;

import org.testng.annotations.Test;

import com.revature.JSONEntities.JSONRequest;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.ReqLink;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class JSONRequestTest {

	@Test
	public void testToString() {
		assertTrue(new JSONRequest().toString() instanceof String);
	}

	@Test
	public void testContentInstanceOf() {
		JSONRequest one = new JSONRequest(1, "GET Test", "JSON", "This tests a get request", null, 92846L, 93847L,
				null);
		assertTrue(one instanceof JSONRequest);
	}
	
	@Test
	public void testContentEquals() {
		JSONRequest one = new JSONRequest(1, "GET Test", "JSON", "This tests a get request", null, 92846L, 93847L,
				null);
		JSONRequest two = new JSONRequest();
		assertTrue(one != two);
	}

	/**
	 * EqualsVerifier will throw an AssertionError if there are any issues with its
	 * utilization. The suppression for non final fields is for the error
	 * "Mutability: equals depends on mutable field". It is generally not
	 * recommended to use this approach but the JSONRequest class itself or its
	 * fields would have to be modified with final to properly address this.
	 */
	@Test
	public void equalsTest() {
		EqualsVerifier.forClass(JSONRequest.class)
				.withPrefabValues(ReqLink.class, new ReqLink(1, null, null, "different"),
						new ReqLink(2, null, null, "affiliations"))
				.withPrefabValues(Content.class,
						new Content(99, "Java a New Begining", "String", "The Java that brought hope back",
								"https://en.wikipedia.org/wiki/Star_Wars_(film)", 15554l, 15554l, new HashSet<Link>()),
						new Content(47, "Java the savior", "String", "Java king of kings",
								"https://en.wikipedia.org/wiki/Star_Wars_(film)", 87392, 84934, new HashSet<Link>()))
				.withPrefabValues(Link.class, new Link(1, null, null, "different", 1),
						new Link(2, null, null, "affiliations", 1))
				.usingGetClass().suppress(Warning.NONFINAL_FIELDS).verify();
	}
}
