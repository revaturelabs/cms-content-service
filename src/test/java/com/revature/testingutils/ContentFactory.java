package com.revature.testingutils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.entities.Content;
import com.revature.entities.Link;

/**
 * This is a utility class meant to reduce the repetition caused by
 * creating Content instances throughout unit testing. This class 
 * provides a basic Content instance and it's relationship with a
 * Link object instance.
 */
public class ContentFactory {

	//content field values
	public static final String title = "TestTitle",
							    format = "TestFormat",
							    description = "Test description",
							    url = "http://test.com";
	public static final Integer id = 117,
								moduleId = 1,
								linkId = 1;
	
	/**
	 * Returns a new instance of a content object with the default
	 * constant values provided in this class
	 * @return - new instance of a content object
	 */
	public static Content getContent () {
		//generate link set
		Set<Link> links = new HashSet<Link> ();
		Link link = new Link (linkId, id, moduleId,"link-affiliation");
		links.add(link);
		
		//generate content object
		return new Content (id, title, format, description, url, links, 1L, 1L);
	}
	
	/**
	 * Returns a list of modules meant to be related to the default link
	 * data.
	 * @return - List of module ids related to the sample content
	 */
	public static List<Integer> getModules () {
		List<Integer> modules = new ArrayList<Integer> ();
		modules.add(moduleId);
		return modules;
	}
}
