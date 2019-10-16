package com.revature.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

import com.revature.JSONEntities.JSONContent;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.services.ContentService;
import com.revature.services.SearchService;
import com.revature.util.LogException;

@CrossOrigin(origins = "*", allowCredentials = "true")
@Transactional
@RestController
@RequestMapping(value = "/content")
public class ContentController {

	@Autowired
	ContentService contentService;

	@Autowired
	SearchService searchService;

	// creates one content object
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONContent> createContent(@RequestBody JSONContent jsonContent) throws Exception {
		List<Link> links = new ArrayList<Link>();
		Content content = jsonContentToContent(jsonContent);
		content = contentService.createContent(content);
		jsonContent.setId(content.getId());
		if (jsonContent.getLinks() != null) {
			for (Link link : jsonContent.getLinks()) {
				link.setContent(content);
				links.add(link);
			}
			contentService.createLinksByContentId(content.getId(), links);
		}
		return ResponseEntity.ok(jsonContent);
	}

	@PostMapping(value = "/{id}/links", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Link>> createLinks(@RequestBody List<Link> links, @PathVariable int id) {
		return ResponseEntity.ok(contentService.createLinksByContentId(id, links));
	}

	// Returns all Content
	@GetMapping()
	public ResponseEntity<Set<JSONContent>> getAllContent() {
		Set<Content> contentSet = contentService.getAllContent();
		Set<JSONContent> jsonContent = new HashSet<JSONContent>();
		for (Content content : contentSet) {
			JSONContent jc = contentToJSONContent(content);
			jsonContent.add(jc);
		}
		return ResponseEntity.ok(jsonContent);
	}

	// Returns specific content
	@GetMapping(value = "{id}")
	public ResponseEntity<JSONContent> getContentById(@PathVariable int id) {
		Content content = contentService.getContentById(id);
		JSONContent jc = contentToJSONContent(content);
		return ResponseEntity.ok(jc);
	}

	// return all links attached to a given content
	@GetMapping("/{id}/links")
	public ResponseEntity<Set<Link>> getLinksByContentId(@PathVariable int id) {
		return ResponseEntity.ok(contentService.getLinksByContentId(id));
	}

	// This query returns a subset of Content based on the values of the query
	// parameters passed in
	// If a parameter is empty, it is not used in the filtering process.
	// modules is a string in comma separated format of integers ex. "1,2,3,4"
	
	//Change this to tokenize the format input the same way we do modules
	@LogException
	@GetMapping(params = { "title", "format", "modules" })
	public ResponseEntity<Set<JSONContent>> getSearchResults(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "format", required = false) String format,
			@RequestParam(value = "modules", required = false) String modules) {
		ArrayList<Integer> moduleIdsList = new ArrayList<Integer>();
		ArrayList<String> formatList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(modules, ",");
		while(st.hasMoreTokens()) {
			moduleIdsList.add(Integer.parseInt(st.nextToken()));
		}
		
		StringTokenizer ft = new StringTokenizer(format, ",");
		while (ft.hasMoreTokens()) {
			formatList.add(ft.nextToken());
		}

		Set<Content> contentSet = searchService.filter(title, formatList, moduleIdsList);
		Set<JSONContent> jsonContent = new HashSet<JSONContent>();
		for (Content content : contentSet) {
			JSONContent jc = contentToJSONContent(content);
			jsonContent.add(jc);
		}
		return ResponseEntity.ok(jsonContent);
	}

	/**
	 * Description - PUT request for updating content, updates a content in the
	 * content repository
	 * 
	 * @param newContent - the updated content received from the client
	 * @return - the updated content
	 * @throws - NullPointerException - if the newContent is null or the content
	 *           doesn't already exist in content repo.
	 */
	@PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Content> updateContent(@RequestBody Content newContent) {
		return ResponseEntity.ok(contentService.updateContent(newContent));
	}

	@PutMapping(value = "{id}/links", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Link>> updateLinks(@RequestBody List<Link> links, @PathVariable int id) {
		return ResponseEntity.ok(contentService.updateLinksByContentId(id, links));
	}

	// deletes a single Content
	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deleteContent(@PathVariable int id) {
		Content content = contentService.getContentById(id);
		contentService.deleteContent(content);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

	public JSONContent contentToJSONContent(Content content) {
		JSONContent jsonContent = new JSONContent();
		jsonContent.setId(content.getId());
		jsonContent.setTitle(content.getTitle());
		jsonContent.setFormat(content.getFormat());
		jsonContent.setDescription(content.getDescription());
		jsonContent.setUrl(content.getUrl());
		jsonContent.setDateCreated(content.getDateCreated());
		jsonContent.setLastModified(content.getLastModified());
		jsonContent.setLinks(content.getLinks());
		return jsonContent;
	}

	public Content jsonContentToContent(JSONContent jsonContent) {
		Content content = new Content();
		content.setId(jsonContent.getId());
		content.setTitle(jsonContent.getTitle());
		content.setFormat(jsonContent.getFormat());
		content.setDescription(jsonContent.getDescription());
		content.setUrl(jsonContent.getUrl());
		content.setDateCreated(jsonContent.getDateCreated());
		content.setLastModified(jsonContent.getLastModified());
		content.setLinks(jsonContent.getLinks());
		return content;
	}
}
