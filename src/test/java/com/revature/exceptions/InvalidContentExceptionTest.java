package com.revature.exceptions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;


/**
 * Class for testing {@link com.revature.exceptions.InvalidContentException InvalidContentException}
 *
 */
public class InvalidContentExceptionTest {

	/**
	 * Test for throwing an {@link com.revature.exceptions.InvalidContentException InvalidContentException}
	 */
	@Test
	public void testInvalidContentException_MessageInjectedByThrow() {
		try {
			throw new InvalidContentException("InvalidContentException message");
		} catch (InvalidContentException e) {
			assertEquals("InvalidContentException message", e.getMessage());
		}
	}

	/**
	 * Test for ensuring that an {@link com.revature.exceptions.InvalidContentException InvalidContentException} is a {@link java.lang.RuntimeException RuntimeException}
	 */
	@Test
	public void testInvalidContentException_MessageInjectedBySuper() {
		RuntimeException invalidContentException = new InvalidContentException("InvalidContentException message");
		assertEquals("InvalidContentException message", invalidContentException.getMessage());
	}
}
