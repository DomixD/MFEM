package muenchen.praxis.mfem.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString(exclude = "metri")
@Entity
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnswerID")
    private int id;
    private String content;
    private double value;
    @ManyToOne
    private Metric metri;

    public Answer () {}

    public Answer(int id, String content, double value, Metric metri) {
        this.id = id;
        this.content = content;
        this.value = value;
        this.metri = metri;
    }

}
