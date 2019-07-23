package com.revature.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;


/**
 * 
 * Module - a persisted data model to represent a record in the Module table. 
 * A Module is synonymous with tags associated with some Content, essentially subjects that some Content could fall under.
 * Example modules: Java, SQL, JavaScript
 *
 */
@Entity
public class Module {
	
	/**
	 * m_id is the primary key column, a unique identifier for a Module record
	 */
	@Id
	@GeneratedValue
	@Column(name = "m_id")
	private int id;

	/**
	 * subject is the tag category that some Content would fall under.
	 */
	private String subject;

	/**
	 * created is a UNIX time stamp which represents the date when a Module was added to the data base
	 */
	private long created;
	
	/**
	 * links is a foreign key to the Links table which is an intermediary table representing the ManyToMany
	 * relationship between Module(s) and Content(s)
	 */
	@OneToMany(mappedBy = "moduleId", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Link> links;

	/**
	 * Default no argument constructor
	 */
	public Module() {
		super();
	}

	/**
	 * Auto-generated constructor using all the fields
	 * @param id
	 * @param subject
	 * @param created
	 * @param links
	 */
	public Module(int id, String subject, int created, Set<Link> links) {
		super();
		this.id = id;
		this.subject = subject;
		this.created = System.currentTimeMillis();
		this.links = links;
	}

	/**
	 * 
	 * @return the unique identifier associated with the Module
	 */
	public int getId() {
		return id;
	}

	/**
	 * Simple setter to manually set an id.
	 * @param id, the unique identifier associated with the Module
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return the category associated with the Module
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Simple setter to manually set the subject
	 * @param subject, the category some Content might be associated with
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 
	 * @return the UNIX time stamp in milliseconds representing the date a Module was created.
	 */
	public long getCreated() {
		return created;
	}

	/**
	 * Simple setter to manually set a creation date.
	 * @param created, the UNIX time stamp in milliseconds representing the date a Module was created.
	 */
	public void setCreated(long created) {
		this.created = created;
	}

	/**
	 * 
	 * @return a set of Link objects that is associated with the Module
	 */
	public Set<Link> getLinks() {
		return links;
	}

	/**
	 * Simple setter to manually set a set of links to the piece of Content.
	 * @param links, a set of Link objects associated with the Module
	 */
	public void setLinks(Set<Link> links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", subject=" + subject + ", created=" + created + ", links=" + links + "]";
	}

	// generated hashCode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (created ^ (created >>> 32));
		result = prime * result + id;
		result = prime * result + ((links == null) ? 0 : links.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		Module other = (Module) obj;
		if (created != other.created)
			return false;
		if (id != other.id)
			return false;
		if (links == null) {
			if (other.links != null)
				return false;
		} else if (!links.equals(other.links))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}
	
	
}
