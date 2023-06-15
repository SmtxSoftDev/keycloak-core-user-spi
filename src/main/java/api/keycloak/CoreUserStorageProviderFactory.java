package api.keycloak;

import application.services.user.IUserService;
import application.services.user.UserService;
import domain.helpers.Constants;
import infrastructure.drivers.db.DatabaseTypes;
import infrastructure.repositories.UserRepositoryImpl;
import infrastructure.utils.HibernateUtil;
import org.hibernate.SessionFactory;
import org.keycloak.component.ComponentModel;
import org.keycloak.component.ComponentValidationException;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;
import org.keycloak.storage.UserStorageProviderFactory;
import org.keycloak.utils.StringUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoreUserStorageProviderFactory implements UserStorageProviderFactory<CoreUserStorageProvider> {
    @Override
    public CoreUserStorageProvider create(KeycloakSession keycloakSession, ComponentModel componentModel) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(componentModel);
        IUserService userService = new UserService(new UserRepositoryImpl(sessionFactory));
        return new CoreUserStorageProvider(keycloakSession, componentModel, userService);
    }

    @Override
    public String getId() {
        return "core-user-spi";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        List<String> driverListOfDb = Stream.of(DatabaseTypes.values())
                .map(DatabaseTypes::name).collect(Collectors.toList());
        return ProviderConfigurationBuilder.create()
                .property(Constants.DB_DRIVER, "Database driver", "Select database driver", ProviderConfigProperty.LIST_TYPE, "", driverListOfDb)
                .property(Constants.DB_SERVER, "Database server", "Address of Sql server", ProviderConfigProperty.STRING_TYPE, "", null)
                .property(Constants.DB_NAME, "Database name", "Database name", ProviderConfigProperty.STRING_TYPE, "", null)
                .property(Constants.DB_USERNAME, "Database Username", "Database username", ProviderConfigProperty.STRING_TYPE, "", null)
                .property(Constants.DB_PASSWORD, "Database Password", "Database password", ProviderConfigProperty.PASSWORD, "", null)
                .build();
    }

    @Override
    public void validateConfiguration(KeycloakSession session, RealmModel realm, ComponentModel config) throws ComponentValidationException {
        if(StringUtil.isBlank(config.get(Constants.DB_DRIVER))){
            throw new ComponentValidationException("Database driver is not selected, before to save please select your database driver please.");
        }
    }
}