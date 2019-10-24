package com.revature.controllers;

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
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.services.ContentService;
import com.revature.services.SearchService;
import com.revature.testingutils.ContentFactory;

@SpringBootTest(classes = CMSforceApplication.class)
public class ContentControllerTest extends AbstractTestNGSpringContextTests {

	private MockMvc mvc;
	private ObjectMapper objMapper = new ObjectMapper();
	private static final int id = 1;
	private static final String subject = "subject";
	private static final long created = 1;
	private static final String affiliation = "affiliation";
	private static final int priority = 0;
	private static Set<Link> links = new HashSet<>();
	private static Link link = new Link (id,new Content(),new Module(),affiliation,priority);


	@InjectMocks
	private ContentController cc;

	@Mock
	private ContentService cs;

	@Mock
	private SearchService ss;
	private Content content;
	private Module module;

	/**
	 * Initialize Mockito and mocking dependencies
	 */
	@BeforeClass
	public void setup() {
		// build mock MVC so can build mock requests
		cc = new ContentController();
		mvc = MockMvcBuilders.standaloneSetup(cc).build();
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Ensure clean content for each test
	 */
	@BeforeTest
	public void preTestSetup() {
		content = ContentFactory.getContent();
		 links = new HashSet<Link> ();
			//caution: content and module not sprint beans here

			 link = new Link (id,new Content(),new Module(),affiliation,priority);

			links.add(link);
			module = new Module(id, subject, created, links, new HashSet<ReqLink>(), new HashSet<Module>(),
					new HashSet<Module>());
	}

	/**
	 * Test adding new content to the back-end
	 * 
	 * @throws Exception - if mocked http request fails
	 */
	@Test
	public void givenValidDataCreateContentStatusOk() throws Exception {
		Mockito.when(cs.createContent(content)).thenReturn(content);
		ResultActions result = mvc.perform(post("/content").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(content)));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void givenValidDataCreateContentTestReturn() throws Exception {
		Mockito.when(cs.createContent(content)).thenReturn(content);
		ResultActions result = mvc.perform(post("/content").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(content)));
		Content ret = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Content.class);
		assertEquals(ret, content, "Failed to create content");
	}

	@Test
	public void givenValidDataCreateContentGetLinksNotNullStatsuOk() throws Exception {
		Mockito.when(cs.createContent(content)).thenReturn(content);
		StringBuilder contentBuilder = new StringBuilder(objMapper.writeValueAsString(content));
		contentBuilder.insert(contentBuilder.length() - 1,
				",\"links\":" + objMapper.writeValueAsString(content.getLinks()));
		ResultActions result = mvc.perform(
				post("/content").contentType(MediaType.APPLICATION_JSON_VALUE).content(contentBuilder.toString()));
		result.andExpect(status().isOk());
	}

	@Test
	public void givenValidDataCreateContentGetLinksNotNullTestReturn() throws Exception {
		StringBuilder contentBuilder = new StringBuilder(objMapper.writeValueAsString(content));
		contentBuilder.insert(contentBuilder.length() - 1,
				",\"links\":" + objMapper.writeValueAsString(content.getLinks()));
		ResultActions result = mvc.perform(
				post("/content").contentType(MediaType.APPLICATION_JSON_VALUE).content(contentBuilder.toString()));
		Content ret = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Content.class);
		assertEquals(ret, content, "Failed to create content");
	}

	
	@Test
	public void createLinksStatusOk() throws Exception {
		List<Link> links = content.getLinks().stream().collect(Collectors.toList());
		Mockito.when(cs.createLinksByContentId(content.getId(), links)).thenReturn(links);
		ResultActions result = mvc.perform(post("/content/{id}/links", content.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(links)));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void createLinksTestReturn() throws Exception {
		List<Link> links = content.getLinks().stream().collect(Collectors.toList());
		Mockito.when(cs.createLinksByContentId(content.getId(), links)).thenReturn(links);
		ResultActions result = mvc.perform(post("/content/{id}/links", content.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(links)));
		List<Link> ret = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				objMapper.getTypeFactory().constructCollectionType(List.class, Link.class));
		assertEquals(ret, links, "Failed to create Link List");
	}

	/**
	 * Test retrieving all content from the back-end
	 * 
	 * @throws Exception - if http request fails
	 */
	@Test
	public void getAllContentsStatusOk() throws Exception {
		Set<Content> expected = new HashSet<Content>();
		expected.add(content);
		Mockito.when(cs.getAllContent()).thenReturn(expected);
		ResultActions result = mvc.perform(get("/content"));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void getAllContentsStatusTestReturn() throws Exception {
		Set<Content> expected = new HashSet<Content>();
		expected.add(content);
		Mockito.when(cs.getAllContent()).thenReturn(expected);
		ResultActions result = mvc.perform(get("/content"));
		String actual = result.andReturn().getResponse().getContentAsString();
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
	public void getContentByIdstatusOk() throws Exception {
		// given
		Mockito.when(cs.getContentById(content.getId())).thenReturn(content);
		ResultActions result = mvc.perform(get("/content/" + content.getId()));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void getContentByIdTestReturn() throws Exception {
		// given
		Mockito.when(cs.getContentById(content.getId())).thenReturn(content);
		ResultActions result = mvc.perform(get("/content/" + content.getId()));
		Content actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Content.class);
		assertEquals(actual, content, "Failed to retrieve content");
	}

	@Test
	public void getLinksByContentIdStatusOk() throws Exception {
		Mockito.when(cs.getLinksByContentId(content.getId())).thenReturn(content.getLinks());
		ResultActions result = mvc.perform(get("/content/" + content.getId() + "/links"));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void getLinksByContentIdTestReturn() throws Exception {
		Mockito.when(cs.getLinksByContentId(content.getId())).thenReturn(content.getLinks());
		ResultActions result = mvc.perform(get("/content/" + content.getId() + "/links"));
		Set<Link> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				objMapper.getTypeFactory().constructCollectionType(Set.class, Link.class));
		assertEquals(actual, content.getLinks(), "Failed to retrieve Link Set");
	}

	/**
	 * Test updating existing content
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void updateContentStatusOk() throws Exception {
		Mockito.when(cs.updateContent(content)).thenReturn(content);
		ResultActions result = mvc.perform(put("/content/" + content.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(content)));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void updateContentTestReturn() throws Exception {
		Mockito.when(cs.updateContent(content)).thenReturn(content);
		ResultActions result = mvc.perform(put("/content/" + content.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(content)));
		Content actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Content.class);
		assertEquals(actual, content, "Failed to update content");
	}

	@Test
	public void updateLinksStatusOk() throws Exception {
		List<Link> links = content.getLinks().stream().collect(Collectors.toList());
		Mockito.when(cs.updateLinksByContentId(content.getId(), links)).thenReturn(links);
		ResultActions result = mvc.perform(put("/content/" + content.getId() + "/links")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(links)));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void updateLinksTestReturn() throws Exception {
		List<Link> links = content.getLinks().stream().collect(Collectors.toList());
		Mockito.when(cs.updateLinksByContentId(content.getId(), links)).thenReturn(links);
		ResultActions result = mvc.perform(put("/content/" + content.getId() + "/links")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(links)));
		List<Link> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				objMapper.getTypeFactory().constructCollectionType(List.class, Link.class));
		assertEquals(actual, links, "Failed to update Links");
	}

	/**
	 * Test deleting existing content
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteContentStatusOk() throws Exception {
		Mockito.doNothing().when(cs).deleteContent(content);
		Mockito.when(cs.getContentById(content.getId())).thenReturn(content);
		ResultActions result = mvc
				.perform(delete("/content/" + content.getId()).contentType(MediaType.APPLICATION_JSON_VALUE));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void getSearchResultsStatusOk() throws Exception {
		Set<Content> expected = new HashSet<Content>();
		expected.add(content);
        String [] params = { "title", "format", "modules" };
		List<Integer> integers = new ArrayList<>();
		integers.add(1);
		Mockito.when(ss.filter(anyString(), anyList(), anyList())).thenReturn(expected);
		ResultActions result = mvc.perform(get("/content").param(params[0], content.getTitle()).param(params[1], content.getFormat()).param(params[2], "1"));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void getSearchResultsTestReturn() throws Exception {
		Set<Content> expected = new HashSet<Content>();
		expected.add(content);
        String [] params = { "title", "format", "modules", "curriculum"};
		List<Integer> integers = new ArrayList<>();
		integers.add(1);
		Mockito.when(ss.filter(anyString(), anyList(), anyList(), anyList())).thenReturn(expected);
		ResultActions result = mvc.perform(get("/content").param(params[0], content.getTitle()).param(params[1], content.getFormat()).param(params[2], "1").param(params[3], "1"));
		String actual = result.andReturn().getResponse().getContentAsString();
		assertEquals(actual, convertToJSONContentSetString(expected), "Failed to find content");
	}
	
}
