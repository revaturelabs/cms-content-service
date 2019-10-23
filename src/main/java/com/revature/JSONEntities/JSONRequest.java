package com.revature.JSONEntities;

import java.util.Set;

import com.revature.entities.Content;
import com.revature.entities.ReqLink;

public class JSONRequest {

	private int id;

	private String title;

	private String format;

	private String description;

	private Content content;

	private Long dateCreated;

	private Long lastModified;

	private Set<ReqLink> reqLinks;

	public JSONRequest() {
		super();
	}

	public JSONRequest(int id, String title, String format, String description, Content content, Long dateCreated,
			Long lastModified, Set<ReqLink> reqLinks) {
		super();
		this.id = id;
		this.title = title;
		this.format = format;
		this.description = description;
		this.content = content;
		this.dateCreated = dateCreated;
		this.lastModified = lastModified;
		this.reqLinks = reqLinks;
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

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getLastModified() {
		return lastModified;
	}

	public void setLastModified(Long lastModified) {
		this.lastModified = lastModified;
	}

	public Set<ReqLink> getReqLinks() {
		return reqLinks;
	}

	public void setReqLinks(Set<ReqLink> reqLinks) {
		this.reqLinks = reqLinks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastModified == null) ? 0 : lastModified.hashCode());
		result = prime * result + ((reqLinks == null) ? 0 : reqLinks.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		JSONRequest other = (JSONRequest) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
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
		return true;
	}

	@Override
	public String toString() {
		return "JSONRequest [id=" + id + ", title=" + title + ", format=" + format + ", description=" + description
				+ ", content=" + content + ", dateCreated=" + dateCreated + ", lastModified=" + lastModified
				+ ", reqLinks=" + reqLinks + "]";
	}

}
