package com.revature.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.util.LogException;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	ContentRepository cr;
	@Autowired
	ModuleRepository mr;
	@Autowired
	LinkRepository lr;
	@Autowired
	ContentService csi;

	/**
	 * filterContentByTitle takes in a string value and returns a map of 
	 * content, with key-value pairs that are associated with the title
	 * string that was passed into the method.
	 */
	@Override
	@LogException
	public Set<Content> filterContentByTitle(String title) {
		Set<Content> temp = cr.findByTitle(title); 
		return temp;
	}

	/**
	 * filderContentByFormat takes in a string value and returns a map of
	 * content, with key-value pairs that are associated with the format
	 * type string that was passed into the method
	 */
	@Override
	@LogException
	public Set<Content> filterContentByFormat(String format) {
		return cr.findByFormat(format); 
	}

	/**
	 * filterContentBySubjects takes in a list of integers that represent
	 * module ids. It then stores all the related links of that module
	 * in a temporary set.
	 * then it gets all the content that relates to the selected links.
	 * The method uses AND logic. 
	 */
	@Override
	@LogException
	public Set<Content> filterContentBySubjects(List<Integer> moduleIds) {
		Set<Content> contents = new HashSet<>();
		Set<Integer> ids = new HashSet<>();
		Set<Integer> idsTemp = new HashSet<>();
		
		Set<Link> linksByModuleID = lr.findByModuleId(moduleIds.get(0));
		for(Link link : linksByModuleID) {
			ids.add(link.getContentId());
		}

		for(int i = 1; i < moduleIds.size(); i++) {
			linksByModuleID = lr.findByModuleId(moduleIds.get(i));
			for(Link link : linksByModuleID) {
				idsTemp.add(link.getContentId());
			}
			ids.retainAll(idsTemp);
		}
		
		cr.findAllById(ids).forEach(contents :: add);
		
		return contents;	
	}
	
	/**
	 * getContentByModuleID takes in a int that is a module id and 
	 * returns a set of content that have the inputed value as 
	 * the moduleID.
	 */
	@Override
	@LogException
	public Set<Content> getContentByModuleId(int moduleId) {
		Set<Link> links = lr.findByModuleId(moduleId);
		int contentId = links.iterator().next().getContentId();
		return cr.findById(contentId);
	}

	/**
	 * Filter takes a content title, content format and/or
	 * a list of Integers that represent module ids.
	 * It will then return a set of content that contains all content that 
	 * matches all 3 inputs using AND logic.
	 * If an input is empty it is ignored and is not part of the logic.
	 */
	@Override
	@LogException
	public Set<Content> filter(String title, String format, List<Integer> modules) {
		
		Set<Content> contents = null;
		Set<Content> copy = null;
		
		if(format != null && !format.equals("All") && !format.equals("")) {
			contents = cr.findByFormat(format);
		}
		
		if(title != null && !title.equals("")) {
			
			if(contents == null) {
				
				contents = cr.findByTitleContaining(title);
				
			} else {
				
				copy = new HashSet<Content>(contents);
				
				for(Content c : copy) {
					
					if(!c.getTitle().toLowerCase().contains(title.toLowerCase()))
						contents.remove(c);
				}
			}
		}
		
		if(contents == null) {
			contents = csi.getAllContent();
		}
		
		if(modules != null && !modules.isEmpty()) {
			
			copy = new HashSet<Content>(contents);
			Set<Link> linksInModules = lr.findByModuleIdIn(modules);
			
			boolean inModule;
			
			for(Content c : copy) {
				
				inModule = false;
				
				for(Link l : c.getLinks()) {
					
					if(linksInModules.contains(l)) {
						
						inModule = true;
						break;
					}
				}
				
				if(!inModule) {
					contents.remove(c);
				}
			}
		}
		
		return contents;
	}

	@Override
	public Set<Content> filterContent(Set<Content> contents, Map<String, Object> filters) {
		
		Set<Content> copy;
		
		String title = (String) filters.get("title");
		String format = (String) filters.get("format");
		boolean isTitle = false;
		boolean isFormat = false;
		
		if(title != null && !title.isEmpty()) {
			isTitle = true;
		}
		if(format != null && !format.equals("All")) {
			isFormat = true;
		}
			
		if(isTitle || isFormat) {
			copy = new HashSet<Content>(contents);
			
			for(Content c : copy) {
				
				if(isTitle && !c.getTitle().toLowerCase().contains(title.toLowerCase()))
					contents.remove(c);
				if(isFormat && !c.getFormat().equals(format))
					contents.remove(c);
			}
		}
			
		ArrayList<Integer> ids = (ArrayList<Integer>) filters.get("modules");
		
		if(ids != null && !ids.isEmpty()) {

			copy = new HashSet<Content>(contents);
			
			boolean inModule = false;
			
			for(Content c : copy) {
				
				inModule = false;
				
				for(Link l : c.getLinks()) {
					
					if(ids.contains(l.getModuleId())) {
						inModule = true;
						break;
					}
				}
				
				if(!inModule)
					contents.remove(c);
			}
		}
		
		return contents;
	}
}

	
