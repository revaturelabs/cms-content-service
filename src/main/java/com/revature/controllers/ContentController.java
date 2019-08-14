package com.revature.controllers;

/**
 * For documentation on the controllers check out some documentation on swaggerhub:
 * https://app.swaggerhub.com/apis-docs/pacquito/CMS-Controllers/0.1
 */
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Content;
import com.revature.services.ContentService;
import com.revature.services.ModuleService;

@CrossOrigin(origins = "*", allowCredentials="true")
@Transactional
@RestController
public class ContentController {

	@Autowired
	ContentService contentService;
	
	@Autowired
	ModuleService moduleService;
	
	@RequestMapping(value = "/content", method = RequestMethod.POST, produces  = MediaType.APPLICATION_JSON_VALUE) 
	public Content createContent(@RequestBody Content content ) throws Exception{
		
		content = contentService.createContent(content);
		return content;
	}
	
	// Returns a set of contents 
	// Finds all the content in the repository 
	@GetMapping("/content")
	public Set<Content> getAllContent() {
		return (Set<Content>) contentService.getAllContent();
	}
	
	// Returns content depending on what ID is passed in
	// Uses the findById method in the repository
	@GetMapping("/content/{id}")
	public Content getContentById(@PathVariable int id) {
		return contentService.getContentById(id);
	}
	
	/**
	 * Description - PUT request for updating content, updates a content in the content repository
	 * @param newContent - the updated content received from the client
	 * @return - the updated content
	 * @throws - NullPointerException - if the newContent is null or the content doesn't already exist in content repo.
	 */
	@RequestMapping(value = "/content", method = RequestMethod.PUT, produces  = MediaType.APPLICATION_JSON_VALUE)
	public Content updateContent(@RequestBody Content newContent) {
		return contentService.updateContent(newContent);
	}
	
	@DeleteMapping("/content/{id}")
	public void deleteContent(@PathVariable int id) {
		Content content = contentService.getContentById(id);
		contentService.deleteContent(content);
	}
}
