package com.revature.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.revature.entities.Content;
import com.revature.entities.Request;

public interface SearchService {

	public Set<Content> filterContentByTitle(String title);
	
	public Set<Content> filterContentByFormat(String format);
	
	public Set<Content> filterContentBySubjectIds(List<Integer> moduleIds);
	
	public Set<Content> getContentByModuleId(int moduleId);
	
	public Set<Content> filter(String title, List<String> format, List<Integer> modules, List<Integer> curricula);
	
	public Set<Content> filter(String title, List<String> format, List<Integer> modules);
	
	public Set<Content> filterContent(Set<Content> contents, Map<String, Object> filters);
	
	public Set<Request> filterRequestByTitle(String title);
	
	public Set<Request> filterRequestByFormat(String format);
	
	public Set<Request> filterRequestBySubjectIds(List<Integer> moduleIds);
	
	public Set<Request> filterReq(String title, List<String> format, List<Integer> modules);
	
	//Added By UTAT-643. Needed For Testing Purposes === TestfilterContentByCurriculaIds()
	public Set<Content> filterContentByCurriculaIds(List<Integer> curriculaIds);
}
