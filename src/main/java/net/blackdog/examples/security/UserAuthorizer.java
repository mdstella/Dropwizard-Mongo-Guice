package net.blackdog.examples.security;

import io.dropwizard.auth.Authorizer;
import net.blackdog.examples.api.enitity.Role;

public class UserAuthorizer implements Authorizer<UserDetail> {

	@Override
	public boolean authorize(UserDetail principal, String role) {
		if(principal.getRole() != null && principal.getRole().equals(Role.valueOf(role))) {
            return true;
        }
        return false;
	}
}
