package muenchen.praxis.mfem.entities;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private Metric metric;

    public Question(String question) {
        this.question = question;
    }

    public Question() {}

}
