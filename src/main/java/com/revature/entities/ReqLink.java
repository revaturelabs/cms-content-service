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
@Table(name = "reqLink")
public class ReqLink {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "z_id")
	private int id;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "fk_r")
	private Requests requests;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "fk_rm")
	private ReqModule reqModule;

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

	public Requests getRequests() {
		return requests;
	}

	public void setRequests(Requests requests) {
		this.requests = requests;
	}

	public ReqModule getReqModule() {
		return reqModule;
	}

	public void setReqModule(ReqModule reqModule) {
		this.reqModule = reqModule;
	}

	@Override
	public String toString() {
		return "ReqLink [id=" + id + ", requestId=" + requestId + ", reqModuleId=" + reqModuleId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (requestId != other.requestId)
			return false;
		return true;
	}

}
