package com.revature.exceptions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;


/**
 * Simple Test for InvalidContentException to allow for testng full coverage.
 *
 */
public class InvalidContentExceptionTest {

	@Test
	public void InvalidContentExceptionMessageInjectedByThrowTestTrue() {
		try {
			throw new InvalidContentException("InvalidContentException message");
		} catch (InvalidContentException e) {
			assertEquals("InvalidContentException message", e.getMessage());
		}
	}

	@Test
	public void InvalidContentExceptionMessageInjectedBySuperTestTrue() {
		RuntimeException invalidContentException = new InvalidContentException("InvalidContentException message");
		assertEquals("InvalidContentException message", invalidContentException.getMessage());
	}

	@Test
	public void InvalidContentExceptionMessageInjectedByThrowTestFalse() {
		try {
			throw new InvalidContentException("InvalidContentException message");
		} catch (InvalidContentException e) {
			assertNotEquals("Testing failure", e.getMessage());
		}
	}

	@Test
	public void InvalidContentExceptionMessageInjectedBySuperTestFalse() {
		RuntimeException invalidContentException = new InvalidContentException("InvalidContentException message");
		assertNotEquals("Testing failure", invalidContentException.getMessage());
	}
}
