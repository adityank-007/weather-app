package me.aditya.weather;

import com.mongodb.client.MongoClient;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import me.aditya.weather.api.WeatherResponse;
import me.aditya.weather.configuration.AdvancedConfiguration;
import me.aditya.weather.database.MongoDBFactoryConnection;
import me.aditya.weather.database.MongoDBManaged;
import me.aditya.weather.database.dao.UserDAO;
import me.aditya.weather.resource.WeatherResource;
import me.aditya.weather.service.UserService;
import me.aditya.weather.service.WeatherService;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class WeatherApplication extends Application<AdvancedConfiguration> {
    public static void main(String[] args) throws Exception {
        new WeatherApplication().run(args);
    }
    @Override
    public void run(AdvancedConfiguration advancedConfiguration, Environment environment) throws Exception {
        final MongoDBFactoryConnection mongoDBManagerConn =
                new MongoDBFactoryConnection(advancedConfiguration.getMongoDBConnection());
        MongoClient mongoClient = mongoDBManagerConn.getClient();
        final MongoDBManaged mongoDBManaged = new MongoDBManaged(mongoClient);
        final UserDAO userInfoDAO = new UserDAO(mongoDBManagerConn);

        final WeatherResource weatherResource = new WeatherResource(new UserService(userInfoDAO), new WeatherService());

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        environment.jersey().register(weatherResource);
        environment.lifecycle().manage(mongoDBManaged);
    }
}
