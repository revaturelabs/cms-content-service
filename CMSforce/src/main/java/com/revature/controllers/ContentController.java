package com.revature.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Content;
import com.revature.services.ContentService;
import com.revature.services.ModuleService;
import com.revature.util.LogCreation;

@CrossOrigin(origins = "*", allowCredentials="true")
@Transactional
@RestController
public class ContentController {

	@Autowired
	ContentService contentService;
	
	@Autowired
	ModuleService moduleService;
	
	@LogCreation
	@RequestMapping(value = "/content", method = RequestMethod.POST, produces  = MediaType.APPLICATION_JSON_VALUE) 
	public Content createContent(@RequestBody Content content ) throws Exception{
		
		content = contentService.createContent(content);
		return content;
	}
	
	// Returns a set of contents 
	// Finds all the content in the repository 
	@GetMapping("/content")
	public Set<Content> getAllContent() {
		return contentService.getAllContent();
	}
	
	// Returns content depending on what ID is passed in
	// Uses the findById method in the repository
	@GetMapping("/content/{id}")
	public Content getContentById(@PathVariable int id) {
		return contentService.getContentById(id);
	}
	



	// Updates a specific content in the table 
	// First assures that the content exists by its ID and if it does
	// it will update that content
	// @RequestMapping(value = "/content", method = RequestMethod.PUT)
	// public Content updateContent(@RequestBody Content inputContent) {
	// 	return contentService.updateContent(inputContent);
	// }

}
