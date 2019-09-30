package net.blackdog.examples.service;

import java.util.List;

import net.blackdog.examples.api.enitity.Role;
import net.blackdog.examples.api.enitity.User;

public interface UserService {
	
	public void registerUser(User user);

	public List<User> findByRole(Role role);
	
}
