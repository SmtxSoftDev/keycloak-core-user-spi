import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

public class CoreUserStorageProviderFactory implements UserStorageProviderFactory<CoreUserStorageProvider> {
    @Override
    public CoreUserStorageProvider create(KeycloakSession keycloakSession, ComponentModel componentModel) {
        return new CoreUserStorageProvider(keycloakSession, componentModel);
    }
    @Override
    public String getId() {
        return "core-user-spi";
    }
}
