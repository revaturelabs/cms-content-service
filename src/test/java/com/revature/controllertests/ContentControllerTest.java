package com.revature.controllertests;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.ContentController;
import com.revature.entities.Content;
import com.revature.services.ContentService;
import com.revature.testingutils.ContentFactory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

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
import org.testng.annotations.BeforeTest;


@SpringBootTest(classes = CMSforceApplication.class)
class ContentControllerTest extends AbstractTestNGSpringContextTests {

	//allows us to send mocked http requests
	private MockMvc mvc;

	//allows json<->object conversion
	@Autowired
	private Gson gson;

	//controller being tested
	@InjectMocks
	private ContentController cc;
	
	//service used by controller
	@Mock
	private ContentService cs;
	
	//content being passed to controller requests
	private Content content;
	
	/**
	 * Initialize Mockito and mocking dependencies 
	 */
	@BeforeClass
	public void setup () {
		//build mock MVC so can build mock requests
		cc = new ContentController ();
		mvc = MockMvcBuilders.standaloneSetup(cc).build();
		
		//enables mockito annotations
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Ensure clean content for each test
	 */
	@BeforeTest
	public void preTestSetup () {
		content = ContentFactory.getContent();
	}
	
	/**
	 * Test adding new content to the back-end
	 * @throws Exception - if mocked http request fails
	 */
	@Test
	public void givenValidDataCreateContent () throws Exception {
		//given
		Mockito.when(cs.createContent(content)).thenReturn(content);
		
		//when
		ResultActions result = mvc.perform(post ("/content")
								.contentType(MediaType.APPLICATION_JSON_VALUE)
								.content(gson.toJson(content)));
		Content ret = gson.fromJson(result.andReturn().getResponse()
						  .getContentAsString(), Content.class);
		
		//then
		//expect status of OK
		result.andExpect(status().isOk());
		//content should be created
		assertEquals (ret, content, "Failed to create content");
	}
	
	/**
	 * Test retrieving all content from the back-end
	 * @throws Exception - if http request fails
	 */
	@Test 
	public void getAllContents () throws Exception {
		//given
		Set<Content> expected = new HashSet<Content> ();
		String actual = null;
		expected.add(content);
		Mockito.when(cs.getAllContent()).thenReturn(expected);
		
		//when
		ResultActions result = mvc.perform(get ("/content"));
		actual = result.andReturn().getResponse().getContentAsString();
		
		//then
		//expect status of OK
		result.andExpect(status().isOk());
		//compare as json to avoid warnings from conversion
		assertEquals (actual, gson.toJson(expected), "Failed to find content");
	}
	
	/**
	 * Test retrieving content based upon an id 
	 * @throws Exception - if http request fails
	 */
	@Test 
	public void getContentById() throws Exception {
		Content actual = null;
		int id = ContentFactory.id;
		
		//given
		Mockito.when(cs.getContentById(id)).thenReturn(content);
		
		//when
		ResultActions result = mvc.perform(get ("/content/" + id));
		actual = gson.fromJson(result.andReturn().getResponse()
				.getContentAsString(), Content.class);
		
		//then
		//expect status of OK
		result.andExpect(status().isOk());
		//should retrieve same content back
		assertEquals (actual, content, "Failed to retrieve content");
		
	}

	/**
	 * Test updating existing content
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void updateContent () throws Exception {
		//given
		Mockito.when (cs.updateContent(content)).thenReturn(content);
		
		//when
		ResultActions result = mvc.perform(put ("/content")
								.contentType(MediaType.APPLICATION_JSON_VALUE)
								.content(gson.toJson(content)));
		Content actual = gson.fromJson(result.andReturn().getResponse()
				.getContentAsString(), Content.class);

		//then
		//expect status of OK
		result.andExpect(status().isOk());
		//should retrieve same content back
		assertEquals (actual, content, "Failed to update content");
				
	}

	/**
	 * Test deleting existing content
	 * @throws Exception
	 */
	@Test
	public void deleteContent () throws Exception {
		//given 
		Mockito.doNothing().when(cs).deleteContent(content);
		//mock content service finding content by id
		Mockito.when(cs.getContentById(ContentFactory.id)).thenReturn(content);
		
		//when
		ResultActions result = mvc.perform(delete ("/content/" + ContentFactory.id)
								.contentType(MediaType.APPLICATION_JSON_VALUE)
								.content(gson.toJson(content)));

		//then
		//expect status of OK
		result.andExpect(status().isOk());
		//expect controller to request deletion of content to service
		Mockito.verify(cs).deleteContent(content);
	}
}
