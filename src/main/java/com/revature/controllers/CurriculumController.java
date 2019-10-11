package com.revature.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.JSONEntities.JSONModule;
import com.revature.entities.Curriculum;
import com.revature.entities.Module;
import com.revature.services.CurriculumService;

@CrossOrigin(origins = "*", allowCredentials = "true")
@RestController
@RequestMapping(value = "/curriculum")


public class CurriculumController {
	
	@Autowired
	CurriculumService curriculumService;
	
	@GetMapping()
	public Set<Curriculum> getAllCurriculums(){
		Set<Curriculum> curriculums = curriculumService.getAllCurriculums();
		return curriculums;
	}
	
	@GetMapping(value="{id}")
	public ResponseEntity<Curriculum> getModuleById(@PathVariable int id) {
		
		Curriculum curriculum = curriculumService.getCurriculumById(id);
		
		return ResponseEntity.ok(curriculum);
	}
	
	

}
