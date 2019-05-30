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

	@Override
	public Set<Content> filterContentBySubjects(List<Integer> moduleIds) {
		// contents set to return
		Set<Content> contents = new HashSet<Content>();
		
		Set<Integer> ids = new HashSet<>();
		Set<Integer> ids_temp = new HashSet<>();
		
		Set<Link> LinksByModuleID = lr.findByModuleId(moduleIds.get(0));
		for(Link link : LinksByModuleID) {
			ids.add(link.getContentId());
		}
		for(int i = 1; i < moduleIds.size(); i++) {
			LinksByModuleID = lr.findByModuleId(moduleIds.get(i));
			for(Link link : LinksByModuleID) {
				ids_temp.add(link.getContentId());
			}
			ids.retainAll(ids_temp);
		}
		
		cr.findAllById(ids).forEach(contents :: add);
		
		return contents;	
	}
	
	@Override
	public Set<Content> getContentByModuleId(int ModuleId) {
		Set<Link> links = lr.findByModuleId(ModuleId);
		int contentId = links.iterator().next().getContentId();
		Set<Content> contents = cr.findById(contentId);
		System.out.println(contents);
		return contents;
	}
	
	@Override
	public Set<Content> filter(String title, String format, List<Integer> modules) {
		
		Set<Content> selectedContent = new HashSet<Content>();
		Set<Content> tempSet = new HashSet<Content>();
		
		if (modules.size() == 0) {
			selectedContent = csi.getAllContent();
		}
		else {
			selectedContent = this.filterContentBySubjects(modules);
		}
		
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

	
