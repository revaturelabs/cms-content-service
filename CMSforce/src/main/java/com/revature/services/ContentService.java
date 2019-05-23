package com.revature.services;

import java.util.List;

import com.revature.entities.Content;

public interface ContentService {

	public Content createContent(Content content);
	
	public List<Content> getAllContent();
	
	public Content getContentById(int id);
	
	public Content updateContent(Content content);
	
	public Content addContentTags(Content content, String[] subject);
	
	public Content addContentTags(Content content, Module[] modules);
	
	public Content removeContentTags(Content content, String[] subject);
	
	public Content removeContentTags(Content content, Module[] modules);
	
	public void deleteContent(int id);
}
