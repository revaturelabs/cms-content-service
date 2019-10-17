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
public class CurriculumModuleController {

	@Autowired
	CurriculumModuleService curriculumModuleService;
	
	@PostMapping
	public ResponseEntity<CurriculumModule> createCurrModule(@RequestBody CurriculumModule cMod){
		return ResponseEntity.ok(curriculumModuleService.createCurriculumModule(cMod));
	}
	
	@GetMapping
	public ResponseEntity <Set<CurriculumModule>> getAllCurrModules(){
		Set<CurriculumModule> currMods = curriculumModuleService.getAllCurriculumModules();
		return ResponseEntity.ok(currMods);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<CurriculumModule> getCurrModuleById(@PathVariable int id){
		CurriculumModule currMod = curriculumModuleService.getCurriculumModuleById(id);
		return ResponseEntity.ok(currMod);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ArrayList<CurriculumModule>> updateCurrModules(@RequestBody Set<CurriculumModule> modules){
		return ResponseEntity.ok((ArrayList<CurriculumModule>)curriculumModuleService.updateCurriculumModule(modules));
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<String> deleteCurrModule(@PathVariable int id){
		CurriculumModule cMod = curriculumModuleService.getCurriculumModuleById(id);
		curriculumModuleService.deleteCurriculumModule(cMod);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
