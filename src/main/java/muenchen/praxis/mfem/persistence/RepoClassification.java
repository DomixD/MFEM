package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Classification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "classi")
public interface RepoClassification extends CrudRepository<Classification, Integer> {
}
