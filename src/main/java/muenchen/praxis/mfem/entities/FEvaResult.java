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
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "FrameworkEvaluationID")
    private FrameworkEvaluation frameworkEvaluation;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "QuestionID")
    private Question question;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "AnswerID")
    private Answer answer;

    public FEvaResult() {}

    public FEvaResult(int id, FrameworkEvaluation frameworkEvaluation, Question question, Answer answer) {
        this.id = id;
        this.frameworkEvaluation = frameworkEvaluation;
        this.question = question;
        this.answer = answer;
    }

}
