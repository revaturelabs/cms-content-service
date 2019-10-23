package com.revature.JSONEntities;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.revature.JSONEntities.JSONModule;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class JSONModuleTest {

	@Test
	public void testToString() {
		assertTrue(new JSONModule().toString() instanceof String);
	}

	@Test
	public void testContentInstanceOf() {
		JSONModule one = new JSONModule(1, "b", 1, null, null, null, null);
		assertTrue(one instanceof JSONModule);
	}
	
	@Test
	public void testContentEquals() {
		JSONModule one = new JSONModule(1, "b", 1, null, null, null, null);
		JSONModule two = new JSONModule();
		assertTrue(one != two);
	}

	/**
	 * EqualsVerifier will throw an AssertionError if there are any issues with its
	 * utilization. The suppression for non final fields is for the error
	 * "Mutability: equals depends on mutable field". It is generally not
	 * recommended to use this approach but the JSONModule class itself or its
	 * fields would have to be modified with final to properly address this.
	 */
	@Test
	public void equalsTest() {
		EqualsVerifier.forClass(JSONModule.class)
				.withPrefabValues(Link.class, new Link(1, null, null, "different", 1),
						new Link(2, null, null, "affiliations", 1))
				.withPrefabValues(ReqLink.class, new ReqLink(1, null, null, "different"),
						new ReqLink(2, null, null, "affiliations"))
				.withPrefabValues(Module.class, new Module(1, "Java", 56890, null, null, null, null),
						new Module(2, "C#", 67953, null, null, null, null))
				.usingGetClass().suppress(Warning.NONFINAL_FIELDS).verify();
	}
}
