package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "answer")
public interface RepoAnswer extends CrudRepository<Answer, Integer> {
}
