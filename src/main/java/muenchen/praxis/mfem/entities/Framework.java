package muenchen.praxis.mfem.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Framework implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FrameworkID")
    private int id;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FrameworkEvaluationID")
    private FrameworkEvaluation frameworkEvaluation;

}
