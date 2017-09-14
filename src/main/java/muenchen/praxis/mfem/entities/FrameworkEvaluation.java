package muenchen.praxis.mfem.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class FrameworkEvaluation implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FrameworkEvaluationID")
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FrameworkID")
    private Framework framework;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ClassificationID")
    private Classification classification;

}
