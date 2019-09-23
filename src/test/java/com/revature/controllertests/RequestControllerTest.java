package com.revature.controllertests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.RequestController;
import com.revature.entities.Request;
import com.revature.services.RequestService;

@SpringBootTest(classes = CMSforceApplication.class)
public class RequestControllerTest extends AbstractTestNGSpringContextTests{
	//allows us to send mocked http requests
		private MockMvc mvc;

		//allows json<->object conversion
		@Autowired
		private Gson gson;

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
		
		@Test
		public void givenValidDataCreateRequest() throws Exception {
			Mockito.when(rs.createRequest(request)).thenReturn(request);
			
			ResultActions result = mvc.perform(post ("/requests")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(gson.toJson(request)));
			
			Request ret = gson.fromJson(result.andReturn().getResponse()
					.getContentAsString(),  Request.class);
		}
		
		
		
}
