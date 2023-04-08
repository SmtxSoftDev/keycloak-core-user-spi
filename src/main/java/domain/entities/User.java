package domain.entities;

import lombok.Data;

@Data
public class User {
    private String Id;
    private String Name;
    private String FirstName;
    private String LastName;
    private String Login;
    private String Password;
    private String Email;
    private boolean Enabled;
    private Long Created;

    public User(String Id,
                String FirstName,
                String LastName,
                String Login,
                String Password,
                String Email,
                Long Created,
                boolean Enabled) {
        this.Id = Id;
        this.Name = (FirstName+" "+LastName);
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Login = Login;
        this.Password = Password;
        this.Email = Email;
        this.Created = Created;
        this.Enabled = Enabled;
    }
}