package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "user")
//@PreAuthorize("@SecurityService.hasPermission('ADMIN_ACCESS')") //Authorisierung funktioniert nicht mehr
public interface RepoUser extends CrudRepository<User, Integer> {
    User findByUsername (String username);
}
