package infrastructure.utils;

import domain.entities.User;
import domain.helpers.Constants;
import infrastructure.drivers.db.DatabaseDriverFactory;
import infrastructure.drivers.db.DatabaseTypes;
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
            logger.info(model.get(Constants.DB_DRIVER));
            DatabaseTypes databaseType = DatabaseTypes.valueOf(model.get(Constants.DB_DRIVER));
            Properties properties = DatabaseDriverFactory.getInstance(databaseType).getProperties(model);
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