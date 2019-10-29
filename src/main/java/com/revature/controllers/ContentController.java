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
	/**
	 * This method creates content from a Post request sent to /content. The Posted data should be a JSONContent object.
	 * @param jsonContent the JSON data that represents a content.
	 * @return A ResponseEntity with status and JSONContent.
	 * @throws Exception
	 */
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
	/**
	 * This method creates links for a Content by passing in the id of the content in the path.
	 * @param links The links for the content.
	 * @param id The id of the content.
	 * @return A ResponseEntity with status and a list of links.
	 */
	@PostMapping(value = "/{id}/links", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Link>> createLinks(@RequestBody List<Link> links, @PathVariable int id) {
		return ResponseEntity.ok(contentService.createLinksByContentId(id, links));
	}

	// Returns all Content
	/**
	 * This method responds to Get requests on /content and returns all available contents.
	 * @return A ResponseEntity with a status and a JSONContent.
	 */
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
	/**
	 * This method returns JSONContent based on an id passed in with the path.
	 * @param id The path variable id of the content.
	 * @return A ResponseEntity with a status and a JSONContent.
	 */
	@GetMapping(value = "{id}")
	public ResponseEntity<JSONContent> getContentById(@PathVariable int id) {
		Content content = contentService.getContentById(id);
		JSONContent jc = contentToJSONContent(content);
		return ResponseEntity.ok(jc);
	}

	// return all links attached to a given content
	/**
	 * This method intends to get content links based on a path variable id.
	 * @param id The path variable id of the content.
	 * @return A ResponseEntity with a status and a set of Links.
	 */
	@GetMapping("/{id}/links")
	public ResponseEntity<Set<Link>> getLinksByContentId(@PathVariable int id) {
		return ResponseEntity.ok(contentService.getLinksByContentId(id));
	}

	// This query returns a subset of Content based on the values of the query
	// parameters passed in
	// If a parameter is empty, it is not used in the filtering process.
	// modules is a string in comma separated format of integers ex. "1,2,3,4"
	
	//Change this to tokenize the format input the same way we do modules
	/**
	 * A method to filter through contents in an effort to return relevant search results
	 * @param title Content title.
	 * @param formats Content format (Code,Document,Powerpoint).
	 * @param modules Subject Matter
	 * @param curriculum Group content belongs to
	 * @return A ResponseEntity with a Set of JSONContents and a status. 
	 */
	@LogException
	@GetMapping(params = { "title", "format", "modules"})
	public ResponseEntity<Set<JSONContent>> getSearchResults(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "format", required = false) List<String> formats,
			@RequestParam(value = "modules", required = false) List<Integer> modules,
			@RequestParam(value = "curriculum", required = false) List<Integer> curriculum) {
		
		Set<Content> contentSet = searchService.filter(title, formats, modules, curriculum);
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
	
	/**
	 * A method to update the links associated with a content by id given in path.
	 * @param links The links to add to a content.
	 * @param id The path variable id of a content.
	 * @return A ResponseEntity with a status and list of links added.
	 */
	@PutMapping(value = "{id}/links", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Link>> updateLinks(@RequestBody List<Link> links, @PathVariable int id) {
		return ResponseEntity.ok(contentService.updateLinksByContentId(id, links));
	}

	// deletes a single Content
	/**
	 * A method to delete a content from the database using a path id.
	 * @param id The path variable id for a content to be deleted.
	 * @return A ResponseEntity with a status and an empty body
	 */
	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deleteContent(@PathVariable int id) {
		Content content = contentService.getContentById(id);
		contentService.deleteContent(content);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	/**
	 * A convenience method for transforming a Content object to a JSONContent object.
	 * @param content The content to be transformed.
	 * @return The JSONContent representation of a Content.
	 */
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
	/**
	 * A convenience method for transforming a JSONContent back to a Content.
	 * @param jsonContent The JSONContent to transform.
	 * @return The Content representation of the JSONContent.
	 */
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
