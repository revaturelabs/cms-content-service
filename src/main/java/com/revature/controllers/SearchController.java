package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Content;
import com.revature.services.SearchService;

@CrossOrigin(origins = "*", allowCredentials="true")
@RestController
@Transactional
public class SearchController {

	@Autowired
	SearchService searchService;
	
	@PostMapping("/search")
	public Set<Content> filter(@RequestBody Map<String, Object> body) {
		@SuppressWarnings("unchecked")
		List<Integer> lst = (ArrayList<Integer>) body.get("modules");
		return searchService.filter(body.get("title").toString(), body.get("format").toString(), lst);
	}
}
