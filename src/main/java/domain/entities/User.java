package domain.entities;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    private String Id;
    private String FirstName;
    private String LastName;
    private String Login;
    private String Password;
    private String Email;
    private Long Created;
    private boolean Enabled;
}