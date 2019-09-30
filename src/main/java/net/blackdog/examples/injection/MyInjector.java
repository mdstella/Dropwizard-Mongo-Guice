package net.blackdog.examples.injection;

import com.google.inject.AbstractModule;

import net.blackdog.examples.dao.UserDao;
import net.blackdog.examples.dao.mongo.UserDaoImpl;
import net.blackdog.examples.service.UserService;
import net.blackdog.examples.service.UserServiceImpl;

public class MyInjector extends AbstractModule{

	@Override
	protected void configure() {
		bind(UserService.class).to(UserServiceImpl.class);
		bind(UserDao.class).to(UserDaoImpl.class);
	}

}
