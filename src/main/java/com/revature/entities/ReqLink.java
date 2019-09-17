package com.revature.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "req_link")
public class ReqLink {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "z_id")
	private int id;

	@Column(name = "fk_r")
	private int requestId;

	@Column(name = "fk_rm")
	private int reqModuleId;
	
	//unimplemented
	private String affiliation;

	public ReqLink() {
		super();
	}

	public ReqLink(int id, int requestId, int reqModuleId) {
		super();
		this.id = id;
		this.requestId = requestId;
		this.reqModuleId = reqModuleId;
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
		return "ReqLink [id=" + id + ", requestId=" + requestId + ", reqModuleId=" + reqModuleId + ", affiliation="
				+ affiliation + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((affiliation == null) ? 0 : affiliation.hashCode());
		result = prime * result + id;
		result = prime * result + reqModuleId;
		result = prime * result + requestId;
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
		ReqLink other = (ReqLink) obj;
		if (affiliation == null) {
			if (other.affiliation != null)
				return false;
		} else if (!affiliation.equals(other.affiliation))
			return false;
		if (id != other.id)
			return false;
		if (reqModuleId != other.reqModuleId)
			return false;
		if (requestId != other.requestId)
			return false;
		return true;
	}

}
