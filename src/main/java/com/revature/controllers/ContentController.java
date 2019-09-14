package com.revature.controllers;

import java.util.ArrayList;
/**
 * For documentation on the controllers check out some documentation on swaggerhub:
 * https://app.swaggerhub.com/apis-docs/pacquito/CMS-Controllers/0.1
 */
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Content;
import com.revature.services.ContentService;
import com.revature.services.SearchService;
import com.revature.util.LogException;

@CrossOrigin(origins = "*", allowCredentials="true")
@Transactional
@RestController
@RequestMapping(value="/content")
public class ContentController {

	@Autowired
	ContentService contentService;
	
	@Autowired
	SearchService searchService;
	
	@PostMapping(produces  = MediaType.APPLICATION_JSON_VALUE)
	public Content createContent(@RequestBody Content content ) throws Exception{
		
		content = contentService.createContent(content);
		return content;
	}
	
	// Returns a set of contents 
	// Finds all the content in the repository 
	@GetMapping()
	public Set<Content> getAllContent() {
		return (Set<Content>) contentService.getAllContent();
	}
	
	// Returns content depending on what ID is passed in
	// Uses the findById method in the repository
	@GetMapping(value="{id}")
	public Content getContentById(@PathVariable int id) {
		return contentService.getContentById(id);
	}
	
	@LogException
	@GetMapping /* (params= {"title", "format", "modules"}) */
	public Set<Content> getSearchResults(
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="format", required=false) String format, 
			@RequestParam(value="modules", required=false) String modules
		) {
		ArrayList<Integer> moduleIdsList = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(modules, ",");
		while (st.hasMoreTokens()) {
			moduleIdsList.add(Integer.parseInt(st.nextToken()));
		}
		return searchService.filter(title, format, moduleIdsList);
	}
	
	/**
	 * Description - PUT request for updating content, updates a content in the content repository
	 * @param newContent - the updated content received from the client
	 * @return - the updated content
	 * @throws - NullPointerException - if the newContent is null or the content doesn't already exist in content repo.
	 */
	@PutMapping(value="{id}", produces  = MediaType.APPLICATION_JSON_VALUE)
	public Content updateContent(@RequestBody Content newContent) {
		return contentService.updateContent(newContent);
	}
	
	@DeleteMapping(value="{id}")
	public void deleteContent(@PathVariable int id) {
		Content content = contentService.getContentById(id);
		contentService.deleteContent(content);
	}
}
