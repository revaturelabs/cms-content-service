package com.revature.services;
import java.util.Set;
import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.entities.Module;
public interface ContentService {
	
    public Content createContent(Content content);
    
    public Set<Content> getAllContent();
    
    public Content getContentById(int id);
    
    public Content updateContent(Content content);
    
    public Content addContentAndLinks(Content content, Link[] Link);
    
    public Content addLinks(Content content, String[] subject);
    
    public Content addLinks(Content content, Module[] modules);
    
    public Content removeLinks(Content content, String[] subject);
    
    public Content removeLinks(Content content, Module[] modules);
    
    public boolean deleteContent(int id);
}
