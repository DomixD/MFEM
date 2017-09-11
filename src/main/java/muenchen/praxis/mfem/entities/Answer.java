package muenchen.praxis.mfem.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString(exclude = "metri")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnswerID")
    private int id;
    private String content;
    private double value;
    @ManyToOne
    private Metric metri;

}
