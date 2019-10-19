package com.revature.controllers;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.CurriculumModule;
import com.revature.services.CurriculumModuleService;

@CrossOrigin(origins = "*", allowCredentials="true")
@RestController
@RequestMapping("/curriculum-modules")
/**
 * 
 * @author Java Batch 1908
 *
 *         CurriculumModule Controller
 *         Delegate different HTTP request for CurriculumModule to corresponding CurriculumModuleService method
 * 
 *
 */
public class CurriculumModuleController {

	@Autowired
	CurriculumModuleService curriculumModuleService;
	
	@PostMapping
	/**
	 *  Mapping 'POST' request with '/curriculum-modules' URL to CurriculumModuleService's createCurriculumModule method to create a new CurriculumModule record in database
	 *
	 * @param cMod - the CurriculumModule Object to create, it's provided in request body
	 * @return ResponseEntity<CurriculumModule> - response body with JSON format of CurriculumModule Object
	 */
	public ResponseEntity<CurriculumModule> createCurrModule(@RequestBody CurriculumModule cMod){
		return ResponseEntity.ok(curriculumModuleService.createCurriculumModule(cMod));
	}
	
	@GetMapping
	 /**
     * Mapping 'GET' request with '/curriculum-modules' URL to CurriculumModuleService's getAllCurriculumModules method to get all CurriculumModule Object available in database
     * @return ResponseEntity<set<CurriculumModule>> - response body with JSON format of a set of CurriculumModule Objects available in database
     */
	public ResponseEntity <Set<CurriculumModule>> getAllCurrModules(){
		Set<CurriculumModule> currMods = curriculumModuleService.getAllCurriculumModules();
		return ResponseEntity.ok(currMods);
	}
	
	@GetMapping(value="/{id}")
	/**
	 * Mapping 'GET' request with '/curriculum-modules/{id}' URL to CurriculumModuleService getCurriculumModuleById method to get the CurriculumMoudle Object with given Id
	 * @param id - CurriculumModule Id
	 * @return ResponseEntity<Curriculum> - ResponseBody with JSON format of CurriculumModule Object 
	 * 
	 */
	public ResponseEntity<CurriculumModule> getCurrModuleById(@PathVariable int id){
		CurriculumModule currMod = curriculumModuleService.getCurriculumModuleById(id);
		return ResponseEntity.ok(currMod);
	}
	
	@PutMapping(value="/curriculum/{id}")
	/**
	 * Mapping 'PUT' request with '/curriculum-modules/curriculum/{id}' URL to CurriculumService's updateCurrModules method to update Curriculum record in database with given id
	 *
	 * @param modules - a set of CurriculumModule Objects to update provided in request body
	 * @param id - Curriculum Id associated with all CurriculumModules
	 * @return ResponseEntity<ArrayList<CurriculumModule>> - response body with JSON format of a set of CurriculumModule Objects whose curriculum id has been updated 
	 */
	public ResponseEntity<ArrayList<CurriculumModule>> updateCurrModules(@PathVariable int id, @RequestBody Set<CurriculumModule> modules){
		for(CurriculumModule c : modules) {
			c.setCurriculum(id);
		}
		return ResponseEntity.ok((ArrayList<CurriculumModule>)curriculumModuleService.updateCurriculumModule(modules));
	}
	
	@DeleteMapping(value="/{id}")
	/**
	 * Mapping 'DELETE' request with '/curriculum-modules/{id}' URL to CurriculumModuleService getCurriculumModuleById and deleteCurriculumModule method to delete the CurriculumModule record in database with given id
	 *
	 * @param id - the CurriculumModule id to delete
	 * @return ResponseEntity<String> - set status to be "HTTPStatus.ok" and empty string in response body if the CurriculumModule record with given Id is successfully deleted
	 */	
	public ResponseEntity<String> deleteCurrModule(@PathVariable int id){
		CurriculumModule cMod = curriculumModuleService.getCurriculumModuleById(id);
		curriculumModuleService.deleteCurriculumModule(cMod);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
