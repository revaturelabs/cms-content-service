package com.revature.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Column
	private String subject;

	@Column
	private long created;
	
	@JsonIgnore
	//the set of link objects associated with the module
	@OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Link> links;
	
	@JsonIgnore
	//the set of link objects associated with the module
	@OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ReqLink> reqLinks;

	//parents of the module. A requirement of CMS force is that modules can have many parents
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "joins", 
		joinColumns = @JoinColumn(name = "fk_m_child"), 
		inverseJoinColumns = @JoinColumn(name = "fk_m_parent"))
	private Set<Module> parents;
	
	//children of the module.
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "joins", 
		joinColumns = @JoinColumn(name = "fk_m_parent"), 
		inverseJoinColumns = @JoinColumn(name = "fk_m_child"))
	private Set<Module> children;

	public Module() {
		super();
	}

	public Module(Integer id, String subject, long created, Set<Link> links, Set<ReqLink> reqLinks, Set<Module> parents,
			Set<Module> children) {
		super();
		this.id = id;
		this.subject = subject;
		this.created = created;
		this.links = links;
		this.reqLinks = reqLinks;
		this.parents = parents;
		this.children = children;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Set<ReqLink> getReqLinks() {
		return reqLinks;
	}

	public void setReqLinks(Set<ReqLink> reqLinks) {
		this.reqLinks = reqLinks;
	}

	public Set<Module> getParents() {
		return parents;
	}

	public void setParents(Set<Module> parents) {
		this.parents = parents;
	}

	public Set<Module> getChildren() {
		return children;
	}

	public void setChildren(Set<Module> children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (created ^ (created >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((parents == null) ? 0 : parents.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parents == null) {
			if (other.parents != null)
				return false;
		} else if (!parents.equals(other.parents))
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
		return "Module [id=" + id + ", subject=" + subject + ", created=" + created + ", parents=" + parents + "]";
	}
	
}
