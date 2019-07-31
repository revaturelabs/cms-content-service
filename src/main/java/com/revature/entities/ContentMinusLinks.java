package com.revature.entities;

public interface ContentMinusLinks {

	/**
	 * The ContentMinusLinks interface is used to represent the Content entity without the Links relationship to its Modules.
	 * This improves performance when actively grabbing Content by not requiring an implicit DB call to the links table to populate the original Content entity's set of Links.
	 * When used in a JPA repository method call this interface populates the fields it shares with the Content entity.
	 */
	
	public int getId();
	public String getTitle();
	public String getFormat();
	public String getDescription();
	public String getUrl();
	public long getDateCreated();
	public long getLastModified();
}
