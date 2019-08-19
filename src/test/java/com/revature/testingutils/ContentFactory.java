package com.revature.testingutils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.entities.Content;
import com.revature.entities.Link;

public class ContentFactory {

	//content field values
	public static final String title = "TestTitle",
							    format = "TestFormat",
							    description = "Test description",
							    url = "http://test.com";
	public static final Integer id = 117;
	
	public static Content getContent () {
		Set<Link> links = new HashSet<Link> ();
		Link link = new Link (1,id,1,"link-affiliation");
		links.add(link);
		
		
		Content content = new Content (id, title, format, description, url, links, 1L, 1L);
		return content;
	}
	
	public static List<Integer> getModules () {
		List<Integer> modules = new ArrayList<Integer> ();
		modules.add(1);
		return modules;
	}
	

}
