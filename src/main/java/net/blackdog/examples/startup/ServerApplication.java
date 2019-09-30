package net.blackdog.examples.startup;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.blackdog.examples.api.UserEndpoint;
import net.blackdog.examples.config.mongo.MongoConfig;
import net.blackdog.examples.config.mongo.MongoDao;
import net.blackdog.examples.injection.MyInjector;
import net.blackdog.examples.security.UserAuthenticator;
import net.blackdog.examples.security.UserAuthorizer;
import net.blackdog.examples.security.UserDetail;

public class ServerApplication extends Application<ServerConfiguration> {
	//TODO llevar las constantes a un lado unico que se reusan por todos lados
	private static final String REGISTRATION_INDEX = "registrationIndex";
	private static final String EMAIL = "email";
	private static final String ALIAS = "alias";
	private static final String ALIAS_INDEX = "aliasIndex";
	private static final String USERS_COLLECTION = "users";
	

	@Override
	public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void run(ServerConfiguration configuration, Environment environment) throws Exception {
		// Injection
		Injector injector = Guice.createInjector(new MyInjector());
		environment.jersey().register(injector.getInstance(UserEndpoint.class));

		// Set up MongoDB config
		MongoConfig.setDbName(configuration.getDbName());
		MongoConfig.setHost(configuration.getDbHost());
		MongoConfig.setPort(configuration.getDbPort());
		MongoDatabase db = MongoDao.getInstance().getDb();
		
		//Create registration index if not exists
		IndexOptions indexOptions = new IndexOptions();
		indexOptions.unique(true);
		indexOptions.name(REGISTRATION_INDEX);
		db.getCollection(USERS_COLLECTION).createIndex(new BasicDBObject(EMAIL,1),indexOptions);
		
		indexOptions = new IndexOptions();
		indexOptions.unique(true);
		indexOptions.name(ALIAS_INDEX);
		db.getCollection(USERS_COLLECTION).createIndex(new BasicDBObject(ALIAS,1),indexOptions);
		
		

		// Security
		environment.jersey().register(RolesAllowedDynamicFeature.class);
		final BasicCredentialAuthFilter<UserDetail> userBasicCredentialAuthFilter = new BasicCredentialAuthFilter.Builder<UserDetail>()
				.setAuthenticator(new UserAuthenticator()).setRealm("SUPER SECRET STUFF")
				.setAuthorizer(new UserAuthorizer()).buildAuthFilter();
		environment.jersey().register(new AuthDynamicFeature(userBasicCredentialAuthFilter));
		environment.jersey().register(new AuthValueFactoryProvider.Binder(UserDetail.class));
	}

	public static void main(String[] args) throws Exception {
		new ServerApplication().run(args);
	}
}