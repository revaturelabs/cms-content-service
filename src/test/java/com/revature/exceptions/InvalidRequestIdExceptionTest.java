package com.revature.exceptions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

public class InvalidRequestIdExceptionTest {

	@Test
	public void InvalidRequestIdException_MessageInjectedByThrow_TestTrue() {
		try {
			throw new InvalidRequestIdException("InvalidRequestIdException message");
		} catch (InvalidRequestIdException e) {
			assertEquals("InvalidRequestIdException message", e.getMessage());
		}
	}

	@Test
	public void InvalidRequestIdException_MessageInjectedBySuper_TestTrue() {
		RuntimeException invalidRequestIdException = new InvalidRequestIdException("InvalidRequestIdException message");
		assertEquals("InvalidRequestIdException message", invalidRequestIdException.getMessage());
	}

	@Test
	public void InvalidRequestIdException_MessageInjectedByThrow_TestFalse() {
		try {
			throw new InvalidRequestIdException("InvalidRequestIdException message");
		} catch (InvalidRequestIdException e) {
			assertNotEquals("Testing failure", e.getMessage());
		}
	}

	@Test
	public void InvalidRequestIdException_MessageInjectedBySuper_TestFalse() {
		RuntimeException invalidRequestIdException = new InvalidRequestException("InvalidRequestIdException message");
		assertNotEquals("Testing failure", invalidRequestIdException.getMessage());
	}
}
