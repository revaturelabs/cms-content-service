package com.revature.services;

import java.util.HashSet;
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

	@Override
	public Set<Content> filterContentByTitle(String title) {
		return cr.findByTitle(title); 
	}

	@Override
	public Set<Content> filterContentByFormat(String format) {
		return cr.findByFormat(format); 
	}

	@Override
	public Set<Content> filterContentBySubjects(int[] moduleIds) {
		// contents set to return
		Set<Content> contents = new HashSet<Content>();
		
		Set<Integer> ids = new HashSet<>();
		Set<Integer> ids_temp = new HashSet<>();
		
		Set<Link> LinksByModuleID = lr.findByModuleId(moduleIds[0]);
		for(Link link : LinksByModuleID) {
			ids.add(link.getContentId());
		}
		for(int i = 1; i < moduleIds.length; i++) {
			LinksByModuleID = lr.findByModuleId(moduleIds[0]);
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

}
