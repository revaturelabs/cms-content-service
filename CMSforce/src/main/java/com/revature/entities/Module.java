package com.revature.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Entity
public class Module {
	@Id
	@GeneratedValue
	@Column(name = "m_id")
	private int id;

	private String subject;

	private int created;

	public Module() {
		super();
	}

	public Module(int id, String subject, int created) {
		super();
		this.id = id;
		this.subject = subject;
		this.created = created;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", subject=" + subject + ", created=" + created + "]";
	}

}
