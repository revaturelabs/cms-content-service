package com.revature.exceptionsTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

<<<<<<< HEAD:src/test/java/com/revature/exceptionsTest/InvalidSearchExceptionTest.java
import com.revature.exceptions.InvalidSearchException;

=======
/**
 * Class for testing {@link com.revature.exceptions.InvalidSearchException InvalidSearchException}
 *
 */
>>>>>>> cba4e573336b49e07cc69336f48150344250f355:src/test/java/com/revature/exceptions/InvalidSearchExceptionTest.java
public class InvalidSearchExceptionTest {

	/**
	 * Test for throwing an {@link com.revature.exceptions.InvalidSearchException InvalidSearchException}
	 */
	@Test
	public void InvalidSearchExceptionMessageInjectedByThrowTestTrue() {
		try {
			throw new InvalidSearchException("InvalidSearchException message");
		} catch (InvalidSearchException e) {
			assertEquals("InvalidSearchException message", e.getMessage());
		}
	}

	/**
	 * Test for ensuring that an {@link com.revature.exceptions.InvalidSearchException InvalidSearchException} is a {@link java.lang.RuntimeException RuntimeException}
	 */
	@Test
	public void InvalidSearchExceptionMessageInjectedBySuperTestTrue() {
		RuntimeException invalidSearchException = new InvalidSearchException("InvalidSearchException message");
		assertEquals("InvalidSearchException message", invalidSearchException.getMessage());
	}
}
