package net.blackdog.examples.api.enitity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Role {
	ANONYMOUS, ADMIN, SUPER_ADMIN;
	
	//TODO mejorar esto
	public final static String ANONYMOUS_NAME = "ANONYMOUS";
	public final static String ADMIN_NAME = "ADMIN";
	public final static String SUPER_ADMIN_NAME = "SUPER_ADMIN";
	
	//TODO borrar esto
	private static final List<Role> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static Role randomRole() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
