package com.revature.entities;

public interface RequestsMinusReqLinks {

	
	public int getId();
	public String getTitle();
	public String getFormat();
	public String getDescription();
	public String getUrl();
	public long getDateCreated();
	public long getLastModified();
	default String tooString() {
		return "RequestsMinusReqLinks: id=" + getId() + ", Title=" + getTitle() + ", format=" + getFormat();
	}
}
