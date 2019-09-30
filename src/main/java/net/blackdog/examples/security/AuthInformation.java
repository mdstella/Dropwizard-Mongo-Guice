package net.blackdog.examples.security;

import java.io.Serializable;

import net.blackdog.examples.api.enitity.Role;

//TODO volar esto y usar algo de caching authenticator
public class AuthInformation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3782059943935564709L;

	public AuthInformation(Role role, String pass) {
		this.role = role;
		this.pass = pass;
	}
	private Role role;
	private String pass;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
