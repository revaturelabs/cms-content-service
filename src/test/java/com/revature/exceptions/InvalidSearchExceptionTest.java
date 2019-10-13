package com.revature.exceptions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

public class InvalidSearchExceptionTest {

	@Test
	public void InvalidSearchException_MessageInjectedByThrow_TestTrue() {
		try {
			throw new InvalidSearchException("InvalidSearchException message");
		} catch (InvalidSearchException e) {
			assertEquals("InvalidSearchException message", e.getMessage());
		}
	}

	@Test
	public void InvalidSearchException_MessageInjectedBySuper_TestTrue() {
		RuntimeException invalidSearchException = new InvalidSearchException("InvalidSearchException message");
		assertEquals("InvalidSearchException message", invalidSearchException.getMessage());
	}

	@Test
	public void InvalidSearchException_MessageInjectedByThrow_TestFalse() {
		try {
			throw new InvalidSearchException("InvalidSearchException message");
		} catch (InvalidSearchException e) {
			assertNotEquals("Testing failure", e.getMessage());
		}
	}

	@Test
	public void InvalidSearchException_MessageInjectedBySuper_TestFalse() {
		RuntimeException invalidSearchException = new InvalidSearchException("InvalidSearchException message");
		assertNotEquals("Testing failure", invalidSearchException.getMessage());
	}
}
