package com.revature.exceptions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

/**
 * Class for testing {@link com.revature.exceptions.InvalidRequestException InvalidRequestException}
 */
public class InvalidRequestExceptionTest {

	/**
	 * Test for throwing an {@link com.revature.exceptions.InvalidRequestException InvalidRequestException}
	 */
	@Test
	public void InvalidRequestExceptionMessageInjectedByThrowTestTrue() {
		try {
			throw new InvalidRequestException("InvalidRequestException message");
		} catch (InvalidRequestException e) {
			assertEquals("InvalidRequestException message", e.getMessage());
		}
	}

	/**
	 * Test for ensuring that an {@link com.revature.exceptions.InvalidRequestException InvalidRequestException} is a {@link java.lang.RuntimeException RuntimeException}
	 */
	@Test
	public void InvalidRequestExceptionMessageInjectedBySuperTestTrue() {
		RuntimeException invalidRequestException = new InvalidRequestException("InvalidRequestException message");
		assertEquals("InvalidRequestException message", invalidRequestException.getMessage());
	}
}
