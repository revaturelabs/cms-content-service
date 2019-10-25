package com.revature.exceptions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

/**
 * Class for testing {@link com.revature.exceptions.InvalidContentId InvalidContentId}
 *
 */
public class InvalidContentIdTest {

	/**
	 * Test for throwing an {@link com.revature.exceptions.InvalidContentId InvalidContentId} exception
	 */
	@Test
	public void testInvalidContentId_MessageInjectedByThrow() {
		try {
			throw new InvalidContentId("InvalidContentId message");
		} catch (InvalidContentId e) {
			assertEquals("InvalidContentId message", e.getMessage());
		}
	}

	/**
	 * Test for ensuring that an {@link com.revature.exceptions.InvalidContentId InvalidContentId} is a {@link java.lang.RuntimeException RuntimeException}
	 */
	@Test
	public void testInvalidContentId_MessageInjectedBySuper() {
		RuntimeException invalidContentId = new InvalidContentId("InvalidContentId message");
		assertEquals("InvalidContentId message", invalidContentId.getMessage());
	}
}
