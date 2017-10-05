package muenchen.praxis.mfem.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Framework implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FrameworkID")
    private int id;
    private String nameFW;
    private String descriptionFW;

    public Framework() {}

    public Framework(int id, String nameFW, String descriptionFW) {
        this.id = id;
        this.nameFW = nameFW;
        this.descriptionFW = descriptionFW;
    }

}
