package com.revature.controllers;

import java.util.ArrayList;
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

import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.entities.Request;
import com.revature.services.RequestService;
import com.revature.services.SearchService;
import com.revature.util.LogException;

@CrossOrigin(origins = "*", allowCredentials="true")
@Transactional
@RestController
@RequestMapping(value="/requests")
public class RequestController {

	@Autowired
	RequestService requestService;
	
	@Autowired
	SearchService searchService;
	
	@PostMapping(produces  = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Request> createRequest(@RequestBody Request request ) throws Exception{
		return ResponseEntity.ok(requestService.createRequests(request));
	}
	
	@PostMapping(value="/links", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReqLink>> createReqLinks(@RequestBody List<ReqLink> reqLinks) {
		return ResponseEntity.ok(requestService.createReqLinks(reqLinks));
	}
	
	@GetMapping()
	public ResponseEntity<Set<Request>> getAllRequest() {
		return ResponseEntity.ok(requestService.getAllRequests());
	}
	
	@GetMapping(value="{id}")
	public ResponseEntity<Request> getRequestById(@PathVariable int id) {
		return ResponseEntity.ok(requestService.getRequestsById(id));
	}
	
	//This query returns a subset of Content based on the values of the query parameters passed in
    //If a parameter is empty, it is not used in the filtering process.
    //modules is a string in comma separated format of integers ex. "1,2,3,4"
    @LogException
    @GetMapping (params= {"title", "format", "modules"})
    public ResponseEntity<Set<Request>> getSearchResults(
            @RequestParam(value="title", required=false) String title,
            @RequestParam(value="format", required=false) String format,
            @RequestParam(value="modules", required=false) String modules
        ) {
    	ArrayList<Integer> moduleIdsList = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(modules, ",");
		while (st.hasMoreTokens()) {
			moduleIdsList.add(Integer.parseInt(st.nextToken()));
		}
        return ResponseEntity.ok(searchService.filterReq(title, format, moduleIdsList));
    }

	@PutMapping(value="{id}")
	public ResponseEntity<Request> updateRequest(@PathVariable Integer Id, @RequestBody Request r){
		if(requestService.getRequestsById(Id) == null) {
			return ResponseEntity.status(405).body(null);
		}
		return ResponseEntity.ok(requestService.updateRequests(r));
	}
	
	@PutMapping(value="{id}/links")
	public ResponseEntity<List<ReqLink>> updateReqLinks(@PathVariable int id, @RequestBody List<ReqLink> reqLinks){
		return ResponseEntity.ok(requestService.updateReqLinks(id, reqLinks));
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<String> deleteRequest(@PathVariable int id) {
		Request request = requestService.getRequestsById(id);
		requestService.deleteRequests(request);
		return ResponseEntity.status(HttpStatus.OK).body("Request Deleted");
	}
}
