package com.revature.servicestests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.User;
import com.revature.repositories.ContentRepository;
import com.revature.services.ContentService;
import com.revature.services.TimegraphService;

/**
 * Tests for the Time Graph Service.
 * 
 * <p> Contains the tests that cover the breadth and depth of the TimeGraphService.
 * 
 * @author wsm
 * @version 3.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@SpringBootTest
public class TimeGraphServiceTest {
	
	/** ONE_MONTH - {@value}, Represents 1 month in milliseconds. */
	long ONE_MONTH = 2592000000L;
	
	/** SIX_MONTHS - {@value}, Represents 6 months in milliseconds. */
	long SIX_MONTHS = 15552000000L;
	
	/** ONE_YEAR - {@value}, Represents 1 year in milliseconds. */
	long ONE_YEAR = 31536000000L;
	
	@Autowired
	TimegraphService ts;
	
	@Autowired
	ContentService cs;
	
	@Autowired
	ContentRepository cr;
	
	@Autowired
	JdbcTemplate template;
	
	/**
	 * Tests if a value that should fall in the margin appears.
	 */
	@Test
	@Rollback
	void timeScaleInMarginOneVal()
	{
		long fivemonths = ONE_MONTH * 5;
		long systimeAdj5mo = System.currentTimeMillis() - fivemonths;
		User user = new User("TEST", "USER");
		user = cr.save(user);
		cs.createContent(new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION",
				"http://TESTURL.COM", systimeAdj5mo, systimeAdj5mo, new HashSet<Link>(), user, "PENDING"));

		List<Long> resultSet = ts.findByCreatedBetween(SIX_MONTHS).getReturnedLongs();

		JdbcTestUtils.deleteFromTableWhere(template, "content", String.format("title = '%s'", "FIRST TEST CONTENT"));

		assertTrue(resultSet.contains(systimeAdj5mo));
	}
	
	/**
	 * Tests if a value that should fall in the margin when there are multiple values in the dataset appears.
	 */
	@Test
	@Rollback
	void timeScaleInMarginTwoVal()
	{
		long fivemonths = ONE_MONTH * 5;
		long systimeAdj5mo = System.currentTimeMillis() - fivemonths;
		User user = new User("TEST", "USER");
		user = cr.save(user);
		cs.createContent(new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION",
				"http://TESTURL.COM", systimeAdj5mo, systimeAdj5mo, new HashSet<Link>(), user, "PENDING"));

		long sevenmonths = ONE_MONTH + SIX_MONTHS;
		long systimeAdj7mo = System.currentTimeMillis() - sevenmonths;
		cs.createContent(new Content(0, "OLD TEST CONTENT", "Code", "OLD TEST CONTENT DESCRIPTION",
				"http://OLDTESTURL.COM", systimeAdj7mo, systimeAdj7mo, new HashSet<Link>(), user, "PENDING"));
		
		List<Long> resultSet = ts.findByCreatedBetween(SIX_MONTHS).getReturnedLongs();

		JdbcTestUtils.deleteFromTableWhere(template, "content", String.format("title = '%s'", "FIRST TEST CONTENT"));

		JdbcTestUtils.deleteFromTableWhere(template, "content", String.format("title = '%s'", "OLD TEST CONTENT"));
		
		assertTrue(resultSet.contains(systimeAdj5mo));
	}

	/**
	 * Tests if a value that should not appear in the set appears.
	 */
	@Test
	@Rollback
	void timeScaleOutsideMarginTwoVal()
	{
		long fivemonths = ONE_MONTH * 5;
		long systimeAdj5mo = System.currentTimeMillis() - fivemonths;
		User user = new User("TEST", "USER");
		user = cr.save(user);
		cs.createContent(new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION",
				"http://TESTURL.COM", systimeAdj5mo, systimeAdj5mo, new HashSet<Link>(), user, "PENDING"));

		long sevenmonths = ONE_MONTH + SIX_MONTHS;
		long systimeAdj7mo = System.currentTimeMillis() - sevenmonths;
		cs.createContent(new Content(0, "OLD TEST CONTENT", "Code", "OLD TEST CONTENT DESCRIPTION",
				"http://OLDTESTURL.COM", systimeAdj7mo, systimeAdj7mo, new HashSet<Link>(), user, "PENDING"));
		
		List<Long> resultSet = ts.findByCreatedBetween(SIX_MONTHS).getReturnedLongs();

		JdbcTestUtils.deleteFromTableWhere(template, "content", String.format("title = '%s'", "FIRST TEST CONTENT"));

		JdbcTestUtils.deleteFromTableWhere(template, "content", String.format("title = '%s'", "OLD TEST CONTENT"));
		
		assertFalse(resultSet.contains(systimeAdj7mo));
	}

}
