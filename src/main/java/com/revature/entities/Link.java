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
}
