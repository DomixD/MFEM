package muenchen.praxis.mfem.entities;

import lombok.Data;

@Data
public class Question {
    private String value;

    public Question(String value) {
        this.value = value;
    }

    public Question() {}
}
