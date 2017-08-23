package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Framework;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "frame")
public interface RepoFramework extends CrudRepository<Framework, Integer> {
}
