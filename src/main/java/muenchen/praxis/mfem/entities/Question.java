package muenchen.praxis.mfem.entities;

import lombok.Data;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Question extends ResourceSupport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iD;
    @Column(insertable = false, updatable = false)
    private Link id;
    private String value;

    public Question(String value) {
        this.value = value;
    }

    public Question() {}
}
