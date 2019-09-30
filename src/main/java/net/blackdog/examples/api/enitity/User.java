package net.blackdog.examples.api.enitity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	@JsonProperty
	@NotNull
	private String name;
	
	@JsonProperty
	@NotNull
	private String lastName;
	
	@JsonProperty
	@NotNull
	private String alias;

	@JsonProperty
	@NotNull
	private String password;

	@JsonProperty
	@NotNull
	@Email
	private String email;

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	 
}
