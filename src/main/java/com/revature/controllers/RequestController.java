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

import com.revature.JSONEntities.JSONRequest;
import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.services.RequestService;
import com.revature.services.SearchService;
import com.revature.util.LogException;

@CrossOrigin(origins = "*", allowCredentials = "true")
@Transactional
@RestController
@RequestMapping(value = "/requests")
public class RequestController {

	@Autowired
	RequestService requestService;

	@Autowired
	SearchService searchService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONRequest> createRequest(@RequestBody JSONRequest jsonRequest) throws Exception {
		//return ResponseEntity.ok(requestService.createRequest(request));
		List<ReqLink> reqLinks = new ArrayList<ReqLink>();
		Request request = jsonRequestToRequest(jsonRequest);
		request = requestService.createRequest(request);
		for (ReqLink reqLink : jsonRequest.getReqLinks()) {
			reqLink.setRequest(request);
			reqLinks.add(reqLink);
		}
		requestService.createReqLinksByRequestId(request.getId(), reqLinks);
		return ResponseEntity.ok(jsonRequest);
	}

	@PostMapping(value = "/{id}/req-links", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReqLink>> createReqLinks(@RequestBody List<ReqLink> reqLinks, @PathVariable int id) {
		return ResponseEntity.ok(requestService.createReqLinksByRequestId(id, reqLinks));
	}

	@GetMapping()
	public ResponseEntity<Set<JSONRequest>> getAllRequest() {
		Set<Request> requests = requestService.getAllRequests();
		Set<JSONRequest> jsonRequests = new HashSet<JSONRequest>();
		for (Request request : requests) {
			JSONRequest jr = requestToJSONRequest(request);
			jsonRequests.add(jr);
		}
		return ResponseEntity.ok(jsonRequests);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<JSONRequest> getRequestById(@PathVariable int id) {
		Request request = requestService.getRequestsById(id);
		JSONRequest jr = requestToJSONRequest(request);
		return ResponseEntity.ok(jr);
	}

	// return all links attached to a given request
	@GetMapping("/{id}/req-links")
	public ResponseEntity<List<ReqLink>> getReqLinksByRequestId(@PathVariable int id) {
		return ResponseEntity.ok(requestService.getReqLinksByRequestId(id));
	}

	// This query returns a subset of Request based on the values of the query
	// parameters passed in
	// If a parameter is empty, it is not used in the filtering process.
	// modules is a string in comma separated format of integers ex. "1,2,3,4"
	@LogException
	@GetMapping(params = { "title", "format", "modules" })
	public ResponseEntity<Set<JSONRequest>> getSearchResults(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "format", required = false) String format,
			@RequestParam(value = "modules", required = false) String modules) {
		ArrayList<Integer> moduleIdsList = new ArrayList<Integer>();
		ArrayList<String> formatList = new ArrayList<String>();
		if (modules != null)
		{
			StringTokenizer st = new StringTokenizer(modules, ",");
			while (st.hasMoreTokens())
			{
				moduleIdsList.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		StringTokenizer ft = new StringTokenizer(format, ",");
		while (ft.hasMoreTokens()) {
			formatList.add(ft.nextToken());
		}
		
		Set<Request> requests = searchService.filterReq(title, formatList, moduleIdsList);
		Set<JSONRequest> jsonRequests = new HashSet<JSONRequest>();
		for (Request request : requests) {
			JSONRequest jr = requestToJSONRequest(request);
			jsonRequests.add(jr);
		}
		return ResponseEntity.ok(jsonRequests);
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<Request> updateRequest(@PathVariable Integer id, @RequestBody Request r) {
		if (requestService.getRequestsById(id) == null) {
			return ResponseEntity.status(405).body(null);
		}
		return ResponseEntity.ok(requestService.updateRequest(r));
	}

	@PutMapping(value = "{id}/links")
	public ResponseEntity<List<ReqLink>> updateReqLinks(@PathVariable int id, @RequestBody List<ReqLink> reqLinks) {
		return ResponseEntity.ok(requestService.updateReqLinks(id, reqLinks));
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deleteRequest(@PathVariable int id) {
		Request request = requestService.getRequestsById(id);
		requestService.deleteRequest(request);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	
	public JSONRequest requestToJSONRequest(Request request) {
		JSONRequest jsonRequest = new JSONRequest();
		jsonRequest.setId(request.getId());
		jsonRequest.setTitle(request.getTitle());
		jsonRequest.setFormat(request.getFormat());
		jsonRequest.setDescription(request.getDescription());
		jsonRequest.setContent(request.getContent());
		jsonRequest.setDateCreated(request.getDateCreated());
		jsonRequest.setLastModified(request.getLastModified());
		jsonRequest.setReqLinks(request.getReqLinks());
		return jsonRequest;
	}
	
	public Request jsonRequestToRequest(JSONRequest jsonRequest) {
		Request request = new Request();
		request.setId(jsonRequest.getId());
		request.setTitle(jsonRequest.getTitle());
		request.setFormat(jsonRequest.getFormat());
		request.setDescription(jsonRequest.getDescription());
		request.setContent(jsonRequest.getContent());
		request.setDateCreated(jsonRequest.getDateCreated());
		request.setLastModified(jsonRequest.getLastModified());
		request.setReqLinks(jsonRequest.getReqLinks());
		return request;
	}
}
