package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestResource(path = "user")
//@PreAuthorize("hasAuthority('ADMIN_ACCESS')") //Authorisierung funktioniert nicht mehr
public interface RepoUser extends CrudRepository<User, Integer> {
    User findByUsername (String username);
}
