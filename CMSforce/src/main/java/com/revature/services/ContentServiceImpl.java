package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.repositories.LinkRepository;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.util.LogException;

@Service
@Transactional
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	ContentRepository cr;
	@Autowired 
	LinkRepository lr;
	@Autowired
	ModuleRepository mr;

	//@LogException
	@Override
	public Content createContent(Content content) {
		
		
		
		Set<Link> links = content.getLinks();
		
		//If null links set, fail
		if (links == null) {
			throw new NullPointerException();
		}
		
		content.setLinks(null);
		content = cr.save(content);
		
		for(Link link : links) {
			link.setContentId(content.getId());
		}
		
		lr.saveAll(links);
		
		content.setLinks(links);		

		return content;
	}
	

	@Override
	//@LogException
	public Set<Content> getAllContent() {
			Set<Content> contents = new HashSet<Content>();
			cr.findAll().forEach(contents :: add);
			return contents;
		}
	
	@Override
	//@LogException
	public Content getContentById(int id) {	
			return cr.findById(id).iterator().next();		
	}

}
