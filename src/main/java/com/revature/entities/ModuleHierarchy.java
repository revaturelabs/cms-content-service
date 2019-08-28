package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "joins")
public class ModuleHierarchy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "j_id")
	private int id;

	@Column(name = "fk_m_parent")
	private int mParent;

	@Column(name = "fk_m_child")
	private int mChild;


	public ModuleHierarchy() {
		super();
	}

	public ModuleHierarchy(int id, int mParent, int mChild) {
		super();
		this.id = id;
		this.mParent = mParent;
		this.mChild = mChild;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getmParent() {
		return mParent;
	}

	public void setmParent(int mParent) {
		this.mParent = mParent;
	}

	public int getmChild() {
		return mChild;
	}

	public void setmChild(int mChild) {
		this.mChild = mChild;
	}

	@Override
	public String toString() {
		return "ModuleHierarchy [id=" + id + ", mChild=" + mChild + ", mParent=" + mParent + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + mChild;
		result = prime * result + mParent;
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
		ModuleHierarchy other = (ModuleHierarchy) obj;
		if (id != other.id)
			return false;
		if (mChild != other.mChild)
			return false;
		if (mParent != other.mParent)
			return false;
		return true;
	}



}
