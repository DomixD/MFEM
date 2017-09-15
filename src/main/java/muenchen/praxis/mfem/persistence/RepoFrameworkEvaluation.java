package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Classification;
import muenchen.praxis.mfem.entities.Framework;
import muenchen.praxis.mfem.entities.FrameworkEvaluation;
import muenchen.praxis.mfem.entities.Requirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestResource(path = "feva")
public interface RepoFrameworkEvaluation extends CrudRepository<FrameworkEvaluation, Integer> {

    FrameworkEvaluation findByFrameworkInAndClassificationIn(Framework framework, Classification classification);

    List<FrameworkEvaluation> findByClassification(Classification classification);

}
