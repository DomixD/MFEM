package muenchen.praxis.mfem.entities;

import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Permission implements Serializable {

    @GeneratedValue
    @Id
    @Column(name = "PermissionID")
    private int id;
    private String permission;


    public Permission() {}
    public Permission(int id, String permission) {
        this.id = id;
        this.permission = permission;
    }

}
