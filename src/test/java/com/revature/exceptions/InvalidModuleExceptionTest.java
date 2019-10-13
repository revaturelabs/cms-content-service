package com.revature.exceptions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

public class InvalidModuleExceptionTest {

	@Test
	public void InvalidModuleException_MessageInjectedByThrow_TestTrue() {
		try {
			throw new InvalidModuleException("InvalidModuleException message");
		} catch (InvalidModuleException e) {
			assertEquals("InvalidModuleException message", e.getMessage());
		}
	}

	@Test
	public void InvalidModuleException_MessageInjectedBySuper_TestTrue() {
		RuntimeException invalidModuleException = new InvalidModuleException("InvalidModuleException message");
		assertEquals("InvalidModuleException message", invalidModuleException.getMessage());
	}

	@Test
	public void InvalidModuleException_MessageInjectedByThrow_TestFalse() {
		try {
			throw new InvalidModuleException("InvalidModuleException message");
		} catch (InvalidModuleException e) {
			assertNotEquals("Testing failure", e.getMessage());
		}
	}

	@Test
	public void InvalidModuleException_MessageInjectedBySuper_TestFalse() {
		RuntimeException invalidModuleException = new InvalidModuleException("InvalidModuleException message");
		assertNotEquals("Testing failure", invalidModuleException.getMessage());
	}
}
