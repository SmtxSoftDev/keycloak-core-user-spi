package data.repositories;

import domain.entities.CoreUser;
import domain.interfaces.ICoreUserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CoreUserMockRepositoryImpl implements ICoreUserRepository {
    private final List<CoreUser> users;

    public CoreUserMockRepositoryImpl() {
        Long created = System.currentTimeMillis();
        users = Arrays.asList(
                new CoreUser("1", "Администратор", "Администратор", "admin10", "admin10", "admin@gmail.com", created, true),
                new CoreUser("2", "Иван", "Иван", "ivanow", "ivanow", "ivanow@gmail.com", created, true),
                new CoreUser("3", "Владимир", "Суслов", "vladimir", "vladimir", "vladimir@gmail.com", created, true),
                new CoreUser("4", "Александр", "Андреевич", "alexander", "alexandr", "alexandr@gmail.com", created, true));
    }

    public List<CoreUser> getAllUsers(){
        return users;
    }

    public CoreUser findUserById(String id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public CoreUser findUserByLogin(String Login) {
        return users.stream().filter(user -> user.getLogin().equals(Login)).findFirst().orElse(null);
    }

    public CoreUser findUserByEmail(String Email) {
        return users.stream().filter(user -> user.getEmail().equals(Email)).findFirst().orElse(null);
    }

    public List<CoreUser> findUsers(String query) {
        return users.stream()
                .filter(user -> user.getName().contains(query) ||
                        user.getEmail().contains(query) ||
                        user.getLogin().contains(query))
                .collect(Collectors.toList());
    }

    public boolean validateCredentials(String Login, String Password) {
        return findUserByLogin(Login).getPassword().equals(Password);
    }

    public boolean updateCredentials(String Login, String Password){
        findUserByLogin(Login).setPassword(Password);
        return true;
    }
}