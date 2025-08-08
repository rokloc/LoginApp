package io.github.rokloc.login.entity;

import jakarta.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private boolean isAdmin;

	//getter/setter
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	
}
