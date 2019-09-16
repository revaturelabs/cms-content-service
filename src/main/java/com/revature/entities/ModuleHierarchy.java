package com.revature.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "joins")
public class ModuleHierarchy {

	// TODO: missing annotation
	private Module primaryModule;
	
	// TODO: missing annotation
	private Set<Module> children;
	
	// TODO: missing annotation
	private Set<Content> content;

	public Module getPrimaryModule() {
		return primaryModule;
	}

	public void setPrimaryModule(Module primaryModule) {
		this.primaryModule = primaryModule;
	}

	public Set<Module> getChildren() {
		return children;
	}

	public void setChildren(Set<Module> children) {
		this.children = children;
	}

	public Set<Content> getContent() {
		return content;
	}

	public void setContent(Set<Content> content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((primaryModule == null) ? 0 : primaryModule.hashCode());
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
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (primaryModule == null) {
			if (other.primaryModule != null)
				return false;
		} else if (!primaryModule.equals(other.primaryModule))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ModuleHierarchy [primaryModule=" + primaryModule + ", children=" + children + ", content=" + content
				+ "]";
	}
	
	
	
	
}
