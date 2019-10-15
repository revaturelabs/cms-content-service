package com.revature.controllertests;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.ContentController;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.services.ContentService;
import com.revature.services.SearchService;
import com.revature.testingutils.ContentFactory;

@SpringBootTest(classes = CMSforceApplication.class)
public class ContentControllerTest extends AbstractTestNGSpringContextTests {

	// allows us to send mocked http requests
	private MockMvc mvc;

	// allows json<->object conversion
	private ObjectMapper objMapper = new ObjectMapper();

	// controller being tested
	@InjectMocks
	private ContentController cc;

	// service used by controller
	@Mock
	private ContentService cs;

	@Mock
	private SearchService ss;
	
	// content being passed to controller requests
	private Content content;

	/**
	 * Initialize Mockito and mocking dependencies
	 */
	@BeforeClass
	public void setup() {
		// build mock MVC so can build mock requests
		cc = new ContentController();
		mvc = MockMvcBuilders.standaloneSetup(cc).build();

		// enables mockito annotations
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Ensure clean content for each test
	 */
	@BeforeTest
	public void preTestSetup() {
		content = ContentFactory.getContent();
	}

	/**
	 * Test adding new content to the back-end
	 * 
	 * @throws Exception - if mocked http request fails
	 */
	@Test
	public void givenValidDataCreateContent() throws Exception {
		// given
		Mockito.when(cs.createContent(content)).thenReturn(content);

		// when
		ResultActions result = mvc.perform(post("/content").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(content)));
		Content ret = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Content.class);

		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// content should be created
		assertEquals(ret, content, "Failed to create content");
	}

	@Test
	public void givenValidDataCreateContentGetLinksNotNull() throws Exception {
		// given
//		Mockito.when(cs.createContent(content)).thenReturn(content);
		StringBuilder contentBuilder = new StringBuilder(objMapper.writeValueAsString(content));
		contentBuilder.insert(contentBuilder.length() - 1,
				",\"links\":" + objMapper.writeValueAsString(content.getLinks()));

		// when
		ResultActions result = mvc.perform(
				post("/content").contentType(MediaType.APPLICATION_JSON_VALUE).content(contentBuilder.toString()));

		Content ret = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Content.class);
		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// content should be created
		assertEquals(ret, content, "Failed to create content");
	}

	@Test
	public void createLinks() throws Exception {
		List<Link> links = content.getLinks().stream().collect(Collectors.toList());
		// given
		Mockito.when(cs.createLinksByContentId(content.getId(), links)).thenReturn(links);

		// when
		ResultActions result = mvc.perform(post("/content/{id}/links", content.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(links)));
		List<Link> ret = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				objMapper.getTypeFactory().constructCollectionType(List.class, Link.class));
		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// Link List should be created
		assertEquals(ret, links, "Failed to create Link List");
	}

	/**
	 * Test retrieving all content from the back-end
	 * 
	 * @throws Exception - if http request fails
	 */
	@Test
	public void getAllContents() throws Exception {
		// given
		Set<Content> expected = new HashSet<Content>();
		expected.add(content);
		Mockito.when(cs.getAllContent()).thenReturn(expected);

		// when
		ResultActions result = mvc.perform(get("/content"));
		String actual = result.andReturn().getResponse().getContentAsString();

		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// compare as json to avoid warnings from conversion
		assertEquals(actual, convertToJSONContentSetString(expected), "Failed to find content");
	}

	private String convertToJSONContentSetString(Set<Content> allContent) throws Exception {
		StringBuilder result = new StringBuilder("[");
		for (Content con : allContent) {
			result.append(objMapper.writeValueAsString(cc.contentToJSONContent(con)));
			result.append(",");
		}
		if (allContent.size() > 0)
			result.deleteCharAt(result.length() - 1);

		result.append("]");

		return result.toString();
	}

	/**
	 * Test retrieving content based upon an id
	 * 
	 * @throws Exception - if http request fails
	 */
	@Test
	public void getContentById() throws Exception {
		// given
		Mockito.when(cs.getContentById(content.getId())).thenReturn(content);

		// when
		ResultActions result = mvc.perform(get("/content/" + content.getId()));
		Content actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Content.class);

		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// should retrieve same content back
		assertEquals(actual, content, "Failed to retrieve content");
	}

	@Test
	public void getLinksByContentId() throws Exception {
		// given
		Mockito.when(cs.getLinksByContentId(content.getId())).thenReturn(content.getLinks());

		// when
		ResultActions result = mvc.perform(get("/content/" + content.getId() + "/links"));
		Set<Link> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				objMapper.getTypeFactory().constructCollectionType(Set.class, Link.class));

		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// should retrieve same content back
		assertEquals(actual, content.getLinks(), "Failed to retrieve Link Set");
	}

	/**
	 * Test updating existing content
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void updateContent() throws Exception {
		// given
		Mockito.when(cs.updateContent(content)).thenReturn(content);

		// when
		ResultActions result = mvc.perform(put("/content/" + content.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(content)));
		Content actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Content.class);

		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// should retrieve same content back
		assertEquals(actual, content, "Failed to update content");

	}

	@Test
	public void updateLinks() throws Exception {
		List<Link> links = content.getLinks().stream().collect(Collectors.toList());
		// given
		Mockito.when(cs.updateLinksByContentId(content.getId(), links)).thenReturn(links);

		// when
		ResultActions result = mvc.perform(put("/content/" + content.getId() + "/links")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(links)));
		List<Link> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				objMapper.getTypeFactory().constructCollectionType(List.class, Link.class));

		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// should retrieve same content back
		assertEquals(actual, links, "Failed to update Links");

	}

	/**
	 * Test deleting existing content
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteContent() throws Exception {
		// mock content service
		Mockito.doNothing().when(cs).deleteContent(content);
		Mockito.when(cs.getContentById(content.getId())).thenReturn(content);

		// when
		ResultActions result = mvc
				.perform(delete("/content/" + content.getId()).contentType(MediaType.APPLICATION_JSON_VALUE));

		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// expect controller to request deletion of content to service
		Mockito.verify(cs).deleteContent(content);
	}

	@Test
	public void getSearchResults() throws Exception {
		// given
		Set<Content> expected = new HashSet<Content>();
		expected.add(content);
        String [] params = { "title", "format", "modules" };
		List<Integer> integers = new ArrayList<>();
		integers.add(1);
		Mockito.when(ss.filter(anyString(), anyString(), anyList())).thenReturn(expected);
		
		// when
		ResultActions result = mvc.perform(get("/content").param(params[0], content.getTitle()).param(params[1], content.getFormat()).param(params[2], "1"));
		String actual = result.andReturn().getResponse().getContentAsString();
		// then
		// expect status of OK
		result.andExpect(status().isOk());
		// compare as json to avoid warnings from conversion
		assertEquals(actual, convertToJSONContentSetString(expected), "Failed to find content");
	}
}
