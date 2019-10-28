package com.revature.controllersTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmsforce.CMSforceApplication;
import com.revature.controllers.LinkController;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.services.LinkService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests the {@link com.revature.controllers.LinkController LinkController} class.
 */
@SpringBootTest(classes = CMSforceApplication.class)
public class LinkControllerTest {

	private MockMvc mvc;
	private ObjectMapper objMapper = new ObjectMapper();
	@InjectMocks
	private LinkController lc;
	@Mock
	private LinkService ls;

	/**
	 * Set up for testing the LinkController.
	 * Instantiates mocks.
	 */
	@BeforeClass
	public void setup() {
		lc = new LinkController();
		mvc = MockMvcBuilders.standaloneSetup(lc).build();
	}

	/**
	 * Initializes mocks before each method.
	 */
	@BeforeMethod
	public void reinitMocks() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Tests the status result of the {@link com.revature.controllers.LinkController#createLink(Link) createLink(Link)} method.
	 * 
	 * This method assumes that the {@link com.revature.entities.Link Link} being created contains both a
	 * valid {@link com.revature.entities.Content Content} object and a valid {@link com.revature.entities.Module Module} object.
	 * 
	 * The expected result is a status of OK (200).
	 * @throws Exception
	 */
	@Test
	public void testCreateLink_StatusOk() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(0, content, module, "affiliation",1);
		Mockito.when(ls.createLink(link)).thenReturn(link);
		ResultActions result = mvc.perform(post("/links").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(link)));
		result.andExpect(status().isOk());
	}
	
	/**
	 * Tests the {@link com.revature.controllers.LinkController#createLink(Link) createLink(Link)} method.
	 * 
	 * This method assumes that the {@link com.revature.entities.Link Link} being created contains both a
	 * valid {@link com.revature.entities.Content Content} object and a valid {@link com.revature.entities.Module Module} object.
	 * 
	 * The expected result is a Link mapped from a ResponseEntity<Link> matching the test Link.
	 * @throws Exception
	 */
	@Test
	public void testCreateLink_Return() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(0, content, module, "affiliation", 1);
		Mockito.when(ls.createLink(link)).thenReturn(link);
		ResultActions result = mvc.perform(post("/links").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(link)));
		Link actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Link.class);
		Assert.assertEquals(actual, link, "Failed to retrieve expected link");
	}

	/**
	 * Tests the status result of the {@link com.revature.controllers.LinkController#getAllLinks() getAllLinks()} method.
	 * 
	 * This method assumes that the {@link com.revature.entities.Link Links} being retrieved contain both a
	 * valid {@link com.revature.entities.Content Content} object and a valid {@link com.revature.entities.Module Module} object.
	 * 
	 * The expected result is a status of OK (200).
	 * @throws Exception
	 */
	@Test
	public void testGetAllLinks_StatusOk() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(0, content, module, "affiliation", 1);
		Set<Link> allLinks = new HashSet<Link>();
		allLinks.add(link);
		Mockito.when(ls.getAllLinks()).thenReturn(allLinks);
		ResultActions result = mvc.perform(get("/links").contentType(MediaType.APPLICATION_JSON_VALUE));
		result.andExpect(status().isOk());
	}

	/**
	 * Tests the {@link com.revature.controllers.LinkController#getAllLinks() getAllLinks()} method.
	 * 
	 * This method assumes that the {@link com.revature.entities.Link Links} being retrieved contain both a
	 * valid {@link com.revature.entities.Content Content} object and a valid {@link com.revature.entities.Module Module} object.
	 * 
	 * The expected result is a Set<Link> mapped from a ResponseEntity<Set<Link>> matching the test Set<Link>.
	 * @throws Exception
	 */
	@Test
	public void testGetAllLinksStatus_Return() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(0, content, module, "affiliation", 1);
		Set<Link> allLinks = new HashSet<Link>();
		allLinks.add(link);
		Mockito.when(ls.getAllLinks()).thenReturn(allLinks);
		ResultActions result = mvc.perform(get("/links").contentType(MediaType.APPLICATION_JSON_VALUE));
		Set<Link> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<Link>>() {
				});
		Assert.assertEquals(actual, allLinks, "Failed to retrieve expected links");
	}

	/**
	 * Tests the status result of the {@link com.revature.controllers.LinkController#getLinkById(int) getAllLinks(int)} method.
	 * 
	 * This method assumes that the {@link com.revature.entities.Link Links} being retrieved contain both a
	 * valid {@link com.revature.entities.Content Content} object and a valid {@link com.revature.entities.Module Module} object.
	 * 
	 * The expected result is a status of OK (200).
	 * @throws Exception
	 */
	@Test
	public void testGetLinkById_StatusOk() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(1, content, module, "affiliation",1);
		Mockito.when(ls.getLinkById(link.getId())).thenReturn(link);
		ResultActions result = mvc.perform(get("/links/" + link.getId()).contentType(MediaType.APPLICATION_JSON_VALUE));
		result.andExpect(status().isOk());
	}
	
	/**
	 * Tests the {@link com.revature.controllers.LinkController#getLinkById(int) getLinkById(int)} method.
	 * 
	 * This method assumes that the {@link com.revature.entities.Link Link} being retrieved contains both a
	 * valid {@link com.revature.entities.Content Content} object and a valid {@link com.revature.entities.Module Module} object.
	 * 
	 * The expected result is a Link mapped from a ResponseEntity<Link> with an id matching the test Link's id.
	 * @throws Exception
	 */
	@Test
	public void testGetLinkById_Return() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(1, content, module, "affiliation",1);
		Mockito.when(ls.getLinkById(link.getId())).thenReturn(link);
		ResultActions result = mvc.perform(get("/links/" + link.getId()).contentType(MediaType.APPLICATION_JSON_VALUE));
		Link actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Link.class);
		Assert.assertEquals(actual.getId(), link.getId(), "Failed to retrieve expected link");
	}

	/**
	 * Tests the status result of the {@link com.revature.controllers.LinkController#getSearchResults(String,String,String) getAllLinks(String, String, String)} method.
	 * 
	 * This method assumes that the search returns a valid Set<Set<Link>>.
	 * 
	 * The expected result is a status of OK (200).
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test()
	public void testGetSearchResults_StatusOk() throws Exception {
		String[] params = { "title", "format", "modules" };
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(0, content, module, "affiliation",1);
		Set<Link> allLinks = new HashSet<Link>();
		allLinks.add(link);
		Set<Set<Link>> setOfSets = new HashSet<>();
		setOfSets.add(allLinks);
		Mockito.when(ls.filter(anyString(), anyString(), any(ArrayList.class))).thenReturn(setOfSets);
		ResultActions result = mvc
				.perform(get("/links").param(params[0], content.getTitle()).param(params[1], content.getFormat())
						.param(params[2], "1"));
		result.andExpect(status().isOk());
	}
	
	/**
	 * Tests the status result of the {@link com.revature.controllers.LinkController#getSearchResults(String,String,String) getAllLinks(String, String, String)} method.
	 * 
	 * This method assumes that the search returns a valid Set<Set<Link>>.
	 * 
	 * The expected result is a Set<Set<Link>> mapped from a ResponseEntity<Link> matching the test Set<Set<Link>>.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test()
	public void testGetSearchResults_Return() throws Exception {
		String[] params = { "title", "format", "modules" };
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(0, content, module, "affiliation",1);
		Set<Link> allLinks = new HashSet<Link>();
		allLinks.add(link);
		Set<Set<Link>> setOfSets = new HashSet<>();
		setOfSets.add(allLinks);	
		Mockito.when(ls.filter(anyString(), anyString(), any(ArrayList.class))).thenReturn(setOfSets);
		ResultActions result = mvc
				.perform(get("/links").param(params[0], content.getTitle()).param(params[1], content.getFormat())
						.param(params[2], "1"));
		Set<Set<Link>> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<Set<Link>>>() {
				});
		Assert.assertEquals(actual, setOfSets, "Failed to retrieve expected links");
	}

	/**
	 * Tests the status result of the {@link com.revature.controllers.LinkController#updateLink(Link) updateLink(Link)} method.
	 * 
	 * This method assumes that the {@link com.revature.entities.Link Link} being updated contains both a
	 * valid {@link com.revature.entities.Content Content} object and a valid {@link com.revature.entities.Module Module} object.
	 * 
	 * The expected result is a status of OK (200).
	 * @throws Exception
	 */
	@Test
	public void testUpdateLink_StatusOk() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(1, content, module, "affiliation",1);
		Mockito.when(ls.updateLink(link)).thenReturn(link);
		ResultActions result = mvc.perform(put("/links/" + link.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(link)));
		result.andExpect(status().isOk());
	}
	
	/**
	 * Tests the {@link com.revature.controllers.LinkController#updateLink(Link) updateLink(Link)} method.
	 * 
	 * This method assumes that the {@link com.revature.entities.Link Link} being created contains both a
	 * valid {@link com.revature.entities.Content Content} object and a valid {@link com.revature.entities.Module Module} object.
	 * 
	 * The expected result is a Link mapped from a ResponseEntity<Link> matching the test Link.
	 * @throws Exception
	 */
	@Test
	public void testUpdateLink_Return() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(1, content, module, "affiliation",1);
		Mockito.when(ls.updateLink(link)).thenReturn(link);
		ResultActions result = mvc.perform(put("/links/" + link.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(link)));
		Link actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Link.class);
		Assert.assertEquals(actual, link, "Failed to retrieve expected link");
	}

	/**
	 * Tests the status result of the {@link com.revature.controllers.LinkController#deleteLink(Link) deleteLink(Link)} method.
	 * 
	 * This method assumes no deletion will be performed when testing the method.
	 * 
	 * The expected result is a status of OK (200).
	 * @throws Exception
	 */
	@Test
	public void testDeleteLink_StatusOk() throws Exception {
		int linkId = 1;
		Mockito.doNothing().when(ls).deleteLinkById(linkId);
		ResultActions result = mvc.perform(delete("/links/" + linkId).contentType(MediaType.APPLICATION_JSON_VALUE));
		result.andExpect(status().isOk());
	}

}