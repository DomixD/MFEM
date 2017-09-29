package muenchen.praxis.mfem.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class RoleAccess implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "RoleAccessID")
    private int id;
    private String role;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Permission> permissionList;

    public RoleAccess() {}

    public RoleAccess(int id, String role ) {
        this.id = id;
        this.role = role;
    }

}
