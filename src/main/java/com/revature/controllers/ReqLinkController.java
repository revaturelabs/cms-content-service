package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
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

import com.revature.entities.ReqLink;
import com.revature.services.ReqLinkService;
import com.revature.util.LogException;

@CrossOrigin(origins = "*", allowCredentials = "true")
@Transactional
@RestController
@RequestMapping(value = "/req-links")
public class ReqLinkController {

	@Autowired
	ReqLinkService reqLinkService;

	// creates one reqLink object
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReqLink> createReqLink(@RequestBody ReqLink reqLink) throws Exception {
		return ResponseEntity.ok(reqLinkService.createReqLink(reqLink));
	}

	// Returns all reqLink
	@GetMapping()
	public ResponseEntity<Set<ReqLink>> getAllReqLinks() {
		return ResponseEntity.ok(reqLinkService.getAllReqLinks());
	}

	// Returns specific reqLink
	@GetMapping(value = "{id}")
	public ResponseEntity<ReqLink> getReqLinkById(@PathVariable int id) {
		return ResponseEntity.ok(reqLinkService.getReqLinkById(id));
	}

	// This query returns a subset of reqLinks based on the values of the query
	// parameters passed in
	// If a parameter is empty, it is not used in the filtering process.
	// modules is a string in comma separated format of integers ex. "1,2,3,4"
	@LogException
	@GetMapping(params = { "title", "format", "modules" })
	public ResponseEntity<Set<Set<ReqLink>>> getSearchResults(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "format", required = false) String format,
			@RequestParam(value = "modules", required = false) String modules) {
		List<Integer> moduleIdsList = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(modules, ",");
		while (st.hasMoreTokens()) {
			moduleIdsList.add(Integer.parseInt(st.nextToken()));
		}
		return ResponseEntity.ok(reqLinkService.filter(title, format, moduleIdsList));
	}

	// update a reqLink
	@PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReqLink> updatReqLink(@RequestBody ReqLink reqLink) {
		return ResponseEntity.ok(reqLinkService.updateReqLink(reqLink));
	}

	// deletes a single reqLink
	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deleteReqLink(@PathVariable int id) {
		reqLinkService.deleteReqLinkById(id);
		return ResponseEntity.status(HttpStatus.OK).body("ReqLink Deleted");
	}

}
