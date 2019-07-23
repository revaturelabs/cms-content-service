package com.revature.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;


/**
 * Content - a persisted data model to represent a record in the Content table.
 * 
 */
@Entity
public class Content {
	
	/**
	 * id is the primary key column, a unique identifier for a Content record.
	 */
	@Id
	@GeneratedValue
	@Column(name = "c_id")
	private int id;

	/**
	 * title is the title of a piece of Content.
	 */
	private String title;

	/**
	 * format represents the kind of Content it is. Example formats: Code, Document, Powerpoint.
	 */
	private String format;

	/**
	 * description is a short summary of the Content for easy identification.
	 */
	private String description;

	/**
	 * url is the url to the specified Content to access it.
	 */
	private String url;
	
	/**
	 * The following fields, dateCreated and lastModified, were added in order to facilitate functionality for 
	 * displaying a graphical representation of content created over a period of time.
	 */
	
	/**
	 * dateCreated is a UNIX time stamp, the representation of time since epoch in miliseconds. dateCreated 
	 * represents the date that the piece of Content was added to the data base
	 */
	@Column(name = "created")
	private long dateCreated;
	
	/**
	 * lastModified is also a UNIX time stamp which represents the date that the piece of Content was 
	 * last modified from the data base.
	 */
	@Column(name = "last_modified")
	private long lastModified;
	
	/**
	 * links is a foreign key to the Links table which is an intermediary table representing the ManyToMany
	 * relationship between Content(s) and Module(s).
	 */
	@OneToMany(mappedBy ="contentId", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Link> links;
	
	/**
	 * Default no argument constructor.
	 */
	public Content() {
		super();
	}

	/**
	 * Auto-generated constructor from all the fields.
	 * @param id
	 * @param title
	 * @param format
	 * @param description
	 * @param url
	 * @param links
	 * @param dateCreated
	 * @param lastModified
	 */
	public Content(int id, String title, String format, String description, String url, Set<Link> links, long dateCreated, long lastModified) {
		super();
		this.id = id;
		this.title = title;
		this.format = format;
		this.description = description;
		this.url = url;
		this.links = links;
		this.lastModified = lastModified;
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the id that uniquely identifies the Content record
	 */
	public int getId() {
		return id;
	}

	/**
	 * Simple setter to manually set an id.
	 * @param id, the id that uniquely identifies the Content record
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title of the Content record
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Simple setter to manually set a title.
	 * @param title, the title of the piece of Content record
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the format that the Content record is in
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Simple setter to manually set a format.
	 * @param format, the format the Content record is in.
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the description that summarizes the Content.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Simple setter to manually set a description.
	 * @param description, 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the url to access the Content.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Simple setter to manually set a url.
	 * @param url, the url to access the Content.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return a set of Link objects that is associated with the Content
	 */
	public Set<Link> getLinks() {
		return links;
	}

	/**
	 * Simple setter to manually set a set of links to the piece of Content.
	 * @param links, a set of Link objects associated with the Content
	 */
	public void setLinks(Set<Link> links) {
		this.links = links;
	}
	
	/**
	 * @return a UNIX time stamp represented in milliseconds representing when the record was created.
	 */
	public long getDateCreated() {
		return dateCreated;
	}

	/**
	 * Simple setter to manually set a creation date.
	 * @param dateCreated, a UNIX time stamp in milliseconds representing when the record was created.
	 */
	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * 
	 * @return a UNIX time stamp in milliseconds representing when the record was last updated.
	 */
	public long getLastModified() {
		return lastModified;
	}

	/**
	 * Simple setter to manually set a last updated date.
	 * @param lastModified, a UNIX time stamp in milliseconds representing when the record was last updated.
	 */
	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", title=" + title + ", format=" + format + ", description=" + description
				+ ", url=" + url + ", dateCreated=" + dateCreated + ", lastModified=" + lastModified + ", links="
				+ links + "]";
	}

	// generated hashCode and equals since they were not pre-existing
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (dateCreated ^ (dateCreated >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + id;
		result = prime * result + (int) (lastModified ^ (lastModified >>> 32));
		result = prime * result + ((links == null) ? 0 : links.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Content other = (Content) obj;
		if (dateCreated != other.dateCreated)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (id != other.id)
			return false;
		if (lastModified != other.lastModified)
			return false;
		if (links == null) {
			if (other.links != null)
				return false;
		} else if (!links.equals(other.links))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	

	
	
}
