package infrastructure.repositories;

import domain.entities.User;
import domain.interfaces.IUserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserMockRepositoryImpl implements IUserRepository {
    private final List<User> users;

    public UserMockRepositoryImpl() {
        Long created = System.currentTimeMillis();
        users = Arrays.asList(
                new User("1", "Администратор", "Администратор", "admin10", "admin10", "admin@gmail.com", created, true),
                new User("2", "Иван", "Иван", "ivanow", "ivanow", "ivanow@gmail.com", created, true),
                new User("3", "Владимир", "Суслов", "vladimir", "vladimir", "vladimir@gmail.com", created, true),
                new User("4", "Александр", "Андреевич", "alexander", "alexandr", "alexandr@gmail.com", created, true));

    }

    @Override
    public User findUserById(String id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public User findUserByLogin(String Login) {
        return users.stream().filter(user -> user.getLogin().equals(Login)).findFirst().orElse(null);
    }

    @Override
    public User findUserByEmail(String Email) {
        return users.stream().filter(user -> user.getEmail().equals(Email)).findFirst().orElse(null);
    }

    @Override
    public List<User> findUsers(String query) {
        return users.stream()
                .filter(user -> user.getFirstName().contains(query) ||
                        user.getLastName().contains(query) ||
                        user.getEmail().contains(query) ||
                        user.getLogin().contains(query))
                .collect(Collectors.toList());
    }

    @Override
    public boolean validateCredentials(String Login, String Password) {
        return findUserByLogin(Login).getPassword().equals(Password);
    }

    @Override
    public boolean updateCredentials(String Login, String Password){
        findUserByLogin(Login).setPassword(Password);
        return true;
    }
}