package com.revature.utiltests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.util.TimeGraphData;

public class TimeGraphTest {
	
	//The data being Tested
	TimeGraphData t1;
	TimeGraphData t2;
	
	List<Long> al = new ArrayList<Long>();
	
	//initialize the data
	@BeforeTest
	void setup() {
		t1 = new TimeGraphData(al, 0);
		t2 = new TimeGraphData(al, 2);
	}
	
	//tear down the data
	@AfterTest
	void teardown() {
		t1 = null;
		t2 = null;
	}	
	
	//test the constructors
	@Test
	void testTimeGraphData() {
		TimeGraphData one = new TimeGraphData();
		assertTrue(one instanceof TimeGraphData);
		TimeGraphData two = new TimeGraphData();
		assertTrue(one != two);
		
	}

	@Test
	void testTimeGraphDataListOfLongInt() {
		TimeGraphData one = new TimeGraphData(new ArrayList<Long>(), 0);
		assertTrue(one instanceof TimeGraphData);
		TimeGraphData two = new TimeGraphData(new ArrayList<Long>(), 2);
		assertTrue(one != two);
	}

	//test the getters and setters
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

	//test the toString
	@Test
	void testToString() {
		assertTrue(t1.toString() instanceof String);
		assertTrue(t2.toString() instanceof String);
	}
}
