package com.revature.servicestests;

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

}
