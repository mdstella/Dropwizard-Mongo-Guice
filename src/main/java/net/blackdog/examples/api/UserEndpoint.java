package net.blackdog.examples.api;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

import io.dropwizard.validation.Validated;
import net.blackdog.examples.api.enitity.Role;
import net.blackdog.examples.api.enitity.User;
import net.blackdog.examples.service.UserService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserEndpoint {

	@Inject
	private UserService userService;

	@Path("/anonymous")
	@RolesAllowed({ Role.ANONYMOUS_NAME, Role.ADMIN_NAME, Role.SUPER_ADMIN_NAME })
	@GET
	public List<User> retrieveAnonymous() {
		return userService.findByRole(Role.ANONYMOUS);
	}

	@Path("/admin")
	@RolesAllowed({ Role.ADMIN_NAME, Role.SUPER_ADMIN_NAME })
	@GET
	public List<User> retrieveAdmin() {
		return userService.findByRole(Role.ADMIN);
	}

	@Path("/super-admin")
	@RolesAllowed(Role.SUPER_ADMIN_NAME)
	@GET
	public List<User> retrieveSuperAdmin() {
		return userService.findByRole(Role.SUPER_ADMIN);
	}

	@Path("/register")
	@POST
	public void register(@Validated User user) {
		userService.registerUser(user);
	}

}
