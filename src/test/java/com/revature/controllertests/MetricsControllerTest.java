package com.revature.controllertests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
		assertThrows(NullPointerException.class, () -> {
		mc.getMetrics(0, nullVals).getCodeCount();
	});
		
	}
	/**
	 * Testing null values for avgResources
	 */
	@Test
	public void nullIdsAvgResources() {
		Map<String, Object> nullVals = new HashMap<String, Object>();
		nullVals.put(null, null);
		assertThrows(NullPointerException.class, () -> {
		mc.getMetrics(0, nullVals).getAvgResources();
	});
		
	}
	
	/**
	 * Testing null values for document count
	 */
	@Test
	public void nullIdsDocumentCount() {
		Map<String, Object> nullVals = new HashMap<String, Object>();
		nullVals.put(null, null);
		assertThrows(NullPointerException.class, () -> {
		mc.getMetrics(0, nullVals).getDocumentCount();
	});
		
	}
	
	/**
	 * Testing null values for mods count
	 */
	@Test
	public void nullIdsModsCount() {
		Map<String, Object> nullVals = new HashMap<String, Object>();
		nullVals.put(null, null);
		assertThrows(NullPointerException.class, () -> {
		mc.getMetrics(0, nullVals).getNumDiffModsCount();
	});
	}
	
	/**
	 * Testing null values for pptCount
	 */
	@Test
	public void nullIdsPptCount() {
		Map<String, Object> nullVals = new HashMap<String, Object>();
		nullVals.put(null, null);
		assertThrows(NullPointerException.class, () -> {
		mc.getMetrics(0, nullVals).getPptCount();
	});
	}
	
	/**
	 * Testing null values for time graph
	 */
	@Test
	public void nullIdsTimeGraph() {
		Map<String, Object> nullVals = new HashMap<String, Object>();
		nullVals.put(null, null);
		assertThrows(NullPointerException.class, () -> {
		mc.getMetrics(0, nullVals).getTimeGraphData();
	});
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
		assertEquals(null, md);
	}
	
	/**
	 * Testing zeros avgResources
	 */
	@Test
	public void TestAvgResourcesWithZero() {
		Map<String, Object> test = new HashMap<>();
		test.put("modules", new ArrayList<Integer>(0));
		assertThrows(NullPointerException.class, () -> { mc.getMetrics(0, test).getAvgResources();});
	}
	
	/**
	 * Testing zeros DocumentCount
	 */
	@Test
	public void TestDocumentCountWithZero() {
		Map<String, Object> test = new HashMap<>();
		test.put("modules", new ArrayList<Integer>(0));
		assertThrows(NullPointerException.class, () -> { mc.getMetrics(0, test).getDocumentCount();});
	}
	
	/**
	 * Testing zeros diffNums
	 */
	@Test
	public void TestModsCountWithZero() {
		Map<String, Object> test = new HashMap<>();
		test.put("modules", new ArrayList<Integer>(0));
		assertThrows(NullPointerException.class, () -> { mc.getMetrics(0, test).getNumDiffModsCount();});
	}
	
	/**
	 * Testing zeros PptCount
	 */
	@Test
	public void TestPptCountWithZero() {
		Map<String, Object> test = new HashMap<>();
		test.put("modules", new ArrayList<Integer>(0));
		assertThrows(NullPointerException.class, () -> { mc.getMetrics(0, test).getPptCount();});
	}
	
	/**
	 * Testing zeros Time Graph
	 */
	@Test
	public void TestTimeGraphWithZero() {
		Map<String, Object> test = new HashMap<>();
		test.put("modules", new ArrayList<Integer>(0));
		assertThrows(NullPointerException.class, () -> { mc.getMetrics(0, test).getTimeGraphData();});
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
			assertEquals(null, md);
		}
	
	/**
	 * Testing zeros Time Graph for formatting
	 */
	@Test
	public void TestTimeGraphWithZeroFormat() {
		Map<String, Object> test = new HashMap<>();
		test.put("format", "All");
		assertThrows(NullPointerException.class, () -> { mc.getMetrics(0, test).getTimeGraphData();});
	}
	
	/**
	 * Testing zeros avgResource for formatting
	 */
	@Test
	public void TestAvgResourceWithZeroFormat() {
		Map<String, Object> test = new HashMap<>();
		test.put("format", "All");
		assertThrows(NullPointerException.class, () -> { mc.getMetrics(0, test).getAvgResources();});
	}
	
	/**
	 * Testing zeros Code Count for formatting
	 */
	@Test
	public void TestCodeCountWithZeroFormat() {
		Map<String, Object> test = new HashMap<>();
		test.put("format", "All");
		assertThrows(NullPointerException.class, () -> { mc.getMetrics(0, test).getCodeCount();});
	}
	
	/**
	 * Testing zeros Document Count for formatting
	 */
	@Test
	public void TestDocumentCountZeroFormat() {
		Map<String, Object> test = new HashMap<>();
		test.put("format", "All");
		assertThrows(NullPointerException.class, () -> { mc.getMetrics(0, test).getDocumentCount();});
	}
	
	/**
	 * Testing zeros number of mod for formatting
	 */
	@Test
	public void TestNumModsWithZeroFormat() {
		Map<String, Object> test = new HashMap<>();
		test.put("format", "All");
		assertThrows(NullPointerException.class, () -> { mc.getMetrics(0, test).getNumDiffModsCount();});
	}
	
	/**
	 * Testing zeros pptCount for formatting
	 */
	@Test
	public void TestPptCountWithZeroFormat() {
		Map<String, Object> test = new HashMap<>();
		test.put("format", "All");
		assertThrows(NullPointerException.class, () -> { mc.getMetrics(0, test).getPptCount();});
	}
	
	
	
	
	
	
	
	

}
