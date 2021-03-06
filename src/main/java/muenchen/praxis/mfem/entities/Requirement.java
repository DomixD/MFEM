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
	@OneToMany(mappedBy = "require", cascade = {/*CascadeType.PERSIST, */CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	private List<Question> questionList;
	@ManyToOne
	private Classification classi;
	@ManyToOne
	private Category category;
	private Priority priority;

	public Requirement() {}

	public Requirement(int id, String content, List<Question> questionList, Classification classi, Category category, Priority priority) {
		this.id = id;
		this.content = content;
		this.questionList = questionList;
		this.classi = classi;
		this.category = category;
		this.priority = priority;
	}

}
