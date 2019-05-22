package com.revature.services;

import java.util.List;

import com.revature.entities.Content;

public interface ContentService {

	public boolean createContent(Content content);
	
	public List<Content> getAllContent();
	
	public Content getContentById(int id);
	
	public boolean updateContent(Content content);
	
	public boolean addContentTags(String[] subject);
	
	public boolean removeContentTags(String[] subject);
	
	public boolean deleteContent(int id);
}
