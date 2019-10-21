package com.revature.controllers;

import java.util.HashSet;
import java.util.Set;

import com.revature.entities.Curriculum;
import com.revature.entities.CurriculumModule;
import com.revature.services.CurriculumModuleService;
import com.revature.services.CurriculumService;

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

@CrossOrigin(origins = "*", allowCredentials = "true")
@RestController
/**
 * 
 * @author Java Batch 1908
 *
 *         Curriculum Controller
 *         Delegate different HTTP request for curriculum to corresponding CurriculumService method
 *
 */
@RequestMapping(value = "/curricula")
public class CurriculumController {

	@Autowired
	CurriculumService curriculumService;

	@Autowired
	CurriculumModuleService currModuleService;
    /**
     * Mapping 'GET' request with '/curriculums' URL to CurriculumService getAllCurriculum method to get all Curriculum Object available in database
     * @return curriculums - all Curriculum Objects available in database
     */
	@GetMapping()
	public Set<Curriculum> getAllCurriculums() {
		Set<Curriculum> curriculums = curriculumService.getAllCurriculums();
		return curriculums;
	}
    
	
	@GetMapping(value = "{id}")
	/**
	 * Mapping 'GET' request with '/curriculums/{id}' URL to CurriculumService getCurriculumById method to get the Curriculum Object with given Id
	 * @param id - Curriculum Id
	 * @return ResponseEntity<Curriculum> - ResponseBody with JSON format of Curriculum Object 
	 * 
	 */
	public ResponseEntity<Curriculum> getCurriculumById(@PathVariable int id) {
		Curriculum curriculum = curriculumService.getCurriculumById(id);
		Set<CurriculumModule> currModules = new HashSet<>();
		if (null == curriculum) {
			return ResponseEntity.notFound().build();
		}
		Set<CurriculumModule> allCurrModules = currModuleService.getAllCurriculumModules();
		for (CurriculumModule c : allCurrModules) {
			if (c.getCurriculum() == id) {
				currModules.add(c);
			}
		}
		curriculum.setCurrModules(currModules);
		return ResponseEntity.ok(curriculum);
	}

	@PostMapping
	/**
	 *  Mapping 'POST' request with '/curriculums' URL to CurriculumService createCurriculum method to create a new Curriculum record in database
	 *
	 * @param curriculum - the Curriculum Object to create
	 * @return ResponseEntity<Curriculum> - response body with JSON format of Curriculum Object
	 */
	public ResponseEntity<Curriculum> createCurriculum(@RequestBody Curriculum curriculum) {
		return ResponseEntity.ok(curriculumService.createCurriculum(curriculum));
	}

	/**
	 * Mapping 'PUT' request with '/curriculums' URL to CurriculumService updateCurriculum method to update Curriculum record in database with given id
	 *
	 * @param curriculum - the Curriculum Object to update provided in request body
	 * @return ResponseEntity<Curriculum> - response body with JSON format of Curriculum Object
	 */
	@PutMapping(value = "{id}")
	public ResponseEntity<Curriculum> updateCurriculum(@PathVariable int id, @RequestBody Curriculum curriculum) {
		curriculum.setId(id);
		return ResponseEntity.ok(curriculumService.updateCurriculum(curriculum));
	}

	@DeleteMapping(value = "{id}")
	/**
	 * Mapping 'DELETE' request with '/curriculums/{id}' URL to CurriculumService deleteCurriculum method to delete the Curriculum record in database with given id
	 *
	 * @param id - the Curriculum id to delete
	 * @return ResponseEntity<String> - set status to be "HTTPStatus.ok" and empty string in response body if the Curriculum record with given Id is successfully deleted
	 */	
	public ResponseEntity<String> deleteCurriculum(@PathVariable int id) {
		Curriculum curriculum = curriculumService.getCurriculumById(id);
		curriculumService.deleteCurriculum(curriculum);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}