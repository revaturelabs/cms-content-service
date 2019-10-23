package com.revature.exceptions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

public class InvalidRequestExceptionTest {

	@Test
	public void InvalidRequestExceptionMessageInjectedByThrowTestTrue() {
		try {
			throw new InvalidRequestException("InvalidRequestException message");
		} catch (InvalidRequestException e) {
			assertEquals("InvalidRequestException message", e.getMessage());
		}
	}

	@Test
	public void InvalidRequestExceptionMessageInjectedBySuperTestTrue() {
		RuntimeException invalidRequestException = new InvalidRequestException("InvalidRequestException message");
		assertEquals("InvalidRequestException message", invalidRequestException.getMessage());
	}

	@Test
	public void InvalidRequestExceptionMessageInjectedByThrowTestFalse() {
		try {
			throw new InvalidRequestException("InvalidRequestException message");
		} catch (InvalidRequestException e) {
			assertNotEquals("Testing failure", e.getMessage());
		}
	}

	@Test
	public void InvalidRequestExceptionMessageInjectedBySuperTestFalse() {
		RuntimeException invalidRequestException = new InvalidRequestException("InvalidRequestException message");
		assertNotEquals("Testing failure", invalidRequestException.getMessage());
	}
}
