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

    /*
     * Test that when we hit the /links endpoint with a
     * POST request, our servlet and controller triggers
     * the method that adds a link object to the database
     * (with the correct arguments), and that the returned 
     * value makes it back to the request source.
     * @test throws Exception - if http request fails
     */
    @Test
    public void testCreateLink() throws Exception
    {
    	//Define Variables
        Content content = new Content(1, "title", "format", "description", "http://www.google.com",
                1L, 1L, new HashSet<Link>());
        Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
                new HashSet<Module>(), new HashSet<Module>());
        Link link = new Link(0, content, module, "affiliation");
        
        //Mock LinkService.createLink(link) method to return 'link' when 
        //called.
        Mockito.when(ls.createLink(link)).thenReturn(link);
        
        //Make request to servlet to see if it hits the controller.
        ResultActions result = mvc.perform( post("/links")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objMapper.writeValueAsString(link)));
        //Format returned value from POST request to a Link object.
        Link actual = objMapper.readValue(result.andReturn().getResponse()
                .getContentAsString(), Link.class);
        //Check that the status of the POST request is ok.
        result.andExpect(status().isOk());
        //Verify that the LinkService.createLink('link') method is called
        //once with an argument of 'link' passed in.
        Mockito.verify(ls).createLink(link);
        
        Assert.assertEquals(actual, link, "Failed to retrieve expected link");
    }
    
    /*
     * Test that when we hit the "/links" endpoint with a GET
     * request, our servlet and controller triggers the method that 
     * grabs all Links from the database (with the correct arguments), 
     * And that the value that is returned makes it back to the 
     * request source.
     * @test throws Exception - if http request fails
     */
    @Test
    public void testGetAllLinks() throws Exception
    {
    	//Define Vars
        Content content = new Content(1, "title", "format", "description", "http://www.google.com",
                1L, 1L, new HashSet<Link>());
        Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
                new HashSet<Module>(), new HashSet<Module>());
        Link link = new Link(0, content, module, "affiliation");
        Set<Link> allLinks = new HashSet<Link>();
        allLinks.add(link);

        //Mock LinkService.getAllLinks() to return "allLinks" when called.
        Mockito.when(ls.getAllLinks()).thenReturn(allLinks);

        //Make Get Request to servlet at "/links" endpoint.
        ResultActions result = mvc.perform( get("/links")
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        //Format result from GET request 
        Set<Link> actual = objMapper.readValue(result.andReturn().getResponse()
                .getContentAsString(), new TypeReference<Set<Link>>() { });

        //Affirm that POST request returns ok status
        result.andExpect(status().isOk());
        //Verify that LinkService.getAllLinks() gets called once with no arguments.
        Mockito.verify(ls).getAllLinks();
        //Make sure that the the links we got back from our request matches the 
        //links our Mocked method returned.
        Assert.assertEquals(actual, allLinks, "Failed to retrieve expected links");
    }

    /*
     * Test that when we hit the "/links/{id}" endpoint with a GET
     * request, our servlet and controller triggers the method that 
     * grabs a Links from the database based on its id 
     * (with the correct arguments), and that the value that is 
     * returned makes it back to the request source.
     * @test throws Exception - if http request fails
     */
    @Test
    public void testGetLinkById() throws Exception
    {
    	//Define Variables
        Content content = new Content(1, "title", "format", "description", "http://www.google.com",
                1L, 1L, new HashSet<Link>());
        Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
                new HashSet<Module>(), new HashSet<Module>());
        Link link = new Link(1, content, module, "affiliation");
        
        //Mock ListService.getLinkById(id) to return 'link' when called.
        Mockito.when(ls.getLinkById(link.getId())).thenReturn(link);

        //Make GET request to servlet to see if it reaches the controller.
        ResultActions result = mvc.perform( get("/links/" + link.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        //Format results of GET request into a Link object.
        Link actual = objMapper.readValue(result.andReturn().getResponse()
                .getContentAsString(), Link.class);

        //Affirm that the status of the GET request is ok.
        result.andExpect(status().isOk());
        //verify that LinkService.getLinkByID() is called with 
        //'link.getId()' passed in as an argument.
        Mockito.verify(ls).getLinkById(link.getId());
        //check that the object returned when our Service Method is called,
        // is the value returned by the GET request.
        Assert.assertEquals(actual, link, "Failed to retrieve expected link");
    }
    //Check controller for details
    @Test(enabled=false)
    public void testGetSearchResults()
    {
        // Needs implementation
    }

    
    /*
     * Test that when we hit the "/links/{id}" endpoint 
     * with a PUT request, our servlet and controller 
     * triggers the service method that updates a Link
     * object to the db (with the correct arguments),
     * and that the returned value makes it back to 
     * the request source.
     * @test throws Exception - if http request fails
     */
    @Test
    public void testUpdateLink() throws Exception
    {
    	//Define Vars
        Content content = new Content(1, "title", "format", "description", "http://www.google.com",
                1L, 1L, new HashSet<Link>());
        Module module = new Module(1, "test_module", 1L, new HashSet<Link>(), new HashSet<ReqLink>(),
                new HashSet<Module>(), new HashSet<Module>());
        Link link = new Link(1, content, module, "affiliation");
        
        
        //Mock linkService.updateLink(link)method to return 'link' when called.
        Mockito.when(ls.updateLink(link)).thenReturn(link);

        //Make request to servlet to see if it makes it to controller.
        ResultActions result = mvc.perform( put("/links/" + link.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objMapper.writeValueAsString(link)));
        //format result from request to servlet into a Link object.
        Link actual = objMapper.readValue(result.andReturn().getResponse()
                .getContentAsString(), Link.class);

        //The Put request should return an 'ok' status.
        result.andExpect(status().isOk());
        // Verify that ls.updateLink(link) executes once, and contains
        	//this 'link' as an argument.
        	//On Failure it prints out what it was expecting, and what
        	//it got as an argument.
        Mockito.verify(ls).updateLink(link);
        Assert.assertEquals(actual, link, "Failed to retrieve expected link");
    }
    
    /*
     * Test that when we hit the "/links/{id}" endpoint
     * with a DELETE request, the servlet and controller
     * triggers the appropriate service method that deletes
     * a link from the db, and that the method is called with
     * the correct arguments.
     */
    @Test
    public void testDeleteLink() throws Exception
    {
    	//Define Variables
        int linkId = 1;
        //Mock the LinkService.deleteLkinkById(id) method to do nothing when called.
        Mockito.doNothing().when(ls).deleteLinkById(linkId);
        //Hit servlet endpoint with a DELETE request.
        ResultActions result = mvc.perform( delete("/links/" + linkId)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        //Affirm that the Delete Request returns with status of OK
        result.andExpect(status().isOk());
        //Verify that the LinkService.deleteLinkById(id) method is called
        //with the value 'linkId' as an argument.
        Mockito.verify(ls).deleteLinkById(linkId);
        
    }
}