package muenchen.praxis.mfem.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = "requirementList")
@Entity
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassificationID")
    private int id;
    private String name;
    private String description;
    @OneToMany(mappedBy="classi", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private List<Requirement> requirementList;

    public Classification() {}

    public Classification(int id, String name, String description, List<Requirement> requirementList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.requirementList = requirementList;
    }

}
