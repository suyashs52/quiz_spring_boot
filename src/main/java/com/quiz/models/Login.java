package com.quiz.models;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@MappedSuperclass
public class Login {
	@NotBlank
	@Size(min = 3, max = 100)
	protected String username;

	@NotBlank
	@Size(min = 6, max = 100)
	@Pattern(regexp = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#]).{6,100})$", message = "must be at least one character, number , @#")
	@Transient
	protected String credential;
	
	 
	@Size( max = 100)
	protected String password;
	
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

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}
 
	

}
