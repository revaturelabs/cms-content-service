package com.revature.controllers;

/**
 * For documentation on the controllers check out some documentation on swaggerhub:
 * https://app.swaggerhub.com/apis-docs/pacquito/CMS-Controllers/0.1
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Content;
import com.revature.services.SearchService;
import com.revature.util.LogException;

@CrossOrigin(origins = "*", allowCredentials="true")
@RestController
@Transactional
@RequestMapping(value="/search")
public class SearchController {

	@Autowired
	SearchService searchService;
	
	@LogException
	@PostMapping
	public Set<Content> filter(@RequestBody Map<String, Object> body) {
		@SuppressWarnings("unchecked")
		List<Integer> lst = (ArrayList<Integer>) body.get("modules");
		return searchService.filter(body.get("title").toString(), body.get("format").toString(), lst);
	}
	
	@LogException
	@GetMapping /* (params= {"title", "format", "modules"}) */
	public Set<Content> getSearchResults(
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="format", required=false) String format, 
			@RequestParam(value="modules", required=false) String modules
		) {
		ArrayList<Integer> moduleIdsList = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(modules, ",");
		while (st.hasMoreTokens()) {
			moduleIdsList.add(Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(title + " " + format + " " + moduleIdsList);
		return searchService.filter(title, format, moduleIdsList);
	}
	
}
