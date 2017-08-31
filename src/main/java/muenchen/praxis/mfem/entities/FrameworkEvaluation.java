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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FrameworkID")
    private Framework framework;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AnswerID")
    private Answer answer;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "QuestionID")
    private Question question;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RequirementID")
    private Requirement requirement;
    private Priority priority;

}
