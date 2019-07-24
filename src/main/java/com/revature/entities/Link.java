package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * Link - a persisted data model to represent a record in the Link table.
 * These Link records represent a ManyToMany relationship between some Content(s) and Module(s)
 */
@Entity
@Table(name = "link")
public class Link {
	
	/**
	 * cm_id is the unique identifier for a Link record
	 */
	@Id
	@GeneratedValue
	@Column(name = "cm_id")
	private int id;

	/**
	 * fk_c is a foreign key reference to a Content's unique id
	 */
	@Column(name = "fk_c")
	private int contentId;

	/**
	 * fk_m is a foreign key reference to a Module's unique id
	 */
	@Column(name = "fk_m")
	private int moduleId;

	/**
	 * affiliation is a string representation of the relationship between Content and Module
	 */
	private String affiliation;

	/**
	 * Default no argument constructor.
	 */
	public Link() {
		super();
	}

	/**
	 * Auto-generated constructor from all the fields.
	 * @param id
	 * @param contentId
	 * @param moduleId
	 * @param affiliation
	 */
	public Link(int id, int contentId, int moduleId, String affiliation) {
		super();
		this.id = id;
		this.contentId = contentId;
		this.moduleId = moduleId;
		this.affiliation = affiliation;
	}

	/**
	 * 
	 * @return the id that uniquely identifies the Link record
	 */
	public int getId() {
		return id;
	}

	/**
	 * Simple setter to manually set an id.
	 * @param id, the unique identifier for a Link record
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return the unique identifier for a Content record, a foreign key in the Link record
	 */
	public int getContentId() {
		return contentId;
	}

	/**
	 * Simple setter to manually set a content id, has to reference a Content record id
	 * @param contentId, the unique identifier for a Content record, a foreign key in the Link record
	 */
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	/**
	 * 
	 * @return the unique identifier for a Module record, a foreign key in the Linkk record
	 */
	public int getModuleId() {
		return moduleId;
	}

	/**
	 * Simple setter to manually set a module id, has to reference a Module record id
	 * @param moduleId, the unique identifier for a Module record, a foreign key in the Link record
	 */
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * 
	 * @return a string that represents the relationship between a Content and Module
	 */
	public String getAffiliation() {
		return affiliation;
	}

	/**
	 * Simple setter to manually set the affiliation string
	 * @param affiliation, a string that represents the relationship between a Content and Module
	 */
	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", contentId=" + contentId + ", moduleId=" + moduleId + ", affiliation=" + affiliation
				+ "]";
	}

	// generated hashCode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((affiliation == null) ? 0 : affiliation.hashCode());
		result = prime * result + contentId;
		result = prime * result + id;
		result = prime * result + moduleId;
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
		Link other = (Link) obj;
		if (affiliation == null) {
			if (other.affiliation != null)
				return false;
		} else if (!affiliation.equals(other.affiliation))
			return false;
		if (contentId != other.contentId)
			return false;
		if (id != other.id)
			return false;
		if (moduleId != other.moduleId)
			return false;
		return true;
	}
	
	
}
