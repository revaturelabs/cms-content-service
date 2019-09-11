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
public class ReqModule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rm_id")
	private int id;

	private String subject;

	private long created;
	@OneToMany(mappedBy = "reqModuleId", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Zelda> zeldas;

	//All parents of the module.
	@ElementCollection
	@CollectionTable(name="joins",joinColumns=@JoinColumn(name="fk_rm_child"))
	@Column(name="fk_rm_parent")
	private Set<Integer> parentModules;

	//All children of the module.
	@ElementCollection
	@CollectionTable(name="joins",joinColumns=@JoinColumn(name="fk_rm_parent"))
	@Column(name="fk_rm_child")
	private Set<Integer> childrenModules;

	public ReqModule() {
		super();
  }
  
	public ReqModule(int id, String subject, long created, Set<Zelda> zeldas) {
		super();
		this.id = id;
		this.subject = subject;
		this.created = created;
		this.zeldas = zeldas;
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

	public Set<Zelda> getLinks() {
		return zeldas;
	}

	public void setLinks(Set<Zelda> zeldas) {
		this.zeldas = zeldas;
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

	public ReqModule(int id, String subject, long created, Set<Zelda> zeldas, Set<Integer> parentModules,
			Set<Integer> childrenModules) {
		super();
		this.id = id;
		this.subject = subject;
		this.created = created;
		this.zeldas = zeldas;
		this.parentModules = parentModules;
		this.childrenModules = childrenModules;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((childrenModules == null) ? 0 : childrenModules.hashCode());
		result = prime * result + (int) (created ^ (created >>> 32));
		result = prime * result + ((zeldas == null) ? 0 : zeldas.hashCode());
		result = prime * result + ((parentModules == null) ? 0 : parentModules.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ReqModule))
			return false;
		ReqModule other = (ReqModule) obj;
		if (childrenModules == null) {
			if (other.childrenModules != null)
				return false;
		} else if (!childrenModules.equals(other.childrenModules))
			return false;
		if (created != other.created)
			return false;
		if (zeldas == null) {
			if (other.zeldas != null)
				return false;
		} else if (!zeldas.equals(other.zeldas))
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
