package data.repositories;

import domain.entities.User;
import domain.interfaces.IUserRepository;

import java.util.List;

public class UserRepositoryImpl implements IUserRepository {
    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User findUserById(String id) {
        return null;
    }

    @Override
    public User findUserByLogin(String Login) {
        return null;
    }

    @Override
    public User findUserByEmail(String Email) {
        return null;
    }

    @Override
    public List<User> findUsers(String query) {
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
