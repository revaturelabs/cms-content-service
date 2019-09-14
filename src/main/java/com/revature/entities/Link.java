package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "link")
public class Link {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cm_id")
	private int id;

	@Column(name = "fk_c")
	private int contentId;

	@Column(name = "fk_m")
	private int moduleId;

	public Link() {
		super();
	}

	public Link(int id, int contentId, int moduleId) {
		super();
		this.id = id;
		this.contentId = contentId;
		this.moduleId = moduleId;
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

	@Override
	public String toString() {
		return "Link [id=" + id + ", contentId=" + contentId + ", moduleId=" + moduleId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (contentId != other.contentId)
			return false;
		if (id != other.id)
			return false;
		if (moduleId != other.moduleId)
			return false;
		return true;
	}

}
