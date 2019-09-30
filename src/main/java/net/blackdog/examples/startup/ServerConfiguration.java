package net.blackdog.examples.startup;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class ServerConfiguration extends Configuration {
	
	@JsonProperty
	@NotNull
	private String dbHost;
	
	@JsonProperty
	@NotNull
	private Integer dbPort;
	
	@JsonProperty
	@NotNull
	private String dbName;

	public String getDbHost() {
		return dbHost;
	}

	public String getDbName() {
		return dbName;
	}

	public Integer getDbPort() {
		return dbPort;
	}

}
