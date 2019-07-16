package com.revature.controllertests;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.services.ContentService;

import aj.org.objectweb.asm.Attribute;
import net.minidev.json.parser.JSONParser;

import org.mockito.Mockito;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.revature.cmsforce.CMSforceApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
class ContentControllerTest {

	@Autowired
    private MockMvc mockmvc;
	
	@MockBean
	ContentService cs;
	
	@Autowired
	Gson gson;
	
	@Test
	void createContent() throws Exception {
		String JSON = "{\"id\":88,\"title\":\"Dadaism\",\"format\":\"Ironing Board\",\"description\":\"Anti-art\",\"url\":\"www.dadaism.test\",\"links\":[{\"id\":12,\"contentId\":88,\"moduleId\":3,\"affiliation\":\"relaventTo\"}]}";
		Content cont = gson.fromJson(JSON, Content.class);
		Mockito.when(cs.createContent(cont)).thenReturn(cont);
		ResultActions resultActions = mockmvc.perform(post("/content").contentType(MediaType.APPLICATION_JSON_VALUE).content(JSON));
		resultActions.andExpect(status().isOk());
		String JOH = gson.toJson(cont);
		resultActions.andExpect(content().string(JOH));
	}
		
	
	
	@Test
	void getAllContent() throws Exception {

		Mockito.when(cs.getAllContent()).thenReturn(this.fakeContents());		
		ResultActions resultActions = mockmvc.perform(get("/content"));	
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(content().json("[{\"id\":88,\"title\":\"Dadaism\",\"format\":\"Ironing Board\",\"description\":\"Anti-art\",\"url\":\"www.dadaism.test\",\"links\":[{\"id\":12,\"contentId\":88,\"moduleId\":3,\"affiliation\":\"relaventTo\"}]},{\"id\":99,\"title\":\"Impressionism\",\"format\":\"painting\",\"description\":\"A work that gets the essence of an image\",\"url\":\"www.impression.test\",\"links\":[{\"id\":12,\"contentId\":88,\"moduleId\":3,\"affiliation\":\"relaventTo\"}]}]"));
//		System.err.println(resultActions.andReturn().getResponse().getContentAsString());
		
	} 
	
	@Test
	void getContentById() throws Exception {
		
		Mockito.when(cs.getContentById(88)).thenReturn(this.fakeContents().iterator().next());
		ResultActions resultActions = mockmvc.perform(get("/content/88"));
		resultActions.andExpect(status().isOk());
		
	}
	
	private Set<Content> fakeContents(){
		
 		Module mod1 = new Module(1, "Fakey McFake", 0, null);
		Module mod2 = new Module(2, "Mocky McMock", 0, null);
		Module mod3 = new Module(3, "Pastiche McPastiche", 0, null);
		
		Set<Link> links = new HashSet<Link>();
		
		Link link1 = new Link(10,99,mod1.getId(),"relaventTo");
		Link link2 = new Link(11,99,mod2.getId(),"relaventTo");
		
		links.add(link1);
		links.add(link2);
		
		Content content = new Content(99,
				"Impressionism",
				"painting",
				"A work that gets the essence of an image",
				"www.impression.test",
				links);
				
		links.clear();	
		
		
		Link link3 = new Link(12,88,mod3.getId(),"relaventTo");
		links.add(link3);
		
		Content content2 = new Content(88,
				"Dadaism",
				"Ironing Board",
				"Anti-art",
				"www.dadaism.test",
				links);
		
		Set <Content> contents = new HashSet<Content>();
		contents.add(content);
		contents.add(content2);
		
		return contents;
		
	}

}
