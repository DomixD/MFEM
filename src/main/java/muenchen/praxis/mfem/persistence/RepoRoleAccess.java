package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.RoleAccess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "roles")
public interface RepoRoleAccess extends CrudRepository<RoleAccess, Integer> {
}
