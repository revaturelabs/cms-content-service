package com.revature.util;

import java.util.Arrays;

import com.revature.entities.Content;
import com.revature.entities.ContentModule;

public class ContentCreationHelper {

	private Content contentToAdd;
	private ContentModule[] contentModulesToAdd;
	
	public ContentCreationHelper() {
		super();
	}

	public ContentCreationHelper(Content contentToAdd, ContentModule[] contentModulesToAdd) {
		super();
		this.contentToAdd = contentToAdd;
		this.contentModulesToAdd = contentModulesToAdd;
	}

	public Content getContentToAdd() {
		return contentToAdd;
	}

	public void setContentToAdd(Content contentToAdd) {
		this.contentToAdd = contentToAdd;
	}

	public ContentModule[] getContentModulesToAdd() {
		return contentModulesToAdd;
	}

	public void setContentModulesToAdd(ContentModule[] contentModulesToAdd) {
		this.contentModulesToAdd = contentModulesToAdd;
	}

	@Override
	public String toString() {
		return "ContentCreationHelper [contentToAdd=" + contentToAdd + ", contentModulesToAdd="
				+ Arrays.toString(contentModulesToAdd) + "]";
	}
}
