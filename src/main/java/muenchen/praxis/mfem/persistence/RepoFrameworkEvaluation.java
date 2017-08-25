package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.FrameworkEvaluation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "feva")
public interface RepoFrameworkEvaluation extends CrudRepository<FrameworkEvaluation, Integer> {
}