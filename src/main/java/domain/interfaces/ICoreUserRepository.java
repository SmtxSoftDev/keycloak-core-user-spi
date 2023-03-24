package domain.interfaces;

import domain.entities.CoreUser;

import java.util.List;

public interface ICoreUserRepository {
    public List<CoreUser> getAllUsers();

    public CoreUser findUserById(String id);

    public CoreUser findUserByLogin(String Login);

    public CoreUser findUserByEmail(String Email);

    public List<CoreUser> findUsers(String query);
    public boolean validateCredentials(String Login, String Password);

    public boolean updateCredentials(String Login, String Password);
}
