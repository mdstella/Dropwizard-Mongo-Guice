package net.blackdog.examples.security;

import java.security.Principal;

import net.blackdog.examples.api.enitity.Role;

public class UserDetail implements Principal {

	private final String name;

	private final Role role;

	public UserDetail(String name) {
		this.name = name;
		this.role = null;
	}

	public UserDetail(String name, Role role) {
		this.name = name;
		this.role = role;
	}

	@Override
	public String getName() {
		return name;
	}

	public Role getRole() {
		return role;
	}

}
