package com.revature.exceptions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

public class InvalidContentIdTest {

	@Test
	public void InvalidContentId_MessageInjectedByThrow_TestTrue() {
		try {
			throw new InvalidContentId("InvalidContentId message");
		} catch (InvalidContentId e) {
			assertEquals("InvalidContentId message", e.getMessage());
		}
	}

	@Test
	public void InvalidContentId_MessageInjectedBySuper_TestTrue() {
		RuntimeException invalidContentId = new InvalidContentId("InvalidContentId message");
		assertEquals("InvalidContentId message", invalidContentId.getMessage());
	}

	@Test
	public void InvalidContentId_MessageInjectedByThrow_TestFalse() {
		try {
			throw new InvalidContentId("InvalidContentId message");
		} catch (InvalidContentId e) {
			assertNotEquals("Testing failure", e.getMessage());
		}
	}

	@Test
	public void InvalidContentId_MessageInjectedBySuper_TestFalse() {
		RuntimeException invalidContentId = new InvalidContentId("InvalidContentId message");
		assertNotEquals("Testing failure", invalidContentId.getMessage());
	}
}
