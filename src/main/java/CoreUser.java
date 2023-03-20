public class CoreUser {
    private String Id;
    private String Name;
    private String Login;
    private String Password;
    private String Email;

    public CoreUser() {
    }

    public CoreUser(String Id, String Name, String Login, String Password, String Email) {
        this.Id = Id;
        this.Name = Name;
        this.Login = Login;
        this.Password = Password;
        this.Email = Email;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}