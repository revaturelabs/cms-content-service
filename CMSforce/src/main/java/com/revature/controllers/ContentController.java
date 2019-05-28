package com.revature.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Content;
import com.revature.services.ContentService;
import com.revature.services.ModuleService;
import com.revature.util.ContentWrapper;

@CrossOrigin(origins = "*", allowCredentials="true")
@RestController
public class ContentController {

	@Autowired
	ContentService contentService;
	
	@Autowired
	ModuleService moduleService;
	
	@RequestMapping(value = "/content", method = RequestMethod.POST, produces  = MediaType.APPLICATION_JSON_VALUE) 
	public Content createContent(@RequestBody Content content ){
		
		content = contentService.createContent(content);
		return content;
	}
	
	// Returns a set of contents 
	// Finds all the content in the repository 
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public Set<Content> getAllContent() {
		return contentService.getAllContent();
	}
	
	// Returns content depending on what ID is passed in
	// Uses the findById method in the repository
	@RequestMapping(value = "/content/{id}", method = RequestMethod.GET)
	public Content getContentById(@PathVariable int id) {
		return contentService.getContentById(id);
	}
	

}
