package com.revature.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.revature.entities.Content;
import com.revature.entities.Module;
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
	ContentService cs;
	@Autowired
	ModuleService ms;

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
		Set<Module> modules = new HashSet<Module>();
		Set<Content> contents = new HashSet<>();
		
		//get modules by moduleIds
		for (Integer id : moduleIds) {
			modules.add(ms.getModuleById(id));
		}
		//get all content of each module
		for(Module module : modules) {
			//the first iteration, contents will be empty so we don't want to do an intersection yet
			if (contents.size() <= 0) {
				contents.add((Content) module.getContent());
			} else {
				contents = Sets.intersection(contents, module.getContent());
			}
			//if contents is empty at the end of an iteration, that means there was no content relating to that module
			if (contents.size() <= 0) {
				break;
			}
		}
		
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
		Module module = ms.getModuleById(moduleId);
		return module.getContent();
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
	public Set<Content> filter(String title, String format, List<Integer> moduleIds) {
		
		Set<Content> titleContent = this.filterContentByTitle(title);
		Set<Content> formatContent = this.filterContentByFormat(format);
		Set<Content> moduleContent = this.filterContentBySubjects(moduleIds);
		//this is an AND search, if you want to do an OR search, just use the <set>.addAll() method instead of the Sets.intersection() method
		return Sets.intersection(titleContent, Sets.intersection(formatContent, moduleContent));
		
//		if(format != null && !format.equals("All") && !format.equals("")) {
//			contents = cr.findByFormat(format);
//		}
//		
//		if(title != null && !"".equals(title)) {
//			
//			if(contents == null) {
//				
//				contents = cr.findByTitleContaining(title);
//				
//			} else {
//				
//				copy = new HashSet<Content>(contents);
//				
//				for(Content c : copy) {
//					
//					if(!c.getTitle().toLowerCase().contains(title.toLowerCase()))
//						contents.remove(c);
//				}
//			}
//		}
//		
//		if(contents == null) {
//			contents = cs.getAllContent();
//		}
//		
//		if(modules != null && !modules.isEmpty()) {
//			
//			copy = new HashSet<Content>(contents);
//			Set<Link> linksInModules = lr.findByModuleIdIn(modules);
//			
//			boolean inModule;
//			
//			for(Content c : copy) {
//				
//				inModule = false;
//				
//				for(Link l : c.getLinks()) {
//					
//					if(linksInModules.contains(l)) {
//						
//						inModule = true;
//						break;
//					}
//				}
//				
//				if(!inModule) {
//					contents.remove(c);
//				}
//			}
//		}
//		
//		return contents;
	}

	@Override
	public Set<Content> filterContent(Set<Content> contents, Map<String, Object> filters) {
		
		Set<Content> titleContent = this.filterContentByTitle(filters.get("title").toString());
		Set<Content> formatContent = this.filterContentByFormat(filters.get("format").toString());
		
		ArrayList<Integer> moduleIdsList = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(filters.get("modules").toString(), ",");
		while (st.hasMoreTokens()) {
			moduleIdsList.add(Integer.parseInt(st.nextToken()));
		}
		
		Set<Content> moduleContent = this.filterContentBySubjects(moduleIdsList);
		//this is an AND search, if you want to do an OR search, just use the <set>.addAll() method instead of the Sets.intersection() method
		return Sets.intersection(titleContent, Sets.intersection(formatContent, moduleContent));
	}
//		Set<Content> copy;
//		
//		String title = (String) filters.get("title");
//		String format = (String) filters.get("format");
//		boolean isTitle = false;
//		boolean isFormat = false;
//		
//		if(title != null && !title.isEmpty()) {
//			isTitle = true;
//		}
//		if(format != null && !format.equals("All")) {
//			isFormat = true;
//		}
//			
//		if(isTitle || isFormat) {
//			copy = new HashSet<Content>(contents);
//			
//			for(Content c : copy) {
//				
//				if(isTitle && !c.getTitle().toLowerCase().contains(title.toLowerCase()))
//					contents.remove(c);
//				if(isFormat && !c.getFormat().equals(format))
//					contents.remove(c);
//			}
//		}
//			
//		ArrayList<Integer> ids = (ArrayList<Integer>) filters.get("modules");
//		
//		if(ids != null && !ids.isEmpty()) {
//
//			copy = new HashSet<Content>(contents);
//			
//			boolean inModule = false;
//			
//			for(Content c : copy) {
//				
//				inModule = false;
//				
//				for(Link l : c.getLinks()) {
//					
//					if(ids.contains(l.getModuleId())) {
//						inModule = true;
//						break;
//					}
//				}
//				
//				if(!inModule)
//					contents.remove(c);
//			}
//		}
//		
//		return contents;
//	}
}

	
