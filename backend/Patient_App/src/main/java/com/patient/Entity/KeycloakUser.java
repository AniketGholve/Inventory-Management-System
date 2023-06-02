package com.patient.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class KeycloakUser{
	
	@Id
	@Column(name ="user_id")
	private String id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="email")
	private String email;
	
	

}
