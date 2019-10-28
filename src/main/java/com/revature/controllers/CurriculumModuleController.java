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
 *         Delegate different HTTP request for {@link com.revature.entities.CurriculumModule CurriculumModule} to corresponding {@link com.revature.services.CurriculumModuleService CurriculumModuleService} methods
 * 
 *
 */
public class CurriculumModuleController {

	@Autowired
	CurriculumModuleService curriculumModuleService;
	
	/**
	 * Mapping 'POST' request with '/curriculum-modules' URL to {@link com.revature.services.CurriculumModuleServiceImpl#createCurriculumModule(CurriculumModule) createCurriculumModule(CurriculumModule)} to create a new {@link com.revature.entities.CurriculumModule CurriculumModule} record in database
	 *
	 * @param cMod - the CurriculumModule Object to create, it's provided in request body
	 * @return ResponseEntity<CurriculumModule> - response body with JSON format of CurriculumModule Object
	 */
	@PostMapping
	public ResponseEntity<CurriculumModule> createCurrModule(@RequestBody CurriculumModule cMod){
		return ResponseEntity.ok(curriculumModuleService.createCurriculumModule(cMod));
	}
	
	/**
     * Mapping 'GET' request with '/curriculum-modules' URL to {@link com.revature.services.CurriculumModuleServiceImpl#getAllCurriculumModules() getAllCurriculumModules()} to get all {@link com.revature.entities.CurriculumModule CurriculumModule} objects available in database
     * @return ResponseEntity<set<CurriculumModule>> - response body with JSON format of a set of CurriculumModule Objects available in database
     */
	@GetMapping
	public ResponseEntity <Set<CurriculumModule>> getAllCurrModules(){
		Set<CurriculumModule> currMods = curriculumModuleService.getAllCurriculumModules();
		return ResponseEntity.ok(currMods);
	}
	
	/**
	 * Mapping 'GET' request with '/curriculum-modules/{id}' URL to {@link com.revature.services.CurriculumModuleServiceImpl#getCurriculumModuleById(int) getCurriculumModuleById(int)} method to get the {@link com.revature.entities.CurriculumModule CurriculumModule} object with given Id
	 * @param id - CurriculumModule Id
	 * @return ResponseEntity<Curriculum> - ResponseBody with JSON format of CurriculumModule Object 
	 * 
	 */
	@GetMapping(value="/{id}")
	public ResponseEntity<CurriculumModule> getCurrModuleById(@PathVariable int id){
		CurriculumModule currMod = curriculumModuleService.getCurriculumModuleById(id);
		return ResponseEntity.ok(currMod);
	}
	
	@PutMapping(value="/curriculum/{id}")
	/**
	 * Mapping 'PUT' request with '/curriculum-modules/curriculum/{id}' URL to {@link com.revature.services.CurriculumModuleServiceImpl#updateCurrModules(Set<CurriculumModule>) updateCurrModules(Set<CurriculumModule>)} to update the {@link com.revature.entities.CurriculumModule CurriculumModule} record in database with given id
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
	
	/**
	 * Mapping 'DELETE' request with '/curriculum-modules/{id}' URL to {@link com.revature.services.CurriculumModuleServiceImpl#getCurriculumModuleById(int) getCurriculumModuleById(int)} and {@link com.revature.services.CurriculumModuleService#deleteCurriculumModule(CurriculumModule) deleteCurriculumModule(CurriculumModule)} to delete the {@link com.revature.entities.CurriculumModule CurriculumModule} record in database with given id
	 *
	 * @param id - the CurriculumModule id to delete
	 * @return ResponseEntity<String> - set status to be "HTTPStatus.ok" and empty string in response body if the CurriculumModule record with given Id is successfully deleted
	 */
	@DeleteMapping(value="/{id}")
	public ResponseEntity<String> deleteCurrModule(@PathVariable int id){
		CurriculumModule cMod = curriculumModuleService.getCurriculumModuleById(id);
		curriculumModuleService.deleteCurriculumModule(cMod);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
