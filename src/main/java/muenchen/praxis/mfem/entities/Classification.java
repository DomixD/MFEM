package muenchen.praxis.mfem.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassificationID")
    private int id;
    private String name;
    private String description;

}
