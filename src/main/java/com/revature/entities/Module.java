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
	private Set<Link> links;

	//All parents of the module.
	@ElementCollection
	@CollectionTable(name="joins",joinColumns=@JoinColumn(name="fk_m_child"))
	@Column(name="fk_m_parent")
	private Set<Integer> parentModules;

	//All children of the module.
	@ElementCollection
	@CollectionTable(name="joins",joinColumns=@JoinColumn(name="fk_m_parent"))
	@Column(name="fk_m_child")
	private Set<Integer> childrenModules;

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
	
	public Set<Integer> getParentModules() {
		return parentModules;
	}

	public void setParentModules(Set<Integer> parentModules) {
		this.parentModules = parentModules;
	}

	public Set<Integer> getChildrenModules() {
		return childrenModules;
	}

	public void setChildrenModules(Set<Integer> childrenModules) {
		this.childrenModules = childrenModules;
	}

	public Module(int id, String subject, long created, Set<Link> links, Set<Integer> parentModules,
			Set<Integer> childrenModules) {
		super();
		this.id = id;
		this.subject = subject;
		this.created = created;
		this.links = links;
		this.parentModules = parentModules;
		this.childrenModules = childrenModules;
	}



	

}
