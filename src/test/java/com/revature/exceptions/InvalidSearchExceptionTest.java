package com.revature.exceptions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

public class InvalidSearchExceptionTest {

	@Test
	public void InvalidSearchExceptionMessageInjectedByThrowTestTrue() {
		try {
			throw new InvalidSearchException("InvalidSearchException message");
		} catch (InvalidSearchException e) {
			assertEquals("InvalidSearchException message", e.getMessage());
		}
	}

	@Test
	public void InvalidSearchExceptionMessageInjectedBySuperTestTrue() {
		RuntimeException invalidSearchException = new InvalidSearchException("InvalidSearchException message");
		assertEquals("InvalidSearchException message", invalidSearchException.getMessage());
	}

	@Test
	public void InvalidSearchExceptionMessageInjectedByThrowTestFalse() {
		try {
			throw new InvalidSearchException("InvalidSearchException message");
		} catch (InvalidSearchException e) {
			assertNotEquals("Testing failure", e.getMessage());
		}
	}

	@Test
	public void InvalidSearchExceptionMessageInjectedBySuperTestFalse() {
		RuntimeException invalidSearchException = new InvalidSearchException("InvalidSearchException message");
		assertNotEquals("Testing failure", invalidSearchException.getMessage());
	}
}
