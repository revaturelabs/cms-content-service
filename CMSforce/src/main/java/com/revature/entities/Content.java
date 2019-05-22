package com.revature.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Content {
	@Id
	@GeneratedValue
	@Column(name = "c_id")
	private int id;

	private String title;

	private String format;

	private String description;

	private String url;

	public Content() {
		super();
	}

	public Content(int id, String title, String format, String description, String url) {
		super();
		this.id = id;
		this.title = title;
		this.format = format;
		this.description = description;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", title=" + title + ", format=" + format + ", description=" + description
				+ ", url=" + url + "]";
	}

}
