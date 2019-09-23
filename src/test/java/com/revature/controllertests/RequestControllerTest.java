package com.revature.controllertests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.RequestController;
import com.revature.entities.Request;
import com.revature.services.RequestService;

@SpringBootTest(classes = CMSforceApplication.class)
public class RequestControllerTest extends AbstractTestNGSpringContextTests{
	//allows us to send mocked http requests
		private MockMvc mvc;

		//allows json<->object conversion
    	private ObjectMapper objMapper = new ObjectMapper();

		//controller being tested
		@InjectMocks
		private RequestController rc;
		
		//service used by controller
		@Mock
		private RequestService rs;
		
		//content being passed to controller requests
		private Request request;
		
		@BeforeClass
		public void setup() {
			//build mock MVC so can build mock requests
			rc = new RequestController();
			mvc = MockMvcBuilders.standaloneSetup(rc).build();

			// enables Mockito annotations (???)
			MockitoAnnotations.initMocks(this);
		}

		@BeforeTest
		public void preTestSetup() {
		    request = null;
		}
		
		@Test
		public void givenValidDataCreateRequest() throws Exception {

			request = new Request(0, "test title", "code", "test description", null, 1L, 1L, null);
			Mockito.when(rs.createRequest(request)).thenReturn(request);

			
			ResultActions result = mvc.perform(post ("/requests")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objMapper.writeValueAsString(request)));

			Request ret = objMapper.readValue(result.andReturn().getResponse()
					.getContentAsString(),  Request.class);

			result.andExpect(status().isOk());
		}
}
