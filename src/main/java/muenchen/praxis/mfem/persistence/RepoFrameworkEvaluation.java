package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.FrameworkEvaluation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "feva")
public interface RepoFrameworkEvaluation extends CrudRepository<FrameworkEvaluation, Integer> {
}
