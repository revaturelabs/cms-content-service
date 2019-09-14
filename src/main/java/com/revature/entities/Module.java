package com.revature.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

@Entity
public class Module {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "m_id")
	private int id;

	private String subject;

	private long created;
	@OneToMany(mappedBy = "moduleId", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ContentPlusModules> links;

	
	//ToDo: update this to 'private Module parentModule;' which will contain the Module
		//object of this Module Object's parent. 
		//We want to restrict Module Objects to only one parent
	//All parents of the module.
	@ElementCollection
	@CollectionTable(name="joins",joinColumns=@JoinColumn(name="fk_m_child"))
	@Column(name="fk_m_parent")
	//private Set<Integer> parentModules;
	private Module parentModule;
	
	//ToDo: update this to 'private Set<Module> childrenModules;' which will
		//contain all of the children modules of this module
	//All children of the module.
	@ElementCollection
	@CollectionTable(name="joins",joinColumns=@JoinColumn(name="fk_m_parent"))
	@Column(name="fk_m_child")
	private Set<Integer> childrenModules;

	public Module() {
		super();
  }
  
	public Module(int id, String subject, long created, Set<ContentPlusModules> links) {
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

	public Set<ContentPlusModules> getLinks() {
		return links;
	}

	public void setLinks(Set<ContentPlusModules> links) {
		this.links = links;
	}

	public Module getParentModule() {
		return parentModule;
	}

	public void setParentModule(Module parentModule) {
		this.parentModule = parentModule;
	}

	public Set<Integer> getChildrenModules() {
		return childrenModules;
	}

	public void setChildrenModules(Set<Integer> childrenModules) {
		this.childrenModules = childrenModules;
	}

	public Module(int id, String subject, long created, Set<ContentPlusModules> links, 
			/* Set<Integer> parentModules, */
			Module parentModule,
			Set<Integer> childrenModules) {
		super();
		this.id = id;
		this.subject = subject;
		this.created = created;
		this.links = links;
		this.parentModule = parentModule;
		this.childrenModules = childrenModules;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((childrenModules == null) ? 0 : childrenModules.hashCode());
		result = prime * result + (int) (created ^ (created >>> 32));
		result = prime * result + id;
		result = prime * result + ((links == null) ? 0 : links.hashCode());
		result = prime * result + ((parentModule == null) ? 0 : parentModule.hashCode());
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
		if (parentModule == null) {
			if (other.parentModule != null)
				return false;
		} else if (!parentModule.equals(other.parentModule))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

}
