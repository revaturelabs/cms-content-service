package com.revature.services;
import java.util.Map;
import java.util.Set;

import com.revature.entities.Content;
public interface ContentService {
	
    public Content createContent(Content content);
    
    public Set<Content> getAllContent();
    
    public Content getContentById(int id);
    
    public Map<String, Integer> getContentByFormat(String[] format);   
    
    public Map<String, Integer> getContentByFormat(Set<Content> contents);   
    
    public Content updateContent(Content newContent);
    
    public Set<Content> getAllContentMinusLinks();
    
    public void deleteContent(Content content);
}
