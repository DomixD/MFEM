package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "quest")
@PreAuthorize("hasAuthority('READ_ACCESS')")
public interface RepoQuestion extends CrudRepository<Question, Integer> {

    @Override
    @PreAuthorize("hasAuthority('CREATE_QUEST')")
    Question save(Question question);

    @Override
    @PreAuthorize("hasAuthority('CREATE_QUEST')")
    void deleteAll();

}
