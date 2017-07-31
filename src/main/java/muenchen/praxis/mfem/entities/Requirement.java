package muenchen.praxis.mfem.entities;

import java.util.List;
import lombok.Data;

@Data
public class Requirement {
	private Priority prio;
	private String content;
	private List<Question> questionList;

	public Requirement(String content, Priority prio, List<Question> questionList) {
		this.content = content;
		this.prio = prio;
		this.questionList = questionList;
	}
}
