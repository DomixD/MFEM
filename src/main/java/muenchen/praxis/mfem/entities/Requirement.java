package muenchen.praxis.mfem.entities;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Requirement implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Priority prior;
	private String content;

	@OneToMany
	private List<Question> questionList;

	public Requirement(String content, Priority prior, List<Question> questionList) {
		this.content = content;
		this.prior = prior;
		this.questionList = questionList;
	}
	
	public Requirement() {}

}
