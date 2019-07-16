package com.revature.services;
import java.util.Set;
import com.revature.entities.Content;
public interface ContentService {
	
    public Content createContent(Content content);
    
    public Set<Content> getAllContent();
    
    public Content getContentById(int id);
    
}
