package com.revature.controllers;

import java.util.HashSet;
/**
 * For documentation on the controllers check out some documentation on swaggerhub:
 * https://app.swaggerhub.com/apis-docs/pacquito/CMS-Controllers/0.1
 */
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.revature.JSONEntities.JSONModule;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.services.ModuleService;

/**
 * Handles HTTP requests to perform actions in the {@link com.revature.services.ModuleService ModuleService}
 */
@CrossOrigin(origins = "*", allowCredentials="true")
@RestController
@RequestMapping("/modules")
public class ModuleController {

	@Autowired
	ModuleService moduleService;
	
	/**
	 * HTTP POST method
	 * Uses @PostMapping to create a single {@link com.revature.entities.Module Module} using the {@link com.revature.services.ModuleService#createModule(Module) createModule(Module)} service method.
	 * Returns OK status (200) and the created Module in the response body upon successful creation.
	 * @param module
	 * @return ResponseEntity<Module>
	 */
	@PostMapping
	public ResponseEntity<Module> createModule(@RequestBody Module module) {
		return ResponseEntity.ok(moduleService.createModule(module));
	}
	
	/**
	 * HTTP GET method
	 * Uses @GetMapping to retrieve all {@link com.revature.entities.Module Module} objects using the {@link com.revature.services.ModuleService#getAllModules() getAllModules()} service method.
	 * Returns OK status (200) and a Set of the retrieved Modules in the response body upon successful retrieval.
	 * @return ResponseEntity<Set<JSONModule>>
	 */
	@GetMapping
	public ResponseEntity<Set<JSONModule>> getAllModules() {
		Set<Module> modules = moduleService.getAllModules();
		Set<JSONModule> jsonModules = new HashSet<JSONModule>();
		for (Module module : modules) {
			JSONModule jm = moduleToJSONModule(module);
			jsonModules.add(jm);
		}
		return ResponseEntity.ok(jsonModules);
	}
	
	/**
	 * HTTP GET method
	 * Uses @GetMapping to retrieve a specific {@link com.revature.entities.Module Module} object by id using the {@link com.revature.services.ModuleService#getModuleById(int) getModuleById(int)} service method.
	 * Returns OK status (200) and the retrieved Module in the response body upon successful retrieval.
	 * @return ResponseEntity<JSONModule>
	 */
	@GetMapping(value="{id}")
	public ResponseEntity<JSONModule> getModuleById(@PathVariable int id) {
		Module module = moduleService.getModuleById(id);
		JSONModule jm = moduleToJSONModule(module);
		return ResponseEntity.ok(jm);
	}
	
	/**
	 * HTTP GET method
	 * Uses @GetMapping to retrieve all "root" {@link com.revature.entities.Module Module} objects 
	 * (Modules with no parent Module) using the {@link com.revature.services.ModuleService#getAllRootModules() getAllRootModules()} service method.
	 * Returns OK status (200) and a Set of all root Modules in the response body upon successful retrieval.
	 * @return ResponseEntity<Set<JSONModule>>
	 */
	@GetMapping("/roots")
	public ResponseEntity<Set<JSONModule>> getAllRootModules(){
		Set<Module> modules = moduleService.getAllRootModules();
		Set<JSONModule> jsonModules = new HashSet<JSONModule>();
		for (Module module : modules) {
			JSONModule jm = moduleToJSONModule(module);
			jsonModules.add(jm);
		}
		return ResponseEntity.ok(jsonModules);
	}
	
	/**
	 * HTTP GET method
	 * Uses @GetMapping to retrieve all "children" {@link com.revature.entities.Module Module} objects 
	 * of a Module with a specified id using the {@link com.revature.services.ModuleService#getChildrenByParentId(int) getChildrenByParentId(int)} service method.
	 * Returns OK status (200) and a Set of all requested children Modules in the response body upon successful retrieval.
	 * @return ResponseEntity<Set<JSONModule>>
	 */
	@GetMapping("/{id}/children")
    public ResponseEntity<Set<JSONModule>> getChildrenByModuleId(@PathVariable int id) {
		Set<Module> modules = moduleService.getChildrenByParentId(id);
		Set<JSONModule> jsonModules = new HashSet<JSONModule>();
		for (Module module : modules) {
			JSONModule jm = moduleToJSONModule(module);
			jsonModules.add(jm);
		}
        return ResponseEntity.ok(jsonModules);
    }
	
	/**
	 * HTTP GET method
	 * Uses @GetMapping to retrieve all {@link com.revature.entities.Link Links} of a {@link com.revature.entities.Module Module} object 
	 * with a specific id using the {@link com.revature.services.ModuleService#getLinksByModuleId(int) getLinksByModuleId(int)} service method.
	 * Returns OK status (200) and a Set of all requested Links in the response body upon successful retrieval.
	 * @return ResponseEntity<Set<Link>>
	 */
	@GetMapping("/{id}/links")
	public ResponseEntity<Set<Link>> getLinksByModuleId(@PathVariable int id) {
		return ResponseEntity.ok(moduleService.getLinksByModuleId(id));
	}
	
