package muenchen.praxis.mfem.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class User implements Serializable {

    @GeneratedValue
    @Id
    @Column(name = "UserID")
    private int id;
    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<RoleAccess> roleList;


    public User() {}

    public User(int id, String username, String password, List<RoleAccess> roleList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleList = roleList;
    }

}
