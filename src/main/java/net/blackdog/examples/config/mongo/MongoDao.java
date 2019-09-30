package net.blackdog.examples.config.mongo;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoDao {

	private MongoClient connection;
	private MongoDatabase db;

	private static MongoDao mongoDao = null;

	private MongoDao() {
		// Connection is created as below
		connection = new MongoClient(new ServerAddress(MongoConfig.getHost(), MongoConfig.getPort()));
		db = connection.getDatabase(MongoConfig.getDbName());
	}

	// A singleton design pattern to get the connection for the outside world
	public static MongoDao getInstance() {
		if (mongoDao == null) {
			mongoDao = new MongoDao();
		}
		return mongoDao;
	}

	public MongoDatabase getDb() {
		return db;
	}
}
