package com.revature.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "req_link")
public class ReqLink {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "z_id")
	private int id;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "fk_r")
	private Requests requests;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "fk_rm")
	private Module reqModule;

	private String affiliation;

	public ReqLink() {
		super();
	}

	public ReqLink(int id, Requests requests, Module reqModule) {
		super();
		this.id = id;
		this.requests = requests;
		this.reqModule = reqModule;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Requests getRequests() {
		return requests;
	}

	public void setRequests(Requests requests) {
		this.requests = requests;
	}

	public Module getReqModule() {
		return reqModule;
	}

	public void setReqModule(Module reqModule) {
		this.reqModule = reqModule;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		return "ReqLink [id=" + id + ", requestId=" + requestId + ", reqModuleId=" + reqModuleId + ", affiliation="
				+ affiliation + "]";
=======
		return "ReqLink [id=" + id + ", requests=" + requests + ", reqModule=" + reqModule + "]";
>>>>>>> a98e74d42e3346aa3ad9d535465a61e396077418
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((affiliation == null) ? 0 : affiliation.hashCode());
		result = prime * result + id;
		result = prime * result + ((reqModule == null) ? 0 : reqModule.hashCode());
		result = prime * result + ((requests == null) ? 0 : requests.hashCode());
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
		if (reqModule == null) {
			if (other.reqModule != null)
				return false;
		} else if (!reqModule.equals(other.reqModule))
			return false;
		if (requests == null) {
			if (other.requests != null)
				return false;
		} else if (!requests.equals(other.requests))
			return false;
		return true;
	}

}
