package com.revature.util;

import java.util.Arrays;

import com.revature.entities.Content;
import com.revature.entities.Link;

public class ContentWrapper {

	private Content content;
	private Link[] links;
	
	public ContentWrapper() {
		super();
	}

	public ContentWrapper(Content content, Link[] links) {
		super();
		this.content = content;
		this.links = links;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Link[] getLinks() {
		return links;
	}

	public void setLinks(Link[] links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "ContentWrapper [content=" + content + ", links=" + Arrays.toString(links) + "]";
	}
}
