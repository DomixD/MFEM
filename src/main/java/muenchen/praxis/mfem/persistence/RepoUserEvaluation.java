package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.UserEvaluation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "ueva")
public interface RepoUserEvaluation extends CrudRepository<UserEvaluation, Integer> {
}
