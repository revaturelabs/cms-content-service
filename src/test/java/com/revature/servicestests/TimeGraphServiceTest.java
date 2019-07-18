package com.revature.servicestests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@SpringBootTest
public class TimeGraphServiceTest {
	
	long ONE_MONTH = 2678400;
	long SIX_MONTHS = 13046400;
	long ONE_YEAR = 31536000;
	
	@Autowired
	TimegraphService ts;
	
	@Autowired
	ContentService cs;
	
	@Autowired
	JdbcTemplate template;
	
	@Test
	@Commit
	void timeScaleTests()
	{
		cs.createContent(new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION",
				"http://TESTURL.COM", new HashSet<Link>(), 1563378565, 1563378565));

		int resultSetSize = ts.findByCreatedBetween(SIX_MONTHS).getReturnedLongs().size();

		cs.createContent(new Content(0, "OLD TEST CONTENT", "Code", "OLD TEST CONTENT DESCRIPTION",
				"http://OLDTESTURL.COM", new HashSet<Link>(), 1405598540, 1405598540));
		
		int resultSetSize2 = ts.findByCreatedBetween(SIX_MONTHS).getReturnedLongs().size();

		JdbcTestUtils.deleteFromTableWhere(template, "content", String.format("title = '%s'", "FIRST TEST CONTENT"));

		JdbcTestUtils.deleteFromTableWhere(template, "content", String.format("title = '%s'", "OLD TEST CONTENT"));
		
		assertEquals(1, resultSetSize);
		assertEquals(1, resultSetSize2);
	}
}
