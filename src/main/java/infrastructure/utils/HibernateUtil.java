package infrastructure.utils;

import domain.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Properties;
import java.util.logging.Logger;

public class HibernateUtil {

    private static Logger logger = Logger.getLogger(HibernateUtil.class.getName());

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.url", "jdbc:sqlserver://DESKTOP-TNNQF82\\SQLSERVER;databaseName=Dev;encrypt=true;trustServerCertificate=true");
            properties.setProperty("dialect", "org.hibernate.dialect.SQLServerDialect");
            properties.setProperty("hibernate.connection.username", "admin");
            properties.setProperty("hibernate.connection.password", "admin");
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
            logger.warning("HibernateUtil error:"+e.getMessage());
            throw new RuntimeException("There was an error building the factory");
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}