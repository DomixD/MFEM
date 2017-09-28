package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.RoleAccess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "roles")
//@PreAuthorize("hasAuthority('ADMIN_ACCESS')") //wirft Fehler
public interface RepoRoleAccess extends CrudRepository<RoleAccess, Integer> {
}
