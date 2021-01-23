package com.emird.medicalCardApp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority {
	@Id
	private String username;

	private String authorities;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
}
