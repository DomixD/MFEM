package muenchen.praxis.mfem.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@ToString(exclude = "questionList")
public class Requirement implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RequirementID")
	private int id;
	private String content;
	@OneToMany(mappedBy = "require", cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	private List<Question> questionList;
	@ManyToOne
	private Classification classi;
	private Priority priority;

	public Requirement(String content, List<Question> questionList) {
		this.content = content;
		this.questionList = questionList;
	}
	
	public Requirement() {}

}
