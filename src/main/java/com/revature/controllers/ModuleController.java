package com.revature.controllers;

import java.util.HashSet;
/**
 * For documentation on the controllers check out some documentation on swaggerhub:
 * https://app.swaggerhub.com/apis-docs/pacquito/CMS-Controllers/0.1
 */
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.revature.JSONEntities.JSONModule;
import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;
import com.revature.services.ModuleService;

@CrossOrigin(origins = "*", allowCredentials="true")
@RestController
@Transactional
@RequestMapping("/modules")
public class ModuleController {

	@Autowired
	ModuleService moduleService;
	
	//create a single module
	@PostMapping
	public ResponseEntity<Module> createModule(@RequestBody Module module) {
		return ResponseEntity.ok(moduleService.createModule(module));
	}
	
	//get all the modules
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
	
	//get a specific module
	@GetMapping(value="{id}")
	public ResponseEntity<JSONModule> getModuleById(@PathVariable int id) {
		Module module = moduleService.getModuleById(id);
		JSONModule jm = moduleToJSONModule(module);
		return ResponseEntity.ok(jm);
	}
	
	//get all modules without a parent
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
	
	//get all the children of a specific module
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
	
	//get all links by given module
	@GetMapping("/{id}/links")
	public ResponseEntity<Set<Link>> getLinksByModuleId(@PathVariable int id) {
		return ResponseEntity.ok(moduleService.getLinksByModuleId(id));
	}
	
	//get all reqLinks by given module
	@GetMapping("/{id}/req-links")
	public ResponseEntity<Set<ReqLink>> getReqLinksByModuleId(@PathVariable int id) {
		return ResponseEntity.ok(moduleService.getRequestLinksByModuleId(id));
	}

	//update a specific module
	@PutMapping("/{id}")
	public ResponseEntity<Module> updateModule(@PathVariable("id") int id, @RequestBody Module module) {
		return ResponseEntity.ok(moduleService.updateModule(module));
	}
	
	//delete a specific module, retaining all content
	@DeleteMapping(value="{id}")
	public ResponseEntity<String> deleteModule(@PathVariable int id) {
		Module module = moduleService.getModuleById(id);
		moduleService.deleteModule(module);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	
	//delete some or all associated content along with deleting the module
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
