package com.revature.controllersTest;

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
	 * The {@link com.revature.controllers.ContentControllerTest#setup() setup} method prepares for tests in the 
	 * ContentControllerTest file by mocking data using Mockito before any test is run. 
	 */
	@BeforeClass
	public void setup() {
		// build mock MVC so can build mock requests
		cc = new ContentController();
		mvc = MockMvcBuilders.standaloneSetup(cc).build();
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * The {@link com.revature.controllers.ContentControllerTest#preTestSetup() preTestSetup} method prepares for a test
	 * by ensuring clean content is made for each test.
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
	 * The {@link com.revature.controllers.ContentControllerTest#testCreateContent() TestCreateContent} method tests the
	 * {@link com.revature.controllers.ContentController#createContent(com.revature.JSONEntities.JSONContent) createContent} method.
	 * This method assumes content is created and passed using a Post request.
	 * The result expected is a status OK ResponseEntity.
	 * Test adding new content to the back-end
	 * 
	 * @throws Exception - if mocked http request fails
	 * @return A passed test if the returned status is OK.
	 */
	@Test
	public void testCreateContent() throws Exception {
		Mockito.when(cs.createContent(content)).thenReturn(content);
		ResultActions result = mvc.perform(post("/content").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(content)));
		result.andExpect(status().isOk());
	}
	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testCreateContent_Return() TestCreateContent_Return} method tests the
	 * {@link com.revature.controllers.ContentController#createContent(com.revature.JSONEntities.JSONContent) createContent} method.
	 * This method assumes that content is created and passed using a Post request.
	 * The result expected is a returned content that matches the mocked content.
	 * @throws Exception
	 */
	@Test
	public void testCreateContent_Return() throws Exception {
		Mockito.when(cs.createContent(content)).thenReturn(content);
		ResultActions result = mvc.perform(post("/content").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(content)));
		Content ret = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Content.class);
		assertEquals(ret, content, "Failed to create content");
	}
	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testCreateContent_LinksNotNull() TestCreateContent_LinksNotNull} method tests the
	 * {@link com.revature.controllers.ContentController#createContent(com.revature.JSONEntities.JSONContent)} method.
	 * The method assumes that a mocked content will be sent using Post with links attached.
	 * The result expected is a ResponseEntity with status OK.
	 * @throws Exception
	 */
	@Test
	public void testCreateContent_LinksNotNull() throws Exception {
		Mockito.when(cs.createContent(content)).thenReturn(content);
		StringBuilder contentBuilder = new StringBuilder(objMapper.writeValueAsString(content));
		contentBuilder.insert(contentBuilder.length() - 1,
				",\"links\":" + objMapper.writeValueAsString(content.getLinks()));
		ResultActions result = mvc.perform(
				post("/content").contentType(MediaType.APPLICATION_JSON_VALUE).content(contentBuilder.toString()));
		result.andExpect(status().isOk());
	}

	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testCreateContent_LinksNotNullReturn() TestCreateContent_LinksNotNullReturn} method tests the
	 * {@link com.revature.controllers.ContentController#createContent(com.revature.JSONEntities.JSONContent)} method.
	 * The method assumes that a mocked content will be sent using Post with links attached.
	 * The result expected is a ResponseEntity that has the mocked content and links attached.
	 * @throws Exception
	 */
	@Test
	public void testCreateContent_LinksNotNullReturn() throws Exception {
		StringBuilder contentBuilder = new StringBuilder(objMapper.writeValueAsString(content));
		contentBuilder.insert(contentBuilder.length() - 1,
				",\"links\":" + objMapper.writeValueAsString(content.getLinks()));
		ResultActions result = mvc.perform(
				post("/content").contentType(MediaType.APPLICATION_JSON_VALUE).content(contentBuilder.toString()));
		Content ret = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Content.class);
		assertEquals(ret, content, "Failed to create content");
	}

	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testCreateLinks() TestCreateLinks} method tests the
	 * {@link com.revature.controllers.ContentController#createLinks(List, int) createLinks} method.
	 * This method assumes mocked content has links to be created
	 * The reult expected is a ResponseEntity with status OK.
	 * @throws Exception
	 */
	@Test
	public void testCreateLinks() throws Exception {
		List<Link> links = content.getLinks().stream().collect(Collectors.toList());
		Mockito.when(cs.createLinksByContentId(content.getId(), links)).thenReturn(links);
		ResultActions result = mvc.perform(post("/content/{id}/links", content.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(links)));
		result.andExpect(status().isOk());
	}
	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testCreateLinks_Return() TestCreateLinks_Return} method tests the
	 * {@link com.revature.controllers.ContentController#createLinks(List, int) createLinks} method.
	 * This method assumes mocked content has links to be created
	 * The result expected is a ResponseEntity with a list of Links that were created.
	 * @throws Exception
	 */
	@Test
	public void testCreateLinks_Return() throws Exception {
		List<Link> links = content.getLinks().stream().collect(Collectors.toList());
		Mockito.when(cs.createLinksByContentId(content.getId(), links)).thenReturn(links);
		ResultActions result = mvc.perform(post("/content/{id}/links", content.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(links)));
		List<Link> ret = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				objMapper.getTypeFactory().constructCollectionType(List.class, Link.class));
		assertEquals(ret, links, "Failed to create Link List");
	}

	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testGetAllContents() testGetAllContents} method tests the
	 * {@link com.revature.controllers.ContentController#getAllContent() getAllContents} method.
	 * This method assumes nothing is sent in a Get request.
	 * The result expected is a ResponseEntity with status OK.
	 * @throws Exception
	 */
	@Test
	public void testGetAllContents() throws Exception {
		Set<Content> expected = new HashSet<Content>();
		expected.add(content);
		Mockito.when(cs.getAllContent()).thenReturn(expected);
		ResultActions result = mvc.perform(get("/content"));
		result.andExpect(status().isOk());
	}
	
	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testGetAllContents_Return() testGetAllContents_Return} method tests the
	 * {@link com.revature.controllers.ContentController#getAllContent() getAllContents} method.
	 * This method assumes nothing is sent in a Get request.
	 * The result expected is a ResponseEntity with the mocked content.
	 * @throws Exception
	 */
	@Test
	public void testGetAllContents_Return() throws Exception {
		Set<Content> expected = new HashSet<Content>();
		expected.add(content);
		Mockito.when(cs.getAllContent()).thenReturn(expected);
		ResultActions result = mvc.perform(get("/content"));
		String actual = result.andReturn().getResponse().getContentAsString();
		assertEquals(actual, convertToJSONContentSetString(expected), "Failed to find content");
	}
	/**
	 * The {@link com.revature.controllers.ContentControllerTest#convertToJSONContentSetString(Set) convertToJSONContentSetString} method is
	 * a utility function for use in the ContentControllerTest file.
	 * @param allContent A set that contains the content to be JSON stringified.
	 * @return
	 * @throws Exception
	 */
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
	 * The {@link com.revature.controllers.ContentControllerTest#testGetContent_ById() testGetContent_ById} method tests the
	 * {@link com.revature.controllers.ContentController#getContentById(int) getContentById} method.
	 * The method assumes that a mocked content is available for retrieval.
	 * The result expected is a ResponseEntity with status OK.
	 * 
	 * @throws Exception - if http request fails
	 */
	@Test
	public void testGetContent_ById() throws Exception {
		// given
		Mockito.when(cs.getContentById(content.getId())).thenReturn(content);
		ResultActions result = mvc.perform(get("/content/" + content.getId()));
		result.andExpect(status().isOk());
	}
	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testGetContent_ByIdReturn() testGetContent_ByIdReturn} method tests the
	 * {@link com.revature.controllers.ContentController#getContentById(int) getContentById} method.
	 * The method assumes that a mocked content is available for retrieval.
	 * The result expected is a ResponseEntity with the mocked content.
	 * 
	 * @throws Exception - if http request fails
	 */
	@Test
	public void testGetContent_ByIdReturn() throws Exception {
		// given
		Mockito.when(cs.getContentById(content.getId())).thenReturn(content);
		ResultActions result = mvc.perform(get("/content/" + content.getId()));
		Content actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Content.class);
		assertEquals(actual, content, "Failed to retrieve content");
	}
	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testGetLinks_ByContentId() testGetLinks_ByContentId} method tests
	 * the {@link com.revature.controllers.ContentController#getLinksByContentId(int) getLinksByContentId} method.
	 * This method assumes that a mocked content is available that has links.
	 * The result expected is a ResponseEntity with status OK.
	 */
	@Test
	public void testGetLinks_ByContentId() throws Exception {
		Mockito.when(cs.getLinksByContentId(content.getId())).thenReturn(content.getLinks());
		ResultActions result = mvc.perform(get("/content/" + content.getId() + "/links"));
		result.andExpect(status().isOk());
	}
	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testGetLinks_ByContentIdReturn() testGetLinks_ByContentIdReturn} method tests
	 * the {@link com.revature.controllers.ContentController#getLinksByContentId(int) getLinksByContentId} method.
	 * This method assumes that a mocked content is available that has links.
	 * The result expected is a ResponseEntity with the mocked content links.
	 */
	@Test
	public void testGetLinks_ByContentIdReturn() throws Exception {
		Mockito.when(cs.getLinksByContentId(content.getId())).thenReturn(content.getLinks());
		ResultActions result = mvc.perform(get("/content/" + content.getId() + "/links"));
		Set<Link> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				objMapper.getTypeFactory().constructCollectionType(Set.class, Link.class));
		assertEquals(actual, content.getLinks(), "Failed to retrieve Link Set");
	}

	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testUpdateContent() testUpdateContent} method tests the
	 * {@link com.revature.controllers.ContentController#updateContent(Content) updateContent} method.
	 * The method assumes that a mocked content is available for "update"
	 * The result expected is a response entity with Status OK.
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void testUpdateContent() throws Exception {
		Mockito.when(cs.updateContent(content)).thenReturn(content);
		ResultActions result = mvc.perform(put("/content/" + content.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(content)));
		result.andExpect(status().isOk());
	}
	
	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testUpdateContent_Return() testUpdateContent_Return} method tests the
	 * {@link com.revature.controllers.ContentController#updateContent(Content) updateContent} method.
	 * The method assumes that a mocked content is available for "update"
	 * The result expected is a response entity with the mocked content returned.
	 * 
	 * @throws Exception - if the http request fails
	 */
	@Test
	public void testUpdateContent_Return() throws Exception {
		Mockito.when(cs.updateContent(content)).thenReturn(content);
		ResultActions result = mvc.perform(put("/content/" + content.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(content)));
		Content actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Content.class);
		assertEquals(actual, content, "Failed to update content");
	}
	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testUpdateLinks() testUpdateLinks} method tests the
	 * {@link com.revature.controllers.ContentController#updateLinks(List, int)} method.
	 * The method assumes a mocked content has links.
	 * The result expected is a ResponseEntity with status ok.
	 * @throws Exception
	 */
	@Test
	public void testUpdateLinks() throws Exception {
		List<Link> links = content.getLinks().stream().collect(Collectors.toList());
		Mockito.when(cs.updateLinksByContentId(content.getId(), links)).thenReturn(links);
		ResultActions result = mvc.perform(put("/content/" + content.getId() + "/links")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(links)));
		result.andExpect(status().isOk());
	}
	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testUpdateLinks_Return() testUpdateLinks_Return} method tests the
	 * {@link com.revature.controllers.ContentController#updateLinks(List, int) updateLinks} method.
	 * The method assumes a mocked content has links.
	 * The result expected is a ResponseEntity with the mocked content returned.
	 * @throws Exception
	 */
	@Test
	public void testUpdateLinks_Return() throws Exception {
		List<Link> links = content.getLinks().stream().collect(Collectors.toList());
		Mockito.when(cs.updateLinksByContentId(content.getId(), links)).thenReturn(links);
		ResultActions result = mvc.perform(put("/content/" + content.getId() + "/links")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objMapper.writeValueAsString(links)));
		List<Link> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				objMapper.getTypeFactory().constructCollectionType(List.class, Link.class));
		assertEquals(actual, links, "Failed to update Links");
	}

	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testDeleteContent() testDeleteContent} method tests the
	 * {@link com.revature.controllers.ContentController#deleteContent(int) deleteContent} method.
	 * The method assumes nothing happens when delete is called.
	 * The result expected is a ResponseEntity with status OK.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteContent() throws Exception {
		Mockito.doNothing().when(cs).deleteContent(content);
		Mockito.when(cs.getContentById(content.getId())).thenReturn(content);
		ResultActions result = mvc
				.perform(delete("/content/" + content.getId()).contentType(MediaType.APPLICATION_JSON_VALUE));
		result.andExpect(status().isOk());
	}
	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testSearchResults() testSearchResults} method tests the
	 * {@link com.revature.controllers.ContentController#getSearchResults(String, List, List, List) getSearchResults} method.
	 * The method assumes that we are searching for the mocked content.
	 * The result expected is a ResponseEntity with status OK.
	 * @throws Exception
	 */
	@Test
	public void testSearchResults() throws Exception {
		Set<Content> expected = new HashSet<Content>();
		expected.add(content);
        String [] params = { "title", "format", "modules" };
		List<Integer> integers = new ArrayList<>();
		integers.add(1);
		Mockito.when(ss.filter(anyString(), anyList(), anyList())).thenReturn(expected);
		ResultActions result = mvc.perform(get("/content").param(params[0], content.getTitle()).param(params[1], content.getFormat()).param(params[2], "1"));
		result.andExpect(status().isOk());
	}
	/**
	 * The {@link com.revature.controllers.ContentControllerTest#testSearchResults_Return() testSearchResults_Return} method tests the
	 * {@link com.revature.controllers.ContentController#getSearchResults(String, List, List, List) getSearchResults} method.
	 * The method assumes that we are searching for the mocked content.
	 * The result expected is a ResponseEntity with the mocked content as a JSON string.
	 * @throws Exception
	 */
	@Test
	public void testSearchResults_Return() throws Exception {
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
