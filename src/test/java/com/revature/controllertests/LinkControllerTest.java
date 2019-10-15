package com.revature.controllertests;

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

@SpringBootTest(classes = CMSforceApplication.class)
public class LinkControllerTest {

	private MockMvc mvc;
	private ObjectMapper objMapper = new ObjectMapper();
	@InjectMocks
	private LinkController lc;
	@Mock
	private LinkService ls;

	@BeforeClass
	public void setup() {
		lc = new LinkController();
		mvc = MockMvcBuilders.standaloneSetup(lc).build();
	}

	@BeforeMethod
	public void reinitMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateLinkStatusOk() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(0, content, module, "affiliation");
		Mockito.when(ls.createLink(link)).thenReturn(link);
		ResultActions result = mvc.perform(post("/links").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(link)));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void testCreateLinkVerifyCreateLink() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(0, content, module, "affiliation");
		Mockito.when(ls.createLink(link)).thenReturn(link);
		Mockito.verify(ls).createLink(link);
	}
	
	@Test
	public void testCreateLinkTestReturn() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(0, content, module, "affiliation");
		Mockito.when(ls.createLink(link)).thenReturn(link);
		ResultActions result = mvc.perform(post("/links").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(link)));
		Link actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Link.class);
		Assert.assertEquals(actual, link, "Failed to retrieve expected link");
	}

	@Test
	public void testGetAllLinksStatusOk() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(0, content, module, "affiliation");
		Set<Link> allLinks = new HashSet<Link>();
		allLinks.add(link);
		Mockito.when(ls.getAllLinks()).thenReturn(allLinks);
		ResultActions result = mvc.perform(get("/links").contentType(MediaType.APPLICATION_JSON_VALUE));
		Set<Link> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<Link>>() {
				});
		result.andExpect(status().isOk());
		Mockito.verify(ls).getAllLinks();
		Assert.assertEquals(actual, allLinks, "Failed to retrieve expected links");
	}
	@Test
	public void testGetAllLinksStatusVerifygetAllLinks() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(0, content, module, "affiliation");
		Set<Link> allLinks = new HashSet<Link>();
		allLinks.add(link);
		Mockito.when(ls.getAllLinks()).thenReturn(allLinks);
		Mockito.verify(ls).getAllLinks();
	}
	@Test
	public void testGetAllLinksStatusTestReturn() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(0, content, module, "affiliation");
		Set<Link> allLinks = new HashSet<Link>();
		allLinks.add(link);
		Mockito.when(ls.getAllLinks()).thenReturn(allLinks);
		ResultActions result = mvc.perform(get("/links").contentType(MediaType.APPLICATION_JSON_VALUE));
		Set<Link> actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				new TypeReference<Set<Link>>() {
				});
		Assert.assertEquals(actual, allLinks, "Failed to retrieve expected links");
	}

	@Test
	public void testGetLinkByIdStatusOk() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(1, content, module, "affiliation");
		Mockito.when(ls.getLinkById(link.getId())).thenReturn(link);
		ResultActions result = mvc.perform(get("/links/" + link.getId()).contentType(MediaType.APPLICATION_JSON_VALUE));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void testGetLinkByIdVerifyGetLinkById() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(1, content, module, "affiliation");
		Mockito.when(ls.getLinkById(link.getId())).thenReturn(link);
		Mockito.verify(ls).getLinkById(link.getId());
	}
	
	@Test
	public void testGetLinkByIdTestReturn() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(1, content, module, "affiliation");
		Mockito.when(ls.getLinkById(link.getId())).thenReturn(link);
		ResultActions result = mvc.perform(get("/links/" + link.getId()).contentType(MediaType.APPLICATION_JSON_VALUE));
		Link actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Link.class);
		Assert.assertEquals(actual, link, "Failed to retrieve expected link");
	}

	@SuppressWarnings("unchecked")
	@Test()
	public void testGetSearchResultsStatusOk() throws Exception {
		String[] params = { "title", "format", "modules" };
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(0, content, module, "affiliation");
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
	
	@SuppressWarnings("unchecked")
	@Test()
	public void testGetSearchResultsVerifyFilter() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(0, content, module, "affiliation");
		Set<Link> allLinks = new HashSet<Link>();
		allLinks.add(link);
		Set<Set<Link>> setOfSets = new HashSet<>();
		setOfSets.add(allLinks);		
		Mockito.when(ls.filter(anyString(), anyString(), any(ArrayList.class))).thenReturn(setOfSets);
		Mockito.verify(ls).filter(anyString(), anyString(), any(ArrayList.class));
	}
	@SuppressWarnings("unchecked")
	@Test()
	public void testGetSearchResultsTestReturn() throws Exception {
		String[] params = { "title", "format", "modules" };
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(0, content, module, "affiliation");
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

	@Test
	public void testUpdateLinkSTatusOk() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(1, content, module, "affiliation");
		Mockito.when(ls.updateLink(link)).thenReturn(link);
		ResultActions result = mvc.perform(put("/links/" + link.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(link)));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateLinkVerifyUpdateLink() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(1, content, module, "affiliation");
		Mockito.when(ls.updateLink(link)).thenReturn(link);
		Mockito.verify(ls).updateLink(link);
	}
	
	@Test
	public void testUpdateLinkTestReturn() throws Exception {
		Content content = new Content(1, "title", "format", "description", "http://www.google.com", 1L, 1L,
				new HashSet<Link>());
		Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
				new HashSet<Module>(), new HashSet<Module>());
		Link link = new Link(1, content, module, "affiliation");
		Mockito.when(ls.updateLink(link)).thenReturn(link);
		ResultActions result = mvc.perform(put("/links/" + link.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objMapper.writeValueAsString(link)));
		Link actual = objMapper.readValue(result.andReturn().getResponse().getContentAsString(), Link.class);
		Assert.assertEquals(actual, link, "Failed to retrieve expected link");
	}

	@Test
	public void testDeleteLinkStatusOk() throws Exception {
		int linkId = 1;
		Mockito.doNothing().when(ls).deleteLinkById(linkId);
		ResultActions result = mvc.perform(delete("/links/" + linkId).contentType(MediaType.APPLICATION_JSON_VALUE));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteLinkVerifyDeleteLinkById() throws Exception {
		int linkId = 1;
		Mockito.doNothing().when(ls).deleteLinkById(linkId);
		Mockito.verify(ls).deleteLinkById(linkId);
	}
	
}