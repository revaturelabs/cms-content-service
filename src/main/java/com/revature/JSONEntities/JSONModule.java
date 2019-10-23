package com.revature.JSONEntities;

import java.util.Set;

import com.revature.entities.Link;
import com.revature.entities.Module;
import com.revature.entities.ReqLink;

public class JSONModule {

	private Integer id;

	private String subject;

	private long created;

	private Set<Link> links;

	private Set<ReqLink> reqLinks;

	private Set<Module> parents;

	private Set<Module> children;

	public JSONModule() {
		super();
	}

	public JSONModule(Integer id, String subject, long created, Set<Link> links, Set<ReqLink> reqLinks, Set<Module> parents,
			Set<Module> children) {
		super();
		this.id = id;
		this.subject = subject;
		this.created = created;
		this.links = links;
		this.reqLinks = reqLinks;
		this.parents = parents;
		this.children = children;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public Set<Link> getLinks() {
		return links;
	}

	public void setLinks(Set<Link> links) {
		this.links = links;
	}

	public Set<ReqLink> getReqLinks() {
		return reqLinks;
	}

	public void setReqLinks(Set<ReqLink> reqLinks) {
		this.reqLinks = reqLinks;
	}

	public Set<Module> getParents() {
		return parents;
	}

	public void setParents(Set<Module> parents) {
		this.parents = parents;
	}

	public Set<Module> getChildren() {
		return children;
	}

	public void setChildren(Set<Module> children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + (int) (created ^ (created >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((links == null) ? 0 : links.hashCode());
		result = prime * result + ((parents == null) ? 0 : parents.hashCode());
		result = prime * result + ((reqLinks == null) ? 0 : reqLinks.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		JSONModule other = (JSONModule) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (created != other.created)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (links == null) {
			if (other.links != null)
				return false;
		} else if (!links.equals(other.links))
			return false;
		if (parents == null) {
			if (other.parents != null)
				return false;
		} else if (!parents.equals(other.parents))
			return false;
		if (reqLinks == null) {
			if (other.reqLinks != null)
				return false;
		} else if (!reqLinks.equals(other.reqLinks))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JSONModule [id=" + id + ", subject=" + subject + ", created=" + created + ", links=" + links
				+ ", reqLinks=" + reqLinks + ", parents=" + parents + ", children=" + children + "]";
	}

}
