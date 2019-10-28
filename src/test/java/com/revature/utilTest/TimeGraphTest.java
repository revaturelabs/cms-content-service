package com.revature.utilTest;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.util.TimeGraphData;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class TimeGraphTest {

	TimeGraphData t1;
	TimeGraphData t2;

	List<Long> al = new ArrayList<Long>();

	@BeforeTest
	void setup() {
		t1 = new TimeGraphData(al, 0);
		t2 = new TimeGraphData(al, 2);
	}

	@AfterTest
	void teardown() {
		t1 = null;
		t2 = null;
	}

	/**
	 * This method tests
	 * {@link com.revature.util.TimegraphData#TimeGraphData() 
	 * TimeGraphData()}.
	 * This method assumes that a TimeGraphData Object exists.
	 * The result expected is that the newly instantiated object is a member of the TimeGraphData class.
	 */
	@Test
	void testTimeGraphDataInstanceof() {
		TimeGraphData one = new TimeGraphData(new ArrayList<Long>(), 0);
		assertTrue(one instanceof TimeGraphData);
	}

	/**
	 * This method tests
	 * {@link com.revature.util.TimegraphData#TimeGraphData() 
	 * TimeGraphData()}.
	 * This method assumes that 2 TimeGraphData Objects exist.
	 * The result expected is that the newly instantiated objects are different from each other.
	 */
	@Test
	void testTimeGraphDataNotEqual() {
		TimeGraphData one = new TimeGraphData(new ArrayList<Long>(), 0);
		TimeGraphData two = new TimeGraphData(new ArrayList<Long>(), 2);
		assertTrue(one != two);
	}

	/** GETTERS AND SETTERS FOR TIMEGRAPH DATA OBJECT
	 * This method tests
	 * {@link com.revature.util.TimeGraphData#getReturnedLongs() 
	 * getReturnedLongs()}.
	 * This method assumes that a TimeGraphData object t1 contains the array of longs al.
	 * The result expected is that the array of longs returned from the object and al are the same.
	 */    
	@Test
	void testGetReturnedLongs() {
		assertTrue(t1.getReturnedLongs().equals(al));
	}

	/**This method tests
	 * {@link com.revature.util.TimeGraphData#setReturnedLongs() 
	 * setReturnedLongs()}.
	 * This method assumes that a TimeGraphData object t1 receives a new array of longs atmp.
	 * The result expected is that t1 returns the array of longs atmp.
	 */    
	@Test
	void testSetReturnedLongs() {
		List<Long> atmp = new ArrayList<Long>();
		atmp.add(1024l);
		t1.setReturnedLongs(atmp);
		assertTrue(t1.getReturnedLongs().equals(atmp));
	}

	/**This method tests
	 * {@link com.revature.util.TimeGraphData#getNumContents(List<Long> returnedDates) 
	 * getNumContents(List<Long> returnedDates)}.
	 * This method assumes that a TimeGraphData object t2 contains the integer numContents.
	 * The result expected is that numContents returns 2.
	 */   
	@Test
	void testGetNumContents() {
		assertTrue(t2.getNumContents() == 2);
	}

	/**This method tests
	 * {@link com.revature.util.TimeGraphData#setNumContents(int numContents) 
	 * setNumContents(int numContents)}.
	 * This method assumes that a the integer numContents in the object t2 gets set to 5.
	 * The result expected is that t2.numContents = 5.
	 */    
	@Test
	void testSetNumContents() {
		t2.setNumContents(5);
		assertTrue(t2.getNumContents() == 5);
	}

	/**
	 * This method tests
	 * {@link com.revature.util.TimeGraphData#toString() 
	 * toString()}.
	 * This method assumes that a MetricsData object exists.
	 * The result expected is the stringified version of the MetricsData Object.
	 */    
	@Test
	void testToString() {
		assertTrue(t1.toString() instanceof String);
	}

	/**
	 * EqualsVerifier will throw an AssertionError if there are any issues with its
	 * utilization. The suppression for non final fields is for the error
	 * "Mutability: equals depends on mutable field".
	 */
	@Test
	public void equalsTest() {
		EqualsVerifier.forClass(TimeGraphData.class).usingGetClass().suppress(Warning.NONFINAL_FIELDS).verify();
	}
}