	/**
	 * HTTP GET method
	 * Uses @GetMapping to retrieve all {@link com.revature.entities.ReqLink ReqLinks} of a given {@link com.revature.entities.Module Module} object 
	 * by id using the {@link com.revature.services.ModuleService#getRequestLinksByModuleId(int) getRequestLinksByModuleId(int)} service method.
	 * Returns OK status (200) and a Set of all requested ReqLinks in the response body upon successful retrieval.
	 * @return ResponseEntity<Set<ReqLink>>
	 */
	@GetMapping("/{id}/req-links")
	public ResponseEntity<Set<ReqLink>> getReqLinksByModuleId(@PathVariable int id) {
		return ResponseEntity.ok(moduleService.getRequestLinksByModuleId(id));
	}

	/**
	 * HTTP PUT method
	 * Uses @PutMapping to update a {@link com.revature.entities.Module Module} object 
	 * using the {@link com.revature.services.ModuleService#updateModule(Module) updateModule(Module)} service method.
	 * Returns OK status (200) and the updated Module in the response body upon successful update.
	 * @return ResponseEntity<Module>
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Module> updateModule(@PathVariable("id") int id, @RequestBody Module module) {
		return ResponseEntity.ok(moduleService.updateModule(module));
	}

	//Note From Tester: refactor name to updateLinksByModuleId
	/**
	 * HTTP PUT method
	 * Uses @PutMapping to update the {@link com.revature.entities.Link Links} of a given {@link com.revature.entities.Module Module} object 
	 * using the {@link com.revature.services.ModuleService#updateLinksByModuleId(int, Set) updateLinksByModuleId(int, Set)} service method.
	 * Returns OK status (200) and a Set of Links in the response body upon successful update.
	 * @return ResponseEntity<Set<Link>>
	 */
	@PutMapping(value = "{id}/links", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Link>> updateLinksById(@RequestBody Set<Link> links, @PathVariable int id) {

		return ResponseEntity.ok(moduleService.updateLinksByModuleId(id, links));
	}
	
	/**
	 * HTTP DELETE method
	 * Uses @DeleteMapping to delete a {@link com.revature.entites.Module Module} object based on id
	 * using the {@link com.revature.services.ModuleService#deleteModule(Module) deleteModule(Module)} service method.
	 * Returns OK status (200) and an empty String in the response body.
	 * @param id
	 * @return ResponseEntity<String>
	 */
	@DeleteMapping(value="{id}")
	public ResponseEntity<String> deleteModule(@PathVariable int id) {
		Module module = moduleService.getModuleById(id);
		moduleService.deleteModule(module);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	
	/**
	 * HTTP DELETE method
	 * Uses @DeleteMapping to delete {@link com.revature.entities.Content Content} {@link com.revature.entites.Module Module} object based on id
	 * using the {@link com.revature.services.ModuleService#deleteModule(Module) deleteModule(Module)} service method.
	 * Returns OK status (200) and an empty String in the response body.
	 * @param id
	 * @return ResponseEntity<String>
	 */
	@DeleteMapping(value="{id}", params= {"type"})
	public ResponseEntity<String> deleteModule(@PathVariable int id, @RequestParam(value="type", required=false) String type) {
		//get the module to be deleted
		Module module = moduleService.getModuleById(id);
		
		//use string.equals(variable) to avoid null pointer exceptions because sometimes we don't get a value for "type"
		if ("all".equals(type)) {
			//delete the module and all content that has an association with it
			moduleService.deleteModuleWithAllContent(module);
		} else if ("unique".equals(type)) {
			//delete the module and the content that has an association with it and no other module
			moduleService.deleteModuleWithSpecificContent(module);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bad Value For 'type' Parameter");
		}
		return ResponseEntity.ok("");
	}

	/**
	 * Utility method that allows conversion from a {@link com.revature.entities.Module Module} object to a {@link com.revature.JSONEntities.JSONModule JSONModule} object.
	 * @param module
	 * @return JSONModule
	 */
	public JSONModule moduleToJSONModule(Module module) {
		JSONModule jsonModule = new JSONModule();
		jsonModule.setId(module.getId());
		jsonModule.setSubject(module.getSubject());
		jsonModule.setCreated(module.getCreated());
		jsonModule.setLinks(module.getLinks());
		jsonModule.setReqLinks(module.getReqLinks());
		jsonModule.setParents(module.getParents());
		jsonModule.setChildren(module.getChildren());
		return jsonModule;
	}
}