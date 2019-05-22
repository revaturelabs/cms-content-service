package com.revature.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "content_module")
public class ContentModule {
	@Id
	@GeneratedValue
	@Column(name = "cm_id")
	private int id;

	@Column(name = "fk_c")
	private int fkContent;

	@Column(name = "fk_m")
	private int fkModule;

	private String affiliation;

	public ContentModule() {
		super();
	}

	public ContentModule(int id, int fkContent, int fkModule, String affiliation, List<Module> modules,
			List<Content> contents) {
		super();
		this.id = id;
		this.fkContent = fkContent;
		this.fkModule = fkModule;
		this.affiliation = affiliation;
		this.modules = modules;
		this.contents = contents;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Module> modules = new ArrayList<Module>();

	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Content> contents = ArrayList<Content>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFkContent() {
		return fkContent;
	}

	public void setFkContent(int fkContent) {
		this.fkContent = fkContent;
	}

	public int getFkModule() {
		return fkModule;
	}

	public void setFkModule(int fkModule) {
		this.fkModule = fkModule;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "ContentModule [id=" + id + ", fkContent=" + fkContent + ", fkModule=" + fkModule + ", affiliation="
				+ affiliation + "]";
	}
}
