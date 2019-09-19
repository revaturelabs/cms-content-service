package com.revature.services;
import java.util.Map;
import java.util.Set;

import com.revature.entities.Content;
import com.revature.entities.Link;
public interface ContentService {
	
	public Content createContent(Content content);
    
    public Set<Content> getAllContent();
    
    public Content getContentById(int id);
    
    public Map<String, Integer> getFormatCount(String[] format);   
    
    public Map<String, Integer> getFormatCount(Set<Content> contents);   
    
    public Content updateContent(Content newContent);

    public void deleteContent(Content content);

	public Set<Link> getLinksByContentId(int id);
}
