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

import com.revature.entities.Module;
import com.revature.entities.Requests;
import com.revature.services.RequestService;
import com.revature.services.SearchService;
import com.revature.util.LogException;

@CrossOrigin(origins = "*", allowCredentials="true")
@Transactional
@RestController
@RequestMapping(value="/request")
public class RequestController {

	@Autowired
	RequestService requestService;
	
	@Autowired
	SearchService searchService;
	
	@PostMapping(produces  = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Requests> createRequest(@RequestBody Requests request ) throws Exception{
		return ResponseEntity.ok(requestService.createRequests(request));
	}
	
	@GetMapping()
	public ResponseEntity<Set<Requests>> getAllRequest() {
		return ResponseEntity.ok(requestService.getAllRequests());
	}
	
	@GetMapping(value="{id}")
	public ResponseEntity<Requests> getRequestById(@PathVariable int id) {
		return ResponseEntity.ok(requestService.getRequestsById(id));
	}
	
	//This query returns a subset of Content based on the values of the query parameters passed in
    //If a parameter is empty, it is not used in the filtering process.
    //modules is a string in comma separated format of integers ex. "1,2,3,4"
    @LogException
    @GetMapping (params= {"title", "format", "modules"})
    public ResponseEntity<Set<Requests>> getSearchResults(
            @RequestParam(value="title", required=false) String title,
            @RequestParam(value="format", required=false) String format,
            @RequestParam(value="modules", required=false) String modules
        ) {
        ArrayList<Module> moduleList = new ArrayList<Module>();
        StringTokenizer st = new StringTokenizer(modules, ",");
        while (st.hasMoreTokens()) {
            moduleList.add((Module) st.nextElement());
        }
        return ResponseEntity.ok(searchService.filterReq(title, format, moduleList));
    }
	
	@PutMapping(value="{id}")
	public ResponseEntity<Requests> updateRequest(@PathVariable Integer Id, @RequestBody Requests r){
		if(requestService.getRequestsById(Id) == null) {
			return ResponseEntity.status(405).body(null);
		}
		return ResponseEntity.ok(requestService.updateRequests(r));
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<String> deleteRequest(@PathVariable int id) {
		Requests request = requestService.getRequestsById(id);
		requestService.deleteRequests(request);
		return ResponseEntity.status(HttpStatus.OK).body("Request Deleted");
	}
}
