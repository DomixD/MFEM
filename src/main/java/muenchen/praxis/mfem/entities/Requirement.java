package muenchen.praxis.mfem.entities;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Requirement implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RequirementID")
	private int id;
	private String content;
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	private List<Question> questionList;

	public Requirement(String content, List<Question> questionList) {
		this.content = content;
		this.questionList = questionList;
	}
	
	public Requirement() {}

}
