package com.revature.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.entities.Content;
import com.revature.services.SearchService;

public class SearchController {

	@Autowired
	SearchService searchService;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public Set<Content> filter(@RequestParam String title, @RequestParam String format, @RequestParam List<String> subjects) {
//		return searchService.filterContentByTitle(requestBody);
		System.out.println("title: " + title);
		System.out.println("format: " + format);
		System.out.println("subjects: " + subjects);
		return null;
	}
}
