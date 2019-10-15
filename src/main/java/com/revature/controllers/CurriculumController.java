package com.revature.controllers;

import java.util.HashSet;
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

import com.revature.entities.CurrModule;
import com.revature.entities.Curriculum;
import com.revature.services.CurrModuleService;
import com.revature.services.CurriculumService;

@CrossOrigin(origins = "*", allowCredentials = "true")
@RestController
@RequestMapping(value = "/curriculum")

public class CurriculumController {

	@Autowired
	CurriculumService curriculumService;

	@Autowired
	CurrModuleService currModuleService;

	@GetMapping()
	public Set<Curriculum> getAllCurriculums() {
		Set<Curriculum> curriculums = curriculumService.getAllCurriculums();
		return curriculums;
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Curriculum> getCurriculumById(@PathVariable int id) {

		Curriculum curriculum = curriculumService.getCurriculumById(id);
		Set<CurrModule> currModules = new HashSet<>();
		if (null != curriculum) {
			Set<CurrModule> allCurrModules = currModuleService.getAllCurrModules();
			for (CurrModule c : allCurrModules) {
				if (c.getCurriculum() == id) {
					currModules.add(c);
				}
			}
		}
		curriculum.setCurrModules(currModules);
		return ResponseEntity.ok(curriculum);
	}

	@PostMapping
	public ResponseEntity<Curriculum> createCurriculum(@RequestBody Curriculum curriculum) {
		return ResponseEntity.ok(curriculumService.createCurriculum(curriculum));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Curriculum> updateCurriculum(@PathVariable("id") int id, @RequestBody Curriculum curriculum) {
		return ResponseEntity.ok(curriculumService.updateCurriculum(curriculum));
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deleteCurriculum(@PathVariable int id) {
		Curriculum curriculum = curriculumService.getCurriculumById(id);
		curriculumService.deleteCurriculum(curriculum);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
