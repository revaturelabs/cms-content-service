package com.revature.services;

import java.util.List;

import com.revature.entities.Content;

public interface SearchService {

	public List<Content> filterContentByTitle(String title);
	
	public List<Content> filterContentByFormat(String format);
	
	public List<Content> filterContentBySubjects(String[] subjects);
}
