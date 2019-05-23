package com.revature.services;

import java.util.List;

import com.revature.entities.Content;
import com.revature.entities.Module;

public interface ContentService {

	public Content createContent(Content content);
	
	public List<Content> getAllContent();
	
	public Content getContentById(int id);
	
	public Content updateContent(Content content);
	
	public boolean addContentTags(Content content, String[] subject);
	
	public boolean addContentTags(Content content, Module[] modules);
	
	public boolean removeContentTags(Content content, String[] subject);
	
	public boolean removeContentTags(Content content, Module[] modules);
	
	public void deleteContent(int id);
}
