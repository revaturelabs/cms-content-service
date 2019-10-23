package com.revature.util;

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

	@Test
	void testTimeGraphDataInstanceof() {
		TimeGraphData one = new TimeGraphData(new ArrayList<Long>(), 0);
		assertTrue(one instanceof TimeGraphData);
	}

	@Test
	void testTimeGraphDataNotEqual() {
		TimeGraphData one = new TimeGraphData(new ArrayList<Long>(), 0);
		TimeGraphData two = new TimeGraphData(new ArrayList<Long>(), 2);
		assertTrue(one != two);
	}

	@Test
	void testGetReturnedLongs() {
		assertTrue(t1.getReturnedLongs().equals(al));
	}

	@Test
	void testSetReturnedLongs() {
		List<Long> atmp = new ArrayList<Long>();
		atmp.add(1024l);
		t1.setReturnedLongs(atmp);
		assertTrue(t1.getReturnedLongs().equals(atmp));
	}

	@Test
	void testGetNumContents() {
		assertTrue(t2.getNumContents() == 2);
	}

	@Test
	void testSetNumContents() {
		t2.setNumContents(5);
		assertTrue(t2.getNumContents() == 5);
	}

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
