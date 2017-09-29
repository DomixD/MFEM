package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Metric;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "metric")
@PreAuthorize("hasAuthority('READ_ACCESS')")
public interface RepoMetric extends CrudRepository<Metric, Integer> {

    @Override
    @PreAuthorize("hasAuthority('CREATE_METRIC')")
    Metric save(Metric metric);

}
