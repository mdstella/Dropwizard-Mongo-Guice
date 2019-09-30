package net.blackdog.examples.security;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bson.Document;

import com.google.common.base.Optional;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import net.blackdog.examples.api.enitity.Role;
import net.blackdog.examples.config.mongo.MongoDao;

//TODO volar esto y usar algo de caching authenticator de dropwizard
//TODO ver oauth de dropwizard 
public class UserAuthenticator implements Authenticator<BasicCredentials, UserDetail> {

	// TODO poner esto en un unico lugar
	private static final String COLLECTION_NAME = "users";
	private static final String PASS = "pass";
	private static final String ALIAS = "alias";
	private static final String ROLE = "role";
	private static final Map<String, AuthInformation> VALID_USERS = new HashMap<String, AuthInformation>();

	@Override
	public Optional<UserDetail> authenticate(BasicCredentials credentials) throws AuthenticationException {

		if (VALID_USERS.containsKey(credentials.getUsername())
				&& credentials.getPassword().equals(VALID_USERS.get(credentials.getUsername()).getPass())
				&& VALID_USERS.get(credentials.getUsername()).getRole() != null)
			return Optional.of(
					new UserDetail(credentials.getUsername(), VALID_USERS.get(credentials.getUsername()).getRole()));
		else {
			MongoDatabase db = MongoDao.getInstance().getDb();
			MongoCollection<Document> collection = db.getCollection(COLLECTION_NAME);
			BasicDBObject query = new BasicDBObject();
			query.append(ALIAS, credentials.getUsername()).append(PASS, credentials.getPassword()).append(ROLE,
					new BasicDBObject("$exists", true));
			Iterator<Document> iterator = collection.find(query).iterator();
			if (iterator.hasNext()) {
				Document document = iterator.next();
				Role role = Role.valueOf(document.getString(ROLE));
				VALID_USERS.put(credentials.getUsername(), new AuthInformation(role, credentials.getPassword()));
				return Optional.of(new UserDetail(credentials.getUsername(), role));
			}
			return Optional.absent();
		}
	}
}
