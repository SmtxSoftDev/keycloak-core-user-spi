package application.services.user;

import domain.entities.User;
import domain.interfaces.IUserRepository;

import java.util.List;

public class UserService implements IUserService{
    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserByLogin(String Login) {
        return userRepository.findUserByLogin(Login);
    }

    @Override
    public User findUserByEmail(String Email) {
        return userRepository.findUserByEmail(Email);
    }

    @Override
    public List<User> findUsers(String query) {
        return userRepository.findUsers(query);
    }

    @Override
    public boolean validateCredentials(String Login, String Password) {
        return findUserByLogin(Login).getPassword().equals(Password);
    }

    @Override
    public boolean updateCredentials(String Login, String Password) {
        return true;
    }
}
