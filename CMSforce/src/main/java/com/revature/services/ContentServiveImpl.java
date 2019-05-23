package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.entities.Content;
import com.revature.entities.ContentModule;
import com.revature.entities.Module;
import com.revature.repositories.ContentModuleRepository;
import com.revature.repositories.ContentRepository;

public class ContentServiveImpl implements ContentService {
	
	@Autowired
	ContentRepository cr;
	@Autowired 
	ContentModuleRepository cmr;

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
	public List<Content> getAllContent() {
		try {
			List<Content> contents = new ArrayList<Content>();
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
			return cr.findOne(id);	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Content updateContent(Content content) {
		try {
			// following if prevents creation
			if(cr.findOne(content.getId()) != null) { 
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
	public boolean addContentTags(Content content, String[] subject) {
		return false;
	}

	@Override
	public boolean addContentTags(Content content, Module[] modules) {
		try {
			// using the following list will make only one call to the database to save, more efficient vs saving in the loop
			List<ContentModule> contentModules = new ArrayList<ContentModule>();
			for(Module module: modules) {
				contentModules.add(new ContentModule(0, content.getId(), module.getId(), "relevantTo", 
						new ArrayList<Module>(), new ArrayList<Content>()));
			} 
			cmr.save(contentModules); // TODO what if they're already in the table??!
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
		return true; 
	}

	@Override
	public boolean removeContentTags(Content content, String[] subject) {
		
		return false;
	}

	@Override
	public boolean removeContentTags(Content content, Module[] modules) {
		try {
			List<ContentModule> contentModules = new ArrayList<ContentModule>();
			for(Module module: modules) {
				// TODO need ContentModuleRepository to have find by (content id, module id)!!
				// Find ContentModules by content id
				// add to contentModules list if module id is found
			}
			// delete all for contentModules list
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
		return true;
	}

	@Override
	public boolean deleteContent(int id) {
		try {
			cr.delete(id);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
