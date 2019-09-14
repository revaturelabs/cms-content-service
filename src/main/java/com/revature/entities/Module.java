package com.revature.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
public class Module {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "m_id", updatable = false, nullable = false)
	private Integer id;

	private String subject;

	private long created;
	
	// @OneToMany(mappedBy = "moduleId", cascade = CascadeType.ALL, orphanRemoval = true)
	// private Set<ContentPlusModules> links;

	
	//ToDo: update this to 'private Module parentModule;' which will contain the Module
		//object of this Module Object's parent. 
		//We want to restrict Module Objects to only one parent
	//All parents of the module.
	// what they had - H
	// @ElementCollection
	// @CollectionTable(name="joins",joinColumns=@JoinColumn(name="fk_m_child"))
	// @Column(name="fk_m_parent")
	//private Set<Integer> parentModules;
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="joins",
			joinColumns=@JoinColumn(name="fk_m_child"),
			inverseJoinColumns=@JoinColumn(name="fk_m_parent"))
	private Module parentModule;
	
	//ToDo: update this to 'private Set<Module> childrenModules;' which will
		//contain all of the children modules of this module
//	//All children of the module.
//	@ElementCollection
//	@CollectionTable(name="joins",joinColumns=@JoinColumn(name="fk_m_parent"))
//	@Column(name="fk_m_child")
//	private Set<Integer> childrenModules;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="joins",
			joinColumns=@JoinColumn(name="fk_m_parent"),
			inverseJoinColumns=@JoinColumn(name="fk_m_child"))
	private Set<Module> childModules;

	public Module() {
		super();
	}

	public Module(int id, String subject, long created, Module parentModule, Set<Module> childModules) {
		super();
		this.id = id;
		this.subject = subject;
		this.created = created;
		this.parentModule = parentModule;
		this.childModules = childModules;
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

	public Module getParentModule() {
		return parentModule;
	}

	public void setParentModule(Module parentModule) {
		this.parentModule = parentModule;
	}

	public Set<Module> getChildModules() {
		return childModules;
	}

	public void setChildModules(Set<Module> childModules) {
		this.childModules = childModules;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((childModules == null) ? 0 : childModules.hashCode());
		result = prime * result + (int) (created ^ (created >>> 32));
		result = prime * result + id;
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
		if (childModules == null) {
			if (other.childModules != null)
				return false;
		} else if (!childModules.equals(other.childModules))
			return false;
		if (created != other.created)
			return false;
		if (id != other.id)
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

	@Override
	public String toString() {
		return "Module [id=" + id + ", subject=" + subject + ", created=" + created + ", parentModule=" + parentModule
				+ ", childModules=" + childModules + "]";
	}

	
}
