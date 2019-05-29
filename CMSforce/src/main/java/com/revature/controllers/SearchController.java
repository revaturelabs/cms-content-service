package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Content;
import com.revature.services.SearchService;

@CrossOrigin(origins = "*", allowCredentials="true")
@RestController
public class SearchController {

	@Autowired
	SearchService searchService;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public Set<Content> filter(@RequestBody Map<String, Object> body) {
		List<Integer> lst = (ArrayList<Integer>) body.get("modules");
		//System.out.println(lst);
		return searchService.filter(body.get("title").toString(), body.get("format").toString(), lst);
	}
}
