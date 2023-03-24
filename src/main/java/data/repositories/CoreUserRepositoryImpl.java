package data.repositories;

import domain.entities.CoreUser;
import domain.interfaces.ICoreUserRepository;

import java.util.List;

public class CoreUserRepositoryImpl implements ICoreUserRepository {
    @Override
    public List<CoreUser> getAllUsers() {
        return null;
    }

    @Override
    public CoreUser findUserById(String id) {
        return null;
    }

    @Override
    public CoreUser findUserByLogin(String Login) {
        return null;
    }

    @Override
    public CoreUser findUserByEmail(String Email) {
        return null;
    }

    @Override
    public List<CoreUser> findUsers(String query) {
        return null;
    }

    @Override
    public boolean validateCredentials(String Login, String Password) {
        return false;
    }

    @Override
    public boolean updateCredentials(String Login, String Password) {
        return false;
    }
}
