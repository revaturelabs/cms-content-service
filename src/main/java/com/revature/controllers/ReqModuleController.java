package com.revature.controllers;

/**
 * For documentation on the controllers check out some documentation on swaggerhub:
 * https://app.swaggerhub.com/apis-docs/pacquito/CMS-Controllers/0.1
 */
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.ReqModule;
import com.revature.services.RequestModuleService;

@CrossOrigin(origins = "*", allowCredentials="true")
@RestController
@Transactional
@RequestMapping("/reqModules")
public class ReqModuleController {

	@Autowired
	RequestModuleService reqModuleService;
	
	@GetMapping
	public Set<ReqModule> getAllReqModules() {
		return (Set<ReqModule>) reqModuleService.getAllReqModules();
	}
	
	@GetMapping(value="{id}")
	public ReqModule getReqModuleById(@PathVariable int id) {
		return reqModuleService.getReqModuleById(id);
	}
	
	@PostMapping
	public ReqModule createReqModule(@RequestBody ReqModule reqModule) {
		return reqModuleService.createReqModule(reqModule);
	}
	
	@DeleteMapping(value="{id}")
	public void deleteReqModule(@PathVariable int id) {
		ReqModule reqModule = reqModuleService.getReqModuleById(id);
		reqModuleService.deleteReqModule(reqModule);
	}
	
	@GetMapping("/roots")
	public Set<ReqModule> getAllReqModulesByRoot(){
		return (Set<ReqModule>) reqModuleService.getAllReqModulesByRoot();
	}
	

	@GetMapping("/{id}/children")
    public Set<ReqModule> getChildrenByReqModuleId(@PathVariable int id) {
        return (Set<ReqModule>) reqModuleService.getChildrenByReqModuleId(id);
    }
	
	@PutMapping("/{parent}/children/{child}")
	public void setChildToParent(@PathVariable("parent") int parentId,@PathVariable("child") int childId) {
		reqModuleService.setChildToParent(parentId,childId);
	}
	
	@DeleteMapping("/withrequest/{id}")
	public void deleteReqModuleWithContent(@PathVariable int id) {
		ReqModule reqModule = reqModuleService.getReqModuleById(id);
		reqModuleService.deleteReqModuleWithAllContent(reqModule);
	}
	
	@DeleteMapping("/specrequest/{id}")
	public void deleteReqModuleWithSpecificContent(@PathVariable int id) {
		ReqModule reqModule = reqModuleService.getReqModuleById(id);
		reqModuleService.deleteReqModuleWithSpecificContent(reqModule);
	}
}
