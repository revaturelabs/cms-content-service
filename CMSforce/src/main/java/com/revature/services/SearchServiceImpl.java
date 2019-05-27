package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
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
	LinkRepository cmr;

	@Override
	public Set<Content> filterContentByTitle(String title) {
		try {
			return cr.findByTitle(title); // uses CRUDrepository, semi-custom findByTitle method
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Content> filterContentByFormat(String format) {
		try {
			return cr.findByFormat(format); // uses CRUDrepository, semi-custom findByFormat method
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Content> filterContentBySubjects(String[] subjects) {
		// TODO ***** This Method can probably be simplified *****
		
		try {
			// initialize contents, to be returned
			Set<Content> contents = new HashSet<Content>();
			
			// convert String[] subjects to Module[] modules
			Set<Module> modules = new HashSet<>();
			for(String subject : subjects) {
				// adds the module corresponding to each subject via the iterable<Module> returned from mr.findBysubject(subject)
				mr.findBysubject(subject).forEach(modules :: add); 
			}
			
			// this moves the modules Set into an array
			Module[] modulesArr = new Module[modules.size()];
			modules.toArray(modulesArr);
			
			// this iterates to form a list of content ids that need to be read from the DB and returned as a Content[]
			List<Integer> ids = new ArrayList<>();
			for(Module module : modules) {
				// initializing and populating a Set of ContentModules by module id, using an iterable from the CRUDrepository again
				Set<Link> LinksByModuleID = new HashSet<>(); 
				cmr.findByModuleId(module.getId()).forEach(LinksByModuleID :: add);
				
				// merely adding those ids to the list of content ids
				for(Link Link : LinksByModuleID) {
					ids.add(Link.getContentId());
				}
			}
			
			// using the CRUDrepository to find all contents corresponding to the list of 
			// content ids, then populating the content Set via an iterable<Content> again
			cr.findAllById(ids).forEach(contents :: add);
			
			return contents;	
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Content> getContentByModuleId(int ModuleId) {
		Set<Link> links = cmr.findByModuleId(ModuleId);
		int contentId = links.iterator().next().getContentId();
		Set<Content> contents = cr.findById(contentId);
		System.out.println(contents);
		return contents;
	}
	

}
