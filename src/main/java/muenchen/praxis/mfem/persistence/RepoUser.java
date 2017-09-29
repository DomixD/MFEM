package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestResource(path = "user")
public interface RepoUser extends CrudRepository<User, Integer> {

    User findByUsername (String username);

    @Override
    @PreAuthorize("hasAuthority('ADMIN_ACCESS')")
    User save(User user);

    @Override
    @PreAuthorize("hasAuthority('ADMIN_ACCESS')")
    void delete(User user);

}
