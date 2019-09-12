package com.revature.controllers;

/**
 * For documentation on the controllers check out some documentation on swaggerhub:
 * https://app.swaggerhub.com/apis-docs/pacquito/CMS-Controllers/0.1
 */
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class RequestController {

	@Autowired
	RequestService requestService;
	
	@Autowired
	RequestModuleService requestModuleService;
	
	@RequestMapping(value = "/request", method = RequestMethod.POST, produces  = MediaType.APPLICATION_JSON_VALUE) 
	public Requests createRequest(@RequestBody Requests request ) throws Exception{
		
		request = requestService.createRequests(request);
		return request;
	}
	
	@GetMapping("/request")
	public Set<Requests> getAllRequest() {
		return (Set<Requests>) requestService.getAllRequests();
	}
	
	@GetMapping("/request/{id}")
	public Requests getRequestById(@PathVariable int id) {
		return requestService.getRequestsById(id);
	}
	
	@RequestMapping(value = "/request", method = RequestMethod.PUT, produces  = MediaType.APPLICATION_JSON_VALUE)
	public Requests updateRequest(@RequestBody Requests newContent) {
		return requestService.updateRequests(newContent);
	}
	
	@DeleteMapping("/request/{id}")
	public void deleteRequest(@PathVariable int id) {
		Requests request = requestService.getRequestsById(id);
		requestService.deleteRequests(request);
	}
}
