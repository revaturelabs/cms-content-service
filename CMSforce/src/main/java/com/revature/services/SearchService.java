package com.revature.services;

import java.util.Set;

import com.revature.entities.Content;

public interface SearchService {

	public Set<Content> filterContentByTitle(String title);
	
	public Set<Content> filterContentByFormat(String format);
	
	public Set<Content> filterContentBySubjects(int[] moduleIds);
	
	// TODO needs to get all contents, wait for tests
	public Set<Content> getContentByModuleId(int ModuleId);
	
}
