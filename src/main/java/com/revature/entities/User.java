package com.revature.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="USERS")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1085066665666780589L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "FIRST_NAME")
	private String fname;

	@Column(name = "LAST_NAME")
	private String lname;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="createdDate")
	private Date createdDate;
	
	@Column(name = "resetToken")
	private String resetToken;
	
//	columnDefinition forces the role to be either MANAGER, CUSTOMER, or VENDOR
	@Column(name="role")//, columnDefinition = "VARCHAR2(50) CHECK (role IN ('ADMIN', 'QC', 'USER'))")
	private String role;
	
	
public User() { }

public User(String email) {
	this.email=email;
}

public User(String email, String password) {
	super();
	this.email = email;
	this.password = password;
}


//public User(String resetToken) {
//	super();
//	this.resetToken = resetToken;
//}


public User(long id, String email, String password, String firstName, String lastName, Date createdDate,
		String resetToken, String role) {
	super();
	this.id = id;
	this.email = email;
	this.password = password;
	this.fname = firstName;
	this.lname = lastName;
	this.createdDate = createdDate;
	this.resetToken = resetToken;
	this.role = role;
}

public User(String fname, String lname, String email, String password, String role) {
	super();
	this.fname = fname;
	this.lname = lname;
	this.email = email;
	this.password = password;
	this.role=role;
}

public User(int id, String fname, String lname, String email, String password, String role) {
	super();
	this.id = id;
	this.fname = fname;
	this.lname = lname;
	this.email = email;
	this.password = password;
	this.role=role;
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getFname() {
	return fname;
}

public void setFname(String fname) {
	this.fname = fname;
}

public String getLname() {
	return lname;
}

public void setLname(String lname) {
	this.lname = lname;
}

public Date getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}

public String getResetToken() {
	return resetToken;
}

public void setResetToken(String resetToken) {
	this.resetToken = resetToken;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((fname == null) ? 0 : fname.hashCode());
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((lname == null) ? 0 : lname.hashCode());
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((resetToken == null) ? 0 : resetToken.hashCode());
	result = prime * result + ((role == null) ? 0 : role.hashCode());
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
	User other = (User) obj;
	if (createdDate == null) {
		if (other.createdDate != null)
			return false;
	} else if (!createdDate.equals(other.createdDate))
		return false;
	if (email == null) {
		if (other.email != null)
			return false;
	} else if (!email.equals(other.email))
		return false;
	if (fname == null) {
		if (other.fname != null)
			return false;
	} else if (!fname.equals(other.fname))
		return false;
	if (id != other.id)
		return false;
	if (lname == null) {
		if (other.lname != null)
			return false;
	} else if (!lname.equals(other.lname))
		return false;
	if (password == null) {
		if (other.password != null)
			return false;
	} else if (!password.equals(other.password))
		return false;
	if (resetToken == null) {
		if (other.resetToken != null)
			return false;
	} else if (!resetToken.equals(other.resetToken))
		return false;
	if (role == null) {
		if (other.role != null)
			return false;
	} else if (!role.equals(other.role))
		return false;
	return true;
}

@Override
public String toString() {
	return "User [id=" + id + ", email=" + email + ", password=" + password + ", fname=" + fname + ", lname=" + lname
			+ ", createdDate=" + createdDate + ", resetToken=" + resetToken + ", role=" + role + "]";
}


	
}
