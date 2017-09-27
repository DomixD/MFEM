package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Classification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "classi")
@PreAuthorize("@SecurityService.hasPermission('READ_ACCESS')")
public interface RepoClassification extends CrudRepository<Classification, Integer> {

    @Override
    @PreAuthorize("@SecurityService.hasPermission('CREATE_CLASSI')")
    Classification save(Classification classification);

}
