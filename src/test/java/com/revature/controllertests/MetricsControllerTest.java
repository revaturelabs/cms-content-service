package com.revature.controllertests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.controllers.MetricsController;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
//@ContextConfiguration(classes = com.revature.controllers.ModuleController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class MetricsControllerTest {
	
	// Object of MetricsController to provide methods we need to test
	private static MetricsController mc = new MetricsController();
	
	// Test if the methods throw exceptions
	// Count Codes
	@Test
	void noExceptionCountCodeEx() {
		Exception thrown = assertThrows(Exception.class, () -> mc.getCountCodeEx(), "Expected CountCode to throw but it didn't");
		assertTrue(thrown.getMessage().contains("Expected CountCode to throw but it didn't"));
	}
	
	// Count Documents
	@Test
	void noExceptionCountDocEx() {
		Exception thrown = assertThrows(Exception.class, () -> mc.getCountDocEx(), "Expected CountDoc to throw but it didn't");
		assertTrue(thrown.getMessage().contains("Expected CountDoc to throw but it didn't"));
	}
	
	// Count PowerPoints
	@Test
	void noExceptionCountPPEx() {
		Exception thrown = assertThrows(Exception.class, () -> mc.getCountPPEx(), "Expected CountPP to throw but it didn't");
		assertTrue(thrown.getMessage().contains("Expected CountPP to throw but it didn't"));
	}
	
	// Number of Different Modules
	@Test
	void noExceptionNumDiffMod() {
		Exception thrown = assertThrows(Exception.class, () -> mc.getNumDiffMod(), "Expected NumDiffMod to throw but it didn't");
		assertTrue(thrown.getMessage().contains("Expected NumDiffMod to throw but it didn't"));
	}
	
	// Average Num of Resources
	@Test
	void noExceptionAvgRec() {
		Exception thrown = assertThrows(Exception.class, () -> mc.getAvgRec(), "Expected AvgRec to throw but it didn't");
		assertTrue(thrown.getMessage().contains("Expected AvgRec to throw but it didn't"));
	}
	
	// Other Tests?
}
