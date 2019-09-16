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

//	//parent of the module.
//	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
//	@JoinTable(name="joins",
//		joinColumns=@JoinColumn(name="fk_m_child"),
//		inverseJoinColumns=@JoinColumn(name="fk_m_parent"))
//	private Module parentModule;

	// All children of the module.
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "joins", 
		joinColumns = @JoinColumn(name = "fk_m_parent"), 
		inverseJoinColumns = @JoinColumn(name = "fk_m_child"))
	private Set<Module> childModules;

	// All content associated to the module
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "link", joinColumns = @JoinColumn(name = "fk_m"), inverseJoinColumns = @JoinColumn(name = "fk_c"))
	private Set<Content> content;

//	//All requests associated to the module
//	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
//	@JoinTable(name="reqLink", 
//		joinColumns=@JoinColumn(name="fk_m"),
//		inverseJoinColumns=@JoinColumn(name="fk_c"))
//	private Set<Request> requests;

	public Module() {
		super();
	}

	public Module(Integer id, String subject, long created, Set<Module> childModules, Set<Content> content) {
		super();
		this.id = id;
		this.subject = subject;
		this.created = created;
		this.childModules = childModules;
		this.content = content;
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

	public Set<Module> getChildModules() {
		return childModules;
	}

	public void setChildModules(Set<Module> childModules) {
		this.childModules = childModules;
	}

	public Set<Content> getContent() {
		return content;
	}

	public void setContent(Set<Content> content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((childModules == null) ? 0 : childModules.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + (int) (created ^ (created >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (created != other.created)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return "Module [id=" + id + ", subject=" + subject + ", created=" + created + ", childModules=" + childModules
				+ ", content=" + content + "]";
	}

}
