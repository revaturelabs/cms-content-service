package com.revature.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.ModuleRepository;

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

	@Override
	public Set<Content> filterContentByTitle(String title) {
		return cr.findByTitle(title); 
	}

	@Override
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
	public Set<Content> filterContentBySubjects(List<Integer> moduleIds) {
		/**
		 * a set of content that will be returned
		 */
		Set<Content> contents = new HashSet<Content>();
		/**
		 * Temporary sets to hold integers while searching
		 */
		Set<Integer> ids = new HashSet<>();
		Set<Integer> ids_temp = new HashSet<>();
		
		/**
		 * create a set of links that have a moduleID of the first element
		 * of the list that is passed into the method and adds it to 
		 * a temporary set of ids.
		 */
		Set<Link> LinksByModuleID = lr.findByModuleId(moduleIds.get(0));
		for(Link link : LinksByModuleID) {
			ids.add(link.getContentId());
		}
		/**
		 * loop over the array that was passed in getting all the links
		 * assosiated to each element and only keeping those who share 
		 * a that of the list that was already made.
		 */
		for(int i = 1; i < moduleIds.size(); i++) {
			LinksByModuleID = lr.findByModuleId(moduleIds.get(i));
			for(Link link : LinksByModuleID) {
				ids_temp.add(link.getContentId());
			}
			ids.retainAll(ids_temp);
		}
		
		/**
		 * this finds all the content by id for each element in the 
		 * temp list of ids and adds them to the content set that will 
		 * be returned.
		 */
		cr.findAllById(ids).forEach(contents :: add);
		
		return contents;	
	}
	
	/**
	 * getContentByModuleID takes in a int that is a module id and 
	 * returns a set of content that have the inputed value as 
	 * the moduleID.
	 */
	@Override
	public Set<Content> getContentByModuleId(int ModuleId) {
		Set<Link> links = lr.findByModuleId(ModuleId);
		int contentId = links.iterator().next().getContentId();
		Set<Content> contents = cr.findById(contentId);
		System.out.println(contents);
		return contents;
	}
	/**
	 * Filter takes a content title, content format and/or
	 * a list of Integers that represent module ids.
	 * It will then return a set of content that contains all content that 
	 * matches all 3 inputs using AND logic.
	 * If an input is empty it is ignored and is not part of the logic.
	 */
	@Override
	public Set<Content> filter(String title, String format, List<Integer> modules) {
		
		Set<Content> selectedContent = new HashSet<Content>();
		Set<Content> tempSet = new HashSet<Content>();
		/**
		 * check if the array passed in was empty and populating the initial 
		 * set of content
		 */
		if (modules.size() == 0) {
			selectedContent = csi.getAllContent();
		}
		else {
			selectedContent = this.filterContentBySubjects(modules);
		}
		/**
		 * check if title is empty if it isn't it will remove any content 
		 * from the set that does not match the title.
		 */
		if (!title.equalsIgnoreCase("")) {
			Iterator<Content> contentIterator = selectedContent.iterator();
			
			while(contentIterator.hasNext()) {
				Content tempContent = contentIterator.next();
				if(tempContent.getTitle().contains(title)) {
					tempSet.add(tempContent);
				}
			}
			selectedContent = tempSet;
		}
		
		/**
		 * check if the format is empty, if it isn't remove all content
		 * that the format does not match what is passed in. 
		 */
		if (!format.equalsIgnoreCase("")) {
			Iterator<Content> contentIterator = selectedContent.iterator();
			while (contentIterator.hasNext()) {
				Content tempContent = contentIterator.next();
				if (tempContent.getFormat().contains(format)) {
					tempSet.add(tempContent);
				}
			}
			selectedContent = tempSet;
		}
		
		return selectedContent;
	}
}

	
