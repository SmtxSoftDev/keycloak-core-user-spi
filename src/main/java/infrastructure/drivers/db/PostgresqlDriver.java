package infrastructure.drivers.db;

import domain.helpers.Constants;
import org.keycloak.component.ComponentModel;

import java.util.Properties;

public class PostgresqlDriver implements IDatabaseDriver{
    @Override
    public Properties getProperties(ComponentModel model) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url",
                "jdbc:postgresql://"+model.get(Constants.DB_SERVER) +"/"+model.get(Constants.DB_NAME));
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.connection.username", model.get(Constants.DB_USERNAME));
        properties.setProperty("hibernate.connection.password", model.get(Constants.DB_PASSWORD));
        properties.setProperty("hibernate.connection.driver_class", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("show_sql", "true");
        return properties;
    }
}