package com.revature.servicestests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.services.ContentService;
import com.revature.services.TimegraphService;

/**
 * Tests for the Time Graph Service.
 * 
 * <p> Contains the tests that cover the breadth and depth of the TimeGraphService.
 * 
 * @author wsm
 * @version 2.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@SpringBootTest
public class TimeGraphServiceTest {
	
	/** Value - {@value}, Represents 1 month in milliseconds. */
	long ONE_MONTH = 2592000000L;
	
	/** Value - {@value}, Represents 6 months in milliseconds. */
	long SIX_MONTHS = 15552000000L;
	
	/** Value - {@value}, Represents 1 year in milliseconds. */
	long ONE_YEAR = 31536000000L;
	
	@Autowired
	TimegraphService ts;
	
	@Autowired
	ContentService cs;
	
	@Autowired
	JdbcTemplate template;
	
	/**
	 * Overarching test for the timescale handling.
	 * 
	 * <p> To achieve coverage, it creates two new content values,
	 * one before and one after 6 months ago, and stores it for checking
	 * if the content provided back fits in the timescale correctly.
	 * Afterwards, it deletes the content, and tests the assertions.
	 */
	@Test
	@Commit
	void timeScaleTests()
	{
		long fivemonths = ONE_MONTH * 5;
		long systimeAdj5mo = System.currentTimeMillis() - fivemonths;
		cs.createContent(new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION",
				"http://TESTURL.COM", new HashSet<Link>(), systimeAdj5mo, systimeAdj5mo));

//		int resultSetSize = ts.findByCreatedBetween(SIX_MONTHS).getReturnedLongs().size();
		Set<Long> resultSet = ts.findByCreatedBetween(SIX_MONTHS).getReturnedLongs();

		long sevenmonths = ONE_MONTH + SIX_MONTHS;
		long systimeAdj7mo = System.currentTimeMillis() - sevenmonths;
		cs.createContent(new Content(0, "OLD TEST CONTENT", "Code", "OLD TEST CONTENT DESCRIPTION",
				"http://OLDTESTURL.COM", new HashSet<Link>(), systimeAdj7mo, systimeAdj7mo));
		
//		int resultSetSize2 = ts.findByCreatedBetween(SIX_MONTHS).getReturnedLongs().size();
		Set<Long> resultSet2 = ts.findByCreatedBetween(SIX_MONTHS).getReturnedLongs();

		JdbcTestUtils.deleteFromTableWhere(template, "content", String.format("title = '%s'", "FIRST TEST CONTENT"));

		JdbcTestUtils.deleteFromTableWhere(template, "content", String.format("title = '%s'", "OLD TEST CONTENT"));
		
		assertTrue(resultSet.contains(systimeAdj5mo));
		assertTrue(resultSet2.contains(systimeAdj5mo));
		assertFalse(resultSet2.contains(systimeAdj7mo));
	}
}
