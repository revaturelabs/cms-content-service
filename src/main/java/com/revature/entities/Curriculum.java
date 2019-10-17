package com.revature.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Curriculum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "curr_id", updatable = false, nullable = false)
	private int id;
	
	@Column(name = "name", updatable = true, nullable = false)
	private String name;
	
	@Transient
	private Set<CurriculumModule> curriculumModules;
	public Curriculum() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Curriculum(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CurriculumModule> getCurriculumModules() {
		return curriculumModules;
	}

	public void setCurriculumModules(Set<CurriculumModule> curriculumModules) {
		this.curriculumModules = curriculumModules;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Curriculum other = (Curriculum) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Curriculum [id=" + id + ", name=" + name + "]";
	}

	
	
	
}
