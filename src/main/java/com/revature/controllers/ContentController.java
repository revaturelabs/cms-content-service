package com.revature.controllers;

import java.util.ArrayList;
/**
 * For documentation on the controllers check out some documentation on swaggerhub:
 * https://app.swaggerhub.com/apis-docs/pacquito/CMS-Controllers/0.1
 */
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.revature.entities.Link;
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
	
	//creates one content object
	@PostMapping(produces  = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Content> createContent(@RequestBody Content content ) throws Exception{
		return ResponseEntity.ok(contentService.createContent(content));
	}
	
	// Returns all Content
	@GetMapping()
	public ResponseEntity<Set<Content>> getAllContent() {
		for (Content content : contentService.getAllContent()) {
			System.out.println("Link: " + content.getLinks());
		}
		return ResponseEntity.ok(contentService.getAllContent());
	}
	
	// Returns specific content
	@GetMapping(value="{id}")
	public ResponseEntity<Content> getContentById(@PathVariable int id) {
		return ResponseEntity.ok(contentService.getContentById(id));
	}
	
	//return all links attached to a given content
	@GetMapping("/{id}/links")
	public ResponseEntity<Set<Link>> getLinksByContentId(@PathVariable int id) {
		return ResponseEntity.ok(contentService.getLinksByContentId(id));
	}
	
	
	//This query returns a subset of Content based on the values of the query parameters passed in
	//If a parameter is empty, it is not used in the filtering process.
	//modules is a string in comma separated format of integers ex. "1,2,3,4"
	@LogException
	@GetMapping (params= {"title", "format", "modules"})
	public ResponseEntity<Set<Content>> getSearchResults(
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="format", required=false) String format, 
			@RequestParam(value="modules", required=false) String modules
		) {
		ArrayList<Integer> moduleIdsList = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(modules, ",");
		while (st.hasMoreTokens()) {
			moduleIdsList.add(Integer.parseInt(st.nextToken()));
		}
		return ResponseEntity.ok(searchService.filter(title, format, moduleIdsList));
	}
	
	/**
	 * Description - PUT request for updating content, updates a content in the content repository
	 * @param newContent - the updated content received from the client
	 * @return - the updated content
	 * @throws - NullPointerException - if the newContent is null or the content doesn't already exist in content repo.
	 */
	@PutMapping(value="{id}", produces  = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Content> updateContent(@RequestBody Content newContent) {
		return ResponseEntity.ok(contentService.updateContent(newContent));
	}
	
	//deletes a single Content
	@DeleteMapping(value="{id}")
	public ResponseEntity<String> deleteContent(@PathVariable int id) {
		Content content = contentService.getContentById(id);
		contentService.deleteContent(content);
		return ResponseEntity.status(HttpStatus.OK).body("Content Deleted");
	}
}
