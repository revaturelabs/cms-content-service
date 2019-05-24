package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
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
public class ContentServiveImpl implements ContentService {
	
	@Autowired
	ContentRepository cr;
	@Autowired 
	ContentModuleRepository cmr;
	@Autowired
	ModuleRepository mr;

	@Override
	public Content createContent(Content content) {
		try {
			return cr.save(content);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	@Override
	public Set<Content> getAllContent() {
		try {
			Set<Content> contents = new HashSet<Content>();
			cr.findAll().forEach(contents :: add);
			return contents;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Content getContentById(int id) {
		try {		
			return cr.findById(id).get();	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Content updateContent(Content content) {
		try {
			// following if prevents creation
			if(cr.findById(content.getId()) != null) { 
				return cr.save(content);
			} else {
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Content addContentModules(Content content, String[] subjects) {
		// array of subjects change to array of modules
		Set<Module> modules = new HashSet<>();
		for(String subject : subjects) {
			mr.findBysubject(subject).forEach(modules :: add);
		}
		Module[] modulesArr = new Module[modules.size()];
		
		// basically a wrapper for the function it overloads
		return addContentModules(content, modules.toArray(modulesArr));		
	}

	@Override
	public Content addContentModules(Content content, Module[] modules) {
		try {
			Set<ContentModule> contentModules = new HashSet<ContentModule>();
			Set<ContentModule> contentModulesByContentID = new HashSet<ContentModule>();
			cmr.findByfkContent(content.getId()).forEach(contentModulesByContentID :: add);
			for(Module module : modules) {
				boolean alreadyExists = false;
				for(ContentModule contentModule : contentModulesByContentID) {
					if(contentModule.getFkModule() == module.getId()) {
						alreadyExists = true;
					}
				}
		
				if(!alreadyExists) {
					contentModules.add(new ContentModule(0, content.getId(), module.getId(), "relevantTo", 
							new ArrayList<Module>(), new ArrayList<Content>()));
				}

			} 
			cmr.saveAll(contentModules);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
		return content; 
	}

	@Override
	public Content removeContentModules(Content content, String[] subjects) {
		// array of subjects change to array of modules
		Set<Module> modules = new HashSet<>();
		for(String subject : subjects) {
			mr.findBysubject(subject).forEach(modules :: add);
		}
		Module[] modulesArr = new Module[modules.size()];
		
		// basically a wrapper for the function it overloads
		return removeContentModules(content, modules.toArray(modulesArr));	
	}

	@Override
	public Content removeContentModules(Content content, Module[] modules) {	
		try {
			Set<ContentModule> contentModules = new HashSet<ContentModule>();
			Set<ContentModule> contentModulesByContentID = new HashSet<ContentModule>();
			cmr.findByfkContent(content.getId()).forEach(contentModulesByContentID :: add);
			for(Module module : modules) {
				for(ContentModule contentModule : contentModulesByContentID) {
					if(contentModule.getFkModule() == module.getId()) {
						contentModules.add(contentModule);
					}
				}
			} 
			cmr.deleteAll(contentModules);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
		return content; 	
	}

	@Override
	public boolean deleteContent(int id) {
		try {
			cr.deleteById(id);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
