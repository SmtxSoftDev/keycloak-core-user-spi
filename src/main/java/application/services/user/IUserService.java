package application.services.user;

import domain.entities.User;

import java.util.List;

public interface IUserService {
    public User findUserById(String id);

    public User findUserByLogin(String Login);

    public User findUserByEmail(String Email);

    public List<User> findUsers(String query);
    public boolean validateCredentials(String Login, String Password);

    public boolean updateCredentials(String Login, String Password);
}
