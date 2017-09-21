package muenchen.praxis.mfem.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class RoleAccess implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "RoleAccessID")
    private int id;
    private String role;
    private boolean readAccess;
    private boolean writeAccess;

    public RoleAccess() {}

    public RoleAccess(int id, String role, boolean readAccess, boolean writeAccess) {
        this.id = id;
        this.role = role;
        this.readAccess = readAccess;
        this.writeAccess = writeAccess;
    }

}
