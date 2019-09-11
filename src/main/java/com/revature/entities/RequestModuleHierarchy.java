package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ties")
public class RequestModuleHierarchy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "t_id")
	private int id;

	@Column(name = "fk_rm_parent")
	private int rmParent;

	@Column(name = "fk_rm_child")
	private int rmChild;


	public RequestModuleHierarchy() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getmParent() {
		return rmParent;
	}

	public void setmParent(int rmParent) {
		this.rmParent = rmParent;
	}

	public int getmChild() {
		return rmChild;
	}

	public void setmChild(int rmChild) {
		this.rmChild = rmChild;
	}

	@Override
	public String toString() {
		return "RequestModuleHierarchy [id=" + id + ", rmChild=" + rmChild + ", rmParent=" + rmParent + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + rmChild;
		result = prime * result + rmParent;
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
		RequestModuleHierarchy other = (RequestModuleHierarchy) obj;
		if (id != other.id)
			return false;
		if (rmChild != other.rmChild)
			return false;
		if (rmParent != other.rmParent)
			return false;
		return true;
	}

	public RequestModuleHierarchy(int rmParent, int rmChild) {
		this.rmParent = rmParent;
		this.rmChild = rmChild;
	}
}

