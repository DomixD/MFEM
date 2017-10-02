package muenchen.praxis.mfem.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@ToString(exclude = "require")
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuestionID")
    private int id;
    private String question;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "MetricID")
    private Metric metric;
    @ManyToOne
    private Requirement require;

    public Question () {}

    public Question (int id, String question, Metric metric, Requirement requirement) {
        this. id = id;
        this.question = question;
        this.metric = metric;
        this.require = requirement;
    }

}
