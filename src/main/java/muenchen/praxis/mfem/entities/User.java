package muenchen.praxis.mfem.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class User implements Serializable {

    @GeneratedValue
    @Id
    private int id;
    private String username;
    private String password;
    private String role;
    
}
