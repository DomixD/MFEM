package muenchen.praxis.mfem.entities;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;

@Entity
@Data
public class Requirement extends ResourceSupport implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Link id;
	private Priority prior;
	private String content;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH }, fetch = FetchType.EAGER)
	private List<Question> questionList;

	public Requirement(String content, Priority prior, List<Question> questionList) {
		this.content = content;
		this.prior = prior;
		this.questionList = questionList;
	}
	
	public Requirement() {}

}
