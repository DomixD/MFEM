package muenchen.praxis.mfem.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class FEvaResult implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEvaResultID")
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FrameworkEvaluationID")
    private FrameworkEvaluation frameworkEvaluation;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "QuestionID")
    private Question question;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AnswerID")
    private Answer answer;

}
