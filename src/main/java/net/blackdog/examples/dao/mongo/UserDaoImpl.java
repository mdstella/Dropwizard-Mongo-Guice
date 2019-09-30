package net.blackdog.examples.dao.mongo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import net.blackdog.examples.api.enitity.Role;
import net.blackdog.examples.api.enitity.User;
import net.blackdog.examples.config.mongo.MongoDao;
import net.blackdog.examples.dao.UserDao;

public class UserDaoImpl implements UserDao {

	//TODO poner esto en un unico lugar
	private static final String COLLECTION_NAME = "users";
	private static final String NAME = "name";
	private static final String LAST_NAME = "lastName";
	private static final String PASS = "pass";
	private static final String ALIAS = "alias";
	private static final String EMAIL = "email";
	private static final String ROLE = "role";

	@Override
	public void registerUser(User user) {
		MongoDatabase db = MongoDao.getInstance().getDb();
		MongoCollection<Document> collection = db.getCollection(COLLECTION_NAME);
		Document userDocument = new Document();
		userDocument.append(NAME, user.getName()).append(LAST_NAME, user.getLastName()).append(EMAIL, user.getEmail())
				.append(ALIAS, user.getAlias()).append(PASS, user.getPassword()).append(ROLE, Role.randomRole().toString());
		collection.insertOne(userDocument);
	}

	@Override
	public List<User> findByRole(Role role) {
		MongoDatabase db = MongoDao.getInstance().getDb();
		MongoCollection<Document> collection = db.getCollection(COLLECTION_NAME);
		BasicDBObject query = new BasicDBObject(ROLE,role.toString());
		Iterator<Document> iterator = collection.find(query).iterator();
		List<User> users = new ArrayList<User>();
		Document document;
		User user;
		while (iterator.hasNext()) {
			document = iterator.next();
			user = new User();
			user.setName(document.getString(NAME));
			user.setLastName(document.getString(LAST_NAME));
			user.setPassword(document.getString(PASS));
			user.setAlias(document.getString(ALIAS));
			user.setEmail(document.getString(EMAIL));
			users.add(user);
		}
		return users;
	}

}
