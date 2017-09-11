package muenchen.praxis.mfem.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = "requirementList")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassificationID")
    private int id;
    private String name;
    private String description;
    @OneToMany(mappedBy="classi", cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
    private List<Requirement> requirementList;

}
