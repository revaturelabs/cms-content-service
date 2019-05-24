package com.revature.services;
import java.util.Set;
import com.revature.entities.Content;
import com.revature.entities.Module;
public interface ContentService {
    public Content createContent(Content content);
    
    public Set<Content> getAllContent();
    
    public Content getContentById(int id);
    
    public Content updateContent(Content content);
    
    public Content addContentModules(Content content, String[] subject);
    
    public Content addContentModules(Content content, Module[] modules);
    
    public Content removeContentModules(Content content, String[] subject);
    
    public Content removeContentModules(Content content, Module[] modules);
    
    public boolean deleteContent(int id);
}
