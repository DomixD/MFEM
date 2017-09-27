package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Requirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "req")
@PreAuthorize("@SecurityService.hasPermission('READ_ACCESS')")
public interface RepoRequirement extends CrudRepository<Requirement, Integer> {

    @Override
    @PreAuthorize("@SecurityService.hasPermission('CREATE_REQ')")
    Requirement save(Requirement requirement);

}
