package com.revature.exceptions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

/**
 * Class for testing {@link com.revature.exceptions.InvalidSearchException InvalidSearchException}
 *
 */
public class InvalidSearchExceptionTest {

	/**
	 * Test for throwing an {@link com.revature.exceptions.InvalidSearchException InvalidSearchException}
	 */
	@Test
	public void InvalidSearchExceptionMessageInjectedByThrowTestTrue() {
		try {
			throw new InvalidSearchException("InvalidSearchException message");
		} catch (InvalidSearchException e) {
			assertEquals("InvalidSearchException message", e.getMessage());
		}
	}

	/**
	 * Test for ensuring that an {@link com.revature.exceptions.InvalidSearchException InvalidSearchException} is a {@link java.lang.RuntimeException RuntimeException}
	 */
	@Test
	public void InvalidSearchExceptionMessageInjectedBySuperTestTrue() {
		RuntimeException invalidSearchException = new InvalidSearchException("InvalidSearchException message");
		assertEquals("InvalidSearchException message", invalidSearchException.getMessage());
	}
}
