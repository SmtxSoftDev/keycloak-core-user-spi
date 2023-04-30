package infrastructure.drivers.db;

import domain.helpers.Constants;
import org.keycloak.component.ComponentModel;

import java.util.Properties;

public class MssqlDriver implements IDatabaseDriver{
    @Override
    public Properties getProperties(ComponentModel model) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url",
                "jdbc:sqlserver://"+model.get(Constants.DB_SERVER)
                        +";databaseName="+model.get(Constants.DB_NAME)
                        +";encrypt=false;trustServerCertificate=false");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        properties.setProperty("hibernate.connection.username", model.get(Constants.DB_USERNAME));
        properties.setProperty("hibernate.connection.password", model.get(Constants.DB_PASSWORD));
        properties.setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.setProperty("show_sql", "true");
        return properties;
    }
}