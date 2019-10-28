package com.revature.controllers;

import java.util.ArrayList;
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

import com.revature.entities.Link;
import com.revature.services.LinkService;
import com.revature.util.LogException;

/**
 * LinkController
 * Delegate different HTTP request for curriculum to corresponding {@link com.revature.service.LinkService LinkService} method
 *
 */
@CrossOrigin(origins = "*", allowCredentials = "true")
@Transactional
@RestController
@RequestMapping(value = "/links")
public class LinkController {

	@Autowired
	LinkService linkService;
	/**
	 * HTTP POST method
	 * Uses @PostMapping to create one {@link com.revature.entities.Link Link} object using the {@link com.revature.services.LinkServiceImpl#createLink(Link) createLink} method.
	 * Upon successful creation, the ResponseEntity will return status OK.
	 * @param link
	 * @return ResponseEntity<Link>
	 * @throws Exception
	 */
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Link> createLink(@RequestBody Link link) throws Exception {

		return ResponseEntity.ok(linkService.createLink(link));
	}

	/**
	 * HTTP GET method
	 * Uses @GetMapping to retrieve all {@link com.revature.entities.Link Link} objects using the {@link com.revature.services.LinkServiceImpl#getAllLinks() getAllLinks()} method.
	 * Upon successful retrieval, the ResponseEntity will return status OK.
	 * @param link
	 * @return ResponseEntity<Link>
	 */
	@GetMapping()
	public ResponseEntity<Set<Link>> getAllLinks() {
		return ResponseEntity.ok(linkService.getAllLinks());
	}

	/**
	 * HTTP GET method
	 * Uses @GetMapping to retrieve a specific {@link com.revature.entities.Link Link} object based on id using the {@link com.revature.services.LinkServiceImpl#getLinkById(int) getLinkById(int)} method.
	 * Upon successful retrieval, the ResponseEntity will return status OK.
	 * @param id
	 * @return ResponseEntity<Link>
	 */
	@GetMapping(value = "{id}")
	public ResponseEntity<Link> getLinkById(@PathVariable int id) {
		return ResponseEntity.ok(linkService.getLinkById(id));
	}

	/**
	 * HTTP GET method
	 * Uses @GetMapping to retrieve specific {@link com.revature.entities.Link Link} objects based on title, format, and modules using the {@link com.revature.services.LinkServiceImpl#filter(String, String, String) filter(String title, String format, String modules)} method.
	 * If a parameter is empty, it is not used in the filtering process.
	 * "modules" is a String in comma-separated format of integers (e.g. "1,2,3,4").
	 * Upon successful retrieval, the ResponseEntity will return status OK.
	 * @param title, format, modules
	 * @return ResponseEntity<Set<Set<Link>>>
	 */
	@LogException
	@GetMapping(params = { "title", "format", "modules" })
	public ResponseEntity<Set<Set<Link>>> getSearchResults(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "format", required = false) String format,
			@RequestParam(value = "modules", required = false) String modules) {
		ArrayList<Integer> moduleIdsList = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(modules, ",");
		while (st.hasMoreTokens()) {
			moduleIdsList.add(Integer.parseInt(st.nextToken()));
		}
		return ResponseEntity.ok(linkService.filter(title, format, moduleIdsList));
	}

	/**
	 * HTTP PUT method
	 * Uses @PutMapping to update a specific {@link com.revature.entities.Link Link} object based on id using the {@link com.revature.services.LinkServiceImpl#updateLink(Link) updateLink(Link link)} method.
	 * Upon successful update, the ResponseEntity will return status OK.
	 * @param link
	 * @return ResponseEntity<Link>
	 */
	@PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Link> updateLink(@RequestBody Link link) {
		return ResponseEntity.ok(linkService.updateLink(link));
	}

	/**
	 * HTTP DELETE method
	 * Uses @DeleteMapping to delete a specific {@link com.revature.entities.Link Link} object based on id using the {@link com.revature.services.LinkServiceImpl#deleteLinkById(int) deleteLinkById(int id)} method.
	 * Upon successful update, the ResponseEntity will return status OK.
	 * @param id
	 * @return ResponseEntity<Link>
	 */
	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deleteLink(@PathVariable int id) {
		linkService.deleteLinkById(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
