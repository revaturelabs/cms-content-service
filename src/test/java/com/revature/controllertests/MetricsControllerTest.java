package com.revature.controllertests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.controllers.MetricsController;
import com.revature.util.MetricsData;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MetricsControllerTest {
	
	public MetricsControllerTest()
	{
		super();
	}

	@MockBean
	MetricsController mc;
	
	@Test
	public void nullIds() {
		Map<String, Object> ids = null;
		Exception thrown = assertThrows(Exception.class, () -> mc.getMetrics(0, ids), "Expected getMetrics to throw but it didn't");
		assertTrue(thrown.getClass() == NullPointerException.class);
	}
	
	@Test
	public void outOfRangeId() {
		Map<String, Object> ids = new HashMap<String, Object>();
		ids.put("modules", new ArrayList<Integer>(3000));
		
		Exception thrown = assertThrows(Exception.class, () -> mc.getMetrics(0, ids), "Expected getMetrics to throw but it didn't");
		assertTrue(thrown.getClass() == NullPointerException.class);
	}

}
