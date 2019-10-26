package com.revature.exceptions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

/**
 * Class for testing {@link com.revature.exceptions.InvalidRequestIdException InvalidRequestIdException}
 */
public class InvalidRequestIdExceptionTest {

	/**
	 * Test for throwing an {@link com.revature.exceptions.InvalidRequestIdException InvalidRequestIdException}
	 */
	@Test
	public void InvalidRequestIdExceptionMessageInjectedByThrowTestTrue() {
		try {
			throw new InvalidRequestIdException("InvalidRequestIdException message");
		} catch (InvalidRequestIdException e) {
			assertEquals("InvalidRequestIdException message", e.getMessage());
		}
	}

	/**
	 * Test for ensuring that an {@link com.revature.exceptions.InvalidRequestIdException InvalidRequestIdException} is a {@link java.lang.RuntimeException RuntimeException}
	 */
	@Test
	public void InvalidRequestIdExceptionMessageInjectedBySuperTestTrue() {
		RuntimeException invalidRequestIdException = new InvalidRequestIdException("InvalidRequestIdException message");
		assertEquals("InvalidRequestIdException message", invalidRequestIdException.getMessage());
	}
}
