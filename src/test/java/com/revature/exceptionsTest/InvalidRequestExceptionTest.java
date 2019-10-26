package com.revature.exceptionsTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

<<<<<<< HEAD:src/test/java/com/revature/exceptionsTest/InvalidRequestExceptionTest.java
import com.revature.exceptions.InvalidRequestException;

=======
/**
 * Class for testing {@link com.revature.exceptions.InvalidRequestException InvalidRequestException}
 */
>>>>>>> cba4e573336b49e07cc69336f48150344250f355:src/test/java/com/revature/exceptions/InvalidRequestExceptionTest.java
public class InvalidRequestExceptionTest {

	/**
	 * Test for throwing an {@link com.revature.exceptions.InvalidRequestException InvalidRequestException}
	 */
	@Test
	public void InvalidRequestExceptionMessageInjectedByThrowTestTrue() {
		try {
			throw new InvalidRequestException("InvalidRequestException message");
		} catch (InvalidRequestException e) {
			assertEquals("InvalidRequestException message", e.getMessage());
		}
	}

	/**
	 * Test for ensuring that an {@link com.revature.exceptions.InvalidRequestException InvalidRequestException} is a {@link java.lang.RuntimeException RuntimeException}
	 */
	@Test
	public void InvalidRequestExceptionMessageInjectedBySuperTestTrue() {
		RuntimeException invalidRequestException = new InvalidRequestException("InvalidRequestException message");
		assertEquals("InvalidRequestException message", invalidRequestException.getMessage());
	}
}
