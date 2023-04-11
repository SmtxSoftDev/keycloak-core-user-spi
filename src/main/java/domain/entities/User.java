package domain.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class User {
    @Id
    private String Id;
    private String FirstName;
    private String LastName;
    private String Login;
    private String Password;
    private String Email;
    private Long Created;
    private boolean Enabled;
}