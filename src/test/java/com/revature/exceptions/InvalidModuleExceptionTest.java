package com.revature.exceptions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

/**
 * Class for testing {@link com.revature.exceptions.InvalidModuleException InvalidModuleException}
 *
 */
public class InvalidModuleExceptionTest {

	/**
	 * Test for throwing an {@link com.revature.exceptions.InvalidModuleException InvalidModuleException}
	 */
	@Test
	public void testInvalidModuleException_MessageInjectedByThrow() {
		try {
			throw new InvalidModuleException("InvalidModuleException message");
		} catch (InvalidModuleException e) {
			assertEquals("InvalidModuleException message", e.getMessage());
		}
	}

	/**
	 * Test for ensuring that an {@link com.revature.exceptions.InvalidModuleException InvalidModuleException} is a {@link java.lang.RuntimeException RuntimeException}
	 */
	@Test
	public void testInvalidModuleException_MessageInjectedBySuper() {
		RuntimeException invalidModuleException = new InvalidModuleException("InvalidModuleException message");
		assertEquals("InvalidModuleException message", invalidModuleException.getMessage());
	}
}
