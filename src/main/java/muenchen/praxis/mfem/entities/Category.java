package muenchen.praxis.mfem.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@ToString(exclude = "requirementList")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private int id;
    private String category;
    @OneToMany(mappedBy = "category", cascade = {/*CascadeType.PERSIST, */CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, fetch= FetchType.EAGER)
    private List<Requirement> requirementList;

    public Category () {}

    public Category (int id, String category, List<Requirement> requirementList) {
        this.id = id;
        this.category = category;
        this.requirementList = requirementList;
    }

}
