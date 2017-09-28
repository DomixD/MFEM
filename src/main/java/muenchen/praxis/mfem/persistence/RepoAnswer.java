package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "answer")
@PreAuthorize("hasAuthority('READ_ACCESS')")
public interface RepoAnswer extends CrudRepository<Answer, Integer> {

    @Override
    @PreAuthorize("hasAuthority('CREATE_METRIC')")
    Answer save(Answer answer);

}
