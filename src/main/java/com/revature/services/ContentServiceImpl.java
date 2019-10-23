<<<<<<< Updated upstream
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
import com.revature.exceptions.InvalidContentException;
import com.revature.exceptions.InvalidContentId;
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
	ModuleRepository mr;
	@Autowired
	LinkRepository lr;

	/**
	 * create new content and store in database
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
		return cr.findById(id);
	}
	
	/**
	 * gets formats and cycles through all elements in DB to return
	 * how many times each format is used. 
	 * Much faster than using a findByFormat
	 * */
	@Override
	@LogException
	public Map<String, Integer> getFormatCount(String[] formats) {
		Set<Content> allContent = (Set<Content>) cr.findAll();
		Map<String, Integer> formatCountMapping = new HashMap<>();
		
		//populate the map with the formats as keys and initial occurrence values of 0
		for (String format : formats) {
			formatCountMapping.put(format, 0);
		}
		//iterate through each content, adding one to the count of the appropriate
			//format each time through
		for(Content content : allContent) {
			String curFormat = content.getFormat();
			//We are only counting the formats asked of us via the passed in formats parameter
			if (formatCountMapping.containsKey(curFormat)) {
				int newOccurence = formatCountMapping.get(curFormat) + 1;
				formatCountMapping.put(curFormat, newOccurence);
			}
		}
		return formatCountMapping;
	}
	
	@Override
	@LogException
	public Map<String, Integer> getFormatCount(Set<Content> contents) {
		Map<String, Integer> formatCountMapping = new HashMap<>();
		
		for(Content c : contents) {
			if(formatCountMapping.containsKey(c.getFormat())) {
				formatCountMapping.put(c.getFormat(), formatCountMapping.get(c.getFormat()) + 1);
			} else {
				formatCountMapping.put(c.getFormat(), 1);
			}
		}
		return formatCountMapping;
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
		if(newContent == null) {
			throw new InvalidContentException("updateContent, newContent is null");
		}
		if (Character.isDigit(newContent.getId())) {
			throw new InvalidContentId("updateContent, newContent does not have a valid id");
		}
		return cr.save(newContent);
	}

	@Override
	public void deleteContent(Content content) {
		if(content != null) {
			cr.delete(content);
		}
	}

	@Override
	public Set<Link> getLinksByContentId(int id) {
		return lr.findByContentId(id);
	}


	@Override
	public List<Link> updateLinksByContentId(int id, List<Link> links) {
		for (Link link : links) {
			lr.save(link);
		}
		return links;
	}


	@Override
	public List<Link> createLinksByContentId(int id, List<Link> links) {
		List<Link> savedLinks = new ArrayList<Link>();
		for (Link link : links) {
			savedLinks.add(lr.save(link));
		}
		return savedLinks;
	}

}
=======
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
import com.revature.exceptions.InvalidContentException;
import com.revature.exceptions.InvalidContentId;
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
	ModuleRepository mr;
	@Autowired
	LinkRepository lr;

	/**
	 * create new content and store in database
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
		return cr.findById(id);
	}
	
	/**
	 * gets formats and cycles through all elements in DB to return
	 * how many times each format is used. 
	 * Much faster than using a findByFormat
	 * */
	@Override
	@LogException
	public Map<String, Integer> getFormatCount(String[] formats) {
		Set<Content> allContent = (Set<Content>) cr.findAll();
		Map<String, Integer> formatCountMapping = new HashMap<>();
		
		//populate the map with the formats as keys and initial occurrence values of 0
		for (String format : formats) {
			formatCountMapping.put(format, 0);
		}
		//iterate through each content, adding one to the count of the appropriate
			//format each time through
		for(Content content : allContent) {
			String curFormat = content.getFormat();
			//We are only counting the formats asked of us via the passed in formats parameter
			if (formatCountMapping.containsKey(curFormat)) {
				int newOccurence = formatCountMapping.get(curFormat) + 1;
				formatCountMapping.put(curFormat, newOccurence);
			}
		}
		return formatCountMapping;
	}
	
	@Override
	@LogException
	public Map<String, Integer> getFormatCount(Set<Content> contents) {
		Map<String, Integer> formatCountMapping = new HashMap<>();
		
		for(Content c : contents) {
			if(formatCountMapping.containsKey(c.getFormat())) {
				formatCountMapping.put(c.getFormat(), formatCountMapping.get(c.getFormat()) + 1);
			} else {
				formatCountMapping.put(c.getFormat(), 1);
			}
		}
		return formatCountMapping;
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
		if(newContent == null) {
			throw new InvalidContentException("updateContent, newContent is null");
		}
		if (cr.findById(newContent.getId()) == null) {
			throw new InvalidContentId("updateContent, newContent does not have a valid id");
		}
		return cr.save(newContent);
	}

	@Override
	public void deleteContent(Content content) {
		if(content != null) {
			cr.delete(content);
		}
	}

	@Override
	public Set<Link> getLinksByContentId(int id) {
		
		return lr.findByContentId(id);
	}


	@Override
	public List<Link> updateLinksByContentId(int id, List<Link> links) {
		for (Link link : links) {
			lr.save(link);
		}
		return links;
	}


	@Override
	public List<Link> createLinksByContentId(int id, List<Link> links) {
		List<Link> savedLinks = new ArrayList<Link>();
		for (Link link : links) {
			savedLinks.add(lr.save(link));
		}
		return savedLinks;
	}

}
>>>>>>> Stashed changes
