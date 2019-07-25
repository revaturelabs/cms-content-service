package com.revature.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Content;
import com.revature.entities.Link;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.LinkRepository;
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

	/**
	 * createContent first inserts the content to the database
	 * then iterates over the set of links that are within the content
	 * and replaces the fk of content id with the correct content id
	 * then adds the set of links to the link table. 
	 */
	@LogException
	@Override
	public Content createContent(Content content) {
		
		Set<Link> links = content.getLinks();
		
		
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
		
		/**
		 * The createContent method was modified to generate the dateCreated field for the Content entity
		 * if the content object doesn't have values in those fields.
		 */
		if(content.getDateCreated() == 0L && content.getLastModified() == 0L) {
		content.setDateCreated(System.currentTimeMillis());
		
		content.setLastModified(System.currentTimeMillis());
		}
		
		return content;
	}
	

	/**
	 * Get all the content from the database and passes a set of content objects
	 */
	@Override
	@LogException
	public Set<Content> getAllContent() {
			Set<Content> contents = new HashSet<>();
			cr.findAll().forEach(contents :: add);
			return contents;
		}
	
	/**
	 * get content from the data base that match a passed in id
	 * then returns the content with that id.
	 */
	@Override
	@LogException
	public Content getContentById(int id) {	
			return cr.findById(id).iterator().next();		
	}


	/*
	 * gets formats and cycles through all elements in DB to return
	 * how many time each format is used. 
	 * Much faster than using a findByFormat
	 * */
	@Override
	@LogException
	public ArrayList<Integer> getContentByFormat(String[] formats) {
		ArrayList<Integer> numList = new ArrayList<>();
		ArrayList<Content> all = (ArrayList<Content>) cr.findAll();
		int num= 0;
		
		for(int j = 0; j<formats.length; j++) {
			for(int i = 0; i < all.size(); i++) {
				if(all.get(i).getFormat().equals(formats[j])) {
					num++;
				}
			}
			numList.add(num);
			num = 0;
		}
		return numList;
	}
	
	
	
	
	@Override
	@LogException
	public Map<String, Integer> getContentByFormat(String[] formats, Set<Content> allContent) {
		
		Map<String, Integer> contentMap = new HashMap<String, Integer>();
		
		for(Content c : allContent) {
//			for (int j = 0; j<formats.length; j++)
//			{
//				if (c.getFormat().equals(formats[j]))
//				{
//					num++;
//					break;
//				}
//			}
//			numList.add(num);
//			num = 0;
			
			if(contentMap.containsKey(c.getFormat())) {
				contentMap.put(c.getFormat(), contentMap.get(c.getFormat()) + 1);
			} else {
				contentMap.put(c.getFormat(), 1);
			}
		}
		
		return contentMap;
	}

}
