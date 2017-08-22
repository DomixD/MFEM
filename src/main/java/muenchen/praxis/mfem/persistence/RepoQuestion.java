package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "quest")
public interface RepoQuestion extends CrudRepository<Question, Integer> {

}
