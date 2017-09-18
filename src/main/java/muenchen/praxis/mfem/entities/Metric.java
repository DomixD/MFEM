package muenchen.praxis.mfem.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@ToString(exclude = "answerList")
@Entity
public class Metric implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MetricID")
    private int id;

    private String description;

    @OneToMany(mappedBy = "metri", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    private List<Answer> answerList;

    public Metric () {}

    public Metric (int id, String description, List<Answer> answerList) {
        this.id = id;
        this.description = description;
        this.answerList = answerList;
    }

}
