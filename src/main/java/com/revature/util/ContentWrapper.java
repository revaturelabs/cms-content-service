package com.revature.util;

import java.util.Arrays;

import com.revature.entities.Content;

public class ContentWrapper { //Bean used to attach an array of links to content
	private Content content;
	// private ContentPlusModules[] links;
	
	public ContentWrapper() {
		super();
	}

	public ContentWrapper(Content content/*, ContentPlusModules[] links*/) {
		super();
		this.content = content;
		// this.links = links;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}
/*
	public ContentPlusModules[] getLinks() {
		return links;
	}

	public void setLinks(ContentPlusModules[] links) {
		this.links = links;
	}
*/
	/*
	@Override
	public String toString() {
		return "ContentWrapper [content=" + content + ", links=" + Arrays.toString(links) + "]";
	}
	*/

	@Override
	public String toString() {
		return "ContentWrapper [content=" + content + "]";
	}
	
}
