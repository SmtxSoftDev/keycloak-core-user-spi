package infrastructure.utils;

import domain.entities.User;
import domain.helpers.Constants;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.keycloak.component.ComponentModel;

import java.util.Properties;
import java.util.logging.Logger;

public class HibernateUtil {

    private static Logger logger = Logger.getLogger(HibernateUtil.class.getName());

    private static SessionFactory sessionFactory = null;

    private static SessionFactory buildSessionFactory(ComponentModel model) {
        try {
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.url", "jdbc:sqlserver://"+model.get(Constants.DB_SERVER)+";databaseName="+model.get(Constants.DB_NAME)+";encrypt=true;trustServerCertificate=true");
            properties.setProperty("dialect", "org.hibernate.dialect.SQLServerDialect");
            properties.setProperty("hibernate.connection.username", model.get(Constants.DB_USERNAME));
            properties.setProperty("hibernate.connection.password", model.get(Constants.DB_PASSWORD));
            properties.setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            properties.setProperty("show_sql", "true");
            Configuration configuration = new Configuration();
            configuration.addProperties(properties);
            configuration.addAnnotatedClass(User.class);
            return configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("HibernateUtil error:" + e.getMessage());
            throw new RuntimeException("There was an error building the factory");
        }
    }

    public static synchronized SessionFactory getSessionFactory(ComponentModel model) {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory(model);
        }
        return sessionFactory;
    }
}