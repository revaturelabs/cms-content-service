package com.revature.controllertests;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import com.revature.controllers.MetricsController;
import com.revature.util.MetricsData;


/**
 * 
 * This test class fails to test the functionality of the controller,
 * not to mention the controller is nearly untestable at this point.
 * These tests should be deprecated and replaced when the controller
 * is fixed.
 *
 */


@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MetricsControllerTest {
	
	public MetricsControllerTest()
	{
		super();
	}

	@MockBean
	MetricsController mc;
	
	MetricsData md;
	
	/*
	 * Testing null values for each method in getMetrics 
	 */
	
	/**
	 * Testing null values for code count
	 */
	@Test
	public void nullIdsCodeCount() {
		Map<String, Object> nullVals = new HashMap<String, Object>();
		nullVals.put(null, null);		
	}
	/**
	 * Testing null values for avgResources
	 */
	@Test
	public void nullIdsAvgResources() {
		Map<String, Object> nullVals = new HashMap<String, Object>();
		nullVals.put(null, null);
		
	}
	
	/**
	 * Testing null values for document count
	 */
	@Test
	public void nullIdsDocumentCount() {
		Map<String, Object> nullVals = new HashMap<String, Object>();
		nullVals.put(null, null);
		
	}
	
	/**
	 * Testing null values for mods count
	 */
	@Test
	public void nullIdsModsCount() {
		Map<String, Object> nullVals = new HashMap<String, Object>();
		nullVals.put(null, null);
	}
	
	/**
	 * Testing null values for pptCount
	 */
	@Test
	public void nullIdsPptCount() {
		Map<String, Object> nullVals = new HashMap<String, Object>();
		nullVals.put(null, null);
	}
	
	/**
	 * Testing null values for time graph
	 */
	@Test
	public void nullIdsTimeGraph() {
		Map<String, Object> nullVals = new HashMap<String, Object>();
		nullVals.put(null, null);
	}
	
	/**
	 * Testing zeros for returning data
	 */
	@Test
	public void testingReturnData() {
		Map<String, Object> test = new HashMap<>();
		test.put("modules", new ArrayList<Integer>(0));
		md = mc.getMetrics(0, test);
		System.out.println(md);

	}
	
	/**
	 * Testing zeros avgResources
	 */
	@Test
	public void TestAvgResourcesWithZero() {
		Map<String, Object> test = new HashMap<>();
		test.put("modules", new ArrayList<Integer>(0));

	}
	
	/**
	 * Testing zeros DocumentCount
	 */
	@Test
	public void TestDocumentCountWithZero() {
		Map<String, Object> test = new HashMap<>();
		test.put("modules", new ArrayList<Integer>(0));

	}
	
	/**
	 * Testing zeros diffNums
	 */
	@Test
	public void TestModsCountWithZero() {
		Map<String, Object> test = new HashMap<>();
		test.put("modules", new ArrayList<Integer>(0));

	}
	
	/**
	 * Testing zeros PptCount
	 */
	@Test
	public void TestPptCountWithZero() {
		Map<String, Object> test = new HashMap<>();
		test.put("modules", new ArrayList<Integer>(0));

	}
	
	/**
	 * Testing zeros Time Graph
	 */
	@Test
	public void TestTimeGraphWithZero() {
		Map<String, Object> test = new HashMap<>();
		test.put("modules", new ArrayList<Integer>(0));

	}

	/**
	 * Testing zeros return data for formatting
	 */
	@Test
		public void testingReturnDataFormat() {
			Map<String, Object> test = new HashMap<>();
			test.put("format", "All");
			md = mc.getMetrics(0, test);
			System.out.println(md);
		
		}
	
	/**
	 * Testing zeros Time Graph for formatting
	 */
	@Test
	public void TestTimeGraphWithZeroFormat() {
		Map<String, Object> test = new HashMap<>();
		test.put("format", "All");

	}
	
	/**
	 * Testing zeros avgResource for formatting
	 */
	@Test
	public void TestAvgResourceWithZeroFormat() {
		Map<String, Object> test = new HashMap<>();
		test.put("format", "All");

	}
	
	/**
	 * Testing zeros Code Count for formatting
	 */
	@Test
	public void TestCodeCountWithZeroFormat() {
		Map<String, Object> test = new HashMap<>();
		test.put("format", "All");

	}
	
	/**
	 * Testing zeros Document Count for formatting
	 */
	@Test
	public void TestDocumentCountZeroFormat() {
		Map<String, Object> test = new HashMap<>();
		test.put("format", "All");
	
	}
	
	/**
	 * Testing zeros number of mod for formatting
	 */
	@Test
	public void TestNumModsWithZeroFormat() {
		Map<String, Object> test = new HashMap<>();
		test.put("format", "All");
	
	}
	
	/**
	 * Testing zeros pptCount for formatting
	 */
	@Test
	public void TestPptCountWithZeroFormat() {
		Map<String, Object> test = new HashMap<>();
		test.put("format", "All");

	}


}
