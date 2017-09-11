package muenchen.praxis.mfem.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@ToString(exclude = "require")
@AllArgsConstructor
@NoArgsConstructor
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuestionID")
    private int id;
    private String question;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MetricID")
    private Metric metric;
    @ManyToOne
    private Requirement require;


//    public Question(String question) {
//        this.question = question;
//    }
////
//    public Question() {}

}
