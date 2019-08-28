package com.revature.servicestests;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.cmsforce.CMSforceApplication;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.repositories.ContentRepository;
import com.revature.services.TimegraphServiceImpl;

//the springboottest and the extends are needed to use spring in TestNG
@SpringBootTest(classes = CMSforceApplication.class)
public class TimeGraphServiceTest extends AbstractTestNGSpringContextTests {
	
	//Mock the function under test with the components needed
	@InjectMocks
	TimegraphServiceImpl ts;
	@Mock
	ContentRepository cr;
	
	//these are needed to run the test
	Content c1 = null;
	Content c2 = null;
	Content c3 = null;
	Content c4 = null;
	
	//this is just to keep everything as consistent as possible
	Long sysTime = System.currentTimeMillis();
	
	ArrayList<Content> all = null;
	Set<Content> setAll = null;
	
	/** ONE_MONTH - {@value}, Represents 1 month in milliseconds. */
	long ONE_MONTH = 2592000000L;
	
	/** SIX_MONTHS - {@value}, Represents 6 months in milliseconds. */
	long SIX_MONTHS = 15552000000L;
	
	/** ONE_YEAR - {@value}, Represents 1 year in milliseconds. */
	long ONE_YEAR = 31536000000L;
	
	//need to initialize some of the components manually because testNG doesn't
	//by default
	@BeforeClass
	public void setup() {
		ts = new TimegraphServiceImpl();
		
		//enables mockito annotations
		MockitoAnnotations.initMocks(this);
	}
	
	//Initialize the components
	@BeforeTest
	public void preTestSetup() {
		c1 = new Content(99, "Java a New Begining", "String", "The Java the brought hope back", "https://en.wikipedia.org/wiki/Star_Wars_(film)",
				new HashSet<Link>(), (sysTime - ONE_MONTH), (sysTime - ONE_MONTH));
		c2 = new Content(114, "Java the phantom menance", "String", "The one with the cool darth", "https://en.wikipedia.org/wiki/Star_Wars_(film)",
				new HashSet<Link>(), (sysTime - (ONE_MONTH * 5)), (sysTime - ONE_MONTH));
		c3 = new Content(114, "Java the phantom menance", "String", "The one with the cool darth", "https://en.wikipedia.org/wiki/Star_Wars_(film)",
				new HashSet<Link>(), (sysTime - ONE_YEAR), (sysTime - ONE_MONTH));
		c4 = new Content(99, "Java a New Begining", "String", "The Java the brought hope back", "https://en.wikipedia.org/wiki/Star_Wars_(film)",
				new HashSet<Link>(), sysTime, sysTime);
		all = new ArrayList<Content>();
		all.add(c1);
		all.add(c2);
		all.add(c3);
		all.add(c4);
		
		setAll = new HashSet<Content>();
		setAll.add(c1);
		setAll.add(c2);
		setAll.add(c3);
		setAll.add(c4);
	}
	
	//tear down the components
	@AfterTest
	public void teardown() {
		c1 = null;
		c2 = null;
		c3 = null;
		c4 = null;
		all = null;
		setAll = null;
	}
	
	//this will grab the data from the database (which we will mock) then separate it based on a
	//certain amount of time
	@Test
	public void timeScaleInMarginOneVal()
	{
		//Mocking so that we are just testing this function
		Mockito.when(cr.findAll()).thenReturn(all);
		//variable needed to run the test
		long fivemonths = ONE_MONTH * 5;
		long systimeAdj5mo = sysTime - fivemonths;

		List<Long> resultSet = ts.findByCreatedBetween(SIX_MONTHS).getReturnedLongs();

		assertTrue(resultSet.contains(systimeAdj5mo));
		assertFalse(resultSet.contains((sysTime - ONE_YEAR)));
	}
	
	//this test does the same function as the previous test but with a known set of variables
	//so there is no call to the database
	@Test
	public void testGetTimeGraphData() {
		//variable needed to run the test
		long fivemonths = ONE_MONTH * 5;
		long systimeAdj5mo = sysTime - fivemonths;
		
		List<Long> resultSet = ts.getTimeGraphData(SIX_MONTHS, setAll).getReturnedLongs();

		assertTrue(resultSet.contains(systimeAdj5mo));
		assertFalse(resultSet.contains((sysTime - ONE_YEAR)));
		
	}
}
