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
	private Requests requestId;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "fk_rm")
	private Module reqModuleId;

	public ReqLink() {
		super();
	}

	public ReqLink(int id, Requests requestId, Module reqModuleId) {
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

	public Requests getRequestId() {
		return requestId;
	}

	public void setRequestId(Requests requestId) {
		this.requestId = requestId;
	}

	public Module getReqModuleId() {
		return reqModuleId;
	}

	public void setReqModuleId(Module reqModuleId) {
		this.reqModuleId = reqModuleId;
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
		result = prime * result + ((reqModuleId == null) ? 0 : reqModuleId.hashCode());
		result = prime * result + ((requestId == null) ? 0 : requestId.hashCode());
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
		if (reqModuleId == null) {
			if (other.reqModuleId != null)
				return false;
		} else if (!reqModuleId.equals(other.reqModuleId))
			return false;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		return true;
	}

}
