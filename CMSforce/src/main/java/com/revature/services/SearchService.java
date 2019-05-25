package com.revature.services;

import java.util.Set;

import com.revature.entities.Content;

public interface SearchService {

	public Set<Content> filterContentByTitle(String title);
	
	public Set<Content> filterContentByFormat(String format);
	
	public Set<Content> filterContentBySubjects(String[] subjects);
	
	public Set<Content> getContentByModuleId(int ModuleId);
}
