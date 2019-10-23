package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/*
 * The point of the link entity is to give additional context
 * to the relationship between a content object and a module object.
 * This is specifically requested by the project owner and will be for 
 * a future feature that will be implemented by a future batch.
 */

@Entity
@Table(name = "link")
public class Link {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cm_id")
	private int id;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="fk_c")
	private Content content;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="fk_m")
	private Module module;
	
	//The affiliation property is for a feature that will be asked for 
		//by some future batch. For now, product owner has simply ask that we include it.
	@Column
	private String affiliation;
	
	@Column
	private int priority;

	public Link() {
		super();
	}


	public Link(int id, Content content, Module module, String affiliation, int priority) {
		super();
		this.id = id;
		this.content = content;
		this.module = module;
		this.affiliation = affiliation;
		this.priority = priority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
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

	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((affiliation == null) ? 0 : affiliation.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + id;
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + priority;
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
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (priority != other.priority)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", content=" + content + ", module=" + module + ", affiliation=" + affiliation
				+ ", priority=" + priority + "]";
	}
}
