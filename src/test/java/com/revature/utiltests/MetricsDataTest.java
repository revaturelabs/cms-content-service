package com.revature.utiltests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.util.MetricsData;
import com.revature.util.TimeGraphData;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class MetricsDataTest {
	
	//The components needed to test
	MetricsData md1;
	MetricsData md2;
	
	TimeGraphData t = new TimeGraphData();
	
	//initialize the components
	@BeforeTest
	void setup() {
		md1 = new MetricsData(0, 10, 15, 20, 50, t);
		md2 = new MetricsData(1, 11, 16, 21, 41, t);
	}
	
	//tear down the components
	@AfterTest
	void teardown() {
		md1 = null;
		md2 = null;
		
	}
	
	//Test the constructors
	@Test
	void testMetricsData() {
		MetricsData one = new MetricsData();
		assertTrue(one instanceof MetricsData);
		MetricsData two = new MetricsData();
		assertTrue(one != two);
	}

	@Test
	void testMetricsDataIntIntIntIntDoubleTimeGraphData() {
		MetricsData one = new MetricsData(0, 10, 15, 20, 50, new TimeGraphData());
		assertTrue(one instanceof MetricsData);
		MetricsData two = new MetricsData(1, 11, 16, 21, 41, new TimeGraphData());
		assertTrue(one != two);
	}

	//test the getters and setters
	@Test
	void testGetCodeCount() {
		assertTrue(md1.getCodeCount() == 0);
	}

	@Test
	void testSetCodeCount() {
		md1.setCodeCount(55);
		assertTrue(md1.getCodeCount() == 55);
	}

	@Test
	void testGetDocumentCount() {
		assertTrue(md2.getDocumentCount() == 11);
	}

	@Test
	void testSetDocumentCount() {
		md2.setDocumentCount(15);
		assertTrue(md2.getDocumentCount() == 15);
	}

	@Test
	void testGetPptCount() {
		assertTrue(md1.getPptCount() == 15);
	}

	@Test
	void testSetPptCount() {
		md1.setPptCount(45);
		assertTrue(md1.getPptCount() == 45);
	}

	@Test
	void testGetNumDiffModsCount() {
		assertTrue(md2.getNumDiffModsCount() == 21);
	}

	@Test
	void testSetNumDiffModsCount() {
		md2.setNumDiffModsCount(24);
		assertTrue(md2.getNumDiffModsCount() == 24);
	}

	@Test
	void testGetAvgResources() {
		assertTrue(md1.getAvgResources() == 50);
	}

	@Test
	void testSetAvgResources() {
		md1.setAvgResources(45.2);
		assertTrue(md1.getAvgResources() == 45.2);
	}

	@Test
	void testGetTimeGraphData() {
		assertTrue(md2.getTimeGraphData().equals(t));
	}

	@Test
	void testSetTimeGraphData() {
		TimeGraphData tmp = new TimeGraphData(new ArrayList<Long>(), 5);
		md2.setTimeGraphData(tmp);
		assertTrue(md2.getTimeGraphData().equals(tmp));
	}

	//test the toString
	@Test
	void testToString() {
		assertTrue(md1.toString() instanceof String);
		assertTrue(md2.toString() instanceof String);
	}

	//test the equals and hash
	@Test
	void testEqualsObject() {
		EqualsVerifier.forClass(MetricsData.class)
		.suppress(Warning.NONFINAL_FIELDS)
		.verify();
	}
}
