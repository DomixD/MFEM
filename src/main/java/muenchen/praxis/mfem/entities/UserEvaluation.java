package muenchen.praxis.mfem.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class UserEvaluation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserEvaluationID")
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RequirementID")
    private Requirement requirement;
    private Priority priority;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "QuestionID")
    private Question question;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AnswerID")
    private Answer answer;

}
