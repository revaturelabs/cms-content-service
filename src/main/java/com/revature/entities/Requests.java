package com.revature.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
public class Requests {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "r_id")
	private int id;

	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String format;

	@Column(nullable=false)
	private String description;

	@Column(nullable=true)
	private String url;
	
	/**
	 * The following fields, dateCreated and lastModified, were added in order to facilitate functionality for 
	 * displaying a graphical representation of content created over a period of time.
	 */
	
	@Column(name = "created", nullable=true)
	private Long dateCreated;
	
	@Column(name = "last_modified", nullable=true)
	private Long lastModified;
	
	@OneToMany(mappedBy ="requestId", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ReqLink> reqLinks;
	
	public Requests() {
		super();
	}

	public Requests(int id, String title, String format, String description, String url, Set<ReqLink> reqLinks, Long dateCreated, Long lastModified) {
		super();
		this.id = id;
		this.title = title;
		this.format = format;
		this.description = description;
		this.url = url;
		this.reqLinks = reqLinks;
		this.lastModified = lastModified;
		this.dateCreated = dateCreated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<ReqLink> getReqLinks() {
		return reqLinks;
	}

	public void setReqLinks(Set<ReqLink> reqLinks) {
		this.reqLinks = reqLinks;
	}
	

	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", title=" + title + ", format=" + format + ", description=" + description
				+ ", url=" + url + ", dateCreated=" + dateCreated + ", lastModified=" + lastModified + ", reqLinks="
				+ reqLinks + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastModified == null) ? 0 : lastModified.hashCode());
		result = prime * result + ((reqLinks == null) ? 0 : reqLinks.hashCode());
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
		Requests other = (Requests) obj;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
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
		if (lastModified == null) {
			if (other.lastModified != null)
				return false;
		} else if (!lastModified.equals(other.lastModified))
			return false;
		if (reqLinks == null) {
			if (other.reqLinks != null)
				return false;
		} else if (!reqLinks.equals(other.reqLinks))
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
