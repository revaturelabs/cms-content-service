package com.revature.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
public class Module {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "m_id")
	private int id;

	private String subject;

	private long created;

	@OneToMany(mappedBy = "moduleId", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Link> links;

	//All parents of the module.
	@ManyToMany(mappedBy = "mChild", cascade = CascadeType.ALL)
	private Set<ModuleHierarchy> parentModules;

	//All children of the module.
	@ManyToMany(mappedBy = "mParent", cascade = CascadeType.ALL)
	private Set<ModuleHierarchy> childrenModules;

	public Module() {
		super();
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

	public Set<ModuleHierarchy> getParentModules() {
		return parentModules;
	}

	public void setParentModules(Set<ModuleHierarchy> parentModules) {
		this.parentModules = parentModules;
	}

	public Set<ModuleHierarchy> getChildrenModules() {
		return childrenModules;
	}

	public void setChildrenModules(Set<ModuleHierarchy> childrenModules) {
		this.childrenModules = childrenModules;
	}

	public Module(int id, String subject, long created, Set<Link> links, Set<ModuleHierarchy> parentModules,
			Set<ModuleHierarchy> childrenModules) {
		this.id = id;
		this.subject = subject;
		this.created = created;
		this.links = links;
		this.parentModules = parentModules;
		this.childrenModules = childrenModules;
	}

	@Override
	public String toString() {
		return "Module [childrenModules=" + childrenModules + ", created=" + created + ", id=" + id + ", links=" + links
				+ ", parentModules=" + parentModules + ", subject=" + subject + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((childrenModules == null) ? 0 : childrenModules.hashCode());
		result = prime * result + (int) (created ^ (created >>> 32));
		result = prime * result + id;
		result = prime * result + ((links == null) ? 0 : links.hashCode());
		result = prime * result + ((parentModules == null) ? 0 : parentModules.hashCode());
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
		if (childrenModules == null) {
			if (other.childrenModules != null)
				return false;
		} else if (!childrenModules.equals(other.childrenModules))
			return false;
		if (created != other.created)
			return false;
		if (id != other.id)
			return false;
		if (links == null) {
			if (other.links != null)
				return false;
		} else if (!links.equals(other.links))
			return false;
		if (parentModules == null) {
			if (other.parentModules != null)
				return false;
		} else if (!parentModules.equals(other.parentModules))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

}
