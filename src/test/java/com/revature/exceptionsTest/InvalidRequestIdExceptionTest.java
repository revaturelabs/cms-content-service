package com.revature.exceptionsTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

import com.revature.exceptions.InvalidRequestException;
import com.revature.exceptions.InvalidRequestIdException;

public class InvalidRequestIdExceptionTest {

	@Test
	public void InvalidRequestIdExceptionMessageInjectedByThrowTestTrue() {
		try {
			throw new InvalidRequestIdException("InvalidRequestIdException message");
		} catch (InvalidRequestIdException e) {
			assertEquals("InvalidRequestIdException message", e.getMessage());
		}
	}

	@Test
	public void InvalidRequestIdExceptionMessageInjectedBySuperTestTrue() {
		RuntimeException invalidRequestIdException = new InvalidRequestIdException("InvalidRequestIdException message");
		assertEquals("InvalidRequestIdException message", invalidRequestIdException.getMessage());
	}

	@Test
	public void InvalidRequestIdExceptionMessageInjectedByThrowTestFalse() {
		try {
			throw new InvalidRequestIdException("InvalidRequestIdException message");
		} catch (InvalidRequestIdException e) {
			assertNotEquals("Testing failure", e.getMessage());
		}
	}

	@Test
	public void InvalidRequestIdExceptionMessageInjectedBySuperTestFalse() {
		RuntimeException invalidRequestIdException = new InvalidRequestException("InvalidRequestIdException message");
		assertNotEquals("Testing failure", invalidRequestIdException.getMessage());
	}
}
