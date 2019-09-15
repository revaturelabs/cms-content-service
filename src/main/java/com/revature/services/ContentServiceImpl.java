package com.revature.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.Content;
import com.revature.exceptions.InvalidContentException;
import com.revature.exceptions.InvalidContentId;
import com.revature.repositories.ContentRepository;
import com.revature.repositories.ModuleRepository;
import com.revature.util.LogException;

@Service
@Transactional
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	ContentRepository cr;
	@Autowired 
	ModuleRepository mr;

	/**
	 * Creates a new Content in the database
	 */
	@LogException
	@Override
	public Content createContent(Content content) {	
		//set date created and date modified
		if(content.getDateCreated() == 0L && content.getLastModified() == 0L) {
			content.setDateCreated(System.currentTimeMillis());
			content.setLastModified(System.currentTimeMillis());
		}
		//save the content to the database
		content = cr.save(content);
		//return the saved content
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
		Content content = (Content) cr.findById(id);
		return content;
	}
	
	/**
	 * Description - Updates an existing content
	 * @param newContent - content received from client request
	 * @return - the updated content
	 * @throws - NullPointerException - if the newContent parameter is null or if the requested content to be updated doesn't exist in content Repository
	 */
	@Override
	@LogException
	public Content updateContent(Content newContent) {
		
		if(newContent == null)
			throw new InvalidContentException("updateContent, newContent is null");
		
		if(Character.isDigit(newContent.getId()))
			throw new InvalidContentId("updateContent, newContent does not have a valid Id");
		Content oldContent = this.getContentById(newContent.getId());
		
		if(oldContent == null)
			throw new InvalidContentException("updateContent, oldContent is null");

		return cr.save(newContent);
	}
	
	/**
	 * gets formats and cycles through all elements in DB to return
	 * how many times each format is used. 
	 * Much faster than using a findByFormat
	 * */
	@Override
	@LogException
	public Map<String, Integer> getContentByFormat(String[] formats) {
		Map<String, Integer> numList = new HashMap<>();
		Set<Content> allContent = (Set<Content>) cr.findAll();
		
		for(Content content : allContent) {
			if(numList.containsKey(content.getFormat()))
				numList.put(content.getFormat(), numList.get(content.getFormat()) + 1);
			else
				numList.put(content.getFormat(), 1);
		}
		return numList;
	}
	
	@Override
	@LogException
	public Map<String, Integer> getContentByFormat(Set<Content> contents) {
		Map<String, Integer> numList = new HashMap<>();
		
		for(Content c : contents) {
			if(numList.containsKey(c.getFormat()))
				numList.put(c.getFormat(), numList.get(c.getFormat()) + 1);
			else
				numList.put(c.getFormat(), 1);
		}
		return numList;
	}


	@Override
	public void deleteContent(Content content) {
		if(content != null) {
			cr.delete(content);
		}
	}
}
