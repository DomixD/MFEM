package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestResource(path = "result")
public interface RepoFEvaResult extends CrudRepository<FEvaResult, Integer> {

    List<FEvaResult> findByFrameworkEvaluation(FrameworkEvaluation frameworkEvaluation);

}
