import java.util.Arrays;
import java.util.List;

public class CoreUserRepository {
    private List<CoreUser> users;

    public CoreUserRepository() {
        users = Arrays.asList(
                new CoreUser("1", "Администратор", "admin", "admin", "admin@gmail.com"),
                new CoreUser("2", "Иван Иван", "ivanow", "ivanow", "ivanow@gmail.com"),
                new CoreUser("3", "Владимир Суслов", "vladimir", "vladimir", "vladimir@gmail.com"),
                new CoreUser("4", "Александр Андреевич", "alexander", "alexandr", "alexandr@gmail.com"));
    }

    List<CoreUser> getAll() {
        return users;
    }

    int getUserCount() {
        return users.size();
    }

    CoreUser findUserById(String id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    CoreUser findUserByLogin(String Login) {
        return users.stream().filter(user -> user.getLogin().equals(Login)).findFirst().orElse(null);
    }

    boolean validateCredentials(String Login, String Password){
        return findUserByLogin(Login).getPassword().equals(Password);
    }
}
