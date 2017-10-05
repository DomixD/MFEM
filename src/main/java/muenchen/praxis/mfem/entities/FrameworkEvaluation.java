package muenchen.praxis.mfem.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class FrameworkEvaluation implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FrameworkEvaluationID")
    private int id;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "FrameworkID")
    private Framework framework;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "ClassificationID")
    private Classification classification;

    public FrameworkEvaluation() {}

    public FrameworkEvaluation(int id, Framework framework, Classification classification) {
        this.id = id;
        this.framework = framework;
        this.classification = classification;
    }

}
