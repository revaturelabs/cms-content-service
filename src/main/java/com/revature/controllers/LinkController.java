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

@CrossOrigin(origins = "*", allowCredentials = "true")
@Transactional
@RestController
@RequestMapping(value = "/links")

public class LinkController {

	@Autowired
	LinkService linkService;

	// creates one Link object
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Link> createLink(@RequestBody Link link) throws Exception {
		System.out.println("LinkController.createLink()");
		return ResponseEntity.ok(linkService.createLink(link));
	}

	// Returns all links
	@GetMapping()
	public ResponseEntity<Set<Link>> getAllLinks() {
		return ResponseEntity.ok(linkService.getAllLinks());
	}

	// Returns specific link
	@GetMapping(value = "{id}")
	public ResponseEntity<Link> getLinkById(@PathVariable int id) {
		return ResponseEntity.ok(linkService.getLinkById(id));
	}

	// This query returns a subset of Links based on the values of the query
	// parameters passed in
	// If a parameter is empty, it is not used in the filtering process.
	// modules is a string in comma separated format of integers ex. "1,2,3,4"
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

	// update a link
	@PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Link> updateLink(@RequestBody Link link) {
		return ResponseEntity.ok(linkService.updateLink(link));
	}

	// deletes a single Link
	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deleteLink(@PathVariable int id) {
		linkService.deleteLinkById(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
