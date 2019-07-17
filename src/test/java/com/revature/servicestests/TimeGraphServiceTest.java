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
	
	@Autowired
	TimegraphService ts;
	
	@Autowired
	ContentService cs;
	
	@Autowired
	JdbcTemplate template;
	
	@Test
	@Commit
	@Order(1)
	void createValidContent() {
		cs.createContent(new Content(0, "FIRST TEST CONTENT", "Code", "FIRST TEST CONTENT DESCRIPTION",
				"http://TESTURL.COM", new HashSet<Link>(), 1563378565, 1563378565));
	}
	
	@Test
	@Commit
	@Order(2)
	void testOneInTimeFrame() {
		int resultSetSize = ts.findByCreatedBetween(1563378565).size();
		assertEquals(1, resultSetSize);
	}
	
	@Test
	@Commit
	@Order(3)
	void createValidContent2() {
		cs.createContent(new Content(0, "OLD TEST CONTENT", "Code", "OLD TEST CONTENT DESCRIPTION",
				"http://OLDTESTURL.COM", new HashSet<Link>(), 1405598540, 1405598540));
	}
	
	@Test
	@Commit
	@Order(4)
	void testOutsideOfTimeFrame() {
		int resultSetSize = ts.findByCreatedBetween(1531828945).size();
		assertEquals(1, resultSetSize);
		
	}
	

}
