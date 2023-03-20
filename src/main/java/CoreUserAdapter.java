import org.keycloak.common.util.MultivaluedHashMap;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.LegacyUserCredentialManager;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.SubjectCredentialManager;
import org.keycloak.models.UserModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.adapter.AbstractUserAdapter;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CoreUserAdapter extends AbstractUserAdapter {

    private final CoreUser user;

    public CoreUserAdapter(KeycloakSession session,
                           RealmModel realm,
                           ComponentModel storageProviderModel,
                           CoreUser user) {
        super(session, realm, storageProviderModel);
        this.storageId = new StorageId(storageProviderModel.getId(), user.getId());
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public String getEmail(){
        return user.getEmail();
    }

    @Override
    public SubjectCredentialManager credentialManager() {
        return new LegacyUserCredentialManager(session, realm, this);
    }

    @Override
    public Map<String, List<String>> getAttributes(){
        MultivaluedHashMap<String, String> attributes
                = new MultivaluedHashMap<>();
        attributes.add(UserModel.USERNAME, getUsername());
        attributes.add(UserModel.EMAIL, getEmail());
        return attributes;
    }

    @Override
    public Stream<String> getAttributeStream(String name){
        if(name.equals(UserModel.USERNAME))
            return Stream.of(getUsername());
        return Stream.empty();
    }
}