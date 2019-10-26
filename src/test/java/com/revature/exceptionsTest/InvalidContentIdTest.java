package com.revature.exceptionsTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

<<<<<<< HEAD:src/test/java/com/revature/exceptionsTest/InvalidContentIdTest.java
import com.revature.exceptions.InvalidContentId;

=======
/**
 * Class for testing {@link com.revature.exceptions.InvalidContentId InvalidContentId}
 *
 */
>>>>>>> cba4e573336b49e07cc69336f48150344250f355:src/test/java/com/revature/exceptions/InvalidContentIdTest.java
public class InvalidContentIdTest {

	/**
	 * Test for throwing an {@link com.revature.exceptions.InvalidContentId InvalidContentId} exception
	 */
	@Test
	public void testInvalidContentId_MessageInjectedByThrow() {
		try {
			throw new InvalidContentId("InvalidContentId message");
		} catch (InvalidContentId e) {
			assertEquals("InvalidContentId message", e.getMessage());
		}
	}

	/**
	 * Test for ensuring that an {@link com.revature.exceptions.InvalidContentId InvalidContentId} is a {@link java.lang.RuntimeException RuntimeException}
	 */
	@Test
	public void testInvalidContentId_MessageInjectedBySuper() {
		RuntimeException invalidContentId = new InvalidContentId("InvalidContentId message");
		assertEquals("InvalidContentId message", invalidContentId.getMessage());
	}
}
