package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "link")
public class Link {
	@Id
	@GeneratedValue
	@Column(name = "cm_id")
	private int id;

	@Column(name = "fk_c")
	private int contentId;

	@Column(name = "fk_m")
	private int moduleId;

	private String affiliation;

	public Link() {
		super();
	}

	public Link(int id, int contentId, int moduleId, String affiliation) {
		super();
		this.id = id;
		this.contentId = contentId;
		this.moduleId = moduleId;
		this.affiliation = affiliation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", contentId=" + contentId + ", moduleId=" + moduleId + ", affiliation=" + affiliation
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((affiliation == null) ? 0 : affiliation.hashCode());
		result = prime * result + contentId;
		result = prime * result + moduleId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Link))
			return false;
		Link other = (Link) obj;
		if (affiliation == null) {
			if (other.affiliation != null)
				return false;
		} else if (!affiliation.equals(other.affiliation))
			return false;
		if (contentId != other.contentId)
			return false;
		if (moduleId != other.moduleId)
			return false;
		return true;
	}

}
