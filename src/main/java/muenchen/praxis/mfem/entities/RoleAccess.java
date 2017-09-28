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
    private boolean readAccess;
    private boolean createClassi;
    private boolean createReq;
    private boolean createQuest;
    private boolean createMetric;
    private boolean evaluation;
    private boolean adminAccess;
    private boolean createCategory;

    public RoleAccess() {}

    public RoleAccess(int id, String role, boolean readAccess, boolean createClassi, boolean createReq, boolean createQuest, boolean createMetric, boolean evaluation, boolean adminAccess, boolean createCategory) {
        this.id = id;
        this.role = role;
        this.readAccess = readAccess;
        this.createClassi = createClassi;
        this.createReq = createReq;
        this.createQuest = createQuest;
        this.createMetric = createMetric;
        this.evaluation = evaluation;
        this.adminAccess = adminAccess;
        this.createCategory = createCategory;
    }

}
