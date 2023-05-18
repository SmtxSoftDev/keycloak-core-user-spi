package infrastructure.drivers.db;

import domain.helpers.Constants;
import org.keycloak.component.ComponentModel;

import java.util.Properties;

public class MysqlDriver implements IDatabaseDriver{
    @Override
    public Properties getProperties(ComponentModel model) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url",
                "jdbc:mysql://"+model.get(Constants.DB_SERVER)+"/"+model.get(Constants.DB_NAME)+"?useSSL=false");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.connection.username", model.get(Constants.DB_USERNAME));
        properties.setProperty("hibernate.connection.password", model.get(Constants.DB_PASSWORD));
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("show_sql", "true");
        return properties;
    }
}