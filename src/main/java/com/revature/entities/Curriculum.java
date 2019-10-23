package com.revature.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
/**
 * 
 * @author Java Batch 1908
 * 
 *         Define Curriculum model 
 *
 */
public class Curriculum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "curriculum_id", updatable = false, nullable = false)
	private int id;

	@Column(name = "name", updatable = true, nullable = false)
	private String name;

	@Transient
	private Set<CurriculumModule> currModules;

	/**
	 * Default Constructor
	 */
	public Curriculum() {
		super();
	}

	public Curriculum(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * Getter for Curriculum Id
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter for Curriculum Id
	 * @param id
	 */

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * getter for Curriculum name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for Curriculum name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for curriculum modules
	 * @return Set<CurriculumModule> - set of CurriculumModule objects 
	 */
	public Set<CurriculumModule> getCurrModules() {
		return this.currModules;
	}
	
	/**
	 * Setter for CurriculumModule
	 * @param currModules - Set of CurriculumModule objects to set
	 */

	public void setCurrModules(Set<CurriculumModule> currModules) {
		this.currModules = currModules;
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
