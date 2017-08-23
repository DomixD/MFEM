package muenchen.praxis.mfem.entities;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuestionID")
    private int id;
    private String question;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MetricID")
    private Metric metric;

    public Question(String question) {
        this.question = question;
    }

    public Question() {}

}
