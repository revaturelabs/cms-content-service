package com.revature.services;
import java.util.Set;
import com.revature.entities.Content;
public interface ContentService {
	
    public Content createContent(Content content);
    
    public Iterable<Content> getAllContent();
    
    public Content getContentById(int id);
    
}
