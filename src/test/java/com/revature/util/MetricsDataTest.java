package com.revature.util;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.util.MetricsData;
import com.revature.util.TimeGraphData;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class MetricsDataTest {
	
	MetricsData md1;
	MetricsData md2;
	
	TimeGraphData t = new TimeGraphData();
	
	@BeforeTest
	void setup() {
		md1 = new MetricsData(0, 10, 15, 20, 50, t);
		md2 = new MetricsData(1, 11, 16, 21, 41, t);
	}
	
	@AfterTest
	void teardown() {
		md1 = null;
		md2 = null;
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.MetricsData#MetricsData(int codeCount, int documentCount, int pptCount, int numDiffModsCount, double avgResources,
			TimeGraphData timeGraphData) 
	 * MetricsData(int codeCount, int documentCount, int pptCount, int numDiffModsCount, double avgResources,
			TimeGraphData timeGraphData)}.
	 * This method assumes that parameters are passed into the constructor.
	 * The result expected is that an instance of MetricsData is created from the constructor.
	 */    
	@Test
	void testMetricsDataInstanceof() {
		MetricsData one = new MetricsData(0, 10, 15, 20, 50, new TimeGraphData());
		assertTrue(one instanceof MetricsData);
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.MetricsData#MetricsData(int codeCount, int documentCount, int pptCount, int numDiffModsCount, double avgResources,
			TimeGraphData timeGraphData) 
	 * MetricsData(int codeCount, int documentCount, int pptCount, int numDiffModsCount, double avgResources,
			TimeGraphData timeGraphData)}.
	 * This method assumes that 2 MetricsData objects are created.
	 * The result expected is the two objects are not equal to each other.
	 */    
	@Test
	void testMetricsDataNotEqual() {
		MetricsData one = new MetricsData(0, 10, 15, 20, 50, new TimeGraphData());
		MetricsData two = new MetricsData(1, 11, 16, 21, 41, new TimeGraphData());
		assertTrue(one != two);
	}
	
	/**
	 * This method tests
	 * {@link com.revature.util.MetricsData#getCodeCount() 
	 * getCodeCount()}.
	 * This method assumes that a field code count exists for the MetricsData object.
	 * The result expected is the code count of the MetricsData object md1 is 0.
	 */    
	@Test
	void testGetCodeCount() {
		assertTrue(md1.getCodeCount() == 0);
	}

	/**
	 * This method tests
	 * {@link com.revature.util.MetricsData#setCodeCount() 
	 * setCodeCount()}.
	 * This method assumes that a field code count exists for the MetricsData object.
	 * The result expected is the code count of the MetricsData object md1 gets set to 55.
	 */    
	@Test
	void testSetCodeCount() {
		md1.setCodeCount(55);
		assertTrue(md1.getCodeCount() == 55);
	}

	/** GETTERS AND SETTERS FOR METRICS DATA OBJECT
	 * This method tests
	 * {@link com.revature.util.MetricsData#getDocumentCount() 
	 * getDocumentCount()}.
	 * This method assumes that a field documentcount exists for the MetricsData object.
	 * The result expected is the documentcount of the MetricsData object md2 is 11.
	 */    
	@Test
	void testGetDocumentCount() {
		assertTrue(md2.getDocumentCount() == 11);
	}

	/**
	 * This method tests
	 * {@link com.revature.util.MetricsData#setDocumentCount(int documentCount) 
	 * setDocumentCount(int documentCount)}.
	 * This method assumes that a field documentcount exists for the MetricsData object.
	 * The result expected is the documentcount of the MetricsData object md2 gets set to 15.
	 */    
	@Test
	void testSetDocumentCount() {
		md2.setDocumentCount(15);
		assertTrue(md2.getDocumentCount() == 15);
	}

	/** 
	 * This method tests
	 * {@link com.revature.util.MetricsData#getPptCount() 
	 * getPptCount()}.
	 * This method assumes that a field pptcount exists for the MetricsData object.
	 * The result expected is the pptcount of the MetricsData object md1 is 15.
	 */   
	@Test
	void testGetPptCount() {
		assertTrue(md1.getPptCount() == 15);
	}

	/**
	 * This method tests
	 * {@link com.revature.util.MetricsData#setDocumentCount(int pptCount) 
	 * setDocumentCount(int pptCount)}.
	 * This method assumes that a field pptcount exists for the MetricsData object.
	 * The result expected is the pptcount of the MetricsData object md1 gets set to 45.
	 */    
	@Test
	void testSetPptCount() {
		md1.setPptCount(45);
		assertTrue(md1.getPptCount() == 45);
	}

	/** 
	 * This method tests
	 * {@link com.revature.util.MetricsData#getnumDiffModsCount() 
	 * numDiffModsCount()}.
	 * This method assumes that a field numDiffmodscount exists for the MetricsData object.
	 * The result expected is the numdiffmodscount of the MetricsData object md2 is 21.
	 */   
	@Test
	void testGetNumDiffModsCount() {
		assertTrue(md2.getNumDiffModsCount() == 21);
	}

	/**
	 * This method tests
	 * {@link com.revature.util.MetricsData#setNumDiffModsCount(int numDiffModsCount) 
	 * setNumDiffModsCount(int numDiffModsCount)}.
	 * This method assumes that a field numDiffModsCount exists for the MetricsData object.
	 * The result expected is the numDiffModsCount of the MetricsData object md2 gets set to 24.
	 */    
	@Test
	void testSetNumDiffModsCount() {
		md2.setNumDiffModsCount(24);
		assertTrue(md2.getNumDiffModsCount() == 24);
	}

	/** 
	 * This method tests
	 * {@link com.revature.util.MetricsData#getAvgResources() 
	 * getAvgResources()}.
	 * This method assumes that a field AvgResources exists for the MetricsData object.
	 * The result expected is the AvgResources of the MetricsData object md1 is 50.
	 */   
	@Test
	void testGetAvgResources() {
		assertTrue(md1.getAvgResources() == 50);
	}

	/**
	 * This method tests
	 * {@link com.revature.util.MetricsData#setAvgResources(int avgResources) 
	 * setAvgResources(int avgResources)}.
	 * This method assumes that a field AvgResources exists for the MetricsData object.
	 * The result expected is the AvgResources of the MetricsData object md1 gets set to 45.2.
	 */    
	@Test
	void testSetAvgResources() {
		md1.setAvgResources(45.2);
		assertTrue(md1.getAvgResources() == 45.2);
	}

	/** 
	 * This method tests
	 * {@link com.revature.util.MetricsData#getTimeGraphData() 
	 * getTimeGraphData()}.
	 * This method assumes that a field TimeGraphData exists for the MetricsData object.
	 * The result expected is the TimeGraphData of the MetricsData object md1 equivalent to t.
	 */   
	@Test
	void testGetTimeGraphData() {
		assertTrue(md2.getTimeGraphData().equals(t));
	}

	/**
	 * This method tests
	 * {@link com.revature.util.MetricsData#setTimeGraphData(TimeGraphData timeGraphData) 
	 * setTimeGraphData(TimeGraphData timeGraphData)}.
	 * This method assumes that a field TimeGraphData exists for the MetricsData object.
	 * The result expected is the TimeGraphData of the MetricsData object md2 gets set to the object tmp.
	 */ 
	@Test
	void testSetTimeGraphData() {
		TimeGraphData tmp = new TimeGraphData(new ArrayList<Long>(), 5);
		md2.setTimeGraphData(tmp);
		assertTrue(md2.getTimeGraphData().equals(tmp));
	}

	/**
	 * This method tests
	 * {@link com.revature.util.MetricsData#toString() 
	 * toString()}.
	 * This method assumes that a MetricsData object exists.
	 * The result expected is the stringified version of the MetricsData Object.
	 */    
	@Test
	void testToString() {
		assertTrue(md1.toString() instanceof String);
	}

	/**
	 * This method tests
	 * {@link com.revature.util.MetricsData#equals() 
	 * equals()}.
	 * This method assumes that a MetricsData object exists.
	 * The result expected is the conditions provided by the equals and hashcode method are verified.
	 */    
	@Test
	void testEqualsObject() {
		EqualsVerifier.forClass(MetricsData.class)
		.suppress(Warning.NONFINAL_FIELDS)
		.verify();
	}
}
