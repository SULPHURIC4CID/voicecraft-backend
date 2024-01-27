package com.skyvera.texttovoice.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "credentials")
public class CredentialsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name  = "id")
	private Long id;	
	
	@Column (name = "username")
	private String username;
	
	@Column (name="password")
	private String password;

	//No argument Constructor
	public CredentialsEntity() {
		super();
	}

	//Parameterized Constructor
	public CredentialsEntity(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	

}
