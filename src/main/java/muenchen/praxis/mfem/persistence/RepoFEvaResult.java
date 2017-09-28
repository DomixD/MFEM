package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestResource(path = "result")
@PreAuthorize("hasAuthority('READ_ACCESS')")
public interface RepoFEvaResult extends CrudRepository<FEvaResult, Integer> {

    List<FEvaResult> findByFrameworkEvaluation(FrameworkEvaluation frameworkEvaluation);

    @Override
    @PreAuthorize("hasAuthority('EVALUATION')")
    FEvaResult save(FEvaResult fEvaResult);

}
