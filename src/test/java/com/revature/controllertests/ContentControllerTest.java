package com.revature.controllertests;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;


/*
 * This class of tests is testing the functionality of the servlet
 * and controller:
 * 
 * Given a particular request is made to a servlet endpoint regarding
 * contend objects,
 * 
 * Is the proper database method in our controller being triggered.
 * 		If values are returned, are they making it back to the requester?
 */

@SpringBootTest(classes = CMSforceApplication.class)
public class ContentControllerTest extends AbstractTestNGSpringContextTests {

	//allows us to send mocked http requests
	private MockMvc mvc;

	//allows json<->object conversion
    private ObjectMapper objMapper = new ObjectMapper();

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
	 * Test that our servlet and controller are triggering 
	 * the appropriate DB methods when the '/content' is 
	 * hit with a POST request, and that the returned value 
	 * makes it back to the request source.
	 * @throws Exception - if mocked http request fails
	 */
	@Test
	public void testCreateContent() throws Exception {
		//given
		Mockito.when(cs.createContent(content)).thenReturn(content);
		
		//when
		ResultActions result = mvc.perform(post ("/content")
								.contentType(MediaType.APPLICATION_JSON_VALUE)
								.content(objMapper.writeValueAsString(content)));
		Content ret = objMapper.readValue(result.andReturn().getResponse()
						  .getContentAsString(), Content.class);
		//then
		//expect Post request status of OK
		result.andExpect(status().isOk());
		//content should be created
		assertEquals (ret, content, "Failed to create content");
	}
	
	//Create Links based on content ID
	@Test(enabled=false)
	public void testCreateLinks() throws Exception
	{
		//Needs to be Implemented.
	}
	
	/**
	 * Test that our servlet and controller are 
	 * triggering the appropriate DB methods when 
	 * the '/content' is hit with a GET request, 
	 * and that the returned value makes it back 
	 * to the request source.
	 * @throws Exception - if http request fails
	 */
	@Test 
	public void testGetAllContents() throws Exception {
		//given
		Set<Content> expected = new HashSet<Content> ();
		expected.add(content);
		Mockito.when(cs.getAllContent()).thenReturn(expected);
		
		//when
		ResultActions result = mvc.perform(get ("/content"));
		String actual = result.andReturn().getResponse().getContentAsString();
		
		//then
		//expect status of OK
		result.andExpect(status().isOk());
		//compare as json to avoid warnings from conversion
		assertEquals (actual, convertContentSetToString(expected), "Failed to find content");
	}
	
	/**
	 * This helper function converts a Content Set
	 * to a string that can be compared to another
	 * string representation of a content object.
	 * 
	 * 
	 * @param allContent
	 * 		A set, containing several Content Objects.
	 * @return
	 * 		A string representing a set of Content Objects.
	 * @throws Exception
	 * 
	 */
	private String convertContentSetToString(Set<Content> allContent) throws Exception
	{
		StringBuilder result = new StringBuilder("[");
		for (Content con : allContent)
		{
			result.append(objMapper.writeValueAsString(cc.contentToJSONContent(con)));
			result.append(",");
		}
		if (allContent.size() > 0)
			result.deleteCharAt(result.length() - 1);

		result.append("]");

		return result.toString();
	}
	
	/**
	 * Test that our servlet and controller are 
	 * triggering the appropriate content Service methods 
	 * when the /content/{content_ID} endpoint is 
	 * hit with a GET request, and that the returned 
	 * value makes it back to the request source.
	 * @throws Exception - if http request fails
	 */
	@Test 
	public void testGetContentById() throws Exception {
		//given
		Mockito.when(cs.getContentById(content.getId())).thenReturn(content);
		
		//when
		ResultActions result = mvc.perform(get ("/content/" + content.getId()));
		Content actual = objMapper.readValue(result.andReturn().getResponse()
				.getContentAsString(), Content.class);
		
		//then
		//expect status of OK
		result.andExpect(status().isOk());
		//should retrieve same content back
		assertEquals (actual, content, "Failed to retrieve content");
	}

	//Grabs Links based on content ID
	@Test(enabled=false)
	public void testGetLinksByContentId() throws Exception
	{
		//Needs to be Implemented.
	}
	
	//Check ContentController
	@Test(enabled=false)
	public void testGetSearchResults() throws Exception
	{
		//Needs to be Implemented.
	}
	
	/**
	 * Test that our servlet and controller are triggering
	 * The appropriate DB methods in the content Service when
	 * the /content/{id} endpoint is hit with a PUT request, 
	 * and that the returned value makes it back to the 
	 * request source.
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void testUpdateContent() throws Exception {
		//given
		Mockito.when (cs.updateContent(content)).thenReturn(content);
		
		//when
		ResultActions result = mvc.perform(put ("/content/" + content.getId())
								.contentType(MediaType.APPLICATION_JSON_VALUE)
								.content(objMapper.writeValueAsString(content)));
		Content actual = objMapper.readValue(result.andReturn().getResponse()
				.getContentAsString(), Content.class);

		//then
		//expect status of put operation is OK
		result.andExpect(status().isOk());
		//should retrieve same content back
		assertEquals (actual, content, "Failed to update content");
				
	}
	
	//Check Content Controller
	@Test(enabled=false)
	public void testUpdateLinks() throws Exception
	{
		//Needs to be Implemented.
	}

	/**
	 * Test that our servlet and controller are triggering 
	 * the appropriate DB method in the content Service when 
	 * /content/{id} endpoint is hit with a DELETE request.
	 * @throws Exception
	 */
	@Test
	public void testDeleteContent() throws Exception {
		//mock content service
		Mockito.doNothing().when(cs).deleteContent(content);
		Mockito.when(cs.getContentById(content.getId())).thenReturn(content);

		//when
		ResultActions result = mvc.perform(delete ("/content/" + content.getId())
								.contentType(MediaType.APPLICATION_JSON_VALUE));

		//then
		//expect status of OK
		result.andExpect(status().isOk());
		//expect controller to request deletion of content to service
		Mockito.verify(cs).deleteContent(content);
	}
}
