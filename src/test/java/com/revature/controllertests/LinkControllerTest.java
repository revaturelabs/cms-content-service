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

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.*;

@SpringBootTest(classes = CMSforceApplication.class)
public class LinkControllerTest
{

    private MockMvc mvc;
    private ObjectMapper objMapper = new ObjectMapper();
    @InjectMocks
    private LinkController lc;
    @Mock
    private LinkService ls;

    @BeforeClass
    public void setup()
    {
        lc = new LinkController();
        mvc = MockMvcBuilders.standaloneSetup(lc).build();
    }

    @BeforeMethod
    public void reinitMocks()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateLink() throws Exception
    {
        Content content = new Content(1, "title", "format", "description", "http://www.google.com",
                1L, 1L, new HashSet<Link>());
        Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
                new HashSet<Module>(), new HashSet<Module>());
        Link link = new Link(0, content, module, "affiliation",0);
        Mockito.when(ls.createLink(link)).thenReturn(link);

        ResultActions result = mvc.perform( post("/links")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objMapper.writeValueAsString(link)));

        Link actual = objMapper.readValue(result.andReturn().getResponse()
                .getContentAsString(), Link.class);

        result.andExpect(status().isOk());
        Mockito.verify(ls).createLink(link);
        Assert.assertEquals(actual, link, "Failed to retrieve expected link");
    }

    @Test
    public void testGetAllLinks() throws Exception
    {
        Content content = new Content(1, "title", "format", "description", "http://www.google.com",
                1L, 1L, new HashSet<Link>());
        Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
                new HashSet<Module>(), new HashSet<Module>());
        Link link = new Link(0, content, module, "affiliation",0);
        Set<Link> allLinks = new HashSet<Link>();
        allLinks.add(link);

        Mockito.when(ls.getAllLinks()).thenReturn(allLinks);

        ResultActions result = mvc.perform( get("/links")
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        Set<Link> actual = objMapper.readValue(result.andReturn().getResponse()
                .getContentAsString(), new TypeReference<Set<Link>>() { });

        result.andExpect(status().isOk());
        Mockito.verify(ls).getAllLinks();
        Assert.assertEquals(actual, allLinks, "Failed to retrieve expected links");
    }

    @Test
    public void testGetLinkById() throws Exception
    {
        Content content = new Content(1, "title", "format", "description", "http://www.google.com",
                1L, 1L, new HashSet<Link>());
        Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
                new HashSet<Module>(), new HashSet<Module>());
        Link link = new Link(1, content, module, "affiliation",0);
        Mockito.when(ls.getLinkById(link.getId())).thenReturn(link);

        ResultActions result = mvc.perform( get("/links/" + link.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        Link actual = objMapper.readValue(result.andReturn().getResponse()
                .getContentAsString(), Link.class);

        result.andExpect(status().isOk());
        Mockito.verify(ls).getLinkById(link.getId());
        Assert.assertEquals(actual, link, "Failed to retrieve expected link");
    }

    @Test(enabled=false)
    public void testGetSearchResults()
    {
        // Needs implementation
    }

    @Test
    public void testUpdateLink() throws Exception
    {
        Content content = new Content(1, "title", "format", "description", "http://www.google.com",
                1L, 1L, new HashSet<Link>());
        Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
                new HashSet<Module>(), new HashSet<Module>());
        Link link = new Link(1, content, module, "affiliation",0);
        Mockito.when(ls.updateLink(link)).thenReturn(link);

        ResultActions result = mvc.perform( put("/links/" + link.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objMapper.writeValueAsString(link)));

        Link actual = objMapper.readValue(result.andReturn().getResponse()
                .getContentAsString(), Link.class);

        result.andExpect(status().isOk());
        Mockito.verify(ls).updateLink(link);
        Assert.assertEquals(actual, link, "Failed to retrieve expected link");
    }

    @Test
    public void testDeleteLink() throws Exception
    {
        int linkId = 1;
        Mockito.doNothing().when(ls).deleteLinkById(linkId);

        ResultActions result = mvc.perform( delete("/links/" + linkId)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        result.andExpect(status().isOk());
        Mockito.verify(ls).deleteLinkById(linkId);
    }
}