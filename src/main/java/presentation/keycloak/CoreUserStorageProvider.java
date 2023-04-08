package presentation.keycloak;

import domain.entities.User;
import domain.interfaces.ICoreUserRepository;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputUpdater;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.*;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;
import org.keycloak.storage.user.UserRegistrationProvider;

import java.util.Map;
import java.util.stream.Stream;
import java.util.logging.Logger;

public class CoreUserStorageProvider implements UserStorageProvider,
        UserLookupProvider, UserQueryProvider, CredentialInputUpdater, CredentialInputValidator,
        UserRegistrationProvider {

    Logger logger = Logger.getLogger(CoreUserStorageProvider.class.getName());

    private final KeycloakSession session;
    private final ComponentModel model;
    private final ICoreUserRepository repository;

    public CoreUserStorageProvider(KeycloakSession session, ComponentModel model, ICoreUserRepository repository){
        this.session = session;
        this.model = model;
        this.repository = repository;
    }

    @Override
    public boolean supportsCredentialType(String s) {
        return PasswordCredentialModel.TYPE.equals(s);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realmModel, UserModel userModel, String s) {
        return supportsCredentialType(s);
    }

    @Override
    public boolean isValid(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
        if(!supportsCredentialType(credentialInput.getType()) || !(credentialInput instanceof UserCredentialModel))
            return false;
        UserCredentialModel cred = (UserCredentialModel)credentialInput;
        return repository.validateCredentials(userModel.getUsername(), cred.getChallengeResponse());
    }

    @Override
    public boolean updateCredential(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
        if(!supportsCredentialType(credentialInput.getType()) || !(credentialInput instanceof UserCredentialModel))
            return false;
        UserCredentialModel cred = (UserCredentialModel)credentialInput;
        return repository.updateCredentials(userModel.getUsername(), cred.getChallengeResponse());
    }

    @Override
    public void disableCredentialType(RealmModel realmModel, UserModel userModel, String s) {

    }

    @Override
    public Stream<String> getDisableableCredentialTypesStream(RealmModel realmModel, UserModel userModel) {
        return Stream.empty();
    }

    @Override
    public void close() {

    }

    @Override
    public UserModel getUserById(RealmModel realmModel, String s) {
        String externalId = StorageId.externalId(s);
        return new UserAdapter(session, realmModel, model, repository.findUserById(externalId));
    }

    @Override
    public UserModel getUserByUsername(RealmModel realmModel, String s) {
        User user = repository.findUserByLogin(s);
        if (user != null)
            return new UserAdapter(session, realmModel, model, user);
        return null;
    }

    @Override
    public UserModel getUserByEmail(RealmModel realmModel, String s) {
        User user = repository.findUserByEmail(s);
        if(user != null)
            return new UserAdapter(session, realmModel, model, user);
        return null;
    }

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realmModel, String s) {
        return repository.findUsers(s).stream().
                map(user -> new UserAdapter(session, realmModel, model, user));
    }

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realmModel, String s, Integer integer, Integer integer1) {
        return searchForUserStream(realmModel, s);
    }

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realmModel, Map<String, String> map, Integer integer, Integer integer1) {
        return repository.getAllUsers().stream()
                .map(user -> new UserAdapter(session, realmModel, model, user));
    }

    @Override
    public Stream<UserModel> getGroupMembersStream(RealmModel realmModel, GroupModel groupModel, Integer integer, Integer integer1) {
        return Stream.empty();
    }

    @Override
    public Stream<UserModel> searchForUserByUserAttributeStream(RealmModel realmModel, String s, String s1) {
        return Stream.empty();
    }

    @Override
    public UserModel addUser(RealmModel realmModel, String s) {
        return null;
    }

    @Override
    public boolean removeUser(RealmModel realmModel, UserModel userModel) {
        return false;
    }
}
