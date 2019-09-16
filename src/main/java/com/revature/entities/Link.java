package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * DO NOT DELETE THIS FILE. According to the product owner.
 * 
 */

@Entity
@Table(name = "link")
public class Link {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cm_id")
	private int id;

	/*
	@Column(name = "fk_c")
	private int contentId;

	@Column(name = "fk_m")
	private int moduleId;
	 */
	
	@Column(name="fk_m")
	private Module module;
	
	// SUPER IMPORTANT, according to the owner.
	// ANNOTATION GOES HERE.
	private String affiliation;
	
	public Link() {
		super();
	}

	public Link(int id, Module module, String affiliation) {
		super();
		this.id = id;
		this.module = module;
		this.affiliation = affiliation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((affiliation == null) ? 0 : affiliation.hashCode());
		result = prime * result + id;
		result = prime * result + ((module == null) ? 0 : module.hashCode());
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
		if (id != other.id)
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", module=" + module + ", affiliation=" + affiliation + "]";
	}

	
}
