package com.revature.services;

import java.util.ArrayList;
import java.util.Arrays;
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
			// creating content using CRUDrepository
			return cr.save(content); 
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	@Override
	public Set<Content> getAllContent() {
		
		try {
			// initialize 
			Set<Content> contents = new HashSet<Content>();
			
			// populate contents Set via the iterable<Content> returned from cr.findAll()
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
			// getting and returning content by id via CRUDrepository
			return cr.findById(id).get();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Content updateContent(Content content) {
		try {
			// the following "if" prevents creation (instead of update) by checking that the 
			// content isn't already in the database before saving
			if (cr.findById(content.getId()) != null) { 
				return cr.save(content); // CRUDrepository create content
				
			} else {
				return null;	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Content addContentAndContentModules(Content content, ContentModule[] contentModules) {
		
		try {
			// calls createContent in this service, return updated content id, as its stored in the database
			content = createContent(content); 
			
			// sets the content id foreign key in each of the ContentModules to reflect the above creation
			for (ContentModule contentModule : contentModules) {
				contentModule.setFkContent(content.getId());
			}
			
			// CRUDrepository create. Needs iterable. Hence, Arrays.asList()
			cmr.saveAll(Arrays.asList(contentModules)); 
			
			return content;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Content addContentModules(Content content, String[] subjects) {
		
		// initialize modules to be populated via the subjects String[]
		Set<Module> modules = new HashSet<>();
		
		// finds the corresponding module for each subject in subjects
		for (String subject : subjects) {
			// populates modules from the iterable returned from the CRUDrepository
			// Note that the iterable should consist of only one member
			mr.findBysubject(subject).forEach(modules :: add); 
		}
		
		// addContentModules requires an array of modules, the following modulesArr is used for that purpose
		Module[] modulesArr = new Module[modules.size()];
		
		// basically a wrapper for the function it overloads
		return addContentModules(content, modules.toArray(modulesArr));		
	}

	@Override
	public Content addContentModules(Content content, Module[] modules) {
		
		try {
			// initialize
			Set<ContentModule> contentModules = new HashSet<ContentModule>(); // this will contain the contentModules to be saved
			Set<ContentModule> contentModulesByContentID = new HashSet<ContentModule>(); // this will contain all ContentModules for a content id
			
			// this populates contentModulesByContentID from the iterable returned from the cmr CRUDrepository
			cmr.findByfkContent(content.getId()).forEach(contentModulesByContentID :: add);
			
			// this loop creates and adds ContentModules to the contentModules Set, which will eventually be saved to the DB
			for (Module module : modules) {
				// this flag allows us to prevent the addition of ContentModules 
				// with the same foreign key entries as another entry in the DB.
				// Note: Maybe it's better to fail to update if one of the ContentModules are already in the DB?
				boolean alreadyExists = false;
				
				// this loop just marks the flag if it finds that there's already a ContentModule with the fk pair
				for (ContentModule contentModule : contentModulesByContentID) {
					
					if (contentModule.getFkModule() == module.getId()) { // Note no need to check content id
						alreadyExists = true;
						break; // no point in continuing the loop if alreadyExists is marked to true
					}
				}
				
				if (!alreadyExists) {
					
					// Note that the affiliation is set to "relevantTo" by default
					contentModules.add(new ContentModule(0, content.getId(), module.getId(), "relevantTo", 
							new ArrayList<Module>(), new ArrayList<Content>()));
				}

			} 
			cmr.saveAll(contentModules); // creates all of the content modules that weren't already in the DB, via CRUDrepository
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
		return content; 
	}

	@Override
	public Content removeContentModules(Content content, String[] subjects) {
		
		// initialize modules to be populated via the subjects String[]
		Set<Module> modules = new HashSet<>();
		
		// finds the corresponding module for each subject in subjects
		for(String subject : subjects) {
			// populates modules from the iterable returned from the CRUDrepository
			// Note that the iterable should consist of only one member
			mr.findBysubject(subject).forEach(modules :: add);
		}
		
		// removeContentModules requires an array of modules, the following modulesArr is used for that purpose
		Module[] modulesArr = new Module[modules.size()];
		
		// basically a wrapper for the function it overloads
		return removeContentModules(content, modules.toArray(modulesArr));	
	}

	@Override
	public Content removeContentModules(Content content, Module[] modules) {	
		
		try {
			// initialize
			Set<ContentModule> contentModules = new HashSet<ContentModule>(); // this will contain the contentModules to be saved
			Set<ContentModule> contentModulesByContentID = new HashSet<ContentModule>(); // this will contain all ContentModules for a content id
			
			// this populates contentModulesByContentID from the iterable returned from the cmr CRUDrepository
			cmr.findByfkContent(content.getId()).forEach(contentModulesByContentID :: add);
			
			// this loop gets the ContentModules with each of the fk pairs and adds them to the contentModules Set
			for(Module module : modules) {
				for(ContentModule contentModule : contentModulesByContentID) {
					if(contentModule.getFkModule() == module.getId()) { // Note: no need to check content id
						contentModules.add(contentModule);
						break; // there should only be one ContentModule with that fk pair, so breaking here is more efficient
					}
				}
			} 
			
			// deletes all of the ContentModules in the contentModules Set in DB via CRUDrepository
			cmr.deleteAll(contentModules);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
		return content; // returns the same content that was passed without changes	
	}

	@Override
	public boolean deleteContent(int id) {
		try {
			// creating content using CRUDrepository
			cr.deleteById(id);
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
