package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Content;
import com.revature.entities.ContentModule;
import com.revature.entities.Module;
import com.revature.repositories.ContentModuleRepository;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.ModuleRepository;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	ContentRepository cr;
	@Autowired
	ModuleRepository mr;
	@Autowired
	ContentModuleRepository cmr;

	@Override
	public Set<Content> filterContentByTitle(String title) {
		try {
			return cr.findByTitle(title);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Content> filterContentByFormat(String format) {
		try {
			return cr.findByFormat(format);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Content> filterContentBySubjects(String[] subjects) {
		// TODO ***** This Method can probably be simplified *****
		
		try {
			Set<Content> contents = new HashSet<Content>();
			
			// subjects to modules []
			Set<Module> modules = new HashSet<>();
			for(String subject : subjects) {
				mr.findBysubject(subject).forEach(modules :: add);
			}
			Module[] modulesArr = new Module[modules.size()];
			modules.toArray(modulesArr);
			
			// iterating to form a list of content ids that need to be found and returned
			List<Integer> ids = new ArrayList<>();
			for(Module module : modules) {
				Set<ContentModule> contentModulesByModuleID = new HashSet<>(); 
				cmr.findByfkModule(module.getId()).forEach(contentModulesByModuleID :: add);
				for(ContentModule contentModule : contentModulesByModuleID) {
					ids.add(contentModule.getFkContent());
				}
			}
			
			cr.findAllById(ids).forEach(contents :: add);
			
			return contents;	
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
