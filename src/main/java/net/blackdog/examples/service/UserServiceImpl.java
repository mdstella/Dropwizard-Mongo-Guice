package net.blackdog.examples.service;

import java.util.List;

import com.google.inject.Inject;

import net.blackdog.examples.api.enitity.Role;
import net.blackdog.examples.api.enitity.User;
import net.blackdog.examples.dao.UserDao;
//TODO verificar si hace falta una capa de servicios ya que no hay transacciones
public class UserServiceImpl implements UserService {

	@Inject
	private UserDao userDao;
	
	@Override
	public void registerUser(User user) {
		userDao.registerUser(user);
	}

	@Override
	public List<User> findByRole(Role role) {
		return userDao.findByRole(role);
	}

}
