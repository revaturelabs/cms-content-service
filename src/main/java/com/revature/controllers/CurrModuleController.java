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

import com.revature.entities.CurrModule;
import com.revature.services.CurrModuleService;

@CrossOrigin(origins = "*", allowCredentials="true")
@RestController
@RequestMapping("/currmodules")
public class CurrModuleController {

	@Autowired
	CurrModuleService currModuleService;
	
	@PostMapping
	public ResponseEntity<CurrModule> createCurrModule(@RequestBody CurrModule cMod){
		return ResponseEntity.ok(currModuleService.createCurrModule(cMod));
	}
	
	@GetMapping
	public ResponseEntity <Set<CurrModule>> getAllCurrModules(){
		Set<CurrModule> currMods = currModuleService.getAllCurrModules();
		return ResponseEntity.ok(currMods);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<CurrModule> getCurrModuleById(@PathVariable int id){
		CurrModule currMod = currModuleService.getCurrModuleById(id);
		return ResponseEntity.ok(currMod);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ArrayList<CurrModule>> updateCurrModules(@RequestBody Set<CurrModule> modules){
		return ResponseEntity.ok((ArrayList<CurrModule>)currModuleService.updateCurrModule(modules));
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<String> deleteCurrModule(@PathVariable int id){
		CurrModule cMod = currModuleService.getCurrModuleById(id);
		currModuleService.deleteCurriculumModule(cMod);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
