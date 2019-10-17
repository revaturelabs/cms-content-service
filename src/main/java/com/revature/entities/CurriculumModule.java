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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="curriculum_module")
public class CurriculumModule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "curriculum_module_id")
	private int id;
	
	@Column(name="curriculum_id")
	private int curriculum;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="module_id")
	private Module module;
	
	@Column(name = "priority")
	private int priority;
	
	public CurriculumModule() {
		super();
	}
	
	public CurriculumModule(int curriculum, Module module, int priority) {
		super();
		this.curriculum = curriculum;
		this.module = module;
		this.priority = priority;
	}

	public CurriculumModule(int id, int curriculum, Module module, int priority) {
		super();
		this.id = id;
		this.curriculum = curriculum;
		this.module = module;
		this.priority = priority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(int curriculum) {
		this.curriculum = curriculum;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
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
		result = prime * result + curriculum;
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
		CurriculumModule other = (CurriculumModule) obj;
		if (curriculum != other.curriculum)
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
		return "CurrModule [id=" + id + ", curriculum=" + curriculum + ", module=" + module + ", priority=" + priority
				+ "]";
	}

	
	
}
