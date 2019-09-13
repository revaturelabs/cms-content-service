package com.revature.controllers;

/**
 * For documentation on the controllers check out some documentation on swaggerhub:
 * https://app.swaggerhub.com/apis-docs/pacquito/CMS-Controllers/0.1
 */
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Requests;
import com.revature.services.RequestService;
import com.revature.services.RequestModuleService;

@CrossOrigin(origins = "*", allowCredentials="true")
@Transactional
@RestController
@RequestMapping(value="/request")
public class RequestController {

	@Autowired
	RequestService requestService;
	
	@Autowired
	RequestModuleService requestModuleService;
	
	@PostMapping(produces  = MediaType.APPLICATION_JSON_VALUE) 
	public Requests createRequest(@RequestBody Requests request ) throws Exception{
		
		request = requestService.createRequests(request);
		return request;
	}
	
	@GetMapping()
	public Set<Requests> getAllRequest() {
		return (Set<Requests>) requestService.getAllRequests();
	}
	
	@GetMapping(value="{id}")
	public Requests getRequestById(@PathVariable int id) {
		return requestService.getRequestsById(id);
	}
	
	//is this the correct PutMapping?
	@PutMapping(value="{id}")
	public ResponseEntity<Requests> updateRequest(@PathVariable Integer Id, @RequestBody Requests r){
		if(requestService.getRequestsById(Id) == null) {
			return ResponseEntity.status(405).body(null);
		}
		return ResponseEntity.ok(requestService.updateRequests(r));
	}
	
	@DeleteMapping(value="{id}")
	public void deleteRequest(@PathVariable int id) {
		Requests request = requestService.getRequestsById(id);
		requestService.deleteRequests(request);
	}
}
