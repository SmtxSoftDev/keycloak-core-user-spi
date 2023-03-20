import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

public class CoreUserStorageProviderFactory implements UserStorageProviderFactory<CoreUserStorageProvider> {
    @Override
    public CoreUserStorageProvider create(KeycloakSession keycloakSession, ComponentModel componentModel) {
        CoreUserRepository repository = new CoreUserRepository();
        return new CoreUserStorageProvider(keycloakSession, componentModel, repository);
    }
    @Override
    public String getId() {
        return "core-user-spi";
    }
}
