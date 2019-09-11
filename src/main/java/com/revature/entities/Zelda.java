package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zelda")
public class Zelda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "z_id")
	private int id;

	@Column(name = "fk_r")
	private int requestId;

	@Column(name = "fk_rm")
	private int reqModuleId;

	private String affiliation;

	public Zelda() {
		super();
	}

	public Zelda(int id, int requestId, int reqModuleId, String affiliation) {
		super();
		this.id = id;
		this.requestId = requestId;
		this.reqModuleId = reqModuleId;
		this.affiliation = affiliation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getReqModuleId() {
		return reqModuleId;
	}

	public void setReqModuleId(int reqModuleId) {
		this.reqModuleId = reqModuleId;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	@Override
	public String toString() {
		return "Zelda [id=" + id + ", requestId=" + requestId + ", reqModuleId=" + reqModuleId + ", affiliation=" + affiliation
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((affiliation == null) ? 0 : affiliation.hashCode());
		result = prime * result + requestId;
		result = prime * result + reqModuleId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Zelda))
			return false;
		Zelda other = (Zelda) obj;
		if (affiliation == null) {
			if (other.affiliation != null)
				return false;
		} else if (!affiliation.equals(other.affiliation))
			return false;
		if (requestId != other.requestId)
			return false;
		if (reqModuleId != other.reqModuleId)
			return false;
		return true;
	}

}
