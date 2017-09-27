package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Framework;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "frame")
@PreAuthorize("@SecurityService.hasPermission('READ_ACCESS')")
public interface RepoFramework extends CrudRepository<Framework, Integer> {

    @Override
    @PreAuthorize("@SecurityService.hasPermission('EVALUATION')")
    Framework save(Framework framework);

}
