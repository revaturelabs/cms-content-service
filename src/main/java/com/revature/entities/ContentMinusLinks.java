package com.revature.entities;

public interface ContentMinusLinks {

	
	public int getId();
	public String getTitle();
	public String getFormat();
	public String getDescription();
	public String getUrl();
	public long getDateCreated();
	public long getLastModified();
	default String tooString() {
		return "ContentMinusLinks: id=" + getId() + ", Title=" + getTitle() + ", format=" + getFormat();
	}
}
