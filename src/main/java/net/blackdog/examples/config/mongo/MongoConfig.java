package net.blackdog.examples.config.mongo;

public class MongoConfig {

	private static String host = null;
	private static Integer port = null;
	private static String dbName = null;

	public static String getHost() {
		return host;
	}

	public static Integer getPort() {
		return port;
	}

	public static String getDbName() {
		return dbName;
	}

	public static void setHost(String host) {
		if (MongoConfig.host == null)
			MongoConfig.host = host;
	}

	public static void setDbName(String dbName) {
		if (MongoConfig.dbName == null)
			MongoConfig.dbName = dbName;
	}

	public static void setPort(Integer port) {
		if (MongoConfig.port == null)
			MongoConfig.port = port;
	}

}
