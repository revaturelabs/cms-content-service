package com.revature.cmsforcetests;

import org.testng.annotations.Test;

import com.revature.cmsforce.CMSforceApplication;

public class CMSforceApplicationTest {

	// Test class added ONLY to cover main() invocation not covered by application tests.
	@Test
	public void mainTest() {
		CMSforceApplication.main(new String[] {});
	}
}
