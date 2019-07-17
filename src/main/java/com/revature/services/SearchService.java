package com.revature.services;

import java.util.List;
import java.util.Set;

import com.revature.entities.Content;

public interface SearchService {

	public Set<Content> filterContentByTitle(String title);
	
	public Set<Content> filterContentByFormat(String format);
	
	public Set<Content> filterContentBySubjects(List<Integer> moduleIds);
	
	public Set<Content> getContentByModuleId(int ModuleId);
	
	public Set<Content> filter(String title, String format, List<Integer> modules);
}
