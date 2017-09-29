package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "permission")
@PreAuthorize("hasAuthority('ADMIN_ACCESS')")
public interface RepoPermission extends CrudRepository<Permission, Integer> {
}
