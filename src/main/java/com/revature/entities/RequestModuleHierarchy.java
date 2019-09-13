package com.revature.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ties")
public class RequestModuleHierarchy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "t_id")
	private int id;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "fk_rm_parent")
	private ReqModule rmParent;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "fk_rm_child")
	private ReqModule rmChild;


	public RequestModuleHierarchy() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ReqModule getRmParent() {
		return rmParent;
	}

	public void setRmParent(ReqModule rmParent) {
		this.rmParent = rmParent;
	}

	public ReqModule getRmChild() {
		return rmChild;
	}

	public void setRmChild(ReqModule rmChild) {
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
		result = prime * result + ((rmChild == null) ? 0 : rmChild.hashCode());
		result = prime * result + ((rmParent == null) ? 0 : rmParent.hashCode());
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
		if (rmChild == null) {
			if (other.rmChild != null)
				return false;
		} else if (!rmChild.equals(other.rmChild))
			return false;
		if (rmParent == null) {
			if (other.rmParent != null)
				return false;
		} else if (!rmParent.equals(other.rmParent))
			return false;
		return true;
	}

	public RequestModuleHierarchy(ReqModule rmParent, ReqModule rmChild) {
		this.rmParent = rmParent;
		this.rmChild = rmChild;
	}
}

