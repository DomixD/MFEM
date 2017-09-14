package muenchen.praxis.mfem.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@ToString(exclude = "questionList")
@AllArgsConstructor
@NoArgsConstructor
public class Requirement implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RequirementID")
	private int id;
	private String content;
	@OneToMany(mappedBy = "require", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	private List<Question> questionList;
	@ManyToOne
	private Classification classi;
	@ManyToOne
	private Category category;
	private Priority priority;

}
