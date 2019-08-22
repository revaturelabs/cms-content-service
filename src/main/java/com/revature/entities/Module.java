package com.revature.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
public class Module {
	@Id
	@GeneratedValue
	@Column(name = "m_id")
	private int id;

	private String subject;

	private long created;
	@OneToMany(mappedBy = "moduleId", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Link> links;

	public Module() {
		super();
	}

	public Module(int id, String subject, long created, Set<Link> links) {
		super();
		this.id = id;
		this.subject = subject;
		this.created = created;
		this.links = links;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public Set<Link> getLinks() {
		return links;
	}

	public void setLinks(Set<Link> links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", subject=" + subject + ", created=" + created + ", links=" + links + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (created ^ (created >>> 32));
		result = prime * result + ((links == null) ? 0 : links.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Module))
			return false;
		Module other = (Module) obj;
		if (created != other.created)
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
