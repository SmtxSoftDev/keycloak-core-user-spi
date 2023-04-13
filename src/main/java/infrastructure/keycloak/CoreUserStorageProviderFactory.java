package infrastructure.keycloak;

import data.repositories.UserRepositoryImpl;
import domain.helpers.Constants;
import domain.interfaces.IUserRepository;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;
import org.keycloak.storage.UserStorageProviderFactory;

import java.util.List;

public class CoreUserStorageProviderFactory implements UserStorageProviderFactory<CoreUserStorageProvider> {
    @Override
    public CoreUserStorageProvider create(KeycloakSession keycloakSession, ComponentModel componentModel) {
        //IUserRepository repository = new UserMockRepositoryImpl();
        IUserRepository repository = new UserRepositoryImpl();
        return new CoreUserStorageProvider(keycloakSession, componentModel, repository);
    }

    @Override
    public String getId() {
        return "core-user-spi";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return ProviderConfigurationBuilder.create()
                .property(Constants.DB_SERVER, "Database server", "Address of Sql server", ProviderConfigProperty.STRING_TYPE, "", null)
                .property(Constants.DB_NAME, "Database name", "Database name", ProviderConfigProperty.STRING_TYPE, "", null)
                .property(Constants.DB_USERNAME, "Database Username", "Database username", ProviderConfigProperty.STRING_TYPE, "", null)
                .property(Constants.DB_PASSWORD, "Database Password", "Database password", ProviderConfigProperty.PASSWORD, "", null)
                .build();
    }
}