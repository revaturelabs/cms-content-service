package com.revature.exceptions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

public class InvalidModuleExceptionTest {

	@Test
	public void InvalidModuleExceptionMessageInjectedByThrowTestTrue() {
		try {
			throw new InvalidModuleException("InvalidModuleException message");
		} catch (InvalidModuleException e) {
			assertEquals("InvalidModuleException message", e.getMessage());
		}
	}

	@Test
	public void InvalidModuleExceptionMessageInjectedBySuperTestTrue() {
		RuntimeException invalidModuleException = new InvalidModuleException("InvalidModuleException message");
		assertEquals("InvalidModuleException message", invalidModuleException.getMessage());
	}

	@Test
	public void InvalidModuleExceptionMessageInjectedByThrowTestFalse() {
		try {
			throw new InvalidModuleException("InvalidModuleException message");
		} catch (InvalidModuleException e) {
			assertNotEquals("Testing failure", e.getMessage());
		}
	}

	@Test
	public void InvalidModuleExceptionMessageInjectedBySuperTestFalse() {
		RuntimeException invalidModuleException = new InvalidModuleException("InvalidModuleException message");
		assertNotEquals("Testing failure", invalidModuleException.getMessage());
	}
}
